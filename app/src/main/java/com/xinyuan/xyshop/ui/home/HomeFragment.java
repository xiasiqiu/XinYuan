package com.xinyuan.xyshop.ui.home;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sunfusheng.marqueeview.MarqueeView;
import com.trello.rxlifecycle.FragmentEvent;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.HomeMultipleItemAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.HomeMultipleItem;
import com.xinyuan.xyshop.entity.ItemData;
import com.xinyuan.xyshop.entity.ItemGoods;
import com.xinyuan.xyshop.mvp.contract.HomeContract;
import com.xinyuan.xyshop.mvp.presenter.HomePresenterImpl;
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.xinyuan.xyshop.util.Image;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fx on 2017/5/9 0009.
 */

public class HomeFragment extends BaseFragment implements HomeContract.HomeView, SwipeRefreshLayout.OnRefreshListener {


	@BindView(R.id.home_list)
	RecyclerView mRecyclerView;
	@BindView(R.id.home_swipeLayout)
	SwipeRefreshLayout mSwipeRefreshLayout;
	@BindView(R.id.home_loadingView)
	XLoadingView xLoadingView;

	private Toolbar mToolbar;
	private Button mScan;
	private Button mMsg;

	private View headView;


	private Context context;
	private HomeContract.HomePresenter presenter;
	private HomeMultipleItemAdapter homeMultipleItemAdapter;
	private int mDistanceY = 0;

	private static final int TOTAL_COUNTER = 30;

	private static final int PAGE_SIZE = 6;


	@Override
	protected void lazyLoad() {

	}

	@Override
	public View initLayout(LayoutInflater inflater, ViewGroup container, boolean b) {
		View rootView = inflater.inflate(R.layout.fragment_home, null);
		mToolbar = (Toolbar) rootView.findViewById(R.id.home_toolbar);
		mScan = (Button) rootView.findViewById(R.id.act_home_btn_scan);
		mMsg = (Button) rootView.findViewById(R.id.act_home_btn_msg);
		SystemBarHelper.immersiveStatusBar(getActivity(), 0);
		SystemBarHelper.setHeightAndPadding(getActivity(), mToolbar);
		new HomePresenterImpl(this);
		context = getActivity();
		ButterKnife.bind(this, rootView);
		return rootView;

	}

	@Override
	@CallSuper
	public void onResume() {
		super.onResume();
		mDistanceY=0;
	}

	@Override
	public void setPresenter(HomeContract.HomePresenter presenter) {
		this.presenter = presenter;

	}

