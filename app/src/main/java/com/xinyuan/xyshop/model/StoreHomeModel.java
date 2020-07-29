package com.xinyuan.xyshop.model;


import com.xinyuan.xyshop.bean.GoodListItemBean;
import com.xinyuan.xyshop.bean.StoreBean;
import com.xinyuan.xyshop.bean.StoreCouponBean;
import com.xinyuan.xyshop.entity.ItemData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/7/12.
 * 店铺首页数据
 */

public class StoreHomeModel implements Serializable {

	private static final long serialVersionUID = -620387687838371096L;
	private ItemData ad;
	private List<CollGood> favoriteGoodsList;
	private List<CollGood> sellGoodsList;
	private List<GoodListItemBean> recommendedGoodsList;
	private List<StoreCouponBean> couponList;
	private StoreBean storeInfo;

	public ItemData getAd() {
		return ad;
	}

	public List<CollGood> getFavoriteGoodsList() {
		return favoriteGoodsList;
	}

	public List<CollGood> getSellGoodsList() {
		return sellGoodsList;
	}

	public List<GoodListItemBean> getRecommendedGoodsList() {
		return recommendedGoodsList;
	}

	public List<StoreCouponBean> getCouponList() {
		return couponList;
	}

	public StoreBean getStoreInfo() {
		return storeInfo;
	}

	public class CollGood implements Serializable {
		private static final long serialVersionUID = -1730631178968044282L;
		private long goodsId;
		private String goodsImg;
		private long goodsSum;
		private int goodsType;

		public long getGoodsId() {
			return goodsId;
		}

		public String getGoodsImg() {
			return goodsImg;
		}

		public long getGoodsSum() {
			return goodsSum;
		}

		public int getGoodsType() {
			return goodsType;
		}

		@Override
		public String toString() {
			return "CollGood{" +
					"goodsId=" + goodsId +
					", goodsImg='" + goodsImg + '\'' +
					", goodsSum=" + goodsSum +
					", goodsType=" + goodsType +
					'}';
		}
	}

	@Override
	public String toString() {
		return "StoreHomeModel{" +
				"ad=" + ad +
				", favoriteGoodsList=" + favoriteGoodsList +
				", sellGoodsList=" + sellGoodsList +
				", recommendedGoodsList=" + recommendedGoodsList +
				", couponList=" + couponList +
				", storeInfo=" + storeInfo +
				'}';
	}
}
