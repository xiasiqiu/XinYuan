package com.xinyuan.xyshop.mvp.presenter;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.ObservableBody;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.CreditNewsBean;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.callback.JsonConvert;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.InvoiceModel;
import com.xinyuan.xyshop.model.NewsModel;
import com.xinyuan.xyshop.mvp.contract.CreditNewsView;
import com.xinyuan.xyshop.mvp.contract.NewView;
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
 * Created by fx on 2017/8/29.
 */

public class CreditNewsPresenter extends BasePresenter<CreditNewsView> {
    private Activity mActivity;

    public CreditNewsPresenter(Activity activity, CreditNewsView view) {
        super(view);
        this.mActivity = activity;
    }

    /**
     * 获取积分动态消息列表
     *
     * @param page
     * @param limit
     */
    public void getNewsList(int page, int limit) {

        OkGo.<LzyResponse<List<CreditNewsBean>>>post(Urls.URL_CREDIT_LIST)
                .tag(this)
                .params("ship", page)
                .params("limit", limit)
                .execute(new JsonCallback<LzyResponse<List<CreditNewsBean>>>() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<List<CreditNewsBean>>> response) {
                        if (HttpUtil.handleResponse(mActivity, response.body())) {
                            mView.showList(response.body().getDatas());
                        }

                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<LzyResponse<List<CreditNewsBean>>> response) {
                        HttpUtil.handleError(mActivity, response);
                    }
                });


    }
}
