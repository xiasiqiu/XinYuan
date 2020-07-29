package com.xinyuan.xyshop.mvp.presenter;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.ObservableBody;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.callback.JsonConvert;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.SearchStoreModel;
import com.xinyuan.xyshop.mvp.contract.SearchStoreView;
import com.youth.xframe.utils.XEmptyUtils;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by fx on 2017/8/3.
 */

public class SearchStorePresenter extends BasePresenter<SearchStoreView> {

	private Activity activity;

	public SearchStorePresenter(Activity activity, SearchStoreView view) {
		super(view);
		this.activity = activity;

	}

	/**
	 * 搜索店铺列表
	 *
	 * @param keyword
	 * @param sorts
	 * @param page
	 * @param limit
	 */
	public void getStore(String keyword, String sorts, int page, int limit) {
		OkGo.<LzyResponse<SearchStoreModel>>post(Urls.URL_SEARCH_STORE)
				.params("keyword", keyword)
				.params("sorts", sorts)
				.params("currentPage", page)
				.params("pageSize", limit)
				.converter(new JsonConvert<LzyResponse<SearchStoreModel>>() {
				})
				.adapt(new ObservableBody<LzyResponse<SearchStoreModel>>())
				.subscribeOn(Schedulers.io())//
				.doOnSubscribe(new Consumer<Disposable>() {
					@Override
					public void accept(@NonNull Disposable disposable) throws Exception {

					}
				})
				.map(new Function<LzyResponse<SearchStoreModel>, SearchStoreModel>() {
					@Override
					public SearchStoreModel apply(@NonNull LzyResponse<SearchStoreModel> response) throws Exception {
						if (HttpUtil.handleResponse(activity, response)) {
							if (XEmptyUtils.isEmpty(response.datas)) {
								SearchStoreModel model = new SearchStoreModel();
								return model;
							} else {
								return response.datas;

							}
						} else {
							return response.datas;
						}
					}
				})
				.observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
				.compose(mView.bindLife())
				.subscribe(new Observer<SearchStoreModel>() {
					@Override
					public void onSubscribe(@NonNull Disposable d) {

					}

					@Override
					public void onNext(@NonNull SearchStoreModel searchModel) {

						mView.showStore(searchModel);
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
