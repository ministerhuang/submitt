package com.yaya.submitt.controller;

import com.yaya.submitt.pojo.FeedBack;
import com.yaya.submitt.pojo.ResponseMessage;
import com.yaya.submitt.pojo.dto.FeedbackDto;
import com.yaya.submitt.service.IFeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //接口方法返回对象，转换成json格式
@RequestMapping("/feedback") //localhost:8088/citizenship/
public class FeedBackController {
    @Autowired
    IFeedBackService feedBackService;

    @PostMapping(value = "addFeedback")   //URL: localhost:8088/citizenship/addTestLog   method:post
    public ResponseMessage<FeedBack> add(@RequestBody FeedbackDto feedback) {  //传进来的是json
        FeedBack feedBackNew = feedBackService.add(feedback);
        return ResponseMessage.success(feedBackNew);
    }
}
