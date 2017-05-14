package com.xinyuan.xyshop.entity;

import java.io.Serializable;

/**
 * Title:
 * Description:
 * Created by Administrator on 2017/5/11.
 * 作者：fx on 2017/5/11 00:01
 */

public class BaseData <T> implements Serializable{
    private int code;
    private String datas;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDatas() {
        return this.datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    public String toString() {
        return "BaseData{code=" + this.code + ", datas='" + this.datas + '\'' + '}';
    }
}
