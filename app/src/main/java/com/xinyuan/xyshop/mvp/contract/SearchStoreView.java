package com.xinyuan.xyshop.mvp.contract;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xinyuan.xyshop.model.SearchGoodModel;
import com.xinyuan.xyshop.model.SearchStoreModel;

/**
 * Created by fx on 2017/8/3.
 */

public interface SearchStoreView {

	LifecycleTransformer<SearchStoreModel> bindLife();
	void showState(int state);
	void showStore(SearchStoreModel searchStoreModel);


}
