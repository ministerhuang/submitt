package com.yaya.submitt.repository;

import com.yaya.submitt.pojo.CityAqi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityAqiRepository extends JpaRepository<CityAqi, String> {
    List<CityAqi> findByCityId(String cityId);
    List<CityAqi> getAllCityByCityIdAndCityDay(String cityId, String cityDay);

    List<CityAqi> getAllCityByCityIdAndCityTime(String cityId, String cityTime);

}
