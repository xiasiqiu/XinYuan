package com.xinyuan.xyshop.even;

/**
 * Created by Administrator on 2017/9/26.
 */

public class OrderPageRefresh {
	public OrderPageRefresh(boolean Refresh) {
		this.refresh = Refresh;
	}

	private boolean refresh;

	public boolean isRefresh() {
		return refresh;
	}

	public void setRefresh(boolean refresh) {
		this.refresh = refresh;
	}
}
