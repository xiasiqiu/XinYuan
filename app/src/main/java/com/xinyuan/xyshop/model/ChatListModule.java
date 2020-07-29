package com.xinyuan.xyshop.model;

import com.xinyuan.xyshop.bean.ChatListItemBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/8/4.
 * 聊天消息列表数据
 */

public class ChatListModule implements Serializable{

	private static final long serialVersionUID = -6906740891283292146L;
	private List<ChatListItemBean> chattingList;

	public List<ChatListItemBean> getChattingList() {
		return chattingList;
	}

	@Override
	public String toString() {
		return "ChatListModule{" +
				"chattingList=" + chattingList +
				'}';
	}
}
