package com.merpyzf.reviewandroid.customui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * 自定义折线图
 */

public class ChartView extends View {

    //绘制坐标系的paint
    private Paint mCsPaint;

    //绘制坐标系上刻度的paint
    private Paint mScalePaint;

    //绘制点的paint
    private Paint mPointPaint;

    //绘制线的paint
    private Paint mLinePaint;

    //绘制文字的paint
    private Paint mTextPaint;

    //X轴上的刻度
    private int SCALE_X = 4;

    //Y轴上的刻度
    private int SCALE_Y = 4;

    //获取传入点的X轴方向上点的最大值
    private int POINT_MAX_X;

    //获取传入点的Y轴方向上点的最大值
    private int POINT_MAX_Y;


    //当前View的高度
    private int VIEW_HEIGHT;

    //当前View的宽度
    private int VIEW_WIDTH;

    //获取当前View的pading值
    private int PADDING;






    //需要绘制的点的坐标集合
    private ArrayList<Point> mPoints;
    private float realScaleX;
    private float realScaleY;


    public ChartView(Context context) {
        super(context);
        //初始化数据
        Init();
    }



    public ChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        Init();
    }

    public ChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init();
    }



    /**
     * 测量
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        /**
         * 获取当前View的宽和高
         */
        VIEW_WIDTH = getMeasuredWidth();
        VIEW_HEIGHT = getMeasuredHeight();

        //获取Padding值
        PADDING = 60;

    }

    //设置数据并且绘制坐标
    public void SetDataAndDraw(int scaleX, int scaleY, ArrayList<Point> points){

        //调用此方法进行重绘
        invalidate();
        SCALE_X = scaleX;
        SCALE_Y = scaleY;
        mPoints = points;

        POINT_MAX_X = getMaxX();

        POINT_MAX_Y = getMaxY();





    }

    /**
     * 获取坐标集合中最大的x坐标
     * @return
     */
    private int getMaxX() {

        int max = mPoints.get(0).x;
        for(int i=0;i<mPoints.size()-1;i++){


            if(max< mPoints.get(i+1).x){

                max = mPoints.get(i+1).x;

            }


        }
        return max;
    }

    /**
     * 获取坐标集合中最大的y坐标
     * @return
     */
    private int getMaxY() {

        int max = mPoints.get(0).y;
        for(int i=0;i<mPoints.size()-1;i++){


            if(max< mPoints.get(i+1).y){

                max = mPoints.get(i+1).y;

            }


        }
        return max;
    }


    /**
     * 绘制折线图
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {

        drawCoordinateSystem(canvas);

        drawLineAndPoint(canvas);



    }

    /**
     * 绘制折线和对应的点
     * @param canvas
     */
    private void drawLineAndPoint(Canvas canvas) {

        float lastX = PADDING;

        float lastY = VIEW_HEIGHT-PADDING;


        for(int i=0;i<mPoints.size();i++){

            float x =  PADDING+(mPoints.get(i).x*1f/SCALE_X)*realScaleX;
            float y =  VIEW_HEIGHT-PADDING-((mPoints.get(i).y*1f/SCALE_Y)*realScaleY);

            Log.i("wk","要绘制的X坐标:"+mPoints.get(i).x+"要绘制的Y:"+mPoints.get(i).y);

            canvas.drawLine(lastX,lastY,x,y,mLinePaint);

            Path path = new Path();

            path.moveTo(lastX,lastY);

            int wt = (int) ((lastX+x)/2);

            path.quadTo(wt,lastY,x,y);

            canvas.drawPath(path,mLinePaint);



            canvas.drawCircle(x,y,6,mPointPaint);

            Rect rect = new Rect();

            mTextPaint.getTextBounds("("+mPoints.get(i).x+","+mPoints.get(i).y+")",0,1,rect);




            canvas.drawText("("+mPoints.get(i).x+","+mPoints.get(i).y+")",x-(rect.width()*6),y,mTextPaint);

            lastX = x;
            lastY = y;


        }


    }

    /**
     * 绘制坐标系
     * @param canvas
     */
    private void drawCoordinateSystem(Canvas canvas) {

        //根据用户设置的x方向上的刻度间隔，及当前方向上最大点的坐标和当前View的宽度计算绘制时候的两个点之间的距离

        int xNum= (POINT_MAX_X/SCALE_X)+2;
        int yNum= (POINT_MAX_Y/SCALE_Y)+2;

        //对应屏幕上真实的刻度间距
        realScaleX = (float) (((VIEW_WIDTH-PADDING*2)*1.0)/(xNum*1.0));

        realScaleY = (float) (((VIEW_HEIGHT-PADDING*2)*1.0)/(yNum*1.0));


        Log.i("wk","View宽度:"+VIEW_WIDTH+"x轴上的间距:"+ realScaleX +"X轴上绘制的个数："+POINT_MAX_X/SCALE_X);

        Log.i("wk","View高度"+VIEW_HEIGHT+"y轴上的间距:"+ realScaleY +"Y轴上绘制的个数："+POINT_MAX_Y/SCALE_Y);

        canvas.drawLine(PADDING,VIEW_HEIGHT-PADDING,getWidth()-PADDING,VIEW_HEIGHT-PADDING,mCsPaint);
        canvas.drawLine(PADDING,VIEW_HEIGHT-PADDING,PADDING,PADDING,mCsPaint);
        canvas.drawCircle(PADDING,VIEW_HEIGHT-PADDING,6,mPointPaint);

        /**
         * 绘制X轴坐标以及X轴上面的刻度
         */
        for(int i=0;i<xNum;i++){


            canvas.drawLine(PADDING+(i* realScaleX),VIEW_HEIGHT-PADDING,PADDING+(i* realScaleX),VIEW_HEIGHT-PADDING-10,mScalePaint);

            canvas.drawText(SCALE_X*i+"",PADDING+(i* realScaleX),VIEW_HEIGHT,mTextPaint);

        }

        /**
         * 绘制Y轴坐标以及Y轴上面的刻度
         */
        for (int j=0;j<yNum;j++){

            canvas.drawLine(PADDING,VIEW_HEIGHT-PADDING-(j* realScaleY),PADDING+10,VIEW_HEIGHT-PADDING-(j* realScaleY),mScalePaint);

            if(j!=0) {
                canvas.drawText(SCALE_X * j + "", 25, VIEW_HEIGHT - PADDING - (j * realScaleY), mTextPaint);
            }

        }


    }

    /**
     * 画笔的相关初始化
     */
    private void Init() {

        //绘制坐标系的Paint
        mCsPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCsPaint.setStyle(Paint.Style.FILL);
        mCsPaint.setStrokeWidth(4);
        mCsPaint.setColor(Color.BLACK);

        //绘制坐标系上刻度的paint
        mScalePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mScalePaint.setStyle(Paint.Style.FILL);
        mCsPaint.setStrokeWidth(6);
        mScalePaint.setColor(Color.BLACK);


        //用于绘制点的Paint
        mPointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPointPaint.setStyle(Paint.Style.FILL);
        mCsPaint.setStrokeWidth(4);
        mPointPaint.setColor(Color.BLUE);

        //用于绘制线的Paint
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mCsPaint.setStrokeWidth(4);
        mLinePaint.setColor(Color.BLACK);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(30);


    }





}
