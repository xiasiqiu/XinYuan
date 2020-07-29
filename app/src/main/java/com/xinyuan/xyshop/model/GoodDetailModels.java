package com.xinyuan.xyshop.model;

import com.xinyuan.xyshop.bean.GoodEvaBean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by fx on 2017/8/15.
 * 商品详情数据
 */

public class GoodDetailModels implements Serializable {
    private static final long serialVersionUID = 3254958751398676105L;
    private int goodsTransfee;
    private long goodsId;
    private GoodEvaNum evaluate;
    private String goodsDetails;
    private GoodsStoreInfo store;
    private List<GoodEvaBean> evaluateList;
    private GoodsAttrsBean goodSpec;
    private long consumeNum;
    private BigDecimal goodsOriginalPrice;
    private List<GoodBanner> goodsBanner;
    private String goodsAfterSalesText;
    private String goodsAddress;
    private int goodsInventory;
    private int goodsEvaluates;
    private List<GoodParam> specParams;
    private String goodsAfterSalesImg;
    private BigDecimal goodsPrice;
    private int goodsStatus;
    private List<String> storeSecurity;
    private int goodsType;
    private String goodsName;
    private int goodsFavorites;
    private GoodsActivityBean goodsActivity;

    public GoodsActivityBean getGoodsActivity() {
        return goodsActivity;
    }

    public class GoodsActivityBean implements Serializable {
        private static final long serialVersionUID = -3520180841520579384L;
        private String groupName;
        private String groupTime;

        public String getGroupName() {
            return groupName;
        }

        public String getGroupTime() {
            return groupTime;
        }
    }

    public class GoodEvaNum implements Serializable {
        private static final long serialVersionUID = 1767499666057191287L;
        private int goodsEvaluates;
        private int goodsEvaluatesImg;
        private int goodsEvaluatesLow;
        private int goodsEvaluatesNormal;
        private int goodsEvaluatesGood;

        public int getGoodsEvaluates() {
            return goodsEvaluates;
        }

        public int getGoodsEvaluatesImg() {
            return goodsEvaluatesImg;
        }

        public int getGoodsEvaluatesLow() {
            return goodsEvaluatesLow;
        }

        public int getGoodsEvaluatesNormal() {
            return goodsEvaluatesNormal;
        }

        public int getGoodsEvaluatesGood() {
            return goodsEvaluatesGood;
        }

        @Override
        public String toString() {
            return "GoodEvaNum{" +
                    "goodsEvaluates=" + goodsEvaluates +
                    ", goodsEvaluatesImg=" + goodsEvaluatesImg +
                    ", goodsEvaluatesLow=" + goodsEvaluatesLow +
                    ", goodsEvaluatesNormal=" + goodsEvaluatesNormal +
                    ", goodsEvaluatesGood=" + goodsEvaluatesGood +
                    '}';
        }
    }

    public class GoodsStoreInfo implements Serializable {
        private static final long serialVersionUID = 2697329127392906181L;
        private String storeName;
        private Double logistics_score;
        private Double service_score;
        private Double evaluation_score;
        private String storeLogo;
        private long storeId;
        private long storeUserId;
        private String storeUserImg;
        private String storeUserName;
        private String storeGradeName;
        private int storeCredit;

        @Override
        public String toString() {
            return "GoodsStoreInfo{" +
                    "storeName='" + storeName + '\'' +
                    ", logistics_score=" + logistics_score +
                    ", service_score=" + service_score +
                    ", evaluation_score=" + evaluation_score +
                    ", storeLogo='" + storeLogo + '\'' +
                    ", storeId=" + storeId +
                    ", storeUserId=" + storeUserId +
                    ", storeUserImg='" + storeUserImg + '\'' +
                    ", storeUserName='" + storeUserName + '\'' +
                    ", storeGradeName='" + storeGradeName + '\'' +
                    ", storeCredit=" + storeCredit +
                    '}';
        }

