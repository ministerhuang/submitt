package com.yaya.submitt.service;

import com.yaya.submitt.pojo.SaveAnalysisLog;


public interface ISaveAnalysisLogService {
    SaveAnalysisLog add(SaveAnalysisLog saveAnalysisLog);
    SaveAnalysisLog getAnalysisLast(String clientIp, String analysisType);
}
