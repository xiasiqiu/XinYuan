package com.xinyuan.xyshop.ui.mine.info.address;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.AddressBean;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.even.AddressEven;
import com.xinyuan.xyshop.even.AreaEven;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.EditTextWithDel;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.XRegexUtils;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fx on 2017/7/4.
 * 新增收货地址fragment
 */

public class AddAddressFragment extends BaseFragment {
	@BindView(R.id.toolbar_tv)
	Toolbar toolbar_tv;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;

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
	private static String addressId = null;
	private static String name = null;
	private static String phone = null;
	private static String area = null;
	private static String area_info = null;
	private static String isDefault = null;
	private AddressBean address;

	public static AddAddressFragment newInstance(AddressBean addressBean) {
		AddAddressFragment fragment = new AddAddressFragment();
		fragment.address = addressBean;
		return fragment;
	}


	@OnClick({R.id.rl_area, R.id.bt_submit})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.rl_area:
				start(AreaContentFragment.newInstance(0));
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
				} else if (XEmptyUtils.isSpace(ed_address_content.getText().toString())) {
					XToast.error("请输入具体地址！");
					return;
				}
				name = ed_address_name.getText().toString().trim();
				phone = ed_address_phone.getText().toString().trim();
				area_info = ed_address_content.getText().toString().trim();
				updateAddress();


		}
	}


	private void updateAddress() {
		OkGo.<LzyResponse<List<TokenBean>>>post(Urls.URL_USER_ADDRESS_UPDATE)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("id", MyShopApplication.userId)
				.params("userName", name)
				.params("addressId", addressId)
				.params("phone", phone)
				.params("areaId", area)
				.params("addressInfo", area_info)
				.params("isDefault", isDefault)
				.execute(new JsonCallback<LzyResponse<List<TokenBean>>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<List<TokenBean>>> response) {
						if (HttpUtil.handleResponse(mContext, response.body())) {
							XToast.info(response.body().msg);
							EventBus.getDefault().post(new AddressEven(0, true));
							_mActivity.onBackPressed();
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<List<TokenBean>>> response) {
						HttpUtil.handleError(mContext, response);
					}
				});

	}

	@Subscribe
	public void choseAddress(AreaEven even) {
		if (!XEmptyUtils.isEmpty(even.getArea_id())) {
			area = String.valueOf(even.getArea_id());
			tv_area.setText(even.getArea_name());
		}

	}


	@Override
	public void initView(View rootView) {
		SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_tv);
		CommUtil.initToolBar(getActivity(), iv_header_left, null);
		EventBus.getDefault().register(this);
		if (!XEmptyUtils.isEmpty(address)) {
			ed_address_name.setText(address.getUserName());
			ed_address_phone.setText(address.getMobile());
			tv_area.setText(address.getAddress());
			ed_address_content.setText(address.getAddressInfo());
			addressId = String.valueOf(address.getAddressId());
			if (address.getIsDefault() == 1) {
				cb_address_default.setChecked(true);
			}
			tv_header_center.setText("修改收货地址");
		} else {
			tv_header_center.setText("新增收货地址");
		}

		cb_address_default.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
				if (b) {
					isDefault = "1";
				} else {
					isDefault = "0";
				}
			}
		});

	}

	@Override
	public void initData() {

	}

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_address_add;
	}

	@Override
	public void onStop() {
		EventBus.getDefault().unregister(this);
		super.onStop();
	}


}
