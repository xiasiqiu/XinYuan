package com.xinyuan.xyshop.ui.goods.groupbuy;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.CommonPagerAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.ui.goods.groupbuy.fragment.GroupGoodsFragment;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;

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
	@BindView(R.id.toolbar_iv)
	Toolbar toolbar_iv;
	@BindView(R.id.collapsing_toolbar)
	CollapsingToolbarLayout collapsing_toolbar;
	private CommonPagerAdapter adapter;
	@BindView(R.id.appbar)
	AppBarLayout mAppbar;
	private GroupGoodsFragment newGoodsFragment;
	private final String[] mTitles = {
			"热门团购", "最新团购", "全部团购"
	};
	@BindView(R.id.ll_header_layout)
	ImageView headerLayout;
	@BindView(R.id.iv_header_right)
	ImageView iv_header_right;
	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;


	//是否隐藏了头部
	private boolean isHideHeaderLayout = false;

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
		setSupportActionBar(toolbar_iv);


		SystemBarHelper.immersiveStatusBar(this); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(this, toolbar_iv);
		CommUtil.initToolBar(this, iv_header_left, iv_header_right);
		tv_header_center.setText("团购商城");
		for (String title : mTitles) {
			mFragments.add(newGoodsFragment.getInstance(title));
		}
		adapter = new CommonPagerAdapter(getSupportFragmentManager(), mFragments, mTitles);
		vp_content.setAdapter(adapter);
		psts_group.setViewPager(vp_content);
		vp_content.setCurrentItem(0);
		vp_content.setOffscreenPageLimit(3);
		headerLayout.setImageResource(R.drawable.group_buy);
		initAppBarLayout();
	}


	// 初始化AppBarLayout
	private void initAppBarLayout() {
		LayoutTransition mTransition = new LayoutTransition();
		/**
		 * 添加View时过渡动画效果
		 */
		ObjectAnimator addAnimator = ObjectAnimator.ofFloat(null, "translationY", 0, 1.f).
				setDuration(mTransition.getDuration(LayoutTransition.APPEARING));
		mTransition.setAnimator(LayoutTransition.APPEARING, addAnimator);

		//header layout height
		final int headerHeight = getResources().getDimensionPixelOffset(R.dimen.header_height);
		mAppbar.setLayoutTransition(mTransition);

		mAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
			@Override
			public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
				verticalOffset = Math.abs(verticalOffset);

				XLog.v("移动:" + verticalOffset);
				if (verticalOffset >= 250) {

					isHideHeaderLayout = true;
					//当偏移量超过顶部layout的高度时，我们认为他已经完全移动出屏幕了
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							AppBarLayout.LayoutParams mParams = (AppBarLayout.LayoutParams) collapsing_toolbar.getLayoutParams();
							mParams.setScrollFlags(0);
							collapsing_toolbar.setLayoutParams(mParams);
							headerLayout.setVisibility(View.GONE);
						}
					}, 100);
				}
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			//监听返回键
			if (isHideHeaderLayout) {
				isHideHeaderLayout = false;

				((GroupGoodsFragment) mFragments.get(0)).getRvList().scrollToPosition(0);
				headerLayout.setVisibility(View.VISIBLE);

				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						AppBarLayout.LayoutParams mParams = (AppBarLayout.LayoutParams) collapsing_toolbar.getLayoutParams();
						mParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL |
								AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
						collapsing_toolbar.setLayoutParams(mParams);
					}
				}, 300);
			} else {
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
