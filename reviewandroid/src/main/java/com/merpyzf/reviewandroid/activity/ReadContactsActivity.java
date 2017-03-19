package com.merpyzf.reviewandroid.activity;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.merpyzf.reviewandroid.R;

/**
 *  读取手机中的联系人信息
 *
 */
public class ReadContactsActivity extends AppCompatActivity {

    //管理联系人的Uri
    private static Uri contentUri = ContactsContract.Contacts.CONTENT_URI;

    //管理联系人的电话号码的Uri
    private static Uri phoneNumUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_contacts);


    }

    /**
     * 使用内容解析者查询手机上的联系人信息
     * @param v
     */
    public void clickQuery(View v){


        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        while (cursor.moveToNext()){


            //获取联系人的Id
            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));


            Cursor cursor_phone_num = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,id+"="+ContactsContract.CommonDataKinds.Phone._ID, null, null);


            while (cursor_phone_num.moveToNext()){


                //查询电话信息
                String phone_num = cursor_phone_num.getString(cursor_phone_num.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));


                Log.i("wk","联系人Id: "+id+"姓名: "+name+"电话号码: "+phone_num);


            }



        }







    }


    /**
     * 查询手机中的联系人
     *
     * 第二遍
     * @param v
     */
    public void clickQuery2(View v){


        //管理手机联系人的Uri
         Uri mContentUri = ContactsContract.Contacts.CONTENT_URI;

        Cursor cursor = getContentResolver().query(mContentUri, null, null, null, null);

        while (cursor.moveToNext()){

            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

            Log.i("wk","联系人Id:"+id);

            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

            Cursor cursorPhone = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone._ID + " = " + id, null, null);


            Log.i("wk","联系人姓名:"+name);

            while (cursorPhone.moveToNext()){


                String PhoneNumer = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Log.i("wk","联系人电话:"+PhoneNumer);

            }

        }
    }


    //通过内容提供者进行插入一条联系人的信息
    public void clickInsert(View v){

        ContentValues values = new ContentValues();


        Uri rowContactUri = getContentResolver().insert(ContactsContract.Contacts.CONTENT_URI, values);

        Long rawContactId = ContentUris.parseId(rowContactUri);

        Log.i("wk","rawContactId:"+rawContactId);

        values.clear();

    }


    /**
     * 使用ContentProvider管理多媒体内容
     * 需要添加sdcard的读取权限
     */

    public void clickMediaStore(View v){




        //储存在外置sdk上的音频内容的Contentprovider的Uri
        Uri audioUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        Cursor mediaCursor = getContentResolver().query(audioUri, null, null, null, MediaStore.Audio.Media.DATE_MODIFIED);


        while(mediaCursor.moveToNext()){


            String mediaPath = mediaCursor.getString(mediaCursor.getColumnIndex(MediaStore.Audio.Media.DATA));

            Log.i("wk","音频文件的路径:"+mediaPath);


        }



        //存储在外置sdk上的图片文件内容的ContentProvider的Uri
        Uri MediaUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        //按照修改的时间进行排序
        Cursor cursor = getContentResolver().query(MediaUri, null, null, null, MediaStore.Images.Media.DATE_MODIFIED);


        while (cursor.moveToNext()){

            //MediaStore.Images.Media.DATA 获取文件路径
            String FilePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));

            //MediaStore.Images.Media.DISPLAY_NAME 获取文件名称
            String FileName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));

            //MediaStore.Images.Media.DESCRIPTION 获取文件的详细描述
            String fileDesc = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DESCRIPTION));


            Log.i("wk","详细描述:"+fileDesc);


            Log.i("wk","文件名:"+FileName);

            Log.i("wk","文件路径:"+FilePath);



        }

    }
}
