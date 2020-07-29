package com.xinyuan.xyshop.mvp.presenter;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.ObservableBody;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.callback.JsonConvert;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.EvaluateModel;
import com.xinyuan.xyshop.mvp.contract.CommentContentView;
import com.youth.xframe.utils.XEmptyUtils;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by fx on 2017/8/11.
 */

public class GoodCommentPresenter extends BasePresenter<CommentContentView> {
	private static Activity activity;

	public GoodCommentPresenter(Activity activity, CommentContentView view) {
		super(view);
		this.activity = activity;
	}

	/**
	 * 获取商品评价列表
	 *
	 * @param goodsId
	 * @param type
	 * @param page
	 * @param limit
	 */
	public void getCommentList(String goodsId, String type, int page, int limit) {
		OkGo.<LzyResponse<EvaluateModel>>post(Urls.URL_ORDER_EVA)
				.params("goodsId", goodsId)
				.params("ship", page)
				.params("limit", limit)
				.params("type", type)
				.converter(new JsonConvert<LzyResponse<EvaluateModel>>() {
				})
				.adapt(new ObservableBody<LzyResponse<EvaluateModel>>())
				.subscribeOn(Schedulers.io())//
				.doOnSubscribe(new Consumer<Disposable>() {
					@Override
					public void accept(@NonNull Disposable disposable) throws Exception {

					}
				})
				.map(new Function<LzyResponse<EvaluateModel>, EvaluateModel>() {
					@Override
					public EvaluateModel apply(@NonNull LzyResponse<EvaluateModel> response) throws Exception {
						if (HttpUtil.handleResponse(activity, response)) {
							if (XEmptyUtils.isEmpty(response.datas)) {
								EvaluateModel evaluateModel = new EvaluateModel();
								return evaluateModel;
							} else {
								return response.datas;
							}
						}
						return response.datas;
					}
				})
				.observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
				.compose(mView.bindLife())
				.subscribe(new Observer<EvaluateModel>() {
					@Override
					public void onSubscribe(@NonNull Disposable d) {

					}

					@Override
					public void onNext(@NonNull EvaluateModel evaluateModel) {
						mView.showList(evaluateModel);

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
