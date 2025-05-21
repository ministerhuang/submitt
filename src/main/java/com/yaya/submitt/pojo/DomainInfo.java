package com.yaya.submitt.pojo;

public class DomainInfo {
    private String officialResults;
    private String sslCaResult;
    private String sslValidityResult;
    private String sslKeyResult;
    private String domainAgeDescription; // 例如："已注册16年"

    private String officialVerdict;
    private String sslCaVerdict;
    private String sslValidityVerdict;
    private String sslKeyVerdict;
    private String domainAgeDescriptionVerdict;

    private float officialScore;
    private float allScore;
    private float sslCaScore;
    private float sslValidityScore;
    private float sslKeyScore;
    private float domainAgeDescriptionScore;

    // Getter 和 Setter 方法
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

    public String getDomainAgeDescription() {
        return domainAgeDescription;
    }

    public void setDomainAgeDescription(String domainAgeDescription) {
        this.domainAgeDescription = domainAgeDescription;
    }

    public float getAllScore() {
        return allScore;
    }

    public void setAllScore(float allScore) {
        this.allScore = allScore;
    }

    public String getOfficialVerdict() {
        return officialVerdict;
    }

    public void setOfficialVerdict(String officialVerdict) {
        this.officialVerdict = officialVerdict;
    }

    public String getSslCaVerdict() {
        return sslCaVerdict;
    }

    public void setSslCaVerdict(String sslCaVerdict) {
        this.sslCaVerdict = sslCaVerdict;
    }

    public String getSslValidityVerdict() {
        return sslValidityVerdict;
    }

    public void setSslValidityVerdict(String sslValidityVerdict) {
        this.sslValidityVerdict = sslValidityVerdict;
    }

    public String getSslKeyVerdict() {
        return sslKeyVerdict;
    }

    public void setSslKeyVerdict(String sslKeyVerdict) {
        this.sslKeyVerdict = sslKeyVerdict;
    }

    public String getDomainAgeDescriptionVerdict() {
        return domainAgeDescriptionVerdict;
    }

    public void setDomainAgeDescriptionVerdict(String domainAgeDescriptionVerdict) {
        this.domainAgeDescriptionVerdict = domainAgeDescriptionVerdict;
    }

    public float getOfficialScore() {
        return officialScore;
    }

    public void setOfficialScore(float officialScore) {
        this.officialScore = officialScore;
    }

    public float getSslCaScore() {
        return sslCaScore;
    }

    public void setSslCaScore(float sslCaScore) {
        this.sslCaScore = sslCaScore;
    }

    public float getSslValidityScore() {
        return sslValidityScore;
    }

    public void setSslValidityScore(float sslValidityScore) {
        this.sslValidityScore = sslValidityScore;
    }

    public float getSslKeyScore() {
        return sslKeyScore;
    }

    public void setSslKeyScore(float sslKeyScore) {
        this.sslKeyScore = sslKeyScore;
    }

    public float getDomainAgeDescriptionScore() {
        return domainAgeDescriptionScore;
    }

    public void setDomainAgeDescriptionScore(float domainAgeDescriptionScore) {
        this.domainAgeDescriptionScore = domainAgeDescriptionScore;
    }
}
