package com.xinyuan.xyshop.model;

import com.xinyuan.xyshop.bean.LzyResponse;
import com.xinyuan.xyshop.http.ApiServer;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.mvp.contract.GoodsDetailContract;
import com.youth.xframe.utils.log.XLog;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/6/14.
 */

public class TestModel {

	private int code;
	private GoodDetail datas;

	public int getCode() {
		return code;
	}

	public GoodDetail getDatas() {
		return datas;
	}

	@Override
	public String toString() {
		return "TestModel{" +
				"code=" + code +
				", datas=" + datas +
				'}';
	}

	/**
	 * Created by Administrator on 2017/6/12.
	 */

	public static class GoodsDetailPresenterImpl implements GoodsDetailContract.GoodsDetailPresenter {

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
}
