package com.merpyzf.reviewandroid.activity;

import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.merpyzf.reviewandroid.R;

/**
 * 内容观察者的使用学习
 *
 */
public class ContentObserverActivity extends AppCompatActivity {


    //管理联系人的uri
    private Uri UriContacts = ContactsContract.Contacts.CONTENT_URI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_observer);

        //注册一个内容观察者
        getContentResolver().registerContentObserver(ContactsContract.Contacts.CONTENT_URI,true,new ContactsObserver(new Handler()));


    }

    //创建内容观察者的监听器类
    class ContactsObserver extends ContentObserver{


        public ContactsObserver(Handler handler) {
            super(handler);
        }


        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);

            Log.i("wk","联系人的数据库内容发生改变了");


            Cursor cursor = getContentResolver().query(UriContacts, null, null, null, null);

            while (cursor.moveToNext()){

                String _ID = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                Log.i("wk","_ID:"+_ID);

                String NAME = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                Log.i("wk","NAME:"+NAME);


                /**
                 *
                 * 查询联系人的号码信息
                 *
                 */

            }


        }



    }
}
