package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by fx on 2017/7/20.
 * 积分动态
 *
 */

public class CreditNewsBean implements Serializable {
	private static final long serialVersionUID = -2653075234238374076L;

	private String userName;
	private String Content;
	private String time;

	@Override
	public String toString() {
		return "NewsBean{" +
				"userName='" + userName + '\'' +
				", Content='" + Content + '\'' +
				", time='" + time + '\'' +
				'}';
	}


	public String getUserName() {
		return userName;
	}

	public String getContent() {
		return Content;
	}

	public String getTime() {
		return time;
	}
}
