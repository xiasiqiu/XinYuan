package com.xinyuan.xyshop.mvp.presenter;

import com.xinyuan.xyshop.bean.LzyResponse;
import com.xinyuan.xyshop.http.ApiServer;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.GoodDetailModel;
import com.xinyuan.xyshop.model.GoodsDetailModel;
import com.xinyuan.xyshop.model.HomeModel;
import com.xinyuan.xyshop.mvp.contract.GoodsDetailContract;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/6/12.
 */

public class GoodsDetailPresenterImpl implements GoodsDetailContract.GoodsDetailPresenter {

	private GoodsDetailContract.GoodsDetailView view;

	public GoodsDetailPresenterImpl(GoodsDetailContract.GoodsDetailView view) {
		this.view = view;
		view.setPresenter(this);
	}

	@Override
	public void initData(int goodsId) {
		Subscription subscription = ApiServer.getGoodsDetail(Urls.URL_GOODS_DETAIL)
				.doOnSubscribe(new Action0() {
					@Override
					public void call() {
						view.showState(0);
					}
				})
				.map(new Func1<LzyResponse<GoodDetailModel>, GoodDetailModel>() {

					@Override
					public GoodDetailModel call(LzyResponse<GoodDetailModel> goodsDetaillLzyResponse) {
						return goodsDetaillLzyResponse.getDatas();
					}
				})
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<GoodDetailModel>() {
					@Override
					public void call(GoodDetailModel goodsDetail) {
						XLog.v(goodsDetail.toString());
						view.showView(goodsDetail);

					}
				}, new Action1<Throwable>() {

					@Override
					public void call(Throwable throwable) {
						throwable.printStackTrace();
						view.showState(2);
					}
				});


	}


	@Override
	public void initData() {

	}
}
