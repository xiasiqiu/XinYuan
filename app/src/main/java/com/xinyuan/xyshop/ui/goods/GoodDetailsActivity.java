package com.xinyuan.xyshop.ui.goods;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.gxz.PagerSlidingTabStrip;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.ItemTitlePagerAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.ui.goods.fragment.GoodsCommentFragment;
import com.xinyuan.xyshop.ui.goods.fragment.GoodsDetailFragment;
import com.xinyuan.xyshop.ui.goods.fragment.GoodsInfoFragment;
import com.xinyuan.xyshop.ui.goods.fragment.GoodsRecommFragment;
import com.xinyuan.xyshop.widget.NoScrollViewPager;
import com.xinyuan.xyshop.widget.NotSlipViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodDetailsActivity extends BaseActivity {


	@BindView(R.id.psts_tabs)
	PagerSlidingTabStrip psts_tabs;
	@BindView(R.id.vp_content)
	NoScrollViewPager vp_content;
	@BindView(R.id.tv_title)
	public TextView tv_title;
	private List<Fragment> fragmentList = new ArrayList<>();
	private GoodsInfoFragment goodsInfoFragment;
	private GoodsDetailFragment goodsDetailFragment;
	private GoodsCommentFragment goodsCommentFragment;
	private GoodsRecommFragment goodsRecommFragment;


	@Override
	public int getLayoutId() {
		return R.layout.activity_good_details;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		ButterKnife.bind(this);
		fragmentList.add(goodsInfoFragment = new GoodsInfoFragment());
		fragmentList.add(goodsDetailFragment = new GoodsDetailFragment());
		fragmentList.add(goodsCommentFragment = new GoodsCommentFragment());
		fragmentList.add(goodsRecommFragment = new GoodsRecommFragment());
		vp_content.setAdapter(new ItemTitlePagerAdapter(getSupportFragmentManager(),
				fragmentList, new String[]{"商品", "详情", "评价", "推荐"}));
		vp_content.setOffscreenPageLimit(4);
		psts_tabs.setViewPager(vp_content);
	}
}
