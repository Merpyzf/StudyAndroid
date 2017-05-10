package com.merpyzf.reviewandroid.activity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.merpyzf.reviewandroid.R;
import com.merpyzf.reviewandroid.customui.GradienterView;

public class GradienterActivity extends AppCompatActivity implements SensorEventListener {

    private GradienterView mGradienterView;
    private SensorManager sm;
    private Sensor orientationSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradienter);
        mGradienterView = (GradienterView) findViewById(R.id.GradienterView);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        orientationSensor = sm.getDefaultSensor(Sensor.TYPE_ORIENTATION);

    }

    @Override
    protected void onResume() {
        super.onResume();


        sm.registerListener(this,orientationSensor,SensorManager.SENSOR_DELAY_GAME);


    }

    @Override
    protected void onStop() {

        sm.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {


        float[] values = event.values;

        mGradienterView.xScale = values[2]/180;
        mGradienterView.yScale = values[1]/180;
        mGradienterView.postInvalidate();

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
