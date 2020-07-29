package com.xinyuan.xyshop.even;

/**
 * Created by fx on 2017/8/15.
 * 商品详情售后说明消息
 */

public class GoodServiceEvent {
    private String goodsAfterSalesText;
    private String goodsAfterSalesImg;

    public GoodServiceEvent(String serviceText, String serviceImg) {
        this.goodsAfterSalesText = serviceText;
        this.goodsAfterSalesImg = serviceImg;
    }

    public String getServiceText() {
        return goodsAfterSalesText;
    }

    public String getServiceImg() {
        return goodsAfterSalesImg;
    }
}
