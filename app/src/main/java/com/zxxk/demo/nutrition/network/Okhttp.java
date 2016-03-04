package com.zxxk.demo.nutrition.network;

import android.content.Context;

import com.squareup.okhttp.OkHttpClient;
import com.zxxk.demo.nutrition.common.Constant;

import java.util.concurrent.TimeUnit;

/**
 * User: Michael(michael.hms@outlook.com)
 * Date: 2016-03-03
 * Time: 18:02
 * Description:
 */
public class Okhttp {
    private static OkHttpClient okHttpClient;
    public static OkHttpClient createHttpClient(Context context){
        if (okHttpClient == null){
            synchronized (Okhttp.class){
                okHttpClient = new OkHttpClient();
//                okHttpClient.setCache(new Cache(FileUtils.getHttpCacheDir(context), Constant
//                        .HTTP_CACHE_SIZE));
                okHttpClient.setReadTimeout(Constant.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS);
                okHttpClient.setConnectTimeout(Constant.HTTP_CONNECT_TIMEOUT,TimeUnit.MILLISECONDS);

            }
        }
        return okHttpClient;
    }
}  