package com.xinyuan.xyshop.mvp.contract;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xinyuan.xyshop.model.SearchGoodModel;

/**
 * Created by fx on 2017/7/31.
 */

public interface SearchGoodView {
	void showGoods(SearchGoodModel searchModel);


	LifecycleTransformer<SearchGoodModel> bindLife();

	void showState(int state);
}
