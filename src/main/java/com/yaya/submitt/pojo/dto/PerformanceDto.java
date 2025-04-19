package com.yaya.submitt.pojo.dto;

import java.sql.Date;

public class PerformanceDto {
    private Integer perId;
    private Integer modId;
    private Float accuracy;
    private Float precisionScore;
    private Float recallScore;
    private Float f1Score;
    private Float falsePositiveRate;
    private Float falseNegativeRate;
    private Date evaluationDate;

    // Getters and Setters

    public Integer getPerId() {
        return perId;
    }

    public void setPerId(Integer perId) {
        this.perId = perId;
    }

    public Integer getModId() {
        return modId;
    }

    public void setModId(Integer modId) {
        this.modId = modId;
    }

    public Float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Float accuracy) {
        this.accuracy = accuracy;
    }

    public Float getPrecisionScore() {
        return precisionScore;
    }

    public void setPrecisionScore(Float precisionScore) {
        this.precisionScore = precisionScore;
    }

    public Float getRecallScore() {
        return recallScore;
    }

    public void setRecallScore(Float recallScore) {
        this.recallScore = recallScore;
    }

    public Float getF1Score() {
        return f1Score;
    }

    public void setF1Score(Float f1Score) {
        this.f1Score = f1Score;
    }

    public Float getFalsePositiveRate() {
        return falsePositiveRate;
    }

    public void setFalsePositiveRate(Float falsePositiveRate) {
        this.falsePositiveRate = falsePositiveRate;
    }

    public Float getFalseNegativeRate() {
        return falseNegativeRate;
    }

    public void setFalseNegativeRate(Float falseNegativeRate) {
        this.falseNegativeRate = falseNegativeRate;
    }

    public Date getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(Date evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    @Override
    public String toString() {
        return "PerformanceDto{" +
                "perId=" + perId +
                ", modId=" + modId +
                ", accuracy=" + accuracy +
                ", precisionScore=" + precisionScore +
                ", recallScore=" + recallScore +
                ", f1Score=" + f1Score +
                ", falsePositiveRate=" + falsePositiveRate +
                ", falseNegativeRate=" + falseNegativeRate +
                ", evaluationDate=" + evaluationDate +
                '}';
    }
}