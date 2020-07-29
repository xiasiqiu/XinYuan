package com.xinyuan.xyshop.model;

import java.io.Serializable;

/**
 * Created by fx on 2017/9/11.
 */

public class BuyExpressModel  implements Serializable{
	private static final long serialVersionUID = 5065485278550480527L;
	private String mail;
	private String express;
	private String seller;
	private String ems;
	private String choses;



	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setExpress(String express) {
		this.express = express;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public void setEms(String ems) {
		this.ems = ems;
	}

	public String getChoses() {
		return choses;
	}

	public void setChoses(String choses) {
		this.choses = choses;
	}

	public String getMail() {
		return mail;
	}

	public String getExpress() {
		return express;
	}

	public String getSeller() {
		return seller;
	}

	public String getEms() {
		return ems;
	}

	@Override
	public String toString() {
		return "BuyExpressModel{" +
				"mail='" + mail + '\'' +
				", express='" + express + '\'' +
				", seller='" + seller + '\'' +
				", ems='" + ems + '\'' +
				", choses='" + choses + '\'' +
				'}';
	}
}
