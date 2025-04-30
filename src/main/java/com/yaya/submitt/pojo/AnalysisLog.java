package com.yaya.submitt.pojo;

import jakarta.persistence.*;
import java.util.Date;

@Table(name = "ANALYSISLOG")
@Entity
public class AnalysisLog {
    @Id
    @Column(name = "log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logId;
    //分析-网站验证，图像检测，新闻分析
    @Column(name = "analysis_type")
    private String analysisType;
    //结果摘要
    @Column(name = "result_summary", columnDefinition = "TEXT")
    private String resultSummary;
    //置信分数(先不写)
    @Column(name = "confidence_score")
    private Float confidenceScore;
    //处理时间
    @Column(name = "processing_time_ms")
    private Integer processingTimeMs;
    //模型ID
    @Column(name = "model_id")
    private Integer modelId;
    //当前时间
    @Column(name = "timestamp", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    //不写
    //@Column(name = "client_ip")
    //private String clientIp;
    //不写
    @Column(name = "website_domain")
    private String websiteDomain;

    @Column(name = "media_name")
    private String mediaName;

    @Transient
    private String mediaBiasRating;

    @Transient
    private Double mediaBiasScore;

    @Transient
    private String captureDate;

    @Transient
    private String location;

    @Transient
    private String cameraModel;

    @Transient
    private String biasLevel;

    @Transient
    private urlResult urlResult;


    // Getters and Setters

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getAnalysisType() {
        return analysisType;
    }

    public void setAnalysisType(String analysisType) {
        this.analysisType = analysisType;
    }

    public String getResultSummary() {
        return resultSummary;
    }

    public void setResultSummary(String resultSummary) {
        this.resultSummary = resultSummary;
    }

    public Float getConfidenceScore() {
        return confidenceScore;
    }

    public void setConfidenceScore(Float confidenceScore) {
        this.confidenceScore = confidenceScore;
    }

    public Integer getProcessingTimeMs() {
        return processingTimeMs;
    }

    public void setProcessingTimeMs(Integer processingTimeMs) {
        this.processingTimeMs = processingTimeMs;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

//    public String getClientIp() {
//        return clientIp;
//    }
//
//    public void setClientIp(String clientIp) {
//        this.clientIp = clientIp;
//    }

    public String getWebsiteDomain() {
        return websiteDomain;
    }

    public void setWebsiteDomain(String websiteDomain) {
        this.websiteDomain = websiteDomain;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getCaptureDate() {
        return captureDate;
    }

    public void setCaptureDate(String captureDate) {
        this.captureDate = captureDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCameraModel() {
        return cameraModel;
    }

    public void setCameraModel(String cameraModel) {
        this.cameraModel = cameraModel;
    }

    public String getMediaBiasRating() {
        return mediaBiasRating;
    }

    public void setMediaBiasRating(String mediaBiasRating) {
        this.mediaBiasRating = mediaBiasRating;
    }

    public Double getMediaBiasScore() {
        return mediaBiasScore;
    }

    public void setMediaBiasScore(Double mediaBiasScore) {
        this.mediaBiasScore = mediaBiasScore;
    }

    public String getBiasLevel() {
        return biasLevel;
    }

    public void setBiasLevel(String biasLevel) {
        this.biasLevel = biasLevel;
    }

    public urlResult getUrlResult() {
        return urlResult;
    }

    public void setUrlResult(urlResult urlResult) {
        this.urlResult = urlResult;
    }

    @Override
    public String toString() {
        return "AnalysisLog{" +
                "logId=" + logId +
                ", analysisType='" + analysisType + '\'' +
                ", resultSummary='" + resultSummary + '\'' +
                ", confidenceScore=" + confidenceScore +
                ", processingTimeMs=" + processingTimeMs +
                ", modelId=" + modelId +
                ", timestamp=" + timestamp +
                //", clientIp='" + clientIp + '\'' +
                ", websiteDomain='" + websiteDomain + '\'' +
                ", mediaName='" + mediaName + '\'' +
                ", mediaBiasRating='" + mediaBiasRating + '\'' +
                ", mediaBiasScore=" + mediaBiasScore +
                ", captureDate='" + captureDate + '\'' +
                ", location='" + location + '\'' +
                ", cameraModel='" + cameraModel + '\'' +
                ", biasLevel='" + biasLevel + '\'' +
                ", urlResult=" + urlResult +
                '}';
    }
}