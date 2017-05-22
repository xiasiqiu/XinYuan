package com.xinyuan.xyshop.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.sunfusheng.marqueeview.MarqueeView;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.ExpandableItemAdapter;
import com.xinyuan.xyshop.adapter.HomeMultipleItemAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.bean.ExpandItem;
import com.xinyuan.xyshop.entity.HomeMultipleItem;
import com.xinyuan.xyshop.entity.ItemData;
import com.xinyuan.xyshop.entity.KeyWord;
import com.xinyuan.xyshop.entity.Menu;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.mvp.contract.HomeContract;
import com.xinyuan.xyshop.mvp.presenter.HomePresenterImpl;
import com.xinyuan.xyshop.ui.goods.SearchGoodsActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.xinyuan.xyshop.util.JsonUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.xframe.common.XActivityStack;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by fx on 2017/5/9 0009.
 */

public class HomeFragment extends BaseFragment implements HomeContract.HomeView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {


	@BindView(R.id.home_list)
	RecyclerView mRecyclerView;

	RecyclerView menuListView;
	@BindView(R.id.home_swipeLayout)
	SwipeRefreshLayout mSwipeRefreshLayout;
	@BindView(R.id.home_loadingView)
	XLoadingView xLoadingView;

	@BindView(R.id.home_toolbar)
	Toolbar mToolbar;
	@BindView(R.id.act_home_btn_scan)
	ImageView mScan;
	@BindView(R.id.act_home_btn_msg)
	ImageView mMsg;
	@BindView(R.id.frag_home_et_search)
	EditText et_search;
	private View headView;

	private HomeContract.HomePresenter presenter;
	private HomeMultipleItemAdapter homeMultipleItemAdapter;

	private int mDistanceY = 0;
	private static final int TOTAL_COUNTER = 30;
	private static final int PAGE_SIZE = 6;
	private static String keyWord = "";
	private static String showWord = "";
	private int delayMillis = 1000;
	private boolean isErr;
	private boolean mLoadMoreEndGone = false;
	private int mCurrentCounter = 0;

	private List<HomeMultipleItem> data;
	private static int num=1;
	@Override
	public int getLayoutId() {
		return R.layout.fragment_home;
	}

	@Override
	public void initView() {
		if(num==1){
			SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
			SystemBarHelper.setHeightAndPadding(getActivity(), mToolbar);


		}
		XLog.v("首页切换"+num);
		num++;
	}

	/**
	 * 设置Presenter
	 *
	 * @param presenter
	 */
	@Override
	public void setPresenter(HomeContract.HomePresenter presenter) {
		this.presenter = presenter;

	}

