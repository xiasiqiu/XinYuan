package com.xinyuan.xyshop.mvp.contract;

import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.base.BaseView;

/**
 * Created by Administrator on 2017/5/19.
 */

public class GoodsInfoContract {
	public interface GoodsInfoView extends BaseView<GoodsInfoPresenter> {

		void showBanner();
		void showInfo();
		void showComment();
		void showStoreInfo();
	}

	public interface GoodsInfoPresenter extends BasePresenter<GoodsInfoView> {



	}
}
