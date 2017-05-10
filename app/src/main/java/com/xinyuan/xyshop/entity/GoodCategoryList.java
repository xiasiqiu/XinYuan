package com.xinyuan.xyshop.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Title:
 * Description:
 * Created by Administrator on 2017/5/11.
 * 作者：fx on 2017/5/11 00:19
 */

public class GoodCategoryList implements Serializable {

    private List<GoodCategory> categoryList = new ArrayList();

    public List<GoodCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<GoodCategory> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public String toString() {
        return "GoodCategoryList{" +
                "categoryList=" + categoryList +
                '}';
    }
}
