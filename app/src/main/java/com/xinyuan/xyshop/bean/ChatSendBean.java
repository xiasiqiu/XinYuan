package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by fx on 2017/8/9.
 * 发送消息返回数据
 */

public class ChatSendBean implements Serializable{
	private static final long serialVersionUID = -6051346667157756727L;
	private String chattingInfo;

	public String getChattingInfo() {
		return chattingInfo;
	}

	@Override
	public String toString() {
		return "ChatSendBean{" +
				"chattingInfo='" + chattingInfo + '\'' +
				'}';
	}
}
