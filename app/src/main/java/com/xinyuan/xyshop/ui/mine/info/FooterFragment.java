package com.xinyuan.xyshop.ui.mine.info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.CreditAdapter;
import com.xinyuan.xyshop.adapter.GoodsGridAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.CreditBean;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/26.
 */

public class FooterFragment extends BaseFragment {
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.toolbar_tv)
	Toolbar toolbar_tv;
	@BindView(R.id.rv_footer)
	RecyclerView rv_footer;

	private GoodsGridAdapter adapter;

	public static FooterFragment newInstance() {
		FooterFragment fragment = new FooterFragment();
		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_footer;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {

		if (toolbar_tv != null) {
			SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
			SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_tv);
			tv_header_center.setText("我的足迹");
		}

	}

	@Override
	public void onLazyInitView(@Nullable Bundle savedInstanceState) {
		GridLayoutManager layoutManager = new GridLayoutManager(this.context, 3, 1, false);
		this.rv_footer.setLayoutManager(layoutManager);
		List<GoodsVo> list = new ArrayList<>();
		list.add(new GoodsVo());
		list.add(new GoodsVo());
		list.add(new GoodsVo());
		list.add(new GoodsVo());
		list.add(new GoodsVo());
		list.add(new GoodsVo());
		list.add(new GoodsVo());
		list.add(new GoodsVo());
		list.add(new GoodsVo());
		list.add(new GoodsVo());
		this.adapter = new GoodsGridAdapter(R.layout.fragment_footer_item, list);
		this.rv_footer.setAdapter(adapter);
	}
}
