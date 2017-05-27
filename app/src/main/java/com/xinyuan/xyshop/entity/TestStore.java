package com.xinyuan.xyshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/27.
 */

public class TestStore {
	private String storeName;
	private String express;
	private List<TestGood>goodList;

	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
	}

	public List<TestGood> getGoodList() {
		return goodList;
	}

	public void setGoodList(List<TestGood> goodList) {
		this.goodList = goodList;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@Override
	public String toString() {
		return "TestStore{" +
				"storeName='" + storeName + '\'' +
				", express='" + express + '\'' +
				", goodList=" + goodList +
				'}';
	}

	public class  TestGood{
		private String discount;
		private String goodImg;
		private String goodName;
		private String goodSpec;
		private String goodPrice;
		public TestGood(){}

		public String getDiscount() {
			return discount;
		}

		public void setDiscount(String discount) {
			this.discount = discount;
		}

		public String getGoodImg() {
			return goodImg;
		}

		public void setGoodImg(String goodImg) {
			this.goodImg = goodImg;
		}

		public String getGoodName() {
			return goodName;
		}

		public void setGoodName(String goodName) {
			this.goodName = goodName;
		}

		public String getGoodSpec() {
			return goodSpec;
		}

		public void setGoodSpec(String goodSpec) {
			this.goodSpec = goodSpec;
		}

		public String getGoodPrice() {
			return goodPrice;
		}

		public void setGoodPrice(String goodPrice) {
			this.goodPrice = goodPrice;
		}

		@Override
		public String toString() {
			return "TestGood{" +
					"discount='" + discount + '\'' +
					", goodImg='" + goodImg + '\'' +
					", goodName='" + goodName + '\'' +
					", goodSpec='" + goodSpec + '\'' +
					", goodPrice='" + goodPrice + '\'' +
					'}';
		}
	}



}
