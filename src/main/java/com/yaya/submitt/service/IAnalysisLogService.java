package com.yaya.submitt.service;

import com.yaya.submitt.pojo.AnalysisLog;
import com.yaya.submitt.pojo.dto.AnalysisLogDto;
import org.springframework.web.multipart.MultipartFile;

public interface  IAnalysisLogService {
    AnalysisLog add(AnalysisLogDto analysislog);
    AnalysisLog addByNews(String inputText, String mediaName);

    AnalysisLog addByUrl(String urlRequest);

    AnalysisLog addByImage(MultipartFile file);

}
