package com.merpyzf.reviewandroid.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.merpyzf.reviewandroid.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 *
 * 1.开启一个Activity关闭后将数据返回给当前的Actiivty
 *
 *
 */
public class AboutActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_RAW = 3;
    private static final int REQUEST_CODE_THUMB = 4;

    //拍照图片存储的位置
    private String imageSavePath = null;

    private ImageView iv_show_pic;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        iv_show_pic = (ImageView) findViewById(R.id.imageView_show_pic);



        imageSavePath = Environment.getExternalStorageDirectory().toString()+"/thumb.png";

    }


    public void clickOpenNewActivity(View v){

        Intent intent = new Intent(this,BackDataActivity.class);


        /**
         * 参1: 要开启的Activity的意图对象
         * 参2: 请求码
         */
        startActivityForResult(intent,1);


    }


    /**
     * 调用相机拍照返回大图
     * @param v
     */
    public void clickRaw(View v){


        Intent intent = new Intent();

        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        //设置照片拍照后的存储位置
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(imageSavePath)));

        startActivityForResult(intent,REQUEST_CODE_RAW);
    }


    /**
     * 调用相机拍照返回缩略图
     * @param v
     */
    public void clickThumb(View v){

        Intent intent = new Intent();

        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(intent,REQUEST_CODE_THUMB);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == 2) {

            String name = data.getStringExtra("name");

            Log.i("wk","从上一个Activity中返回的name:"+name);

        }else if(requestCode == REQUEST_CODE_THUMB){
            //如果返回的是缩略图

            if(data!=null){

                 Bundle bundle = data.getExtras();

                Bitmap bm = (Bitmap) bundle.get("data");

                iv_show_pic.setImageBitmap(bm);
            }else {

                Toast.makeText(this,"你没有进行拍照",Toast.LENGTH_SHORT).show();

            }


        }else if(requestCode == REQUEST_CODE_RAW){


            try {
                InputStream is = new FileInputStream(imageSavePath);

                Bitmap bm = BitmapFactory.decodeStream(is);


                iv_show_pic.setImageBitmap(bm);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
            }
        }



    }
}
