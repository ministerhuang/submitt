package com.yaya.submitt.service;
import javax.net.ssl.*;
import java.net.URL;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Service for analyzing SSL certificates of domains
 */
public class SSLCertificateAnalyzer {
    private int ca_score = 0;
    private int validity_score = 0;

    public int getCa_score() {
        return ca_score;
    }

    public void setCa_score(int ca_score) {
        this.ca_score = ca_score;
    }

    public int getValidity_score() {
        return validity_score;
    }

    public void setValidity_score(int validity_score) {
        this.validity_score = validity_score;
    }

    private static final Set<String> TRUSTED_CAS = new HashSet<>(Arrays.asList(
            "CN=DigiCert", "CN=Let's Encrypt", "CN=Sectigo",
            "CN=Amazon", "CN=GlobalSign", "CN=GoDaddy",
            "CN=Comodo", "CN=Entrust", "CN=Google Trust Services",
            "CN=Microsoft", "CN=IdenTrust", "CN=RapidSSL",
            "CN=Symantec", "CN=Thawte", "CN=Verisign"
    ));

    /**
     * Analyzes the SSL certificate of a given URL
     *
     * @param urlString URL to analyze
     * @return Map with three analysis statements about the certificate
     */
    public Map<String, String> analyzeCertificate(String urlString) {
        Map<String, String> result = new HashMap<>();
        result.put("issuerAnalysis", "");
        result.put("validityAnalysis", "");
        result.put("cryptoAnalysis", "");

        try {

            URL url = new URL(urlString);
            String host = url.getHost();

            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() { return null; }
                        public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException { }
                        public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException { }
                    }
            };

            // Install the trust manager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create socket factory
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            // Open connection
            SSLSocket socket = (SSLSocket) sslSocketFactory.createSocket(host, 443);
            socket.setSoTimeout(10000); // 10 seconds timeout

