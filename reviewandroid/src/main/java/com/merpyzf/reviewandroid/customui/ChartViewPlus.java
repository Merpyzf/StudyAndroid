package com.merpyzf.reviewandroid.customui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.merpyzf.reviewandroid.R;

import java.util.ArrayList;

/**
 * 折线图,曲线图
 *
 * @author 春水碧于天
 */

public class ChartViewPlus extends View {

    //X轴的数据
    private ArrayList<String> XRawData;

    //Y轴的坐标值
    private ArrayList<Double> YRawData;

    //Y轴的刻度
    private double YRawScale = 1;

    //Y轴上最大的刻度
    private double YMaxScale;

    //Y轴的刻度对应在屏幕上的像素大小
    private double YScreenScale;

    //X轴上刻度的个数
    private int XRawNum;

    //小圆点的大小
    private int PointSize = dip2px(getContext(), 4);

    //X轴的刻度对应在屏幕上的像素大小
    private double XScreenScale;
    private double yScaleNum;


    //是否绘制坐标值
    private Boolean isDrawCoordValue = true;

    private ChartType mChartType = ChartType.GraphicChart;

    private ArrayList<MyPoint> mPoints = new ArrayList<MyPoint>();


    //枚举定义表格类型
    private static enum ChartType {

        LineChart, GraphicChart

    }


    /**
     * 画笔的声明
     */
    //点画笔
    private Paint mPointPaint;
    //折线画笔
    private Paint mLinePaint;
    //文字画笔
    private Paint mTextPaint;

    //坐标系画笔
    private Paint mSysPaint;


    private Rect rect = new Rect();


    //折线图的Padding值(上下左右)
    private int mPadding;


    //view的高度(px)
    private Double mChartViewHeight;

    //view的宽度(px)
    private Double mChartViewWidth;


    private Context mContext;


    public ChartViewPlus(Context context) {
        this(context, null);
    }

    public ChartViewPlus(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChartViewPlus(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mPadding = dip2px(context, 40);

        InitPaint();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Log.i("wk", "当前控件的宽度:" + w);
        Log.i("wk", "当前控件的高度:" + h);


        mChartViewHeight = h * 1.0d;
        mChartViewWidth = w * 1.0d;


        YMaxScale = getYMaxScale();

        //根据最大刻度数和刻度间距计算出y轴上刻度的个数
        yScaleNum = Math.round(YMaxScale / YRawScale);
        Log.i("wk", "Y轴上的刻度数:" + yScaleNum);

        //根据屏幕的高度计算出
        YScreenScale = (mChartViewHeight - (2 * mPadding)) / yScaleNum;

        Log.i("wk", "Y轴在屏幕上的刻度间距:" + YScreenScale);

        //+1是为了美观
        XRawNum = XRawData.size() + 1;

        Log.i("wk", "x轴上刻度的个数:" + XRawNum);

        Log.i("wk", "Padding==>" + mPadding);

        XScreenScale = (mChartViewWidth - (2 * mPadding)) / XRawNum;

        Log.i("wk", "x轴在屏幕上的刻度间距:" + XScreenScale);

        for (int i = 0; i < YRawData.size(); i++) {

            float y = (float) (mChartViewHeight - mPadding - ((float) ((YRawData.get(i) * 1f / YRawScale) * YScreenScale)));

            Log.i("wk", "反映在屏幕上的y点坐标=====>" + y);

            MyPoint point = new MyPoint();

            point.x = (float) (i * XScreenScale) + mPadding;
            point.y = y;

            mPoints.add(point);

        }


    }

    @Override
    protected void onDraw(Canvas canvas) {

        drawAllYLine(canvas);
        drawAllXline(canvas);

        //绘制折线
        if (mChartType == ChartType.LineChart) {

            drawLineChart(canvas);

            //绘制曲线
        } else if (mChartType == ChartType.GraphicChart) {


            drawGraphicChart(canvas);


        }


    }

    private void drawGraphicChart(Canvas canvas) {

        MyPoint startp = new MyPoint();
        MyPoint endp = new MyPoint();
        for (int i = 0; i < mPoints.size() - 1; i++) {
            startp = mPoints.get(i);
            endp = mPoints.get(i + 1);
            float flag = (startp.x + endp.x) / 2;
            MyPoint p3 = new MyPoint();
            MyPoint p4 = new MyPoint();
            p3.y = startp.y;
            p3.x = flag;
            p4.y = endp.y;
            p4.x = flag;

            Path path = new Path();
            path.moveTo(startp.x, startp.y);

            //三阶贝塞尔曲线
            path.cubicTo(p3.x, p3.y, p4.x, p4.y, endp.x, endp.y);
            canvas.drawPath(path, mLinePaint);

            //绘制圆点

            canvas.drawCircle(startp.x, startp.y, PointSize, mPointPaint);

            if(isDrawCoordValue) {


                //绘制坐标值
                mTextPaint.getTextBounds(YRawData.get(i) + "", 0, 1, rect);
                mTextPaint.setTextAlign(Paint.Align.CENTER);
                canvas.drawText(YRawData.get(i) + "", startp.x, startp.y - rect.height(), mTextPaint);

            }

            if (i == mPoints.size() - 2) {

                canvas.drawCircle(mPoints.get(i + 1).x, mPoints.get(i + 1).y, PointSize, mPointPaint);

                if (isDrawCoordValue) {
                    canvas.drawText(YRawData.get(i + 1) + "", mPoints.get(i + 1).x, mPoints.get(i + 1).y - rect.height(), mTextPaint);

                }
            }

        }
    }

    private void drawLineChart(Canvas canvas) {


        MyPoint startp = new MyPoint();
        MyPoint endp = new MyPoint();
        for (int i = 0; i < mPoints.size() - 1; i++) {
            startp = mPoints.get(i);
            endp = mPoints.get(i + 1);

            Log.i("wk", "绘制折线了" + startp.x + "  " + startp.y);


            canvas.drawLine(startp.x, startp.y, endp.x, endp.y, mLinePaint);

            //绘制圆点

            canvas.drawCircle(startp.x, startp.y, PointSize, mPointPaint);


            if (isDrawCoordValue) {

                //绘制坐标值
                mTextPaint.getTextBounds(YRawData.get(i) + "", 0, 1, rect);
                mTextPaint.setTextAlign(Paint.Align.CENTER);
                canvas.drawText(YRawData.get(i) + "", startp.x, startp.y - rect.height(), mTextPaint);

            }
            if (i == mPoints.size() - 2) {

                canvas.drawCircle(mPoints.get(i + 1).x, mPoints.get(i + 1).y, PointSize, mPointPaint);
                if (isDrawCoordValue) {
                    canvas.drawText(YRawData.get(i + 1) + "", mPoints.get(i + 1).x, mPoints.get(i + 1).y - rect.height(), mTextPaint);

                }
            }
        }


    }


    /**
     * 初始化画笔
     */
    private void InitPaint() {


        mSysPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSysPaint.setStyle(Paint.Style.STROKE);
        mSysPaint.setColor(Color.GRAY);
        mSysPaint.setStrokeWidth(dip2px(getContext(), 1));

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(getResources().getColor(R.color.colorBabyBlue));
        mLinePaint.setStrokeWidth(dip2px(getContext(), 1));
        mLinePaint.setStyle(Paint.Style.STROKE);

        mPointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPointPaint.setColor(getResources().getColor(R.color.colorBabyBlue));
        mPointPaint.setStyle(Paint.Style.FILL);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.GRAY);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(dip2px(getContext(), 13));

    }


