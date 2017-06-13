package com.xinyuan.xyshop.callback;

/**
 * Created by Administrator on 2017/6/13.
 */

public interface SKUInterface {
	/**
	 * 选中属性
	 *
	 * @param attr
	 */
	void selectedAttribute(String[] attr);


	/**
	 * 取消属性选择
	 *
	 * @param attr
	 */
	void uncheckAttribute(String[] attr);
}
