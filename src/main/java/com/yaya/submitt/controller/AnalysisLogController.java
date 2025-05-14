package com.yaya.submitt.controller;

import com.yaya.submitt.pojo.AnalysisLog;
import com.yaya.submitt.pojo.ResponseMessage;
import com.yaya.submitt.pojo.SaveAnalysisLog;
import com.yaya.submitt.pojo.dto.AnalysisLogDto;
import com.yaya.submitt.pojo.dto.TextRequestDto;
import com.yaya.submitt.pojo.dto.UrlRequestDto;
import com.yaya.submitt.service.IAnalysisLogService;
import com.yaya.submitt.service.ISaveAnalysisLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.UnknownHostException;
import java.util.Date;

@RestController //接口方法返回对象，转换成json格式
@RequestMapping("/analysislog") //localhost:8088/analysislog/
public class AnalysisLogController {
    @Autowired
    IAnalysisLogService analysisLogService;

    @Autowired
    ISaveAnalysisLogService saveAnalysisLog;

    //增加
    @PostMapping(value = "addAnalysisLog")   //URL: localhost:8088/analysislog/addAnalysisLog   method:post
    public ResponseMessage<AnalysisLog> add(@RequestBody AnalysisLogDto analysislog) {  //传进来的是json
        AnalysisLog analysisLogNew = analysisLogService.add(analysislog);
        return ResponseMessage.success(analysisLogNew);
    }

    @PostMapping(value = "addAnalysisLogByNews")
    public ResponseMessage<AnalysisLog> addByNews(@RequestBody TextRequestDto textRequest){
        //String inputText = textRequest.getText();
        String inputText = textRequest.getText();
        String clientIp = textRequest.getClientIp();
        // 获取mediaName值
        String mediaName = textRequest.getMediaName();
        AnalysisLog analysisLogNew = analysisLogService.addByNews(inputText, mediaName, clientIp);

        SaveAnalysisLog saveAnalysisLogNew = new SaveAnalysisLog();
        saveAnalysisLogNew.setAnalysisLog(analysisLogNew);
        Date now = new Date();
        saveAnalysisLogNew.setTimestamp(now);
        saveAnalysisLogNew.setClientIp(clientIp);
        saveAnalysisLogNew.setAnalysisType(analysisLogNew.getAnalysisType());
        saveAnalysisLog.add(saveAnalysisLogNew);

        return ResponseMessage.success(analysisLogNew);
    }

    @PostMapping(value = "addAnalysisLogByUrl")
    public ResponseMessage<AnalysisLog> addByUrl(@RequestBody UrlRequestDto urlRequest) throws UnknownHostException {
        String inputUrl = urlRequest.getUrl();
        String clientIp = urlRequest.getClientIp();
        AnalysisLog analysisLogNew = analysisLogService.addByUrl(inputUrl, clientIp);

        SaveAnalysisLog saveAnalysisLogNew = new SaveAnalysisLog();
        saveAnalysisLogNew.setAnalysisLog(analysisLogNew);
        Date now = new Date();
        saveAnalysisLogNew.setTimestamp(now);
        saveAnalysisLogNew.setClientIp(clientIp);
        saveAnalysisLogNew.setAnalysisType(analysisLogNew.getAnalysisType());
        saveAnalysisLog.add(saveAnalysisLogNew);

        return ResponseMessage.success(analysisLogNew);
    }

    @PostMapping(value = "addAnalysisLogByImage")
    public ResponseMessage<AnalysisLog> addByImage(@RequestParam("image") MultipartFile file) {
        AnalysisLog analysisLogNew = analysisLogService.addByImage(file);
        return ResponseMessage.success(analysisLogNew);
    }

    @GetMapping("/getMostUseWebsite")
    public ResponseMessage<String> getMostUseWebsite() {
        String most_website = analysisLogService.get_most_website();
        if (most_website.isEmpty()) {
            return ResponseMessage.error("No data found");
        } else{
            return ResponseMessage.success(most_website);
        }
    }

    @GetMapping("/getMostPopularFeature")
    public ResponseMessage<String> getMostPopularFeature() {
        String most_feature = analysisLogService.get_most_feature();
        if (most_feature.isEmpty()) {
            return ResponseMessage.error("No data found");
        } else{
            return ResponseMessage.success(most_feature);
        }
    }

    @GetMapping("/getMostCommonBias")
    public ResponseMessage<String> getMostCommonBias() {
        String most_bias = analysisLogService.get_most_bias();
        if (most_bias.isEmpty()) {
            return ResponseMessage.error("No data found");
        } else{
            return ResponseMessage.success(most_bias);
        }
    }

}
