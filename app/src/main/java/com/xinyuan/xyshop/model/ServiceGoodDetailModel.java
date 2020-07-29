package com.xinyuan.xyshop.model;

import com.xinyuan.xyshop.bean.ServiceGoodDetailBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/9/28.
 */

public class ServiceGoodDetailModel implements Serializable {
	private static final long serialVersionUID = -948486501962172432L;
	private List<ServiceGoodDetailBean> infoList;

	public List<ServiceGoodDetailBean> getInfoList() {
		return infoList;
	}

	@Override
	public String toString() {
		return "ServiceGoodDetailModel{" +
				"infoList=" + infoList +
				'}';
	}
}
