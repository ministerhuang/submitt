package com.yaya.submitt.service;

import com.yaya.submitt.pojo.CityAqi;
import com.yaya.submitt.pojo.dto.CityAqiDto;

import java.util.List;

public interface ICityAqiService {
    CityAqi add(CityAqiDto cityaqi);

    //CityAqi getCity(String cityId);

    CityAqi addByApi(CityAqiDto cityName);

    List<CityAqi> findByCityId(String cityId);

    List<CityAqi> getAllCityByCityIdAndCityDay(String cityId, String cityDay);

    List<CityAqi> getAllCityByCityIdAndCityTime(String cityId, String cityTime);

    CityAqi edit(CityAqiDto cityaqi);

    CityAqi delete(String cityId);
}
