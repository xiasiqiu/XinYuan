package com.xinyuan.xyshop.bean;

import java.io.Serializable;
import java.lang.ref.PhantomReference;

/**
 * Created by fx on 2017/7/10
 * 收货地址实体类
 */

public class AddressBean implements Serializable {
	private static final long serialVersionUID = 7988110915135603955L;
	private int addressId;
	private long areaId;


	private String userName;
	private String mobile;
	private int isDefault;
	private String address;
	private String addressInfo;


	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public long getAreaId() {
		return areaId;
	}

	public void setAreaId(long areaId) {
		this.areaId = areaId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(String addressInfo) {
		this.addressInfo = addressInfo;
	}
}
