package com.xinyuan.xyshop.ui.goods.groupbuy;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.flyco.tablayout.SlidingTabLayout;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.CommonPagerAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.ui.goods.groupbuy.fragment.GroupAllFragment;
import com.xinyuan.xyshop.ui.goods.groupbuy.fragment.GroupGoodsFragment;
import com.xinyuan.xyshop.ui.goods.store.fragment.StoreHomeFragment;
import com.xinyuan.xyshop.util.SystemBarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/21.
 */

public class GroupBuyActivity extends BaseActivity {
	@BindView(R.id.group__tabs)
	public SlidingTabLayout psts_group;
	@BindView(R.id.vp_content)
	public ViewPager vp_content;
	private ArrayList<Fragment> mFragments = new ArrayList<>();
	@BindView(R.id.group_toolbar)
	Toolbar group_toolbar;
	@BindView(R.id.collapsing_toolbar)
	CollapsingToolbarLayout collapsingToolbar;
	private CommonPagerAdapter adapter;
	@BindView(R.id.appbar)
	AppBarLayout mAppbar;
	private GroupGoodsFragment newGoodsFragment;
	private GroupAllFragment allFragment;
	private final String[] mTitles = {
			"热门团购", "最新团购"
	};


	@Override
	public int getLayoutId() {
		return R.layout.activity_group_buy;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		ButterKnife.bind(this);
		setSupportActionBar(group_toolbar);
		collapsingToolbar.setTitleEnabled(false);

		SystemBarHelper.immersiveStatusBar(this); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(this, group_toolbar);


		for (String title : mTitles) {
			mFragments.add(newGoodsFragment.getInstance(title));
		}
		mFragments.add(allFragment.getInstance("全部团购"));
		 String[] mTitle = {
				"热门团购", "最新团购","全部团购"
		};

		adapter = new CommonPagerAdapter(getSupportFragmentManager(), mFragments, mTitle);
		vp_content.setAdapter(adapter);
		psts_group.setViewPager(vp_content);
		vp_content.setCurrentItem(0);
		vp_content.setOffscreenPageLimit(4);
	}


}
