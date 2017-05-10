package com.xinyuan.xyshop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/5/3 0003.
 */

public class CatrgoryResponse<T> implements Serializable {

    private static final long serialVersionUID = 5213230387175982834L;

    public int code;
    public List<GoodCategory> itemList;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<GoodCategory> getItemList() {
        return itemList;
    }

    public void setDatas(List<GoodCategory> datas) {
        this.itemList = datas;
    }

    @Override
    public String toString() {
        return "LzyResponse{" +
                "code=" + code +
                ", datas=" + itemList +
                '}';
    }
}
