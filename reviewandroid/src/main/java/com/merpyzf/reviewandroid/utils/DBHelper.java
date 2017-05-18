package com.merpyzf.reviewandroid.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 *
 *
 */

public class DBHelper extends SQLiteOpenHelper {

    //用于创建表的语句
    private String sqlCreateT;

    public DBHelper(Context context, String dbName, String sqlCreateT) {
        super(context, dbName, null, 2);
        this.sqlCreateT = sqlCreateT;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(sqlCreateT);
        Log.i("wk","表结构被创建了");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
