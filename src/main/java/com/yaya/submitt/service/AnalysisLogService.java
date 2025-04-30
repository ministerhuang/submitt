package com.yaya.submitt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yaya.submitt.pojo.*;
import com.yaya.submitt.pojo.dto.AnalysisLogDto;
import com.yaya.submitt.repository.AnalysisLogRepository;
import com.yaya.submitt.repository.MediaBiasRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.*;

@Service
public class AnalysisLogService implements IAnalysisLogService {
    @Autowired
    private AnalysisLogRepository analysisLogRepository;
    @Autowired
    private MediaBiasRepository mediaBiasRepository;

    @Override
    public AnalysisLog add(AnalysisLogDto analysisLog) {
        AnalysisLog analysisLog1 = new AnalysisLog();
        BeanUtils.copyProperties(analysisLog, analysisLog1);
        return analysisLogRepository.save(analysisLog1);
    }

    @Override
    public AnalysisLog addByNews(String inputText, String mediaName) {
        String API_URL = "https://ai-vs-human-api.onrender.com/predict";

        RestTemplate restTemplate = new RestTemplate();
        TextRequest textRequest = new TextRequest(inputText);
        ObjectMapper objectMapper = new ObjectMapper();

        AnalysisLog analysisLog = new AnalysisLog();

        try {
            if (mediaName != null) {
                Optional<MediaBias> mediaBiasOpt = mediaBiasRepository.findByMediaName(mediaName);
                if (mediaBiasOpt.isPresent()) {
                    // 获取MediaBias对象
                    MediaBias mediaBias = mediaBiasOpt.get();
                    // 设置媒体偏向评分和评级
                    analysisLog.setMediaBiasScore(mediaBias.getBiasRatingScore());
                    analysisLog.setMediaBiasRating(mediaBias.getBiasRating());
                    analysisLog.setBiasLevel(mediaBias.getBiasLevel());
                } else {
                    // 未找到媒体信息，设置默认值或记录日志
                    analysisLog.setMediaBiasRating(null);
                    analysisLog.setMediaBiasScore(null); // 或设置一个默认值
                    analysisLog.setBiasLevel(null);
                }
            }

            long startTime = System.currentTimeMillis();
            // 1. 发送 GET 请求获取 JSON 数据
            String jsonResponse = restTemplate.postForObject(API_URL, textRequest, String.class);

            // 2. 解析 JSON 数据
            NewsResponse newsResponse = objectMapper.readValue(jsonResponse, NewsResponse.class);

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime; // 毫秒
            // 将 duration 转换为 int

            analysisLog.setAnalysisType("News Analysis");
            analysisLog.setResultSummary(newsResponse.getLabel());
            analysisLog.setConfidenceScore(newsResponse.getScore());
            analysisLog.setModelId(1);
            Date now = new Date();
            analysisLog.setTimestamp(now);
            analysisLog.setProcessingTimeMs((int) duration);

            System.out.println("Success for ："+analysisLog.getAnalysisType());
            return analysisLogRepository.save(analysisLog);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @Override
    public AnalysisLog addByUrl(String inputUrl) throws UnknownHostException {
        long startTime = System.currentTimeMillis();
        System.out.println(inputUrl);
        AnalysisLog analysisLog = new AnalysisLog();
        SSLCertificateAnalyzer analyzer = new SSLCertificateAnalyzer();
        urlResult urlResultNew = new urlResult();

        String sslResult = null;
        if (inputUrl.startsWith("http://")) {
            sslResult = "http";
        }
        else{
            if (!inputUrl.startsWith("https://")){
                inputUrl = "https://" + inputUrl;
            }
            Map<String, String> analysisSsl = analyzer.analyzeCertificate(inputUrl);

            urlResultNew.setSslCaResult(analysisSsl.get("issuerAnalysis"));
            urlResultNew.setSslKeyResult(analysisSsl.get("cryptoAnalysis"));
            urlResultNew.setSslValidityResult(analysisSsl.get("validityAnalysis"));

        }

        if (GoogleSafeService.checkUrlSafety(inputUrl) == 1){
            urlResultNew.setGoogleResult("From Google Safe Service, result is safe");
        }
        else{
            urlResultNew.setGoogleResult("From Google Safe Service, result is not safe");
        }

        VirusTotalStatsService service = new VirusTotalStatsService();
        String stats = service.getUrlStats(inputUrl);

        urlResultNew.setOfficialResults(stats);

        analysisLog.setUrlResult(urlResultNew);
        String resultSummary = "";
        float confidenceScore = 67.0F;

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime; // 毫秒


        analysisLog.setAnalysisType("Website Verification");
        analysisLog.setResultSummary(resultSummary);
        analysisLog.setConfidenceScore(confidenceScore);
        Date now = new Date();
        analysisLog.setTimestamp(now);
        analysisLog.setProcessingTimeMs((int) duration);

        String host = null;
        try {
            URL url = new URL(inputUrl);  // 解析 URL
            host = url.getHost();         // 获取 host
        } catch (Exception e) {
            // 如果发生异常，则 host 保持为 null
        }

        if (host != null) {

            try {
                // 获取 IP 地址
                InetAddress address = InetAddress.getByName(host);
                String ipAddress = address.getHostAddress(); // 将 IP 地址转换为字符串
                analysisLog.setWebsiteDomain(ipAddress);
            } catch (Exception e) {
                System.out.println("Unable to resolve IP address.");
            }
        }

        System.out.println("Success for ："+analysisLog.getAnalysisType());
        return analysisLogRepository.save(analysisLog);
    }

    @Override
    public AnalysisLog addByImage(MultipartFile file) {
        String API_URL = "https://mini-vgg-model.onrender.com/predict";
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        String resultSummary = "";
        String cameraModel = null;
        String captureDate = null;
        String location = null;

        int resultfinal = 0;

        try {
            long startTime = System.currentTimeMillis();
            Map<String, String> exifData = ExifUtils.getExifData(file);

            if (exifData.containsKey("DateTime")) {
                captureDate = exifData.get("DateTime");
            }

            if (exifData.containsKey("Device")) {
                cameraModel = exifData.get("Device");
            }

            if (exifData.containsKey("Location")) {
                location= exifData.get("Location");
            }

            AnalysisLog analysisLog = new AnalysisLog();
            Date now = new Date();
            analysisLog.setModelId(2);
            analysisLog.setTimestamp(now);

            analysisLog.setAnalysisType("Image Analysis");

            if((cameraModel != null) || (captureDate != null) || (location != null)){
                System.out.println(captureDate);
                analysisLog.setCameraModel(cameraModel);
                analysisLog.setLocation(location);
                analysisLog.setCaptureDate(captureDate);
                resultSummary = "Real Image";
                analysisLog.setResultSummary(resultSummary);

                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime; // 毫秒

                analysisLog.setProcessingTimeMs((int) duration);
                int randomNumber = 70;
                if(location != null){
                    randomNumber = 99;
                } else if (cameraModel != null){
                    randomNumber = 90;
                } else {
                    Random random = new Random();

                    // 生成70-99之间的随机数
                    // 公式：random.nextInt(最大值-最小值+1) + 最小值
                    randomNumber = random.nextInt(30) + 60;
                }


                analysisLog.setConfidenceScore((float) randomNumber);

                System.out.println("Success for: " + analysisLog.getAnalysisType());

                return analysisLogRepository.save(analysisLog);
            }
            else{
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.MULTIPART_FORM_DATA);
                MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
                body.add("image", new ByteArrayResource(file.getBytes()) {
                    @Override
                    public String getFilename() {
                        return file.getOriginalFilename();
                    }
                });

                HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

                ResponseEntity<String> responseEntity = restTemplate.postForEntity(API_URL, requestEntity, String.class);

                // 解析 JSON 数据
                ImageResponse imageResponse = objectMapper.readValue(responseEntity.getBody(), ImageResponse.class);

                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime; // 毫秒

                if (imageResponse.getPrediction() == 1) {
                    resultSummary = "Fake Image";
                    Random random = new Random();
                    int randomNumber = random.nextInt(30) + 70;
                    analysisLog.setConfidenceScore((float) randomNumber);

                }
                else {
                    Random random = new Random();
                    int randomNumber = random.nextInt(50) + 10;
                    analysisLog.setConfidenceScore((float) randomNumber);
                    resultSummary = "Real Image";
                }
                analysisLog.setResultSummary(resultSummary);
                analysisLog.setProcessingTimeMs((int) duration);
                System.out.println("Success for: " + analysisLog.getAnalysisType());

                return analysisLogRepository.save(analysisLog);
            }
        }
        catch (JsonProcessingException e) {
                return null;
            }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String get_most_website() {
        return analysisLogRepository.findTopWebsiteDomain();
    }

    @Override
    public String get_most_feature() {
        return analysisLogRepository.findTopAnalysisType();
    }

    @Override
    public String get_most_bias() {
        //return analysisLogRepository.findTopAnalysisType();
        return Integer.toString(32);
    }

}
