package com.xinyuan.xyshop.ui.goods.detail.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.GoodsGridAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.RecomGoodModel;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailActivity;
import com.xinyuan.xyshop.ui.goods.store.StoreFragment;
import com.xinyuan.xyshop.ui.mine.info.FavGoodFragment;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by f-x on 2017/10/2709:22
 */

public class GoodEmptyActivity extends BaseActivity {
    @BindView(R.id.rv_good)
    RecyclerView rv_good;
    @BindView(R.id.toolbar_tv)
    Toolbar toolbar_tv;
    @BindView(R.id.tv_header_center)
    TextView tv_header_center;
    @BindView(R.id.iv_header_left)
    ImageView iv_header_left;
    @BindView(R.id.lodingView)
    XLoadingView lodingView;
    private GoodsGridAdapter goodsAdapter;


    @Override
    public void initData() {
        getGoods();
        lodingView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getGoods();

            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_good_empty;
    }

    @Override
    public void initView() {
        SystemBarHelper.immersiveStatusBar(this, 0); //设置状态栏透明
        SystemBarHelper.setHeightAndPadding(this, toolbar_tv);
        tv_header_center.setText("商品过期不存在");
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        this.rv_good.setLayoutManager(layoutManager);
        CommUtil.initToolBar(this, iv_header_left, null);
    }

    private void getGoods() {
        /**
         * 获取商品推荐数据
         */
        OkGo.<LzyResponse<RecomGoodModel>>get(Urls.URL_GOODS_RECOME_GOODS)
                .execute(new JsonCallback<LzyResponse<RecomGoodModel>>() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<RecomGoodModel>> response) {
                        if (HttpUtil.handleResponse(GoodEmptyActivity.this, response.body())) {
                            showGood(response.body().getDatas());
                        }
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<LzyResponse<RecomGoodModel>> response) {
                        HttpUtil.handleError(GoodEmptyActivity.this, response);
                    }
                });
    }

    private void showGood(RecomGoodModel datas) {
        if (XEmptyUtils.isEmpty(goodsAdapter)) {
            if (XEmptyUtils.isEmpty(datas.getGoodlist())) {
                lodingView.showEmpty();
            } else {
                this.goodsAdapter = new GoodsGridAdapter(R.layout.item_good_grid, datas.getGoodlist());
                this.rv_good.setAdapter(goodsAdapter);
                goodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Map<String, String> params = new HashMap();
                        params.put(Constants.GOODID, goodsAdapter.getData().get(position).getGoodsId());
                        params.put(Constants.GOODTYPE, goodsAdapter.getData().get(position).getGoodsType());
                        CommUtil.gotoActivity(GoodEmptyActivity.this, GoodDetailActivity.class, false, params);
                    }
                });
                View bootomView = LayoutInflater.from(this).inflate(R.layout.fragment_good_empty_top, (ViewGroup) rv_good.getParent(), false);
                goodsAdapter.addHeaderView(bootomView);
                rv_good.scrollToPosition(0);
                lodingView.showContent();
            }
        }
    }


}


