package com.yaya.submitt.pojo;

public class urlResult {
    private String googleResult; //谷歌api结果
    private String officialResults; //api结果

    private String sslCaResult;  //证书颁发机构
    private String sslValidityResult;  //证书有效期
    private String sslKeyResult; //证书密钥强度

    public String getGoogleResult() {
        return googleResult;
    }

    public void setGoogleResult(String googleResult) {
        this.googleResult = googleResult;
    }

    public String getOfficialResults() {
        return officialResults;
    }

    public void setOfficialResults(String officialResults) {
        this.officialResults = officialResults;
    }

    public String getSslCaResult() {
        return sslCaResult;
    }

    public void setSslCaResult(String sslCaResult) {
        this.sslCaResult = sslCaResult;
    }

    public String getSslValidityResult() {
        return sslValidityResult;
    }

    public void setSslValidityResult(String sslValidityResult) {
        this.sslValidityResult = sslValidityResult;
    }

    public String getSslKeyResult() {
        return sslKeyResult;
    }

    public void setSslKeyResult(String sslKeyResult) {
        this.sslKeyResult = sslKeyResult;
    }
}
