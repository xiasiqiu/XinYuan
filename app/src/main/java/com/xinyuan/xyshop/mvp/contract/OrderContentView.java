package com.xinyuan.xyshop.mvp.contract;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xinyuan.xyshop.model.OnOrderModel;
import com.xinyuan.xyshop.model.OrderModel;

/**
 * Created by fx on 2017/7/31.
 */

public interface OrderContentView {
	void showState(int state);

	void showList(OrderModel orderModel);
	void showOnLineList(OnOrderModel orderModel);

	LifecycleTransformer<OrderModel> bindLife();
	LifecycleTransformer<OnOrderModel> bindtoLife();
}
