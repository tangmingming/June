package com.example.root.june;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.telephony.TelephonyManager;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;
import android.content.SharedPreferences;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;

public class MainActivity extends AppCompatActivity {

//    public LocationClient mLocationClient = null;
//    private MyLocationListener myListener = new MyLocationListener(this);
    public static LocationClient mLocationClient = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(this.mLocationClient == null) {
            MyLocationListener myListener = new MyLocationListener(this);

            mLocationClient = new LocationClient(getApplicationContext());
            //声明LocationClient类
            mLocationClient.registerLocationListener(myListener);

            LocationClientOption option = new LocationClientOption();

            option.setLocationMode(LocationMode.Battery_Saving);
//可选，设置定位模式，默认高精度
//LocationMode.Hight_Accuracy：高精度；
//LocationMode. Battery_Saving：低功耗；
//LocationMode. Device_Sensors：仅使用设备；

            option.setCoorType("bd09ll");
//可选，设置返回经纬度坐标类型，默认gcj02
//gcj02：国测局坐标；
//bd09ll：百度经纬度坐标；
//bd09：百度墨卡托坐标；
//海外地区定位，无需设置坐标类型，统一返回wgs84类型坐标

            option.setScanSpan(10000);
//可选，设置发起定位请求的间隔，int类型，单位ms
//如果设置为0，则代表单次定位，即仅定位一次，默认为0
//如果设置非0，需设置1000ms以上才有效

            option.setOpenGps(true);
//可选，设置是否使用gps，默认false
//使用高精度和仅用设备两种定位模式的，参数必须设置为true

            option.setLocationNotify(true);
//可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

            option.setIgnoreKillProcess(true);
//可选，定位SDK内部是一个service，并放到了独立进程。
//设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

            option.SetIgnoreCacheException(false);
//可选，设置是否收集Crash信息，默认收集，即参数为false

            option.setWifiCacheTimeOut(5 * 60 * 1000);
//可选，7.2版本新增能力
//如果设置了该接口，首次启动定位时，会先判断当前WiFi是否超出有效期，若超出有效期，会先重新扫描WiFi，然后定位

            option.setEnableSimulateGps(false);
//可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false

            mLocationClient.setLocOption(option);
//mLocationClient为第二步初始化过的LocationClient对象
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
//更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明

            mLocationClient.start();
//        Toast.makeText(this, "服务已经启动", Toast.LENGTH_LONG).show();

            Toast.makeText(this, "服务已经启动", Toast.LENGTH_LONG).show();
        }else if(!mLocationClient.isStarted()){
            mLocationClient.start();
            Toast.makeText(this, "postion service restart", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume");
        if(!mLocationClient.isStarted()){
            mLocationClient.start();
            Toast.makeText(this, "postion service restart", Toast.LENGTH_LONG).show();
        }
    }

    public void setUsername(View view){
        EditText et_username = (EditText) findViewById(R.id.username);
        String username = et_username.getText().toString();
        MyLocationListener.username = username;
        Toast.makeText(getBaseContext(), username, Toast.LENGTH_SHORT).show();
        SharedPreferences settings = getSharedPreferences("mingmingtang", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("username", username);
        editor.commit();
    }

    public void getUsername(View view){
        System.out.println("getUsername");
        SharedPreferences settings = getSharedPreferences("mingmingtang", 0);
        String username = settings.getString("username", "");
        if(username.equals("")){
            Toast.makeText(getBaseContext(), "kong", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getBaseContext(), username, Toast.LENGTH_SHORT).show();
        }
    }
}
