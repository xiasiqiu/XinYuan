package com.xinyuan.xyshop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2017/6/20.
 */

public class OrderModel implements Serializable {


	private static final long serialVersionUID = 6369893594565596187L;
	private List<OrderBean> orderList;
	private int pages;
	private int skip;
	private int limit;

	public List<OrderBean> getOrderList() {
		return orderList;
	}

	public int getPages() {
		return pages;
	}

	public int getSkip() {
		return skip;
	}

	public int getLimit() {
		return limit;
	}

	public class OrderBean implements Serializable {
		private static final long serialVersionUID = -7305744274572894506L;
		private int orderStatus;
		private long orderID;
		private String createTime;
		private String storeName;
		private long storePhone;
		private int ordergoodNum;
		private int storeId;
		private int orderPrice;
		private int orderExtra;
		private String closeTime;
		private String address;
		private String receiver;
		private long phoneNum;
		private List<OrderGood> goodsList;

		public String getCloseTime() {
			return closeTime;
		}

		public long getStorePhone() {
			return storePhone;
		}

		public int getOrdergoodNum() {
			return ordergoodNum;
		}

		public int getOrderStatus() {
			return orderStatus;
		}

		public long getOrderID() {
			return orderID;
		}

		public String getCreateTime() {
			return createTime;
		}

		public String getStoreName() {
			return storeName;
		}

		public int getStoreId() {
			return storeId;
		}

		public int getOrderPrice() {
			return orderPrice;
		}

		public int getOrderExtra() {
			return orderExtra;
		}

		public String getAddress() {
			return address;
		}

		public String getReceiver() {
			return receiver;
		}

		public long getPhoneNum() {
			return phoneNum;
		}

		public List<OrderGood> getGoodsList() {
			return goodsList;
		}

		public class OrderGood implements Serializable {
			private static final long serialVersionUID = -8020880899109809687L;
			private int goodId;
			private String goodName;
			private int goodNum;
			private String goodSpecText;
			private int goodPrice;
			private int goodOldPrice;
			private String goodImg;
			private int goodServiceStatus;

			public int getGoodId() {
				return goodId;
			}

			public String getGoodName() {
				return goodName;
			}

			public int getGoodNum() {
				return goodNum;
			}

			public String getGoodSpecText() {
				return goodSpecText;
			}

			public int getGoodPrice() {
				return goodPrice;
			}

			public int getGoodOldPrice() {
				return goodOldPrice;
			}

			public String getGoodImg() {
				return goodImg;
			}

			public int getGoodServiceStatus() {
				return goodServiceStatus;
			}
		}
	}
}
