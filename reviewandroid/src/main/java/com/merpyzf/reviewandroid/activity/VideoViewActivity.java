package com.merpyzf.reviewandroid.activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.merpyzf.reviewandroid.R;

public class VideoViewActivity extends AppCompatActivity {

    private VideoView mVideoView;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        mediaController = new MediaController(this);
        mVideoView = (VideoView) findViewById(R.id.videoView);

        //播放Raw目录下的视频文件
        mVideoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/raw/video"));

//        mVideoView.setVideoPath();


        //给VideoView绑定MediaController
        mVideoView.setMediaController(mediaController);
        //开始播放视频
        mVideoView.start();
    }
}
