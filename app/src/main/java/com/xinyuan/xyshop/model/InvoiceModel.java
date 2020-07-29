package com.xinyuan.xyshop.model;

import com.xinyuan.xyshop.bean.InvoiceContentBean;
import com.xinyuan.xyshop.bean.InvoiceHeaderBean;
import com.xinyuan.xyshop.bean.InvoiceSpecBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/9/6.
 */

public class InvoiceModel implements Serializable {
	private static final long serialVersionUID = -3123359563156255956L;
	private List<InvoiceHeaderBean> headers;
	private List<InvoiceContentBean> contents;
	private List<InvoiceSpecBean> aptitudes;

	public List<InvoiceHeaderBean> getHeaders() {
		return headers;
	}

	public List<InvoiceContentBean> getContents() {
		return contents;
	}

	public List<InvoiceSpecBean> getAptitudes() {
		return aptitudes;
	}

	public void setHeaders(List<InvoiceHeaderBean> headers) {
		this.headers = headers;
	}

	public void setContents(List<InvoiceContentBean> contents) {
		this.contents = contents;
	}

	public void setAptitudes(List<InvoiceSpecBean> aptitudes) {
		this.aptitudes = aptitudes;
	}

	@Override
	public String toString() {
		return "InvoiceModel{" +
				"headers=" + headers +
				", contents=" + contents +
				", aptitudes=" + aptitudes +
				'}';
	}
}
