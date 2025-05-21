package com.yaya.submitt.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;

public class UrlChatRequest {
    @JsonProperty("logId")
    private Integer logId;
    //分析-网站验证，图像检测，新闻分析
    @JsonProperty("analysisType")
    private String analysisType;
    //结果摘要
    @JsonProperty("resultSummary")
    private String resultSummary;
    //置信分数(先不写)
    @JsonProperty("confidenceScore")
    private Float confidenceScore;
    //处理时间
    @JsonProperty("processingTimeMs")
    private Integer processingTimeMs;
    //模型ID
    @JsonProperty("modelId")
    private Integer modelId;
    //当前时间
    @JsonProperty("timestamp")
    private Date timestamp;
    //不写
    @JsonProperty("clientIp")
    private String clientIp;
    //不写
    @JsonProperty("websiteDomain")
    private String websiteDomain;

    @JsonProperty("mediaName")
    private String mediaName;

    @JsonProperty("mediaBiasRating")
    private String mediaBiasRating;

    @JsonProperty("mediaBiasScore")
    private Double mediaBiasScore;

    @JsonProperty("captureDate")
    private String captureDate;

    @JsonProperty("TextBiasScore")
    private float TextBiasScore;

    @JsonProperty("location")
    private String location;

    @JsonProperty("cameraModel")
    private String cameraModel;

    @JsonProperty("biasLevel")
    private String biasLevel;

    @JsonProperty("urlResult")
    private DomainInfo urlResult;

    @JsonProperty("textBiasResult")
    private TextBiasResponse textBiasResult;

    @JsonProperty("chatResponse")
    private String chatResponse;

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

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

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

    public String getCaptureDate() {
        return captureDate;
    }

    public void setCaptureDate(String captureDate) {
        this.captureDate = captureDate;
    }

    public float getTextBiasScore() {
        return TextBiasScore;
    }

    public void setTextBiasScore(float textBiasScore) {
        TextBiasScore = textBiasScore;
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

    public String getBiasLevel() {
        return biasLevel;
    }

    public void setBiasLevel(String biasLevel) {
        this.biasLevel = biasLevel;
    }

    public DomainInfo getUrlResult() {
        return urlResult;
    }

    public void setUrlResult(DomainInfo urlResult) {
        this.urlResult = urlResult;
    }

    public TextBiasResponse getTextBiasResult() {
        return textBiasResult;
    }

    public void setTextBiasResult(TextBiasResponse textBiasResult) {
        this.textBiasResult = textBiasResult;
    }

    public String getChatResponse() {
        return chatResponse;
    }

    public void setChatResponse(String chatResponse) {
        this.chatResponse = chatResponse;
    }
}
