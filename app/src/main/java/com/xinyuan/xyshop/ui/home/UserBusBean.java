package com.xinyuan.xyshop.ui.home;

/**
 * Created by Administrator on 2017/6/20.
 */

public class UserBusBean {
	public static String UserId = "uerId";
	public static String HomeIndex = "homeIndex";


	private String flag;
	private Object obj;

	public UserBusBean(String flag) {
		this.flag = flag;
	}

	public UserBusBean(String flag, Object obj) {
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
