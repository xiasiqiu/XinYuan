package com.xinyuan.xyshop.mvp.presenter;

import android.view.View;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.entity.PageEntity;
import com.xinyuan.xyshop.entity.SearchGoodsList;
import com.xinyuan.xyshop.entity.SearchGoodsListTest;
import com.xinyuan.xyshop.entity.SelectFilter;
import com.xinyuan.xyshop.entity.SelectFilterTest;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.mvp.contract.GoodSearchShowContract;
import com.xinyuan.xyshop.ui.goods.SearchGoodsShowActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.JsonUtil;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/24.
 */

public class SearchGoodsShowPresenterImpl implements GoodSearchShowContract.GoodSearchShowPresenter {
	private GoodSearchShowContract.GoodSearchShowView showView;


	private SearchGoodsList searchGoodsList;
	private SearchGoodsListTest searchGoodsListTest;

	private PageEntity pageEntity;
	private static SelectFilter selectFilter;
	private SelectFilterTest selectFilterTest;
	private List<GoodsVo> goodses = new ArrayList();
	private List<SelectFilterTest.FilterKey> keyList = new ArrayList<>();

	public SearchGoodsShowPresenterImpl(GoodSearchShowContract.GoodSearchShowView view) {
		this.showView = view;
		view.setPresenter(this);
	}


	@Override
	public void initData(String keyword, int brandId, int cat, String sort, String selectValue, final int page) {
		XLog.v("brandId：" + brandId + "key" + keyword);
		String url = "https://java.bizpower.com/api/search?page=" + page + "&pageSize=" + 10;
		if (CommUtil.isNotEmpty(keyword)) {
			url = url + "&keyword=" + keyword;
		}
		if (brandId != -1) {
			url = url + "&brand=" + brandId;
		}
		if (CommUtil.isNotEmpty(sort)) {
			url = url + "&sort=" + sort;
		}
		if (CommUtil.isNotEmpty(selectValue)) {
			url = url + selectValue;
		}
		if (cat != -1) {
			url = url + "&cat=" + cat;
		}
		XLog.v("此次请求的地址为loadGoods: url = " + url);


		if (keyword.equals("手机")) {
			OkGo.get(Urls.URL_SEARCH_SHOUJI)
					.execute(new StringCallback() {
						@Override
						public void onSuccess(String s, Call call, Response response) {
							searchGoodsListTest = JsonUtil.toBean(s, SearchGoodsListTest.class);
							SearchGoodsListTest.SearchGoodsData data = searchGoodsListTest.getDatas();
							selectFilterTest = data.getSelectFilter();

							keyList = selectFilterTest.getKeyList();

							pageEntity = data.getPageEntity();
							List<GoodsVo> goodsVoList = data.getGoodsList();
							showView.showGoodList(goodsVoList, selectFilterTest,pageEntity);

						}


						@Override
						public void onError(Call call, Response response, Exception e) {
							showView.showState(2);
						}
					});
		} else {

		OkGo.get(url)
				.execute(new StringCallback() {
					@Override
					public void onSuccess(String s, Call call, Response response) {

						searchGoodsList = JsonUtil.toBean(s, SearchGoodsList.class);

						SearchGoodsList.SearchGoodsData data = searchGoodsList.getDatas();
						pageEntity = data.getPageEntity();
						selectFilter = data.getSelectFilter();
						List<GoodsVo> goodsVoList = data.getGoodsList();
						showView.showGoodList(goodsVoList, selectFilterTest, pageEntity);

					}
				});
		}
	}


	@Override
	public void initData() {

	}


}
