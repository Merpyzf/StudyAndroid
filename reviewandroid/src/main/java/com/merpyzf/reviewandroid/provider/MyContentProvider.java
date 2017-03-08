package com.merpyzf.reviewandroid.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.merpyzf.reviewandroid.utils.SQLiteHelper;

/**
 * Created by wangke on 2017/2/26.
 */

public class MyContentProvider extends ContentProvider {

    private static final int QUERY_SUCCEED = 1;
    private static final int ADD_SUCCEED = 1;
    private static final int DELETE_SUCCEED = 1;
    private static final int UPDATE_SUCCEED = 1;


    //1.创建 uri的匹配器
    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);



    //2.增加匹配器
    static {

        sURIMatcher.addURI("com.merpyzf.reviewandroid.userinfo","query",QUERY_SUCCEED);
        sURIMatcher.addURI("com.merpyzf.reviewandroid.userinfo","add",ADD_SUCCEED);
        sURIMatcher.addURI("com.merpyzf.reviewandroid.userinfo","delete",DELETE_SUCCEED);
        sURIMatcher.addURI("com.merpyzf.reviewandroid.userinfo","update",UPDATE_SUCCEED);


    }

    private SQLiteDatabase writableDatabase;

    @Override
    public boolean onCreate() {

        SQLiteHelper sqLiteHelper = new SQLiteHelper(getContext());

        writableDatabase = sqLiteHelper.getWritableDatabase();


        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {

        if(sURIMatcher.match(uri) == QUERY_SUCCEED){

            Cursor cursor = writableDatabase.query("user_info", strings, s, strings1, null, null, s1);

            Log.i("wk","匹配成功");

            return cursor;

        }else{ 


            return null;
        }

    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        if(sURIMatcher.match(uri) ==ADD_SUCCEED){


            long num = writableDatabase.insert("user_info", null, contentValues);

            Log.i("wk","插入了:"+num+"条数据");



        }else{

            return null;
        }

        return uri;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {


        if(sURIMatcher.match(uri) == DELETE_SUCCEED){


            int num = writableDatabase.delete("user_info", s, strings);

            Log.i("wk","匹配成功");
            return num;

        }else {


            Log.i("wk","匹配失败");

            return -1;

        }

    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {

        if(sURIMatcher.match(uri) == UPDATE_SUCCEED){


            int update = writableDatabase.update("user_info", contentValues, s, strings);

            Log.i("wk","匹配成功");

            return update;

        }else {

            Log.i("wk","匹配失败");

            return -1;

        }

    }
}
