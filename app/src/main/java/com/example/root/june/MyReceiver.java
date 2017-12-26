package com.example.root.june;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by root on 17-12-22.
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("onReceive");
        if (!MainActivity.mLocationClient.isStarted()) {
            MainActivity.mLocationClient.start();
            System.out.println("restart");
        }
    }
}