package com.xinyuan.xyshop.common.state;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.xinyuan.xyshop.even.GoodBusEven;
import com.xinyuan.xyshop.ui.buy.ConfirmOrderActivity;
import com.xinyuan.xyshop.ui.mine.MsgActivity;
import com.xinyuan.xyshop.ui.mine.msg.ChattingDetailActivity;
import com.xinyuan.xyshop.util.CommUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

/**
 * Created by fx on 2017/7/25.
 * 登录状态模式
 */

public class LoginState implements UserState {

	@Override
	public void buy(Boolean isOnline, Map<String, String> param, Context context) {
		CommUtil.gotoActivity(context, ConfirmOrderActivity.class, false, param);
	}

	@Override
	public void addCart(Context context) {
		EventBus.getDefault().post(new GoodBusEven(GoodBusEven.addShopCar, true));
	}


	@Override
	public void showMsg(Context context) {
		CommUtil.gotoActivity(context, MsgActivity.class, false, null);

	}

	@Override
	public void signDay(Context context) {

	}

	@Override
	public Boolean showMine(final Context mContext) {
		return true;
	}

	@Override
	public void checkNewMsg(Context context) {
		Intent i = new Intent();
		i.setAction("com.xinyuan.xyshop.service.MsgService");
		i.setPackage(context.getPackageName());
		Bundle bundle = new Bundle();
		bundle.putString("taskName", "msgCheck");
		i.putExtras(bundle);
		context.startService(i);
	}

	@Override
	public void contactService(Map<String, String> param, Context context) {
		CommUtil.gotoActivity(context, ChattingDetailActivity.class, false, param);
	}

	@Override
	public Boolean getCoupon(Context context) {
		return true;
	}
}
