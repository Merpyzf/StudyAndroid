package com.merpyzf.reviewandroid.utils.orm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.merpyzf.reviewandroid.domian.User;

import java.sql.SQLException;

/**
 * Created by 春水碧于天 on 2017/5/22.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DB_NAME = "sqlite-test.db";

    /**
     * 每张表对应一个
     */
    private static Dao<User, Integer> userDao;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 2);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, User.class); //使用TabUtils进行表的创建
        } catch (SQLException e) {
            e.printStackTrace();
            Log.i("wk", "数据表创建失败");
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {


        try {

            TableUtils.dropTable(connectionSource, User.class, true);
            onCreate(sqLiteDatabase, connectionSource);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getHelper(Context context) {


        if (instance == null) {

            synchronized (DatabaseHelper.class) {

                if (instance == null) {

                    instance = new DatabaseHelper(context);

                }

            }

        }
        return instance;
    }

    /**
     * 获得userDao
     *
     * @return
     * @throws SQLException
     */
    public Dao<User, Integer> getUserDao() throws SQLException {

        if (userDao == null) {

            userDao = getDao(User.class);


        }

        return userDao;

    }


    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();
        userDao = null;
    }
}
