package com.xinyuan.xyshop.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by fx on 2017/9/26.
 * 售后商品实体类
 */

public class ServiceGoodBean implements Serializable {

	private static final long serialVersionUID = -2620162334881345691L;
	private long goodsId;        //商品Id
	private String spec_info;    //规格信息
	private long goodsCartId;    //商品购物车ID
	private String goodsName;   //商品名称
	private String goodsImg;    //商品图片

	private long refundId;      //退款ID
	private int refundMark;     //退款进度
	private BigDecimal refundMoney; //退款金额

	private long returnId;    //退货ID
	private int returnMark;  //退货标记
	private BigDecimal returnMoney; //退货金额




	public long getGoodsId() {
		return goodsId;
	}

	public String getSpec_info() {
		return spec_info;
	}

	public long getGoodsCartId() {
		return goodsCartId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public String getGoodsImg() {
		return goodsImg;
	}

	public long getRefundId() {
		return refundId;
	}

	public int getRefundMark() {
		return refundMark;
	}

	public BigDecimal getRefundMoney() {
		return refundMoney;
	}

	public long getReturnId() {
		return returnId;
	}

	public int getReturnMark() {
		return returnMark;
	}

	public BigDecimal getReturnMoney() {
		return returnMoney;
	}

	@Override
	public String toString() {
		return "ServiceGoodBean{" +
				"goodsId=" + goodsId +
				", spec_info='" + spec_info + '\'' +
				", goodsCartId=" + goodsCartId +
				", goodsName='" + goodsName + '\'' +
				", goodsImg='" + goodsImg + '\'' +
				", refundId=" + refundId +
				", refundMark=" + refundMark +
				", refundMoney=" + refundMoney +
				", returnId=" + returnId +
				", returnMark=" + returnMark +
				", returnMoney=" + returnMoney +
				'}';
	}
}
