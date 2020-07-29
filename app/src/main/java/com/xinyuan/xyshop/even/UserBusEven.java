package com.xinyuan.xyshop.even;

/**
 * Created by fx on 2017/6/20.
 *  用户消息
 */

public class UserBusEven {
	public static String UserId = "uerId";
	public static String HomeIndex = "homeIndex";


	private String flag;
	private Object obj;

	public UserBusEven(String flag) {
		this.flag = flag;
	}

	public UserBusEven(String flag, Object obj) {
		this.flag = flag;
		this.obj = obj;
	}


	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
