package com.xinyuan.xyshop.model;

import com.xinyuan.xyshop.bean.GoodListItemBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/10/7.
 */

public class GoodListModel implements Serializable{
    private static final long serialVersionUID = 9159328931714754156L;
    private List<GoodListItemBean>goodsList;

    public List<GoodListItemBean> getGoodsList() {
        return goodsList;
    }
}
