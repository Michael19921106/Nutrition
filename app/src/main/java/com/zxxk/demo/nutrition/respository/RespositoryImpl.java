package com.zxxk.demo.nutrition.respository;

import android.content.Context;

import com.zxxk.demo.nutrition.entity.Tab;
import com.zxxk.demo.nutrition.entity.Tabs;

/**
 * User: Michael(michael.hms@outlook.com)
 * Date: 2016-03-14
 * Time: 15:08
 * Description:
 */
public class RespositoryImpl implements Respository {
    private static final String TAG = RespositoryImpl.class.getSimpleName();
    private CacheRespository mCache;
    private NetRespository mNet;
    private Context context;

    public RespositoryImpl(Context context) {
        this.context = context;
        this.mCache = new CacheRespositoryImpl(context);
        this.mNet = new NetRespositoryImpl();
    }

    @Override
    public void getTabs(final Callback<Tabs> callback) {
        mNet.getTabs(new NetRespository.CallBack<Tabs>() {
            @Override
            public void success(Tabs tabs, String url) {
                callback.success(tabs, false);
                mCache.saveTabs(tabs, url);
            }

            @Override
            public void failure(final Exception error, String url) {
                mCache.getTabs(url, new CacheRespository.CallBack<Tabs>() {
                    @Override
                    public void success(Tabs tabs) {
                        callback.success(tabs, true);
                    }

                    @Override
                    public void failure(Exception e) {
                        callback.failure(error);
                    }
                });
            }
        });
    }

    @Override
    public void getTabDataByType(String type, final Callback<Tab> callback) {
        mNet.getTabDataByType(type, new NetRespository.CallBack<Tab>() {
            @Override
            public void success(Tab tab, String url) {
                callback.success(tab, false);
                mCache.saveTabDataByType(tab, url);
            }

            @Override
            public void failure(final Exception error, String url) {
                mCache.getTabDataByType(url, new CacheRespository.CallBack<Tab>() {
                    @Override
                    public void success(Tab tab) {
                        callback.success(tab, true);
                    }

                    @Override
                    public void failure(Exception e) {
                        callback.failure(error);
                    }
                });
            }
        });
    }

    @Override
    public void getMoreTabDataById(String type, String articleId, final Callback<Tab> callBack) {
        mNet.getMoreTabDataById(type, articleId, new NetRespository.CallBack<Tab>() {
            @Override
            public void success(Tab tab, String url) {
                callBack.success(tab,false);
            }

            @Override
            public void failure(Exception e, String url) {
                mCache.getTabDataByType(url, new CacheRespository.CallBack<Tab>() {
                    @Override
                    public void success(Tab tab) {
                        callBack.success(tab,true);
                    }

                    @Override
                    public void failure(Exception e) {
                        callBack.failure(e);
                    }
                });
            }
        });
    }
}