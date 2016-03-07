package com.zxxk.demo.nutrition.respository;

import com.zxxk.demo.nutrition.api.NutritionApi;
import com.zxxk.demo.nutrition.entity.Tab;
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
    private String session_key = "5f64bd60f6aa47d192ac69a6bf147e33-1453172272663";

    @Override
    public void getTabs(final CallBack<Tabs> callback) {
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

    @Override
    public void getTabDataByType( String type, final CallBack<Tab> callback) {
        NutritionApi.createApi().getTabDataByType(session_key, type, new Callback<Tab>() {
            @Override
            public void success(Tab tab, Response response) {
                callback.success(tab, response.getUrl());
            }

            @Override
            public void failure(RetrofitError error) {
                callback.failure(error, error.getUrl());
            }
        });
    }

    @Override
    public void getMoreTabDataById(String type, String articleId, final CallBack<Tab> callback) {
        NutritionApi.createApi().getMoreTabDataById(session_key,type,articleId, new Callback<Tab>() {
            @Override
            public void success(Tab tab, Response response) {
                callback.success(tab,response.getUrl());
            }

            @Override
            public void failure(RetrofitError error) {
                callback.failure(error,error.getUrl());
            }
        });
    }
}