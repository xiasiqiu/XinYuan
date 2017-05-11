package com.xinyuan.xyshop.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.xinyuan.xyshop.entity.CatrgoryResponse;
import com.xinyuan.xyshop.entity.GoodCategory;
import com.xinyuan.xyshop.http.ApiServer;
import com.xinyuan.xyshop.mvp.contract.CategoryContract;
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
   public static List<GoodCategory> goodsCategoryList_one = new ArrayList();
    public static List<GoodCategory> goodsCategoryList_three = new ArrayList();
    public static List<GoodCategory> goodsCategoryList_two = new ArrayList();
    public CategoryPresenterImpl(CategoryContract.CategoryView view) {
        this.mCategoryView = view;
        view.setPresenter(this);

    }

    @Override
    public void initData() {

        Subscription subscription = ApiServer.getCategoryList("aaa", "bbb")
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                        mCategoryView.showState(0);

                    }
                })
                .map(new Func1<CatrgoryResponse<List<GoodCategory>>, List<GoodCategory>>() {

                    @Override
                    public List<GoodCategory> call(CatrgoryResponse<List<GoodCategory>> listCatrgoryResponse) {

                        return listCatrgoryResponse.itemList;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<GoodCategory>>() {
                    @Override
                    public void call(List<GoodCategory> goodCategoryList) {

                        XLog.list(goodCategoryList);
                        cleanData(goodCategoryList);

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();

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

        goodsCategoryList_one.add(0, new GoodCategory(0, "品牌推荐", "https://java.bizpower.com/wap/images/degault.png"));
        for (int m = 0; m < goodsCategoryList_one.size(); m++) {
            mCategoryView.addGoodsClass((GoodCategory) goodsCategoryList_one.get(m), m);
        }

    }


    public static void jump(Context context, int cat, boolean isBrand) {
        Intent i;
        SharedPreferences sharedPreferences = context.getSharedPreferences("promotion", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (sharedPreferences.getInt("promotion", 0) < 0) {
            editor.remove("promotion");
            editor.apply();

        } else {


        }
//        if (isBrand) {
//            i.putExtra("keyword", " ");
//            i.putExtra("brandId", cat);
//            i.putExtra("brand", cat);
//        } else {
//            i.putExtra("cat", cat);
//        }
//        context.startActivity(i);
    }

    public static List<GoodCategory> getOneGoodCategoryList(){
        return goodsCategoryList_one;
    }
    public static List<GoodCategory> getTwoGoodCategoryList(){
        return goodsCategoryList_two;
    }
    public static List<GoodCategory> getOneThreeCategoryList(){
        return goodsCategoryList_three;
    }
}
