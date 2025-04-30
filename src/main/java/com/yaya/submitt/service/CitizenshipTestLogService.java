package com.yaya.submitt.service;

import com.yaya.submitt.pojo.CitizenshipTestLog;
import com.yaya.submitt.pojo.dto.CitizenshipTestLogDto;
import com.yaya.submitt.repository.CitizenshipTestLogRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CitizenshipTestLogService implements ICitizenshipTestLogService{
    @Autowired
    private CitizenshipTestLogRepository citizenshipTestLogRepository;

    @Override
    public CitizenshipTestLog add(CitizenshipTestLogDto citizenshipTestLog) {
        CitizenshipTestLog citizenshipTestLog1 = new CitizenshipTestLog();
        BeanUtils.copyProperties(citizenshipTestLog, citizenshipTestLog1);

        Byte result1 = (citizenshipTestLog.getAnswer1() != null) ? citizenshipTestLog.getAnswer1() : 0;
        Byte result2 = (citizenshipTestLog.getAnswer2() != null) ? citizenshipTestLog.getAnswer2() : 0;
        Byte result3 = (citizenshipTestLog.getAnswer3() != null) ? citizenshipTestLog.getAnswer3() : 0;
        Byte result4 = (citizenshipTestLog.getAnswer4() != null) ? citizenshipTestLog.getAnswer4() : 0;
        Byte result5 = (citizenshipTestLog.getAnswer5() != null) ? citizenshipTestLog.getAnswer5() : 0;

        int total_score = 0;
        if(result1 == 2){
            total_score+= 1;
        }
        if(result2 == 2){
            total_score+= 1;
        }
        if(result3 == 2){
            total_score+= 1;
        }
        if(result4 == 2){
            total_score+= 1;
        }
        if(result5 == 2){
            total_score+= 1;
        }

        String rating_result = "";
        if(total_score >= 4){
            rating_result = "You're a savvy digital citizen!";
        } else if(total_score >= 2){
            rating_result = "You're learning -stay alert and keep improving.";
        } else{
            rating_result = "Time to boost your awareness. Start with our detection tools!";
        }
        citizenshipTestLog1.setRating(rating_result);
        Date now = new Date();
        citizenshipTestLog1.setTestTimestamp(now);
        citizenshipTestLog1.setTotalScore(total_score);

        return citizenshipTestLogRepository.save(citizenshipTestLog1);
    }
}
