package com.xinyuan.xyshop.ui.home.news;

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
import com.xinyuan.xyshop.adapter.NewsAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.model.NewsModel;
import com.xinyuan.xyshop.mvp.contract.NewView;
import com.xinyuan.xyshop.mvp.presenter.NewsPresenter;
import com.xinyuan.xyshop.util.CommUtil;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Created by fx on 2017/7/5.
 * 文章列表fragment
 */

public class NewsFragment extends BaseFragment<NewsPresenter> implements NewView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.rv_news)
    RecyclerView rv_news;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.loadingView)
    XLoadingView loadingView;

    private NewsAdapter newsAdapter;
    private int page = 1;
    private int limit = 20;
    private String mTitle;


    public static NewsFragment getInstance(String title) {
        NewsFragment sf = new NewsFragment();
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
        loadingView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getNewsList(page, limit);

            }
        });
    }


    @Override
    public void initData() {
        mPresenter.getNewsList(page, limit);
    }

    public void showList(final List<NewsModel> list) {
        if (XEmptyUtils.isEmpty(newsAdapter)) {
            if (XEmptyUtils.isEmpty(list)) {
                loadingView.showEmpty();
            } else {
                newsAdapter = new NewsAdapter(R.layout.fragment_news_item, list);
                rv_news.setAdapter(newsAdapter);
                newsAdapter.setOnLoadMoreListener(this, rv_news);
                newsAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
                newsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        HashMap<String, String> params;
                        params = new HashMap();
                        params.put(Constants.NEWID, String.valueOf(newsAdapter.getData().get(position).getId()));
                        params.put(Constants.NEWTITLE, newsAdapter.getData().get(position).getContent());
                        CommUtil.gotoActivity(mContext, NewsDetailActivity.class, false, params);
                    }
                });
                page++;
            }

        } else {
            if (XEmptyUtils.isEmpty(list)) {
                if (page == 1) {
                    loadingView.showEmpty();
                } else {
                    newsAdapter.loadMoreEnd();
                }

            } else {
                if (page == 1) {
                    newsAdapter.setNewData(list);
                    mSwipeRefreshLayout.setRefreshing(false);
                    newsAdapter.notifyDataSetChanged();
                    newsAdapter.setEnableLoadMore(true);

                } else {
                    newsAdapter.addData(list);
                    mSwipeRefreshLayout.setRefreshing(false);
                    newsAdapter.notifyDataSetChanged();
                    newsAdapter.loadMoreComplete();
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
    public LifecycleTransformer<List<NewsModel>> bindLife() {
        return this.<List<NewsModel>>bindUntilEvent(FragmentEvent.DESTROY_VIEW);
    }


    @Override
    protected NewsPresenter createPresenter() {
        return new NewsPresenter(getActivity(), this);
    }


    @Override
    public void onRefresh() {
        newsAdapter.setEnableLoadMore(false);
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
