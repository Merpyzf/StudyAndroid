package com.merpyzf.reviewandroid.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.merpyzf.reviewandroid.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class CalculateStopMoneyActivity extends AppCompatActivity {

    private TextView tv_time;
    private TextView tv_money;
    private Button btn_start;
    private Timer timer;
    //0表示正在计时的时候退出了
    private int Flag = 1;
     Handler mHandler = null;
    private long time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_stop_money);

        tv_time  = (TextView) findViewById(R.id.tv_time);
        tv_money  = (TextView) findViewById(R.id.tv_money);
        btn_start = (Button)findViewById(R.id.btn_start);


        mHandler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                long time = (Long) msg.obj;

                Date date = new Date(time);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

                String s = simpleDateFormat.format(date);


                Log.i("wk",s);


                tv_time.setText(s);


            }
        };






        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(btn_start.getText().equals("结束停车")){

                    timer.cancel();

                    btn_start.setText("开始停车");

                    Flag =1;


                }else {

                    Flag = 0;

                    btn_start.setText("结束停车");

                    timer = new Timer();

                    timer.schedule(new myTask(),0,1000);
                }
            }
        });


        long lastTime = getSharedPreferences("time",MODE_PRIVATE).getLong("Time",-1);
        long lastSystemTime = getSharedPreferences("time",MODE_PRIVATE).getLong("lastTime",-1);

        time = lastTime+(System.currentTimeMillis()-lastSystemTime);
        timer.schedule(new myTask(),0,1000);




    }





    class myTask extends TimerTask {

        @Override
        public void run() {



            Message msg = new Message();

            msg.obj = time;

            mHandler.sendMessage(msg);
            time += 1000;


        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        timer.cancel();

        if(Flag==0){


            getSharedPreferences("time",MODE_PRIVATE).edit().putLong("Time",time).commit();
            getSharedPreferences("time",MODE_PRIVATE).edit().putLong("lastTime",System.currentTimeMillis());


        }




    }
}


