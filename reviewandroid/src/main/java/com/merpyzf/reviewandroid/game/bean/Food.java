package com.merpyzf.reviewandroid.game.bean;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.Random;

/**
 * Created by Administrator on 2017/3/3.
 */

public class Food {

    //食物的大小
    private int size;

    //食物当前的状态
    private boolean isEat;

    //设置食物生成的边界
    private int withBound;
    private int heightBound;


    //小蛇的头部当前位置对应的point
    private Point snakeHeadPoint;


    private Point foodPoint;
    private Paint mPaint;

    public Food(int size, boolean isEat, int withBound, int heightBound) {

        this.size = size;
        this.isEat = isEat;

        this.withBound = withBound - size;
        this.heightBound = heightBound - size;

        //初始化绘制食物的画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);

    }


    /**
     * @param point
     */
    public void setSnakeHeadPoint(Point point) {

        this.snakeHeadPoint = point;


    }


    //生成食物
    public void makeFood(Canvas canvas) {


//        Log.i("wk", "是否被吃掉：" + isEat);
        if (isEat == true) {

            //当食物被吃掉的时候，才开始进行绘制食物


            foodPoint = new Point();

            int w = (withBound - snakeHeadPoint.x - size) / size;
            int h = (heightBound - snakeHeadPoint.y - size) / size;

            //随机生成食物的坐标位置
            Random random = new Random();

            if (w == 0 && h == 0) {
                w += 2;
                h += 2;

            }

            foodPoint.x = random.nextInt(w) * size;
            foodPoint.y = random.nextInt(h) * size;

            //确保只生成一次
            isEat = false;


        }

        canvas.drawRect(foodPoint.x, foodPoint.y, foodPoint.x + size, foodPoint.y + size, mPaint);


    }


    /**
     * 设置当前食物的状态，是否被吃掉
     *
     * @param isEat
     */
    public void isEat(boolean isEat) {

        this.isEat = isEat;

    }


    /**
     * 返回食物所在点的位置
     */
    public Point getFoodPoint() {

        if (foodPoint != null) {

            return foodPoint;
        } else {


            return null;
        }
    }

}
