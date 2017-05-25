package com.xinyuan.xyshop.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/25.
 */

public class GoodDetailVo {
	private BigDecimal appPrice0;
	private BigDecimal appPrice1;
	private BigDecimal appPrice2;
	private BigDecimal appPriceMin;
	private int appUsable;
	private int areaId1;
	private int areaId2;
	private int batchNum0;
	private int batchNum0End;
	private int batchNum1;
	private int batchNum1End;
	private int batchNum2;
	private BigDecimal batchPrice0;
	private BigDecimal batchPrice1;
	private BigDecimal batchPrice2;
	private BookBean book;
	private List<BookBean> bookList;
	private int brandId;
	private int categoryId;
	private int colorId;
	private int commonId;
	private Discount discount;
	private Integer evaluateNum;
	private int formatBottom;
	private int formatTop;
	private List<GoodGift> giftVoList;
	private List<GoodsAttrVo> goodsAttrList;
	private String goodsBody;
	private int goodsClick;
	private int goodsFavorite;
	private int goodsId;
	private List<GoodsImage> goodsImageList;
	private List<Goods> goodsList;
	private int goodsModal;
	private String goodsName;
	private String goodsPrice;
	private String goodsQRCode;
	private Integer goodsRate;
	private int goodsSaleNum;
	private String goodsSerial;
	private List<String> goodsSpecNameList = new ArrayList();
	private List<GoodsSpecValueJsonVo> goodsSpecValueJson;
	private String goodsSpecValues;
	private int goodsStatus;
	private String imageSrc;
	private int isGift;
	private String jingle;
	private String mobileBody;
	private long promotionCountDownTime;
	private String promotionCountDownTimeType;
	private String promotionEndTime;
	private int promotionId;
	private String promotionStartTime;
	private int promotionState;
	private int promotionType;
	private String promotionTypeText;
	private List<SpecJsonVo> specJson = new ArrayList();
	private int storeId;
	private String unitName;
	private BigDecimal webPrice0;
	private BigDecimal webPrice1;
	private BigDecimal webPrice2;
	private int webUsable;
	private BigDecimal wechatPrice0;
	private BigDecimal wechatPrice1;
	private BigDecimal wechatPrice2;
	private int wechatUsable;
}
