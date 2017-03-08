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

import com.merpyzf.reviewandroid.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 *
 * 开启相机，并将拍摄的图片以缩略图的形式返回
 *
 */
public class OpenCamearActivity extends AppCompatActivity {

    //请求码
    private static final int REQUEST_CODE = 1;

    private static final int REQUEST_CODE_1 = 2;
    private ImageView imageView;

    private String photoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_camear);
        imageView = (ImageView) findViewById(R.id.imageView);

        photoPath = Environment.getExternalStorageDirectory().toString()+"/temp.png";





    }


    public void clickOpenCamera(View v){

        Intent intent = new Intent();

        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(intent,REQUEST_CODE);



    }

    //开启相机获取原始图片
    public void clickOpenCamera1(View v){

        Intent intent = new Intent();

        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        //设置照片拍照后的存储位置
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(photoPath)));

        startActivityForResult(intent,REQUEST_CODE_1);



    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK){

            if(requestCode == REQUEST_CODE){

                Bundle bundle = data.getExtras();
                //获取到的图片为缩略图
                Bitmap bitmap = (Bitmap) bundle.get("data");

                imageView.setImageBitmap(bitmap);

            }else if(requestCode == REQUEST_CODE_1){

                Log.i("wk","执行到了");

                //加载原始图片到ImageView
                try {
                    FileInputStream fis = new FileInputStream(photoPath);

                    Bitmap bp = BitmapFactory.decodeStream(fis);

                    imageView.setImageBitmap(bp);


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                }


            }

        }






    }
}

