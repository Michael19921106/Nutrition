package com.zxxk.demo.nutrition.api;

import com.google.gson.GsonBuilder;
import com.zxxk.demo.nutrition.BuildConfig;
import com.zxxk.demo.nutrition.NutritionApp;
import com.zxxk.demo.nutrition.network.Okhttp;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * User: Michael(michael.hms@outlook.com)
 * Date: 2016-03-03
 * Time: 17:57
 * Description:
 */
public class RetrofitUtils {

    private static RestAdapter restAdapter;
    public static <T> T createApi(Class<T> clazz,String api){
        if (restAdapter == null){
            synchronized (RetrofitUtils.class){
                if (restAdapter == null){
                    restAdapter = new RestAdapter.Builder()
                            .setEndpoint(api)
                            .setLogLevel(BuildConfig.DEBUG? RestAdapter.LogLevel.FULL :
                                    RestAdapter.LogLevel.NONE)
                            .setConverter(new GsonConverter(new GsonBuilder()
                                    .excludeFieldsWithoutExposeAnnotation().create()))
                            .setClient(new OkClient(Okhttp.createHttpClient(NutritionApp.getContext())))
                            .build();
                }
            }
        }
        return restAdapter.create(clazz);
    }
}  