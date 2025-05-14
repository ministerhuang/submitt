package com.yaya.submitt.pojo;

import com.yaya.submitt.service.JsonConverter;
import jakarta.persistence.*;

import java.util.Date;

@Table(name = "save_analysislog")
@Entity
public class SaveAnalysisLog {
    @Id
    @Column(name = "analysislog_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer analysislogId;

    @Convert(converter = JsonConverter.class)
    @Column(name = "analysislog")
    private AnalysisLog analysisLog;

    @Column(name = "client_ip")
    private String clientIp;

    @Column(name = "analysis_type")
    private String analysisType;

    @Column(name = "timestamp", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public AnalysisLog getAnalysisLog() {
        return analysisLog;
    }

    public void setAnalysisLog(AnalysisLog analysisLog) {
        this.analysisLog = analysisLog;
    }

    public Integer getAnalysislogId() {
        return analysislogId;
    }

    public void setAnalysislogId(Integer analysislogId) {
        this.analysislogId = analysislogId;
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
