package com.xinyuan.xyshop.even;

/**
 * Created by fx on 2017/8/5.
 * 消息状态消息
 */

public class MsgStateEven {
	private String msg;
	private boolean hasNew;
	public MsgStateEven(String msg, Boolean state) {
		hasNew = state;
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}



	public boolean isHasNew() {
		return hasNew;
	}
}
