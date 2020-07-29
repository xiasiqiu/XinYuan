package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by fx on 2017/7/11.
 * 系统通知消息实体类
 */

public class MsgBean implements Serializable {
	private static final long serialVersionUID = -6759385592527226567L;
	private String messagePhoto;
	private String messageContent;
	private String messageTime;
	private String fromUser;
	private String messageId;

	@Override
	public String toString() {
		return "MsgBean{" +
				"messagePhoto='" + messagePhoto + '\'' +
				", messageContent='" + messageContent + '\'' +
				", messageTime='" + messageTime + '\'' +
				", fromUser='" + fromUser + '\'' +
				", messageId='" + messageId + '\'' +
				'}';
	}

	public String getMessagePhoto() {
		return messagePhoto;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public String getMessageTime() {
		return messageTime;
	}

	public String getFromUser() {
		return fromUser;
	}

	public String getMessageId() {
		return messageId;
	}
}
