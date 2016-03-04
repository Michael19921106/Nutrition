package com.zxxk.demo.nutrition.respository;

import com.zxxk.demo.nutrition.entity.Tabs;


/**
 * User: Michael(michael.hms@outlook.com)
 * Date: 2016-03-04
 * Time: 09:39
 * Description:
 */
public interface NetRespository {
    void getTabs(String session_key, CallBack<Tabs> callback);
    interface CallBack<T>{
        /**成功**/
        void success(T t, String url);
        /**失败**/
        void failure(Exception e,String url);
    }

}  