package com.xinyuan.xyshop.mvp.presenter;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.ObservableBody;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.callback.JsonConvert;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.CreditGoodModel;
import com.xinyuan.xyshop.mvp.contract.CreditMallDetailView;
import com.youth.xframe.utils.log.XLog;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by fx on 2017/8/19.
 */

public class CreditMallDetailPresenter extends BasePresenter<CreditMallDetailView> {

	private static Activity activity;

	public CreditMallDetailPresenter(Activity activity, CreditMallDetailView view) {
		super(view);
		this.activity = activity;
	}

	/**
	 * 获取积分商城详情列表数据
	 *
	 * @param type
	 * @param page
	 * @param limit
	 */
	public void getData(int type, int page, int limit) {
		String url = "";
		switch (type) {
			case 1:
				url = Urls.URL_CREDIT_GOOD; //积分兑换商品
				break;
			case 2:
				url = Urls.URL_CREDIT_RED;  //积分兑换红包
				break;
			case 3:
				url = Urls.URL_CREDIT_COUPON;//积分兑换优惠券
				break;
		}
		OkGo.<LzyResponse<CreditGoodModel>>post(url)
				.params("ship", page)
				.params("limit", limit)
				.converter(new JsonConvert<LzyResponse<CreditGoodModel>>() {
				})
				.adapt(new ObservableBody<LzyResponse<CreditGoodModel>>())
				.subscribeOn(Schedulers.io())//
				.doOnSubscribe(new Consumer<Disposable>() {
					@Override
					public void accept(@NonNull Disposable disposable) throws Exception {

					}
				})
				.map(new Function<LzyResponse<CreditGoodModel>, CreditGoodModel>() {
					@Override
					public CreditGoodModel apply(@NonNull LzyResponse<CreditGoodModel> response) throws Exception {
						return response.datas;
					}
				})
				.observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
				.compose(mView.bindLife())
				.subscribe(new Observer<CreditGoodModel>() {
					@Override
					public void onSubscribe(@NonNull Disposable d) {

					}

					@Override
					public void onNext(@NonNull CreditGoodModel creditMallModel) {
						XLog.list(creditMallModel.getGoodlist());
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


}
