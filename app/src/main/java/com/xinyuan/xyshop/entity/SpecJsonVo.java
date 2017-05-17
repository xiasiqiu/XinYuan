package com.xinyuan.xyshop.entity;

import java.util.List;

public class SpecJsonVo {
	private int specId;
	private String specName;
	private List<SpecValueListVo> specValueList;

	public int getSpecId() {
		return this.specId;
	}

	public void setSpecId(int specId) {
		this.specId = specId;
	}

	public String getSpecName() {
		return this.specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public List<SpecValueListVo> getSpecValueList() {
		return this.specValueList;
	}

	public void setSpecValueList(List<SpecValueListVo> specValueList) {
		this.specValueList = specValueList;
	}
}
