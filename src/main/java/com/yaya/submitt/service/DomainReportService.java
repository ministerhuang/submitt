package com.yaya.submitt.service;

import com.yaya.submitt.pojo.DomainInfo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.*;
import java.time.chrono.ChronoZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

@Service
public class DomainReportService {

    private static final String API_KEY = "7878e4cfb4b6e304119c604f91ff67c3a9a8e3fdaaa41a7a80bd4f8b800348f4";
    private static final String DOMAIN_URL = "https://www.virustotal.com/api/v3/domains/";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public DomainReportService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * 获取域名报告
     *
     * @param domain 域名
     * @return 返回封装好的DomainInfo对象，包含相关信息
     */
    public DomainInfo getDomainReport(String domain) {
        try {
            DomainInfo domainInfo = new DomainInfo();

            domain = cleanDomainString(domain);
            String domainReport = fetchDomainReport(domain);
            JsonNode rootNode = objectMapper.readTree(domainReport);
            JsonNode attributes = rootNode.path("data").path("attributes");
            JsonNode lastAnalysisStats = attributes.path("last_analysis_stats");

            // 计算官方评分
            int malicious = lastAnalysisStats.path("malicious").asInt();
            int suspicious = lastAnalysisStats.path("suspicious").asInt();
            int undetected = lastAnalysisStats.path("undetected").asInt();
            int harmless = lastAnalysisStats.path("harmless").asInt();
            int timeout = lastAnalysisStats.path("timeout").asInt();

            int total = malicious + suspicious + undetected + harmless + timeout;
            float officialScore = (total == 0) ? 0f :
                    (40f * harmless / total) - (10f * malicious) - (5f * suspicious);
            officialScore = Math.max(0, (float) Math.floor(officialScore));

            domainInfo.setOfficialScore(officialScore);

            if(suspicious!=0 || malicious!= 0){
                if(harmless > malicious){
                    domainInfo.setOfficialVerdict(suspicious+" agency flagged website as suspicious");
                } else{
                    domainInfo.setOfficialVerdict(malicious+" agency flagged website as malicious");
                }
            } else{
                domainInfo.setOfficialVerdict("Harmless");
            }

            // 域名注册时间评分
            long creationTimestamp = attributes.path("creation_date").asLong();
            Instant creationInstant = Instant.ofEpochSecond(creationTimestamp);
            ZonedDateTime creationDate = creationInstant.atZone(ZoneId.systemDefault());
            ZonedDateTime nowZoned = ZonedDateTime.now();

            long totalMonths = ChronoUnit.MONTHS.between(creationDate, nowZoned);
            long years = totalMonths / 12;
            long months = totalMonths % 12;

            float domainAgeScore = totalMonths > 12 ? 15f :
                    totalMonths > 6 ? 10f :
                            totalMonths >= 1 ? 5f : 0f;

            // 创建时间去掉时分秒
            String domainAgeDesc = creationDate.toLocalDate().toString(); // 例：2024-03-18

            // 拼接年龄描述
            StringBuilder ageText = new StringBuilder("Domain created ");
            if (years > 0) {
                ageText.append(years).append(years == 1 ? " year" : " years");
            }
            if (months > 0) {
                if (years > 0) ageText.append(" ");
                ageText.append(months).append(months == 1 ? " month" : " months");
            }
            if (years == 0 && months == 0) {
                ageText.append("less than a month");
            }
            ageText.append(" ago. ");

            // 加入建议部分
            String suggestion;
            if (totalMonths < 3) {
                suggestion = "Beware of suspicious activities.";
            } else if (totalMonths < 12) {
                suggestion = "Consider verifying its trustworthiness.";
            } else {
                suggestion = "Domain appears to have a reliable history.";
            }

            String domainAgeDescriptionVerdict = ageText.toString() + suggestion;
            domainInfo.setDomainAgeDescriptionVerdict(domainAgeDescriptionVerdict);
            domainInfo.setDomainAgeDescriptionScore(domainAgeScore);

            // 证书颁发者评分
            JsonNode issuerNode = attributes.path("last_https_certificate").path("issuer");
            String organization = issuerNode.path("O").asText();
            String commonName = issuerNode.path("CN").asText();
            String issuer = organization + " (" + commonName + ")";
            float sslCaScore = (organization != null && !organization.isEmpty()) ? 10f : 0f;
            String sslCaResult = "Certificate issuer: " + issuer;
            domainInfo.setSslCaVerdict("Trust status: " + (sslCaScore == 10f ? "Trusted." : "Untrusted."));
            domainInfo.setSslCaScore(sslCaScore);
            // SSL 证书有效期处理
            JsonNode validity = attributes.path("last_https_certificate").path("validity");
            String notBeforeStr = validity.path("not_before").asText().substring(0, 10);
            String notAfterStr = validity.path("not_after").asText().substring(0, 10);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate notBefore = LocalDate.parse(notBeforeStr, formatter);
            LocalDate notAfter = LocalDate.parse(notAfterStr, formatter);
            LocalDate now = LocalDate.now();

            long totalMonths_cer = ChronoUnit.MONTHS.between(notBefore, notAfter);
            long remainingMonths = ChronoUnit.MONTHS.between(now, notAfter);

            // SSL有效期建议
            String validityAdvice;
            if (now.isAfter(notAfter)) {
                validityAdvice = "Certificate has expired. Security risk.";
            } else if (now.isBefore(notBefore)) {
                validityAdvice = "Certificate not yet valid. Suspicious configuration.";
            } else if (totalMonths_cer < 3) {
                validityAdvice = "Very short validity period. Likely insecure.";
            } else if (totalMonths_cer < 6) {
                validityAdvice = "Short-term certificate. Monitor usage.";
            } else if (totalMonths_cer < 12) {
                validityAdvice = "Moderate certificate duration. Generally acceptable.";
            } else {
                validityAdvice = "Long-term certificate. Good practice.";
            }

            float validityScore = totalMonths_cer >= 12 ? 15f :
                    totalMonths_cer >= 6 ? 10f :
                            totalMonths_cer >= 3 ? 5f : 0f;
            if (remainingMonths > 1) validityScore += 5f;

            String sslValidityResult = "SSL Valid until: " + notAfter +
                    ", Issued on: " + notAfter;
            domainInfo.setSslValidityVerdict(validityAdvice);
            domainInfo.setSslValidityScore(validityScore);

            // SSL 公钥算法分析
            JsonNode pubKeyNode = attributes.path("last_https_certificate").path("public_key");
            String keyAlg = pubKeyNode.path("algorithm").asText();
            String keyBitsStr = pubKeyNode.path("bits").asText();
            int keyBits = 0;
            try {
                keyBits = Integer.parseInt(keyBitsStr);
            } catch (NumberFormatException ignored) {}

            String keyAdvice;
            float sslKeyScore;

            if ("EC".equalsIgnoreCase(keyAlg)) {
                if (keyBits < 224) {
                    keyAdvice = "Weak ECC key (less than 224 bits). Security risk.";
                    sslKeyScore = 5f;
                } else {
                    keyAdvice = "Elliptic Curve (EC) key with strong cryptography.";
                    sslKeyScore = 15f;
                }
            } else if (keyAlg.toLowerCase().contains("rsa")) {
                if (keyBits < 2048) {
                    keyAdvice = "Weak RSA key (less than 2048 bits). Security risk.";
                    sslKeyScore = 3f;
                } else {
                    keyAdvice = "RSA key with acceptable strength.";
                    sslKeyScore = 5f;
                }
            } else {
                keyAdvice = "Unknown or uncommon key algorithm. Manual review recommended.";
                sslKeyScore = 0f;
            }

            String sigOid = attributes.path("last_https_certificate").path("cert_signature").path("signature_algorithm").asText();
            String sigAlg;

            switch (sigOid) {
                case "1.2.840.10045.4.3.2":
                    sigAlg = "SHA256withECDSA"; break;
                case "1.2.840.113549.1.1.11":
                    sigAlg = "SHA256withRSA"; break;
                // 可添加更多 OID 映射
                default:
                    sigAlg = "Unknown";
            }

            String sslKeyResult = "Key algorithm: " + keyAlg +
                    (keyBits > 0 ? " (" + keyBits + " bits)" : "") + "Signature algorithm: "+ sigAlg;

            domainInfo.setSslKeyVerdict("Advice: " + keyAdvice);
            domainInfo.setSslKeyScore(sslKeyScore);

            // 构造 DomainInfo 返回对象

            domainInfo.setDomainAgeDescription("Created on: "+domainAgeDesc);
            domainInfo.setSslCaResult(sslCaResult);
            domainInfo.setSslValidityResult(sslValidityResult);
            domainInfo.setSslKeyResult(sslKeyResult);

            // 总评分四舍五入取整（float 格式）
            float allScore = officialScore + domainAgeScore + sslCaScore + validityScore + sslKeyScore;
            domainInfo.setAllScore((float) Math.round(allScore));

            domainInfo.setOfficialResults(String.format(
                    "Rated by %d engines. Harmless: %d, Malicious: %d, Suspicious: %d, Undetected: %d",
                    total, harmless, malicious, suspicious, undetected));

            return domainInfo;

        } catch (Exception e) {
            e.printStackTrace();
            return new DomainInfo();
        }
    }
    /**
     * 清理传入的域名字符串，去掉http://或https://前缀，去掉主域名后面的部分
     *
     * @param domain 输入的域名字符串
     * @return 清理后的域名
     */
    private String cleanDomainString(String domain) {
        if (domain.startsWith("https://")) {
            domain = domain.substring(8);
        } else if (domain.startsWith("http://")) {
            domain = domain.substring(7);
        }

        // 处理域名后缀，去掉主域名后面的部分
        int endIndex = domain.indexOf("/", 0);
        if (endIndex != -1) {
            domain = domain.substring(0, endIndex);
        }

        // 返回清理后的域名
        return domain;
    }

    private String fetchDomainReport(String domain) throws Exception {
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-apikey", API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 创建请求实体
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        // 构建获取域名报告的URL
        String url = DOMAIN_URL + domain;

        // 发送GET请求
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                String.class
        );

        // 返回响应体
        return response.getBody();
    }
}