package com.merpyzf.reviewandroid.utils.orm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.merpyzf.reviewandroid.domian.Book;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by 春水碧于天 on 2017/5/23.
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static String DB_NAME = "BOOK.db"; //数据库的名字
    private static DBHelper dbHelper = null;
    private HashMap<String, Dao> daos = new HashMap<String, Dao>();

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {

        try {

            //创建表
            TableUtils.createTable(connectionSource, Book.class);
            Log.i("wk", "onCreate方法执行了");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {


        try {
            TableUtils.dropTable(connectionSource, Book.class, true);

            onCreate(sqLiteDatabase, connectionSource);


            Log.i("wk", "onUpgrade方法执行了");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //创建DBHelper的单例
    public synchronized DBHelper getInstance(Context context) {

        if (dbHelper == null) {

            synchronized (DBHelper.class) {

                if (dbHelper == null) {


                    dbHelper = new DBHelper(context);

                }

            }
        }

        return dbHelper;

    }

    //获取Dao
    public synchronized Dao getDao(Class clazz) {

        Dao dao = null;
        try {
            String className = clazz.getSimpleName();

            if (daos.containsKey(className)) {

                dao = daos.get(className);

            } else {

                dao = super.getDao(clazz);

                daos.put(className, dao);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dao;

    }


}
