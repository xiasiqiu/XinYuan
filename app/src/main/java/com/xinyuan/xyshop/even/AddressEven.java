package com.xinyuan.xyshop.even;

/**
 * Created by fx on 2017/7/6.
 * 收货地址消息
 */

public class AddressEven {
	public boolean update;
	public int position;

	public AddressEven(int position, boolean update) {
		this.position = position;
		this.update = update;
	}

	public boolean isUpdate() {
		return update;
	}

	public int getPosition() {
		return position;
	}
}
