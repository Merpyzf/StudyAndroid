package com.merpyzf.reviewandroid.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.merpyzf.reviewandroid.R;
import com.merpyzf.reviewandroid.utils.DensityUtil;

/**
 * 使用代码进行登录界面的布局
 * @author wangke
 */
public class CodeLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建一个最外层的根布局
        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setBackgroundColor(Color.CYAN);

        //设置线性布局中子View的摆放为竖直方向
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        //创建一个根部局的LayoutParams
        LinearLayout.LayoutParams rootParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //设置根部局的Margin值
        rootParams.setMargins(DensityUtil.dip2px(this,5),DensityUtil.dip2px(this,5),DensityUtil.dip2px(this,5),DensityUtil.dip2px(this,5));
        rootParams.gravity = Gravity.CENTER_HORIZONTAL;
        rootLayout.setLayoutParams(rootParams);
        //设置最外层根部局布局的背景颜色
        rootLayout.setBackgroundColor(Color.CYAN);
        //将当前创建的根部局设置给Activity
        setContentView(rootLayout);


        //创建一个显示用户头像的ImageView
        ImageView ivIco = new ImageView(this);
        ivIco.setImageResource(R.drawable.ico);
        //将创建的用于显示头像的ImageView添加到最外层的线性布局中
        rootLayout.addView(ivIco);
        //当通过子View进行获取所在的布局的时候，rootLayout.addView(ivIco)需要写在前面，原因想起来也很清楚,当前的ImageView都还没有添加到父View如果直接通过getLayoutParams()当然会出先空指针异常(可以查看源码)
        LinearLayout.LayoutParams ivParams = (LinearLayout.LayoutParams) ivIco.getLayoutParams();
        //给用于显示头像的ImageView设置宽高
        ivParams.width = DensityUtil.dip2px(this,100f);
        ivParams.height = DensityUtil.dip2px(this,100f);
        //设置居中的方式(子View在父View下的摆放方式为水平居中)
        ivParams.gravity = Gravity.CENTER_HORIZONTAL;
        ivParams.setMargins(0,DensityUtil.dip2px(this,100),0,0);
        ivIco.setLayoutParams(ivParams);



        //创建水平排列的线性布局,用于放置账户相关的View
        LinearLayout userCountLayout = new LinearLayout(this);
        userCountLayout.setOrientation(LinearLayout.HORIZONTAL);
        //将当前的创建的线性布局添加到根View
        rootLayout.addView(userCountLayout);


        //显示用户名的TextView
        TextView tvShowUser = new TextView(this);
        tvShowUser.setText("用户名:");
        userCountLayout.addView(tvShowUser);
        LinearLayout.LayoutParams userCountlayoutParams = (LinearLayout.LayoutParams) tvShowUser.getLayoutParams();
        userCountlayoutParams.setMargins(DensityUtil.dip2px(this,20),DensityUtil.dip2px(this,20),DensityUtil.dip2px(this,20),DensityUtil.dip2px(this,20));


        //创建用于填写用户账户的EditText
        EditText edtUserCount = new EditText(this);
        userCountLayout.addView(edtUserCount);
        edtUserCount.setHint("请输入用户名");
        LinearLayout.LayoutParams edtUserCountlayoutParams = (LinearLayout.LayoutParams) edtUserCount.getLayoutParams();
        edtUserCount.setWidth(0);
        edtUserCountlayoutParams.weight = 1;
        //父View的Gravity属性,确定子View的摆放规则
        edtUserCountlayoutParams.gravity = Gravity.CENTER_VERTICAL;
        //View.setGravity方法相当于  android:gravity="center",该属性表示对View中内容进行的限定
//        edtUserCount.setGravity(Gravity.CENTER);


        //创建线性布局用于容纳密码相关的View
        LinearLayout pwdLayout =  new LinearLayout(this);
        pwdLayout.setGravity(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams pwdLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        pwdLayout.setLayoutParams(pwdLayoutParams);
        rootLayout.addView(pwdLayout);

        //TextView显示密码文本
        TextView tvShowPwd = new TextView(this);
        tvShowPwd.setText("密    码:");
        pwdLayout.addView(tvShowPwd);
        LinearLayout.LayoutParams tvShowPwdParams = (LinearLayout.LayoutParams) tvShowPwd.getLayoutParams();
        tvShowPwdParams.setMargins(DensityUtil.dip2px(this,20),DensityUtil.dip2px(this,20),DensityUtil.dip2px(this,20),DensityUtil.dip2px(this,20));
        tvShowPwdParams.gravity = Gravity.CENTER_VERTICAL;

        //EdtText用于输入密码
        EditText edtPwd = new EditText(this);
        edtPwd.setHint("请输入密码");
        pwdLayout.addView(edtPwd);
        //使用new的方式来获取 LayoutParams
        LinearLayout.LayoutParams edtPwdParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置权重
        edtPwdParams.weight = 1;
        edtPwdParams.width = 0;
        //将布局参数设置给EdtText
        edtPwd.setLayoutParams(edtPwdParams);

        //用于进行登录的button
        Button btnLogin = new Button(this);
        btnLogin.setText("登陆");
        rootLayout.addView(btnLogin);
        LinearLayout.LayoutParams btnLoginParams = (LinearLayout.LayoutParams) btnLogin.getLayoutParams();
        //设置Button的大小
        btnLoginParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        btnLoginParams.height= LinearLayout.LayoutParams.WRAP_CONTENT;
        btnLoginParams.gravity = Gravity.CENTER_HORIZONTAL;


    }
}
