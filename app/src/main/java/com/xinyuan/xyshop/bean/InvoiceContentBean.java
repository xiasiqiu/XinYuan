package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by fx on 2017/9/6.
 * 发票明细
 */

public class InvoiceContentBean implements Serializable {
	private static final long serialVersionUID = 8013149646992529182L;
	private String contentName;
	private long contentId;
	private boolean isCheck;
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}

	public void setContentId(long contentId) {
		this.contentId = contentId;
	}

	public String getContentName() {
		return contentName;
	}

	public long getContentId() {
		return contentId;
	}

	public void setCheck(boolean check) {
		isCheck = check;
	}

	public boolean isCheck() {
		return isCheck;
	}
}

