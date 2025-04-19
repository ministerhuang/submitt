package com.yaya.submitt.pojo;

import jakarta.persistence.*;

@Table(name= "city_aqi")
@Entity
public class CityAqi {
    @Id
    @Column(name= "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name= "city_id")
    private String cityId;

    @Column(name= "city_name")
    private String cityName;

    @Column(name= "city_day")
    private String cityDay;

    @Column(name= "city_time")
    private String cityTime;

    @Column(name= "city_state")
    private String cityState;

    @Column(name= "aqi_day_value")
    private Integer aqiDayValue;

    @Column(name= "aqi_time_value")
    private Integer aqiTimeValue;

    @Column(name= "aqi_predict")
    private Integer aqiPredict;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityDay() {
        return cityDay;
    }

    public void setCityDay(String cityDay) {
        this.cityDay = cityDay;
    }

    public String getCityTime() {
        return cityTime;
    }

    public void setCityTime(String cityTime) {
        this.cityTime = cityTime;
    }

    public Integer getAqiDayValue() {
        return aqiDayValue;
    }

    public void setAqiDayValue(Integer aqiDayValue) {
        this.aqiDayValue = aqiDayValue;
    }

    public Integer getAqiTimeValue() {
        return aqiTimeValue;
    }

    public void setAqiTimeValue(Integer aqiTimeValue) {
        this.aqiTimeValue = aqiTimeValue;
    }

    public Integer getAqiPredict() {
        return aqiPredict;
    }

    public void setAqiPredict(Integer aqiPredict) {
        this.aqiPredict = aqiPredict;
    }

    public String getCityState() {
        return cityState;
    }

    public void setCityState(String cityState) {
        this.cityState = cityState;
    }

    @Override
    public String toString() {
        return "CityAqi{" +
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
