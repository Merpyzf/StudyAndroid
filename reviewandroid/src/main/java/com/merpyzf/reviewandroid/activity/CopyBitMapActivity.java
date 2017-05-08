package com.merpyzf.reviewandroid.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.merpyzf.reviewandroid.R;

/**
 * 拷贝原图的副本
 */
public class CopyBitMapActivity extends AppCompatActivity {

    private ImageView iv_raw_imageView;
    private ImageView iv_copy_imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy_bit_map);

        iv_raw_imageView = (ImageView) findViewById(R.id.iv_raw_imageView);
        iv_copy_imageView = (ImageView  ) findViewById(R.id.iv_copy_imageView);

        Bitmap rawBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image);

        iv_raw_imageView.setImageBitmap(rawBitmap);

        //创建一个拷贝的bitmap模板
        Bitmap copyBitmap = Bitmap.createBitmap(rawBitmap.getWidth(), rawBitmap.getHeight(), rawBitmap.getConfig());


        Canvas canvas = new Canvas(copyBitmap);

        //这一步完成原图的拷贝，此时copyBitmap中就有内容了
        canvas.drawBitmap(rawBitmap,new Matrix(),new Paint());

        iv_copy_imageView.setImageBitmap(copyBitmap);



    }
}
