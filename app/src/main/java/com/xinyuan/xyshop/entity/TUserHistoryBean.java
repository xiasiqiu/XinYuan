package com.xinyuan.xyshop.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */

public class TUserHistoryBean implements Serializable {
	private static final long serialVersionUID = 8426790057905396537L;
	private int code;
	private List<UserHistoryBean> datas;


	public int getCode() {
		return code;
	}

	public List<UserHistoryBean> getHistoryBean() {
		return datas;
	}
}