            try {
                socket.startHandshake();
                SSLSession session = socket.getSession();
                Certificate[] certs = session.getPeerCertificates();

                if (certs.length > 0 && certs[0] instanceof X509Certificate) {
                    X509Certificate cert = (X509Certificate) certs[0];

                    // 1. Analyze issuer
                    result.put("issuerAnalysis", analyzeIssuer(cert));

                    // 2. Analyze validity period
                    result.put("validityAnalysis", analyzeValidity(cert));

                    // 3. Analyze key and signature algorithm
                    result.put("cryptoAnalysis", analyzeCrypto(cert));
                }
            } finally {
                socket.close();
            }

        } catch (Exception e) {
            // Log the error but keep empty results
            System.err.println("Error analyzing certificate: " + e.getMessage());
        }

        return result;
    }

    /**
     * Analyzes the certificate issuer
     */
    private String analyzeIssuer(X509Certificate cert) {
        String issuerDN = cert.getIssuerX500Principal().getName();

        // Extract CN part
        String issuerCN = "Unknown";
        for (String part : issuerDN.split(",")) {
            if (part.trim().startsWith("CN=")) {
                issuerCN = part.trim().substring(3);
                break;
            }
        }

        // Check if issuer is in trusted list
        boolean isTrusted = false;
        for (String trustedCA : TRUSTED_CAS) {
            if (issuerDN.contains(trustedCA)) {
                isTrusted = true;
                break;
            }
        }

        String trustStatus = isTrusted ? "Trusted" : "Untrusted";

        return "Certificate issuer: " + issuerCN + ". Trust status: " + trustStatus + ".";
    }

    /**
     * Analyzes the certificate validity period
     */
    private String analyzeValidity(X509Certificate cert) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date notBefore = cert.getNotBefore();
        Date notAfter = cert.getNotAfter();
        Date now = new Date();

        // Calculate days
        long certAgeDays = TimeUnit.MILLISECONDS.toDays(now.getTime() - notBefore.getTime());
        long totalValidityDays = TimeUnit.MILLISECONDS.toDays(notAfter.getTime() - notBefore.getTime());

        // Build advice
        String advice = "";
        if (now.after(notAfter)) {
            setValidity_score(0);
            advice = "Certificate has expired. Security risk.";
        } else if (now.before(notBefore)) {
            setValidity_score(0);
            advice = "Certificate not yet valid. Suspicious configuration.";
        } else if (certAgeDays < 7 && totalValidityDays < 90) {
            advice = "Recently issued short-term certificate. Potentially suspicious.";
            setValidity_score(1);
        } else if (certAgeDays < 30) {
            setValidity_score(3);
            advice = "Recently issued certificate. Monitor for suspicious activity.";
        } else if (TimeUnit.MILLISECONDS.toDays(notAfter.getTime() - now.getTime()) < 30) {
            setValidity_score(3);
            advice = "Certificate expiring soon. Renewal recommended.";
        } else {
            setValidity_score(20);
            advice = "Certificate validity period is normal.";
        }

        return "SSL Start on: " + sdf.format(notBefore) +
                ". Valid until: " + sdf.format(notAfter) +
                ". Advice: " + advice;
    }

    /**
     * Analyzes the certificate's cryptographic properties
     */
    private String analyzeCrypto(X509Certificate cert) {
        // Get key algorithm and size
        String keyAlgorithm = cert.getPublicKey().getAlgorithm();
        int keySize = getKeySize(cert.getPublicKey());

        // Get signature algorithm
        String signatureAlgorithm = cert.getSigAlgName();

        // Build advice
        String advice = "";
        boolean hasWeakness = false;

        // Check key strength
        if ("RSA".equals(keyAlgorithm) && keySize < 2048) {
            advice = "Weak RSA key (less than 2048 bits). Security risk.";
            hasWeakness = true;
        } else if ("DSA".equals(keyAlgorithm) && keySize < 2048) {
            advice = "Weak DSA key (less than 2048 bits). Security risk.";
            hasWeakness = true;
        } else if ("EC".equals(keyAlgorithm) && keySize < 224) {
            advice = "Weak ECC key (less than 224 bits). Security risk.";
            hasWeakness = true;
        }

        // Check signature algorithm
        if (signatureAlgorithm.contains("MD5")) {
            advice = "MD5 signature algorithm is broken. Critical security risk.";
            hasWeakness = true;
        } else if (signatureAlgorithm.contains("SHA1") || signatureAlgorithm.contains("SHA-1")) {
            advice = "SHA-1 signature algorithm is weak. Security risk.";
            hasWeakness = true;
        }

        if (!hasWeakness) {
            advice = "Cryptographic configuration is strong.";
            setCa_score(10);
        } else{
            setCa_score(5);
        }

        return "Key algorithm: " + keyAlgorithm + " (" + keySize + " bits)" +
                ". Signature algorithm: " + signatureAlgorithm +
                ". Advice: " + advice;
    }

    /**
     * Gets the key size in bits
     */
    private int getKeySize(PublicKey publicKey) {
        if (publicKey instanceof RSAPublicKey) {
            return ((RSAPublicKey) publicKey).getModulus().bitLength();
        } else if (publicKey instanceof DSAPublicKey) {
            return ((DSAPublicKey) publicKey).getParams().getP().bitLength();
        } else if (publicKey instanceof ECPublicKey) {
            return ((ECPublicKey) publicKey).getParams().getCurve().getField().getFieldSize();
        }
        return 0;
    }

    /**
     * Main method for testing
     */
//    public static void main(String[] args) {
//        SSLCertificateAnalyzer analyzer = new SSLCertificateAnalyzer();
//
//        // Example usage
//        String testUrl = "github.com";
//        Map<String, String> analysis = analyzer.analyzeCertificate(testUrl);
//
//        System.out.println("SSL Certificate Analysis for " + testUrl);
//        System.out.println("--------------------------------------");
//        System.out.println(analysis.get("issuerAnalysis"));
//        System.out.println(analysis.get("validityAnalysis"));
//        System.out.println(analysis.get("cryptoAnalysis"));
//    }
}