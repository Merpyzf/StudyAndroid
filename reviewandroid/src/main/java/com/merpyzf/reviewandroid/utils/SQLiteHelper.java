package com.merpyzf.reviewandroid.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wangke on 2017/2/26.
 * SQLite数据库的使用
 *
 */

public class SQLiteHelper extends SQLiteOpenHelper {


    //用于创建用户登录的数据表

    String sql = "create table user_info(_id integer primary key autoincrement,name text,password text, register_date TIMESTAMP)";





    //初始化数据库
    public SQLiteHelper(Context context) {

        /**
         * context:上下文对象
         * name :数据库名
         * factory :自定义的Curosr,一般为 null
         * version :当前的版本号
         */
        super(context, "Admin.db", null, 2);
    }

    /**
     * 此方法一般用于创建数据库的表结构
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(sql);

    }

    /**
     *
     * 修改当前数据库的版本号，用于更新数据库的表结构
     *
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


        sqLiteDatabase.execSQL("create table user_info(_id integer primary key autoincrement,name text,password text, register_date TIMESTAMP)");

    }
}
