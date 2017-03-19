package com.merpyzf.reviewandroid.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.merpyzf.reviewandroid.R;

import java.util.ArrayList;

public class ItemSlidingListViewActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> BeanList;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_sliding_list_view);
        mContext = this;
        InitView();
        InitData();

        listView.setAdapter(new MyListViewAdapter());

    }

    private void InitData() {
        BeanList = new ArrayList<String>();

        for(int i=0;i<20;i++){


            BeanList.add("测试的数据: "+0);


        }



    }

    private void InitView() {

        listView = (ListView) findViewById(R.id.listView);

    }


    class MyListViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return BeanList.size();
        }

        @Override
        public Object getItem(int position) {

            return BeanList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = View.inflate(mContext,R.layout.listview_item_sliding,null);


            return view;
        }
    }
}
