package com.merpyzf.reviewandroid.activity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.merpyzf.reviewandroid.R;

public class LocationActivity extends AppCompatActivity {

    private TextView tv_location;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        context = this;
        tv_location = (TextView) findViewById(R.id.tv_location);

        //获取LocationManager
        LocationManager lManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        /**
         * 参1:选择定位的方式
         * 参2:定位的间隔时间
         * 参3:当位置改变多少时进行重新定位
         * 参4:位置的回调监听
         */
        lManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 0, new LocationListener() {
            //当位置改变的时候调用
            @Override
            public void onLocationChanged(Location location) {

                //经度
                double longitude = location.getLongitude();
                //纬度
                double latitude = location.getLatitude();

                //海拔
                double altitude = location.getAltitude();

                tv_location.setText("经度:==>"+longitude+" \n 纬度==>"+latitude+"\n"+"海拔==>"+altitude);
            }

            //当GPS状态发生改变的时候调用
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {


                switch (status){

                    case LocationProvider.AVAILABLE:

                        Toast.makeText(context,"当前GPS为可用状态!",Toast.LENGTH_SHORT).show();

                        break;

                    case LocationProvider.OUT_OF_SERVICE:

                        Toast.makeText(context,"当前GPS不在服务内",Toast.LENGTH_SHORT).show();

                        break;

                    case LocationProvider.TEMPORARILY_UNAVAILABLE:

                        Toast.makeText(context,"当前GPS为暂停服务状态",Toast.LENGTH_SHORT).show();
                        break;


                }

            }

            //GPS开启的时候调用
            @Override
            public void onProviderEnabled(String provider) {

                Toast.makeText(context,"GPS开启了",Toast.LENGTH_SHORT).show();

            }

            //GPS关闭的时候调用
            @Override
            public void onProviderDisabled(String provider) {

                Toast.makeText(context,"GPS关闭了",Toast.LENGTH_SHORT).show();

            }
        });




    }
}
