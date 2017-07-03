package com.xinyuan.xyshop.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.xinyuan.xyshop.MainFragment;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.even.StartBrotherEvent;
import com.xinyuan.xyshop.even.TabSelectedEvent;
import com.xinyuan.xyshop.ui.mine.info.FavFragment;
import com.xinyuan.xyshop.ui.mine.info.FollowFragment;
import com.xinyuan.xyshop.ui.mine.info.FooterFragment;
import com.xinyuan.xyshop.ui.mine.info.SettingFragment;
import com.xinyuan.xyshop.ui.mine.info.UserInfoFragment;
import com.xinyuan.xyshop.ui.mine.login.LoginActivity;
import com.xinyuan.xyshop.ui.mine.order.OrderActivity;
import com.xinyuan.xyshop.ui.mine.order.fragment.OrderServiceFragment;
import com.xinyuan.xyshop.ui.mine.pro.AccountFragment;
import com.xinyuan.xyshop.ui.mine.pro.CouponFragment;
import com.xinyuan.xyshop.ui.mine.pro.CreditFragment;
import com.xinyuan.xyshop.ui.mine.pro.ProPertyFragment;
import com.xinyuan.xyshop.util.CommUtil;
import com.youth.xframe.utils.log.XLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fx on 2017/5/2 0002.
 */

public class MineFragment extends BaseFragment {

	private Toolbar toolbar;

	private static int num = 1;
	@BindView(R.id.bt_more_order)
	Button bt_more_order;
	private int requestCode;

	public static MineFragment newInstance() {

		Bundle args = new Bundle();

		MineFragment fragment = new MineFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_mine;
	}

	@Override
	public void initData(@Nullable Bundle savedInstanceState) {
		EventBus.getDefault().register(this);
	}

	@Override
	public void initView() {
		ButterKnife.bind(this, getView());
		XLog.v("加载个人页面Fragment");
		MyShopApplication.isLogin = false;
	}

	@OnClick({R.id.bt_more_order, R.id.bt_mine_order1, R.id.bt_mine_order2, R.id.bt_mine_order3, R.id.bt_mine_order4, R.id.bt_mine_order5})
	public void onOrderClick(View v) {
		switch (v.getId()) {

			case R.id.bt_more_order:

				Intent intent = new Intent(getActivity(), OrderActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("order_index", 0);
				intent.putExtras(bundle);
				startActivity(intent);

				break;
			case R.id.bt_mine_order1:
				Intent order1 = new Intent(getActivity(), OrderActivity.class);
				Bundle bundle1 = new Bundle();
				bundle1.putSerializable("order_index", 1);
				order1.putExtras(bundle1);
				startActivity(order1);
				break;
			case R.id.bt_mine_order2:
				Intent order2 = new Intent(getActivity(), OrderActivity.class);
				Bundle bundle2 = new Bundle();
				bundle2.putSerializable("order_index", 2);
				order2.putExtras(bundle2);
				startActivity(order2);
				break;
			case R.id.bt_mine_order3:
				Intent order3 = new Intent(getActivity(), OrderActivity.class);
				Bundle bundle3 = new Bundle();
				bundle3.putSerializable("order_index", 3);
				order3.putExtras(bundle3);
				startActivity(order3);
				break;
			case R.id.bt_mine_order4:
				Intent order4 = new Intent(getActivity(), OrderActivity.class);
				Bundle bundle4 = new Bundle();
				bundle4.putSerializable("order_index", 4);
				order4.putExtras(bundle4);
				startActivity(order4);
				break;
			case R.id.bt_mine_order5:
				EventBus.getDefault().post(new StartBrotherEvent(OrderServiceFragment.newInstance()));
				break;

		}

	}

	@OnClick({R.id.ll_mine_fav, R.id.customer_image, R.id.tv_mine_perfect, R.id.bt_mine_credit, R.id.ll_follow_store, R.id.ll_mine_foot, R.id.bt_setting, R.id.iv_mine_setting, R.id.iv_mine_msg})
	public void onMyInfoClick(View view) {
		switch (view.getId()) {
			case R.id.ll_mine_fav:
				EventBus.getDefault().post(new StartBrotherEvent(FavFragment.newInstance()));
				break;
			case R.id.customer_image:
				EventBus.getDefault().post(new StartBrotherEvent(UserInfoFragment.newInstance()));
				break;
			case R.id.tv_mine_perfect:
				EventBus.getDefault().post(new StartBrotherEvent(UserInfoFragment.newInstance()));
				break;
			case R.id.bt_mine_credit:
				EventBus.getDefault().post(new StartBrotherEvent(CreditFragment.newInstance()));
				break;
			case R.id.ll_follow_store:
				EventBus.getDefault().post(new StartBrotherEvent(FollowFragment.newInstance()));
				break;
			case R.id.ll_mine_foot:
				EventBus.getDefault().post(new StartBrotherEvent(FooterFragment.newInstance()));
				break;
			case R.id.bt_setting:
				EventBus.getDefault().post(new StartBrotherEvent(SettingFragment.newInstance()));
				break;
			case R.id.iv_mine_setting:
				EventBus.getDefault().post(new StartBrotherEvent(SettingFragment.newInstance()));
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
				EventBus.getDefault().post(new StartBrotherEvent(ProPertyFragment.newInstance()));
				break;
			case R.id.bt_mine_money1:
				EventBus.getDefault().post(new StartBrotherEvent(AccountFragment.newInstance()));
				break;
			case R.id.bt_mine_money2:
				EventBus.getDefault().post(new StartBrotherEvent(CouponFragment.newInstance()));
				break;
			case R.id.bt_mine_money3:
				EventBus.getDefault().post(new StartBrotherEvent(CouponFragment.newInstance()));
				break;
			case R.id.bt_mine_money4:
				EventBus.getDefault().post(new StartBrotherEvent(CreditFragment.newInstance()));
				break;
		}

	}

	private static final int REQ_USER = 100;


	@Subscribe
	public void onTabSelectedEvent(TabSelectedEvent event) {
		if (event.position != MainFragment.MINE) return;
		if (!MyShopApplication.isLogin) {
			Intent intent = new Intent(getActivity(), LoginActivity.class);
			startActivityForResult(intent, REQ_USER);
			MyShopApplication.isLogin = true;
		}

	}
}