	/**
	 * Prenenter加载首页数据
	 *
	 * @param savedInstanceState
	 */
	@Override
	public void initData(@Nullable Bundle savedInstanceState) {
		new HomePresenterImpl(this);
		presenter.initData();
		xLoadingView.setOnRetryClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				presenter.initData();
				xLoadingView.showLoading();
			}
		});

	}


	/**
	 * 加载状态监听
	 *
	 * @param Sate
	 */

	@Override
	public void showState(int Sate) {
		switch (Sate) {
			case 0:

				xLoadingView.showLoading();
				break;
			case 1:

				xLoadingView.showContent();
				break;
			case 2:

				xLoadingView.showError();
				break;

		}

	}

	/**
	 * RecyclerView加载头部View
	 *
	 * @param list
	 */
	@Override
	public void addHead(final List<HomeMultipleItem> list) {

		mSwipeRefreshLayout.setOnRefreshListener(this);
		mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(29, 160, 57));
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


	/**
	 * 显示首页banner
	 *
	 * @param itemList
	 */
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

	/**
	 * 显示首页菜单
	 *
	 * @param itemList
	 */
	@Override
	public void showMenu(final List<ItemData> itemList) {
		menuListView = (RecyclerView) headView.findViewById(R.id.menu_list);
		int menucount = 10;
		ArrayList<MultiItemEntity> res = new ArrayList<>();
		res.add(new ExpandItem(itemList.get(0).getImageUrl(), itemList.get(0).getData()));
		res.add(new ExpandItem(itemList.get(1).getImageUrl(), itemList.get(1).getData()));
		res.add(new ExpandItem(itemList.get(2).getImageUrl(), itemList.get(2).getData()));
		res.add(new ExpandItem(itemList.get(3).getImageUrl(), itemList.get(3).getData()));
		ExpandItem more = new ExpandItem(itemList.get(4).getImageUrl(), itemList.get(4).getData());
		for (int i = 5; i < menucount; i++) {
			more.addSubItem(new Menu(itemList.get(i).getImageUrl(), itemList.get(i).getData()));
		}
		res.add(more);
		ExpandableItemAdapter expandableItemAdapter = new ExpandableItemAdapter(res, itemList);
		final GridLayoutManager manager = new GridLayoutManager(getActivity(), 5);
		menuListView.setAdapter(expandableItemAdapter);
		expandableItemAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
		menuListView.setLayoutManager(manager);
		expandableItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
			@Override
			public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {


				OnImageViewClick(view, itemList.get(position).getType(), itemList.get(position).getData(), false);
			}
		});

	}


	/**
	 * 显示首页公告
	 *
	 * @param itemList
	 */
	@Override
	public void showNotice(List<ItemData> itemList) {
		List<String> name = new ArrayList<>();
		List<String> content = new ArrayList<>();
		for (ItemData itemData : itemList) {
			name.add(itemData.getData());
			content.add(itemData.getImageUrl());
		}

		MarqueeView marquee_name = (MarqueeView) headView.findViewById(R.id.marquee_name);
		MarqueeView marquee_content = (MarqueeView) headView.findViewById(R.id.marquee_content);


		marquee_name.startWithList(name);
		marquee_content.startWithList(content);
	}


	/**
	 * 显示首页列表页面
	 */
	@Override
	public void showList() {
		mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				//滑动的距离
				mDistanceY += dy;
				//toolbar的高度
				int toolbarHeight = mToolbar.getBottom();


				if (mDistanceY < 10) {
					mToolbar.setBackgroundResource(R.color.colorTransparency);
					et_search.setHintTextColor(getResources().getColor(R.color.tv_hint));

				} else if (mDistanceY <= toolbarHeight) {

					float scale = (float) mDistanceY / toolbarHeight;
					float alpha = scale * 255;
					mToolbar.setBackgroundColor(Color.argb((int) alpha, 29, 160, 57));
					et_search.setHintTextColor(getResources().getColor(R.color.tv_hint));

				} else {
					mToolbar.setBackgroundResource(R.color.colorPrimary);
					et_search.setHintTextColor(getResources().getColor(R.color.tv_hint));


				}


			}
		});
		showState(1);
	}


	/**
	 * 设置搜索栏关键词加载
	 *
	 * @param keyWord
	 * @param showWord
	 */
	@Override
	public void setKeyWord(String keyWord, String showWord) {
		this.showWord = showWord;
		this.keyWord = keyWord;
		et_search.setHint(keyWord);
		setSearchListener();

	}

	/**
	 * 首页点击操作监听
	 *
	 * @param view
	 * @param type
	 * @param data
	 * @param isAD
	 */
	@Override
	public void OnImageViewClick(View view, final String type, final String data, boolean isAD) {

		if (type.equals("brand")) {
			XLog.v("ssssssssssssss");
			CommUtil.gotoActivity(getActivity(), BrandActivity.class, false, null);

		}

	}


	/**
	 * 搜素栏监听
	 */
	@Override
	public void setSearchListener() {
		et_search.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
					Intent intent = new Intent(getActivity(), SearchGoodsActivity.class);
					intent.putExtra("keyword", keyWord);
					intent.putExtra("showWord", showWord);
					startActivity(intent);
				}
				return false;
			}
		});
	}

	/**
	 * 刷新监听
	 */
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


	/**
	 * 加载更多
	 */
	@Override
	public void onLoadMoreRequested() {

	}


	@Override
	@CallSuper
	public void onResume() {
		super.onResume();

	}
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
