package com.xinyuan.xyshop.model;

import com.xinyuan.xyshop.bean.AttributesBean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by fx on 2017/6/13.
 * 商品规格属性数据
 */

public class GoodsAttrsBean implements Serializable {


	private static final long serialVersionUID = -255343424355613161L;
	private List<AttributesBean> attributes;
	private List<StockGoodsBean> goodsInfo;
	private StockGoodsBean defaultGood;

	public void setDefaultGood(StockGoodsBean defaultGood) {
		this.defaultGood = defaultGood;
	}

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
		return goodsInfo;
	}

	public void setStockGoods(List<StockGoodsBean> stockGoods) {
		this.goodsInfo = stockGoods;
	}


	public class StockGoodsBean implements Serializable {
		private static final long serialVersionUID = 6439389975197126548L;

		public String toString() {
			return goodSpecText.toString();
		}

		public List<GoodsInfoBean> getGoodsSpec() {
			return goodsSpecPropertys;
		}


		/**
		 * goodsID : 1
		 * goodsInfo : [{"tabID":0,"tabName":"颜色","tabValue":"白"},{"tabID":1,"tabName":"型号","tabValue":"X"},{"tabID":2,"tabName":"衣服","tabValue":"羽绒服"},{"tabID":3,"tabName":"大小","tabValue":"中"}]
		 */

		private String goodSpecText;
		private List<GoodsInfoBean> goodsSpecPropertys;
		private long goodsInventory;
		private long goodsSpecInfoId;
		private BigDecimal goodsPrice;
		private String valueImage;


		public String getSpecText() {
			return goodSpecText;
		}

		public BigDecimal getGoodsPrice() {
			return goodsPrice;
		}

		public long getStock() {
			return goodsInventory;
		}

		public BigDecimal getPrice() {
			return goodsPrice;
		}


		public String getGoodSpecText() {
			return goodSpecText;
		}

		public List<GoodsInfoBean> getGoodsSpecPropertys() {
			return goodsSpecPropertys;
		}

		public long getGoodsInventory() {
			return goodsInventory;
		}

		public long getGoodsSpecInfoId() {
			return goodsSpecInfoId;
		}

		public String getValueImage() {
			return valueImage;
		}

		public List<GoodsInfoBean> getGoodsInfo() {
			return goodsSpecPropertys;
		}

		public void setGoodsInfo(List<GoodsInfoBean> goodsInfo) {
			this.goodsSpecPropertys = goodsInfo;
		}


	}

	public class GoodsInfoBean implements Serializable {
		private static final long serialVersionUID = 4766493430148462012L;

		/**
		 * tabID : 0
		 * tabName : 颜色
		 * tabValue : 白
		 */

		private int gspId;
		private String specName;
		private String gspValue;


		public int getGspId() {
			return gspId;
		}

		public String getSpecName() {
			return specName;
		}

		public String getGspValue() {
			return gspValue;
		}

		@Override
		public String toString() {
			return "GoodsInfoBean{" +
					"gspId=" + gspId +
					", specName='" + specName + '\'' +
					", gspValue='" + gspValue + '\'' +
					'}';
		}
	}
}
