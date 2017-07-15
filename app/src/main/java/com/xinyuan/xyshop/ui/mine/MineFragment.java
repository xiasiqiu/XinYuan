package com.xinyuan.xyshop.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.xinyuan.xyshop.MainFragment;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.TestUserInfo;
import com.xinyuan.xyshop.entity.UserInfo;
import com.xinyuan.xyshop.even.LoginPageEvent;
import com.xinyuan.xyshop.even.MainFragmentStartEvent;
import com.xinyuan.xyshop.even.TabSelectedEvent;
import com.xinyuan.xyshop.http.Urls;
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
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.xinyuan.xyshop.util.JsonUtil;
import com.youth.xframe.utils.log.XLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by fx on 2017/5/2 0002.
 */

public class MineFragment extends BaseFragment {


	private static int num = 1;
	@BindView(R.id.bt_more_order)
	Button bt_more_order;
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
	private UserInfo userInfo;

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

	private void initDatas() {
		OkGo.get(Urls.URL_USER_INFO)
				.execute(new StringCallback() {
					@Override
					public void onSuccess(String s, Call call, Response response) {
						TestUserInfo userInfo = JsonUtil.toBean(s, TestUserInfo.class);
						initInfo(userInfo.getDatas());

					}

					@Override
					public void onError(Call call, Response response, Exception e) {

					}
				});
	}


	@Override
	public void initView() {
		EventBus.getDefault().register(this);
	}


	private void initInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
		tv_user_name.setText(userInfo.getUserName());
		tv_follow_store_num.setText(userInfo.getFollowNum());
		tv_mine_fav_num.setText(userInfo.getFavNum());
		tv_mine_foot_num.setText(userInfo.getHistoryNum());
		bt_mine_credit.setText("我的积分" + userInfo.getUserCredit());
		XLog.v("URL" + userInfo.getHeadImg());
		Glide.with(context).load(userInfo.getHeadImg()).into(customer_image);

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
				EventBus.getDefault().post(new MainFragmentStartEvent(OrderServiceFragment.newInstance()));
				break;

		}

	}

	@OnClick({R.id.ll_mine_fav, R.id.customer_image, R.id.tv_mine_perfect, R.id.bt_mine_credit, R.id.ll_follow_store, R.id.ll_mine_foot, R.id.bt_setting, R.id.iv_mine_setting, R.id.iv_mine_msg})
	public void onMyInfoClick(View view) {
		switch (view.getId()) {
			case R.id.ll_mine_fav:
				EventBus.getDefault().post(new MainFragmentStartEvent(FavFragment.newInstance()));
				break;
			case R.id.customer_image:
				EventBus.getDefault().post(new MainFragmentStartEvent(UserInfoFragment.newInstance()));
				break;
			case R.id.tv_mine_perfect:
				EventBus.getDefault().post(new MainFragmentStartEvent(UserInfoFragment.newInstance()));
				break;
			case R.id.bt_mine_credit:
				EventBus.getDefault().post(new MainFragmentStartEvent(CreditFragment.newInstance()));
				break;
			case R.id.ll_follow_store:
				EventBus.getDefault().post(new MainFragmentStartEvent(FollowFragment.newInstance()));
				break;
			case R.id.ll_mine_foot:
				EventBus.getDefault().post(new MainFragmentStartEvent(FooterFragment.newInstance()));
				break;
			case R.id.bt_setting:
				EventBus.getDefault().post(new MainFragmentStartEvent(SettingFragment.newInstance()));
				break;
			case R.id.iv_mine_setting:
				EventBus.getDefault().post(new MainFragmentStartEvent(SettingFragment.newInstance()));
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
				EventBus.getDefault().post(new MainFragmentStartEvent(CouponFragment.newInstance()));
				break;
			case R.id.bt_mine_money4:
				EventBus.getDefault().post(new MainFragmentStartEvent(CreditFragment.newInstance()));
				break;
		}

	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onEvent(LoginPageEvent event) {
		if (event.isLoginStatus()) {
			initDatas();
		}

	}

	@Subscribe
	public void onTabSelectedEvent(TabSelectedEvent event) {
		if (event.position != MainFragment.MINE) return;
		if (userInfo == null) {
			if (!MyShopApplication.isLogin) {
				CommUtil.gotoActivity(getActivity(), LoginActivity.class, false, null);
			} else {
				initDatas();
			}
		}


	}


	@Override
	public void initData(@Nullable Bundle savedInstanceState) {

	}


	@Override
	public void onDestroyView() {
		super.onDestroyView();
		EventBus.getDefault().unregister(this);
	}
}

