package com.yaya.submitt.repository;

import com.yaya.submitt.pojo.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, String> {
    @Query(value = "SELECT COUNT(DISTINCT question_id) " +
            "FROM answer " +
            "WHERE user_ip = :userIp",
            nativeQuery = true)
    int findAllNumber(@Param("userIp") String userIp);
}
