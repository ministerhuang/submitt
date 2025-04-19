package com.yaya.submitt.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiJsonMessage {

    @JsonProperty("status")
    private String status;

    @JsonProperty("data")
    private Data data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {
        @JsonProperty("aqi")
        private int aqi;

        @JsonProperty("idx")
        private String idx;

        @JsonProperty("attributions")
        private List<Attribution> attributions;

        @JsonProperty("city")
        private City city;

        @JsonProperty("dominentpol")
        private String dominentpol;

        @JsonProperty("iaqi")
        private Map<String, IAQIValue> iaqi;

        @JsonProperty("time")
        private Time time;

        @JsonProperty("forecast")
        private Forecast forecast;

        public int getAqi() {
            return aqi;
        }

        public void setAqi(int aqi) {
            this.aqi = aqi;
        }

        public String getIdx() {
            return idx;
        }

        public void setIdx(String idx) {
            this.idx = idx;
        }

        public List<Attribution> getAttributions() {
            return attributions;
        }

        public void setAttributions(List<Attribution> attributions) {
            this.attributions = attributions;
        }

        public City getCity() {
            return city;
        }

        public void setCity(City city) {
            this.city = city;
        }

        public String getDominentpol() {
            return dominentpol;
        }

        public void setDominentpol(String dominentpol) {
            this.dominentpol = dominentpol;
        }

        public Map<String, IAQIValue> getIaqi() {
            return iaqi;
        }

        public void setIaqi(Map<String, IAQIValue> iaqi) {
            this.iaqi = iaqi;
        }

        public Time getTime() {
            return time;
        }

        public void setTime(Time time) {
            this.time = time;
        }

        public Forecast getForecast() {
            return forecast;
        }

        public void setForecast(Forecast forecast) {
            this.forecast = forecast;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Attribution {
        @JsonProperty("url")
        private String url;

        @JsonProperty("name")
        private String name;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class City {
        @JsonProperty("geo")
        private List<Double> geo;

        @JsonProperty("name")
        private String name;

        @JsonProperty("url")
        private String url;

        public List<Double> getGeo() {
            return geo;
        }

        public void setGeo(List<Double> geo) {
            this.geo = geo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class IAQIValue {
        @JsonProperty("v")
        private double v;

        public double getV() {
            return v;
        }

        public void setV(double v) {
            this.v = v;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Time {
        @JsonProperty("s")
        private String s;

        @JsonProperty("tz")
        private String tz;

        @JsonProperty("iso")
        private String iso;

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }

        public String getTz() {
            return tz;
        }

        public void setTz(String tz) {
            this.tz = tz;
        }

        public String getIso() {
            return iso;
        }

        public void setIso(String iso) {
            this.iso = iso;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Forecast {
        @JsonProperty("daily")
        private Map<String, List<DailyData>> daily;

        public Map<String, List<DailyData>> getDaily() {
            return daily;
        }

        public void setDaily(Map<String, List<DailyData>> daily) {
            this.daily = daily;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DailyData {
        @JsonProperty("avg")
        private int avg;

        @JsonProperty("day")
        private String day;

        @JsonProperty("max")
        private int max;

        @JsonProperty("min")
        private int min;

        public int getAvg() {
            return avg;
        }

        public void setAvg(int avg) {
            this.avg = avg;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }
}
