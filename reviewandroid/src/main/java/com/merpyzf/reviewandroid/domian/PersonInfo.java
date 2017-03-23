package com.merpyzf.reviewandroid.domian;

import java.util.Map;

/**
 * Created by Administrator on 2017/3/20.
 */

public class PersonInfo {

    private String name;
    private int age;
    private Map<String,String> school;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Map<String, String> getSchool() {
        return school;
    }

    public void setSchool( Map<String,String> school) {
        this.school = school;
    }
}
