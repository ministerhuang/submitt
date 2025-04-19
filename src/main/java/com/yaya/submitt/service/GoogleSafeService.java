package com.yaya.submitt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yaya.submitt.pojo.AnalysisLog;
import com.yaya.submitt.pojo.GoogleUrlRequest;
import com.yaya.submitt.pojo.NewsResponse;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;

public class GoogleSafeService {
    public static int checkUrlSafety(String inputUrl){
        GoogleUrlRequest.Client client = new GoogleUrlRequest.Client();
        client.setClientId("iyakah");
        client.setClientVersion("1.5.2");

        GoogleUrlRequest.ThreatEntry entry1 = new GoogleUrlRequest.ThreatEntry();
        entry1.setUrl(inputUrl);

        GoogleUrlRequest.ThreatInfo threatInfo = new GoogleUrlRequest.ThreatInfo();
        threatInfo.setThreatTypes(Arrays.asList("MALWARE", "SOCIAL_ENGINEERING"));
        threatInfo.setPlatformTypes(Arrays.asList("WINDOWS"));
        threatInfo.setThreatEntryTypes(Arrays.asList("URL"));
        threatInfo.setThreatEntries(Arrays.asList(entry1));

        GoogleUrlRequest request = new GoogleUrlRequest();
        request.setClient(client);
        request.setThreatInfo(threatInfo);

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        String API_URL_google = "https://safebrowsing.googleapis.com/v4/threatMatches:find";
        String API_URL_END_google = "?key=";
        String API_TOKEN_google = "AIzaSyCAukawQCW3flIoHiZAu-AFJ_eznAj82O8";

        String finalUrl_google = API_URL_google+API_URL_END_google+API_TOKEN_google;

        try {

            long startTime = System.currentTimeMillis();
            // 1. 发送 GET 请求获取 JSON 数据
            String jsonResponse = restTemplate.postForObject(finalUrl_google, request, String.class);

            // 2. 解析 JSON 数据
            NewsResponse newsResponse = objectMapper.readValue(jsonResponse, NewsResponse.class);

            if (jsonResponse != null && jsonResponse.trim().equals("{}")) {
                return 1;
            }
            else{
                return 0;
            }
        } catch (JsonProcessingException e) {
            return 0;
        }
    }
}
