package com.yaya.submitt.service;

import com.yaya.submitt.pojo.Answer;
import com.yaya.submitt.pojo.dto.AnswerDto;
import com.yaya.submitt.repository.AnswerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService implements IAnswerService{
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public Integer add(Answer answer){
        answerRepository.save(answer);
        String user_ip = answer.getUserIp();
        return answerRepository.findAllNumber(user_ip);
    }
}
