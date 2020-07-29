package com.xinyuan.xyshop.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by fx on 2017/9/6.
 * 红包数据
 */

public class RedPacketBean implements Serializable {
	private static final long serialVersionUID = 1803925503767975491L;
	private String redpacketName;
	private String redpacketStoreId;
	private long redpacketInfoId;
	private boolean isCheck;
	private BigDecimal prices;



	public void setRedpacketName(String redpacketName) {
		this.redpacketName = redpacketName;
	}

	public void setRedpacketStoreId(String redpacketStoreId) {
		this.redpacketStoreId = redpacketStoreId;
	}

	public void setRedpacketInfoId(long redpacketInfoId) {
		this.redpacketInfoId = redpacketInfoId;
	}

	public BigDecimal getPrice() {
		return prices;
	}

	public void setPrice(BigDecimal price) {
		this.prices = price;
	}

	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean check) {
		isCheck = check;
	}

	public String getRedpacketName() {
		return redpacketName;
	}

	public String getRedpacketStoreId() {
		return redpacketStoreId;
	}

	public long getRedpacketInfoId() {
		return redpacketInfoId;
	}

	@Override
	public String toString() {
		return "RedPacketBean{" +
				"redpacketName='" + redpacketName + '\'' +
				", redpacketStoreId='" + redpacketStoreId + '\'' +
				", redpacketInfoId=" + redpacketInfoId +
				", isCheck=" + isCheck +
				", prices=" + prices +
				'}';
	}
}
