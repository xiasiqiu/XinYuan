package com.xinyuan.xyshop.ui.mine.order.fragment;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.OrderBean;
import com.xinyuan.xyshop.even.GoodOrderEven;
import com.xinyuan.xyshop.even.OrderBusEven;
import com.xinyuan.xyshop.model.OrderModel;
import com.xinyuan.xyshop.ui.buy.PayFragment;
import com.xinyuan.xyshop.ui.mine.order.OrderDetailFragment;
import com.xinyuan.xyshop.ui.mine.order.OrderEveFragment;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import cn.bingoogolapple.badgeview.BGABadgeImageView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2017/9/5.
 * 订单fragment
 */

public class OrderFragment extends BaseFragment {
    @BindView(R.id.stab_order)
    SegmentTabLayout psts_tabs;
    @BindView(R.id.order_content)
    FrameLayout order_content;
    @BindView(R.id.msg_toolbar)
    Toolbar msg_toolbar;
    @BindView(R.id.brand_btn_back)
    ImageView brand_btn_back;
    @BindView(R.id.iv_order_msg)
    BGABadgeImageView iv_order_msg;

    private FragmentManager fragmentManager;

    private final String[] mEntityTitles = {
            "实物订单", "虚拟订单"
    };
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    private SupportFragment[] mFragments = new SupportFragment[4];


    public static OrderFragment newInstance() {
        OrderFragment fragment = new OrderFragment();
        return fragment;
    }

    @Override
    public void initView(View rootView) {
        SystemBarHelper.immersiveStatusBar(getActivity()); //设置状态栏透明
        SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
        CommUtil.initToolBar(getActivity(), brand_btn_back, iv_order_msg);

        SupportFragment firstFragment = findFragment(GoodOrderFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = GoodOrderFragment.newInstance();
            mFragments[SECOND] = OnLineOrderFragment.newInstance();

            loadMultipleRootFragment(R.id.order_content, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND]);
        } else {

            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findFragment(OnLineOrderFragment.class);
        }


        psts_tabs.setTabData(mEntityTitles);
        psts_tabs.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                int prposition = 0;
                if (position == 0) {
                    prposition = 1;
                }
                showHideFragment(mFragments[position], mFragments[prposition]);
            }

            @Override
            public void onTabReselect(int position) {


            }
        });

    }

    @Subscribe
    public void OrderBusEven(OrderBusEven event) {
        if (event.getFlag().equals(OrderBusEven.ToOrderDetail)) {//跳转至订单详情页面
            start(OrderDetailFragment.getInstance(((OrderBean) event.getObj()).getOrderFormId(), ((OrderBean) event.getObj()).getOrderType()));
        } else if (event.getFlag().equals(OrderBusEven.ToOrderLogistic)) {
            OrderBean bean = (OrderBean) event.getObj();
            start(LogisticFragment.getInstance(bean.getGoodsList().get(0).getGoodsImg(), String.valueOf(bean.getOrderFormId())));
        } else if (event.getFlag().equals(OrderBusEven.ToOrderPay)) {
            OrderBean bean = (OrderBean) event.getObj();
            start(PayFragment.newInstance(bean.getOrderNumber(), bean.getOrderPrice()));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void initData() {

    }

    @Override
    public void onSupportVisible() { //当fragment可见时，检查是否有新消息
        if (MyShopApplication.IsNewMsg) {
            iv_order_msg.showCirclePointBadge();
        } else {
            iv_order_msg.hiddenBadge();
        }

        super.onSupportVisible();
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_mine_order;
    }


}
