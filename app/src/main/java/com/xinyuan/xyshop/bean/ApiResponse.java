package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/12.
 */

public class ApiResponse<T> implements Serializable{

	private static final long serialVersionUID = 5211130387175982834L;

	public int code;
	public String datas;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDatas() {
		return datas;
	}

	public void setDatas(String datas) {
		this.datas = datas;
	}

	@Override
	public String toString() {
		return "ApiResponse{" +
				"code=" + code +
				", datas='" + datas + '\'' +
				'}';
	}
}
