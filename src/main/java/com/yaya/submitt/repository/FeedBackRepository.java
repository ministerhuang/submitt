package com.yaya.submitt.repository;

import com.yaya.submitt.pojo.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedBack, String> {

}
