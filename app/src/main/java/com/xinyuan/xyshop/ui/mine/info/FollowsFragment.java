package com.xinyuan.xyshop.ui.mine.info;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.FavAdapters;
import com.xinyuan.xyshop.adapter.FollowAdapters;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.bean.FavStoreBean;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.FavGoodModel;
import com.xinyuan.xyshop.model.FavStoreModel;
import com.xinyuan.xyshop.ui.goods.store.StoreActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.RecycleViewDivider;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fx on 2017/6/26.
 * 关注店铺列表Fragment
 */

public class FollowsFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recycler_view)
    RecyclerView rv_fav;
    @BindView(R.id.tv_header_center)
    TextView tv_header_center;
    @BindView(R.id.tv_header_right)
    TextView tv_header_right;
    @BindView(R.id.toolbar_tv)
    Toolbar msg_toolbar;
    @BindView(R.id.lodingView)
    XLoadingView lodingView;
    @BindView(R.id.iv_header_left)
    ImageView iv_header_left;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rl_edit)
    RelativeLayout rl_edit;
    List<FavStoreBean> storeList;

    private boolean falg = true;
    private static final int limit = 10;
    private int page = 1;
    private FollowAdapters followAdapters;
    private LinearLayoutManager manager;

    private List<String> idList = new ArrayList<>();

    public static FollowsFragment newInstance() {
        FollowsFragment fragment = new FollowsFragment();
        return fragment;
    }

    @Override
    public void initView(View rootView) {
        lodingView.showLoading();
        tv_header_center.setText("收藏夹");
        SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
        SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
        CommUtil.initToolBar(getActivity(), iv_header_left, null);

    }

    @Override
    public void initData() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(1);
        rv_fav.setLayoutManager(new LinearLayoutManager(mContext));// 布局管理器。
        getData(page);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_favorite;

    }

    /**
     * 显示列表
     *
     * @param favStoreModel
     */
    private void showStoreList(FavStoreModel favStoreModel) {
        if (XEmptyUtils.isEmpty(followAdapters)) {
            if (XEmptyUtils.isEmpty(favStoreModel)) {
                lodingView.showEmpty();
            } else {
                this.storeList = favStoreModel.getStoreList();
                followAdapters = new FollowAdapters(R.layout.fragment_follow_item, storeList, false);
                rv_fav.setAdapter(followAdapters);
                followAdapters.setOnLoadMoreListener(this);
                followAdapters.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
                followAdapters.setAutoLoadMoreSize(3);
                tv_header_right.setText("编辑");
                tv_header_right.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (falg) {
                            followAdapters.isDetele = true;
                            falg = false;
                            rl_edit.setVisibility(View.VISIBLE);
                            followAdapters.setNewData(storeList);
                            tv_header_right.setText("完成");
                        } else {
                            followAdapters.isDetele = false;
                            falg = true;
                            rl_edit.setVisibility(View.GONE);
                            tv_header_right.setText("编辑");
                            followAdapters.setNewData(followAdapters.getData());
                        }
                    }
                });
                lodingView.showContent();
                page++;
            }

        } else {
            if (XEmptyUtils.isEmpty(favStoreModel)) {
                followAdapters.loadMoreEnd();
            } else {
                if (page == 1) {
                    followAdapters.setNewData(favStoreModel.getStoreList());
                    mSwipeRefreshLayout.setRefreshing(false);
                    followAdapters.setEnableLoadMore(true);
                } else {
                    followAdapters.addData(favStoreModel.getStoreList());
                    followAdapters.loadMoreComplete();
                }
                page++;
            }

        }


    }


    @OnClick({R.id.bt_detelte, R.id.bt_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_detelte:
                if (!XEmptyUtils.isEmpty(followAdapters.choeseList)) {
                    Iterator iter = followAdapters.choeseList.entrySet().iterator();
                    List<Integer> position = new ArrayList<>();
                    while (iter.hasNext()) {
                        Map.Entry entry = (Map.Entry) iter.next();
                        idList.add((String) entry.getValue());
                        position.add((Integer) entry.getKey());
                    }
                    Collections.sort(position, new Comparator<Integer>() {
                        @Override
                        public int compare(Integer o1, Integer o2) {
                            int i = o1 - o2;
                            if (i == 0) {
                                return o1 - o2;
                            }
                            return i;
                        }
                    });
                    for (int i = 0; i < position.size(); i++) {
                        followAdapters.remove(position.get(i) - i);
                    }
                    if (followAdapters.getData().size() == 0) {
                        lodingView.showEmpty();
                    }
                    followAdapters.notifyDataSetChanged();
                    detele(idList);
                } else {
                    XToast.error("还未选择店铺");
                }

                break;
            case R.id.bt_share:
                XToast.info("分享了" + followAdapters.choeseList.toString());
                break;
        }
    }


    @Override
    public void onRefresh() {
        followAdapters.setEnableLoadMore(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                getData(page);
            }
        }, 1000);
    }

    @Override
    public void onLoadMoreRequested() {
        rv_fav.postDelayed(new Runnable() {
            @Override
            public void run() {
                getData(page);
            }

        }, 500);

    }

    /**
     * 获取收藏店铺列表
     *
     * @param ship
     */
    private void getData(int ship) {
        OkGo.<LzyResponse<FavStoreModel>>post(Urls.URL_USER_FAV_STORE)
                .tag(this)
                .headers("token", MyShopApplication.Token)
                .params("id", MyShopApplication.userId)
                .params("ship", ship)
                .params("limit", limit)
                .execute(new JsonCallback<LzyResponse<FavStoreModel>>() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<FavStoreModel>> response) {
                        if (HttpUtil.handleResponse(mContext, response.body())) {
                            showStoreList(response.body().getDatas());
                        }
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<LzyResponse<FavStoreModel>> response) {
                        HttpUtil.handleError(mContext, response);
                    }
                });
    }

    /**
     * 删除收藏店铺
     *
     * @param idList
     */
    private void detele(List<String> idList) {
        OkGo.<LzyResponse<FavGoodModel>>post(Urls.URL_USER_FAV_GOODS_DETELE)
                .tag(this)
                .headers("token", MyShopApplication.Token)
                .params("id", MyShopApplication.userId)
                .params("favorteId", idList.toString())
                .execute(new JsonCallback<LzyResponse<FavGoodModel>>() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<FavGoodModel>> response) {
                        if (HttpUtil.handleResponse(mContext, response.body())) {
                            XToast.info("删除成功");
                            followAdapters.choeseList.clear();
                        }
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<LzyResponse<FavGoodModel>> response) {
                        HttpUtil.handleError(mContext, response);
                    }
                });

    }
}
