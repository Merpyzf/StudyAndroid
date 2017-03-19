package com.merpyzf.reviewandroid.activity;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.merpyzf.reviewandroid.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * 1.开启一个Activity关闭后将数据返回给当前的Actiivty
 *
 *
 */
public class AboutActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_RAW = 3;
    private static final int REQUEST_CODE_THUMB = 4;
    private static final int REQUEST_CODE_IMAGES = 5;

    //拍照图片存储的位置
    private String imageSavePath = null;

    private ImageView iv_show_pic;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        iv_show_pic = (ImageView) findViewById(R.id.imageView_show_pic);
        //设置拍照图片要存储的位置
       imageSavePath =  Environment.getExternalStorageDirectory().toString()+"/tmp.jpg";


        iv_show_pic.scrollTo(0,20);

        try {

            //播放资产目录下的音乐文件

            AssetFileDescriptor fd = getAssets().openFd("lz.mp3");

            MediaPlayer mediaPlayer = new MediaPlayer();

            mediaPlayer.setDataSource(fd.getFileDescriptor(),fd.getStartOffset(),fd.getLength());

            mediaPlayer.prepare();
            mediaPlayer.start();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }


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


    /**
     * 从图库中选择图片并返回选择的图片
     * @param v
     */
    public void clickImage(View v){


        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");

        startActivityForResult(intent,REQUEST_CODE_IMAGES);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //获取拍照的原图
        if(requestCode==REQUEST_CODE_RAW){


            try {
                Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(imageSavePath));

                if(bitmap!=null){

                    iv_show_pic.setImageBitmap(bitmap);

                }


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
            }

            //拍照返回缩略图
        }else if(requestCode == REQUEST_CODE_THUMB){

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");


            iv_show_pic.setImageBitmap(bitmap);




        }else if(requestCode == REQUEST_CODE_IMAGES){

            Uri uri = data.getData();


            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                //显示当前选择的图片
                iv_show_pic.setImageBitmap(bitmap);

                //获取图片的路径

                Cursor cursor = getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);

                cursor.moveToFirst();

                //获取当前选择的图片的路径
                String imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));

                Toast.makeText(AboutActivity.this,"当前选择的图片的路径:"+imagePath,Toast.LENGTH_SHORT).show();



            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }
}
