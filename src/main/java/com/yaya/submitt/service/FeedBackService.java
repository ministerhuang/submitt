package com.yaya.submitt.service;

import com.yaya.submitt.pojo.dto.FeedbackDto;
import com.yaya.submitt.pojo.FeedBack;
import com.yaya.submitt.repository.FeedBackRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FeedBackService implements IFeedBackService{
    @Autowired
    private FeedBackRepository feedBackRepository;

    @Override
    public FeedBack add(FeedbackDto feedBack){
        FeedBack feedBack1 = new FeedBack();
        BeanUtils.copyProperties(feedBack, feedBack1);
        Date now = new Date();
        feedBack1.setTimestamp(now);
        return feedBackRepository.save(feedBack1);
    }
}