    /**
     * 绘制所有的x方向的线条，包括x轴
     */
    private void drawAllXline(Canvas canvas) {

        mTextPaint.setTextAlign(Paint.Align.RIGHT);

        //+1包括绘制坐标系的线
        for (int i = 0; i < yScaleNum + 1; i++) {

            float y = (float) (mChartViewHeight - mPadding - (i * YScreenScale));

            if (i == 0) {

                canvas.drawLine(mPadding, y, (float) (mChartViewWidth - mPadding / 2 - XScreenScale), y, mSysPaint);


                mTextPaint.getTextBounds(0+"", 0, 1, rect);

                canvas.drawText(0+"", mPadding - rect.width(), y, mTextPaint);


            } else {


                canvas.drawLine(mPadding, y, (float) (mChartViewWidth - mPadding - XScreenScale), y, mSysPaint);

                String yScale = String.valueOf(i * YRawScale);

                mTextPaint.getTextBounds(yScale, 0, 1, rect);

                int textWidth = rect.height(); //为了美观，无奈之举/(ㄒoㄒ)/~~

                canvas.drawText(yScale, mPadding - textWidth, y, mTextPaint);

            }


        }


    }


    /**
     * 绘制所有Y方向的线条，包括y轴
     */


    private void drawAllYLine(Canvas canvas) {

        mTextPaint.setTextAlign(Paint.Align.CENTER);


        for (int i = 0; i < XRawNum; i++) {

            float x = (float) (mPadding + (i * XScreenScale));


            if (i == 0) {

                canvas.drawLine(x, mPadding / 2, x, (float) (mChartViewHeight - mPadding), mSysPaint);

                //测量文字的高度
//                mTextPaint.getTextBounds("0", 0, 1, rect);

                //测量文字的高度
                mTextPaint.getTextBounds(XRawData.get(i), 0, 1, rect);

                int textHeight = rect.height() + dip2px(getContext(), 2);


                canvas.drawText(XRawData.get(i), x, (float) (mChartViewHeight - mPadding + textHeight), mTextPaint);

            } else {

                if (i < XRawNum - 1) {

                    //测量文字的高度
                    mTextPaint.getTextBounds(XRawData.get(i), 0, 1, rect);

                    int textHeight = rect.height() + dip2px(getContext(), 2);

                    canvas.drawText(XRawData.get(i), x, (float) (mChartViewHeight - mPadding + textHeight), mTextPaint);

                }

                canvas.drawLine(x, mPadding, x, (float) (mChartViewHeight - mPadding), mSysPaint);


            }
        }


    }


    /**
     * 获取Y轴上的最大刻度值
     *
     * @return
     */
    private double getYMaxScale() {


        double max = YRawData.get(0);
        for (int i = 1; i < YRawData.size(); i++) {

            if (max < YRawData.get(i)) {

                max = YRawData.get(i);

            }
        }


        Log.i("wk", "传入点的Y轴上的最大刻度值:" + max);

        return max;

    }

    public static int dip2px(Context context, float dpValue) {

        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);

    }


    /*  private ArrayList<String> XRawData;

      //Y轴的坐标值
      private ArrayList<Double> YRawData;*/
    public void setData(ArrayList<String> XRawData, ArrayList<Double> YRawData) {

        this.XRawData = XRawData;
        this.YRawData = YRawData;

    }

    class MyPoint {

        float x;
        float y;

    }


}
