package com.xinyuan.xyshop.base;

/**
 * Created by Administrator on 2017/7/4.
 */

public class EventObj {
	public static String CARTDATA = "cartData";
	public static String CARTREFRESH = "cartRefresh";
	public static String IMMESSAGEGETSUCCESS = "imMessageGetSuccess";
	public static String IMMESSAGESENDSUCCESS = "imMesageSendSuccess";
	public static String ORDERREFUNDSENDDELAY = "orderDelay";
	public static String REDPACKAGEGETSUCCESS = "get";
	public static String TRYINDEX = "tryIndex";
	private String flag;
	private Object obj;

	public EventObj(String flag) {
		this.flag = flag;
	}

	public EventObj(String flag, Object obj) {
		this.flag = flag;
		this.obj = obj;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Object getObj() {
		return this.obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
