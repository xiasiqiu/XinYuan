package com.xinyuan.xyshop.mvp.contract;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xinyuan.xyshop.model.FooterModel;

/**
 * Created by fx on 2017/9/4.
 */

public interface FooterView {

	void showList(FooterModel favGoodModel);

	void showState(int state);

	LifecycleTransformer<FooterModel> bindLife();
}
