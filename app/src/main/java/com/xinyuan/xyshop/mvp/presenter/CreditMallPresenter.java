package com.xinyuan.xyshop.mvp.presenter;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.ObservableBody;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.callback.JsonConvert;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.CreditGoodModel;
import com.xinyuan.xyshop.model.CreditMallModel;
import com.xinyuan.xyshop.mvp.contract.CreditMallView;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by fx on 2017/8/19.
 */

public class CreditMallPresenter extends BasePresenter<CreditMallView> {

	private static Activity activity;

	public CreditMallPresenter(Activity activity, CreditMallView view) {
		super(view);
		this.activity = activity;
	}

	/**
	 * 获取积分商城首页数据
	 */
	public void getData() {
		OkGo.<LzyResponse<CreditMallModel>>get(Urls.URL_CREDIT_MALL)
				.converter(new JsonConvert<LzyResponse<CreditMallModel>>() {
				})
				.adapt(new ObservableBody<LzyResponse<CreditMallModel>>())
				.subscribeOn(Schedulers.io())//
				.doOnSubscribe(new Consumer<Disposable>() {
					@Override
					public void accept(@NonNull Disposable disposable) throws Exception {

					}
				})
				.map(new Function<LzyResponse<CreditMallModel>, CreditMallModel>() {
					@Override
					public CreditMallModel apply(@NonNull LzyResponse<CreditMallModel> response) throws Exception {
						return response.datas;
					}
				})
				.observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
				.compose(mView.bindLife())
				.subscribe(new Observer<CreditMallModel>() {
					@Override
					public void onSubscribe(@NonNull Disposable d) {

					}

					@Override
					public void onNext(@NonNull CreditMallModel creditMallModel) {

						mView.showList(creditMallModel);
					}

					@Override
					public void onError(@NonNull Throwable e) {
						e.printStackTrace();

					}

					@Override
					public void onComplete() {


					}
				});


	}

	/**
	 * 获取积分商城首页推荐商品数据
	 */
	public void getGoods() {
		OkGo.<LzyResponse<CreditGoodModel>>get(Urls.URL_CREDIT_GOODS)
				.tag(this)
				.execute(new JsonCallback<LzyResponse<CreditGoodModel>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<CreditGoodModel>> response) {
						if (HttpUtil.handleResponse(activity, response.body())) {
							mView.showGoods(response.body().getDatas());
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<CreditGoodModel>> response) {
						HttpUtil.handleError(activity, response);
					}
				});


	}


}
