package com.example.administrator.healthanalysistest.bean;

/**
 * Created by Administrator on 2019/4/21.
 */

public class ItemBean {
    int itemImage;

    int itemContent;

    public ItemBean(int itemImage, int itemContent) {
        this.itemImage = itemImage;
        this.itemContent = itemContent;
    }

    public int getItemImage() {
        return itemImage;
    }

    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
    }


    public int getItemContent() {
        return itemContent;
    }

    public void setItemContent(int itemContent) {
        this.itemContent = itemContent;
    }
}
