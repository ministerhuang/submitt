package com.yaya.submitt.service;

import com.yaya.submitt.pojo.AnalysisLog;
import com.yaya.submitt.pojo.dto.AnalysisLogDto;
import org.springframework.web.multipart.MultipartFile;

import java.net.UnknownHostException;

public interface  IAnalysisLogService {
    AnalysisLog add(AnalysisLogDto analysislog);
    AnalysisLog addByNews(String inputText, String mediaName);

    AnalysisLog addByUrl(String urlRequest) throws UnknownHostException;

    AnalysisLog addByImage(MultipartFile file);

}
