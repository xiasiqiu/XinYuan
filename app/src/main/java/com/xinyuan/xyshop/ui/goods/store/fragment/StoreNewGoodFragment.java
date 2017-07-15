package com.xinyuan.xyshop.ui.goods.store.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.HomeGoodsAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.GoodListItem;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailsActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.youth.xframe.widget.loadingview.XLoadingView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/1.
 */

public class StoreNewGoodFragment extends BaseFragment {
	@BindView(R.id.rv_store_new)
	RecyclerView rv_store_new;
	@BindView(R.id.lodingView)
	XLoadingView lodingView;

	private List<Fragment> fragmentList = new ArrayList<>();
	private HomeGoodsAdapter adapter;


	@Override
	public int getLayoutId() {
		return R.layout.fragment_store_new;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		EventBus.getDefault().register(this);
		lodingView.showLoading();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		EventBus.getDefault().unregister(this);
	}


	@Subscribe
	public void inGoods(List<GoodListItem> listItems) {
		GridLayoutManager layoutManager2 = new GridLayoutManager(this.context, 2);
		this.rv_store_new.setLayoutManager(layoutManager2);
		this.adapter = new HomeGoodsAdapter(R.layout.item_good_grid, listItems);
		this.rv_store_new.setAdapter(adapter);
		adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
				CommUtil.gotoActivity(getActivity(), GoodDetailsActivity.class, false, null);
			}
		});
		lodingView.showContent();

	}
}
