package com.xinyuan.xyshop.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/5.
 */

public class XyResponse<T> implements Serializable {

	private static final long serialVersionUID = 5213230387175987834L;

	public int code;
	public T data;


}
