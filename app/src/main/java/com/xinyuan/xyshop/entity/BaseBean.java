package com.xinyuan.xyshop.entity;

/**
 * Created by Administrator on 2017/7/14.
 */

public class BaseBean<T> {
	private int code;
	private T datas;

	public T getDatas() {
		return datas;
	}
}
