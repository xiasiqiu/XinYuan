package com.xinyuan.xyshop.even;

/**
 * Created by Administrator on 2017/10/9.
 */

public class StorePageEven {
	private String flag;
	private Object obj;
	public static final String StoreIntroduceFragment = "StoreIntroduceFragment";

	public StorePageEven(String flag, Object obj) {
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
