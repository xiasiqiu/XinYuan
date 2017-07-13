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
	private String storeScoreText;
	private String serviceScoreText;
	private String logisticsScoreText;

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public void setStoreLevel(int storeLevel) {
		this.storeLevel = storeLevel;
	}

	public void setStoreLogo(String storeLogo) {
		this.storeLogo = storeLogo;
	}

	public void setBannerBg(String bannerBg) {
		this.bannerBg = bannerBg;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	public void setFansNum(String fansNum) {
		this.fansNum = fansNum;
	}

	public void setStoreScore(double storeScore) {
		this.storeScore = storeScore;
	}

	public void setServiceScore(double serviceScore) {
		this.serviceScore = serviceScore;
	}

	public void setLogisticsScore(double logisticsScore) {
		this.logisticsScore = logisticsScore;
	}

	public void setStoresign(List<String> storesign) {
		this.storesign = storesign;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public void setFollowStatus(int followStatus) {
		this.followStatus = followStatus;
	}

	public void setStoreScoreText(String storeScoreText) {
		this.storeScoreText = storeScoreText;
	}

	public void setServiceScoreText(String serviceScoreText) {
		this.serviceScoreText = serviceScoreText;
	}

	public void setLogisticsScoreText(String logisticsScoreText) {
		this.logisticsScoreText = logisticsScoreText;
	}

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
				", storeScoreText='" + storeScoreText + '\'' +
				", serviceScoreText='" + serviceScoreText + '\'' +
				", logisticsScoreText='" + logisticsScoreText + '\'' +
				'}';
	}

	public int getFollowStatus() {
		return followStatus;
	}

	public String getStoreScoreText() {
		return storeScoreText;
	}

	public String getServiceScoreText() {
		return serviceScoreText;
	}

	public String getLogisticsScoreText() {
		return logisticsScoreText;
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
