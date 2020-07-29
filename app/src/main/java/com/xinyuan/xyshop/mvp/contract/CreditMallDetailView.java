package com.xinyuan.xyshop.mvp.contract;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xinyuan.xyshop.model.CreditGoodModel;
import com.xinyuan.xyshop.model.CreditMallDetailModel;
import com.xinyuan.xyshop.model.CreditMallModel;

/**
 * Created by fx on 2017/8/19.
 */

public interface CreditMallDetailView {
	void showList(CreditGoodModel creditMallModel);
	LifecycleTransformer<CreditGoodModel> bindLife();
}
