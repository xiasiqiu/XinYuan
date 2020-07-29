package com.xinyuan.xyshop.model;

import java.io.Serializable;

/**
 * Created by fx on 2017/9/1.
 * APP升级数据
 */

public class UpdateModel implements Serializable {
	private static final long serialVersionUID = 2711782343783532184L;
	// app名字
	private String appName;
	// 是否更新
	private Boolean isUpdate;
	//服务器版本
	private String serverVersion;
	//服务器标志
	private String serverFlag;
	//强制升级
	private Boolean lastForce;
	//app最新版本地址
	private String updateUrl;
	//升级信息
	private String upgradeInfo;
	//更新时间
	private String upadteTime;
	//更新apk大小
	private String updateSize;

	public String getAppName() {
		return appName;
	}

	public Boolean getUpdate() {
		return isUpdate;
	}

	public String getServerVersion() {
		return serverVersion;
	}

	public String getServerFlag() {
		return serverFlag;
	}

	public Boolean getLastForce() {
		return lastForce;
	}

	public String getUpdateUrl() {
		return updateUrl;
	}

	public String getUpgradeinfo() {
		return upgradeInfo;
	}

	public String getUpadteTime() {
		return upadteTime;
	}

	@Override
	public String toString() {
		return "{\"appName\":\""+appName+"\","+
				"\"serverVersion\":\""+serverVersion+"\"," +
				"\"serverFlag\":\""+serverFlag+"\"," +
				"\"lastForce\":"+lastForce+"," +
				"\"updateUrl\":\""+updateUrl+"\"," +
				"\"updateSize\":\""+updateSize+"\"," +
				"\"upgradeInfo\":\""+upgradeInfo+"\"," +
				"\"isUpdate\":"+isUpdate+"," +
				"\"upadteTime\":"+"\""+upadteTime+"\""+"}";
	}
}
