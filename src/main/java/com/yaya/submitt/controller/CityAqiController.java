package com.yaya.submitt.controller;

import com.yaya.submitt.pojo.CityAqi;
import com.yaya.submitt.pojo.ResponseMessage;
import com.yaya.submitt.pojo.dto.CityAqiDto;
import com.yaya.submitt.service.ICityAqiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //接口方法返回对象，转换成json格式
@RequestMapping("/cityaqi") //localhost:8088/cityaqi/
public class CityAqiController {
    @Autowired
    ICityAqiService cityAqiService;


    //增加
    @PostMapping(value = "addCityAqi")   //URL: localhost:8088/cityaqi/addCityAqi   method:post
    public ResponseMessage<CityAqi> add(@RequestBody CityAqiDto cityaqi) {  //传进来的是json
        CityAqi cityAqiNew = cityAqiService.add(cityaqi);
        return ResponseMessage.success(cityAqiNew);
    }

    @PostMapping(value = "addByApi")   //URL: localhost:8088/cityaqi/addByApi   method:post
    public ResponseMessage<CityAqi> addByApi(@RequestBody CityAqiDto cityaqi) {  //传进来的是json。调接口的时候也要传json
        CityAqi cityAqiNew = cityAqiService.addByApi(cityaqi);
        return ResponseMessage.success(cityAqiNew);
    }

    //查询
    @GetMapping("/getCityAqi/{cityId}")  //URL: localhost:8088/city/cityId/99   method:get
    public ResponseMessage<List<CityAqi>> get(@PathVariable String cityId) {
        System.out.println(cityId);
        List<CityAqi> cityAqiList = cityAqiService.findByCityId(cityId);
        if (cityAqiList.isEmpty()) {
            return ResponseMessage.error("City AQI data not found for cityId: " + cityId);
        }
        return ResponseMessage.success(cityAqiList);
    }

    //URL: localhost:8088/cityaqi/getCityAqiByDay?cityId=99&cityDay=20250312   method:get
    @GetMapping("/getCityAqiByDay")
    public ResponseMessage<List<CityAqi>> getAllCityByCityIdAndCityDay(@RequestParam String cityId, @RequestParam String cityDay) {
        List<CityAqi> cityAqiList = cityAqiService.getAllCityByCityIdAndCityDay(cityId, cityDay);
        if (cityAqiList.isEmpty()) {
            return ResponseMessage.error("No data found");
        }
        return ResponseMessage.success(cityAqiList);
    }

    //URL: localhost:8088/cityaqi/getCityAqiByTime?cityId=99&cityTime=02   method:get
    @GetMapping("/getCityAqiByTime")
    public ResponseMessage<List<CityAqi>> getAllCityByCityIdAndCityTime(@RequestParam String cityId, @RequestParam String cityTime) {

        List<CityAqi> cityAqiList = cityAqiService.getAllCityByCityIdAndCityTime(cityId, cityTime);
        if (cityAqiList.isEmpty()) {
            return ResponseMessage.error("No data found");
        }
        return ResponseMessage.success(cityAqiList);
    }

    //修改
    @PutMapping(value = "editCityAqi")
    public ResponseMessage<CityAqi> edit(@Validated @RequestBody CityAqiDto cityaqi) {  //传进来的是json，他会自动帮忙转成对象
        CityAqi cityAqiNew = cityAqiService.edit(cityaqi);
        return ResponseMessage.success(cityAqiNew);
    }
    //删除
    @DeleteMapping("/{cityId}")
    public ResponseMessage<CityAqi> delete(@PathVariable String cityId) {
        cityAqiService.delete(cityId);
        return ResponseMessage.success();
    }
}
