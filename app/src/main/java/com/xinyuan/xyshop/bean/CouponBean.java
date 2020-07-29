package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by fx on 2017/7/12.
 * 优惠券数据实体类
 */

public class CouponBean implements Serializable {


    private static final long serialVersionUID = 1825000486644875491L;
    private String storeName;
    private String couponName;
    private int status;
    private String couponImg;
    private String beginTime;
    private String endTime;
    private long couponId;
    private String storeId;

    private int storeGradeLevel;
    private String storeGradeName;
    private int storeCredit;

    private boolean isCheck;

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getCouponName() {
        return couponName;
    }

    public int getStatus() {
        return status;
    }

    public String getCouponImg() {
        return couponImg;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public long getCouponId() {
        return couponId;
    }

    public String getStoreId() {
        return storeId;
    }

    public int getStoreGradeLevel() {
        return storeGradeLevel;
    }

    public String getStoreGradeName() {
        return storeGradeName;
    }

    public int getStoreCredit() {
        return storeCredit;
    }

    public boolean isCheck() {
        return isCheck;
    }
}
