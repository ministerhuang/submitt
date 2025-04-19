package com.yaya.submitt.pojo.dto;

import jakarta.persistence.Column;

public class CityAqiDto {
    private Integer Id;

    private String cityId;

    private String cityName;

    private String cityDay;

    private String cityTime;

    private String cityState;

    private Integer aqiDayValue;

    private Integer aqiTimeValue;

    private Integer aqiPredict;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityTime() {
        return cityTime;
    }

    public void setCityTime(String cityTime) {
        this.cityTime = cityTime;
    }

    public String getCityState() {
        return cityState;
    }

    public void setCityState(String cityState) {
        this.cityState = cityState;
    }

    public Integer getAqiTimeValue() {
        return aqiTimeValue;
    }

    public void setAqiTimeValue(Integer aqiTimeValue) {
        this.aqiTimeValue = aqiTimeValue;
    }

    public Integer getAqiDayValue() {
        return aqiDayValue;
    }

    public void setAqiDayValue(Integer aqiDayValue) {
        this.aqiDayValue = aqiDayValue;
    }

    public Integer getAqiPredict() {
        return aqiPredict;
    }

    public void setAqiPredict(Integer aqiPredict) {
        this.aqiPredict = aqiPredict;
    }

    public String getCityDay() {
        return cityDay;
    }

    public void setCityDay(String cityDay) {
        this.cityDay = cityDay;
    }

    @Override
    public String toString() {
        return "CityAqiDto{" +
                "Id=" + Id +
                ", cityId='" + cityId + '\'' +
                ", cityName='" + cityName + '\'' +
                ", cityDay='" + cityDay + '\'' +
                ", cityTime='" + cityTime + '\'' +
                ", cityState='" + cityState + '\'' +
                ", aqiDayValue=" + aqiDayValue +
                ", aqiTimeValue=" + aqiTimeValue +
                ", aqiPredict=" + aqiPredict +
                '}';
    }
}
