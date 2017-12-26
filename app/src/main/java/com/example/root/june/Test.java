package com.example.root.june;

import android.content.SharedPreferences;

/**
 * Created by root on 17-12-22.
 */

public class Test {
    public static void main(String args[]){
        String response = NetUtils.post("https://www.google.com", "test");
        System.out.println(response);
//        AsynNetUtils.post("https://www.google.com", "test", new AsynNetUtils.Callback() {
//            @Override
//            public void onResponse(String response) {
//                System.out.println(response);
//            }
//        });
    }
}
