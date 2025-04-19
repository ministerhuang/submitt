package com.yaya.submitt.pojo;

import jakarta.persistence.*;

@Table(name = "PERFORMANCE")
@Entity
public class Performance {
    @Id
    @Column(name = "per_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer perId;

    @Column(name = "mod_id")
    private Integer modId;

    @Column(name = "accuracy")
    private Float accuracy;

    @Column(name = "precision_score")
    private Float precisionScore;

    @Column(name = "recall_score")
    private Float recallScore;

    @Column(name = "f1_score")
    private Float f1Score;

    @Column(name = "false_positive_rate")
    private Float falsePositiveRate;

    @Column(name = "false_negative_rate")
    private Float falseNegativeRate;

    @Column(name = "evaluation_date")
    private java.sql.Date evaluationDate;

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

    public java.sql.Date getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(java.sql.Date evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    @Override
    public String toString() {
        return "Performance{" +
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