package com.xinyuan.xyshop.model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2017/6/22.
 */

public class TestCreditModel implements Serializable {
	private static final long serialVersionUID = 2929316496114885316L;
	private int code;
	private CreditModel mallData;


	public int getCode() {
		return code;
	}

	public CreditModel getMallData() {
		return mallData;
	}
}
