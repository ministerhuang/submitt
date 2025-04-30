package com.yaya.submitt.repository;

import com.yaya.submitt.pojo.CitizenshipTestLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenshipTestLogRepository extends JpaRepository<CitizenshipTestLog, String> {

}
