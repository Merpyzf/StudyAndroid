package com.merpyzf.reviewandroid.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.merpyzf.reviewandroid.R;
import com.merpyzf.reviewandroid.utils.SQLiteHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SQLiteActivity extends AppCompatActivity {

    private SQLiteDatabase writableDatabase;
    private TextView tv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        tv_show = (TextView) findViewById(R.id.tv_show);
    }


    public void clickCreateTable(View v){

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        writableDatabase = sqLiteHelper.getWritableDatabase();


        for(int i=0;i<20;i++){

            String name = "name"+i;
            String pwd = "pwd"+i;

            Date date = new Date();


            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String StrDate = simpleDateFormat.format(date);


            ContentValues values = new ContentValues();

            values.put("name","wangke"+i);
            values.put("password","wangke pwd"+i);
            values.put("register_date",StrDate);


          writableDatabase.insert("user_info",null,values);

        }


    }


    public void clickQuery(View v){

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        SQLiteDatabase writableDatabase = sqLiteHelper.getWritableDatabase();




        //查询表中所有数据
//        Cursor cursor = writableDatabase.query("user_info", null, null, null, null, null, null);


        //使用SQl语句来查询数据库中的表数据
        Cursor cursor = writableDatabase.rawQuery("select *from user_info where _id>? ",new String[]{"10"});

        StringBuffer sb = new StringBuffer();

        while (cursor.moveToNext()){


            sb.append("id:"+cursor.getInt(0)+" user_name:"+cursor.getString(1)+"   password:"+cursor.getString(2)+"\n"+" register_date:"+cursor.getString(3)+"\n");

        }

        tv_show.setText(sb.toString());






    }

}
