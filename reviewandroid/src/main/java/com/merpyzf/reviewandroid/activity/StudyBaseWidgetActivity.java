package com.merpyzf.reviewandroid.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.merpyzf.reviewandroid.R;

/**
 * 自动完成文本框的使用
 */
public class StudyBaseWidgetActivity extends AppCompatActivity {

    private AutoCompleteTextView tv_autoComplete;
    private String []names = {"lizhi","zhugeyue","litianxiong"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_base_widget);
        tv_autoComplete = (AutoCompleteTextView) findViewById(R.id.tv_auto_complete);

        //添加适配器
        tv_autoComplete.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,names));


        try {


            //获取软件当前的版本号，以及版本名称
            PackageInfo info = getPackageManager().getPackageInfo(this.getPackageName(), 0);
            int code = info.versionCode;
            String versionName = info.versionName;
            Log.i("wk","当前版本号:"+code+"当前版本名称:"+versionName);


        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


    }
}
