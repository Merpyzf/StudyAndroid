package com.merpyzf.reviewandroid.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

import com.merpyzf.reviewandroid.R;

import java.io.File;
import java.io.FileOutputStream;

public class CopyBitMapActivity extends Activity {

    private Bitmap srcBitmap;
    private ImageView iv;
    private Bitmap copyBitmap;
    private Canvas canvas;
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy_bit_map);
        //[1]找到imageview  显示我们画的内容
        iv = (ImageView) findViewById(R.id.iv_copy_imageView);

        //[2]把bg转换成bitmap
        srcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image);
        //[2.1]创建模板
        copyBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), srcBitmap.getConfig());
        //[2.2]以copybitmap为模板  创建一个画布
        canvas = new Canvas(copyBitmap);
        //[2.3]创建一个画笔
        paint = new Paint();
        //[2.4]开始作画
        canvas.drawBitmap(srcBitmap, new Matrix(), paint);

//		canvas.drawLine(20, 20, 30, 50, paint);

        //[3]把copybitmap显示到iv上
        iv.setImageBitmap(copyBitmap);

        //[4]给iv设置一个触摸事件
        iv.setOnTouchListener(new OnTouchListener() {

            int startX = 0;
            int startY = 0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //[5]获取手指触摸的事件类型
                int action = event.getAction();
                //[6]具体判断一下是什么事件类型
                switch (action) {
                    case MotionEvent.ACTION_DOWN: //按下
                        //[7]获取手指按下坐标
                        startX = (int) event.getX();
                        startY= (int) event.getY();
                        System.out.println("按下:"+startX+"---"+startY);
                        break;

                    case MotionEvent.ACTION_MOVE://移动
                        //[8]获取停止的坐标
                        int stopX = (int) event.getX();
                        int stopY = (int) event.getY();

                        System.out.println("移动:"+stopX+"---"+stopY);

                        //[9]画线
                        canvas.drawLine(startX, startY, stopX, stopY, paint);

                        //[9.1]更新一下起点坐标
                        startX = stopX;
                        startY = stopY;

                        //[10]记得更新ui
                        iv.setImageBitmap(copyBitmap);


                        break;

                    case MotionEvent.ACTION_UP: //抬起;

                        break;
                }
                return true;
            }
        });



    }

    //点击按钮让画笔的颜色 变成红色
    public void click1(View v) {
        //设置画笔颜色
        paint.setColor(Color.RED);
    }


    //让画笔颜色变粗
    public void click2(View v) {
        //设置画笔的宽度
        paint.setStrokeWidth(15);

    }

    //保存大作
    public void click3(View v) {
        /**
         * format 保存图片的格式
         *
         * quality 保存照片的质量
         */
        try {
            File file = new File(Environment.getExternalStorageDirectory().getPath(),"dazuo.png");
            FileOutputStream fos = new FileOutputStream(file);
            copyBitmap.compress(CompressFormat.PNG, 100, fos);

            //发送一条sd卡挂载上来的广播 欺骗一下系统图库应用 说sd卡被挂载了 你去加载图片吧

            Intent intent = new Intent();
            //设置action
            intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
            //设置data
            intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));

            //发送无序广播
            sendBroadcast(intent);

            fos.close(); //关闭流


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
