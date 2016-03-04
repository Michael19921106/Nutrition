package com.zxxk.demo.nutrition.utils;

import android.util.Log;

import com.zxxk.demo.nutrition.BuildConfig;


/**
 * Created by aspsine on 15-3-13.
 */
public class L {

    public static int i(String tag, String msg){
        if (BuildConfig.DEBUG) {
            return Log.i(tag, msg);
        }
        return -1;
    }

    public static int e(String tag, String msg){
        if (BuildConfig.DEBUG) {
            return Log.e(tag, msg);
        }
        return -1;
    }
}
