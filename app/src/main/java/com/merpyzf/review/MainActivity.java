package com.merpyzf.review;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_show = (TextView) findViewById(R.id.textView_show);
    }



    public void add(View v){


        ContentValues values = new ContentValues();

        values.put("book_name","在细雨中呼喊");
        values.put("author","余华");

        getContentResolver().insert(Uri.parse("content://com.merpyzf.reviewandroid.provider.MyBookContentProvider/insert"),values);



    }

    public void delete(View v){


        int delete = getContentResolver().delete(Uri.parse("content://com.merpyzf.reviewandroid.provider.MyBookContentProvider/delete"), "_id=?", new String[]{"1"});





    }

    public void upData(View v){

        ContentValues values = new ContentValues();
        values.put("book_name","岛上书店");

        getContentResolver().update(Uri.parse("content://com.merpyzf.reviewandroid.provider.MyBookContentProvider/update"),values,"_id=?",new String[]{"4"});




    }


    public void query(View v){

        Cursor cursor = getContentResolver().query(Uri.parse("content://com.merpyzf.reviewandroid.provider.MyBookContentProvider/query"), null, null, null, null);

        StringBuilder builder = new StringBuilder();
        while (cursor.moveToNext()){



            String id = cursor.getString(0);
            String bookName = cursor.getString(1);
            String bookAuthor = cursor.getString(2);
            String bookDesc = cursor.getString(3);
            String bookDate = cursor.getString(4);

            builder.append("id: "+id+"bookName: "+bookName+"bookAuthor: "+bookAuthor+"bookDesc: "+bookDesc+"bookDate: "+bookDate+"\n");



        }

        tv_show.setText(builder.toString());


    }
}

