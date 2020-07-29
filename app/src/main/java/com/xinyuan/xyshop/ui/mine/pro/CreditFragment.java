package com.xinyuan.xyshop.ui.mine.pro;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.CreditAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.bean.CreditBean;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.UserCreditModel;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.badgeview.BGABadgeImageView;

/**
 * Created by Administrator on 2017/6/26.
 */

public class CreditFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
	@BindView(R.id.rv_credit)
	RecyclerView rv_credit;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;

	@BindView(R.id.toolbar_iv)
	Toolbar msg_toolbar;
	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;
	@BindView(R.id.iv_header_right)
	BGABadgeImageView iv_header_right;

	@BindView(R.id.swipeLayout)
	SwipeRefreshLayout mSwipeRefreshLayout;

	@BindView(R.id.lodingView)
	XLoadingView lodingView;


	private CreditAdapter adapter;
	private static int page = 1;
	private static int ship = 1;
	private static final int limit = 20;
	private static String credite;

	public static CreditFragment newInstance(String credite) {
		CreditFragment fragment = new CreditFragment();
		fragment.credite = credite;
		return fragment;
	}

	private void inList(List<CreditBean> creditList) {
		this.adapter = new CreditAdapter(R.layout.fragment_credit_item, creditList);
		this.rv_credit.setAdapter(adapter);
		View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_credit_top, (ViewGroup) rv_credit.getParent(), false);
		TextView tv_credite_num = (TextView) view.findViewById(R.id.tv_credite_num);
		tv_credite_num.setText(credite);
		adapter.addHeaderView(view);
		adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
			@Override
			public void onLoadMoreRequested() {
				rv_credit.postDelayed(new Runnable() {
					@Override
					public void run() {
						getData(page);
					}

				}, 1000);


			}
		}, rv_credit);
		adapter.setAutoLoadMoreSize(3);
		lodingView.showContent();
	}

	private void getData(int ship) {
		OkGo.<LzyResponse<UserCreditModel>>post(Urls.URL_USER_CREDIT)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("id", MyShopApplication.userId)
				.params("ship", ship)
				.params("limit", limit)
				.execute(new JsonCallback<LzyResponse<UserCreditModel>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<UserCreditModel>> response) {
						if (HttpUtil.handleResponse(mContext, response.body())) {
							if (XEmptyUtils.isEmpty(adapter)) {
								if (XEmptyUtils.isEmpty(response.body().getDatas())) {
									lodingView.showEmpty();
								} else {
									inList(response.body().getDatas().getIntegralList());
									page++;
								}

							} else {
								if (XEmptyUtils.isEmpty(response.body().getDatas())) {
									adapter.loadMoreEnd();
								} else {
									if (page == 1) {
										page++;
										adapter.setNewData(response.body().getDatas().getIntegralList());
										mSwipeRefreshLayout.setRefreshing(false);
										adapter.setEnableLoadMore(true);
									} else {
										page++;
										adapter.addData(response.body().getDatas().getIntegralList());
										adapter.loadMoreComplete();

									}

								}

							}
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<UserCreditModel>> response) {
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

	@Override
	public void initView(View rootView) {
		if (msg_toolbar != null) {
			SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
			SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
			tv_header_center.setText("我的积分");
			CommUtil.initToolBar(getActivity(), iv_header_left, iv_header_right);
		}
		mSwipeRefreshLayout.setOnRefreshListener(this);
		mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(1);
		this.rv_credit.setLayoutManager(layoutManager);
		getData(ship);
	}

	@Override
	public void initData() {

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
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_credit;

	}

}
