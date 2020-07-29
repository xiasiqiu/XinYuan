package com.xinyuan.xyshop.model;

import com.xinyuan.xyshop.entity.ItemData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/6/22.
 * 积分商城数据
 */

public class CreditMallModel implements Serializable {

	private static final long serialVersionUID = -7759768879628633758L;

	private CreditGoodBean goodsModule;
	private CreditCouponBean couponModule;
	private CreditRedpacketBean redpacketModule;
	private CreditBrannerBean bannerModule;



	public CreditGoodBean getGoodsModule() {
		return goodsModule;
	}

	public CreditCouponBean getCouponModule() {
		return couponModule;
	}

	public CreditRedpacketBean getRedpacketModule() {
		return redpacketModule;
	}

	public CreditBrannerBean getBannerModule() {
		return bannerModule;
	}


	public class CreditGoodBean implements Serializable {
		private static final long serialVersionUID = -5637709142254066276L;
		private ItemData ad;
		private List<CreditGood> goodsList;

		public ItemData getAd() {
			return ad;
		}

		public List<CreditGood> getGoodsList() {
			return goodsList;
		}

		@Override
		public String toString() {
			return "CreditGoodBean{" +
					"ad=" + ad +
					", goodsList=" + goodsList +
					'}';
		}
	}

	public class CreditCouponBean implements Serializable {
		private static final long serialVersionUID = -9204222401439578877L;
		private ItemData ad;
		private List<CreditGood> couponList;

		public ItemData getAd() {
			return ad;
		}

		public List<CreditGood> getCouponList() {
			return couponList;
		}

		@Override
		public String toString() {
			return "CreditCouponBean{" +
					"ad=" + ad +
					", couponList=" + couponList +
					'}';
		}
	}

	public class CreditRedpacketBean implements Serializable {
		private static final long serialVersionUID = -384200508319679565L;
		private ItemData ad;

		private List<CreditGood> redpacketList;

		public ItemData getAd() {
			return ad;
		}

		public List<CreditGood> getRedpacketList() {
			return redpacketList;
		}

		@Override
		public String toString() {
			return "CreditRedpacketBean{" +
					"ad=" + ad +
					", redpacketList=" + redpacketList +
					'}';
		}
	}

	public class CreditBrannerBean {
		private List<ItemData> banner;

		public List<ItemData> getBanner() {
			return banner;
		}

	}

	public class CreditGood implements Serializable {


		private static final long serialVersionUID = 4674201118351400433L;
		private String goodsId;
		private String goodsImg;
		private String goodsPrice;
		private String consumeNum;
		private String goodsName;
		private String goodsType;

		public String getGoodsId() {
			return goodsId;
		}

		public String getGoodsImg() {
			return goodsImg;
		}

		public String getGoodsPrice() {
			return goodsPrice;
		}

		public String getConsumeNum() {
			return consumeNum;
		}

		public String getGoodsName() {
			return goodsName;
		}

		public String getGoodsType() {
			return goodsType;
		}

		@Override
		public String toString() {
			return "CreditGood{" +
					"goodsId='" + goodsId + '\'' +
					", goodsImg='" + goodsImg + '\'' +
					", goodsPrice='" + goodsPrice + '\'' +
					", consumeNum='" + consumeNum + '\'' +
					", goodsName='" + goodsName + '\'' +
					", goodsType='" + goodsType + '\'' +
					'}';
		}
	}

	@Override
	public String toString() {
		return "CreditMallModel{" +
				"goodsModule=" + goodsModule +
				", couponModule=" + couponModule +
				", redpacketModule=" + redpacketModule +
				", bannerModule=" + bannerModule +
				'}';
	}
}
