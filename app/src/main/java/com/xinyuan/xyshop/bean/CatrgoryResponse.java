package com.xinyuan.xyshop.bean;

import com.xinyuan.xyshop.entity.GoodCategory;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/5/3 0003.
 */

public class CatrgoryResponse<T> implements Serializable {

    private static final long serialVersionUID = 5213230387175982834L;

    public int code;
    public String datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "CatrgoryResponse{" +
                "code=" + code +
                ", datas='" + datas + '\'' +
                '}';
    }
}
