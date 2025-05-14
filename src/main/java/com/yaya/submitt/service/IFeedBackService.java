package com.yaya.submitt.service;

import com.yaya.submitt.pojo.FeedBack;
import com.yaya.submitt.pojo.dto.FeedbackDto;

public interface IFeedBackService {
    FeedBack add(FeedbackDto feedback);
}
