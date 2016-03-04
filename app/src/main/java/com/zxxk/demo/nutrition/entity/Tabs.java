package com.zxxk.demo.nutrition.entity;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * User: Michael(michael.hms@outlook.com)
 * Date: 2016-03-03
 * Time: 15:27
 * Description:
 */
public class Tabs  {

    String statuscode;

    public String getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
    }

    @Expose
    List<Tab> result;

    public List<Tab> getTabs() {
        return result;
    }

    public void setTabs(List<Tab> tabs) {
        this.result = tabs;
    }
}
