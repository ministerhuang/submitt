package com.yaya.submitt.service;

import com.yaya.submitt.pojo.SaveAnalysisLog;
import com.yaya.submitt.repository.SaveAnalysisLogRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveAnalysisLogServiceService implements ISaveAnalysisLogService {
    @Autowired
    private SaveAnalysisLogRepository saveAnalysisLogRepository;

    @Override
    public SaveAnalysisLog add(SaveAnalysisLog saveAnalysisLog) {
        SaveAnalysisLog saveAnalysisLog1 = new SaveAnalysisLog();
        BeanUtils.copyProperties(saveAnalysisLog, saveAnalysisLog1);
        return saveAnalysisLogRepository.save(saveAnalysisLog1);
    }

    @Override
    public SaveAnalysisLog getAnalysisLast(String clientIp, String analysisType){
        return saveAnalysisLogRepository.findLastAnalysisLog(clientIp, analysisType);
    }
}
