package com.yaya.submitt.pojo.dto;

import com.yaya.submitt.pojo.AnalysisLog;
import java.util.Date;

public class SaveAnalysisLogDto {
    private Integer analysislogId;
    private AnalysisLog analysisLog;
    private String clientIp;
    private String analysisType;
    private Date timestamp;

    public Integer getAnalysislogId() {
        return analysislogId;
    }

    public void setAnalysislogId(Integer analysislogId) {
        this.analysislogId = analysislogId;
    }

    public AnalysisLog getAnalysisLog() {
        return analysisLog;
    }

    public void setAnalysisLog(AnalysisLog analysisLog) {
        this.analysisLog = analysisLog;
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
