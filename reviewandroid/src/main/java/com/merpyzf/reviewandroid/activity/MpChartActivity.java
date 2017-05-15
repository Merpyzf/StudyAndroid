package com.merpyzf.reviewandroid.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.merpyzf.reviewandroid.R;

import java.util.ArrayList;
import java.util.Random;

public class MpChartActivity extends AppCompatActivity {

    private LineChart mLineChart;
    private LineDataSet set1;

     ArrayList<String> XDatas = new ArrayList<String>();
    private ArrayList<Entry> values;
    private XAxis xAxis;
    private LineData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp_chart);


        XDatas.add("test1");
        XDatas.add("test2");
        XDatas.add("test3");
        XDatas.add("test4");
        XDatas.add("test5");
        XDatas.add("test6");




        mLineChart = (LineChart) findViewById(R.id.linChart);

        //后台绘制背景
        mLineChart.setDrawGridBackground(true);

        //设置描述文本
        mLineChart.getDescription().setEnabled(false);
        //设置支持触控
        mLineChart.setTouchEnabled(true);
        //设置拖动
        mLineChart.setDragEnabled(true);
        //设置缩放
        mLineChart.setScaleEnabled(true);
        //如果禁用,扩展可以在x轴和y轴分别完成
        mLineChart.setPinchZoom(true);



        //自定义显示x轴

        MyXFormatter myXFormatter = new MyXFormatter(XDatas);



        //获取x轴的坐标轴
        xAxis = mLineChart.getXAxis();
        //设置x轴的最大值
        xAxis.setAxisMaximum(6);
        //设置x轴的最小值
        xAxis.setAxisMinimum(0);

        xAxis.setLabelCount(6);

        xAxis.setValueFormatter(myXFormatter);




        //设置X坐标轴的显示位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        YAxis yAxis = mLineChart.getAxisLeft();

        //设置y轴的最大坐标
        yAxis.setAxisMaximum(200f);
        //设置y轴的最小坐标
        yAxis.setAxisMinimum(0f);
        yAxis.setDrawZeroLine(false);
        //设置图标右边的Y轴不显示坐标轴
        mLineChart.getAxisRight().setEnabled(false);


        //构造点数据
        values = new ArrayList<Entry>();

        values.add(new Entry(0, 50));
        values.add(new Entry(1, 66));
        values.add(new Entry(2, 120));
        values.add(new Entry(3, 30));
        values.add(new Entry(4, 10));
        values.add(new Entry(5, 110));
        values.add(new Entry(6, 30));



//        xAxis.setLabelCount(xAxis.getLabelCount()+1);



        setData(values);

        //设置动画
//        mLineChart.animateX(2500);



        XDatas.add("final");

    }

    //设置数据
    private void setData(ArrayList<Entry> values) {


        //LineData 表示折线图上整个y轴的数据
        if(mLineChart.getData()!=null && mLineChart.getData().getDataSetCount()>0){
            //默认获取图标上的第一条折线
            set1 = (LineDataSet) mLineChart.getData().getDataSetByIndex(0);
            //将点设置给LineDataSet
            set1.setValues(values);

            mLineChart.getData().notifyDataChanged();
            mLineChart.notifyDataSetChanged();


        }else {
            //创建一个数据集，并设置类型
            set1 = new LineDataSet(values,"年度总结报告");
            //设置线条的颜色
            set1.setColor(Color.BLUE);
            //设置坐标点的颜色
            set1.setCircleColor(Color.RED);
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            //设置是否填充背景
            set1.setDrawFilled(true);


            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();

            //可以添加多条折线
            dataSets.add(set1);

            data = new LineData(dataSets);
            //设置数据
            mLineChart.setData(data);

            data.notifyDataChanged();

        }

    }

    public void clickAddPoint(View v){


        Random random = new Random();
        int i = random.nextInt(50);


        values.add(new Entry(xAxis.getLabelCount()+1,i));

        xAxis.setAxisMaximum(xAxis.getLabelCount());


        xAxis.setLabelCount(xAxis.getLabelCount());
        mLineChart.setVisibleXRangeMaximum(7);


        XDatas.add(i+"");

        data.notifyDataChanged();

        mLineChart.moveViewToX(xAxis.getLabelCount());


    }






    class MyXFormatter implements IAxisValueFormatter{

        private ArrayList<String> mValues;


        public MyXFormatter(ArrayList<String> mValues) {
            this.mValues = mValues;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {


            Log.i("wk","value==>"+value);


            return mValues.get((int) (value%mValues.size()));
        }
    }




}
