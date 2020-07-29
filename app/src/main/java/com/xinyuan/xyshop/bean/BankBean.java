package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by fx on 2017/9/21.
 * 账户银行卡
 */

public class BankBean implements Serializable {
	private static final long serialVersionUID = -5656422238781194070L;
	private String bankName;
	private long defaultOption;
	private String accountNumber;


	public String getAccountNumber() {
		return accountNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public long getDefaultOption() {
		return defaultOption;
	}
}
