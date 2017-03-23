package com.merpyzf.reviewandroid.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.merpyzf.reviewandroid.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * 测试截图，以及WindowManager的使用
 *
 */
public class CaptureScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_screen);

        WindowManager windowManager = getWindowManager();

        Display display = windowManager.getDefaultDisplay();

        int w = display.getWidth();
        int h = display.getHeight();

        Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);

        View decorView = this.getWindow().getDecorView();

        decorView.setDrawingCacheEnabled(true);

        Bitmap bitmap = decorView.getDrawingCache();

        try {
            File directory = Environment.getExternalStorageDirectory();

            File file = new File(directory, "screen.png");

            FileOutputStream out = new FileOutputStream(file);

            bitmap.compress(Bitmap.CompressFormat.PNG,90,out);

            try {
                out.flush();
               out.close();

                Toast.makeText(this,"截图保存成功",Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
        }


    }
}
