package com.xinyuan.xyshop.ui.home;

import android.app.Activity;
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
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.sunfusheng.marqueeview.MarqueeView;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.ExpandableItemAdapter;
import com.xinyuan.xyshop.adapter.HomeGoodsAdapter;
import com.xinyuan.xyshop.adapter.HomeMultipleItemAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.bean.ExpandItem;
import com.xinyuan.xyshop.entity.GoodListItem;
import com.xinyuan.xyshop.entity.HomeMultipleItem;
import com.xinyuan.xyshop.entity.Menu;
import com.xinyuan.xyshop.model.HomeModel;
import com.xinyuan.xyshop.mvp.contract.HomeContract;
import com.xinyuan.xyshop.mvp.presenter.HomePresenterImpl;
import com.xinyuan.xyshop.ui.goods.SearchGoodsActivity;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailsActivity;
import com.xinyuan.xyshop.ui.goods.store.StoreActivity;
import com.xinyuan.xyshop.ui.mine.MsgActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

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
	@BindView(R.id.home_toolbar)
	Toolbar mToolbar;
	@BindView(R.id.home_header_rl)
	RelativeLayout darkView;
	@BindView(R.id.act_home_btn_scan)
	ImageView mScan;
	@BindView(R.id.act_home_btn_msg)
	ImageView mMsg;
	@BindView(R.id.frag_home_et_search)
	Button et_search;
	private View headView;

	private HomeContract.HomePresenter presenter;
	private HomeMultipleItemAdapter homeMultipleItemAdapter;
	private RecyclerView menuListView;

	private static boolean isLoad = true; //是否加载
	private int mDistanceY = 0;           //列表滑动距离
	private static String keyWord = "";
	private static String showWord = "";
	private int delayMillis = 1000;
	private List<HomeMultipleItem> data;
	private Bundle saveState; //保存状态


	public static HomeFragment newInstance() {
		Bundle args = new Bundle();
		HomeFragment fragment = new HomeFragment();
		fragment.setArguments(args);
		return fragment;
	}


	@Override
	public int getLayoutId() {
		return R.layout.fragment_home;
	}

	@Override
	public void initView() {
		//避免重复加载Toolbar
		if (isLoad) {
			SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
			SystemBarHelper.setHeightAndPadding(getActivity(), mToolbar);
		}
		isLoad = false;
		mDistanceY = 0;
		restoreState();

	}

	@Override
	public void onLazyInitView(@Nullable Bundle savedInstanceState) {
		new HomePresenterImpl(this);
		presenter.initData();   //加载数据
		//加载失败，点击重试
		xLoadingView.setOnRetryClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				presenter.initData();
			}
		});

	}

	@Override
	public void setPresenter(HomeContract.HomePresenter presenter) {
		this.presenter = presenter;
	}


	/**
	 * 显示首页banner
	 *
	 * @param itemList
	 */
	@Override
	public void showBanner(final List<HomeModel.HomeModule.HomeModuleData> itemList) {
		ArrayList<String> bannerImages = new ArrayList<>();     //banner图片列表


		for (HomeModel.HomeModule.HomeModuleData itemData : itemList) {
			bannerImages.add(itemData.getImageUrl());
		}

		final Banner banner = (Banner) headView.findViewById(R.id.banner);
		banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
				.setImageLoader(new GlideImageLoader())
				.setImages(bannerImages)
				.setBannerAnimation(Transformer.DepthPage)
				.isAutoPlay(true)
				.setDelayTime(3000)
				.setIndicatorGravity(BannerConfig.CENTER)
				.start();
		banner.setOnBannerListener(new OnBannerListener() {
			@Override
			public void OnBannerClick(int position) {
				if (itemList != null) {
					HomeModel.HomeModule.HomeModuleData itemData = itemList.get(position);
					OnImageViewClick(banner, itemData.getType(), itemData.getData());
				}
			}
		});
		mMsg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getActivity(), MsgActivity.class);
				startActivity(intent);
			}
		});

		bannerImages.clear();


	}


	/**
	 * 显示首页公告
	 *
	 * @param itemList
	 */
	@Override
	public void showNotice(List<HomeModel.HomeModule.HomeModuleData> itemList) {
		List<CharSequence> noticeList = new ArrayList<>();      //公告列表

		MarqueeView marqueeView = (MarqueeView) headView.findViewById(R.id.marquee_name);
		for (HomeModel.HomeModule.HomeModuleData data : itemList) {
			String s = "【" + data.getText() + "】" + data.getData();
			SpannableString ss1 = new SpannableString(s);
			ss1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimaryDark)), 0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			noticeList.add(ss1);
		}

		marqueeView.startWithList(noticeList);

		RelativeLayout rl_home_news = (RelativeLayout) headView.findViewById(R.id.rl_home_news);
		rl_home_news.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				CommUtil.gotoActivity(getActivity(), NewsActivity.class, false, null);
			}
		});

	}


	/**
	 * 显示首页菜单
	 *
	 * @param itemList
	 */
	@Override
	public void showMenu(final List<HomeModel.HomeModule.HomeModuleData> itemList) {
		ArrayList<MultiItemEntity> menuExpen = new ArrayList<>();   //菜单视图列表
		menuListView = (RecyclerView) headView.findViewById(R.id.menu_list);

		int menucount = 10;


		for (int i = 0; i < 5; i++) {
			ExpandItem expandItem = new ExpandItem(itemList.get(i).getImageUrl(), itemList.get(i).getData());
			if (i == 4) {
				for (int j = 5; j < itemList.size(); j++) {
					expandItem.addSubItem(new Menu(itemList.get(j).getImageUrl(), itemList.get(j).getData()));
				}
			}

			menuExpen.add(expandItem);
		}


		ExpandableItemAdapter expandableItemAdapter = new ExpandableItemAdapter(menuExpen, itemList);
		final GridLayoutManager manager = new GridLayoutManager(getActivity(), 5);

		menuListView.setAdapter(expandableItemAdapter);

		menuListView.setLayoutManager(manager);
		expandableItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
			@Override
			public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

				OnImageViewClick(view, itemList.get(position).getType(), itemList.get(position).getData());
			}
		});
		expandableItemAdapter.expandAll();


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
		headView = getActivity().getLayoutInflater().inflate(R.layout.fragment_home_top, (ViewGroup) mRecyclerView.getParent(), false);

		final GridLayoutManager manager = new GridLayoutManager(context, 4);
		mRecyclerView.setLayoutManager(manager);
		homeMultipleItemAdapter = new HomeMultipleItemAdapter(this.getContext(), list);
		homeMultipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
			@Override
			public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {

				return list.get(position).getSpanSize();
			}
		});
		homeMultipleItemAdapter.addHeaderView(headView);
		mRecyclerView.setAdapter(homeMultipleItemAdapter);

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


	@Override
	public void showGoods(HomeModel.GoodModule goodModule) {
		List<GoodListItem> goodList = new ArrayList<>();    //推荐商品列表

		View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_home_footer, (ViewGroup) mRecyclerView.getParent(), false);
		RecyclerView rv_goods = (RecyclerView) view.findViewById(R.id.rv_home_goods);
		ImageView iv_ca_title = (ImageView) view.findViewById(R.id.iv_tab_title);
		TextView tv_ca_title_cn = (TextView) view.findViewById(R.id.tv_tab_title_cn);
		TextView tv_ca_title_en = (TextView) view.findViewById(R.id.tv_tab_title_en);
		goodList = goodModule.getGoodList();
		HomeGoodsAdapter adapters = new HomeGoodsAdapter(R.layout.item_home_good_grid, goodList);
		GridLayoutManager layoutManager2 = new GridLayoutManager(this.context, 2, 1, false);
		rv_goods.setLayoutManager(layoutManager2);
		rv_goods.setAdapter(adapters);
		adapters.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
				CommUtil.gotoActivity(getActivity(), GoodDetailsActivity.class, false, null);
			}
		});
		GlideImageLoader.setImage(context, goodModule.getGoodInfo().getItemtitleImage(), iv_ca_title);
		tv_ca_title_cn.setText(goodModule.getGoodInfo().getItemtitleCN());
		tv_ca_title_en.setText(goodModule.getGoodInfo().getItemtitleEN());
		tv_ca_title_cn.setTextColor(Color.parseColor(goodModule.getGoodInfo().getItemtitleColor()));
		homeMultipleItemAdapter.addFooterView(view, 0);


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
					darkView.setBackgroundResource(R.color.colorTransparency);
					et_search.setTextColor(getResources().getColor(R.color.tv_hint));

				} else if (mDistanceY <= toolbarHeight) {

					float scale = (float) mDistanceY / toolbarHeight;
					float alpha = scale * 255;
					mToolbar.setBackgroundColor(Color.argb((int) alpha, 0, 118, 42));
					darkView.setBackgroundColor(Color.argb((int) alpha, 29, 160, 57));
					et_search.setTextColor(getResources().getColor(R.color.bg_gray));


				} else {
					mToolbar.setBackgroundResource(R.color.colorPrimaryDark);
					darkView.setBackgroundResource(R.color.colorPrimary);
					et_search.setTextColor(getResources().getColor(R.color.bg_white));
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
		MyShopApplication.setKeyWord(keyWord);
		et_search.setHint(keyWord);
		MyShopApplication.setKeyWord(keyWord);
		setSearchListener();

	}

	/**
	 * 首页点击操作监听
	 *
	 * @param view 点击的View
	 * @param type 跳转类型
	 * @param data 跳转数据
	 */
	@Override
	public void OnImageViewClick(View view, final String type, final String data) {

		Map<String, String> params = new HashMap();


		XLog.v("TYPE" + type + ":DATA" + data);
		if (type.equals("good")) {

			params.put("goodId", data);
			CommUtil.gotoActivity(getActivity(), GoodDetailsActivity.class, false, params);
		} else if (type.equals("store")) {

			params.put("storeId", data);
			CommUtil.gotoActivity(getActivity(), StoreActivity.class, false, params);
		} else if (type.equals("html")) {

			params.put("url", data);
			CommUtil.gotoActivity(getActivity(), WebViewActivity.class, false, params);
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
					Map<String, String> params = new HashMap();
					params.put("keyword", keyWord);
					params.put("showWord", showWord);
					CommUtil.gotoActivity(getActivity(), SearchGoodsActivity.class, false, params);

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
				presenter.initData();
				data = HomePresenterImpl.getHomeMultipleList();
				homeMultipleItemAdapter.setNewData(data);
				mSwipeRefreshLayout.setRefreshing(false);
				homeMultipleItemAdapter.setEnableLoadMore(true);

			}
		}, delayMillis);

	}

	private void restoreState() {
		if (saveState != null) {
			mDistanceY = saveState.getInt("DistanceY");
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		saveStatetoArguments();
	}

	private void saveStatetoArguments() {
		saveState = saveState();
		if (saveState != null) {
			Bundle b = getArguments();
		}
	}

	private Bundle saveState() {
		Bundle d = new Bundle();
		d.putInt("DistanceY", mDistanceY);
		return d;
	}

	@Override
	@CallSuper
	public void onResume() {
		super.onResume();
		initView();


	}

	@Override
	public void initData(@Nullable Bundle savedInstanceState) {

	}

	@OnClick(R.id.act_home_btn_scan)
	public void OpenScan() {
		CommUtil.gotoActivity(getActivity(), ScanActivity.class, false, null);
	}
}
