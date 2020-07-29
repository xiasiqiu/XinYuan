package com.xinyuan.xyshop.ui.goods.store;

import com.xinyuan.xyshop.MainFragment;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.base.BasePresenter;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;

/**
 * Created by fx on 2017/5/31.
 * 店铺Activity
 */

public class StoreActivity extends BaseActivity {


	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.activity_store;
	}

	@Override
	public void initView() {
		if (findFragment(MainFragment.class) == null) {
			loadRootFragment(R.id.store_content, StoreFragment.newInstance());
		}
		setFragmentAnimator(new DefaultHorizontalAnimator());
	}

	@Override
	public void initData() {
	}


}
