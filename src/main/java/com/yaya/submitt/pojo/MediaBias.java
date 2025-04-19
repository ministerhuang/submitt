package com.yaya.submitt.pojo;

import jakarta.persistence.*;
import java.util.Date;

@Table(name = "MEDIABIAS")
@Entity
public class MediaBias {
    @Id
    @Column(name = "media_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mediaId;

    @Column(name = "media_name", nullable = false)
    private String mediaName;

    @Column(name = "media_domain", nullable = false)
    private String mediaDomain;

    @Column(name = "bias_rating")
    private String biasRating;

    @Column(name = "bias_rating_score")
    private Double biasRatingScore;

    @Column(name = "last_updated", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @Column(name = "rating_source")
    private String ratingSource;

    @Column(name = "bias_level")
    private String biasLevel;

    // Getters and Setters

    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getMediaDomain() {
        return mediaDomain;
    }

    public void setMediaDomain(String mediaDomain) {
        this.mediaDomain = mediaDomain;
    }

    public String getBiasRating() {
        return biasRating;
    }

    public void setBiasRating(String biasRating) {
        this.biasRating = biasRating;
    }

    public Double getBiasRatingScore() {
        return biasRatingScore;
    }

    public void setBiasRatingScore(Double biasRatingScore) {
        this.biasRatingScore = biasRatingScore;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getRatingSource() {
        return ratingSource;
    }

    public void setRatingSource(String ratingSource) {
        this.ratingSource = ratingSource;
    }

    public String getBiasLevel() {
        return biasLevel;
    }

    public void setBiasLevel(String biasLevel) {
        this.biasLevel = biasLevel;
    }

    @Override
    public String toString() {
        return "MediaBias{" +
                "mediaId=" + mediaId +
                ", mediaName='" + mediaName + '\'' +
                ", mediaDomain='" + mediaDomain + '\'' +
                ", biasRating='" + biasRating + '\'' +
                ", biasRatingScore=" + biasRatingScore +
                ", lastUpdated=" + lastUpdated +
                ", ratingSource='" + ratingSource + '\'' +
                '}';
    }
}