package com.merpyzf.reviewandroid.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.merpyzf.reviewandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * HightLight相关，以及坐标点点击后的回调用
 *
 */
public class MpChart03Activity extends AppCompatActivity {

    private LineChart mLineChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp_chart03);


        /**
         * 会自动根据传入的点坐标计算出x,y轴的最大坐标值以及间隔
         * 默认是可以进行手指的缩放的
         * 默认x轴显示在上方
         * 默认图表的右侧也显示y轴的坐标
         */


        //1.从xml中获取linechart的引用
        mLineChart = (LineChart) findViewById(R.id.linechart);

        //2.创建一个List集合，用来存放一条折线上的所有点
        List<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(10,20));
        entries.add(new Entry(20,30));
        entries.add(new Entry(30,15));
        entries.add(new Entry(40,50));

        //3.将entries设置给LineDataSet数据集
        LineDataSet dataSet = new LineDataSet(entries, "Label");


        //指定当前的折线是否高亮显示
        dataSet.setHighlightEnabled(true);
        //指定高亮显示的颜色
        dataSet.setHighLightColor(Color.RED);

        dataSet.setDrawHighlightIndicators(true);




        //4.将上面创建的LineDataSet对象设置给LineData
        LineData lineData = new LineData(dataSet);

        //5.把lineData设置给lineChart就可以显示出来折线图了，就像把adapter设置给listview一样
        mLineChart.setData(lineData);

        //手势相关，不完整

        mLineChart.setTouchEnabled(true); //设置成false将拦截掉所有的触摸事件(其它的触摸事件尽管设置了也不会生效)
        mLineChart.setDragEnabled(true); //设置图表放大之后是否可拖动
        mLineChart.setScaleEnabled(false); //设置是否可以进行缩放
        mLineChart.setScaleXEnabled(true); //设置X方向上是否可缩放
        mLineChart.setScaleYEnabled(true); //设置Y方向上是否可缩放
        mLineChart.setPinchZoom(true); //设置双指缩放(类似放大图片的效果)



        mLineChart.setHighlightPerDragEnabled(true); //设置高亮线可以被拖动（数据点与坐标的提示线）
        mLineChart.setHighlightPerTapEnabled(true); //设置是否支持轻击显示高亮线


        Highlight highlight = new Highlight(20, 30, 0);
        mLineChart.highlightValue(highlight,false); //也可以传入一个hightlight对象进去，第二个参数是否回调用
//        mLineChart.highlightValues(higs); //设置一组高亮显示的点，需要传递一个Highlight数组

//        mLineChart.highlightValue(20,30,0); //设置默认高亮显示的点（1:x 2:y 3:对应的折线的下标）

//        Highlight[] highlighted = mLineChart.getHighlighted(); //返回所有高亮显示的点

        //设置点的监听
        mLineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                Log.i("wk","x==>"+e.getX()+" y==>"+e.getY());
            }

            @Override
            public void onNothingSelected() {

            }
        });


    }

}
