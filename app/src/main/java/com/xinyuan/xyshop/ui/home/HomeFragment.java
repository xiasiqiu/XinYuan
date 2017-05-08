package com.xinyuan.xyshop.ui.home;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.HomeMultipleItemAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.common.RxBus;
import com.xinyuan.xyshop.common.RxBusResult;
import com.xinyuan.xyshop.entity.HomeMultipleItem;
import com.xinyuan.xyshop.entity.ItemData;
import com.youth.xframe.utils.log.XLog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fx on 2017/5/2 0002.
 */

public class HomeFragment extends BaseFragment {


	@BindView(R.id.home_list)
	RecyclerView mRecyclerView;
	@BindView(R.id.home_swipeLayout)
	SwipeRefreshLayout mSwipeRefreshLayout;


	private List<HomeMultipleItem> multipleItems;
	private HomeShowViewHelper homeShowViewHelper;
	private HomeDataLoadHelper dataLoadHelper;
	private HomeMultipleItemAdapter homeMultipleItemAdapter;


	private boolean isErr;
	private boolean mLoadMoreEndGone = false;
	private int delayMillis = 1000;

	private Context context;

	private RxBus rxBus = RxBus.getInstance();

	private Toolbar mToolbar;
	private Button mScan;
	private Button mMsg;


	@Override
	protected void lazyLoad() {


	}

	@Override
	public View initLayout(LayoutInflater inflater, ViewGroup container, boolean b) {
		View rootView = inflater.inflate(R.layout.fragment_home, null);
		mToolbar = (Toolbar) rootView.findViewById(R.id.home_toolbar);
		mScan = (Button) rootView.findViewById(R.id.act_home_btn_scan);
		mMsg = (Button) rootView.findViewById(R.id.act_home_btn_msg);
		ButterKnife.bind(this, rootView);
		return rootView;
	}

	@Override
	protected void initData(@Nullable Bundle savedInstanceState) {
		context = this.getContext();
		homeShowViewHelper = new HomeShowViewHelper(context, mRecyclerView);
		dataLoadHelper = new HomeDataLoadHelper(context);
		HomeDataLoadHelper.getHomeData(this.getContext(), homeShowViewHelper);
		rxBus.toObserverableOnMainThread("dataload", new RxBusResult() {
			@Override
			public void onRxBusResult(Object o) {
				addHead();
			}
		});
	}


	public void addHead() {


		List<ItemData> bannerList = HomeDataLoadHelper.getBanner();
		List<ItemData> menuList = HomeDataLoadHelper.getMenu();
		View headView = getActivity().getLayoutInflater().inflate(R.layout.home_top, (ViewGroup) mRecyclerView.getParent(), false);


		homeShowViewHelper.showBanner(bannerList, headView);
		homeShowViewHelper.showHomeMenu(menuList, headView);
		multipleItems = HomeDataLoadHelper.getHomeMultipleItemlist();
		homeMultipleItemAdapter = new HomeMultipleItemAdapter(this.getContext(), multipleItems);


		final GridLayoutManager manager = new GridLayoutManager(context, 4);
		mRecyclerView.setLayoutManager(manager);
		homeMultipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
			@Override
			public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {

				return multipleItems.get(position).getSpanSize();
			}
		});
		homeMultipleItemAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
		mRecyclerView.setAdapter(homeMultipleItemAdapter);
		homeMultipleItemAdapter.addHeaderView(headView);

		mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				//滑动的距离
				mDistanceY += dy;
				//toolbar的高度
				int toolbarHeight = mToolbar.getBottom();

				if (mDistanceY < 10) {
					mToolbar.setBackgroundResource(R.color.bg_white);
					mScan.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.act_home_scan_de), null, null);
					mMsg.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.act_home_msg_de), null, null);
					mScan.setTextColor(getResources().getColor(R.color.tv_hint));
					mMsg.setTextColor(getResources().getColor(R.color.tv_hint));

				} else if (mDistanceY <= toolbarHeight) {

					float scale = (float) mDistanceY / toolbarHeight;
					float alpha = scale * 255;
					mToolbar.setBackgroundColor(Color.argb((int) alpha, 6, 111, 0));
					mScan.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.act_home_scan), null, null);
					mMsg.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.act_home_msg), null, null);
					mScan.setTextColor(getResources().getColor(R.color.bg_white));
					mMsg.setTextColor(getResources().getColor(R.color.bg_white));

				} else {
					mToolbar.setBackgroundResource(R.color.colorPrimary);
					mScan.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.act_home_scan), null, null);
					mMsg.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.act_home_msg), null, null);
					mScan.setTextColor(getResources().getColor(R.color.bg_white));
					mMsg.setTextColor(getResources().getColor(R.color.bg_white));

				}


			}
		});
	}

	private int mDistanceY = 0;
}
