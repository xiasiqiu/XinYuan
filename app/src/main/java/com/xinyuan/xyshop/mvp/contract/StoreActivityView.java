package com.xinyuan.xyshop.mvp.contract;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xinyuan.xyshop.bean.GoodListItemBean;
import com.xinyuan.xyshop.bean.GroupGoodBean;

import java.util.List;

/**
 * Created by fx on 2017/8/31.
 */

public interface StoreActivityView {
	void showState(int state);

	LifecycleTransformer<List<GoodListItemBean>> bindLife();

	void showActivitys(List<GoodListItemBean> list);

}
