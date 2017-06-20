package com.xinyuan.xyshop.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.ui.goods.GoodBusBean;
import com.xinyuan.xyshop.ui.home.UserBusBean;
import com.xinyuan.xyshop.ui.mine.order.OrderActivity;
import com.xinyuan.xyshop.ui.mine.order.OrderDetailActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.log.XLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

	@Override
	public int getLayoutId() {
		return R.layout.fragment_mine;
	}

	@Override
	public void initData(@Nullable Bundle savedInstanceState) {
		EventBus.getDefault().register(this);
		if (true) {
			Intent mIntent = new Intent();
			mIntent.setClass(getActivity(), LoginActivity.class);
			startActivityForResult(mIntent, requestCode);
		}
	}

	@Override
	public void initView() {
		ButterKnife.bind(this, getView());

//		toolbar= (Toolbar) getView().findViewById(R.id.mine_toolbar);
//		if(num==1){
//			SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
//			SystemBarHelper.setHeightAndPadding(getActivity(), toolbar);
//
//		}


		num++;
	}

	@OnClick({R.id.bt_more_order, R.id.bt_mine_order1, R.id.bt_mine_order2, R.id.bt_mine_order3, R.id.bt_mine_order4, R.id.bt_mine_order5})
	public void onClick(View v) {
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
				break;

		}

	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	//第2步:注册一个在后台线程执行的方法,用于接收事件
	public void onUserEvent(UserBusBean userBusBean) {//参数必须是ClassEvent类型, 否则不会调用此方法
		if (userBusBean.getFlag().equals(UserBusBean.UserId)) {
			String id = (String) userBusBean.getObj();
		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data != null) {
			String change01 = data.getStringExtra("userId");
			XLog.v("用户ID" + change01);
		} else {

			EventBus.getDefault().post(new UserBusBean(UserBusBean.HomeIndex, 0));
		}


	}

}
