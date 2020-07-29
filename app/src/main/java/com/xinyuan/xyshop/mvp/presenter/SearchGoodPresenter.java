package com.xinyuan.xyshop.mvp.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.ObservableBody;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.callback.JsonConvert;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.SearchGoodModel;
import com.xinyuan.xyshop.mvp.contract.SearchGoodView;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;

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

public class SearchGoodPresenter extends BasePresenter<SearchGoodView> {


	public SearchGoodPresenter(SearchGoodView view) {
		super(view);
	}

	/**
	 * 搜索商品
	 *
	 * @param keyword
	 * @param sorts
	 * @param currentPage
	 * @param pageSize
	 * @param price
	 * @param brandIdList
	 * @param brandNameList
	 * @param xyself
	 * @param property
	 */
	public void getSearchGoods(String keyword, String sorts, int currentPage, int pageSize, String price, List<String> brandIdList, List<String> brandNameList, String xyself, String property) {

		String brandId = "";
		String brandName = "";

		if (!XEmptyUtils.isEmpty(brandIdList)) {    //如果筛选有品牌ID
			String s = brandIdList.toString();
			brandId = s.substring(1, s.length() - 1);
		}

		if (!XEmptyUtils.isEmpty(brandNameList)) {  //如果筛选有品牌名称
			String s = brandNameList.toString();
			brandName = s.substring(1, s.length() - 1);
		}
		String page = String.valueOf(currentPage);
		String limit = String.valueOf(pageSize);
		XLog.v("此次请求的地址 = ?keyword=" + keyword + "&sort=" + sorts + "&page=" + page + "&limit=" + limit + "&price=" + price + "&brandId=" + brandId + "&brandName=" + brandName + "&xyself=" + xyself + "&property=" + property);
		OkGo.<LzyResponse<SearchGoodModel>>post(Urls.URL_SEARCH_GOOD)
				.params("keyword", keyword)
				.params("sorts", sorts)
				.params("goodsBrandId", brandId)
				.params("goodsBrand", brandName)
				.params("property", property)
				.params("currentPage", page)
				.params("pageSize", limit)
				.params("xyself", "")
				.params("price", price)
				.converter(new JsonConvert<LzyResponse<SearchGoodModel>>() {
				})
				.adapt(new ObservableBody<LzyResponse<SearchGoodModel>>())
				.subscribeOn(Schedulers.io())//
				.doOnSubscribe(new Consumer<Disposable>() {
					@Override
					public void accept(@NonNull Disposable disposable) throws Exception {

					}
				})
				.map(new Function<LzyResponse<SearchGoodModel>, SearchGoodModel>() {
					@Override
					public SearchGoodModel apply(@NonNull LzyResponse<SearchGoodModel> response) throws Exception {
						return response.datas;
					}
				})
				.observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
				.compose(mView.bindLife())
				.subscribe(new Observer<SearchGoodModel>() {
					@Override
					public void onSubscribe(@NonNull Disposable d) {

					}

					@Override
					public void onNext(@NonNull SearchGoodModel searchModel) {

						mView.showGoods(searchModel);
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
