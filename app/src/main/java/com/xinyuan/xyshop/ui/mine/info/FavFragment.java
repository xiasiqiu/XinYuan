package com.xinyuan.xyshop.ui.mine.info;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.GoodsGridAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.ui.mine.pro.AccountFragment;
import com.xinyuan.xyshop.util.SystemBarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/26.
 */

public class FavFragment extends BaseFragment {
	@BindView(R.id.rv_fav)
	RecyclerView rv_fav;
	GoodsGridAdapter adapter;
	private LinearLayoutManager manager;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;

	@BindView(R.id.tv_header_right)
	TextView tv_header_right;
	@BindView(R.id.toolbar_iv)
	Toolbar msg_toolbar;
	public static FavFragment newInstance() {
		FavFragment fragment = new FavFragment();
		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_favorite;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
		tv_header_center.setText("收藏夹");
		tv_header_right.setText("编辑");
		LinearLayoutManager layoutManager = new LinearLayoutManager(context);
		layoutManager.setOrientation(1);
		List<GoodsVo> goodses = new ArrayList<>();
		goodses.add(new GoodsVo());
		goodses.add(new GoodsVo());
		goodses.add(new GoodsVo());
		goodses.add(new GoodsVo());
		goodses.add(new GoodsVo());
		goodses.add(new GoodsVo());
		goodses.add(new GoodsVo());
		this.rv_fav.setLayoutManager(layoutManager);
		this.manager = layoutManager;
		this.adapter = new GoodsGridAdapter(R.layout.activity_favorite_item, goodses);
		this.adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
		this.rv_fav.setAdapter(adapter);
	}
}
