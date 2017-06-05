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
import com.xinyuan.xyshop.model.CategoryModel;
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

import com.xinyuan.xyshop.model.CategoryModel.CategoryData;

/**
 * Title:
 * Description:
 * Created by Administrator on 2017/5/10.
 * 作者：fx on 2017/5/10 22:05
 */

public class CategoryPresenterImpl implements CategoryContract.CategoryPresenter {
	private CategoryContract.CategoryView mCategoryView;
	public static List<CategoryData> goodsCategoryList_one;
	public static List<CategoryData> goodsCategoryList_three;
	public static List<CategoryData> goodsCategoryList_two;

	public CategoryPresenterImpl(CategoryContract.CategoryView view) {
		this.mCategoryView = view;
		view.setPresenter(this);

	}

	@Override
	public void initData() {
		XLog.v("分类页面开始加载数据");
		Subscription subscription = ApiServer.getCategory(Urls.URL_GOODS_CATEGORY)
				.doOnSubscribe(new Action0() {
					@Override
					public void call() {
						XLog.v("分类加载中");
						mCategoryView.showState(0);

					}
				}).map(new Func1<LzyResponse<CategoryModel>, CategoryModel>() {


					@Override
					public CategoryModel call(LzyResponse<CategoryModel> Response) {

						return Response.getDatas();
					}
				})
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<CategoryModel>() {
					@Override
					public void call(CategoryModel categoryModel) {

						List<CategoryModel.CategoryData> goodsCategoryList = new ArrayList<CategoryModel.CategoryData>();
						goodsCategoryList = categoryModel.getDatas();
						cleanData(goodsCategoryList);

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

	private void cleanData(List<CategoryData> goodsCategoryList) {

		goodsCategoryList_one = new ArrayList();
		goodsCategoryList_three = new ArrayList();
		goodsCategoryList_two = new ArrayList();

		for (int i = 0; i < goodsCategoryList.size(); i++) {

			goodsCategoryList_one.add(goodsCategoryList.get(i));
		}

		for (int k = 0; k < goodsCategoryList_one.size(); k++) {
			int m;
			List<CategoryData> goodsCategories = goodsCategoryList_one.get(k).getCategoryList();
			for (m = 0; m < goodsCategories.size(); m++) {
				goodsCategoryList_two.add(goodsCategories.get(m));
			}
		}

		for (int j = 0; j < goodsCategoryList_two.size(); j++) {
			List<CategoryData> goodsCategoryList1 = goodsCategoryList_two.get(j).getCategoryList();
			for (int g = 0; g < goodsCategoryList1.size(); g++) {
				goodsCategoryList_three.add(goodsCategoryList1.get(g));
			}
		}


		mCategoryView.getData();

		for (int m = 0; m < goodsCategoryList_one.size(); m++) {
			mCategoryView.showFrist(goodsCategoryList_one.get(m), m);
		}

	}

	public static void jump(Context context, String name, boolean isBrand) {
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

		i.putExtra("keyword", name);

		context.startActivity(i);
	}


	public static List<CategoryData> getGoodsCategoryList_one() {
		return goodsCategoryList_one;
	}

	public static List<CategoryData> getGoodsCategoryList_two() {
		return goodsCategoryList_two;
	}

	public static List<CategoryData> getGoodsCategoryList_three() {
		return goodsCategoryList_three;
	}


}