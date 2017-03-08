package com.merpyzf.reviewandroid.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.merpyzf.reviewandroid.R;

public class StudyUiActivity extends AppCompatActivity {

    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_ui);


            player = MediaPlayer.create(this, R.raw.lz);

            player.start();



    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        player.pause();


    }
}
