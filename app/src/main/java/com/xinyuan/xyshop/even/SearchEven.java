package com.xinyuan.xyshop.even;

/**
 * Created by fx on 2017/9/18.
 * 搜索筛选消息
 */

public class SearchEven {

	public  static final  String SelectValue="SelectValue";
	public static final  String EmptSelect="EmptySelect";
	private String flag;
	private Object obj;

	public SearchEven(String flag, Object obj) {
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
