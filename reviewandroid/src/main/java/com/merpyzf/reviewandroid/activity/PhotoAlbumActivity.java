package com.merpyzf.reviewandroid.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.merpyzf.reviewandroid.R;

import java.io.IOException;

/**
 * 从相册中选择一张图片
 * <p>
 * 注:只能从图库中选择
 */
public class PhotoAlbumActivity extends AppCompatActivity {


    private static final int REQUEST_CODE = 1;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_album);
        imageView = (ImageView) findViewById(R.id.imageView_p);
    }


    public void clickPhoto(View view) {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_CODE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {

            Bitmap bitmap = null;

            if (requestCode == REQUEST_CODE) {
                try {
                    ContentResolver resolver = getContentResolver();

                    Uri uri = data.getData();


                    bitmap = MediaStore.Images.Media.getBitmap(resolver, uri);

                    //将从图库中选择的图片显示在imageview
                    imageView.setImageBitmap(bitmap);

                    String[] proj = {MediaStore.Images.Media.DATA};

                    //安卓多媒体封装接口
                    Cursor cursor = managedQuery(uri, proj, null, null, null);

                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                    cursor.moveToFirst();

                    //最后根据索引获取图片路径
                    String path = cursor.getString(column_index);


                    Toast.makeText(PhotoAlbumActivity.this, "所选图片的路径:" + path, Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                }


            }


        }


    }


}
