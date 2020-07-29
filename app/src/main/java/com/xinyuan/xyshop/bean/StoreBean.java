package com.xinyuan.xyshop.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by fx on 2017/8/24.
 * 店铺数据
 */

public class StoreBean implements Serializable {
    private static final long serialVersionUID = 6216648327286094261L;
    private long storeId;    //店铺ID
    private String storeName; //店铺名称
    private String storeLogo;   //店铺logo
    private BigDecimal evaluateScore;   //评价分数
    private BigDecimal serviceScore;    //服务评分
    private BigDecimal logisticsScore;  //物流评分
    private String storeFansSum;    //店铺粉丝数量
    private String storeType;   //店铺类型
    private String evaluateScoreText;   //同行业评价水平
    private String serviceScoreText;    //同行业服务水平
    private String logisticsScoreText;  //同行业物流水平
    private String storeAddress;    //店铺所在地
    private String createTime;  //创建时间
    private String storePhone;  //店铺联系手机
    private int storeStatus;  //店铺状态
    private int userFavorites;  //用户是否收藏（0：no/1：yes）
    private long storeUserId;//卖家userID
    private String storeUserName;//卖家名称
    private String storeUserImg;//卖家头像
    private String storeBanner; //店铺店招
    private String storeGradeName;
    private int storeCredit;


    public long getStoreId() {
        return storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public BigDecimal getEvaluateScore() {
        return evaluateScore;
    }

    public BigDecimal getServiceScore() {
        return serviceScore;
    }

    public BigDecimal getLogisticsScore() {
        return logisticsScore;
    }

    public String getStoreFansSum() {
        return storeFansSum;
    }

    public String getStoreType() {
        return storeType;
    }

    public String getEvaluateScoreText() {
        return evaluateScoreText;
    }

    public String getServiceScoreText() {
        return serviceScoreText;
    }

    public String getLogisticsScoreText() {
        return logisticsScoreText;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public int getStoreStatus() {
        return storeStatus;
    }

    public int getUserFavorites() {
        return userFavorites;
    }

    public long getStoreUserId() {
        return storeUserId;
    }

    public String getStoreUserName() {
        return storeUserName;
    }

    public String getStoreUserImg() {
        return storeUserImg;
    }

    public String getStoreBanner() {
        return storeBanner;
    }

    public String getStoreGradeName() {
        return storeGradeName;
    }

    public int getStoreCredit() {
        return storeCredit;
    }
}
