package com.xinyuan.xyshop.model;


import com.xinyuan.xyshop.entity.CouponBean;
import com.xinyuan.xyshop.entity.GoodListItem;
import com.xinyuan.xyshop.entity.ItemData;
import com.xinyuan.xyshop.entity.StoreInfo;
import com.xinyuan.xyshop.entity.StoreInfoBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */

public class StoreHomeModel implements Serializable {

	private static final long serialVersionUID = -620387687838371096L;
	private ItemData ad;
	private List<CollGood> favList;
	private List<CollGood> sellList;
	private List<GoodListItem> recomList;
	private List<CouponBean> couponList;
	private StoreInfoBean storeInfo;




	@Override
	public String toString() {
		return "StoreHomeModel{" +
				"ad=" + ad +
				", sellList=" + sellList +
				", favList=" + favList +
				", recomList=" + recomList +
				", couponBeanList=" + couponList +
				", storeInfo=" + storeInfo +
				'}';
	}

	public StoreInfoBean getStoreInfo() {
		return storeInfo;
	}

	public ItemData getAd() {
		return ad;
	}

	public List<CollGood> getColList() {
		return sellList;
	}

	public List<CollGood> getFavList() {
		return favList;
	}

	public List<GoodListItem> getRecomList() {
		return recomList;
	}

	public List<CouponBean> getCouponBeanList() {
		return couponList;
	}

	public class CollGood implements Serializable {

		private static final long serialVersionUID = -1192150772330990270L;
		private int goodId;
		private String goodImg;
		private int goodSellNum;

		public int getGoodId() {
			return goodId;
		}

		public String getGoodImg() {
			return goodImg;
		}

		public int getGoodSellNum() {
			return goodSellNum;
		}

		@Override
		public String toString() {
			return "CollGood{" +
					"goodId=" + goodId +
					", goodImg='" + goodImg + '\'' +
					", goodSellNum=" + goodSellNum +
					'}';
		}
	}

}
