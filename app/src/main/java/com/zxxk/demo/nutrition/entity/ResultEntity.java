package com.zxxk.demo.nutrition.entity;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * User: Michael(michael.hms@outlook.com)
 * Date: 2016-03-07
 * Time: 10:05
 * Description:
 */
public class ResultEntity {
    @Expose
    private List<ArticlesEntity> articles;

    public void setArticles(List<ArticlesEntity> articles) {
        this.articles = articles;
    }

    public List<ArticlesEntity> getArticles() {
        return articles;
    }
}