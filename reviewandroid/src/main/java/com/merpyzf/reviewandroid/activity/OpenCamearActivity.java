package com.merpyzf.reviewandroid.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.merpyzf.reviewandroid.R;

import java.io.File;

/**
 * 开启相机，并将拍摄的图片以缩略图的形式返回
 */
public class OpenCamearActivity extends AppCompatActivity {


    private String imagePath;
    private int requestCode = 0;
    private int requestCode_1 = 1;
    private ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_camear);

        imagePath = Environment.getExternalStorageDirectory().getPath() + "/temp.png";

        mImageView = (ImageView) findViewById(R.id.imageView);


    }


    public void clickOpenCamera(View v) {


        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, requestCode);


    }

    //开启相机获取原始图片
    public void clickOpenCamera1(View v) {


        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(imagePath)));
        startActivity(intent);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == RESULT_OK) {

            switch (requestCode) {


                case 0:
                    Bundle bundle = data.getExtras();

                    Bitmap bitmap = (Bitmap) bundle.get("data");

                    mImageView.setImageBitmap(bitmap);

                    break;



                case 1:

                    Bitmap bitmap1 = BitmapFactory.decodeFile(imagePath);
                    mImageView.setImageBitmap(bitmap1);


            }

        }


    }
}

