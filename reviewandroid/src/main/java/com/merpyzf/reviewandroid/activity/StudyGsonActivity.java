package com.merpyzf.reviewandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.merpyzf.reviewandroid.R;
import com.merpyzf.reviewandroid.domian.PersonData;
import com.merpyzf.reviewandroid.domian.PersonInfo;
import com.merpyzf.reviewandroid.domian.WeatherBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudyGsonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_gson);


//        getJson();

        String json = "{\n" +
                "    \"date\": \"2014-05-10\",\n" +
                "    \"error\": 0,\n" +
                "    \"results\": [\n" +
                "        {\n" +
                "            \"currentCity\": \"南京\",\n" +
                "            \"weather_data\": [\n" +
                "                {\n" +
                "                    \"date\": \"周六(今天, 实时：19℃)\",\n" +
                "                    \"dayPictureUrl\": \"http://api.map.baidu.com/images/weather/day/dayu.png\",\n" +
                "                    \"nightPictureUrl\": \"http://api.map.baidu.com/images/weather/night/dayu.png\",\n" +
                "                    \"temperature\": \"18℃\",\n" +
                "                    \"weather\": \"大雨\",\n" +
                "                    \"wind\": \"东南风5-6级\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"date\": \"周日\",\n" +
                "                    \"dayPictureUrl\": \"http://api.map.baidu.com/images/weather/day/zhenyu.png\",\n" +
                "                    \"nightPictureUrl\": \"http://api.map.baidu.com/images/weather/night/duoyun.png\",\n" +
                "                    \"temperature\": \"21 ~ 14℃\",\n" +
                "                    \"weather\": \"阵雨转多云\",\n" +
                "                    \"wind\": \"西北风4-5级\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"status\": \"success\"\n" +
                "}";



//        parseJson(json);



        parseSimpleJson();



    }

    /**
     * 解析简单的Json数据
     */
    private void parseSimpleJson() {

        String simpleJson = "[\"Android\",\"Java\",\"PHP\"]";

        String[] strings = new Gson().fromJson(simpleJson, String[].class);

        for(int i = 0;i<strings.length;i++){

            Log.i("wk",strings[i]);
        }


    }

    /**
     * 解析Json数据
     */
    private void parseJson(String json) {


        Gson gson = new Gson();

        WeatherBean weatherBean = gson.fromJson(json, WeatherBean.class);

        String date = weatherBean.getDate();

        Log.i("wk","当前日期:"+date);

        int error = weatherBean.getError();

        Log.i("wk","错误码:"+error);

        List<WeatherBean.resultBean> results = weatherBean.getResults();
        
        for (int i=0;i<results.size();i++){


            String currentCity = results.get(i).getCurrentCity();

            Log.i("wk","当前城市:"+currentCity);

            List<WeatherBean.weather_data> weather_data = results.get(i).getWeather_data();
            
            for(int j = 0;j<weather_data.size();j++){

                String week = weather_data.get(j).getDate();

                Log.i("wk","具体日期:"+week);

                String dayPictureUrl = weather_data.get(j).getDayPictureUrl();
                Log.i("wk","dayPictureUrl:"+dayPictureUrl);
                String nightPictureUrl = weather_data.get(j).getNightPictureUrl();
                Log.i("wk","nightPictureUrl:"+nightPictureUrl);

                String temperature = weather_data.get(j).getTemperature();
                Log.i("wk","temperature:"+temperature);

                String weather = weather_data.get(j).getWeather();

                Log.i("wk","weather:"+weather);
                String wind = weather_data.get(j).getWind();

                Log.i("wk","wind:"+wind);




            }

        }
        


        Log.i("wk","date:"+date);

        
        
        
    }


    private void getJson() {
        ArrayList<PersonInfo> personInfos = new ArrayList<PersonInfo>();

        for(int i=0;i<3;i++){

            PersonInfo personInfo = new PersonInfo();

            personInfo.setName("王珂"+i);
            personInfo.setAge(i+10);

            HashMap<String, String> map = new HashMap<>();

            map.put("中学","新华中学");
            personInfo.setSchool(map);

            personInfos.add(personInfo);

        }

        PersonData personData = new PersonData();

        personData.setResult(1);
        personData.setPersonInfos(personInfos);


        Gson gson = new Gson();

        String json = gson.toJson(personData);


        Log.i("wk","json:"+json);
    }
}
