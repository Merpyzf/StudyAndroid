package com.merpyzf.reviewandroid.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.merpyzf.reviewandroid.R;
import com.merpyzf.reviewandroid.utils.DBHelper;

public class StudySqliteActivity extends AppCompatActivity {

    private SQLiteDatabase mDbWrite;
    private String TABLE_NAME = "company";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_sqlite);

        /**
         * 如果Sqlite中有这个“Admin.db的数据库，创建表结构的方法就不会执行
         * public void onCreate(SQLiteDatabase sqLiteDatabase) 方法
         * ”
         */
        DBHelper dbHelper = new DBHelper(this, "Admin.db", "");

        mDbWrite = dbHelper.getWritableDatabase();






//        delete();

        query();












    }


    public void query(){

//        Cursor cursor = mDbWrite.query(TABLE_NAME, null, "limit 2 offset 3", new String[]{"2", "3"}, "_id", null, "DESC");

        Cursor cursor = mDbWrite.rawQuery("select *from company where _id>? order by salary DESC limit ? ", new String[]{"1","4"});


        while (cursor.moveToNext()){

            int _id = cursor.getInt(0);

            String name = cursor.getString(1);


            int age = cursor.getInt(2);

            String address = cursor.getString(3);

            double salary = cursor.getDouble(4);


            Log.i("wk","_id=> "+_id+"name=> "+name+"age==> "+age+"address==> "+address+"salary==>"+salary);

        }


    }



    public void delete(){

        //将_id为7的那条记录删除
        mDbWrite.delete(TABLE_NAME,"_id = ?",new String[]{"7"});

    }












    public void studyInsert(){

        ContentValues values = new ContentValues();

        values.put("name","xiaohua");
        values.put("age",30);
        values.put("address","tianjin");
        values.put("salary",1000);

        /**
         * 当values参数为空或者里面没有内容的时候，我们insert是会失败的（底层数据库不允许插入一个空行），为了防止这种情况，我们要在这里指定一个 列名，到时候如果发现将要插入的行为空行时，就会将你指定的这个列名的值设为null，然后再向数据库中插入。
         *
         */
        mDbWrite.insert(TABLE_NAME,null,values);


    }

}
