package com.xinyuan.xyshop.entity;

import java.io.Serializable;

/**
 * Created by fx on 2017/5/3 0003.
 */

public class SimpleResponse implements Serializable {

	private static final long serialVersionUID = -1477609349345966116L;

	public int code;


	public LzyResponse toLzyResponse() {
		LzyResponse lzyResponse = new LzyResponse();
		lzyResponse.code = code;
		return lzyResponse;
	}
}
