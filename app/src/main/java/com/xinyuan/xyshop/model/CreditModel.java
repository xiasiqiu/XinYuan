package com.xinyuan.xyshop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2017/6/22.
 */

public class CreditModel implements Serializable {
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public List<CreditBanner> getBanner() {
		return banner;
	}

	public List<CreditNav> getNav() {
		return nav;
	}

	public List<CreditModule> getMallFloors() {
		return mallFloors;
	}

	public List<CreditGood> getRecommend() {
		return recommend;
	}

	private static final long serialVersionUID = 3148987774101626289L;
	private List<CreditBanner> banner;
	private List<CreditNav> nav;
	private List<CreditModule> mallFloors;
	private List<CreditGood> recommend;


	public class CreditBanner implements Serializable {
		private static final long serialVersionUID = -4182564879535860944L;
		private String type;
		private String data;
		private String imageUrl;

		public String getType() {
			return type;
		}

		public String getData() {
			return data;
		}

		public String getImageUrl() {
			return imageUrl;
		}
	}

	public class CreditNav implements Serializable {
		private static final long serialVersionUID = -4335323649950836473L;
		private String navName;
		private String imgSrc;

		public String getNavName() {
			return navName;
		}

		public String getImgSrc() {
			return imgSrc;
		}
	}

	public class CreditModule implements Serializable {
		private static final long serialVersionUID = 3720474246640564051L;
		private String floorTitle;
		private CreditAd ad;
		private List<CreditGood> goodsList;

		public String getFloorTitle() {
			return floorTitle;
		}

		public CreditAd getAd() {
			return ad;
		}

		public List<CreditGood> getGoodsList() {
			return goodsList;
		}

		public class CreditAd implements Serializable {
			private static final long serialVersionUID = -1855661408013589264L;
			private String imageUrl;
			private int goodsId;

			public String getImageUrl() {
				return imageUrl;
			}

			public int getGoodsId() {
				return goodsId;
			}
		}
	}


	public class CreditGood implements Serializable {
		private static final long serialVersionUID = 5628224657529665631L;
		private int goodsId;
		private String goodsName;
		private int goodsType;
		private String goodsActive;
		private BigDecimal goodsPrice;
		private int exNum;
		private String imageUrl;

		public int getGoodsId() {
			return goodsId;
		}

		public String getGoodsName() {
			return goodsName;
		}

		public int getGoodsType() {
			return goodsType;
		}

		public String getGoodsActive() {
			return goodsActive;
		}

		public BigDecimal getGoodsPrice() {
			return goodsPrice;
		}

		public int getExNum() {
			return exNum;
		}

		public String getImageUrl() {
			return imageUrl;
		}
	}


}
