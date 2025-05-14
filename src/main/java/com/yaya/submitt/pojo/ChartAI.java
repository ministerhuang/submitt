package com.yaya.submitt.pojo;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class ChartAI {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chart_id")
    private Integer chartId;

    @Column(name = "curent_time", nullable = true)  // 可以为空
    @Temporal(TemporalType.TIMESTAMP)
    private Date currentTime;       // 用于存储当前时间

    @Column(name = "client_ip", nullable = true)  // 可以为空
    private String clientIP;        // 用于存储客户端 IP 地址
    @Column(name = "user_input", nullable = true)  // 可以为空
    private String userInput;       // 用于存储用户输入的长文本
    @Column(name = "ai_output", nullable = true)  // 可以为空
    private String aiOutput;        // 用于存储 AI 生成的长文本

    public Integer getChartId() {
        return chartId;
    }

    public void setChartId(Integer chartId) {
        this.chartId = chartId;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public String getAiOutput() {
        return aiOutput;
    }

    public void setAiOutput(String aiOutput) {
        this.aiOutput = aiOutput;
    }

    @Override
    public String toString() {
        return "ChartAI{" +
                "chartId=" + chartId +
                ", currentTime=" + currentTime +
                ", clientIP='" + clientIP + '\'' +
                ", userInput='" + userInput + '\'' +
                ", aiOutput='" + aiOutput + '\'' +
                '}';
    }
}