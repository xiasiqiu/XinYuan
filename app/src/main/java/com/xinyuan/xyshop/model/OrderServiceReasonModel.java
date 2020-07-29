package com.xinyuan.xyshop.model;

import java.io.Serializable;
import java.lang.ref.PhantomReference;
import java.util.List;

/**
 * Created by Administrator on 2017/9/23.
 */

public class OrderServiceReasonModel implements Serializable {
	private static final long serialVersionUID = -4215886491189851796L;
	private String money;
	private List<ServiceReasonBean> goodsReturnResonList;

	@Override
	public String toString() {
		return "OrderServiceReasonModel{" +
				"money='" + money + '\'' +
				", goodsReturnResonList=" + goodsReturnResonList +
				'}';
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public List<ServiceReasonBean> getGoodsReturnResonList() {
		return goodsReturnResonList;
	}

	public void setGoodsReturnResonList(List<ServiceReasonBean> goodsReturnResonList) {
		this.goodsReturnResonList = goodsReturnResonList;
	}

	public class ServiceReasonBean implements Serializable {
		private static final long serialVersionUID = 5719106086643893276L;
		private long goodsReturnResonId;
		private String goodsReturnResonName;

		public long getGoodsReturnResonId() {
			return goodsReturnResonId;
		}

		public void setGoodsReturnResonId(long goodsReturnResonId) {
			this.goodsReturnResonId = goodsReturnResonId;
		}

		public String getGoodsReturnResonName() {
			return goodsReturnResonName;
		}

		public void setGoodsReturnResonName(String goodsReturnResonName) {
			this.goodsReturnResonName = goodsReturnResonName;
		}

		@Override
		public String toString() {
			return "ServiceReasonBean{" +
					"goodsReturnResonId=" + goodsReturnResonId +
					", goodsReturnResonName='" + goodsReturnResonName + '\'' +
					'}';
		}
	}
}
