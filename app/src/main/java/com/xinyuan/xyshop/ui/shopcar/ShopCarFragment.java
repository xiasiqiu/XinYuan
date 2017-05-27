package com.xinyuan.xyshop.ui.shopcar;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.HomeMultipleItemAdapter;
import com.xinyuan.xyshop.adapter.ShopCarMultipeAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.HomeMultipleItem;
import com.xinyuan.xyshop.entity.ShopCarMultipleItem;
import com.xinyuan.xyshop.entity.TestStore;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.base.XFragment;
import com.youth.xframe.utils.log.XLog;
import com.zhy.autolayout.AutoRelativeLayout;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fx on 2017/5/2 0002.
 */

public class ShopCarFragment extends BaseFragment {
	@BindView(R.id.category_toolbar)
	Toolbar toolbar;

	@BindView(R.id.rv_shopcar)
	RecyclerView rv_Car;
	@BindView(R.id.rl_car_login_notice)
	AutoRelativeLayout rl_car_login_notice;
	private static int num = 1;

	ShopCarMultipeAdapter shopCarMultipeAdapter;


	@Override
	public int getLayoutId() {
		return R.layout.fragment_shopcar;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {

		if (num == 1) {
			SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
			SystemBarHelper.setHeightAndPadding(getActivity(), toolbar);


		}
		XLog.v("购物车页面切换" + num);
		num++;

		final List<ShopCarMultipleItem> list = new ArrayList<>();
		list.add(new ShopCarMultipleItem(ShopCarMultipleItem.STORE_TITLE, ShopCarMultipleItem.STORE_TITLE_SPAN_SIZE));
		list.add(new ShopCarMultipleItem(ShopCarMultipleItem.GOODS_ITEM, ShopCarMultipleItem.GOODS_ITEM_SPAN_SIZE));
		list.add(new ShopCarMultipleItem(ShopCarMultipleItem.GOODS_ITEM, ShopCarMultipleItem.GOODS_ITEM_SPAN_SIZE));
		list.add(new ShopCarMultipleItem(ShopCarMultipleItem.GOODS_ITEM, ShopCarMultipleItem.GOODS_ITEM_SPAN_SIZE));
		list.add(new ShopCarMultipleItem(ShopCarMultipleItem.STORE_TITLE, ShopCarMultipleItem.STORE_TITLE_SPAN_SIZE));
		list.add(new ShopCarMultipleItem(ShopCarMultipleItem.GOODS_ITEM, ShopCarMultipleItem.GOODS_ITEM_SPAN_SIZE));
		list.add(new ShopCarMultipleItem(ShopCarMultipleItem.GOODS_ITEM, ShopCarMultipleItem.GOODS_ITEM_SPAN_SIZE));
		list.add(new ShopCarMultipleItem(ShopCarMultipleItem.RECOME_TITLE, ShopCarMultipleItem.RECOME_TITLE_SPAN_SIZE));
		list.add(new ShopCarMultipleItem(ShopCarMultipleItem.RECOME_GOOD, ShopCarMultipleItem.RECOME_GOOD_SPAN_SIZE));
		list.add(new ShopCarMultipleItem(ShopCarMultipleItem.RECOME_GOOD, ShopCarMultipleItem.RECOME_GOOD_SPAN_SIZE));
		list.add(new ShopCarMultipleItem(ShopCarMultipleItem.RECOME_GOOD, ShopCarMultipleItem.RECOME_GOOD_SPAN_SIZE));
		list.add(new ShopCarMultipleItem(ShopCarMultipleItem.RECOME_GOOD, ShopCarMultipleItem.RECOME_GOOD_SPAN_SIZE));
		list.add(new ShopCarMultipleItem(ShopCarMultipleItem.RECOME_GOOD, ShopCarMultipleItem.RECOME_GOOD_SPAN_SIZE));
		list.add(new ShopCarMultipleItem(ShopCarMultipleItem.RECOME_GOOD, ShopCarMultipleItem.RECOME_GOOD_SPAN_SIZE));
		list.add(new ShopCarMultipleItem(ShopCarMultipleItem.RECOME_GOOD, ShopCarMultipleItem.RECOME_GOOD_SPAN_SIZE));
		list.add(new ShopCarMultipleItem(ShopCarMultipleItem.RECOME_GOOD, ShopCarMultipleItem.RECOME_GOOD_SPAN_SIZE));
		list.add(new ShopCarMultipleItem(ShopCarMultipleItem.RECOME_GOOD, ShopCarMultipleItem.RECOME_GOOD_SPAN_SIZE));
		list.add(new ShopCarMultipleItem(ShopCarMultipleItem.RECOME_GOOD, ShopCarMultipleItem.RECOME_GOOD_SPAN_SIZE));




		XLog.list(list);
		final GridLayoutManager manager = new GridLayoutManager(context, 4);
		rv_Car.setLayoutManager(manager);
		shopCarMultipeAdapter = new ShopCarMultipeAdapter(this.getContext(), list);
		shopCarMultipeAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
			@Override
			public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {

				return list.get(position).getSpanSize();
			}
		});


		rv_Car.setAdapter(shopCarMultipeAdapter);
	}

	@OnClick(R.id.rl_car_login_notice)
	public void onClick() {
		rl_car_login_notice.setVisibility(View.GONE);
	}



}
