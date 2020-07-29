package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by fx on 2017/8/7.
 * 聊天列表消息实体类
 */

public class ChatListItemBean implements Serializable {
	private static final long serialVersionUID = -78295056646903272L;
	private String chatting_addTime;
	private int chatting_mark;
	private String user_image;
	private String chatting_content;
	private long chatting_id;
	private String chatting_userName;
	private long chatting_touser_id;
	private long chatting_storeId;

	public long getChatting_storeId() {
		return chatting_storeId;
	}

	public void setChatting_storeId(long chatting_storeId) {
		this.chatting_storeId = chatting_storeId;
	}


	public String getChatting_addTime() {
		return chatting_addTime;
	}

	public int getChatting_mark() {
		return chatting_mark;
	}

	public String getChatting_image() {
		return user_image;
	}

	public String getChatting_content() {
		return chatting_content;
	}

	public long getChatting_id() {
		return chatting_id;
	}

	public String getUserName() {
		return chatting_userName;
	}

	public long getChatting_touser_id() {
		return chatting_touser_id;
	}
}
