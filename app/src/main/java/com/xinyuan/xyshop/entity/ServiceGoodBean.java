package com.xinyuan.xyshop.entity;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/7/1.
 */

public class ServiceGoodBean {
	private User_one user_one;
	private Store_one store_one;
	private User_Two user_two;
	private Store_Two store_two;
	private Store_Three store_three;

	@Override
	public String toString() {
		return "ServiceGoodBean{" +
				"user_one=" + user_one +
				", store_one=" + store_one +
				", user_two=" + user_two +
				", store_two=" + store_two +
				", store_three=" + store_three +
				'}';
	}

	public User_one getUser_one() {
		return user_one;
	}

	public void setUser_one(User_one user_one) {
		this.user_one = user_one;
	}

	public Store_one getStore_one() {
		return store_one;
	}

	public void setStore_one(Store_one store_one) {
		this.store_one = store_one;
	}

	public User_Two getUser_two() {
		return user_two;
	}

	public void setUser_two(User_Two user_two) {
		this.user_two = user_two;
	}

	public Store_Two getStore_two() {
		return store_two;
	}

	public void setStore_two(Store_Two store_two) {
		this.store_two = store_two;
	}

	public Store_Three getStore_three() {
		return store_three;
	}

	public void setStore_three(Store_Three store_three) {
		this.store_three = store_three;
	}

	public class Store_Three {
		private String serviceTitle;
		private String servicePrice;
		private String serviceBank;

		@Override
		public String toString() {
			return "Store_Three{" +
					"serviceTitle='" + serviceTitle + '\'' +
					", servicePrice='" + servicePrice + '\'' +
					", serviceBank='" + serviceBank + '\'' +
					'}';
		}

		public String getServiceTitle() {
			return serviceTitle;
		}

		public void setServiceTitle(String serviceTitle) {
			this.serviceTitle = serviceTitle;
		}

		public String getServicePrice() {
			return servicePrice;
		}

		public void setServicePrice(String servicePrice) {
			this.servicePrice = servicePrice;
		}

		public String getServiceBank() {
			return serviceBank;
		}

		public void setServiceBank(String serviceBank) {
			this.serviceBank = serviceBank;
		}
	}


	public class Store_Two {
		private String serviceTitle;

		@Override
		public String toString() {
			return "Store_Two{" +
					"serviceTitle='" + serviceTitle + '\'' +
					'}';
		}

		public String getServiceTitle() {
			return serviceTitle;
		}

		public void setServiceTitle(String serviceTitle) {
			this.serviceTitle = serviceTitle;
		}
	}

	public class User_Two {
		private String serviceTitle;
		private String expressName;
		private String expressNum;

		@Override
		public String toString() {
			return "User_Two{" +
					"serviceTitle='" + serviceTitle + '\'' +
					", expressName='" + expressName + '\'' +
					", expressNum='" + expressNum + '\'' +
					'}';
		}

		public String getServiceTitle() {
			return serviceTitle;
		}

		public void setServiceTitle(String serviceTitle) {
			this.serviceTitle = serviceTitle;
		}

		public String getExpressName() {
			return expressName;
		}

		public void setExpressName(String expressName) {
			this.expressName = expressName;
		}

		public String getExpressNum() {
			return expressNum;
		}

		public void setExpressNum(String expressNum) {
			this.expressNum = expressNum;
		}
	}


	public class Store_one {
		private String service_title;
		private String service_hint;
		private String content;
	}

	public class User_one {
		private String service_title;
		private String service_hint;
		private String order_id;
		private int GoodStatus;
		private String serviceReason;
		private BigDecimal price;

		@Override
		public String toString() {
			return "User_one{" +
					"service_title='" + service_title + '\'' +
					", service_hint='" + service_hint + '\'' +
					", order_id='" + order_id + '\'' +
					", GoodStatus=" + GoodStatus +
					", serviceReason='" + serviceReason + '\'' +
					", price=" + price +
					'}';
		}

		public String getService_title() {
			return service_title;
		}

		public void setService_title(String service_title) {
			this.service_title = service_title;
		}

		public String getService_hint() {
			return service_hint;
		}

		public void setService_hint(String service_hint) {
			this.service_hint = service_hint;
		}

		public String getOrder_id() {
			return order_id;
		}

		public void setOrder_id(String order_id) {
			this.order_id = order_id;
		}

		public int getGoodStatus() {
			return GoodStatus;
		}

		public void setGoodStatus(int goodStatus) {
			GoodStatus = goodStatus;
		}

		public String getServiceReason() {
			return serviceReason;
		}

		public void setServiceReason(String serviceReason) {
			this.serviceReason = serviceReason;
		}

		public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}
	}

}
