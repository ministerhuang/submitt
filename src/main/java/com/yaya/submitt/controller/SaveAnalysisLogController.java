package com.yaya.submitt.controller;

import com.yaya.submitt.pojo.ResponseMessage;
import com.yaya.submitt.pojo.SaveAnalysisLog;
import com.yaya.submitt.pojo.dto.SaveAnalysisLogRequest;
import com.yaya.submitt.service.ISaveAnalysisLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController //接口方法返回对象，转换成json格式
@RequestMapping("/saveAnalysis") //localhost:8088/citizenship/
public class SaveAnalysisLogController {
    @Autowired
    ISaveAnalysisLogService saveAnalysisLogService;

    //URL: localhost:8088/cityaqi/getCityAqiByDay?cityId=99&cityDay=20250312   method:get
    @GetMapping("/getAnalysisLast")
    public ResponseMessage<SaveAnalysisLog> getAnalysisLast(@RequestBody SaveAnalysisLogRequest saveAnalysisLogRequest) {
        String clientIp = saveAnalysisLogRequest.getClientIp();
        String analysisType = saveAnalysisLogRequest.getAnalysisType();
        SaveAnalysisLog saveAnalysisLog = saveAnalysisLogService.getAnalysisLast(clientIp, analysisType);

        return ResponseMessage.success(saveAnalysisLog);
    }
}
