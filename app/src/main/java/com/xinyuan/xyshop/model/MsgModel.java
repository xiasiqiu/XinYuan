package com.xinyuan.xyshop.model;

import com.xinyuan.xyshop.bean.MsgBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/7/22.
 * 系统消息数据
 */

public class MsgModel implements Serializable {
	private static final long serialVersionUID = -6689389952383890059L;
	private List<MsgBean> msgList;

	public List<MsgBean> getMsgList() {
		return msgList;
	}

	@Override
	public String toString() {
		return "MsgModel{" +
				"msgList=" + msgList +
				'}';
	}
}
