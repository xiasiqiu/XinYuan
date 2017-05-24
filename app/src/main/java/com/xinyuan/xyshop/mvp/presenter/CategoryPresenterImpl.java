package com.xinyuan.xyshop.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.reflect.TypeToken;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.bean.LzyResponse;
import com.xinyuan.xyshop.entity.ApiSpecialItem;
import com.xinyuan.xyshop.entity.Brand;
import com.xinyuan.xyshop.entity.GoodCategory;
import com.xinyuan.xyshop.http.ApiServer;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.mvp.contract.CategoryContract;
import com.xinyuan.xyshop.ui.goods.PromotionListActivity;
import com.xinyuan.xyshop.ui.goods.SearchGoodsShowActivity;
import com.xinyuan.xyshop.util.JsonUtil;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Title:
 * Description:
 * Created by Administrator on 2017/5/10.
 * 作者：fx on 2017/5/10 22:05
 */

public class CategoryPresenterImpl implements CategoryContract.CategoryPresenter {
	private CategoryContract.CategoryView mCategoryView;
	public static List<GoodCategory> goodsCategoryList_one;
	public static List<GoodCategory> goodsCategoryList_three;
	public static List<GoodCategory> goodsCategoryList_two;

	public CategoryPresenterImpl(CategoryContract.CategoryView view) {
		this.mCategoryView = view;
		view.setPresenter(this);

	}

	@Override
	public void initData() {
		XLog.v("分类页面开始加载数据");
		Subscription subscription = ApiServer.getApiSpecialList(Urls.URL_GOODS_CATEGORY)
				.doOnSubscribe(new Action0() {
					@Override
					public void call() {
						XLog.v("分类加载中");
						mCategoryView.showState(0);

					}
				})
				.map(new Func1<LzyResponse<String>, List<GoodCategory>>() {

					@Override
					public List<GoodCategory> call(LzyResponse<String> response) {

						List<GoodCategory> list = (List) JsonUtil.toBean(response.getDatas(), "categoryList", new TypeToken<List<GoodCategory>>() {
						}.getType());


						return list;
					}
				})
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<List<GoodCategory>>() {
					@Override
					public void call(List<GoodCategory> goodCategoryList) {
						XLog.v("分类请求成功");
						cleanData(goodCategoryList);

					}
				}, new Action1<Throwable>() {
					@Override
					public void call(Throwable throwable) {
						throwable.printStackTrace();
						XLog.v("分类请求失败");
						mCategoryView.showState(2);
					}
				});
	}

	private void cleanData(List<GoodCategory> goodsCategoryList) {

		goodsCategoryList_one = new ArrayList();
		goodsCategoryList_three = new ArrayList();
		goodsCategoryList_two = new ArrayList();

		for (int i = 0; i < goodsCategoryList.size(); i++) {
			goodsCategoryList_one.add(goodsCategoryList.get(i));
		}

		for (int k = 0; k < goodsCategoryList_one.size(); k++) {
			int m;
			List<GoodCategory> goodsCategories = goodsCategoryList_one.get(k).getCategoryList();
			for (m = 0; m < goodsCategories.size(); m++) {
				goodsCategoryList_two.add(goodsCategories.get(m));
			}
		}

		for (int j = 0; j < goodsCategoryList_two.size(); j++) {
			List<GoodCategory> goodsCategoryList1 = goodsCategoryList_two.get(j).getCategoryList();
			for (int g = 0; g < goodsCategoryList1.size(); g++) {
				goodsCategoryList_three.add(goodsCategoryList1.get(g));
			}
		}


		mCategoryView.getData();

		for (int m = 0; m < goodsCategoryList_one.size(); m++) {
			mCategoryView.showFrist(goodsCategoryList_one.get(m), m);
		}

	}

	public static void jump(Context context, int cat, boolean isBrand) {
		Intent i;
		SharedPreferences sharedPreferences = context.getSharedPreferences("promotion", 0);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		if (sharedPreferences.getInt("promotion", 0) < 0) {
			editor.remove("promotion");
			editor.apply();
			i = new Intent(context, PromotionListActivity.class);
		} else {
			i = new Intent(context, SearchGoodsShowActivity.class);
		}
		if (isBrand) {
			i.putExtra("keyword", MyShopApplication.getKeyWord());
			i.putExtra("brandId", cat);
			i.putExtra("brand", cat);
		} else {
			i.putExtra("cat", cat);
		}
		context.startActivity(i);
	}


	public static List<GoodCategory> getGoodsCategoryList_one() {
		return goodsCategoryList_one;
	}

	public static List<GoodCategory> getGoodsCategoryList_two() {
		return goodsCategoryList_two;
	}

	public static List<GoodCategory> getGoodsCategoryList_three() {
		return goodsCategoryList_three;
	}


}