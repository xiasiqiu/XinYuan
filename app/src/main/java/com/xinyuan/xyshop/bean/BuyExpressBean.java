package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by fx on 2017/9/12.
 * 提交订单邮费
 */

public class BuyExpressBean implements Serializable{
	private static final long serialVersionUID = 2220655011630783043L;
	private String type;
	private String content;
	private Boolean isCheck;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getCheck() {
		return isCheck;
	}

	public void setCheck(Boolean check) {
		isCheck = check;
	}

	@Override
	public String toString() {
		return "BuyExpressBean{" +
				"type='" + type + '\'' +
				", content='" + content + '\'' +
				", isCheck=" + isCheck +
				'}';
	}
}
