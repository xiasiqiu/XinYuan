package com.xinyuan.xyshop.mvp.contract;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xinyuan.xyshop.bean.AddCartBean;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.model.GoodDetailModels;

/**
 * Created by fx on 2017/8/7.
 */

public interface GoodHomeView {
	void showView(GoodDetailModels model);

	void showState(int Sate);

	void showBanner();

	void showGoodsInfo();

	void showEva();

	void showStoreInfo();

	void showWeb();
	void goToBuy(Long cartId);

	void addCartBack(AddCartBean tokenBean);
	LifecycleTransformer<GoodDetailModels> bindLife();

    void shoEmpty();
}
