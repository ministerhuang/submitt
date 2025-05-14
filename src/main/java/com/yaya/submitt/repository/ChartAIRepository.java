//package com.yaya.submitt.repository;
//
//import com.yaya.submitt.pojo.Answer;
//import com.yaya.submitt.pojo.dto.ChatSummaryDto;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//
//public interface ChartAIRepository extends JpaRepository<Answer, String> {
//    @Query("SELECT new com.yourpackage.ChatSummary(c.userInput, c.aiOutput) " +
//            "FROM ChatRecord c " +
//            "WHERE c.clientIp = :clientIp " +
//            "AND DATE(c.currentTime) = CURRENT_DATE " +
//            "ORDER BY c.currentTime DESC")
//    List<ChatSummaryDto> findRecentQuestions(@Param("clientIp") String clientIp);
//}
