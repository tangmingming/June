package com.example.root.june;

import android.app.Application;
import android.content.Context;

/**
 * Created by root on 17-12-22.
 */
public class MyApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context =getApplicationContext();
    }
    public static Context getContext() {
        return context;
    }
}
