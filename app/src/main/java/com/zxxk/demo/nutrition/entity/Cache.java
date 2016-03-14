package com.zxxk.demo.nutrition.entity;

import com.google.gson.annotations.Expose;

/**
 * User: Michael(michael.hms@outlook.com)
 * Date: 2016-03-14
 * Time: 10:04
 * Description:
 */
public class Cache {
    private long id;
    @Expose
    private String request;
    @Expose
    private String response;
    @Expose
    private long time;

    public Cache() {
    }

    public Cache( String request, String response, long time) {
        this.request = request;
        this.response = response;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}