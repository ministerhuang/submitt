package com.yaya.submitt.pojo.dto;

import java.util.Date;

public class MediaBiasDto {
    private Integer mediaId;
    private String mediaName;
    private String mediaDomain;
    private String biasRating;
    private Double biasRatingScore;
    private Date lastUpdated;
    private String ratingSource;
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
        return "MediaBiasDto{" +
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