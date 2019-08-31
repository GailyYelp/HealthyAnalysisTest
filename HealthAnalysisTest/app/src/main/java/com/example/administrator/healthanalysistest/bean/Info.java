package com.example.administrator.healthanalysistest.bean;

/**
 * Created by Administrator on 2019/4/21.
 */

public class Info {
    private String theFormTitle;
    private String reason;
    private String title;
    private int image;
    private String feature;
    private String content;

    public Info(String theFormTitle, String reason, String title, int image, String feature, String content) {
        this.theFormTitle = theFormTitle;
        this.reason = reason;
        this.title = title;
        this.image = image;
        this.feature = feature;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFeature() {
        return feature;
    }
    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getTheFormTitle() {
        return theFormTitle;
    }

    public void setTheFormTitle(String theFormTitle) {
        this.theFormTitle = theFormTitle;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
