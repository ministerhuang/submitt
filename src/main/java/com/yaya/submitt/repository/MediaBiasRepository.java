package com.yaya.submitt.repository;

import com.yaya.submitt.pojo.MediaBias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MediaBiasRepository extends JpaRepository<MediaBias, Long> {

    // 正确的方法定义 - 无static关键字
    Optional<MediaBias> findByMediaName(String mediaName);
}