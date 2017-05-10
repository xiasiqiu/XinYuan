package com.xinyuan.xyshop.entity;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Title:
 * Description:
 * Created by Administrator on 2017/5/10.
 * 作者：fx on 2017/5/10 22:00
 */

public class GoodCategory implements Serializable {
    private String appImage;
    private String appImageUrl;
    private Bitmap bitmap;
    private int categoryId;
    private List<GoodCategory> categoryList = new ArrayList();
    private String categoryName;
    private int categorySort;
    private int deep = 0;
    private int parentId = 0;

    public GoodCategory(int categoryId, String categoryName, String appImageUrl) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.appImageUrl = appImageUrl;
    }

    public GoodCategory(int categoryId, String categoryName, int parentId, int categorySort, int deep, List<GoodCategory> categoryList) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.parentId = parentId;
        this.categorySort = categorySort;
        this.deep = deep;
        this.categoryList = categoryList;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getParentId() {
        return this.parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getCategorySort() {
        return this.categorySort;
    }

    public void setCategorySort(int categorySort) {
        this.categorySort = categorySort;
    }

    public int getDeep() {
        return this.deep;
    }

    public void setDeep(int deep) {
        this.deep = deep;
    }

    public List<GoodCategory> getCategoryList() {
        return this.categoryList;
    }

    public void setCategoryList(List<GoodCategory> categoryList) {
        this.categoryList = categoryList;
    }

    public String getAppImage() {
        return this.appImage;
    }

    public void setAppImage(String appImage) {
        this.appImage = appImage;
    }

    public String getAppImageUrl() {
        return this.appImageUrl;
    }

    public void setAppImageUrl(String appImageUrl) {
        this.appImageUrl = appImageUrl;
    }

    public String toString() {
        return "GoodCategory{categoryId=" + this.categoryId + ", categoryName='" + this.categoryName + '\'' + ", parentId=" + this.parentId + ", categorySort=" + this.categorySort + ", deep=" + this.deep + ", appImage='" + this.appImage + '\'' + ", appImageUrl='" + this.appImageUrl + '\'' + ", categoryList=" + this.categoryList + '}';
    }
}
