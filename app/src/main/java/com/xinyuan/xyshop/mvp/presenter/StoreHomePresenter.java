package com.xinyuan.xyshop.mvp.presenter;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.ObservableBody;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.callback.JsonConvert;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.GoodDetailModels;
import com.xinyuan.xyshop.model.StoreHomeModel;
import com.xinyuan.xyshop.mvp.contract.StoreHomeView;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by fx on 2017/8/16.
 */

public class StoreHomePresenter extends BasePresenter<StoreHomeView> {
    private Activity mActivity;

    public StoreHomePresenter(Activity activity, StoreHomeView mView) {
        super(mView);
        this.mActivity = activity;
    }

    /**
     * 获取店铺首页数据
     *
     * @param storeId
     */
    public void getData(long storeId) {
        OkGo.<LzyResponse<StoreHomeModel>>post(Urls.URL_STORE_INDEX)
                .tag(this)
                .headers("userId", String.valueOf(MyShopApplication.userId == 0 ? "00" : MyShopApplication.userId))
                .params("storeId", storeId)
                .execute(new JsonCallback<LzyResponse<StoreHomeModel>>() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<StoreHomeModel>> response) {
                        if (HttpUtil.handleResponse(mActivity, response.body())) {
                            if (response.body().code == Constants.HTTP_216) {
                                mActivity.finish();
                            } else {
                                showmView(response.body().getDatas());

                            }
                        }

                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<LzyResponse<StoreHomeModel>> response) {
                        HttpUtil.handleError(mActivity, response);
                    }
                });


    }

    private void showmView(StoreHomeModel storeHomeModel) {
        mView.showRecom(storeHomeModel.getRecommendedGoodsList());
        mView.showColl(storeHomeModel.getAd(), storeHomeModel.getSellGoodsList(), storeHomeModel.getFavoriteGoodsList());
        mView.showStoreInfo(storeHomeModel.getStoreInfo());
        mView.showCoupon(storeHomeModel.getCouponList());
    }
}
