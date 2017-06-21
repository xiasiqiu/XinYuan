package com.xinyuan.xyshop.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/21.
 */

public class TestEvaluateModel implements Serializable {
	private static final long serialVersionUID = -253852367719802484L;
	private int code;
	private EvaluateModel datas;

	public int getCode() {
		return code;
	}

	public EvaluateModel getDatas() {
		return datas;
	}
}
