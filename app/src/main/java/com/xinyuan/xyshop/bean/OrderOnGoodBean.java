package com.xinyuan.xyshop.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by fx on 2017/9/14.
 * 虚拟订单商品数据
 */

public class OrderOnGoodBean implements Serializable {
	private static final long serialVersionUID = 1412541375209817310L;
	private BigDecimal price;
	private String imgUrl;
	private String goodName;
	private int goodNum;
	private String goodSpec;
	private String goodSpecId;
	private String phone;
	private long goodId;
	private String storeName;

	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;

	}

	public void setGoodSpecId(String goodSepcId) {
		this.goodSpecId = goodSepcId;
	}

	public String getGoodSpecId() {
		return goodSpecId;
	}

	public long getGoodId() {
		return goodId;
	}

	public void setGoodId(long goodId) {
		this.goodId = goodId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public int getGoodNum() {
		return goodNum;
	}

	public void setGoodNum(int goodNum) {
		this.goodNum = goodNum;
	}

	public String getGoodSpec() {
		return goodSpec;
	}

	public void setGoodSpec(String goodSpec) {
		this.goodSpec = goodSpec;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "OrderOnGoodBean{" +
				"price=" + price +
				", imgUrl='" + imgUrl + '\'' +
				", goodName='" + goodName + '\'' +
				", goodNum=" + goodNum +
				", goodSpec='" + goodSpec + '\'' +
				", goodSpecId='" + goodSpecId + '\'' +
				", phone='" + phone + '\'' +
				", goodId=" + goodId +
				", storeName='" + storeName + '\'' +
				'}';
	}
}
