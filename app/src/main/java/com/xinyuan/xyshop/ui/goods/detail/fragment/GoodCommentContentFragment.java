package com.xinyuan.xyshop.ui.goods.detail.fragment;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.SimpleEvaluateAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.model.EvaluateModel;
import com.xinyuan.xyshop.mvp.contract.CommentContentView;
import com.xinyuan.xyshop.mvp.presenter.GoodCommentPresenter;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailActivity;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.loadingview.XLoadingView;

import butterknife.BindView;

/**
 * Created by fx on 2017/8/14.
 * 商品评论列表
 */

public class GoodCommentContentFragment extends BaseFragment<GoodCommentPresenter> implements CommentContentView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_commont)
    RecyclerView rv_comment;
    @BindView(R.id.lodingView)
    XLoadingView lodingView;
    SimpleEvaluateAdapter simpleEvaluateAdapter;
    public String type;
    public static int page = 1;
    private int limit = 5;

    public static GoodCommentContentFragment getInstance(String type) {
        GoodCommentContentFragment sf = new GoodCommentContentFragment();
        sf.type = type;
        return sf;
    }

    @Override
    public void initView(View rootView) {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
    }

    @Override
    public void initData() {
        mPresenter.getCommentList(GoodDetailActivity.goodsId, type, page, limit);

    }

    @Override
    protected GoodCommentPresenter createPresenter() {
        return new GoodCommentPresenter(getActivity(), this);
    }


    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_good_com_content;
    }

    @Override
    public void showList(EvaluateModel evaModel) {
        if (XEmptyUtils.isEmpty(simpleEvaluateAdapter)) {
            if (XEmptyUtils.isEmpty(evaModel.getEvaluateList())) {
                lodingView.showEmpty();
            } else {
                this.simpleEvaluateAdapter = new SimpleEvaluateAdapter(R.layout.fragment_good_item_evaluate, evaModel.getEvaluateList());
                simpleEvaluateAdapter.setOnLoadMoreListener(this, rv_comment);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                rv_comment.setLayoutManager(linearLayoutManager);
                this.rv_comment.setAdapter(this.simpleEvaluateAdapter);
                this.simpleEvaluateAdapter.notifyDataSetChanged();
                lodingView.showContent();
                page++;
            }

        } else {
            if (XEmptyUtils.isEmpty(evaModel.getEvaluateList())) {
                if (page == 1) {
                    lodingView.showEmpty();
                } else {
                    simpleEvaluateAdapter.loadMoreEnd();
                }
            } else {
                if (page == 1) {
                    simpleEvaluateAdapter.setNewData(evaModel.getEvaluateList());
                    mSwipeRefreshLayout.setRefreshing(false);
                    simpleEvaluateAdapter.setEnableLoadMore(true);
                } else {
                    simpleEvaluateAdapter.addData(evaModel.getEvaluateList());
                    mSwipeRefreshLayout.setRefreshing(false);
                    simpleEvaluateAdapter.loadMoreComplete();

                }
                lodingView.showContent();

            }
            page++;

        }


    }

    @Override
    public LifecycleTransformer<EvaluateModel> bindLife() {
        return this.<EvaluateModel>bindUntilEvent(FragmentEvent.DESTROY_VIEW);
    }

    @Override
    public void onRefresh() {
        simpleEvaluateAdapter.setEnableLoadMore(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                mPresenter.getCommentList(GoodDetailActivity.goodsId, type, page, limit);
            }
        }, 500);
    }

    @Override
    public void onLoadMoreRequested() {
        rv_comment.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (XEmptyUtils.isEmpty(mPresenter)) {
                    mPresenter.getCommentList(GoodDetailActivity.goodsId, type, page, limit);
                }
            }

        }, 500);
    }
}
