package com.xinyuan.xyshop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/3.
 */

public class ExpressBean implements Serializable {
	private static final long serialVersionUID = 8550005751828936591L;
	private String message;
	private String status;
	private String state;
	private String isCheck;
	private String condition;
	private String com;
	private String nu;
	private String phone;
	private List<ExpressInfo> data;

	@Override
	public String toString() {
		return "ExpressBean{" +
				"message='" + message + '\'' +
				", status='" + status + '\'' +
				", State='" + state + '\'' +
				", isCheck='" + isCheck + '\'' +
				", condition='" + condition + '\'' +
				", com='" + com + '\'' +
				", nu='" + nu + '\'' +
				", phone='" + phone + '\'' +
				",}";
	}

	public String getMessage() {
		return message;
	}

	public String getStatus() {
		return status;
	}

	public String getState() {
		return state;
	}

	public String getIsCheck() {
		return isCheck;
	}

	public String getCondition() {
		return condition;
	}

	public String getCom() {
		return com;
	}

	public String getNu() {
		return nu;
	}

	public String getPhone() {
		return phone;
	}

	public List<ExpressInfo> getData() {
		return data;
	}

	public class ExpressInfo implements Serializable {
		private static final long serialVersionUID = 4466352367080704113L;
		private String time;
		private String context;
		private String ftime;

		public String getFtime() {
			return ftime;
		}

		public void setFtime(String ftime) {
			this.ftime = ftime;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getContext() {
			return context;
		}

		public void setContext(String context) {
			this.context = context;
		}
	}
}
