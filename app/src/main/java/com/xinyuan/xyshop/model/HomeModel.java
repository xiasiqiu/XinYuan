package com.xinyuan.xyshop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */

public class HomeModel implements Serializable {

	private static final long serialVersionUID = -2595183988797509119L;

	private List<HomeModule> moduleList;
	private GoodModule goodModule;


	public class HomeModule implements Serializable {
		private static final long serialVersionUID = -116615636885253895L;
		private int itemId;
		private int itemSort;
		private String itemType;
		private String itemTypeText;
		private List<HomeModuleData> dataList;
		private String itemtitleImage;
		private String itemtitleCN;
		private String itemtitleEN;
		private String itemtitleColor;

		public String getItemtitleImage() {
			return itemtitleImage;
		}

		public String getItemtitleCN() {
			return itemtitleCN;
		}

		public String getItemtitleEN() {
			return itemtitleEN;
		}

		public String getItemtitleColor() {
			return itemtitleColor;
		}

		public class HomeModuleData implements Serializable {
			private static final long serialVersionUID = -2099631985121828983L;
			private String type;
			private String imageUrl;
			private String data;
			private String text;


			public String getText() {
				return text;
			}

			public String getType() {
				return type;
			}

			public void setType(String type) {
				this.type = type;
			}

			public String getImageUrl() {
				return imageUrl;
			}

			public void setImageUrl(String imageUrl) {
				this.imageUrl = imageUrl;
			}

			public String getData() {
				return data;
			}

			public void setData(String data) {
				this.data = data;
			}

			@Override
			public String toString() {
				return "HomeModuleData{" +
						"type='" + type + '\'' +
						", imageUrl='" + imageUrl + '\'' +
						", data='" + data + '\'' +
						'}';
			}
		}

		public int getItemId() {
			return itemId;
		}

		public void setItemId(int itemId) {
			this.itemId = itemId;
		}

		public int getItemSort() {
			return itemSort;
		}

		public void setItemSort(int itemSort) {
			this.itemSort = itemSort;
		}

		public String getItemType() {
			return itemType;
		}

		public void setItemType(String itemType) {
			this.itemType = itemType;
		}

		public String getItemTypeText() {
			return itemTypeText;
		}

		public void setItemTypeText(String itemTypeText) {
			this.itemTypeText = itemTypeText;
		}

		public List<HomeModuleData> getDataList() {
			return dataList;
		}

		public void setDataList(List<HomeModuleData> dataList) {
			this.dataList = dataList;
		}

		@Override
		public String toString() {
			return "HomeModule{" +
					"itemId=" + itemId +
					", itemSort=" + itemSort +
					", itemType='" + itemType + '\'' +
					", itemTypeText='" + itemTypeText + '\'' +
					", dataList=" + dataList +
					", itemtitleImage='" + itemtitleImage + '\'' +
					", itemtitleCN='" + itemtitleCN + '\'' +
					", itemtitleEN='" + itemtitleEN + '\'' +
					", itemtitleColor='" + itemtitleColor + '\'' +
					'}';
		}
	}


	public class GoodModule implements Serializable {

		private static final long serialVersionUID = -8688200587140878181L;
		private GoodInfo goodInfo;
		private List<HomeGood> goodList;

		public class HomeGood implements Serializable {
			private static final long serialVersionUID = 4805937780770804782L;
			private BigDecimal appPriceMin;
			private BigDecimal goodsPrice;
			private String goodsName;
			private int commonId;
			private int sellnum;
			private String imageUrl;

			public BigDecimal getAppPriceMin() {
				return appPriceMin;
			}

			public void setAppPriceMin(BigDecimal appPriceMin) {
				this.appPriceMin = appPriceMin;
			}

			public BigDecimal getGoodsPrice() {
				return goodsPrice;
			}

			public void setGoodsPrice(BigDecimal goodsPrice) {
				this.goodsPrice = goodsPrice;
			}

			public String getGoodsName() {
				return goodsName;
			}

			public void setGoodsName(String goodsName) {
				this.goodsName = goodsName;
			}

			public int getCommonId() {
				return commonId;
			}

			public void setCommonId(int commonId) {
				this.commonId = commonId;
			}

			public int getSellnum() {
				return sellnum;
			}

			public void setSellnum(int sellnum) {
				this.sellnum = sellnum;
			}

			public String getImageUrl() {
				return imageUrl;
			}

			public void setImageUrl(String imageUrl) {
				this.imageUrl = imageUrl;
			}

			@Override
			public String toString() {
				return "HomeGood{" +
						"appPriceMin=" + appPriceMin +
						", goodsPrice=" + goodsPrice +
						", goodsName='" + goodsName + '\'' +
						", commonId=" + commonId +
						", sellnum=" + sellnum +
						", imageUrl='" + imageUrl + '\'' +
						'}';
			}
		}

		public class GoodInfo implements Serializable {

			private static final long serialVersionUID = 3238275922626907479L;
			private String itemtitleImage;
			private String itemtitleCN;
			private String itemtitleEN;
			private String itemtitleColor;


			public String getItemtitleImage() {
				return itemtitleImage;
			}

			public String getItemtitleCN() {
				return itemtitleCN;
			}

			public String getItemtitleEN() {
				return itemtitleEN;
			}

			public String getItemtitleColor() {
				return itemtitleColor;
			}
		}

		public GoodInfo getGoodInfo() {
			return goodInfo;
		}

		public List<HomeGood> getGoodList() {
			return goodList;
		}
	}


	public List<HomeModule> getModuleList() {
		return moduleList;
	}

	public GoodModule getGoodModule() {
		return goodModule;
	}

	@Override
	public String toString() {
		return "HomeModel{" +
				"moduleList=" + moduleList +
				", goodModule=" + goodModule +
				'}';
	}
}
