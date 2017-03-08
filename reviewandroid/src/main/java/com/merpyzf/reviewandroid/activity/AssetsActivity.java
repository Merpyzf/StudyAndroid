package com.merpyzf.reviewandroid.activity;

import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.merpyzf.reviewandroid.R;

import java.io.IOException;
import java.io.InputStream;

public class AssetsActivity extends AppCompatActivity {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets);
        imageView = (ImageView) findViewById(R.id.imageView_showQr);

        try{

            //获取一个AssetManager
            AssetManager assets = getAssets();
            //通过open()方法获取文件名对应的原始二进制输入流
            InputStream is = assets.open("girl.jpg");
            imageView.setImageBitmap(BitmapFactory.decodeStream(is));
            is.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
