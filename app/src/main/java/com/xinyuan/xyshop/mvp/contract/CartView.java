package com.xinyuan.xyshop.mvp.contract;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xinyuan.xyshop.bean.CreditNewsBean;
import com.xinyuan.xyshop.bean.StoreInfoBean;
import com.xinyuan.xyshop.model.RecomGoodModel;

import java.util.List;

/**
 * Created by fx on 2017/8/24.
 */

public interface CartView {
	void showState(int state);

	LifecycleTransformer<List<StoreInfoBean>> bindLife();

	void showCart(List<StoreInfoBean> groups);

	void showGood(RecomGoodModel goodModel);

	void deteleGood(boolean result);

    void postOrder();
}
