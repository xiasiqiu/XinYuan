package com.xinyuan.xyshop.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by fx on 2017/7/14.
 * 用户足迹数据
 */

public class UserHistoryBean implements Serializable {
	private static final long serialVersionUID = -3748653806866613197L;
	private String goodsPhoto;
	private String goodsName;
	private BigDecimal goodsPrice;
	private long goodsId;
	private long fId;
	private int goodType;


	public int getGoodType() {
		return goodType;
	}

	public String getGoodsImg() {
		return goodsPhoto;
	}

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public long getGoodsId() {
		return goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}



	public String getGoodsPhoto() {
		return goodsPhoto;
	}

	public long getfId() {
		return fId;
	}

	@Override
	public String toString() {
		return "UserHistoryBean{" +
				"goodsPhoto='" + goodsPhoto + '\'' +
				", goodsName='" + goodsName + '\'' +
				", goodsPrice=" + goodsPrice +
				", goodsId=" + goodsId +
				", fId=" + fId +
				'}';
	}
}
