package com.xinyuan.xyshop.entity;

/**
 * Created by fx on 2017/5/31.
 * 购物车店铺操作
 */

public class StoreInfo {
	public String Id;
	public String name;
	public boolean isChoosed;
	public boolean isEdtor;
	public StoreInfo(String id, String name) {
		Id = id;
		this.name = name;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isChoosed() {
		return isChoosed;
	}

	public void setChoosed(boolean choosed) {
		isChoosed = choosed;
	}

	public boolean getIsEdtor() {
		return isEdtor;
	}

	public void setIsEdtor(boolean edtor) {
		isEdtor = edtor;
	}

	public boolean isEdtor() {
		return isEdtor;
	}

	@Override
	public String toString() {
		return "SotreInfo{" +
				"Id='" + Id + '\'' +
				", name='" + name + '\'' +
				", isChoosed=" + isChoosed +
				", isEdtor=" + isEdtor +
				'}';
	}
}