	@Override
	protected void initData(@Nullable Bundle savedInstanceState) {
		presenter.initData();
		xLoadingView.setOnRetryClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				presenter.initData();
				xLoadingView.showLoading();
			}
		});


	}

	@Override
	public void showState(int Sate) {
		switch (Sate) {
			case 0:
				XLog.v("首页数据加载状态中");
				xLoadingView.showLoading();
				break;
			case 1:
				XLog.v("首页数据加载完毕");
				xLoadingView.showContent();
				break;
			case 2:
				XLog.v("首页数据加载失败");
				xLoadingView.showError();
				break;

		}

	}

	@Override
	public void addHead(final List<HomeMultipleItem> list) {


		mSwipeRefreshLayout.setOnRefreshListener(this);

		mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));

		headView = getActivity().getLayoutInflater().inflate(R.layout.home_top, (ViewGroup) mRecyclerView.getParent(), false);

		final GridLayoutManager manager = new GridLayoutManager(context, 4);
		mRecyclerView.setLayoutManager(manager);

		homeMultipleItemAdapter = new HomeMultipleItemAdapter(this.getContext(), list);

		homeMultipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
			@Override
			public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {

				return list.get(position).getSpanSize();
			}
		});

		//homeMultipleItemAdapter.setOnLoadMoreListener(this, mRecyclerView);


		homeMultipleItemAdapter.addHeaderView(headView);


		mRecyclerView.setAdapter(homeMultipleItemAdapter);
	}


	@Override
	public void showBanner(final List<ItemData> itemList) {
		ArrayList<String> images = new ArrayList<>();
		for (ItemData itemData : itemList) {
			images.add(itemData.getImageUrl());
		}

		final Banner banner = (Banner) headView.findViewById(R.id.banner);
		banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
				.setImageLoader(new GlideImageLoader())
				.setImages(images)
				.setBannerAnimation(Transformer.DepthPage)
				.isAutoPlay(true)
				.setDelayTime(3000)
				.setIndicatorGravity(BannerConfig.CENTER)
				.start();
		banner.setOnBannerListener(new OnBannerListener() {
			@Override
			public void OnBannerClick(int position) {
				if (itemList != null) {

					ItemData itemData = itemList.get(position);
					OnImageViewClick(banner, itemData.getType(), itemData.getData(), true);
				}
			}
		});


	}

	@Override
	public void showMenu(List<ItemData> itemList) {

		ImageView im1 = (ImageView) headView.findViewById(R.id.home_menu_1);
		ImageView im2 = (ImageView) headView.findViewById(R.id.home_menu_2);
		ImageView im3 = (ImageView) headView.findViewById(R.id.home_menu_3);
		ImageView im4 = (ImageView) headView.findViewById(R.id.home_menu_4);
		ImageView im5 = (ImageView) headView.findViewById(R.id.home_menu_5);

		GlideImageLoader.setImage(context, itemList.get(0).getImageUrl(), im1);
		GlideImageLoader.setImage(context, itemList.get(1).getImageUrl(), im2);
		GlideImageLoader.setImage(context, itemList.get(2).getImageUrl(), im3);
		GlideImageLoader.setImage(context, itemList.get(3).getImageUrl(), im4);
		GlideImageLoader.setImage(context, itemList.get(4).getImageUrl(), im5);


		OnImageViewClick(im1, itemList.get(0).getType(), itemList.get(0).getData(), false);
		OnImageViewClick(im2, itemList.get(1).getType(), itemList.get(1).getData(), false);
		OnImageViewClick(im3, itemList.get(2).getType(), itemList.get(2).getData(), false);
		OnImageViewClick(im4, itemList.get(3).getType(), itemList.get(3).getData(), false);
		OnImageViewClick(im4, itemList.get(4).getType(), itemList.get(4).getData(), false);
	}

	@Override
	public void showNotice(List<ItemData> itemList) {
		List<String> notices = new ArrayList<>();
		for (ItemData itemData : itemList) {
			notices.add(itemData.getImageUrl());
		}

		MarqueeView marqueeView = (MarqueeView) headView.findViewById(R.id.marqueeView);

		marqueeView.startWithList(notices);
	}


	@Override
	public void OnImageViewClick(View view, final String type, final String data, boolean isAD) {

	}


	@Override
	public void showList() {
		showState(1);
		mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				//滑动的距离
				mDistanceY += dy;
				//toolbar的高度
				int toolbarHeight = mToolbar.getBottom();


				if (mDistanceY < 10) {
					mToolbar.setBackgroundResource(R.color.colorTransparency);

				} else if (mDistanceY <= toolbarHeight) {

					float scale = (float) mDistanceY / toolbarHeight;
					float alpha = scale * 255;
					mToolbar.setBackgroundColor(Color.argb((int) alpha, 6, 111, 0));

				} else {
					mToolbar.setBackgroundResource(R.color.colorPrimary);


				}


			}
		});
	}

	List<HomeMultipleItem> data;

	@Override
	public void onRefresh() {
		homeMultipleItemAdapter.setEnableLoadMore(false);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				HomePresenterImpl.clearList();
				presenter.initData();
				data = HomePresenterImpl.getHomeMultipleItemlist();
				homeMultipleItemAdapter.setNewData(data);
				isErr = false;
				mCurrentCounter = PAGE_SIZE;
				mSwipeRefreshLayout.setRefreshing(false);
				homeMultipleItemAdapter.setEnableLoadMore(true);

			}
		}, delayMillis);

	}


	private int delayMillis = 1000;
	private boolean isErr;
	private boolean mLoadMoreEndGone = false;
	private int mCurrentCounter = 0;





//
//	@Override
//	public void onLoadMoreRequested() {
//		mSwipeRefreshLayout.setEnabled(false);
//		if (homeMultipleItemAdapter.getData().size() < PAGE_SIZE) {
//			homeMultipleItemAdapter.loadMoreEnd(true);
//		} else {
//
//			homeMultipleItemAdapter.goodsList = HomePresenterImpl.moreGoodsList();
//			List<HomeMultipleItem> list = new ArrayList<>();
//			for (ItemGoods item : HomePresenterImpl.moreGoodsList()) {
//				list.add(new HomeMultipleItem(HomeMultipleItem.GOODS, HomeMultipleItem.GOODS_SPAN_SIZE));
//			}
//			homeMultipleItemAdapter.addData(list);
//			mCurrentCounter = homeMultipleItemAdapter.getData().size();
//			homeMultipleItemAdapter.loadMoreComplete();
//
//		}
//		mSwipeRefreshLayout.setEnabled(true);
//
//	}
}
