package com.yaya.submitt.service;

import com.yaya.submitt.pojo.AnalysisLog;
import com.yaya.submitt.pojo.dto.AnalysisLogDto;
import org.springframework.web.multipart.MultipartFile;

import java.net.UnknownHostException;

public interface  IAnalysisLogService {
    AnalysisLog add(AnalysisLogDto analysislog);
    AnalysisLog addByNews(String inputText, String mediaName, String clientIp);

    AnalysisLog addByUrl(String urlRequest, String clientIp) throws UnknownHostException;

    AnalysisLog addByImage(MultipartFile file);

    String get_most_website();

    String get_most_feature();

    String get_most_bias();
}
