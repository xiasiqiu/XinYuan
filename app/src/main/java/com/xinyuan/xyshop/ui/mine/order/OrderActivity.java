package com.xinyuan.xyshop.ui.mine.order;

import android.content.Intent;

import com.xinyuan.xyshop.MainFragment;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.even.GoodOrderEven;
import com.xinyuan.xyshop.even.OrderBusEven;
import com.xinyuan.xyshop.ui.mine.order.fragment.OrderFragment;

import org.greenrobot.eventbus.EventBus;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;

/**
 * Created by Administrator on 2017/9/5.
 * 订单总Activity
 */

public class OrderActivity extends BaseActivity {
	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.activity_orders;
	}

	@Override
	public void initView() {
		if (findFragment(MainFragment.class) == null) {
			loadRootFragment(R.id.order_content, OrderFragment.newInstance());
		}
		setFragmentAnimator(new DefaultHorizontalAnimator());
	}

	@Override
	public void initData() {
		Intent intent = this.getIntent();
		int childIndex = (int) intent.getSerializableExtra("ORDER_INDEX");
		EventBus.getDefault().postSticky(new GoodOrderEven(childIndex));
	}


}
