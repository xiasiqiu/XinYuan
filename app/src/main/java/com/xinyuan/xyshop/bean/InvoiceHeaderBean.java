package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by fx on 2017/9/6.
 * 发票抬头
 */
public class InvoiceHeaderBean implements Serializable {
	private static final long serialVersionUID = -6842946318427737283L;
	private long headerId;
	private String headerName;


	public long getHeaderId() {
		return headerId;
	}

	public String getHeaderName() {
		return headerName;
	}

	public void setHeaderId(long headerId) {
		this.headerId = headerId;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

}
