package com.xinyuan.xyshop.even;

/**
 * Created by fx on 2017/9/5.
 * 订单操作消息
 */

public class OrderBusEven {
	public static String ToOrderDetail = "ToOrderDetail";
	public static String ToServiceGood = "ToServiceGood";
	public static String ToServiceMoneyDetail = "ToServiceMoneyDetail";
	public static String ToOrderLogistic = "ToOrderLogistic";
	public static String ToOrderPay = "ToOrderPay";

	public static String ToOrderRefresh = "ToOrderRefresh";
	private String flag;
	private Object obj;

	public OrderBusEven(String flag) {
		this.flag = flag;
	}

	public OrderBusEven(String flag, Object obj) {
		this.flag = flag;
		this.obj = obj;
	}


	public String getFlag() {
		return flag;
	}

	public Object getObj() {
		return obj;
	}

	@Override
	public String toString() {
		return "OrderBusEven{" +
				"flag='" + flag + '\'' +
				", obj=" + obj +
				'}';
	}
}
