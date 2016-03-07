package com.zxxk.demo.nutrition.entity;

import com.google.gson.annotations.Expose;

/**
 * User: Michael(michael.hms@outlook.com)
 * Date: 2016-03-07
 * Time: 10:12
 * Description:
 */
public class ArticlesEntity {

    @Expose
    private String hits;
    @Expose
    private String article_id;
    @Expose
    private String censor_time;
    @Expose
    private String censor;
    @Expose
    private String id;
    @Expose
    private String pic;
    @Expose
    private String title;

    public void setHits(String hits) {
        this.hits = hits;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public void setCensor_time(String censor_time) {
        this.censor_time = censor_time;
    }

    public void setCensor(String censor) {
        this.censor = censor;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHits() {
        return hits;
    }

    public String getArticle_id() {
        return article_id;
    }

    public String getCensor_time() {
        return censor_time;
    }

    public String getCensor() {
        return censor;
    }

    public String getId() {
        return id;
    }

    public String getPic() {
        return pic;
    }

    public String getTitle() {
        return title;
    }

}  