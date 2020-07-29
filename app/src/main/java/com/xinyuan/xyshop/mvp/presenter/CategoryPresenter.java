package com.xinyuan.xyshop.mvp.presenter;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.ApiServer;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.CategoryModel;
import com.xinyuan.xyshop.mvp.contract.CategoryView;
import com.youth.xframe.cache.XCache;
import com.youth.xframe.utils.log.XLog;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by fx on 2017/7/31.
 */

public class CategoryPresenter extends BasePresenter<CategoryView> {
	private XCache xCache;

	public CategoryPresenter(Context context, CategoryView view) {
		super(view);
		xCache = XCache.get(context);
	}

	/**
	 * 获取分类数据
	 */
	public void getCategory() {
		Type type = new TypeToken<LzyResponse<CategoryModel>>() {
		}.getType();
		ApiServer.<LzyResponse<CategoryModel>>getData(type, Urls.URL_GOODS_CATEGORY)
				.subscribeOn(Schedulers.io())
				.doOnSubscribe(new Consumer<Disposable>() {
					@Override
					public void accept(@NonNull Disposable disposable) throws Exception {
						mView.showState(0);
					}
				})
				.map(new Function<LzyResponse<CategoryModel>, CategoryModel>() {
					@Override
					public CategoryModel apply(@NonNull LzyResponse<CategoryModel> response) throws Exception {

						return response.datas;

					}
				})
				.observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
				.compose(mView.bindLife())
				.subscribe(new Observer<CategoryModel>() {
					@Override
					public void onSubscribe(@NonNull Disposable d) {

					}

					@Override
					public void onNext(@NonNull CategoryModel categoryModel) {
						List<CategoryModel.CategoryData> goodsCategoryList = new ArrayList<CategoryModel.CategoryData>();
						goodsCategoryList = categoryModel.getDatas();
						xCache.put("categoryModel", categoryModel, XCache.TIME_DAY);
						mView.cleanData(goodsCategoryList);
					}

					@Override
					public void onError(@NonNull Throwable e) {
						e.printStackTrace();
						mView.showState(2);
					}

					@Override
					public void onComplete() {
						mView.showState(1);
					}
				});


	}

}

