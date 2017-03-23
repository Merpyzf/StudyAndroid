package com.merpyzf.reviewandroid.domian;

import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */

public class WeatherBean {

    private String  date;
    private int error;
    private List<resultBean> results;



    public static class resultBean{

        private String currentCity;
        private List<weather_data> weather_data;

        public String getCurrentCity() {
            return currentCity;
        }

        public void setCurrentCity(String currentCity) {
            this.currentCity = currentCity;
        }

        public List<WeatherBean.weather_data> getWeather_data() {
            return weather_data;
        }

        public void setWeather_data(List<WeatherBean.weather_data> weather_data) {
            this.weather_data = weather_data;
        }
    }

    public static class weather_data{

        private String date;
        private String dayPictureUrl;
        private String nightPictureUrl;
        private String temperature;
        private String weather;
        private String wind;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDayPictureUrl() {
            return dayPictureUrl;
        }

        public void setDayPictureUrl(String dayPictureUrl) {
            this.dayPictureUrl = dayPictureUrl;
        }

        public String getNightPictureUrl() {
            return nightPictureUrl;
        }

        public void setNightPictureUrl(String nightPictureUrl) {
            this.nightPictureUrl = nightPictureUrl;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getWind() {
            return wind;
        }

        public void setWind(String wind) {
            this.wind = wind;
        }
    }

    private String status;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public List<resultBean> getResults() {
        return results;
    }

    public void setResults(List<resultBean> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
