package com.merpyzf.reviewandroid.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 *
 *
 */

public class SQLiteHelper1 extends SQLiteOpenHelper {

    //用于创建表的语句
    private String sqlCreateT;

    public SQLiteHelper1(Context context,String dbName,String sqlCreateT) {
        super(context, dbName, null, 1);
        this.sqlCreateT = sqlCreateT;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(sqlCreateT);
        Log.i("wk","t_book表被创建了");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
