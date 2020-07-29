package com.xinyuan.xyshop.ui.goods.credit;

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
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.CreditGoodsAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.model.CreditGoodModel;
import com.xinyuan.xyshop.mvp.contract.CreditMallDetailView;
import com.xinyuan.xyshop.mvp.presenter.CreditMallDetailPresenter;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.loadingview.XLoadingView;

import butterknife.BindView;
import cn.bingoogolapple.badgeview.BGABadgeImageView;

/**
 * Created by fx on 2017/6/22.
 * 积分商城页面
 */

public class CreditMallDetailFragment extends BaseFragment<CreditMallDetailPresenter> implements CreditMallDetailView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
	@BindView(R.id.swipeLayout)
	SwipeRefreshLayout swipeLayout;
	@BindView(R.id.recycler_view)
	RecyclerView recycler_view;
	@BindView(R.id.toolbar_iv)
	Toolbar toolbar_iv;
	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.iv_header_right)
	BGABadgeImageView iv_header_right;

	@BindView(R.id.lodingView)
	XLoadingView lodingView;

	private CreditGoodsAdapter adapter;
	private int type;
	private String title;
	private int page = 1;
	private int limit = 10;

	public static CreditMallDetailFragment newInstance(int type, String title) {
		CreditMallDetailFragment fragment = new CreditMallDetailFragment();
		fragment.type = type;
		fragment.title = title;
		return fragment;
	}

	@Override
	public void initView(View rootView) {
		tv_header_center.setText(title);
		SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_iv);

		CommUtil.initToolBar(getActivity(), iv_header_left, iv_header_right);
		swipeLayout.setOnRefreshListener(this);
		swipeLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
		GridLayoutManager layoutManager = new GridLayoutManager(mContext, 2, 1, false);
		recycler_view.setLayoutManager(layoutManager);

	}

	@Override
	public void initData() {
		mPresenter.getData(type, page, limit);
		lodingView.setOnRetryClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mPresenter.getData(type, page, limit);
			}
		});
	}


	@Override
	public void showList(CreditGoodModel creditMallModel) {
		if (XEmptyUtils.isEmpty(adapter)) {
			if (XEmptyUtils.isEmpty(creditMallModel.getGoodlist())) {
				lodingView.showEmpty();
			} else {
				adapter = new CreditGoodsAdapter(R.layout.item_credit_good, creditMallModel.getGoodlist());
				adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
				recycler_view.setAdapter(adapter);
				adapter.setOnLoadMoreListener(this);
				lodingView.showContent();
				page++;
			}

		} else {
			if (XEmptyUtils.isEmpty(creditMallModel.getGoodlist())) {
				if (page == 1) {
					lodingView.showEmpty();
				} else {
					adapter.loadMoreEnd();

				}
			} else {
				if (page == 1) {
					adapter.setNewData(creditMallModel.getGoodlist());
					swipeLayout.setRefreshing(false);
					adapter.notifyDataSetChanged();
					adapter.setEnableLoadMore(true);
				} else {
					adapter.addData(creditMallModel.getGoodlist());
					adapter.loadMoreComplete();
					swipeLayout.setRefreshing(false);
					adapter.notifyDataSetChanged();

				}
				page++;

			}

		}
	}

	@Override
	public void onRefresh() {
		adapter.setEnableLoadMore(false);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				page = 1;
				mPresenter.getData(type, page, limit);
			}
		}, 500);
	}

	@Override
	public LifecycleTransformer<CreditGoodModel> bindLife() {
		return this.<CreditGoodModel>bindUntilEvent(FragmentEvent.DESTROY_VIEW);
	}

	@Override
	public void onSupportVisible() { //当fragment可见时，检查是否有新消息
		if (MyShopApplication.IsNewMsg) {
			iv_header_right.showCirclePointBadge();
		} else {
			iv_header_right.hiddenBadge();
		}
		super.onSupportVisible();
	}

	@Override
	protected CreditMallDetailPresenter createPresenter() {
		return new CreditMallDetailPresenter(getActivity(), this);
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_credit_detail;
	}

	@Override
	public void onLoadMoreRequested() {
		recycler_view.postDelayed(new Runnable() {
			@Override
			public void run() {
				mPresenter.getData(type, page, limit);
			}

		}, 500);
	}
}
