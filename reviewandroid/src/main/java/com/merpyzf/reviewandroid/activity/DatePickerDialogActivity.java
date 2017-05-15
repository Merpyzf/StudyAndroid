package com.merpyzf.reviewandroid.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.merpyzf.reviewandroid.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePickerDialogActivity extends AppCompatActivity {

    private TextView tv_show_date;
    private DatePickerDialog.OnDateSetListener listener;
    private Calendar calendar;
    private Calendar mCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker_dialog);

        //创建日期对象
        calendar = Calendar.getInstance();
        mCalendar = (Calendar) calendar.clone();

        tv_show_date = (TextView) findViewById(R.id.tv_show_date);

        listener = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //将选择的年月日设置给日期对象
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);


                Log.i("wk","year==>"+year+" month==>"+month+" dayofmonth==>"+dayOfMonth);

                //通过calendar获取日期对象
                Date time = calendar.getTime();

                //格式化日期
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String s = dateFormat.format(time);

                Log.i("wk","当前选中的时间==>"+s);

                tv_show_date.setText(s);


            }
        };


    }

    public void clickShow(View v){


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, listener, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));


        datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {



            }
        });

        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"取消",new DialogInterface.OnClickListener(){


            @Override
            public void onClick(DialogInterface dialog, int which) {



            }
        });

        datePickerDialog.show();






    }

}
