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
    public AnalysisLog addByNews(String inputText, String mediaName, String clientIp) {
        //String API_URL = "https://ai-vs-human-api.onrender.com/predict";
        String API_URL = "http://149.104.27.148:8000/predict";
        String API_URL_bias = "http://149.104.27.148:5000/api/analyze-document";

        RestTemplate restTemplate = new RestTemplate();
        TextRequest textRequest = new TextRequest(inputText);
        ObjectMapper objectMapper = new ObjectMapper();

        AnalysisLog analysisLog = new AnalysisLog();
        analysisLog.setClientIp(clientIp);

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
            // 第一个接口返回 NewsResponse 类型的数据
            String jsonResponse = restTemplate.postForObject(API_URL, textRequest, String.class);
            NewsResponse newsResponse = objectMapper.readValue(jsonResponse, NewsResponse.class);

            // 第二个接口返回 TextBiasResponse 类型的数据
            String jsonResponse_bias = restTemplate.postForObject(API_URL_bias, textRequest, String.class);
            TextBiasResponse newsResponse_bias = objectMapper.readValue(jsonResponse_bias, TextBiasResponse.class);

            analysisLog.setTextBiasResult(newsResponse_bias);
            analysisLog.setTextBiasScore(newsResponse_bias.computeBiasScore());

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime; // 毫秒
            // 将 duration 转换为 int
            float score = newsResponse.getScore();
            String label = newsResponse.getLabel();

            if(Objects.equals(label, "AI-generated")){
                score = score*100;
            } else{
                score = (1-score)*100;
            }

            analysisLog.setAnalysisType("News Analysis");
            analysisLog.setResultSummary(label);

            analysisLog.setConfidenceScore(score);
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
    public AnalysisLog addByUrl(String inputUrl, String clientIp) throws UnknownHostException {
        int score = 0;
        long startTime = System.currentTimeMillis();
        System.out.println(inputUrl);
        AnalysisLog analysisLog = new AnalysisLog();
        SSLCertificateAnalyzer analyzer = new SSLCertificateAnalyzer();
        urlResult urlResultNew = new urlResult();
        analysisLog.setClientIp(clientIp);

        String sslResult = null;
        if (inputUrl.startsWith("http://")) {
            sslResult = "http";
            score+= 20;
        }
        else{
            if (!inputUrl.startsWith("https://")){
                inputUrl = "https://" + inputUrl;
            }
            Map<String, String> analysisSsl = analyzer.analyzeCertificate(inputUrl);

            urlResultNew.setSslCaResult(analysisSsl.get("issuerAnalysis"));
            urlResultNew.setSslKeyResult(analysisSsl.get("cryptoAnalysis"));
            urlResultNew.setSslValidityResult(analysisSsl.get("validityAnalysis"));
            score+= analyzer.getCa_score();
            score+= analyzer.getValidity_score();
        }

        if (GoogleSafeService.checkUrlSafety(inputUrl) == 1){
            urlResultNew.setGoogleResult("From Google Safe Service, result is safe");
            score += 30;
        }
        else{
            urlResultNew.setGoogleResult("From Google Safe Service, result is not safe");
        }

        VirusTotalStatsService service = new VirusTotalStatsService();

        int[] stats = service.getUrlStats(inputUrl);

        String result_status = String.format(
                "Rated by %d agencies, %d rated as: malicious. %d rated as: suspicious. %d rated as: undetected. %d rated as: harmless.",
                stats[0], stats[1], stats[2], stats[3], stats[4]
        );

        float total_agencies = 1;

        if(stats[0] != 0){
            total_agencies = (float) stats[4] /stats[0];
        }
        score+= (int) (10*total_agencies);

        urlResultNew.setOfficialResults(result_status);

        analysisLog.setUrlResult(urlResultNew);

        String resultSummary = "";
        //float confidenceScore = 67.0F;

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime; // 毫秒


        analysisLog.setAnalysisType("Website Verification");
        analysisLog.setResultSummary(resultSummary);
        analysisLog.setConfidenceScore((float) score);
        Date now = new Date();
        analysisLog.setTimestamp(now);
        analysisLog.setProcessingTimeMs((int) duration);

//        String host = null;
//        try {
//            URL url = new URL(inputUrl);  // 解析 URL
//            host = url.getHost();         // 获取 host
//        } catch (Exception e) {
//            // 如果发生异常，则 host 保持为 null
//        }
//
//        if (host != null) {
//
//            try {
//                // 获取 IP 地址
//                InetAddress address = InetAddress.getByName(host);
//                String ipAddress = address.getHostAddress(); // 将 IP 地址转换为字符串
//                analysisLog.setWebsiteDomain(ipAddress);
//            } catch (Exception e) {
//                System.out.println("Unable to resolve IP address.");
//            }
//        }

        analysisLog.setWebsiteDomain(inputUrl);

        System.out.println("Success for ："+analysisLog.getAnalysisType());
        return analysisLogRepository.save(analysisLog);
    }

    @Override
    public AnalysisLog addByImage(MultipartFile file) {
        //String API_URL = "https://mini-vgg-model.onrender.com/predict";
        String API_URL = "http://149.104.27.148:5001/predict";
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

                if (imageResponse.getPredicted_class() == 1) {
                    resultSummary = "Real Image";
                    float confidence = imageResponse.getConfidence();
                    if(confidence> 0.5)
                    {
                        confidence = confidence*100;
                    } else{
                        confidence = (1-confidence)*100;
                    }
                    analysisLog.setConfidenceScore(confidence);
                }
                else {
                    float confidence = imageResponse.getConfidence();
                    if(confidence> 0.5)
                    {
                        confidence = confidence*100;
                    } else{
                        confidence = (1-confidence)*100;
                    }
                    analysisLog.setConfidenceScore(confidence);
                    resultSummary = "Fake Image";
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
        return "Left Bias";
    }

}
