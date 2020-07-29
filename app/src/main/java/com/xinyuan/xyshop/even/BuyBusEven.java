package com.xinyuan.xyshop.even;

/**
 * Created by fx on 2017/9/6.
 * 提交订单消息
 */

public class BuyBusEven {

	public static final String chosesInvoice = "chosesInvoice";
	public static final String choeseRedVoucher = "choeseRedVoucher";
	public static final String choeseCouponVoucher = "choeseCouponVoucher";
	public static final String choeseExpress = "choeseExpress";
	private String flag;
	private Object obj;

	public BuyBusEven(String flag, Object obj) {
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
