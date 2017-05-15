package com.merpyzf.reviewandroid.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.merpyzf.reviewandroid.R;

import java.util.ArrayList;
import java.util.List;

public class MpChart04Activity extends Activity {

    private LineChart mLineChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp_chart02);

        //1.从xml中获取linechart的引用
        mLineChart = (LineChart) findViewById(R.id.linechart);

        XAxis xAxis = mLineChart.getXAxis();

        xAxis.setEnabled(true);
        //设置是否绘制刻度值
        xAxis.setDrawLabels(true);

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis axisRight = mLineChart.getAxisRight();
        axisRight.setEnabled(false);
        YAxis yAxis = mLineChart.getAxisLeft();

        yAxis.setLabelCount(3);

        //设置刻度值绘制的位置，图表内部或外部
//        yAxis.setPosition(YAxis.YAxisLabelPosition.UTSIDE_CHART);

        yAxis.setTextSize(20); //设置标签的字体大小
        yAxis.setTextColor(Color.RED); //设置标签文字的颜色

        yAxis.setAxisLineColor(Color.RED);

        yAxis.setGridColor(Color.YELLOW);
        yAxis.setGridLineWidth(6);


//        yAxis.setInverted(true); //设置反转

//        yAxis.setSpaceTop(1f);


//        xAxis.setValueFormatter(new AxisValueFormatt());



        //2.创建一个List集合，用来存放一条折线上的所有点
        List<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(0f,20));
        entries.add(new Entry(1f,30));
        entries.add(new Entry(2f,15));
        entries.add(new Entry(3f,50));


        xAxis.setLabelCount(entries.size());
        xAxis.setAxisMinimum(0f);



        //3.将entries设置给LineDataSet数据集
        LineDataSet dataSet = new LineDataSet(entries, "Label");


        //4.将上面创建的LineDataSet对象设置给LineData
        LineData lineData = new LineData(dataSet);

        //5.把lineData设置给lineChart就可以显示出来折线图了，就像把adapter设置给listview一样
        mLineChart.setData(lineData);

    }

    class AxisValueFormatt implements IAxisValueFormatter{


        @Override
        public String getFormattedValue(float value, AxisBase axis) {


            Log.i("wk","value==>"+value);

            return "haha";
        }


    }
}
