package com.xinyuan.xyshop.ui.home.news;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.SegmentTabLayout;
import com.lzy.okgo.OkGo;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.CreditNewsAdapter;
import com.xinyuan.xyshop.adapter.NewsAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.bean.CreditNewsBean;
import com.xinyuan.xyshop.model.NewsModel;
import com.xinyuan.xyshop.mvp.contract.CreditNewsView;
import com.xinyuan.xyshop.mvp.presenter.CreditNewsPresenter;
import com.xinyuan.xyshop.mvp.presenter.NewsPresenter;
import com.xinyuan.xyshop.util.CommUtil;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.badgeview.BGABadgeImageView;

/**
 * Created by fx on 2017/7/5.
 * 积分动态列表fragment
 */

public class CreditNewsFragment extends BaseFragment<CreditNewsPresenter> implements CreditNewsView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.rv_news)
    RecyclerView rv_news;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.loadingView)
    XLoadingView loadingView;

    CreditNewsAdapter adapter;
    private int page = 1;
    private static final int limit = 20;
    private String mTitle;


    public static CreditNewsFragment getInstance(String title) {
        CreditNewsFragment sf = new CreditNewsFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_news;
    }

    @Override
    public void initView(View rootView) {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rv_news.setLayoutManager(manager);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
    }


    @Override
    public void initData() {
        mPresenter.getNewsList(page, limit);
    }


    @Override
    public void showList(final List<CreditNewsBean> list) {
        if (XEmptyUtils.isEmpty(adapter)) {
            if (XEmptyUtils.isEmpty(list)) {
                loadingView.showEmpty();
            } else {
                adapter = new CreditNewsAdapter(R.layout.fragment_news_credit_item, list);
                rv_news.setAdapter(adapter);
                adapter.setOnLoadMoreListener(this, rv_news);
                adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
                page++;
                loadingView.showContent();
            }

        } else {
            if (XEmptyUtils.isEmpty(list)) {
                if (page == 1) {
                    loadingView.showEmpty();
                } else {
                    adapter.loadMoreEnd();
                }

            } else {
                if (page == 1) {
                    adapter.setNewData(list);
                    mSwipeRefreshLayout.setRefreshing(false);
                    adapter.setEnableLoadMore(true);
                    loadingView.showContent();

                } else {
                    adapter.addData(list);
                    mSwipeRefreshLayout.setRefreshing(false);
                    adapter.notifyDataSetChanged();

                    adapter.loadMoreComplete();
                    loadingView.showContent();
                }
                page++;
            }
        }
    }

    @Override
    public void showState(int state) {
        switch (state) {
            case 0:
                loadingView.showLoading();
                break;
            case 1:
                loadingView.showContent();
                break;
            case 2:
                loadingView.showError();
                break;
            case 3:
                loadingView.showEmpty();
                break;

        }
    }

    @Override
    public LifecycleTransformer<List<CreditNewsBean>> bindLife() {
        return this.<List<CreditNewsBean>>bindUntilEvent(FragmentEvent.DESTROY_VIEW);
    }


    @Override
    protected CreditNewsPresenter createPresenter() {
        return new CreditNewsPresenter(getActivity(), this);
    }


    @Override
    public void onRefresh() {
        adapter.setEnableLoadMore(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                mPresenter.getNewsList(page, limit);
            }
        }, 500);
    }

    @Override
    public void onLoadMoreRequested() {
        rv_news.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.getNewsList(page, limit);
            }

        }, 500);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        page = 1;
    }

}
