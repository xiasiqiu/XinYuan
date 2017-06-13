package com.xinyuan.xyshop.common;

import com.xinyuan.xyshop.entity.GoodDetailVo;

import java.math.BigDecimal;

/**
 * Title:
 * Description:
 * Created by Administrator on 2017/6/13.
 * 作者：fx on 2017/6/13 22:53
 */

public class GoodHelper {
    public static String getSinglePrice(GoodDetailVo goodDetail, int numAll) {
        BigDecimal price = goodDetail.getAppPrice0();
        if (goodDetail.getGoodsModal() == 2) {
            if (numAll < goodDetail.getBatchNum0()) {
                price = goodDetail.getAppPrice0();
            } else if (goodDetail.getBatchNum0End() == 0) {
                price = goodDetail.getAppPrice0();
            } else if (goodDetail.getBatchNum1End() == 0) {
                if (numAll >= goodDetail.getBatchNum1()) {
                    price = goodDetail.getAppPrice1();
                } else {
                    price = goodDetail.getAppPrice0();
                }
            } else if (numAll >= goodDetail.getBatchNum2()) {
                price = goodDetail.getAppPrice2();
            } else if (numAll >= goodDetail.getBatchNum1()) {
                price = goodDetail.getAppPrice1();
            } else {
                price = goodDetail.getAppPrice0();
            }
        }
        return ShopHelper.getPriceString(price);
    }

    public static String getPriceStringAllShow(GoodDetailVo goodDetail, int allGoodNum) {
        String initPrice = "";
        if (goodDetail.getGoodsModal() == 1) {
            return ShopHelper.getPriceString(goodDetail.getAppPrice0());
        }
        if (goodDetail.getGoodsModal() == 2) {
            return getSinglePrice(goodDetail, allGoodNum);
        }
        return initPrice;
    }
}
