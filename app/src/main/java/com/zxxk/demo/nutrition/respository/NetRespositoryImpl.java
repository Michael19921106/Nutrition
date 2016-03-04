package com.zxxk.demo.nutrition.respository;

import com.zxxk.demo.nutrition.api.NutritionApi;
import com.zxxk.demo.nutrition.entity.Tabs;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * User: Michael(michael.hms@outlook.com)
 * Date: 2016-03-04
 * Time: 09:44
 * Description:
 */
public class NetRespositoryImpl implements NetRespository {
    private static final String TAG = NetRespositoryImpl.class.getSimpleName();


    @Override
    public void getTabs(String session_key, final CallBack<Tabs> callback) {
        NutritionApi.createApi().getTabs(session_key, new Callback<Tabs>() {
            @Override
            public void success(Tabs tabs, Response response) {
                callback.success(tabs,response.getUrl());
            }

            @Override
            public void failure(RetrofitError error) {
                callback.failure(error,error.getUrl());
            }
        });
    }
}