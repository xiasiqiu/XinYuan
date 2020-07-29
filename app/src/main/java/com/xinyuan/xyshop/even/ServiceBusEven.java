package com.xinyuan.xyshop.even;

/**
 * Created by Administrator on 2017/9/26.
 */

public class ServiceBusEven {
	private String flag;
	private Object obj;



	public static final String ToServiceMoneyDetail = "ToServiceMoneyDetail";
	public static final String ToServiceGoodDetail = "ToServiceGoodDetail";

	public ServiceBusEven(String flag, Object obj) {
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
