package com.yaya.submitt.repository;

import com.yaya.submitt.pojo.SaveAnalysisLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SaveAnalysisLogRepository extends JpaRepository<SaveAnalysisLog, String> {
    @Query(value = "SELECT * FROM save_analysislog " +
            "WHERE client_ip = :clientIp " +
            "AND analysis_type = :analysisType " +
            "ORDER BY timestamp DESC " +
            "LIMIT 1",
            nativeQuery = true)
    SaveAnalysisLog findLastAnalysisLog(@Param("clientIp") String clientIp, @Param("analysisType") String analysisType);
}
