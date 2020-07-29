package com.xinyuan.xyshop.ui.goods.credit;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.CreditGoodsAdapter;
import com.xinyuan.xyshop.adapter.CreditMallAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.entity.CreditMultipleItem;
import com.xinyuan.xyshop.entity.ItemData;
import com.xinyuan.xyshop.model.CreditGoodModel;
import com.xinyuan.xyshop.model.CreditMallModel;
import com.xinyuan.xyshop.mvp.contract.CreditMallView;
import com.xinyuan.xyshop.mvp.presenter.CreditMallPresenter;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailActivity;
import com.xinyuan.xyshop.ui.goods.store.StoreActivity;
import com.xinyuan.xyshop.ui.home.WebViewActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cn.bingoogolapple.badgeview.BGABadgeImageView;

/**
 * Created by fx on 2017/6/22.
 * 积分商城页面
 */

public class CreditMallFragment extends BaseFragment<CreditMallPresenter> implements CreditMallView, SwipeRefreshLayout.OnRefreshListener {
	@BindView(R.id.toolbar_iv)
	Toolbar toolbar_iv;
	@BindView(R.id.rv_credit)
	RecyclerView rv_credit;
	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;
	@BindView(R.id.iv_header_right)
	BGABadgeImageView iv_header_right;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.lodingView)
	XLoadingView lodingView;
	@BindView(R.id.swipeLayout)
	SwipeRefreshLayout mSwipeRefreshLayout;


	private CreditMallAdapter creditAdapter;
	List<CreditMultipleItem> list = new ArrayList<>();
	private CreditMallModel creditModel;

	public static CreditMallFragment newInstance() {
		CreditMallFragment fragment = new CreditMallFragment();
		return fragment;
	}

	@Override
	protected CreditMallPresenter createPresenter() {
		return new CreditMallPresenter(getActivity(), this);
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.activity_credit_mall;
	}


	@Override
	public void initView(View rootView) {
		SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_iv);
		tv_header_center.setText("积分商城");
		CommUtil.initToolBar(getActivity(), iv_header_left, iv_header_right);
		mSwipeRefreshLayout.setOnRefreshListener(this);
		mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
		final GridLayoutManager manager = new GridLayoutManager(mContext, 2);
		rv_credit.setLayoutManager(manager);
	}

	@Override
	public void initData() {
		mPresenter.getData();

	}

	@Override
	public void showList(CreditMallModel creditMallModel) {
		if (XEmptyUtils.isEmpty(creditAdapter)) {
			list.add(new CreditMultipleItem(CreditMultipleItem.C_MODULE, CreditMultipleItem.C_MODULE_SPAN_SIZE));
			list.add(new CreditMultipleItem(CreditMultipleItem.C_AD, CreditMultipleItem.C_AD_SPAN_SIZE));
			list.add(new CreditMultipleItem(CreditMultipleItem.R_MODULE, CreditMultipleItem.R_MODULE_SPAN_SIZE));
			list.add(new CreditMultipleItem(CreditMultipleItem.R_AD, CreditMultipleItem.R_AD_SPAN_SIZE));
			list.add(new CreditMultipleItem(CreditMultipleItem.G_MODULE, CreditMultipleItem.G_MODULE_SPAN_SIZE));
			list.add(new CreditMultipleItem(CreditMultipleItem.G_AD, CreditMultipleItem.G_AD_SPAN_SIZE));
			creditAdapter = new CreditMallAdapter(list, creditMallModel.getGoodsModule(), creditMallModel.getCouponModule(), creditMallModel.getRedpacketModule());
			creditAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
				@Override
				public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {

					return list.get(position).getSpanSize();
				}
			});
			rv_credit.setAdapter(creditAdapter);
			creditAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
			addHeader(creditMallModel.getBannerModule().getBanner());
			mPresenter.getGoods();

		} else {
			list.clear();
			creditAdapter.couponBean = creditMallModel.getCouponModule();
			creditAdapter.redpacketBean = creditMallModel.getRedpacketModule();
			creditAdapter.goodBean = creditMallModel.getGoodsModule();

			list.add(new CreditMultipleItem(CreditMultipleItem.C_MODULE, CreditMultipleItem.C_MODULE_SPAN_SIZE));
			list.add(new CreditMultipleItem(CreditMultipleItem.C_AD, CreditMultipleItem.C_AD_SPAN_SIZE));
			list.add(new CreditMultipleItem(CreditMultipleItem.R_MODULE, CreditMultipleItem.R_MODULE_SPAN_SIZE));
			list.add(new CreditMultipleItem(CreditMultipleItem.R_AD, CreditMultipleItem.R_AD_SPAN_SIZE));
			list.add(new CreditMultipleItem(CreditMultipleItem.G_MODULE, CreditMultipleItem.G_MODULE_SPAN_SIZE));
			list.add(new CreditMultipleItem(CreditMultipleItem.G_AD, CreditMultipleItem.G_AD_SPAN_SIZE));
			creditAdapter.setNewData(list);
			creditAdapter.notifyDataSetChanged();
			mSwipeRefreshLayout.setRefreshing(false);
			creditAdapter.setEnableLoadMore(true);

		}

		lodingView.showContent();
	}

	@Override
	public void showGoods(final CreditGoodModel creditGoodModel) {
		View footView = getActivity().getLayoutInflater().inflate(R.layout.activity_credit_mall_item_module, (ViewGroup) rv_credit.getParent(), false);
		TextView textView = (TextView) footView.findViewById(R.id.tv_credit_module);
		textView.setText(R.string.user_recomm);

		RecyclerView rv_recom = (RecyclerView) footView.findViewById(R.id.rv_credit_content);
		CreditGoodsAdapter adapter = new CreditGoodsAdapter(R.layout.item_credit_good, creditGoodModel.getGoodlist());
		GridLayoutManager layoutManager2 = new GridLayoutManager(mContext, 2, 1, false);
		rv_recom.setLayoutManager(layoutManager2);
		rv_recom.setAdapter(adapter);
//		adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//			@Override
//			public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//				Map<String, String> params = new HashMap();
//				params.put(Constants.GOODID, creditGoodModel.getGoodlist().get(position).getGoodsId());
//				params.put(Constants.GOODTYPE, creditGoodModel.getGoodlist().get(position).getGoodsType());
//				CommUtil.gotoActivity(getActivity(), GoodDetailActivity.class, false, params);
//			}
//		});
		creditAdapter.addFooterView(footView);
	}


	private void addHeader(final List<ItemData> imgs) {
		View headView = getActivity().getLayoutInflater().inflate(R.layout.activity_credit_mall_top, (ViewGroup) rv_credit.getParent(), false);

		final Banner banner = (Banner) headView.findViewById(R.id.banner_fx);
		Button bt_coupon = (Button) headView.findViewById(R.id.bt_coupon);
		Button bt_good = (Button) headView.findViewById(R.id.bt_good);
		Button bt_red = (Button) headView.findViewById(R.id.bt_red);
		final ArrayList<String> images = new ArrayList<>();
		for (ItemData data : imgs) {

			images.add(CommUtil.getUrl(data.getImageUrl()));
		}
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
				if (!XEmptyUtils.isEmpty(imgs)) {
					OnImageViewClick(imgs.get(position));
				}
			}
		});
		bt_good.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				start(CreditMallDetailFragment.newInstance(1, "积分兑换礼品"));

			}
		});
		bt_red.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				start(CreditMallDetailFragment.newInstance(2, "积分兑换红包"));

			}
		});
		bt_coupon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				start(CreditMallDetailFragment.newInstance(3, "积分兑换优惠券"));

			}
		});


		creditAdapter.addHeaderView(headView);
	}


	/**
	 * 监听点击
	 *
	 * @param itemData
	 */
	public void OnImageViewClick(ItemData itemData) {
		Map<String, String> params = new HashMap();
		if (itemData.getType().equals(Constants.GOOD)) {
			params.put(Constants.GOODID, itemData.getData());
			params.put(Constants.GOODTYPE, Constants.GOOD_CREDIT);
			CommUtil.gotoActivity(mContext, GoodDetailActivity.class, false, params);
		} else if (itemData.getType().equals(Constants.STORE)) {

			params.put(Constants.STOREID, itemData.getData());
			CommUtil.gotoActivity(mContext, StoreActivity.class, false, params);
		} else if (itemData.getType().equals(Constants.HTML)) {
			params.put(Constants.URL, itemData.getData());
			CommUtil.gotoActivity(mContext, WebViewActivity.class, false, params);
		}


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
	public LifecycleTransformer<CreditMallModel> bindLife() {
		return this.<CreditMallModel>bindUntilEvent(FragmentEvent.DESTROY_VIEW);
	}

	@Override
	public void onRefresh() {
		creditAdapter.setEnableLoadMore(false);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mPresenter.getData();
			}
		}, 500);
	}
}
