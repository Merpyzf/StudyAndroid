package com.merpyzf.reviewandroid.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.merpyzf.reviewandroid.utils.SQLiteHelper1;

/**
 *
 * 内容提供者部分的代码练习
 *
 */

public class MyBookContentProvider extends ContentProvider {


    private static final int QUERY_SUCCESS = 1;
    private static final int INSERT_SUCCESS = 2;
    private static final int DELETE_SUCCESS = 3;
    private static final int UPDATE_SUCCESS = 4;


    //创建一个Uri的匹配器
    private static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);


    static {

        //添加匹配
        sUriMatcher.addURI("com.merpyzf.reviewandroid.provider.MyBookContentProvider", "query", QUERY_SUCCESS);

        sUriMatcher.addURI("com.merpyzf.reviewandroid.provider.MyBookContentProvider", "insert", INSERT_SUCCESS);

        sUriMatcher.addURI("com.merpyzf.reviewandroid.provider.MyBookContentProvider", "delete", DELETE_SUCCESS);

        sUriMatcher.addURI("com.merpyzf.reviewandroid.provider.MyBookContentProvider", "update", UPDATE_SUCCESS);


    }

    private SQLiteDatabase mDBWriter;


    @Override
    public boolean onCreate() {

        //初始化数据库操作对象

//        SQLiteHelper1 sqLiteHelper1 = new SQLiteHelper1(getContext());
//
//        mDBWriter = sqLiteHelper1.getWritableDatabase();


        return true;
    }

    /**
     * 查询所有数据
     *
     * @param uri
     * @param String[] projection
     * @param String   selection
     * @param String[] selectionArgs
     * @param String   sortOrder
     * @return
     */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {


        if (sUriMatcher.match(uri) == QUERY_SUCCESS) {


            Cursor curosr = mDBWriter.query("t_book", strings, s, strings1, null, null, s1);

            Log.i("wk", "匹配成功！");
            return curosr;


        } else {

            Log.i("wk", "匹配失败");
            return null;


        }


    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }


    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        if (sUriMatcher.match(uri) == INSERT_SUCCESS) {


            long i = mDBWriter.insert("t_table", null, contentValues);

            Log.i("wk", "插入成功,影响的行数:" + i);

            return uri;

        } else {


            Log.i("wk", "匹配失败");


            return null;
        }


    }

    /**
     * @param uri
     * @param String   selection
     * @param String[] selectionArgs
     * @return
     */
    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {


        if (sUriMatcher.match(uri) == DELETE_SUCCESS) {


            int i = mDBWriter.delete("t_book", s, strings);

            Log.i("wk", "删除 影响的行数:" + i);

            return i;


        } else {

            Log.i("wk", "匹配失败");
            return 0;
        }


    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {


        if (sUriMatcher.match(uri) == UPDATE_SUCCESS) {

            int update = mDBWriter.update("t_book", contentValues, s, strings);

            Log.i("wk", "更新 影响的行数:" + update);


            return update;


        } else {


            return 0;
        }


    }
}
