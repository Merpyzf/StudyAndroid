package com.merpyzf.reviewandroid.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.merpyzf.reviewandroid.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Android File读取
 */
public class StudyFileActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_list_view);

        /*
         * 测试对应用内存中的目录进行的文件操作，通过this.getFilesDir()获取私有目录下的用于文件存储的路径
         */
        Test1();

        /*
         *向外部存储写入数据
         */

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){

            Test2();

        }else {

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0);

        }






    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 0){

            if(permissions.length>0 && grantResults[0] ==  PackageManager.PERMISSION_GRANTED){


                Test2();
            }else {


                Toast.makeText(this,"权限被拒绝",Toast.LENGTH_SHORT).show();
            }



        }


    }

    private void Test2() {


        File file = new File(Environment.getExternalStorageDirectory(),"/wangke");

        if(file.exists()){

            Log.i("wk",file.getPath()+"目录存在");

            long usableSpace = Environment.getExternalStorageDirectory().getUsableSpace();

            long totalSpace = Environment.getExternalStorageDirectory().getTotalSpace();

            String usableSpace_str = Formatter.formatShortFileSize(this, usableSpace);
            String totalSpace_str = Formatter.formatFileSize(this,totalSpace);


            Log.i("wk","外部存储可用空间:"+usableSpace_str+"外部存储的总大小:"+totalSpace_str);


        }else {


            /**
             *
             * mkdir与mkdirs的区别:
             *  mkdir:创建此抽象路径名指定的目录(只能创建一级目录，且需要存在父目录)
             *  mkdirs:创建此抽象路径指定的目录，包括所有必须但不存在的父目录(可以创建多级目录，无论是否存在根目录)
             *
             *
             */
            Log.i("wk","不存在");
            boolean b = file.mkdir();

            if(b) {
                Log.i("wk","创建成功");
            }else {
                Log.i("wk","创建失败");
            }


        }




    }

    private void Test1() {


        //通过Context对象获取私有目录下的一个路径
        String filesDir = this.getFilesDir().getPath();

        Log.i("wk", "当前应用的私有目录:" + filesDir);

        try {
            File file = new File(filesDir, "Test");

            FileOutputStream fos = new FileOutputStream(file);

            OutputStreamWriter writer = new OutputStreamWriter(fos);

            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write("hello My Name is wangke # hi hi Smartsian");

            bufferedWriter.flush();

            /**
             * 将流关闭
             */
            bufferedWriter.close();
            writer.close();
            fos.close();


            /**
             * 读取刚刚写入的流
             */


            FileInputStream is = new FileInputStream(new File(filesDir, "Test"));

            InputStreamReader isReader = new InputStreamReader(is);

            BufferedReader bReader = new BufferedReader(isReader);

            StringBuffer sb = new StringBuffer();
            String line = null;
            while ((line = bReader.readLine())!=null){

                sb.append(line);

            }

            Log.i("wk","从上一次写入的文件中读取的内容:"+sb.toString());

            /**
             * 使用split对字符串进行分割
             */
            String[] split = sb.toString().split("#");

            for(int i=0;i<split.length;i++){

                Log.i("wk","分割的字符串:"+split[i]);

            }


            bReader.close();
            isReader.close();
            is.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

    }


}
