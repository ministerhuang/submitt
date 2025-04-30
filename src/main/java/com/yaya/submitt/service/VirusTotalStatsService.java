package com.yaya.submitt.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class VirusTotalStatsService {

    private static final String API_KEY = "7878e4cfb4b6e304119c604f91ff67c3a9a8e3fdaaa41a7a80bd4f8b800348f4";
    private static final String SCAN_URL = "https://www.virustotal.com/api/v3/urls";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public VirusTotalStatsService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * 获取URL的VirusTotal统计信息
     *
     * @param url 要检测的URL
     * @return 仅包含统计信息的Map
     */
    public int[] getUrlStats(String url) {
        try {
            // 提交URL进行扫描
            String analysisId = submitUrlForScanning(url);

            // 等待处理
            TimeUnit.SECONDS.sleep(5);

            // 获取分析结果
            Map<String, Integer> stats = extractStatsFromAnalysis(analysisId);

            // 提取各项评级数量
            int malicious = stats.get("malicious");
            int suspicious = stats.get("suspicious");
            int undetected = stats.get("undetected");
            int harmless = stats.get("harmless");
            int timeout = stats.get("timeout");

            // 计算总评级机构数量
            int totalAgencies = malicious + suspicious + undetected + harmless + timeout;

            // 返回一个包含所有数据的数组
            return new int[]{totalAgencies, malicious, suspicious, undetected, harmless, timeout};

        } catch (Exception e) {
            e.printStackTrace();
            // 出错时返回一个包含0的数组
            return new int[]{0, 0, 0, 0, 0, 0};
        }
    }

    private String submitUrlForScanning(String url) throws Exception {
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-apikey", API_KEY);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 准备表单数据
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("url", url);

        // 创建请求实体
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);

        // 发送POST请求
        ResponseEntity<String> response = restTemplate.exchange(
                SCAN_URL,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        // 解析响应获取分析ID
        JsonNode rootNode = objectMapper.readTree(response.getBody());
        return rootNode.path("data").path("id").asText();
    }

    private Map<String, Integer> extractStatsFromAnalysis(String analysisId) throws Exception {
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-apikey", API_KEY);

        // 创建请求实体
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        // 构建获取分析结果的URL
        String analysisUrl = "https://www.virustotal.com/api/v3/analyses/" + analysisId;

        // 发送GET请求
        ResponseEntity<String> response = restTemplate.exchange(
                analysisUrl,
                HttpMethod.GET,
                requestEntity,
                String.class
        );

        // 解析响应，只提取stats部分
        JsonNode rootNode = objectMapper.readTree(response.getBody());
        JsonNode statsNode = rootNode.path("data").path("attributes").path("stats");

        // 创建并填充统计信息Map
        Map<String, Integer> stats = new HashMap<>();
        stats.put("malicious", statsNode.path("malicious").asInt());
        stats.put("suspicious", statsNode.path("suspicious").asInt());
        stats.put("undetected", statsNode.path("undetected").asInt());
        stats.put("harmless", statsNode.path("harmless").asInt());
        stats.put("timeout", statsNode.path("timeout").asInt());

        return stats;
    }
}