        public String getStoreName() {
            return storeName;
        }

        public Double getLogistics_score() {
            return logistics_score;
        }

        public Double getService_score() {
            return service_score;
        }

        public Double getEvaluation_score() {
            return evaluation_score;
        }

        public String getStoreLogo() {
            return storeLogo;
        }

        public long getStoreId() {
            return storeId;
        }

        public long getStoreUserId() {
            return storeUserId;
        }

        public String getStoreUserImg() {
            return storeUserImg;
        }

        public String getStoreUserName() {
            return storeUserName;
        }

        public String getStoreGradeName() {
            return storeGradeName;
        }

        public int getStoreCredit() {
            return storeCredit;
        }
    }

    public class GoodBanner implements Serializable {
        private static final long serialVersionUID = 4755360298062308326L;
        private String goodsTxt;
        private String goodsImage;

        public String getGoodsTxt() {
            return goodsTxt;
        }

        public String getGoodsImage() {
            return goodsImage;
        }
    }

    public class GoodParam implements Serializable {
        private static final long serialVersionUID = -1179193076420497658L;
        private String apecName;
        private String apecValue;

        public String getApecName() {
            return apecName;
        }

        public String getApecValue() {
            return apecValue;
        }

        @Override
        public String toString() {
            return "GoodParam{" +
                    "apecName='" + apecName + '\'' +
                    ", apecValue='" + apecValue + '\'' +
                    '}';
        }
    }

    public GoodEvaNum getEvaluate() {
        return evaluate;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public String getGoodsDetails() {
        return goodsDetails;
    }

    public GoodsStoreInfo getStore() {
        return store;
    }

    public List<GoodEvaBean> getEvaluateList() {
        return evaluateList;
    }

    public GoodsAttrsBean getGoodSpec() {
        return goodSpec;
    }

    public long getConsumeNum() {
        return consumeNum;
    }

    public BigDecimal getGoodsOriginalPrice() {
        return goodsOriginalPrice;
    }

    public List<GoodBanner> getGoodsBanner() {
        return goodsBanner;
    }

    public String getGoodsAfterSalesText() {
        return goodsAfterSalesText;
    }

    public String getGoodsAddress() {
        return goodsAddress;
    }

    public int getGoodsInventory() {
        return goodsInventory;
    }

    public int getGoodsEvaluates() {
        return goodsEvaluates;
    }

    public List<GoodParam> getSpecParams() {
        return specParams;
    }

    public String getGoodsAfterSalesImg() {
        return goodsAfterSalesImg;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public int getGoodsStatus() {
        return goodsStatus;
    }

    public List<String> getStoreSecurity() {
        return storeSecurity;
    }

    public int getGoodsType() {
        return goodsType;
    }

    public int getGoodsTransfee() {
        return goodsTransfee;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public int getGoodsFavorites() {
        return goodsFavorites;
    }

    @Override
    public String toString() {
        return "GoodDetailModels{" +
                "goodsId=" + goodsId +
                ", evaluate=" + evaluate +
                ", goodsDetails='" + goodsDetails + '\'' +
                ", store=" + store +
                ", evaluateList=" + evaluateList +
                ", goodSpec=" + goodSpec +
                ", consumeNum=" + consumeNum +
                ", goodsOriginalPrice=" + goodsOriginalPrice +
                ", goodsBanner=" + goodsBanner +
                ", goodsAfterSalesText='" + goodsAfterSalesText + '\'' +
                ", goodsAddress='" + goodsAddress + '\'' +
                ", goodsInventory=" + goodsInventory +
                ", goodsEvaluates=" + goodsEvaluates +
                ", specParams=" + specParams +
                ", goodsAfterSalesImg='" + goodsAfterSalesImg + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", goodsStatus=" + goodsStatus +
                ", storeSecurity=" + storeSecurity +
                ", goodsType=" + goodsType +
                '}';
    }
}
