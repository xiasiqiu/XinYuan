package com.xinyuan.xyshop.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/6/26.
 * 足迹数据实体类
 */

public class FollowBean implements Serializable {
	private static final long serialVersionUID = 7864340384885038886L;
	private String storeImage;
	private String storeName;
	private List<String> storeLevel;

	public FollowBean(String storeImage, String storeName, List<String> storeLevel) {
		this.storeImage = storeImage;
		this.storeName = storeName;
		this.storeLevel = storeLevel;
	}

	public String getStoreImage() {
		return storeImage;
	}

	public String getStoreName() {
		return storeName;
	}

	public List<String> getStoreLevel() {
		return storeLevel;
	}
}
