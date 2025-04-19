package com.yaya.submitt.pojo.dto;

import java.util.Date;

public class FeedbackDto {
    private Integer feeId;
    private Integer logId;
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

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
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
                ", logId=" + logId +
                ", feedbackRating=" + feedbackRating +
                ", feedbackComment='" + feedbackComment + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}