package com.xinyuan.xyshop.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by fx on 2017/8/4.
 * 消息布局实体类
 */

public class MsgMultipleItem implements MultiItemEntity {
	public static final int CHAT_ITEM_LEFT = 1;
	public static final int CHAT_ITEM_RIGHT = 2;
	public static final int CHAT_TIME_CENTER = 3;


	public static final int CHAT_ITEM_SENDING = 5;
	public static final int CHAT_ITEM_SEND_ERROR = 6;
	public static final int CHAT_ITEM_SEND_SUCCESS = 7;


	public int itemType;
	public int spanSize;
	public long id;


	private String content;
	private int sendState;
	private String time;
	private String header;
	private String imageUrl;
	private String unique;

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	public void setSpanSize(int spanSize) {
		this.spanSize = spanSize;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setSendState(int sendState) {
		this.sendState = sendState;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setUnique(String unique) {
		this.unique = unique;
	}

	public static int getChatItemRight() {
		return CHAT_ITEM_RIGHT;
	}

	public static int getChatItemSending() {
		return CHAT_ITEM_SENDING;
	}

	public static int getChatItemSendError() {
		return CHAT_ITEM_SEND_ERROR;
	}

	public static int getChatItemSendSuccess() {
		return CHAT_ITEM_SEND_SUCCESS;
	}

	public int getSpanSize() {
		return spanSize;
	}

	public String getContent() {
		return content;
	}

	public int getSendState() {
		return sendState;
	}

	public String getTime() {
		return time;
	}

	public String getHeader() {
		return header;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getUnique() {
		return unique;
	}

	public static int getChatItemLeft() {
		return CHAT_ITEM_LEFT;
	}

	public MsgMultipleItem(int itemType) {
		this.itemType = itemType;


	}
	public MsgMultipleItem() {
	}


	public long getId() {
		return id;
	}

	public MsgMultipleItem(int itemType, long id) {
		this.itemType = itemType;
		this.id = id;

	}


	@Override
	public int getItemType() {
		return itemType;
	}

	@Override
	public String toString() {
		return "HomeMultipleItem{" +
				"itemType=" + itemType;
	}
}
