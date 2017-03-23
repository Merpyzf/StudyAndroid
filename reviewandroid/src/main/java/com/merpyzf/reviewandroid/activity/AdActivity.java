package com.merpyzf.reviewandroid.activity;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.merpyzf.reviewandroid.R;


/**
 * ActionBar+DrawerLayout的使用
 */
public class AdActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);



        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        InitActionBar();


    }

    private void InitActionBar() {




        ActionBar actionBar = getSupportActionBar();


        //设置ActionBar的标题
        actionBar.setTitle("哈哈哈哈");

        //设置ActionBar上的按钮是否可点击
        actionBar.setHomeButtonEnabled(true);
        //设置ActionBar栏左侧的Logo是否隐藏
        actionBar.setDisplayShowHomeEnabled(false);
        //设置Logo
        actionBar.setIcon(R.mipmap.ic_launcher);
        //设置显示返回键,当和侧边栏结合时用作显示
        actionBar.setDisplayHomeAsUpEnabled(true);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        toggle = new ActionBarDrawerToggle(this, drawer,R.drawable.ico_menu, R.string.open, R.string.close);

//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,null,R.drawable.ico_menu,R.string.open,R.string.close);


        //同步当前状态,将DrawerLayout与抽屉关联在一起
        toggle.syncState();


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:

                //切换抽屉
                toggle.onOptionsItemSelected(item);


                break;


        }






        return super.onOptionsItemSelected(item);
    }
}
