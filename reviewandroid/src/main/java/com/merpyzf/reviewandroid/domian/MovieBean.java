package com.merpyzf.reviewandroid.domian;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/5.
 */

public class MovieBean {

    //电影名称
    private String movieName;

    //电影封面图片
    private String movieImage;

    //导演
    private ArrayList<String> directors;

    //演员
    private ArrayList<String> casts;

    //电影详情页的Url
    private String movieDesc;

    //电影评分
    private float average;

    //最高评分
    private float max;

    //最低评分
    private float min;

    //上映时间
    private String year;

    //电影所属种类
    private ArrayList<String> genres;


    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }

    public ArrayList<String> getDirectors() {
        return directors;
    }

    public void setDirectors(ArrayList<String> directors) {
        this.directors = directors;
    }

    public ArrayList<String> getCasts() {
        return casts;
    }

    public void setCasts(ArrayList<String> casts) {
        this.casts = casts;
    }

    public String getMovieDesc() {
        return movieDesc;
    }

    public void setMovieDesc(String movieDesc) {
        this.movieDesc = movieDesc;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }









}
