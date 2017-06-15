package com.xinyuan.xyshop.ui.goods;

/**
 * Created by Administrator on 2017/6/2.
 */

public class GoodBusBean {
	public static String SelectedGoods = "selectedGoods";
	public static String GoodEvaluate = "goodEvaluate";
	public static String SelectAdapter = "adapter";

	private String flag;
	private Object obj;

	public GoodBusBean(String flag) {
		this.flag = flag;
	}

	public GoodBusBean(String flag, Object obj) {
		this.flag = flag;
		this.obj = obj;
	}

	public static String getGoodEvaluate() {
		return GoodEvaluate;
	}

	public static void setGoodEvaluate(String goodEvaluate) {
		GoodEvaluate = goodEvaluate;
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
