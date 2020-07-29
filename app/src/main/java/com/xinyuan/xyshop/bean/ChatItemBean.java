package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by fx on 2017/8/4.
 * 聊天消息实体类
 */

public class ChatItemBean implements Serializable {
	private static final long serialVersionUID = -7721485989552922436L;
	private long chatting_touser_id;
	private long chattinglog_id;
	private long chatting_id;
	private String chatting_content;
	private String chatting_image;
	private int chatting_mark;
	private String chatting_addTime;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public long getChatting_user() {
		return chatting_touser_id;
	}

	public long getChattinglog_id() {
		return chattinglog_id;
	}

	public long getChatting_id() {
		return chatting_id;
	}

	public String getChatting_content() {
		return chatting_content;
	}

	public String getChatting_image() {
		return chatting_image;
	}

	public int getChatting_mark() {
		return chatting_mark;
	}

	public String getChatting_addTime() {
		return chatting_addTime;
	}

	@Override
	public String toString() {
		return "ChatItemBean{" +
				"chatting_touser_id=" + chatting_touser_id +
				", chattinglog_id=" + chattinglog_id +
				", chatting_id=" + chatting_id +
				", chatting_content='" + chatting_content + '\'' +
				", chatting_image='" + chatting_image + '\'' +
				", chatting_mark=" + chatting_mark +
				", chatting_addTime='" + chatting_addTime + '\'' +
				'}';
	}
}
