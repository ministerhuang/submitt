package com.yaya.submitt.pojo.dto;

import java.util.Date;

public class WebsiteDto {
    private Integer websiteId;
    private String websiteDomain;
    private String urlPattern;
    private Boolean isPhishing;
    private String verificationStatus;
    private Date lastUpdated;
    private String source;

    // Getters and Setters

    public Integer getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(Integer websiteId) {
        this.websiteId = websiteId;
    }

    public String getWebsiteDomain() {
        return websiteDomain;
    }

    public void setWebsiteDomain(String websiteDomain) {
        this.websiteDomain = websiteDomain;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public Boolean getIsPhishing() {
        return isPhishing;
    }

    public void setIsPhishing(Boolean isPhishing) {
        this.isPhishing = isPhishing;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "WebsiteDto{" +
                "websiteId=" + websiteId +
                ", websiteDomain='" + websiteDomain + '\'' +
                ", urlPattern='" + urlPattern + '\'' +
                ", isPhishing=" + isPhishing +
                ", verificationStatus='" + verificationStatus + '\'' +
                ", lastUpdated=" + lastUpdated +
                ", source='" + source + '\'' +
                '}';
    }
}