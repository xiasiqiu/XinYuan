package com.xinyuan.xyshop.ui.mine.info;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.xinyuan.xyshop.MainFragment;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.AddressBean;
import com.xinyuan.xyshop.entity.AreaBean;
import com.xinyuan.xyshop.even.TabSelectedEvent;
import com.xinyuan.xyshop.ui.mine.login.LoginActivity;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.EditTextWithDel;
import com.youth.xframe.utils.XAppUtils;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.XRegexUtils;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/4.
 */

public class AddAddressFragment extends BaseFragment {
	private static AddressBean address;
	@BindView(R.id.toolbar_tv)
	Toolbar toolbar_tv;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;

	@BindView(R.id.ed_address_name)
	EditTextWithDel ed_address_name;
	@BindView(R.id.ed_address_phone)
	EditTextWithDel ed_address_phone;
	@BindView(R.id.ed_address_content)
	EditTextWithDel ed_address_content;
	@BindView(R.id.cb_address_default)
	CheckBox cb_address_default;

	@BindView(R.id.tv_area)
	TextView tv_area;
	@BindView(R.id.tv_street)
	TextView tv_street;

	public static AddAddressFragment newInstance(AddressBean addressBean) {
		AddAddressFragment fragment = new AddAddressFragment();
		address = addressBean;
		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_address_add;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		if (toolbar_tv != null) {
			SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
			SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_tv);
			tv_header_center.setText("新增收货地址");
		}

		if (!XEmptyUtils.isEmpty(address)) {
			ed_address_name.setText(address.getName());
			ed_address_phone.setText(address.getPhone());
			tv_area.setText(address.getAreaName());
			tv_street.setText(address.getStreetName());
			if (address.isDefault()) {
				cb_address_default.setChecked(true);
			}

		}
	}


	@OnClick({R.id.rl_area, R.id.rl_area_street, R.id.bt_submit})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.rl_area:
				start(AddressContentFragment.newInstance(0));
				break;
			case R.id.rl_area_street:
				if (XEmptyUtils.isSpace(tv_area.getText().toString())) {
					XToast.info("请先选择地区");
				} else {
					start(AddressContentFragment.newInstance(3));

				}
				break;
			case R.id.bt_submit:
				if (XEmptyUtils.isSpace(ed_address_name.getText().toString())) {
					XToast.error("请填写收货人姓名！");
					return;
				} else if (XEmptyUtils.isSpace(ed_address_phone.getText().toString())) {
					XToast.error("请输入手机号！");
					return;
				} else if (!XRegexUtils.checkMobile(ed_address_phone.getText().toString())) {
					XToast.error("手机号格式不正确！");
					return;
				} else if (XEmptyUtils.isSpace(tv_area.getText().toString())) {
					XToast.error("请选择地区！");
					return;
				} else if (XEmptyUtils.isSpace(tv_street.getText().toString())) {
					XToast.error("请选择街道");
					return;
				} else if (XEmptyUtils.isSpace(ed_address_content.getText().toString())) {
					XToast.error("请输入具体地址！");
					return;
				}
				AddressBean addressBean = new AddressBean(areaId, ed_address_name.getText().toString(), ed_address_phone.getText().toString(),
						cb_address_default.isChecked(), areaId, tv_area.getText().toString(), tv_street.getText().toString());

				_mActivity.onBackPressed();
				EventBus.getDefault().post(addressBean);
				XToast.success("提交成功！");


		}
	}

	private static int areaId = 0;

	@Subscribe
	public void onTabSelectedEvent(List<AreaBean> list) {
		if (list.size() == 3) {
			String str = "";
			for (AreaBean s : list) {
				str += s.getAreaName();
			}
			AreaBean a = list.get(list.size() - 1);
			areaId = a.getArea_id();
			tv_area.setText(str);

		} else if (list.size() == 1) {
			String str = "";
			for (AreaBean s : list) {
				str += s.getAreaName();
			}

			tv_street.setText(str);

		}


	}

	@Override
	public void onStop() {
		EventBus.getDefault().unregister(this);
		super.onStop();
	}

	@Override
	public void onStart() {
		EventBus.getDefault().register(this);
		super.onStart();

	}

}
