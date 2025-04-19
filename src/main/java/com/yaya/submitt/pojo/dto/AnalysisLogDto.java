package com.yaya.submitt.pojo.dto;

import com.yaya.submitt.pojo.urlResult;
import jakarta.persistence.Transient;

import java.util.Date;

public class AnalysisLogDto {
    private Integer logId;
    private String analysisType;
    private String resultSummary;
    private Float confidenceScore;
    private Integer processingTimeMs;
    private Integer modelId;
    private Date timestamp;
    private String clientIp;
    private String websiteName;
    private String mediaName;
    private String captureDate;
    private String location;
    private String cameraModel;
    private String biasLevel;
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

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
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

    public String getCameraModel() {
        return cameraModel;
    }

    public void setCameraModel(String cameraModel) {
        this.cameraModel = cameraModel;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
        return "AnalysisLogDto{" +
                "logId=" + logId +
                ", analysisType='" + analysisType + '\'' +
                ", resultSummary='" + resultSummary + '\'' +
                ", confidenceScore=" + confidenceScore +
                ", processingTimeMs=" + processingTimeMs +
                ", modelId=" + modelId +
                ", timestamp=" + timestamp +
                ", clientIp='" + clientIp + '\'' +
                ", websiteName='" + websiteName + '\'' +
                ", mediaName='" + mediaName + '\'' +
                ", captureDate='" + captureDate + '\'' +
                ", location='" + location + '\'' +
                ", cameraModel='" + cameraModel + '\'' +
                ", biasLevel='" + biasLevel + '\'' +
                ", urlResult=" + urlResult +
                '}';
    }
}