package com.xinyuan.xyshop.mvp.contract;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xinyuan.xyshop.model.ChatListModule;

/**
 * Created by fx on 2017/8/5.
 */

public interface StoreMsgView {
	void showMsgList(ChatListModule chatList);

	void showDeteleState();

	LifecycleTransformer<ChatListModule> bindLife();

	void showState(int state);
}
