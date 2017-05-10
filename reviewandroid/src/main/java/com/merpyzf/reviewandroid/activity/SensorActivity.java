package com.merpyzf.reviewandroid.activity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.merpyzf.reviewandroid.R;

/**
 *
 * 传感器的使用示例
 *
 */
public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    private TextView tv_info;
    private TextView tv_orientation;
    private SensorManager sm;
    private Sensor accelerSensor;
    private Sensor orientationSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        tv_info = (TextView) findViewById(R.id.tv_show_sensor_info);
        tv_orientation = (TextView) findViewById(R.id.tv_show_sensor_orientation);


        //1.获取传感器的管理服务
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //获取加速度传感器
        accelerSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //方向传感器
        orientationSensor = sm.getDefaultSensor(Sensor.TYPE_ORIENTATION);



    }

    @Override
    protected void onResume() {
        super.onResume();

        //2.为系统的加速度传感器注册监听器
        /**
         * 参数二:Sensor对象
         * 参数三:指定获取传感器数据的频率
         */
        sm.registerListener(this, accelerSensor, SensorManager.SENSOR_DELAY_GAME);
        sm.registerListener(this,orientationSensor,SensorManager.SENSOR_DELAY_GAME);


    }


    @Override
    protected void onStop() {
        //取消注册
        sm.unregisterListener(this);
        super.onStop();
    }

    //SensorEventListener接口必须实现的两个方法

    //当传感器的值发生改变的时候回调此方法
    @Override
    public void onSensorChanged(SensorEvent event) {



        //获取传感器x,y,z坐标上的值
        float[] values = event.values;
        int type = event.sensor.getType();

        switch (type) {

            case Sensor.TYPE_ACCELEROMETER:
                StringBuilder sb = new StringBuilder();
                tv_info.append("加速度传感器:\n");
                sb.append("x方向上的加速度==>" + values[0] + "\n");
                sb.append("y方向上的加速度==>" + values[1] + "\n");
                sb.append("Z方向上的加速度==>" + values[2] + "\n");
                tv_info.setText(sb.toString());


                break;



            case Sensor.TYPE_ORIENTATION:

                StringBuilder sb1 = new StringBuilder();
                sb1.append("方向传感器:\n");

                sb1.append("绕Z轴转过的角度==>"+values[0]+"\n");
                //表示手机顶部或尾部翘起的角度
                sb1.append("绕X轴转过的角度==>"+values[1]+"\n");
                //表示手机左侧或右侧翘起的角度
                sb1.append("绕Y轴转过的角度==>"+values[2]+"\n");

                tv_orientation.setText(sb1.toString());

                break;



        }

    }

    //当传感器的精度发生变化时调用此方法
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


    }
}
