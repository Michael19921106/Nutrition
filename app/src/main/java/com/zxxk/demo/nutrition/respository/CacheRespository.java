package com.zxxk.demo.nutrition.respository;

import com.zxxk.demo.nutrition.entity.Tab;
import com.zxxk.demo.nutrition.entity.Tabs;

/**
 * User: Michael(michael.hms@outlook.com)
 * Date: 2016-03-14
 * Time: 09:43
 * Description:
 */
public interface CacheRespository {
    void getTabs(String url,CallBack<Tabs> callback);

    void getTabDataByType(String url,CallBack<Tab> callback);

    void getMoreTabDataById(String url,CallBack<Tab> callBack);

    void saveTabs(Tabs tabs,String url);

    void saveTabDataByType(Tab tab,String url);

    void saveMoreTabDataById(Tab tab,String url);

    interface CallBack<T>{
        /**成功**/
        void success(T t);
        /**失败**/
        void failure(Exception e);
    }
}  