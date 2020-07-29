package com.xinyuan.xyshop.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MainFragment;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.UserInfoBean;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.common.state.LoginContext;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.even.LoginPageEvent;
import com.xinyuan.xyshop.even.MainFragmentStartEvent;
import com.xinyuan.xyshop.even.TabSelectedEvent;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.ui.mine.info.FavGoodFragment;
import com.xinyuan.xyshop.ui.mine.info.FollowsFragment;
import com.xinyuan.xyshop.ui.mine.info.FooterFragment;
import com.xinyuan.xyshop.ui.mine.info.SettingFragment;
import com.xinyuan.xyshop.ui.mine.info.UserInfoFragment;
import com.xinyuan.xyshop.ui.mine.order.OrderActivity;
import com.xinyuan.xyshop.ui.mine.order.service.OrderServiceFragment;
import com.xinyuan.xyshop.ui.mine.pro.AccountFragment;
import com.xinyuan.xyshop.ui.mine.pro.CouponFragment;
import com.xinyuan.xyshop.ui.mine.pro.CreditFragment;
import com.xinyuan.xyshop.ui.mine.pro.ProPertyFragment;
import com.xinyuan.xyshop.ui.mine.pro.RedPacketFragment;
import com.xinyuan.xyshop.util.CommUtil;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.XNetworkUtils;
import com.youth.xframe.utils.log.XLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.badgeview.BGABadgeImageView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by fx on 2017/5/2 0002.
 */

public class MineFragment extends BaseFragment {
    @BindView(R.id.bt_more_order)
    Button bt_more_order; //
    @BindView(R.id.customer_image)
    CircleImageView customer_image;
    @BindView(R.id.tv_user_name)
    TextView tv_user_name;
    @BindView(R.id.tv_mine_fav_num)
    TextView tv_mine_fav_num;
    @BindView(R.id.tv_follow_store_num)
    TextView tv_follow_store_num;
    @BindView(R.id.tv_mine_foot_num)
    TextView tv_mine_foot_num;
    @BindView(R.id.bt_mine_credit)
    Button bt_mine_credit;
    @BindView(R.id.iv_mine_msg)
    BGABadgeImageView iv_mine_msg;
    @BindView(R.id.bt_mine_order1)
    Button bt_mine_order1;
    @BindView(R.id.bt_mine_order2)
    Button bt_mine_order2;
    @BindView(R.id.bt_mine_order3)
    Button bt_mine_order3;
    @BindView(R.id.bt_mine_order4)
    Button bt_mine_order4;
    @BindView(R.id.bt_mine_order5)
    Button bt_mine_order5;

