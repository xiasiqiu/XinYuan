package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by fx on 2017/9/6.
 * 发票类型
 */
public class InvoiceSpecBean implements Serializable {
	private static final long serialVersionUID = -339813443015664167L;
	private long aptitudeId;
	private String backName;
	private String invoiceUnit;
	private String account;
	private String registerAddress;
	private String registerPhone;
	private String identificationNumber;
	private boolean isCheck;

	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean check) {
		isCheck = check;
	}

	public void setAptitudeId(long aptitudeId) {
		this.aptitudeId = aptitudeId;
	}

	public void setInvoiceUnit(String invoiceUnit) {
		this.invoiceUnit = invoiceUnit;
	}

	public long getAptitudeId() {
		return aptitudeId;
	}

	public String getInvoiceUnit() {
		return invoiceUnit;
	}
}
