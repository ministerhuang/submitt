package com.yaya.submitt.pojo;

import jakarta.persistence.*;
import java.util.Date;

@Table(name = "WEBSITE")
@Entity
public class Website {
    @Id
    @Column(name = "website_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer websiteId;

    @Column(name = "website_domain", nullable = false)
    private String websiteDomain;

    @Column(name = "url_pattern", columnDefinition = "TEXT")
    private String urlPattern;

    @Column(name = "is_phishing")
    private Boolean isPhishing;

    @Column(name = "verification_status")
    private String verificationStatus;

    @Column(name = "last_updated", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @Column(name = "source")
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
        return "Website{" +
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