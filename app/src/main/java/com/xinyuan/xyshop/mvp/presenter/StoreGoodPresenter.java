package com.xinyuan.xyshop.mvp.presenter;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.ObservableBody;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.GoodListItemBean;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.callback.JsonConvert;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.mvp.contract.StoreGoodView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by fx on 2017/8/31.
 */

public class StoreGoodPresenter extends BasePresenter<StoreGoodView> {
	private Activity mActivity;

	public StoreGoodPresenter(Activity activity, StoreGoodView view) {
		super(view);
		this.mActivity = activity;
	}

	/**
	 * 获取店铺商品列表
	 *
	 * @param storeId
	 * @param type
	 * @param page
	 * @param limit
	 */
	public void getGoods(long storeId, int type, int page, int limit) {
		OkGo.<LzyResponse<List<GoodListItemBean>>>post(Urls.URL_STORE_GOODS)
				.headers("token", MyShopApplication.Token)
				.params("storeId", storeId)
				.params("ship", page)
				.params("limit", limit)
				.params("type", type)
				.converter(new JsonConvert<LzyResponse<List<GoodListItemBean>>>() {
				})
				.adapt(new ObservableBody<LzyResponse<List<GoodListItemBean>>>())
				.subscribeOn(Schedulers.io())
				.map(new Function<LzyResponse<List<GoodListItemBean>>, List<GoodListItemBean>>() {
					@Override
					public List<GoodListItemBean> apply(@NonNull LzyResponse<List<GoodListItemBean>> response) throws Exception {
						return response.datas;
					}
				})
				.observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
				.subscribe(new Observer<List<GoodListItemBean>>() {
					@Override
					public void onSubscribe(@NonNull Disposable d) {

					}

					@Override
					public void onNext(@NonNull List<GoodListItemBean> list) {
						mView.showGoods(list);

					}

					@Override
					public void onError(@NonNull Throwable e) {
						e.printStackTrace();
						mView.showState(2);
					}

					@Override
					public void onComplete() {

					}
				});

	}
}
