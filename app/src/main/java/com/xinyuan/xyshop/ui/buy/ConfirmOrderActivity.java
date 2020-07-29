package com.xinyuan.xyshop.ui.buy;

import com.xinyuan.xyshop.MainFragment;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.common.Constants;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;

/**
 * Created by fx on 2017/6/19.
 * 确认订单Activity
 */

public class ConfirmOrderActivity extends BaseActivity<BasePresenter> {
    private static boolean isOnlineOrder = false;
    private int type;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_confirm_order;
    }

    @Override
    public void initView() {
        String goodtype = getIntent().getStringExtra(Constants.GOODTYPE);
        //fragmentation框架里加载主Fragment
        if (goodtype.equals(Constants.GOOD_NORMAL)) { //实物订单结算
            loadRootFragment(R.id.main_content, ConfirmOrderFragment.newInstance());
        } else if (goodtype.equals(Constants.GOOD_ONLINE)) { //虚拟商品订单结算
            loadRootFragment(R.id.main_content, ConfirmOrderOnFragment.newInstance());

        } else if (goodtype.equals(Constants.CREDIT_RED)) { //积分兑换红包订单
            loadRootFragment(R.id.main_content, ConfirmOrderExFragment.newInstance("兑换红包"));

        } else if (goodtype.equals(Constants.CREDIT_COUPON)) { //积分兑换优惠券订单
            loadRootFragment(R.id.main_content, ConfirmOrderExFragment.newInstance("兑换优惠券"));
        }
        setFragmentAnimator(new DefaultHorizontalAnimator());
    }

    @Override
    public void initData() {

    }
}
