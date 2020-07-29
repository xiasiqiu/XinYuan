package com.xinyuan.xyshop.mvp.contract;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xinyuan.xyshop.bean.CreditNewsBean;
import com.xinyuan.xyshop.model.NewsModel;

import java.util.List;

/**
 * Created by fx on 2017/8/29.
 */

public interface CreditNewsView {
	void showList(List<CreditNewsBean> list);

	void showState(int state);

	LifecycleTransformer<List<CreditNewsBean>> bindLife();


}