    private UserInfoBean userInfo;

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }


    @Subscribe(threadMode = ThreadMode.MAIN) //当登录后自动跳转回来
    public void onEvent(LoginPageEvent event) {
        if (event.isLoginStatus()) {
            if (!XEmptyUtils.isEmpty(event.getToken())) {
                LoginContext.getInstance().checkNewMsg(mContext); //检查是否有新消息
                getInfo(); //获取个人信息数据
            }
        } else {
            tv_user_name.setText("");
            tv_follow_store_num.setText("");
            tv_mine_fav_num.setText("");
            tv_mine_foot_num.setText("");
            bt_mine_credit.setText("");
            GlideImageLoader.setImg(mContext, R.drawable.img_defaule, customer_image);

        }
    }

    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) { //当在主页面点击个人中心时，未登录则跳转至登录界面，登录则加载个人资料
        if (event.position != MainFragment.MINE) return;
        if (LoginContext.getInstance().showMine(mContext)) {
            getInfo();
        }
        if (event.position == 500) {
            getInfo();
        }
    }


    @Override
    public void initView(View rootView) {


    }


    @Override
    public void initData() {
        updataInfo();
    }

    private void initInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
        MyShopApplication.userId = userInfo.getUserId();
        tv_user_name.setText(userInfo.getUserName());
        tv_follow_store_num.setText(String.valueOf(userInfo.getStoreCount()));
        tv_mine_fav_num.setText(String.valueOf(userInfo.getUserFavs()));
        tv_mine_foot_num.setText(String.valueOf(userInfo.getHistoryNum()));
        bt_mine_credit.setText(getString(R.string.mine_credit) + " " + String.valueOf(userInfo.getIntegral()));
        GlideImageLoader.setCircleImageView(mContext, userInfo.getUserPhoto(), customer_image);

        if (userInfo.getPay_no() != 0) {
            bt_mine_order1.setText("待付款(" + userInfo.getPay_no() + ")");

        }
        if (userInfo.getPay_yes() != 0) {
            bt_mine_order2.setText("待发货(" + userInfo.getPay_yes() + ")");
        }

        if (userInfo.getLogistics_no() != 0) {
            bt_mine_order3.setText("待收货(" + userInfo.getLogistics_no() + ")");

        }
        if (userInfo.getLogistics_yes() != 0) {
            bt_mine_order4.setText("待评价(" + userInfo.getLogistics_yes() + ")");

        }
        if (userInfo.getReturns_refund() != 0) {
            bt_mine_order5.setText("退款/售后(" + userInfo.getReturns_refund() + ")");

        }


    }

    private void getInfo() {
        OkGo.<LzyResponse<UserInfoBean>>post(Urls.URL_USER_INFO)
                .tag(this)
                .headers("token", MyShopApplication.Token)
                .params("token", MyShopApplication.Token)
                .params("ip", XNetworkUtils.getIPAddress(true))
                .execute(new JsonCallback<LzyResponse<UserInfoBean>>() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<UserInfoBean>> response) {
                        if (HttpUtil.handleResponse(mContext, response.body())) {
                            initInfo(response.body().getDatas());
                        }
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<LzyResponse<UserInfoBean>> response) {
                        HttpUtil.handleError(mContext, response);
                    }
                });


    }


    @OnClick({R.id.bt_more_order, R.id.bt_mine_order1, R.id.bt_mine_order2, R.id.bt_mine_order3, R.id.bt_mine_order4, R.id.bt_mine_order5})
    public void onOrderClick(View v) {
        switch (v.getId()) {

            case R.id.bt_more_order:    //我的订单

                Intent intent = new Intent(getActivity(), OrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ORDER_INDEX", 0);
                intent.putExtras(bundle);
                startActivity(intent);

                break;
            case R.id.bt_mine_order1://我的订单
                Intent order1 = new Intent(getActivity(), OrderActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("ORDER_INDEX", 1);
                order1.putExtras(bundle1);
                startActivity(order1);
                break;
            case R.id.bt_mine_order2:
                Intent order2 = new Intent(getActivity(), OrderActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putSerializable("ORDER_INDEX", 2);
                order2.putExtras(bundle2);
                startActivity(order2);
                break;
            case R.id.bt_mine_order3:
                Intent order3 = new Intent(getActivity(), OrderActivity.class);
                Bundle bundle3 = new Bundle();
                bundle3.putSerializable("ORDER_INDEX", 3);
                order3.putExtras(bundle3);
                startActivity(order3);
                break;
            case R.id.bt_mine_order4:
                Intent order4 = new Intent(getActivity(), OrderActivity.class);
                Bundle bundle4 = new Bundle();
                bundle4.putSerializable("ORDER_INDEX", 4);
                order4.putExtras(bundle4);
                startActivity(order4);
                break;
            case R.id.bt_mine_order5:
                EventBus.getDefault().post(new MainFragmentStartEvent(OrderServiceFragment.newInstance()));
                break;

        }

    }

    @OnClick({R.id.ll_mine_fav, R.id.customer_image, R.id.tv_mine_perfect, R.id.bt_mine_credit, R.id.ll_follow_store, R.id.ll_mine_foot, R.id.bt_setting, R.id.iv_mine_setting, R.id.iv_mine_msg})
    public void onMyInfoClick(View view) {
        switch (view.getId()) {
            case R.id.ll_mine_fav:
                EventBus.getDefault().post(new MainFragmentStartEvent(FavGoodFragment.newInstance()));
                break;
            case R.id.customer_image:
                EventBus.getDefault().post(new MainFragmentStartEvent(UserInfoFragment.newInstance(userInfo)));
                break;
            case R.id.tv_mine_perfect:
                EventBus.getDefault().post(new MainFragmentStartEvent(UserInfoFragment.newInstance(userInfo)));
                break;
            case R.id.bt_mine_credit:
                EventBus.getDefault().post(new MainFragmentStartEvent(CreditFragment.newInstance(String.valueOf(userInfo.getIntegral()))));
                break;
            case R.id.ll_follow_store:
                EventBus.getDefault().post(new MainFragmentStartEvent(FollowsFragment.newInstance()));
                break;
            case R.id.ll_mine_foot:
                EventBus.getDefault().post(new MainFragmentStartEvent(FooterFragment.newInstance()));
                break;
            case R.id.bt_setting:
                EventBus.getDefault().post(new MainFragmentStartEvent(SettingFragment.newInstance(userInfo)));
                break;
            case R.id.iv_mine_setting:
                EventBus.getDefault().post(new MainFragmentStartEvent(SettingFragment.newInstance(userInfo)));
                break;
            case R.id.iv_mine_msg:
                CommUtil.gotoActivity(getActivity(), MsgActivity.class, false, null);
                break;
        }

    }

    @OnClick({R.id.bt_more_band, R.id.bt_mine_money1, R.id.bt_mine_money2, R.id.bt_mine_money3, R.id.bt_mine_money4})
    public void onMyProClick(View view) {
        switch (view.getId()) {
            case R.id.bt_more_band:
                EventBus.getDefault().post(new MainFragmentStartEvent(ProPertyFragment.newInstance()));
                break;
            case R.id.bt_mine_money1:
                EventBus.getDefault().post(new MainFragmentStartEvent(AccountFragment.newInstance()));
                break;
            case R.id.bt_mine_money2:
                EventBus.getDefault().post(new MainFragmentStartEvent(CouponFragment.newInstance()));
                break;
            case R.id.bt_mine_money3:
                EventBus.getDefault().post(new MainFragmentStartEvent(RedPacketFragment.newInstance()));
                break;
            case R.id.bt_mine_money4:
                EventBus.getDefault().post(new MainFragmentStartEvent(CreditFragment.newInstance(String.valueOf(userInfo.getIntegral()))));
                break;
        }

    }


    private void updataInfo() {
        if (LoginContext.getInstance().isLogin) {
            getInfo();
        } else {
            tv_user_name.setText("");
            tv_follow_store_num.setText("");
            tv_mine_fav_num.setText("");
            tv_mine_foot_num.setText("");
            bt_mine_credit.setText("");
            GlideImageLoader.setImg(mContext, R.drawable.img_defaule, customer_image);
        }


    }

    @Override
    public void onSupportVisible() { //当fragment可见时，检查是否有新消息
        if (MyShopApplication.IsNewMsg) {
            iv_mine_msg.showCirclePointBadge();
        } else {
            iv_mine_msg.hiddenBadge();
        }
        super.onSupportVisible();
    }

    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        registerEventBus(this);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unregisterEventBus(this);
    }


}

