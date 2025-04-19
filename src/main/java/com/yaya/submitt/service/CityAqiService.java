package com.yaya.submitt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yaya.submitt.pojo.ApiJsonMessage;
import com.yaya.submitt.pojo.CityAqi;
import com.yaya.submitt.pojo.dto.CityAqiDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yaya.submitt.repository.CityAqiRepository;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class CityAqiService implements ICityAqiService{

    @Autowired
    private CityAqiRepository cityAqiRepository;
    private final String API_URL = "https://api.waqi.info/feed/";
    private final String API_URL_END = "/?token=";
    private final String API_TOKEN = "114cfaec444ddef2b81131d0cf1eb030780dcb2d";

    @Override
    public CityAqi add(CityAqiDto cityAqi) {
        CityAqi cityAqi1 = new CityAqi();
        //city1.setCityId(city.getCityId());
        BeanUtils.copyProperties(cityAqi, cityAqi1);
        return cityAqiRepository.save(cityAqi1);
    }

    @Override
    public CityAqi addByApi(CityAqiDto cityAqi) {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        String cityName = cityAqi.getCityName();

        try {
            // 1. 发送 GET 请求获取 JSON 数据
            String finalUrl = API_URL+cityName+API_URL_END+API_TOKEN;
            String jsonResponse = restTemplate.getForObject(finalUrl, String.class);

            // 2. 解析 JSON 数据
            ApiJsonMessage apiJsonMessage = objectMapper.readValue(jsonResponse, ApiJsonMessage.class);

            // 3. 创建 CityAqi 对象并赋值
            CityAqi cityAqi1 = new CityAqi();
            cityAqi1.setCityId(apiJsonMessage.getData().getIdx());

            cityAqi1.setCityName(apiJsonMessage.getData().getCity().getName());

            cityAqi1.setAqiDayValue(apiJsonMessage.getData().getAqi());

            String get_date= apiJsonMessage.getData().getTime().getIso();

            if (get_date != null) {
                // 解析 ISO 时间
                OffsetDateTime offsetDateTime = OffsetDateTime.parse(get_date);

                // 提取 LocalDate（去掉时区和时间部分）
                LocalDate localDate = offsetDateTime.toLocalDate();

                // 格式化成 "yy-MM-dd"
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
                String formattedDate = localDate.format(formatter);

                // 赋值给 cityAqi1
                cityAqi1.setCityDay(formattedDate);

                System.out.println("Formatted Date: " + formattedDate);
            }

            // 4. 存入数据库
            System.out.println("写入成功："+cityAqi1.getCityName());
            return cityAqiRepository.save(cityAqi1);

        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @Override
    public List<CityAqi> findByCityId(String cityId) {
        System.out.println(cityId);
        return cityAqiRepository.findByCityId(cityId);
    }

    @Override
    public List<CityAqi> getAllCityByCityIdAndCityDay(String cityId, String cityDay) {
        System.out.println(cityId);
        return cityAqiRepository.getAllCityByCityIdAndCityDay(cityId, cityDay);
    }

    @Override
    public List<CityAqi> getAllCityByCityIdAndCityTime(String cityId, String cityTime) {
        System.out.println(cityId);
        return cityAqiRepository.getAllCityByCityIdAndCityTime(cityId, cityTime);
    }

    @Override
    public CityAqi edit(CityAqiDto cityAqi) {
        CityAqi cityAqi1 = new CityAqi();
        BeanUtils.copyProperties(cityAqi, cityAqi1);
        return cityAqiRepository.save(cityAqi1);
    }

    @Override
    public CityAqi delete(String cityId) {
        cityAqiRepository.deleteById(cityId);
        return null;
    }
}
