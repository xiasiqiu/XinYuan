package com.xinyuan.xyshop.ui.mine.info;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinyuan.xyshop.MainFragment;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.AddressAdapter;
import com.xinyuan.xyshop.adapter.GoodsGridAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.AddressBean;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.even.TabSelectedEvent;
import com.xinyuan.xyshop.ui.mine.login.LoginActivity;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.dialog.color.ColorDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/27.
 */

public class AddressFragment extends BaseFragment {
	@BindView(R.id.toolbar_iv)
	Toolbar msg_toolbar;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.rv_address)
	RecyclerView rv_address;
	AddressAdapter adapter;

	public static AddressFragment newInstance() {
		AddressFragment fragment = new AddressFragment();
		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_address;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		if (msg_toolbar != null) {
			SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
			SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
			tv_header_center.setText("收货地址");
		}
	}

	List<AddressBean> list = new ArrayList<>();

	@Override
	public void onLazyInitView(@Nullable Bundle savedInstanceState) {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(1);
		this.rv_address.setLayoutManager(layoutManager);

		list.add(new AddressBean(1, "冯熙", "15708446531", true, 51011, "四川省成都市天府新区", " 华阳镇滨河路二段"));
		list.add(new AddressBean(1, "冯熙", "15708446531", false, 51011, "四川省成都市天府新区", " 华阳镇滨河路二段"));
		list.add(new AddressBean(1, "冯熙", "15708446531", false, 51011, "四川省成都市天府新区", " 华阳镇滨河路二段"));
		list.add(new AddressBean(1, "冯熙", "15708446531", false, 51011, "四川省成都市天府新区", " 华阳镇滨河路二段"));
		list.add(new AddressBean(1, "冯熙", "15708446531", false, 51011, "四川省成都市天府新区", " 华阳镇滨河路二段"));
		list.add(new AddressBean(1, "冯熙", "15708446531", false, 51011, "四川省成都市天府新区", " 华阳镇滨河路二段"));
		list.add(new AddressBean(1, "冯熙", "15708446531", false, 51011, "四川省成都市天府新区", " 华阳镇滨河路二段"));
		list.add(new AddressBean(1, "冯熙", "15708446531", false, 51011, "四川省成都市天府新区", " 华阳镇滨河路二段"));
		list.add(new AddressBean(1, "冯熙", "15708446531", false, 51011, "四川省成都市天府新区", " 华阳镇滨河路二段"));
		list.add(new AddressBean(1, "冯熙", "15708446531", false, 51011, "四川省成都市天府新区", " 华阳镇滨河路二段"));
		list.add(new AddressBean(1, "冯熙", "15708446531", false, 51011, "四川省成都市天府新区", " 华阳镇滨河路二段"));
		list.add(new AddressBean(1, "冯熙", "15708446531", false, 51011, "四川省成都市天府新区", " 华阳镇滨河路二段"));

		adapter = new AddressAdapter(R.layout.fragment_address_item, list);
		this.rv_address.setAdapter(adapter);

	}


	@Subscribe
	public void onAddressClick(AddressEven event) {
		start(AddAddressFragment.newInstance(list.get(event.position)));
	}

	@Subscribe
	public void addAddress(AddressBean bean) {

		if (bean.isDefault()) {
			for (AddressBean bean1 : list) {
				bean1.setDefault(false);
			}
		}
		list.add(0, bean);
		adapter.notifyDataSetChanged();
	}


	@OnClick(R.id.bt_add_address)
	public void onClick() {
		start(AddAddressFragment.newInstance(null));
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
}
