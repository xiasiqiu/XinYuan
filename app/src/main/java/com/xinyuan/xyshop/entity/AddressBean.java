package com.xinyuan.xyshop.entity;

import java.io.Serializable;
import java.lang.ref.PhantomReference;

/**
 * Created by Administrator on 2017/7/10.
 */

public class AddressBean implements Serializable {
	private static final long serialVersionUID = 7988110915135603955L;
	private int AddressId;
	private String name;
	private String phone;
	private boolean isDefault;
	private int locaionId;
	private String areaName;
	private String streetName;

	public AddressBean(int addressId, String name, String phone, boolean isDefault, int locaionId, String areaName, String streetName) {
		AddressId = addressId;
		this.name = name;
		this.phone = phone;
		this.isDefault = isDefault;
		this.locaionId = locaionId;
		this.areaName = areaName;
		this.streetName = streetName;
	}

	public int getAddressId() {
		return AddressId;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public int getLocaionId() {
		return locaionId;
	}

	public String getAreaName() {
		return areaName;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setDefault(boolean aDefault) {
		isDefault = aDefault;
	}
}
