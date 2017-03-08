package com.merpyzf.reviewandroid.game;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import com.merpyzf.reviewandroid.R;
import com.merpyzf.reviewandroid.game.bean.Food;
import com.merpyzf.reviewandroid.game.bean.Snake;
import com.merpyzf.reviewandroid.game.view.SnakeView;

import static com.merpyzf.reviewandroid.R.id.snakeView;

public class SnakeActivity extends AppCompatActivity {

    private SnakeView mSnakeView;
    private Snake snake;
    private Food food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake);

        mSnakeView = (SnakeView) findViewById(snakeView);




        mSnakeView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                //游戏View的宽高
                int gameWidth = mSnakeView.getWidth();
                int gameHeight = mSnakeView.getHeight()-10;

                Log.i("wk","SnakeView-width:"+gameWidth+"SnakeView-height:"+gameHeight);

                //移除监听
                mSnakeView.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                snake = new Snake(SnakeActivity.this);


                /**
                 *
                 * 小蛇身体的添加要按照 要添加在snakeList的第一个位置,计算小蛇的point 的位置
                 *
                 */

                food = new Food(30,true,gameWidth,gameHeight);

                snake.setFood(food);

                snake.InitSnake(30,30*6,30*6);

                snake.addSnake(new Point(30*5,30*6));

                snake.addSnake(new Point(30*4,30*6));

                snake.addSnake(new Point(30*3,30*6));

                snake.addSnake(new Point(30*2,30*6));

                snake.addSnake(new Point(30,30*6));




                //移动
                mSnakeView.setSnake(snake);




            }
        });














    }


    public void clickUp(View v){

        snake.setDirection(0);



    }



    public void clickDown(View v){

        snake.setDirection(1);

    }



    public void clickLeft(View v){

        snake.setDirection(2);


    }


    public void clickRight(View v){

        snake.setDirection(3);


    }
}
