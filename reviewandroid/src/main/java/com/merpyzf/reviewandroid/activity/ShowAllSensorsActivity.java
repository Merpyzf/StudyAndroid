package com.merpyzf.reviewandroid.activity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.merpyzf.reviewandroid.R;

import java.util.List;

public class ShowAllSensorsActivity extends AppCompatActivity {

    private TextView tv_show_sensors;

    private SensorManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_sensors);
        tv_show_sensors = (TextView) findViewById(R.id.tv_all_sensors);


        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> sensorList = sm.getSensorList(Sensor.TYPE_ALL);

        StringBuilder sb = new StringBuilder();

        sb.append("当前手机拥有的传感器的个数:" + sensorList.size() + "\n" + "分别有:" + "\n");

        for (Sensor sensor : sensorList) {

            sb.append("\n");
            sb.append("传感器名称:"+sensor.getName()+"\n");
            sb.append("传感器供应商:"+sensor.getVendor()+"\n");
            sb.append("传感器耗电量:"+sensor.getPower()+"\n");
            sb.append("传感器值的最大范围:"+sensor.getMaximumRange()+"\n");
            sb.append("传感器版本:"+sensor.getVersion()+"\n");
            sb.append("\n");
            sb.append("-----------------------------------------------");

        }


        tv_show_sensors.setText(sb.toString());


    }


}
