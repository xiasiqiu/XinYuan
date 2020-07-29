package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/28.
 */

public class ServiceGoodDetailBean implements Serializable {
	private static final long serialVersionUID = 8352795798923626330L;
	private String countdown;
	private String handlingTime;
	private int role;
	private String log;
	private int step;
	private String info;

	public String getCountdown() {
		return countdown;
	}

	public String getHandlingTime() {
		return handlingTime;
	}

	public int getRole() {
		return role;
	}

	public String getLog() {
		return log;
	}

	public int getStep() {
		return step;
	}

	public String getInfo() {
		return info;
	}

	@Override
	public String toString() {
		return "ServiceGoodDetailBean{" +
				"countdown='" + countdown + '\'' +
				", handlingTime='" + handlingTime + '\'' +
				", role=" + role +
				", log='" + log + '\'' +
				", step=" + step +
				", info='" + info + '\'' +
				'}';
	}
}
