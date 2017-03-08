package com.merpyzf.reviewandroid.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintJob;
import android.provider.ContactsContract;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.merpyzf.reviewandroid.R;

public class ImplicitIntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent);



    }

    /**
     * 使用隐式意图开启 GestureDetectorActivity
     *
     * @param v
     */
    public void clickActivity(View v) {


        Intent intent = new Intent();

        /**
         * 注意:如果想要通过隐式意图开启一个Activity则必须在<intent - filter>标签中指定<Category>,否则Activity无法开启。
         *
         */
        intent.setAction("com.merpyzf.reviewandroid");


        /**
         *
         * Action匹配正确,如果Category匹配错误的话Activity仍然无法开启
         *
         *  虽然没有指定Category,但intent的category已有一个默认的属性值"android.intent.category.DEFAULT",因此需要隐式启动的Activiy必须在清单文件中
         *  添加Category这一项，否则匹配不上要启动的Activity
         *
         **/
        //    intent.addCategory(Intent.CATEGORY_DEFAULT);


        startActivity(intent);

    }


    /**
     * 开启系统浏览器
     * <p>
     * Intent.ACTION_VIEW
     *
     * @param v
     */


    public void clickBroswer(View v) {


        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        startActivity(intent);


    }

    /**
     * 播放音乐
     * <p>
     * Action : Intent.ACTION_VIEW
     * DATA: playUri
     * Type: "audio/mp3"
     *
     * @param v
     */
    public void clickPlayMusic(View v) {

        String musicPath = Environment.getExternalStorageDirectory().toString() + "/misc.mp3";

        Uri playUri = Uri.parse("file:" + musicPath);
        Intent intent = new Intent(Intent.ACTION_VIEW, playUri);
        intent.setDataAndType(playUri, "audio/mp3");

        startActivity(intent);


    }


    /**
     * 调用拨打电话界面
     *
     * @param v
     */
    public void clickCall(View v) {

        Uri uri = Uri.parse("tel:10086");

        Intent intent = new Intent(Intent.ACTION_DIAL, uri);

        startActivity(intent);


    }

    /**
     * 调用直接拨打电话
     *
     * @param v
     */
    public void clickCallPhone(View v) {
        Uri uri = Uri.parse("tel:10086");

        Intent intent = new Intent(Intent.ACTION_CALL, uri);

        startActivity(intent);

    }


    /**
     * 调用 跳转到短信发送页面
     *
     * @param v
     */
    public void clickSend(View v) {

        Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.putExtra("sms_body", "haha! send aMessage");

        intent.setType("vnd.android-dir/mms-sms");

        startActivity(intent);
    }

    /**
     * 调用直接发送短信
     *
     * @param v
     */
    public void clickSendMsg(View v) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:10086"));
        intent.putExtra("sms_body", "haha! send aMessage!");
        startActivity(intent);
    }


    public void clickAll(View viwe) {


        Intent intent = new Intent(Intent.ACTION_VIEW);

        startActivity(intent);

    }


    /**
     * 从通讯录中选择一个联系人并返回给当前activity
     *
     * @param v
     */
    public void clickGetPhoneNumber(View v) {


        Intent intent = new Intent();

        //设置Intent的Action属性
        intent.setAction(Intent.ACTION_GET_CONTENT);

        //设置Intent的Type属性,要记住！！！
        intent.setType("vnd.android.cursor.item/phone");


        startActivityForResult(intent, 1);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case 1:


                //获取返回的数据
                Uri uri = data.getData();

                CursorLoader loader = new CursorLoader(this, uri, null, null, null, null);
                Cursor cursor = loader.loadInBackground();

                cursor.moveToFirst();

                String contactsId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                String contactsName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                //查询电话号码的cursor
                Cursor cursor_num = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, contactsId + "=" + ContactsContract.CommonDataKinds.Phone._ID, null, null);

                cursor_num.moveToFirst();

                //获取联系人电话号码
                String phoneNum = cursor_num.getString(cursor_num.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                Log.i("wk", "联系人Id:" + contactsId + " 联系人姓名:" + contactsName + "联系人电话号码:" + phoneNum);

                break;


            case 2:

                Uri uri2 = data.getData();

                CursorLoader cursorLoader = new CursorLoader(this);

                cursorLoader.setUri(uri2);

                Cursor cursor1 = cursorLoader.loadInBackground();


                cursor1.moveToFirst();


                //获取联系人的id
                String contactsId1 = cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts._ID));

                //获取联系人的名字
                String contactsName1 = cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));


                //获取联系人的电话号码

                Cursor cursor2 = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, contactsId1 + "=" + ContactsContract.CommonDataKinds.Phone._ID, null, null);


                cursor2.moveToFirst();

                //通过获取的游标对象，查询联系人的电话号码

                String PhoneNum = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));


                Log.i("wk", "联系人1Id: " + contactsId1 + "联系人1名字:" + contactsName1 + "联系人1电话: " + PhoneNum);


                break;


        }

    }


    /**
     * 从通讯录中选择一个联系人并返回当前的Activiy
     *
     * @param v
     */
    public void clickGetPhoneNumber2(View v) {


        Intent intent = new Intent();

        intent.setAction(Intent.ACTION_GET_CONTENT);

        intent.setType("vnd.android.cursor.item/phone");

        startActivityForResult(intent, 2);


    }


}
