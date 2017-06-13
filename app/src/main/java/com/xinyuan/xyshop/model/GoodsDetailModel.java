package com.xinyuan.xyshop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/6/12.
 */

public class GoodsDetailModel implements Serializable {
	private static final long serialVersionUID = 3637898757971283530L;

	private int goodsId;
	private int goodsType;
	private List<GoodBanner> banners;
	private BigDecimal actualPrice;
	private BigDecimal oldPrice;
	private int expressCost;
	private int salesVolume;
	private int stock;
	private String unit;
	private List<GoodParam> Goodparams;
	private List<SpecParam> specParams;
	private GoodComment goodComment;
	private String deliveryAddress;
	private List<String> shopServer;
	private List<String> salesPromotion;
	private ShopInfo shopInfo;
	private List<String> goodsDetailImgList;


	public class GoodBanner implements Serializable {
		private static final long serialVersionUID = 1958557870364691016L;
		private String imgUrl;
		private String imgTxt;

		public String getImgUrl() {
			return imgUrl;
		}

		public String getImgTxt() {
			return imgTxt;
		}

	}

	public class GoodParam implements Serializable {
		private static final long serialVersionUID = -8240530664699364675L;
		private String key;
		private List<String> value;

		public String getKey() {
			return key;
		}

		public List<String> getValue() {
			return value;
		}

		@Override
		public String toString() {
			return
					key + ":" + value + ";";
		}
	}


	public class SpecParam implements Serializable {

		private static final long serialVersionUID = -7836653862109922362L;
		private String spec_id;
		private String value;

		public String getSpec_id() {
			return spec_id;
		}

		public String getValue() {
			return value;
		}
	}

	public class GoodComment implements Serializable {
		private static final long serialVersionUID = -6283770518787839985L;
		private int totalCount;
		private List<GoodCommentContent> list;

		public int getTotalCount() {
			return totalCount;
		}

		public List<GoodCommentContent> getList() {
			return list;
		}

		public class GoodCommentContent implements Serializable {
			private static final long serialVersionUID = 1564392022768013768L;
			private String headImg;
			private String name;
			private int commentLevel;
			private String commentContent;
			private List<String> commentImg;
			private int lookCount;
			private int praiseCount;
			private String time;
			private List<CommentParam> params;
			private CommentMore commentMore;

			public String getHeadImg() {
				return headImg;
			}

			public String getName() {
				return name;
			}

			public int getCommentLevel() {
				return commentLevel;
			}

			public String getCommentContent() {
				return commentContent;
			}

			public List<String> getCommentImg() {
				return commentImg;
			}

			public int getLookCount() {
				return lookCount;
			}

			public int getPraiseCount() {
				return praiseCount;
			}

			public String getTime() {
				return time;
			}

			public List<CommentParam> getGoodParam() {
				return params;
			}

			public CommentMore getCommentMore() {
				return commentMore;
			}

			public class CommentMore implements Serializable {
				private static final long serialVersionUID = -2900255051826958648L;
				private String time;
				private String content;

				public String getTime() {
					return time;
				}

				public String getContent() {
					return content;
				}
			}

			public class CommentParam implements Serializable {
				private static final long serialVersionUID = -933595566508651340L;
				String key;
				String value;


				public String getKey() {
					return key;
				}

				public String getValue() {
					return value;
				}
			}
		}
	}

	public class ShopInfo implements Serializable {
		private static final long serialVersionUID = -3714010973974370483L;
		private int shopId;
		private String shopLogo;
		private String name;
		private List<String> signs;
		private double goodsScore;
		private double serverScore;
		private double logisticsScore;

		public int getShopId() {
			return shopId;
		}

		public String getShopLogo() {
			return shopLogo;
		}

		public String getName() {
			return name;
		}

		public List<String> getSigns() {
			return signs;
		}

		public double getGoodsScore() {
			return goodsScore;
		}

		public double getServerScore() {
			return serverScore;
		}

		public double getLogisticsScore() {
			return logisticsScore;
		}

	}

	public int getGoodsId() {
		return goodsId;
	}

	public int getGoodsType() {
		return goodsType;
	}

	public List<GoodBanner> getBanners() {
		return banners;
	}

	public BigDecimal getActualPrice() {
		return actualPrice;
	}

	public BigDecimal getOldPrice() {
		return oldPrice;
	}

	public int getExpressCost() {
		return expressCost;
	}

	public int getSalesVolume() {
		return salesVolume;
	}

	public int getStock() {
		return stock;
	}

	public String getUnit() {
		return unit;
	}

	public List<GoodParam> getGoodparams() {
		return Goodparams;
	}

	public List<SpecParam> getSpecParams() {
		return specParams;
	}

	public GoodComment getGoodComment() {
		return goodComment;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public List<String> getShopServer() {
		return shopServer;
	}

	public List<String> getSalesPromotion() {
		return salesPromotion;
	}

	public ShopInfo getShopInfo() {
		return shopInfo;
	}

	public List<String> getGoodsDetailImgList() {
		return goodsDetailImgList;
	}
}
