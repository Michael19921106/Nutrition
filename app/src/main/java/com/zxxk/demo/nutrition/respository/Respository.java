package com.zxxk.demo.nutrition.respository;

import com.zxxk.demo.nutrition.entity.Tab;
import com.zxxk.demo.nutrition.entity.Tabs;

/**
 * User: Michael(michael.hms@outlook.com)
 * Date: 2016-03-14
 * Time: 15:03
 * Description:
 */
public interface Respository {
    void getTabs(Callback<Tabs> callback);

    void getTabDataByType(String type,Callback<Tab> callback);

    void getMoreTabDataById(String type,String articleId,Callback<Tab> callBack);

    public interface Callback<T>{
        public void success(T t,boolean outdate);
        public void failure(Exception e);
    }



}  