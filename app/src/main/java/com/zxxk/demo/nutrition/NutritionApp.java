package com.zxxk.demo.nutrition;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.zxxk.demo.nutrition.common.Constant;
import com.zxxk.demo.nutrition.respository.Respository;
import com.zxxk.demo.nutrition.respository.RespositoryImpl;

/**
 * User: Michael(michael.hms@outlook.com)
 * Date: 2016-03-03
 * Time: 18:08
 * Description:
 */
public class NutritionApp extends Application {
    public static Context context;
    public static Respository respository;

    @Override
    public void onCreate() {
        setStrictMode();
        super.onCreate();
        context = getApplicationContext();

        initImageLoader(getApplicationContext());
    }
    public static Context getContext(){
        return context;
    }
    public static Respository getRespository(){
        if (respository == null){
            respository  = new RespositoryImpl(context);
        }
        return respository;
    }
    /**
     * issue: retrofit Memory leak in StrickMode
     * https://github.com/square/retrofit/issues/801
     */
    private void setStrictMode() {
        if (BuildConfig.DEBUG && Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            StrictMode.enableDefaults();
        }
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