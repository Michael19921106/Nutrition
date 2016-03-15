package com.zxxk.demo.nutrition.respository;

import android.content.Context;

import com.google.gson.Gson;
import com.zxxk.demo.nutrition.database.CacheDao;
import com.zxxk.demo.nutrition.entity.Cache;
import com.zxxk.demo.nutrition.entity.Tab;
import com.zxxk.demo.nutrition.entity.Tabs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * User: Michael(michael.hms@outlook.com)
 * Date: 2016-03-14
 * Time: 09:59
 * Description:
 */
public class CacheRespositoryImpl implements CacheRespository {

    private static final String TAG = CacheRespositoryImpl.class.getSimpleName();
    private static DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private Context mContext;
    private Gson gson;
    private CacheDao dao;

    public CacheRespositoryImpl(Context context) {
        mContext = context;
        dao = new CacheDao(context);
        this.gson = new Gson();
    }


    @Override
    public void getTabs(String url, CallBack<Tabs> callback) {
        getDataObject(url,Tabs.class,callback);
    }

    @Override
    public void getTabDataByType(String url, CallBack<Tab> callback) {
        getDataObject(url,Tab.class,callback);
    }

    @Override
    public void getMoreTabDataById(String url, CallBack<Tab> callBack) {
        getDataObject(url,Tab.class,callBack);
    }


    @Override
    public void saveTabs(Tabs tabs, String url) {
        saveCacheToDb(tabs,url);
    }

    @Override
    public void saveTabDataByType(Tab tab, String url) {
        saveCacheToDb(tab,url);
    }

    @Override
    public void saveMoreTabDataById(Tab tab, String url) {
        saveCacheToDb(tab,url);
    }

    private <T> void getDataObject(String url ,Class<T> tClass,CacheRespository.CallBack callBack){
        String json = dao.getCache(url).getResponse();
        T t = gson.fromJson(json,tClass);
        if (t != null){
            callBack.success(t);
        }else {
            callBack.failure(getException(tClass));
        }
    }

    private Exception getException(Class clazz){
        return new Exception(clazz.getSimpleName() + " cache not fount");
    }

    /**
     * 保存到数据库
     * @param o
     * @param url
     */
    private void saveCacheToDb(Object o,String url){
        Cache cache = new Cache(url,gson.toJson(o),Long.valueOf(df.format(Calendar.getInstance()
                .getTimeInMillis())));
        dao.updateCache(cache);
    }
}