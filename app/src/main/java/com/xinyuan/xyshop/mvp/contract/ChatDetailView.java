package com.xinyuan.xyshop.mvp.contract;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xinyuan.xyshop.bean.UserInfoBean;
import com.xinyuan.xyshop.model.ChatDetailModule;

/**
 * Created by fx on 2017/8/5.
 */

public interface ChatDetailView {
	void showMsgList(ChatDetailModule chatList);


	LifecycleTransformer<ChatDetailModule> bindLife();

	void showState(int state);

	void sendState(boolean state, String unique);

	void setUserInfo(UserInfoBean userInfo);

	void addHistory(ChatDetailModule chatListModule);
}
