package com.yaya.submitt.pojo.dto;

public class UrlRequestDto {
    private String url;
    private String clientIp;

    // Getter 和 Setter
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }
}