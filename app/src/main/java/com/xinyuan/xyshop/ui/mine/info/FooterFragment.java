package com.xinyuan.xyshop.ui.mine.info;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.FavAdapters;
import com.xinyuan.xyshop.adapter.FooterAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.FooterModel;
import com.xinyuan.xyshop.model.HomeModel;
import com.xinyuan.xyshop.mvp.contract.FooterView;
import com.xinyuan.xyshop.mvp.presenter.FooterPresenter;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.XToast;
import com.youth.xframe.widget.loadingview.XLoadingView;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/26.
 */

public class FooterFragment extends BaseFragment<FooterPresenter> implements FooterView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.toolbar_tv)
	Toolbar toolbar_tv;
	@BindView(R.id.rv_footer)
	RecyclerView rv_footer;

	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;
	@BindView(R.id.lodingView)
	XLoadingView loadingView;
	@BindView(R.id.swipeLayout)
	SwipeRefreshLayout mSwipeRefreshLayout;
	private int page = 1;
	private int limit = 15;
	private FooterAdapter footerAdapter;

	public static FooterFragment newInstance() {
		FooterFragment fragment = new FooterFragment();
		return fragment;
	}


	@Override
	public void initView(View rootView) {
		SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_tv);
		tv_header_center.setText("我的足迹");
		CommUtil.initToolBar(getActivity(), iv_header_left, null);
		GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3, 1, false);
		this.rv_footer.setLayoutManager(layoutManager);
		mSwipeRefreshLayout.setOnRefreshListener(this);
		mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
	}

	@Override
	public void initData() {
		mPresenter.getFooterGood(page, limit);
	}

	@Override
	protected FooterPresenter createPresenter() {
		return new FooterPresenter(getActivity(), this);
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_footer;
	}

	@Override
	public void showList(FooterModel footerModel) {
		if (XEmptyUtils.isEmpty(footerAdapter)) {

			if (XEmptyUtils.isEmpty(footerModel)) {
				showState(3);
			} else {
				footerAdapter = new FooterAdapter(R.layout.fragment_footer_item, footerModel.getFooterList());
				rv_footer.setAdapter(footerAdapter);
				footerAdapter.setOnLoadMoreListener(this);
				footerAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
				showState(1);
				page++;
			}


		} else {
			if (XEmptyUtils.isEmpty(footerModel)) {
				footerAdapter.loadMoreEnd();
			} else {
				if (page == 1) {
					footerAdapter.setNewData(footerModel.getFooterList());
					mSwipeRefreshLayout.setRefreshing(false);
					footerAdapter.setEnableLoadMore(true);
				} else {
					footerAdapter.addData(footerModel.getFooterList());
					footerAdapter.loadMoreComplete();
				}
				page++;
			}

		}

	}

	@Override
	public void onLoadMoreRequested() {
		rv_footer.postDelayed(new Runnable() {
			@Override
			public void run() {
				mPresenter.getFooterGood(page, limit);
			}

		}, 500);

	}

	@Override
	public void onRefresh() {
		footerAdapter.setEnableLoadMore(false);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				page = 1;
				mPresenter.getFooterGood(page, limit);
			}
		}, 1000);
	}

	@Override
	public LifecycleTransformer<FooterModel> bindLife() {
		return this.<FooterModel>bindUntilEvent(FragmentEvent.DESTROY_VIEW);

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

		}

	}

}
