package com.example.root.june;

import android.os.Handler;
import android.widget.Toast;
import android.content.SharedPreferences;

import com.baidu.location.BDLocation;
import com.baidu.location.BDAbstractLocationListener;
/**
 * Created by root on 17-12-22.
 */

public class MyLocationListener extends BDAbstractLocationListener{
    public MainActivity  mainActivity = null;
    public static String username = null;
    private Handler handler = new Handler();
    MyLocationListener(MainActivity mainActivity){
        Toast.makeText(mainActivity, "MyLocationListener", Toast.LENGTH_SHORT).show();
        this.mainActivity = mainActivity;
        SharedPreferences settings = mainActivity.getSharedPreferences("mingmingtang", 0);
        this.username = settings.getString("username", null);
    }

    @Override
    public void onReceiveLocation(BDLocation location){
        //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
        //以下只列举部分获取经纬度相关（常用）的结果信息
        //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

//        Toast.makeText(mainActivity, "onReceiveLocation", Toast.LENGTH_SHORT).show();

        double latitude = location.getLatitude();    //获取纬度信息
        double longitude = location.getLongitude();    //获取经度信息
        float radius = location.getRadius();    //获取定位精度，默认值为0.0f

        String coorType = location.getCoorType();
        //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准

        int errorCode = location.getLocType();
        //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
        System.out.println(errorCode);

        if(errorCode == 161) {
//            AsynNetUtils.post("http://192.168.1.11:8000/location", "{'latitude':'" + String.valueOf(latitude) + "','longitude':'" + String.valueOf(longitude) + "':'" + username+"'}", new AsynNetUtils.Callback() {
            AsynNetUtils.post("https://location.mingmingt.xyz/location", String.valueOf(latitude)+","+String.valueOf(longitude)+","+username, new AsynNetUtils.Callback() {
                @Override
                public void onResponse(String response) {
                    System.out.println(response);
                }
            });
        }
    }
}
