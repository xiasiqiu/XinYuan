package com.xinyuan.xyshop.mvp.presenter;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.ObservableBody;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.CreditNewsBean;
import com.xinyuan.xyshop.bean.StoreInfoBean;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.callback.DialogCallback;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.callback.JsonConvert;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.RecomGoodModel;
import com.xinyuan.xyshop.mvp.contract.CartView;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by fx on 2017/8/24.
 */

public class CartPresenter extends BasePresenter<CartView> {
	private Activity activity;

	public CartPresenter(Activity activity, CartView view) {
		super(view);
		this.activity = activity;
	}

	/**
	 * 获取购物车数据
	 */
	public void getCart() {
		OkGo.<LzyResponse<List<StoreInfoBean>>>post(Urls.URL_GOODS_CART)
				.headers("token", MyShopApplication.Token)
				.params("id", MyShopApplication.userId)
				.converter(new JsonConvert<LzyResponse<List<StoreInfoBean>>>() {
				})
				.adapt(new ObservableBody<LzyResponse<List<StoreInfoBean>>>())
				.subscribeOn(Schedulers.io())//
				.doOnSubscribe(new Consumer<Disposable>() {
					@Override
					public void accept(@NonNull Disposable disposable) throws Exception {

					}
				})
				.map(new Function<LzyResponse<List<StoreInfoBean>>, List<StoreInfoBean>>() {
					@Override
					public List<StoreInfoBean> apply(@NonNull LzyResponse<List<StoreInfoBean>> response) throws Exception {
						if (HttpUtil.handleResponse(activity, response)) {

							if (XEmptyUtils.isEmpty(response.datas)) {
								List<StoreInfoBean> ch = new ArrayList<>();
								return ch;
							} else {
								return response.datas;
							}
						} else {
							List<StoreInfoBean> ch = new ArrayList<>();
							return ch;
						}

					}
				})
				.observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
				.compose(mView.bindLife())
				.subscribe(new Observer<List<StoreInfoBean>>() {
					@Override
					public void onSubscribe(@NonNull Disposable d) {

					}

					@Override
					public void onNext(@NonNull List<StoreInfoBean> list) {
						mView.showCart(list);
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

	/**
	 * 获取商品推荐数据
	 */
	public void getGood() {
		OkGo.<LzyResponse<RecomGoodModel>>get(Urls.URL_GOODS_RECOME_GOODS)
				.execute(new JsonCallback<LzyResponse<RecomGoodModel>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<RecomGoodModel>> response) {
						if (HttpUtil.handleResponse(activity, response.body())) {
							mView.showGood(response.body().getDatas());
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<RecomGoodModel>> response) {
						HttpUtil.handleError(activity, response);
					}
				});
	}

	/**
	 * 删除购物车商品数据
	 *
	 * @param ids
	 */

	public void deteleGood(String ids) {
		OkGo.<LzyResponse<TokenBean>>post(Urls.URL_GOODS_CART_DETELE)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("cartId", ids)
				.execute(new DialogCallback<LzyResponse<TokenBean>>(activity, "删除中...") {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						if (HttpUtil.handleResponse(activity, response.body())) {
							mView.deteleGood(true);
						} else {
							mView.deteleGood(false);
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						HttpUtil.handleError(activity, response);
						mView.deteleGood(false);
					}
				});
	}

	/**
	 * 提交商品数据
	 */
	public void putGoodNum(String json) {
		OkGo.<LzyResponse<TokenBean>>post(Urls.URL_GOODS_CART_NUM)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("gcJson", json)
				.execute(new JsonCallback<LzyResponse<TokenBean>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						if (HttpUtil.handleResponse(activity, response.body())) {
							mView.postOrder();
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						HttpUtil.handleError(activity, response);
					}
				});
	}
}
