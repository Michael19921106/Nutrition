package com.zxxk.demo.nutrition.entity;

import com.google.gson.annotations.Expose;

/**
 * User: Michael(michael.hms@outlook.com)
 * Date: 2016-03-03
 * Time: 15:37
 * Description:
 */
public class Tab extends BaseEntity {
    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private String order;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}