package com.merpyzf.reviewandroid.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.merpyzf.reviewandroid.R;

/**
 * 逐帧动画的使用
 */
public class FrameByFrameActivity extends AppCompatActivity {

    private ImageView imageView_bird;
    private Button btn_play;
    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_by_frame);

        imageView_bird = (ImageView) findViewById(R.id.imageView);
        btn_play = (Button)findViewById(R.id.btn_play);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        imageView_bird.setImageResource(R.drawable.bird_fly_animation);

        final AnimationDrawable animationDrawable  = (AnimationDrawable) imageView_bird.getDrawable();

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(animationDrawable.isRunning()){


                    animationDrawable.stop();
                    btn_play.setText("开始动画");

                }else {

                    animationDrawable.start();
                    btn_play.setText("停止动画");
                }


            }
        });

    }


}
