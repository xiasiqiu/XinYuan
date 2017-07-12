package com.xinyuan.xyshop.mvp.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.xinyuan.xyshop.bean.LzyResponse;
import com.xinyuan.xyshop.entity.BrandList;
import com.xinyuan.xyshop.http.ApiServer;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.StoreHomeModel;
import com.xinyuan.xyshop.model.TestStoreModel;
import com.xinyuan.xyshop.mvp.contract.StoreHomeContract;
import com.xinyuan.xyshop.util.JsonUtil;
import com.youth.xframe.utils.log.XLog;

import okhttp3.Call;
import okhttp3.Response;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/7/12.
 */

public class StoreHomePresenterImpl implements StoreHomeContract.StoreHomePresenter {
	private StoreHomeContract.StoreHomeView view;

	public StoreHomePresenterImpl(StoreHomeContract.StoreHomeView view) {
		this.view = view;
		view.setPresenter(this);
	}

	@Override
	public void initData(final int storeId) {
		Subscription subscription = ApiServer.getStoreHome(Urls.URL_STOREHOME)
				.doOnSubscribe(new Action0() {
					@Override
					public void call() {
						view.showState(0);
					}
				})
				.map(new Func1<LzyResponse<StoreHomeModel>, StoreHomeModel>() {

					@Override
					public StoreHomeModel call(LzyResponse<StoreHomeModel> getSotreResponse) {
						return getSotreResponse.getDatas();
					}
				})
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<StoreHomeModel>() {
					@Override
					public void call(StoreHomeModel storeHomeModel) {
						XLog.v(storeHomeModel.toString());
						showView(storeHomeModel);


					}
				}, new Action1<Throwable>() {

					@Override
					public void call(Throwable throwable) {
						throwable.printStackTrace();
						view.showState(2);
					}
				});

	}

	private void showView(StoreHomeModel storeHomeModel) {
		view.showRecom(storeHomeModel.getRecomList());
		view.showColl(storeHomeModel.getAd(), storeHomeModel.getColList(), storeHomeModel.getFavList());
		view.showStoreInfo(storeHomeModel.getStoreInfo());
		view.showCoupon(storeHomeModel.getCouponBeanList());
		view.showState(1);

	}

	@Override
	public void initData() {

	}
}
