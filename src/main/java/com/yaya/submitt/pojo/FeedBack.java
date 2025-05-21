package com.yaya.submitt.pojo;

import jakarta.persistence.*;
import java.util.Date;

@Table(name = "FEEDBACK")
@Entity
public class FeedBack {
    @Id
    @Column(name = "fee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feeId;

    @Column(name = "client_ip")
    private String clientIp;

    @Column(name = "analysis_type")
    private String analysisType;

    @Column(name = "feedback_rating")
    private Integer feedbackRating;

    @Column(name = "feedback_comment", columnDefinition = "TEXT")
    private String feedbackComment;

    @Column(name = "timestamp", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    // Getters and Setters

    public Integer getFeeId() {
        return feeId;
    }

    public void setFeeId(Integer feeId) {
        this.feeId = feeId;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
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
        return "Feedback{" +
                "feeId=" + feeId +
                ", clientIp='" + clientIp + '\'' +
                ", analysisType='" + analysisType + '\'' +
                ", feedbackRating=" + feedbackRating +
                ", feedbackComment='" + feedbackComment + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}