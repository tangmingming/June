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
        Toast.makeText(context, "Intent Detected.", Toast.LENGTH_LONG).show();
        if(MyService.service == null) {
            Intent service = new Intent(context, MyService.class);
            MyService.service = service;
            context.startService(service);
        }
    }
}