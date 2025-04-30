package com.yaya.submitt.controller;

import com.yaya.submitt.pojo.CitizenshipTestLog;
import com.yaya.submitt.pojo.ResponseMessage;
import com.yaya.submitt.pojo.dto.CitizenshipTestLogDto;
import com.yaya.submitt.service.ICitizenshipTestLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //接口方法返回对象，转换成json格式
@RequestMapping("/citizenship") //localhost:8088/citizenship/
public class CitizenshipTestLogController {
    @Autowired
    ICitizenshipTestLogService citizenshipTestLogService;

    @PostMapping(value = "addTestLog")   //URL: localhost:8088/citizenship/addTestLog   method:post
    public ResponseMessage<CitizenshipTestLog> add(@RequestBody CitizenshipTestLogDto citizenshipTestLog) {  //传进来的是json
        CitizenshipTestLog citizenshipTestLogNew = citizenshipTestLogService.add(citizenshipTestLog);
        return ResponseMessage.success(citizenshipTestLogNew);
    }
}
