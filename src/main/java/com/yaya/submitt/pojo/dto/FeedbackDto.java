package com.yaya.submitt.pojo.dto;

import jakarta.persistence.Column;

import java.util.Date;

public class FeedbackDto {
    private Integer feeId;
    private String clientIp;
    private String analysisType;
    private Integer feedbackRating;
    private String feedbackComment;
    private Date timestamp;

    // Getters and Setters

    public Integer getFeeId() {
        return feeId;
    }

    public void setFeeId(Integer feeId) {
        this.feeId = feeId;
    }

    public String getClintIp() {
        return clientIp;
    }

    public void setClintIp(String clintIp) {
        this.clientIp = clintIp;
    }

    public String getAnalysisType() {
        return analysisType;
    }

    public void setAnalysisType(String analysisType) {
        this.analysisType = analysisType;
    }

    public Integer getFeedbackRating() {
        return feedbackRating;
    }

    public void setFeedbackRating(Integer feedbackRating) {
        this.feedbackRating = feedbackRating;
    }

    public String getFeedbackComment() {
        return feedbackComment;
    }

    public void setFeedbackComment(String feedbackComment) {
        this.feedbackComment = feedbackComment;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "FeedbackDto{" +
                "feeId=" + feeId +
                ", clintIp='" + clientIp + '\'' +
                ", analysisType='" + analysisType + '\'' +
                ", feedbackRating=" + feedbackRating +
                ", feedbackComment='" + feedbackComment + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}