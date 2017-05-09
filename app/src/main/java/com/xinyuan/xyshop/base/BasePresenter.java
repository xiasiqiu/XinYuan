package com.xinyuan.xyshop.base;

/**
 * Created by fx on 2017/5/2 0002.
 */

public interface BasePresenter<T extends BaseView> {

	String TAG = "myTag";

	/**
	 * 初始化操作
	 */
	void initData();

}



