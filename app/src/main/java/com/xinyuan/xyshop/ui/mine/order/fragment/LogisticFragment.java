package com.xinyuan.xyshop.ui.mine.order.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.LogisticAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.ExpressBean;
import com.xinyuan.xyshop.bean.ExpressInfoBean;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.ExpressModel;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.Collections;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/7/3.
 * 物流详情
 */

public class LogisticFragment extends BaseFragment {
    @BindView(R.id.rv_logis)
    RecyclerView rv_logis;
    @BindView(R.id.toolbar_iv)
    Toolbar toolbar_iv;
    @BindView(R.id.iv_header_left)
    ImageView iv_header_left;
    @BindView(R.id.iv_header_right)
    ImageView iv_header_right;
    @BindView(R.id.tv_header_center)
    TextView tv_header_center;

    @BindView(R.id.loadingView)
    XLoadingView loadingView;
    private LogisticAdapter adapter;
    private String goodImg;
    private String orderId;

    public static LogisticFragment getInstance(String goodImg, String orderId) {
        LogisticFragment sf = new LogisticFragment();
        sf.goodImg = goodImg;
        sf.orderId = orderId;
        return sf;
    }


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_logistic;
    }


    @Override
    public void initView(View rootView) {
        SystemBarHelper.immersiveStatusBar(getActivity()); //设置状态栏透明
        SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_iv);
        tv_header_center.setText("物流查询");
        CommUtil.initToolBar(getActivity(), iv_header_left, iv_header_right);

    }

    TextView tv_express_name;

    private void inRV(ExpressModel model) {
        if (!XEmptyUtils.isSpace(model.getInfo())) {
            Gson gson = new Gson();
            ExpressBean expressBean = gson.fromJson(model.getInfo(), ExpressBean.class);

            if (!expressBean.isSuccess()) {
                adapter.setEmptyView(R.layout.activity_logistic_empty, (ViewGroup) rv_logis.getParent());
            }

            View headView = getLayoutInflater().inflate(R.layout.activity_logistic_top, (ViewGroup) rv_logis.getParent(), false);
            ImageView iv_good_img = (ImageView) headView.findViewById(R.id.iv_good_img);
            TextView tv_express_status = (TextView) headView.findViewById(R.id.tv_express_status);
            tv_express_name = (TextView) headView.findViewById(R.id.tv_express_name);
            TextView tv_order_id = (TextView) headView.findViewById(R.id.tv_order_id);
            getExpressName(expressBean.getShipperCode());
            tv_order_id.setText(expressBean.getLogisticCode());
            if (!XEmptyUtils.isEmpty(goodImg)) {
                GlideImageLoader.setUrlImg(mContext, goodImg, iv_good_img);
            }
            rv_logis.setHasFixedSize(true);
            rv_logis.setLayoutManager(new LinearLayoutManager(mContext));

            Collections.reverse(expressBean.getTraces());
            adapter = new LogisticAdapter(R.layout.activity_logistic_item, expressBean.getTraces());
            rv_logis.setAdapter(adapter);

            switch (Integer.parseInt(expressBean.getState())) {
                case 2:
                    tv_express_status.setText("运输中");
                    break;
                case 3:
                    tv_express_status.setText("已签收");
                    break;
                case 4:
                    tv_express_status.setText("问题件");
                    break;
            }
            adapter.addHeaderView(headView);
        } else {
            loadingView.showEmpty();
        }

    }

    private void getExpressName(String shipperCode) {
        OkGo.<LzyResponse<ExpressInfoBean>>get(Urls.URL_ORDER_EXPRESS_NAME + shipperCode)
                .tag(this)
                .execute(new JsonCallback<LzyResponse<ExpressInfoBean>>() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<ExpressInfoBean>> response) {
                        if (HttpUtil.handleResponse(mContext, response.body())) {
                            if (!XEmptyUtils.isEmpty(response.body().getDatas())) {
                                XToast.info(response.body().getDatas().getInfo());
                                tv_express_name.setText(response.body().getDatas().getInfo());
                            }
                            loadingView.showContent();
                        }
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<LzyResponse<ExpressInfoBean>> response) {
                        HttpUtil.handleError(mContext, response);
                    }
                });

    }

    @Override
    public void initData() {
        OkGo.<LzyResponse<ExpressModel>>post(Urls.URL_ORDER_EXPRESS)
                .tag(this)
                .headers("token", MyShopApplication.Token)
                .params("userId", MyShopApplication.userId)
                .params("orderFormId", orderId)
                .execute(new JsonCallback<LzyResponse<ExpressModel>>() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<ExpressModel>> response) {
                        if (HttpUtil.handleResponse(mContext, response.body())) {
                            inRV(response.body().getDatas());
                        }
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<LzyResponse<ExpressModel>> response) {
                        HttpUtil.handleError(mContext, response);
                    }
                });
    }


}
