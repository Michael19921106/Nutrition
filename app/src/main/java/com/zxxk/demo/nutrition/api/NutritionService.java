package com.zxxk.demo.nutrition.api;

import com.zxxk.demo.nutrition.entity.Tabs;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * User: Michael(michael.hms@outlook.com)
 * Date: 2016-03-03
 * Time: 17:54
 * Description:
 */
public interface NutritionService {
    @GET("/type")
    void getTabs(@Query("session_key") String session_key, Callback<Tabs> callback);
}  