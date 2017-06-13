package com.xinyuan.xyshop.model;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */

public class GoodDetailModel {
	private int goodsId;

	private int goodsType;

	private List<GoodBanner> banner;

	private int actualPrice;

	private int oldPrice;

	private int expressCost;

	private int salesVolume;

	private int stock;

	private String unit;

	private List<Goodparams> params;

	private List<SpecParams> specParams;

	private GoodComment comment;

	private String deliveryAddress;

	private List<String> shopServer;

	private List<String> salesPromotion;

	private ShopInfo shop;

	private List<String> goodsDetailImg;

	public int getGoodsId() {
		return this.goodsId;
	}


	public int getGoodsType() {
		return this.goodsType;
	}


	public List<GoodBanner> getGoodBanner() {
		return this.banner;
	}


	public int getActualPrice() {
		return this.actualPrice;
	}


	public int getOldPrice() {
		return this.oldPrice;
	}


	public int getExpressCost() {
		return this.expressCost;
	}


	public int getSalesVolume() {
		return this.salesVolume;
	}


	public int getStock() {
		return this.stock;
	}


	public String getUnit() {
		return this.unit;
	}


	public List<Goodparams> getGoodparams() {
		return this.params;
	}


	public List<SpecParams> getSpecParams() {
		return this.specParams;
	}


	public GoodComment getGoodComment() {
		return this.comment;
	}


	public String getDeliveryAddress() {
		return this.deliveryAddress;
	}


	public List<String> getShopServer() {
		return this.shopServer;
	}


	public List<String> getSalesPromotion() {
		return this.salesPromotion;
	}


	public ShopInfo getShopInfo() {
		return this.shop;
	}


	public List<String> getGoodsDetailImg() {
		return this.goodsDetailImg;
	}

	public class GoodBanner {
		private String imgUrl;

		private String imgTxt;

		public String getImgUrl() {
			return this.imgUrl;
		}

		public String getImgTxt() {
			return this.imgTxt;
		}

		@Override
		public String toString() {
			return "GoodBanner{" +
					"imgUrl='" + imgUrl + '\'' +
					", imgTxt='" + imgTxt + '\'' +
					'}';
		}
	}


	public class Goodparams {
		private String key;

		private List<String> value;


		public String getKey() {
			return this.key;
		}

		public List<String> getValue() {
			return this.value;
		}
	}


	public class SpecParams {
		private String spec_id;

		private String value;


		public String getSpec_id() {
			return this.spec_id;
		}


		public String getValue() {
			return this.value;
		}
	}


	public class Params {
		private String key;

		private String value;


		public String getKey() {
			return this.key;
		}


		public String getValue() {
			return this.value;
		}

		@Override
		public String toString() {
			return  key +
					":"+ value+";";
		}
	}


	public class CommentMore {
		private String time;

		private String content;


		public String getTime() {
			return this.time;
		}


		public String getContent() {
			return this.content;
		}
	}


	public class CommentList {
		private String headImg;

		private String name;

		private int commentLevel;

		private String commentContent;

		private List<String> commentImg;

		private int lookCount;

		private int praiseCount;

		private String time;

		private List<Params> params;

		private CommentMore commentMore;


		public String getHeadImg() {
			return this.headImg;
		}


		public String getName() {
			return this.name;
		}


		public int getCommentLevel() {
			return this.commentLevel;
		}


		public String getCommentContent() {
			return this.commentContent;
		}


		public List<String> getCommentImg() {
			return this.commentImg;
		}


		public int getLookCount() {
			return this.lookCount;
		}


		public int getPraiseCount() {
			return this.praiseCount;
		}


		public String getTime() {
			return this.time;
		}


		public List<Params> getParams() {
			return this.params;
		}


		public CommentMore getCommentMore() {
			return this.commentMore;
		}
	}


	public class GoodComment {
		private int totalCount;

		private List<CommentList> list;


		public int getTotalCount() {
			return this.totalCount;
		}


		public List<CommentList> getList() {
			return this.list;
		}
	}


	public class ShopInfo {
		private int shopId;

		private String shopLogo;

		private String name;

		private List<String> sign;

		private double goodsScore;

		private double serverScore;

		private double logisticsScore;


		public int getShopId() {
			return this.shopId;
		}


		public String getShopLogo() {
			return this.shopLogo;
		}


		public String getName() {
			return this.name;
		}


		public List<String> getSign() {
			return this.sign;
		}


		public double getGoodsScore() {
			return this.goodsScore;
		}


		public double getServerScore() {
			return this.serverScore;
		}


		public double getLogisticsScore() {
			return this.logisticsScore;
		}
	}

	@Override
	public String toString() {
		return "GoodDetailModel{" +
				"goodsId=" + goodsId +
				", goodsType=" + goodsType +
				", GoodBanner=" + banner +
				", actualPrice=" + actualPrice +
				", oldPrice=" + oldPrice +
				", expressCost=" + expressCost +
				", salesVolume=" + salesVolume +
				", stock=" + stock +
				", unit='" + unit + '\'' +
				", Goodparams=" + params +
				", specParams=" + specParams +
				", GoodComment=" + comment +
				", deliveryAddress='" + deliveryAddress + '\'' +
				", shopServer=" + shopServer +
				", salesPromotion=" + salesPromotion +
				", ShopInfo=" + shop +
				", goodsDetailImg=" + goodsDetailImg +
				'}';
	}
}