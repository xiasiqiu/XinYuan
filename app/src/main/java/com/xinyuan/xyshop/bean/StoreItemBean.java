package com.xinyuan.xyshop.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by fx on 2017/8/3.
 * 店铺列表数据实体类
 */

public class StoreItemBean implements Serializable {

	private static final long serialVersionUID = -8406949908126856116L;

	private long storeUserId;
	private String storeName;
	private int storeSalenum;
	private String storeUserImg;
	private String storeUserName;
	private String storeLogo;
	private long storeId;
	private int storeGoodsSum;
	private List<GoodsInfoBean> goodsInfo;

	public long getStoreUserId() {
		return storeUserId;
	}

	public void setStoreUserId(int storeUserId) {
		this.storeUserId = storeUserId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public int getStoreSalenum() {
		return storeSalenum;
	}

	public void setStoreSalenum(int storeSalenum) {
		this.storeSalenum = storeSalenum;
	}

	public String getStoreUserImg() {
		return storeUserImg;
	}

	public void setStoreUserImg(String storeUserImg) {
		this.storeUserImg = storeUserImg;
	}

	public String getStoreUserName() {
		return storeUserName;
	}

	public void setStoreUserName(String storeUserName) {
		this.storeUserName = storeUserName;
	}

	public String getStoreLogo() {
		return storeLogo;
	}

	public void setStoreLogo(String storeLogo) {
		this.storeLogo = storeLogo;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getStoreGoodsSum() {
		return storeGoodsSum;
	}

	public void setStoreGoodsSum(int storeGoodsSum) {
		this.storeGoodsSum = storeGoodsSum;
	}

	public List<GoodsInfoBean> getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(List<GoodsInfoBean> goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	@Override
	public String toString() {
		return "StoreItemBean{" +
				"storeUserId=" + storeUserId +
				", storeName='" + storeName + '\'' +
				", storeSalenum=" + storeSalenum +
				", storeUserImg='" + storeUserImg + '\'' +
				", storeUserName='" + storeUserName + '\'' +
				", storeLogo='" + storeLogo + '\'' +
				", storeId=" + storeId +
				", storeGoodsSum=" + storeGoodsSum +
				", goodsInfo=" + goodsInfo +
				'}';
	}

	public class GoodsInfoBean implements Serializable {

		private static final long serialVersionUID = -2458383087119982482L;
		private long goodsId;
		private String goodsPhoto;
		private BigDecimal goodsPrice;
		private int goodsType;

		public int getGoodsType() {
			return goodsType;
		}

		public long getGoodsId() {
			return goodsId;
		}

		public void setGoodsId(int goodsId) {
			this.goodsId = goodsId;
		}

		public String getGoodsPhoto() {
			return goodsPhoto;
		}

		public void setGoodsPhoto(String goodsPhoto) {
			this.goodsPhoto = goodsPhoto;
		}

		public BigDecimal getGoodsPrice() {
			return goodsPrice;
		}

		public void setGoodsPrice(BigDecimal goodsPrice) {
			this.goodsPrice = goodsPrice;
		}

		@Override
		public String toString() {
			return "GoodsInfoBean{" +
					"goodsId=" + goodsId +
					", goodsPhoto='" + goodsPhoto + '\'' +
					", goodsPrice=" + goodsPrice +
					'}';
		}
	}
}
