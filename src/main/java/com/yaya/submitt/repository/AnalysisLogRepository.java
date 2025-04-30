package com.yaya.submitt.repository;

import com.yaya.submitt.pojo.AnalysisLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisLogRepository extends JpaRepository<AnalysisLog, String> {
    @Query(value = "SELECT website_domain " +
            "FROM analysislog " +
            "WHERE analysis_type = 'Website Verification' " +
            "AND website_domain IS NOT NULL " +
            "GROUP BY website_domain " +
            "ORDER BY COUNT(log_id) DESC " +
            "LIMIT 1",
            nativeQuery = true)
    String findTopWebsiteDomain();

    @Query(value = "SELECT analysis_type " +
            "FROM analysislog " +
            "GROUP BY analysis_type " +
            "ORDER BY COUNT(log_id) DESC " +
            "LIMIT 1",
            nativeQuery = true)
    String findTopAnalysisType();
}
