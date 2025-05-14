//package com.yaya.submitt.controller;
//
//import com.yaya.submitt.pojo.AnswerRequest;
//import com.yaya.submitt.pojo.ResponseMessage;
//import com.yaya.submitt.service.IChartAIService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController //接口方法返回对象，转换成json格式
//@RequestMapping("/chartAI") //localhost:8088/citizenship/
//public class ChartAIController {
//    @Autowired
//    IChartAIService chartAIService;
//
//    @PostMapping(value = "addChart")   //URL: localhost:8088/citizenship/addTestLog   method:post
//    public ResponseMessage<String> add(@RequestBody String ai_input) {
//        //return chartAIService.save(ai_input);
//        return null;
//    }
//}
