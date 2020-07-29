package com.xinyuan.xyshop.ui.mine.info;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.AddressAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.bean.AddressBean;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.AddressModel;
import com.xinyuan.xyshop.ui.mine.info.address.AddAddressFragment;
import com.xinyuan.xyshop.even.AddressEven;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.loadingview.XLoadingView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.badgeview.BGABadgeImageView;

/**
 * Created by fx on 2017/6/27.
 * 收货地址列表fragment
 */

public class AddressFragment extends BaseFragment {
	@BindView(R.id.toolbar_iv)
	Toolbar msg_toolbar;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.rv_address)
	RecyclerView rv_address;

	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;
	@BindView(R.id.iv_header_right)
	BGABadgeImageView iv_header_right;
	@BindView(R.id.loadingView)
	XLoadingView loadingView;
	AddressAdapter adapter;
	public static List<AddressBean> Addresslist = new ArrayList<>();
	private AddressModel model = new AddressModel();

	public static AddressFragment newInstance() {
		AddressFragment fragment = new AddressFragment();
		return fragment;
	}


	@Override
	public void initView(View rootView) {
		SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
		tv_header_center.setText("收货地址");
		CommUtil.initToolBar(_mActivity, mContext, iv_header_left, iv_header_right);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(1);
		this.rv_address.setLayoutManager(layoutManager);

	}

	@Override
	public void initData() {
		getAddress();
	}

	private void showAddress(AddressModel addressModel) {

		if (!XEmptyUtils.isEmpty(addressModel)) {
			if (XEmptyUtils.isEmpty(adapter)) {

				this.Addresslist = addressModel.getAddressList();
				adapter = new AddressAdapter(R.layout.fragment_address_item, Addresslist);
				this.rv_address.setAdapter(adapter);
				loadingView.showContent();
			} else {
				this.Addresslist = addressModel.getAddressList();
				adapter.setNewData(addressModel.getAddressList());
				loadingView.showContent();
			}
		} else {
			loadingView.showEmpty();
		}

	}


	@Subscribe
	public void onAddressEven(AddressEven event) {
		if (event.isUpdate()) { //新增加了地址，需要更新
			getAddress();
		} else {            //点击了编辑按钮，需要编辑
			start(AddAddressFragment.newInstance(Addresslist.get(event.position)));
		}

	}

	@Subscribe
	public void addAddress(AddressBean bean) {
		getAddress();
	}


	@OnClick(R.id.bt_add_address)
	public void onClick() {
		start(AddAddressFragment.newInstance(null));
	}

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_address;
	}

	/**
	 * 获取地址列表
	 */
	private void getAddress() {
		loadingView.showLoading();
		OkGo.<LzyResponse<AddressModel>>post(Urls.URL_USER_ADDRESS)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("id", MyShopApplication.userId)
				.params("ship", 1)
				.params("limit", 20)
				.execute(new JsonCallback<LzyResponse<AddressModel>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<AddressModel>> response) {
						if (HttpUtil.handleResponse(mContext, response.body())) {
							showAddress(response.body().datas);
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<AddressModel>> response) {
						HttpUtil.handleError(mContext, response);
					}
				});
	}


	@Override
	public void onStart() {
		super.onStart();
		EventBus.getDefault().register(this);

	}

	@Override
	public void onStop() {
		model.setGoodsList(this.Addresslist);
		EventBus.getDefault().post(this.model);
		EventBus.getDefault().unregister(this);
		super.onStop();
	}

}
