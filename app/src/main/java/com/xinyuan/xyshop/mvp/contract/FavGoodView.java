package com.xinyuan.xyshop.mvp.contract;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xinyuan.xyshop.model.FavGoodModel;
import com.xinyuan.xyshop.model.HomeModel;

/**
 * Created by fx on 2017/7/31.
 */

public interface FavGoodView {


	void showList(FavGoodModel favGoodModel);

	void detelteRes(boolean res);
	void showState(int state);

	LifecycleTransformer<HomeModel> bindLife();
}
