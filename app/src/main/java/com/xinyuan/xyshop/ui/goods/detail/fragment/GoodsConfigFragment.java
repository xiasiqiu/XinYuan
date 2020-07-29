package com.xinyuan.xyshop.ui.goods.detail.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.GoodConfigAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.model.GoodDetailModels;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.loadingview.XLoadingView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;


/**
 * Created by fx on 2017/5/19.
 * 商品规格参数界面
 */

public class GoodsConfigFragment extends BaseFragment {
	@BindView(R.id.rv_good_config)
	RecyclerView rv_good_config;
	@BindView(R.id.loadingView)
	XLoadingView loadingView;
	private GoodConfigAdapter adapter;
	private List<GoodDetailModels.GoodParam> params;

	@Override
	public void initView(View rootView) {
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
		rv_good_config.setLayoutManager(linearLayoutManager);

	}

	@Override
	public void initData() {

	}

	@Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
	public void setConfig(List<GoodDetailModels.GoodParam> params) {
		if (!XEmptyUtils.isEmpty(params)) {
			adapter = new GoodConfigAdapter(R.layout.item_good_config, params);
			rv_good_config.setAdapter(adapter);
			loadingView.showContent();
		} else {
			loadingView.showEmpty();
		}
	}

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_good_config;
	}

	@Override
	public void onStart() {
		registerEventBus(this);
		super.onStart();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unregisterEventBus(this);
	}

}
