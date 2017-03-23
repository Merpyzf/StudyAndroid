package com.merpyzf.reviewandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.merpyzf.reviewandroid.R;

import java.util.ArrayList;

public class SlidingConflictActivity extends AppCompatActivity {

    private ListView test_listview;
    private ArrayList<String> strList;
    private int mLastDownX;
    private int mLastDownY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_conflict);

        test_listview = (ListView) findViewById(R.id.listView_test);
        strList = new ArrayList<String>();

        for(int i=0;i<60;i++){

            strList.add("这是测试的数据："+i);

        }

        test_listview.setAdapter(new myAdapter());










    }


    class myAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return strList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TextView view = new TextView(SlidingConflictActivity.this);

            view.setText(strList.get(position).toString());

            return view;
        }
    }

}
