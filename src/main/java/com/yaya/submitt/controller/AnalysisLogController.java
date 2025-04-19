package com.yaya.submitt.controller;

import com.yaya.submitt.pojo.AnalysisLog;
import com.yaya.submitt.pojo.ResponseMessage;
import com.yaya.submitt.pojo.dto.AnalysisLogDto;
import com.yaya.submitt.pojo.dto.TextRequestDto;
import com.yaya.submitt.pojo.dto.UrlRequestDto;
import com.yaya.submitt.service.IAnalysisLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController //接口方法返回对象，转换成json格式
@RequestMapping("/analysislog") //localhost:8088/analysislog/
public class AnalysisLogController {
    @Autowired
    IAnalysisLogService analysisLogService;

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
        // 获取mediaName值
        String mediaName = textRequest.getMediaName();
        AnalysisLog analysisLogNew = analysisLogService.addByNews(inputText, mediaName);
        return ResponseMessage.success(analysisLogNew);
    }

    @PostMapping(value = "addAnalysisLogByUrl")
    public ResponseMessage<AnalysisLog> addByUrl(@RequestBody UrlRequestDto urlRequest){
        String inputUrl = urlRequest.getUrl();
        AnalysisLog analysisLogNew = analysisLogService.addByUrl(inputUrl);
        return ResponseMessage.success(analysisLogNew);
    }

    @PostMapping(value = "addAnalysisLogByImage")
    public ResponseMessage<AnalysisLog> addByImage(@RequestParam("image") MultipartFile file) {
        AnalysisLog analysisLogNew = analysisLogService.addByImage(file);
        return ResponseMessage.success(analysisLogNew);
    }
}
