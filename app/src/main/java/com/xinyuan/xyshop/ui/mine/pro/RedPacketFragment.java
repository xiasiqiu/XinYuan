package com.xinyuan.xyshop.ui.mine.pro;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.CouponAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.CouponBean;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/26.
 */

public class RedPacketFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.toolbar_iv)
    Toolbar toolbar_iv;
    @BindView(R.id.tv_header_center)
    TextView tv_header_center;
    @BindView(R.id.iv_header_left)
    ImageView iv_header_left;
    @BindView(R.id.iv_header_right)
    ImageView iv_header_right;
    @BindView(R.id.rv_coupon)
    RecyclerView rv_coupon;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.lodingView)
    XLoadingView lodingView;
    CouponAdapter adapter;

    private static int page = 1;
    private static int ship = 1;
    private static final int limit = 20;

    public static RedPacketFragment newInstance() {
        RedPacketFragment fragment = new RedPacketFragment();
        return fragment;
    }


    @Override
    public void initView(View rootView) {
        SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
        SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_iv);
        tv_header_center.setText("我的红包");
        CommUtil.initToolBar(getActivity(), iv_header_left, iv_header_right);


        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(1);
        this.rv_coupon.setLayoutManager(layoutManager);
        getData(ship);
    }

    @Override
    public void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_coupon;

    }


    private void inList(List<CouponBean> couponList) {
        this.adapter = new CouponAdapter(R.layout.fragment_coupon_item, couponList);
        this.rv_coupon.setAdapter(adapter);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                rv_coupon.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData(page);
                    }

                }, 1000);


            }
        }, rv_coupon);
        adapter.setAutoLoadMoreSize(3);
        lodingView.showContent();
    }

    private void getData(int ship) {
        OkGo.<LzyResponse<List<CouponBean>>>post(Urls.URL_USER_RED_PACKT)
                .tag(this)
                .headers("token", MyShopApplication.Token)
                .params("id", MyShopApplication.userId)
                .params("ship", ship)
                .params("limit", limit)
                .execute(new JsonCallback<LzyResponse<List<CouponBean>>>() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<List<CouponBean>>> response) {
                        if (HttpUtil.handleResponse(mContext, response.body())) {
                            if (XEmptyUtils.isEmpty(adapter)) {
                                if (XEmptyUtils.isEmpty(response.body().getDatas())) {
                                    lodingView.showEmpty();
                                } else {
                                    inList(response.body().getDatas());
                                    page++;
                                }

                            } else {
                                if (XEmptyUtils.isEmpty(response.body().getDatas())) {
                                    adapter.loadMoreEnd();
                                } else {
                                    if (page == 1) {
                                        page++;
                                        adapter.setNewData(response.body().getDatas());
                                        mSwipeRefreshLayout.setRefreshing(false);
                                        adapter.setEnableLoadMore(true);

                                    } else {
                                        page++;
                                        adapter.addData(response.body().getDatas());
                                        adapter.loadMoreComplete();

                                    }

                                }

                            }
                        }
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<LzyResponse<List<CouponBean>>> response) {
                        HttpUtil.handleError(mContext, response);
                    }
                });

    }


    @Override
    public void onRefresh() {
        adapter.setEnableLoadMore(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                getData(page);
            }
        }, 1000);
    }


}
