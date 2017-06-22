package com.xinyuan.xyshop.entity;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public abstract class XYEntity<T> {
	public boolean isHeader;
	public T t;
	public T i;
	public String header;

	public XYEntity(boolean isHeader, T i) {
		this.isHeader = isHeader;
		this.i = i;
	}

	public XYEntity(T t) {
		this.isHeader = false;
		this.header = null;
		this.t = t;
	}
}
