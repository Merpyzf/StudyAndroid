package com.merpyzf.reviewandroid.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.Toast;

import com.merpyzf.reviewandroid.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveImageActivity extends AppCompatActivity  {

    private ImageView iv_save;
    private Context context;
    private Bitmap bm;
    private File mDirectory;
    private int width;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_image);

        context = this;

        width = getWindowManager().getDefaultDisplay().getWidth();
        height = getWindowManager().getDefaultDisplay().getHeight();

        iv_save = (ImageView) findViewById(R.id.imageview_save);

        mDirectory = new File(Environment.getExternalStorageDirectory().getPath() + "/wangke");


        final GestureDetector gestureDetector = new GestureDetector(this, new MyGestureDetector());

        iv_save.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                return gestureDetector.onTouchEvent(event);
            }
        });


        try {
            bm = BitmapFactory.decodeStream(getAssets().open("girl.jpg"));

            iv_save.setImageBitmap(bm);

            /**
             * 
             * 监听ImageView的布局是否加载完毕
             */
            iv_save.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {


//                    captureImage();
                    
                }
            });






        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }


    /**
     * 按钮事件:截取当前ImageView的内容
     * @param v
     */
    public void btnCaptureImageView(View v){

        captureImage();
        
    }
    
    public void btnCaptureScreen(View v){

        CaptureScreen();
        Toast.makeText(context,"截取当前屏幕成功",Toast.LENGTH_SHORT).show();


    }

    /**
     * 截取当前屏幕
     */
    private void CaptureScreen() {

        View view = this.getWindow().getDecorView();
        /*
            setDrawingCacheEnabled为true后，使用getDrawingCache会将该View绘制成bitmap并返回，
            setDrawingCacheEnabled为fale后，会清空缓存
         */
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = view.getDrawingCache();

        try {
            FileOutputStream fos = new FileOutputStream(new File(mDirectory, "/截取当前的屏幕.png"));

            /**
             * 创建一个新的Bitmap对之前截取的bitmap进行裁切
             */
            Bitmap bm = Bitmap.createBitmap(bitmap,0,0,width,height);

            bm.compress(Bitmap.CompressFormat.PNG,100,fos);

            Toast.makeText(context,"截取当前屏幕成功",Toast.LENGTH_SHORT).show();

            fos.flush();



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }


    }


    /**
     * 截取当前ImageView
     */
    private void captureImage() {


        Bitmap bitmap = Bitmap.createBitmap(iv_save.getMeasuredWidth(), iv_save.getMeasuredHeight(), Bitmap.Config.RGB_565);
        //将bitmap作为canvas的画布
        Canvas canvas = new Canvas(bitmap);
        
        //将ImageView上的内容绘制到bitmap上去
        iv_save.draw(canvas);

        try {
            File file = new File(Environment.getExternalStorageDirectory().getPath()+"/wangke","/截取image.png");

            FileOutputStream fos = new FileOutputStream(file);

            bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);

            fos.flush();
            fos.close();




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }


    }


    class MyGestureDetector implements GestureDetector.OnGestureListener{


        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

//            Toast.makeText(context,"保存图片",Toast.LENGTH_SHORT).show();

            try {


                //如果目录存在
                if(mDirectory.exists()){


                    File file1 = new File(mDirectory, System.currentTimeMillis() + ".png");
                    bm.compress(Bitmap.CompressFormat.PNG,100,new FileOutputStream(file1));
                    Toast.makeText(context,"保存成功",Toast.LENGTH_SHORT).show();

                }else {

                    mDirectory.mkdir();


                    File file1 = new File(mDirectory, System.currentTimeMillis() + ".png");
                    bm.compress(Bitmap.CompressFormat.PNG,100,new FileOutputStream(file1));

                    Toast.makeText(context,"创建目录并保存成功",Toast.LENGTH_SHORT).show();

                }



            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } finally {
            }


        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    }
}
