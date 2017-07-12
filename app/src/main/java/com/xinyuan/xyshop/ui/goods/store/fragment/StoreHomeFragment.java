package com.xinyuan.xyshop.ui.goods.store.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.HomeGoodsAdapter;
import com.xinyuan.xyshop.adapter.ItemTitlePagerAdapter;
import com.xinyuan.xyshop.adapter.SearchGoodListAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.CouponBean;
import com.xinyuan.xyshop.entity.GoodListItem;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.entity.ItemData;
import com.xinyuan.xyshop.entity.SearchGoodsList;
import com.xinyuan.xyshop.entity.StoreInfoBean;
import com.xinyuan.xyshop.even.LoginPageEvent;
import com.xinyuan.xyshop.model.StoreHomeModel;
import com.xinyuan.xyshop.mvp.contract.StoreHomeContract;
import com.xinyuan.xyshop.mvp.presenter.StoreHomePresenterImpl;
import com.xinyuan.xyshop.ui.goods.store.StoreActivity;
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.xinyuan.xyshop.widget.NoScrollViewPager;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.loadingview.XLoadingView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/1.
 */

public class StoreHomeFragment extends BaseFragment implements StoreHomeContract.StoreHomeView {

	@BindView(R.id.rv_store_home)
	RecyclerView rv_store_home;
	@BindView(R.id.lodingView)
	XLoadingView lodingView;
	private List<Fragment> fragmentList = new ArrayList<>();
	private HomeGoodsAdapter adapter;
	private static boolean VIEW_INIT = true;
	private StoreHomeContract.StoreHomePresenter presenter;

	private StoreInfoBean storeInfoBean;

	@Override
	public int getLayoutId() {
		return R.layout.fragment_store_home;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		new StoreHomePresenterImpl(this);
		presenter.initData(123);
	}

	@Override
	public void initView() {
		EventBus.getDefault().register(this);
	}


	@Override
	public void setPresenter(StoreHomeContract.StoreHomePresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void showState(int Sate) {
		switch (Sate) {
			case 0:
				lodingView.showLoading();
				break;
			case 1:
				lodingView.showContent();
				break;
			case 2:
				lodingView.showError();
				break;
		}
	}


	@Override
	public void showStoreInfo(StoreInfoBean storeInfoBean) {
		this.storeInfoBean = storeInfoBean;
		EventBus.getDefault().post(storeInfoBean);
	}

	@Override
	public void showColl(ItemData ad, List<StoreHomeModel.CollGood> collGoodList, List<StoreHomeModel.CollGood> favGoodList) {

		View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_store_top, (ViewGroup) rv_store_home.getParent(), false);
		ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
		GlideImageLoader.setImage(getContext(), ad.getImageUrl(), imageView);


		NoScrollViewPager vp_content = (NoScrollViewPager) view.findViewById(R.id.vp_content);
		SlidingTabLayout homeTab = (SlidingTabLayout) view.findViewById(R.id.store__home_tabs);

		XLog.list(collGoodList);
		XLog.list(favGoodList);


		fragmentList.add(StoreSellFragment.newInstance("收藏", collGoodList, favGoodList));
		fragmentList.add(StoreSellFragment.newInstance("销量", collGoodList, favGoodList));


		vp_content.setAdapter(new ItemTitlePagerAdapter(getChildFragmentManager(),
				fragmentList, new String[]{"收藏排行", "销量排行"}));
		vp_content.setOffscreenPageLimit(2);
		vp_content.setCurrentItem(0);
		homeTab.setViewPager(vp_content);
		adapter.addHeaderView(view);
	}

	@Override
	public void showRecom(List<GoodListItem> recomList) {
		GridLayoutManager layoutManager2 = new GridLayoutManager(this.context, 2);
		this.rv_store_home.setLayoutManager(layoutManager2);
		this.adapter = new HomeGoodsAdapter(R.layout.item_good_grid, recomList);
		this.rv_store_home.setAdapter(adapter);
	}

	@Override
	public void showCoupon(List<CouponBean> couponBeanList) {

	}

	@Subscribe
	public void page(LoginPageEvent eventBus) {

	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		EventBus.getDefault().unregister(this);
	}
}
