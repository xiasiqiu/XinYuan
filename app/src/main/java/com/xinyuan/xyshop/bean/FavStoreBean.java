package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by fx on 2017/7/25.
 * 收藏店铺数据类
 */

public class FavStoreBean implements Serializable {
	private static final long serialVersionUID = -2264769465496913314L;
	private String storeGradeLevel;
	private String storeName;
	private String storeGradeName;
	private String storeTime;
	private String storeLogo;
	private long storeId;
	private long fId;

	public long getFollowId() {
		return fId;
	}

	public String getStoreGradeLevel() {
		return storeGradeLevel;
	}

	public String getStoreName() {
		return storeName;
	}

	public String getStoreGradeName() {
		return storeGradeName;
	}

	public String getStoreTime() {
		return storeTime;
	}

	public String getStoreLogo() {
		return storeLogo;
	}

	public long getStoreId() {
		return storeId;
	}
}
