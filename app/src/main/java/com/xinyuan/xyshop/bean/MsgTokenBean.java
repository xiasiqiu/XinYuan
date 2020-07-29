package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by fx on 2017/8/4.
 * 聊天消息返回数据
 */

public class MsgTokenBean  implements Serializable{
	private static final long serialVersionUID = 2021223170247926909L;
	private String chattinglogId;   //消息ID(具体到某一条消息)
	private String chattingId;      //消息总ID（与某个用户的消息）

	public MsgTokenBean(String chattinglogId, String chattingId) {
		this.chattinglogId = chattinglogId;
		this.chattingId = chattingId;
	}

	public String getChattingId() {
		return chattingId;
	}

	public String getChattinglogId() {
		return chattinglogId;
	}

	@Override
	public String toString() {
		return "MsgTokenBean{" +
				"chattinglogId='" + chattinglogId + '\'' +
				", chattingId='" + chattingId + '\'' +
				'}';
	}
}
