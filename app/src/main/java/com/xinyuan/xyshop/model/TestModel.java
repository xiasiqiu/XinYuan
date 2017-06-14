package com.xinyuan.xyshop.model;

/**
 * Created by Administrator on 2017/6/14.
 */

public class TestModel {

	private int code;
	private GoodDetail datas;

	public int getCode() {
		return code;
	}

	public GoodDetail getDatas() {
		return datas;
	}

	@Override
	public String toString() {
		return "TestModel{" +
				"code=" + code +
				", datas=" + datas +
				'}';
	}
}
