package com.xinyuan.xyshop.model;

import com.xinyuan.xyshop.bean.ChatItemBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/8/7.
 * 聊天详情数据
 */

public class ChatDetailModule implements Serializable {
	private static final long serialVersionUID = -8276374088310043416L;
	private List<ChatItemBean> chattingList;
	private long logId;

	public List<ChatItemBean> getChattingList() {
		return chattingList;
	}

	public long getLogId() {
		return logId;
	}

	@Override
	public String toString() {
		return "ChatDetailModule{" +
				"chattingList=" + chattingList +
				", logId=" + logId +
				'}';
	}
}
