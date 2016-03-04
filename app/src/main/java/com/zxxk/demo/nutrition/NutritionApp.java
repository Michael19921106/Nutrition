package com.zxxk.demo.nutrition;

import android.app.Application;
import android.content.Context;

import com.zxxk.demo.nutrition.respository.NetRespository;
import com.zxxk.demo.nutrition.respository.NetRespositoryImpl;

/**
 * User: Michael(michael.hms@outlook.com)
 * Date: 2016-03-03
 * Time: 18:08
 * Description:
 */
public class NutritionApp extends Application {
    public static Context context;
    public static NetRespository respository;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    public static Context getContext(){
        return context;
    }
    public static NetRespository getNetRespository(){
        if (respository == null){
            respository  = new NetRespositoryImpl();
        }
        return respository;
    }

}