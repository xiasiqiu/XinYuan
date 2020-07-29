package com.xinyuan.xyshop.even;

import java.io.Serializable;

/**
 * Created by fx on 2017/8/5.
 * 新消息状态消息
 */

public class NewChatEvent implements Serializable {
	private static final long serialVersionUID = -4766492251011812362L;

	public NewChatEvent(String chattingId, String chattinglogId) {
		this.chattingId = chattingId;
		this.chattinglogId = chattinglogId;
	}

	private String chattingId;
	private String chattinglogId;

	public String getChattingId() {
		return chattingId;
	}

	public String getChattinglogId() {
		return chattinglogId;
	}

	@Override
	public String toString() {
		return "NewChatEvent{" +
				"chattingId='" + chattingId + '\'' +
				", chattinglogId='" + chattinglogId + '\'' +
				'}';
	}
}
