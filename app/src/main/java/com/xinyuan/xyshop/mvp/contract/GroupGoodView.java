package com.xinyuan.xyshop.mvp.contract;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xinyuan.xyshop.model.CreditGoodModel;
import com.xinyuan.xyshop.model.GoodListModel;

/**
 * Created by fx on 2017/9/2.
 */

public interface GroupGoodView {
	void showState(int state);

	void showGoodList(GoodListModel goodList);

	LifecycleTransformer<GoodListModel> bindLife();

}
