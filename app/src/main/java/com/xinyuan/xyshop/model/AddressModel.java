package com.xinyuan.xyshop.model;

import com.xinyuan.xyshop.bean.AddressBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/7/26.
 * 收货地址列表数据
 *
 */

public class AddressModel implements Serializable {
	private static final long serialVersionUID = -3485495333389752839L;
	private List<AddressBean> addressList;

	public void setGoodsList(List<AddressBean> goodsList) {
		this.addressList = goodsList;
	}

	public List<AddressBean> getAddressList()

	{
		return addressList;
	}
}
