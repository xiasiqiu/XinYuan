package com.xinyuan.xyshop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */

public class StoreInfoBean implements Serializable {
	private static final long serialVersionUID = 6216648327286094261L;
	private int storeId;
	private int storeLevel;
	private String storeLogo;
	private String bannerBg;
	private String storeType;
	private String storename;
	private String fansNum;
	private double storeScore;
	private double serviceScore;
	private double logisticsScore;
	private List<String> storesign;
	private String storeAddress;
	private String createTime;
	private String storePhone;
	private String workTime;
	private int followStatus;

	@Override
	public String toString() {
		return "StoreInfoBean{" +
				"storeId=" + storeId +
				", storeLevel=" + storeLevel +
				", storeLogo='" + storeLogo + '\'' +
				", bannerBg='" + bannerBg + '\'' +
				", storeType='" + storeType + '\'' +
				", storename='" + storename + '\'' +
				", fansNum='" + fansNum + '\'' +
				", storeScore=" + storeScore +
				", serviceScore=" + serviceScore +
				", logisticsScore=" + logisticsScore +
				", storesign=" + storesign +
				", storeAddress='" + storeAddress + '\'' +
				", createTime='" + createTime + '\'' +
				", storePhone='" + storePhone + '\'' +
				", workTime='" + workTime + '\'' +
				", followStatus=" + followStatus +
				'}';
	}

	public int getStoreId() {
		return storeId;
	}

	public int getStoreLevel() {
		return storeLevel;
	}

	public String getStoreLogo() {
		return storeLogo;
	}

	public String getBannerBg() {
		return bannerBg;
	}

	public String getStoreType() {
		return storeType;
	}

	public String getStorename() {
		return storename;
	}

	public String getFansNum() {
		return fansNum;
	}

	public double getStoreScore() {
		return storeScore;
	}

	public double getServiceScore() {
		return serviceScore;
	}

	public double getLogisticsScore() {
		return logisticsScore;
	}

	public List<String> getStoresign() {
		return storesign;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public String getCreateTime() {
		return createTime;
	}

	public String getStorePhone() {
		return storePhone;
	}

	public String getWorkTime() {
		return workTime;
	}

	public int isFollowStatus() {
		return followStatus;
	}
}
