package com.xinyuan.xyshop.mvp.contract;

import android.view.View;

import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.base.BaseView;
import com.xinyuan.xyshop.model.CategoryModel;
import com.xinyuan.xyshop.model.GoodsDetailModel;

import java.util.List;

/**
 * Created by Administrator on 2017/6/12.
 */

public class GoodsDetailContract {
	public interface GoodsDetailView extends BaseView<GoodsDetailContract.GoodsDetailPresenter> {
		void showView(GoodsDetailModel model);
		void showBanner();

		void showGoodsInfo();

		void showEva();

		void showStoreInfo();

		void showWeb();


	}

	public interface GoodsDetailPresenter extends BasePresenter<GoodsDetailContract.GoodsDetailView> {

		void initData(int GoodsId);
	}
}
