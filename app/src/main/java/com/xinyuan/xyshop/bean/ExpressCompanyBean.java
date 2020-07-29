package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/28.
 */

public class ExpressCompanyBean implements Serializable{
	private static final long serialVersionUID = 1656771266457946728L;
	private String expressCompanyName;
	private long expressCompanyId;

	public String getExpressCompanyName() {
		return expressCompanyName;
	}

	public long getExpressCompanyId() {
		return expressCompanyId;
	}
}
