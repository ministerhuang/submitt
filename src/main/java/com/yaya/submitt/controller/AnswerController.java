package com.yaya.submitt.controller;

import com.yaya.submitt.pojo.*;
import com.yaya.submitt.service.IAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController //接口方法返回对象，转换成json格式
@RequestMapping("/answer") //localhost:8088/citizenship/
public class AnswerController {
    @Autowired
    IAnswerService answerService;

    @PostMapping(value = "addAnswer")   //URL: localhost:8088/citizenship/addTestLog   method:post
    public ResponseMessage<AnswerResponse> add(@RequestBody AnswerRequest answerList) {  //传进来的是json

        int questionListSize = answerList.getQuestionList().size();
        int questionAnswerSize = answerList.getQuestionAnswer().size();
        int questionCorrectSize = answerList.getQuestionCorrect().size();

        // 判断三个列表的数量是否一致
        if (questionListSize != questionAnswerSize || questionAnswerSize != questionCorrectSize) {
            // 如果数量不一致，返回错误信息
            return ResponseMessage.error("The size of question_list, question_answer, and question_correct must be the same.");
        }

        int all_number = 0;
        // 如果数量一致，循环处理每个答案并执行 add 方法
        for (int i = 0; i < questionListSize; i++) {
            Answer answer = new Answer();
            answer.setUserIp(answerList.getUserIp());
            // 设置各个字段的值
            answer.setQuestionId(answerList.getQuestionList().get(i));
            answer.setAnswerUser(answerList.getQuestionAnswer().get(i));
            answer.setCorrect(answerList.getQuestionCorrect().get(i));
            Date now = new Date();
            answer.setAnswerTime(now);
            // 调用 answerService.add 方法
            all_number = answerService.add(answer);
            // 可以在这里做一些额外的处理，如记录日志等
        }
        AnswerResponse answerResponse = new AnswerResponse();
        answerResponse.setAddNumber(questionListSize);
        if (all_number > 15){
            all_number = 15;
        }
        answerResponse.setAllNumber(all_number);

        return ResponseMessage.success(answerResponse);
    }
}
