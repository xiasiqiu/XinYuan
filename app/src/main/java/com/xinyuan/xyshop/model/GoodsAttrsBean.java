package com.xinyuan.xyshop.model;

import com.xinyuan.xyshop.entity.Attribute;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */

public class GoodsAttrsBean implements Serializable {


	private static final long serialVersionUID = -255343424355613161L;
	private List<AttributesBean> attributes;
	private List<StockGoodsBean> stockGoods;
	private StockGoodsBean defaultGood;

	public StockGoodsBean getDefaultGood() {
		return defaultGood;
	}

	public List<AttributesBean> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributesBean> attributes) {
		this.attributes = attributes;
	}

	public List<StockGoodsBean> getStockGoods() {
		return stockGoods;
	}

	public void setStockGoods(List<StockGoodsBean> stockGoods) {
		this.stockGoods = stockGoods;
	}

	public static class AttributesBean implements Serializable {
		private static final long serialVersionUID = 5351984957505524721L;
		/**
		 * tabID : 0
		 * tabName : 颜色
		 * attributesItem : ["白","蓝","黑"]
		 */

		private int specId;
		private String specName;
		private List<AttributeBean> attributesItem;

		public int getTabID() {
			return specId;
		}

		public void setTabID(int tabID) {
			this.specId = tabID;
		}

		public String getTabName() {
			return specName;
		}

		public void setTabName(String tabName) {
			this.specName = tabName;
		}

		public List<AttributeBean> getAttributesItem() {
			return attributesItem;
		}

		public void setAttributesItem(List<AttributeBean> attributesItem) {
			this.attributesItem = attributesItem;
		}

		public class AttributeBean implements Serializable {
			private static final long serialVersionUID = 3002630256461811622L;
			private String valueName;
			private String valueImage;

			public String getValueName() {
				return valueName;
			}

			public String getValueImage() {
				return valueImage;
			}
		}
	}

	public static class StockGoodsBean implements Serializable {
		private static final long serialVersionUID = 6439389975197126548L;

		public String toString() {
			return goodsSpec.toString();
		}

		public List<GoodsInfoBean> getGoodsSpec() {
			return goodsSpec;
		}


		public String getGoodsName() {
			return goodsName;
		}

		/**
		 * goodsID : 1
		 * goodsInfo : [{"tabID":0,"tabName":"颜色","tabValue":"白"},{"tabID":1,"tabName":"型号","tabValue":"X"},{"tabID":2,"tabName":"衣服","tabValue":"羽绒服"},{"tabID":3,"tabName":"大小","tabValue":"中"}]
		 */

		private int goodsID;
		private List<GoodsInfoBean> goodsSpec;
		private int stock;
		private String specText;
		private String goodsName;
		private double goodsPrice;


		public String getSpecText() {
			return specText;
		}

		public double getGoodsPrice() {
			return goodsPrice;
		}

		public int getStock() {
			return stock;
		}

		public double getPrice() {
			return goodsPrice;
		}

		public int getGoodsID() {
			return goodsID;
		}

		public void setGoodsID(int goodsID) {
			this.goodsID = goodsID;
		}

		public List<GoodsInfoBean> getGoodsInfo() {
			return goodsSpec;
		}

		public void setGoodsInfo(List<GoodsInfoBean> goodsInfo) {
			this.goodsSpec = goodsInfo;
		}

		public class GoodsInfoBean implements Serializable {
			private static final long serialVersionUID = 4766493430148462012L;

			@Override
			public String toString() {
				return specName + ":" + specValue;
			}

			/**
			 * tabID : 0
			 * tabName : 颜色
			 * tabValue : 白
			 */

			private int specId;
			private String specName;
			private String specValue;

			public int getTabID() {
				return specId;
			}

			public void setTabID(int tabID) {
				this.specId = tabID;
			}

			public String getTabName() {
				return specName;
			}

			public void setTabName(String tabName) {
				this.specName = tabName;
			}

			public String getTabValue() {
				return specValue;
			}

			public void setTabValue(String tabValue) {
				this.specValue = tabValue;
			}
		}
	}
}
