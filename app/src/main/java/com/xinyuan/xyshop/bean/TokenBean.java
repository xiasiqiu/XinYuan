package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by fx on 2017/7/20.
 * 个人信息修改-返回数据（部分返回可通用）
 */

public class TokenBean implements Serializable {
    private static final long serialVersionUID = -4823644777884418722L;
    private String userInfo;

    @Override
    public String toString() {
        return "TokenBean{" +
                "userinfo='" + userInfo + '\'' +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserPhoto() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }
}
