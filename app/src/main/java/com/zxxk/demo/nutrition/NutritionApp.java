package com.zxxk.demo.nutrition;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.zxxk.demo.nutrition.common.Constant;
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

        initImageLoader(getApplicationContext());
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

    public static void initImageLoader(final Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(Constant.IMAGE_CACHE_SIZE) // 50 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO)
//                .writeDebugLogs() // Remove for release app
                .build();
        ImageLoader.getInstance().init(config.createDefault(context));
    }
}