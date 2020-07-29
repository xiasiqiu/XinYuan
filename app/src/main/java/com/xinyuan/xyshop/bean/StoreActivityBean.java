package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by fx on 2017/8/31.
 * 店铺活动数据
 */

public class StoreActivityBean implements Serializable{
	private static final long serialVersionUID = 6692464375436856223L;
	private String group_name;
	private String groupId;
	private String group_content;
	private String status;
	private String beginTime;
	private String joinEndTime;
	private String endTime;
	private String group_vice_name;
	private String addTime;

	public String getGroup_name() {
		return group_name;
	}

	public String getGroupId() {
		return groupId;
	}

	public String getGroup_content() {
		return group_content;
	}

	public String getStatus() {
		return status;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public String getJoinEndTime() {
		return joinEndTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public String getGroup_vice_name() {
		return group_vice_name;
	}

	public String getAddTime() {
		return addTime;
	}
}
