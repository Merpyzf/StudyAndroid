package com.merpyzf.reviewandroid.game.bean;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Vibrator;
import android.util.Log;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Snake {

    private ArrayList<Point> snakeList;

    private int size;

    private Point point;

    private Paint mPaint;

    private int direction = 3;

    private Food mFood;

    private Context mContext;





    //记录小蛇身体的第一个点
    private Point LastPoint = new Point();
    private final Vibrator mVibrator;


    public Snake(Context context) {

        point = new Point();

        this.mContext = context;
        //初始化画笔,设置抗锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);

        //初始化振动器
        mVibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);



    }


    //设置snake移动的方向
    public void setDirection(int direction) {

        this.direction = direction;
    }

    //添加一个小蛇
    public void addSnake(Point point) {

        snakeList.add(point);

    }


    //初始化小蛇的位置,以及小蛇身体的大小
    public void InitSnake(final int size, int x, int y) {

        snakeList = new ArrayList<Point>();
        point.x = x;
        point.y = y;
        this.size = size;
        snakeList.add(point);


        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {


                switch (direction) {

                    case 0:
                        //向上移动
                        actionUp();

                        break;


                    case 1:

                        //向下移动
                        actionDown();


                        break;

                    case 2:

                        //向左移动
                        actionLeft();

                        break;


                    case 3:

                        //向右移动
                        actionRight();
                        break;

                }


            }

        }, 0, 1000);

    }

    /**
     * 贪吃蛇移动方向的判断限制
     */

    private void actionUp() {


        if (snakeList.get(0).y - size == LastPoint.y && snakeList.get(0).x == snakeList.get(0).x) {

            actionDown();

        } else {


            for (int i = snakeList.size() - 1; i > 0; i--) {

                snakeList.get(i).x = snakeList.get(i - 1).x;

                snakeList.get(i).y = snakeList.get(i - 1).y;

            }

            snakeList.get(0).y -= size;

            recordSnakeBodyFirstPoint();

            eatFood();

        }

    }


    private void actionDown() {


        if (snakeList.get(0).y + size == LastPoint.y && snakeList.get(0).x == snakeList.get(0).x) {


            actionUp();

        } else {


            for (int i = snakeList.size() - 1; i > 0; i--) {


                snakeList.get(i).x = snakeList.get(i - 1).x;

                snakeList.get(i).y = snakeList.get(i - 1).y;
            }


            snakeList.get(0).y += size;

            recordSnakeBodyFirstPoint();

            eatFood();

        }
    }
    /**
     * snake向左边移动
     */
    private void actionLeft() {

        //判断小蛇移动的方向不能回头,判断snake下一个要移动的点的位置，和蛇身的第一个位置点进行比较，如果相等就说明小蛇回头了
        if (snakeList.get(0).x - size == LastPoint.x && snakeList.get(0).y == snakeList.get(0).y) {

            Log.i("wk", "不能向这个方向移动！！！");

            actionRight();

        } else {


            for (int i = snakeList.size() - 1; i > 0; i--) {

                snakeList.get(i).x = snakeList.get(i - 1).x;

                snakeList.get(i).y = snakeList.get(i - 1).y;

            }

            snakeList.get(0).x -= size;


            recordSnakeBodyFirstPoint();

            eatFood();

        }


    }


    /**
     * snake向右移动
     */

    private void actionRight() {

        //判断小蛇移动的方向不能回头
        if (snakeList.get(0).x + size == LastPoint.x && snakeList.get(0).y == snakeList.get(0).y) {


            actionLeft();


        } else {


            for (int i = snakeList.size() - 1; i > 0; i--) {


                snakeList.get(i).x = snakeList.get(i - 1).x;

                snakeList.get(i).y = snakeList.get(i - 1).y;


            }

            snakeList.get(0).x += size;


            eatFood();

            recordSnakeBodyFirstPoint();



        }
    }


    /**
     * 记录上一次移动的时候,小蛇蛇头后面的第一个点的位置，用户判断小蛇移动的过程中是否回头了
     */
    public void recordSnakeBodyFirstPoint() {

        LastPoint.x = snakeList.get(1).x;
        LastPoint.y = snakeList.get(1).y;

    }

    /**
     * 绘制小蛇
     *
     * @param canvas
     */
    public void drawSnake(Canvas canvas) {

        //将小蛇的头对应的点设置给food对象,用来随机初始化食物的位置
        mFood.setSnakeHeadPoint(snakeList.get(0));
        //绘制食物
        mFood.makeFood(canvas);


        for (int i = 0; i < snakeList.size(); i++) {

            canvas.drawRect(snakeList.get(i).x, snakeList.get(i).y, snakeList.get(i).x + size, snakeList.get(i).y + size, mPaint);


        }


    }


    public void setFood(Food food) {

        mFood = food;


    }


    /**
     * 小蛇吃食物的方法
     */
    public void eatFood() {

        Point foodPoint = mFood.getFoodPoint();

        if(foodPoint!=null) {

            Log.i("wk", "食物所在的位置=>x:" + foodPoint.x + "\n" + "食物所在的位置=>y:" + foodPoint.y);

            //此时表明被吃掉了
            if(foodPoint.x == snakeList.get(0).x && foodPoint.y == snakeList.get(0).y){


                Log.i("wk","食物.x: "+foodPoint.x+" 食物.y: "+foodPoint.y+"头.x: "+snakeList.get(0).x+"头.y: "+snakeList.get(0).y);

                mFood.isEat(true);

                Log.i("wk","被吃掉了 哈哈哈哈");

                snakeList.add(0,mFood.getFoodPoint());

                //设置震动
                mVibrator.vibrate(500);


            }

        }


    }


}
