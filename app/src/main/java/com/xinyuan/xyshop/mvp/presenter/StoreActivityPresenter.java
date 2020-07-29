package com.xinyuan.xyshop.mvp.presenter;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.ObservableBody;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.GoodListItemBean;
import com.xinyuan.xyshop.callback.JsonConvert;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.mvp.contract.StoreActivityView;
import com.youth.xframe.utils.XEmptyUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by fx on 2017/9/4.
 */

public class StoreActivityPresenter extends BasePresenter<StoreActivityView> {
    private Activity mActivity;

    public StoreActivityPresenter(Activity activity, StoreActivityView view) {
        super(view);
        this.mActivity = activity;
    }

    /**
     * 获取店铺活动列表
     *
     * @param page
     * @param limit
     */
    public void getActivityList(int page, int limit) {
        OkGo.<LzyResponse<List<GoodListItemBean>>>post(Urls.URL_STORE_ACTIVITY)
                .params("ship", page)
                .params("limit", limit)
                .converter(new JsonConvert<LzyResponse<List<GoodListItemBean>>>() {
                })
                .adapt(new ObservableBody<LzyResponse<List<GoodListItemBean>>>())
                .subscribeOn(Schedulers.io())//
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {

                    }
                })
                .map(new Function<LzyResponse<List<GoodListItemBean>>, List<GoodListItemBean>>() {
                    @Override
                    public List<GoodListItemBean> apply(@NonNull LzyResponse<List<GoodListItemBean>> response) throws Exception {
                        if (HttpUtil.handleResponse(mActivity, response)) {

                            if (XEmptyUtils.isEmpty(response.datas)) {
                                List<GoodListItemBean> ch = new ArrayList<>();
                                return ch;
                            } else {
                                return response.datas;
                            }
                        } else {
                            List<GoodListItemBean> ch = new ArrayList<>();
                            return ch;
                        }

                    }

                })
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .compose(mView.bindLife())
                .subscribe(new Observer<List<GoodListItemBean>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<GoodListItemBean> creditMallModel) {
                        mView.showActivitys(creditMallModel);
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
