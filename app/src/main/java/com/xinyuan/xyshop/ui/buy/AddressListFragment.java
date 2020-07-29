package com.xinyuan.xyshop.ui.buy;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.AddressListAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.AddressModel;
import com.xinyuan.xyshop.ui.mine.info.AddressFragment;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.loadingview.XLoadingView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * Created by fx on 2017/8/21
 * 提交订单-选择收货地址列表界面
 */

public class AddressListFragment extends BaseFragment<BasePresenter> {
	@BindView(R.id.rv_address)
	RecyclerView rv_address;
	@BindView(R.id.toolbar_tv)
	Toolbar toolbar_tv;
	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.tv_header_right)
	TextView tv_header_right;
	@BindView(R.id.loadingView)
	XLoadingView loadingView;

	private AddressListAdapter addressAdapter;
	private boolean ischoose = false;           //是否是编辑状态

	public static AddressListFragment newInstance() {
		AddressListFragment fragment = new AddressListFragment();
		return fragment;
	}

	@Override
	public void initView(View rootView) {
		SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_tv);
		tv_header_center.setText("选择收货地址");
		tv_header_right.setText("管理");
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(1);
		this.rv_address.setLayoutManager(layoutManager);
	}


	@Override
	public void initListener() {
		iv_header_left.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				_mActivity.onBackPressed();
			}
		});
		tv_header_right.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				start(AddressFragment.newInstance());
			}
		});

	}

	private void setList(final AddressModel addressModel) {
		if (XEmptyUtils.isEmpty(addressModel)) {
			start(AddressFragment.newInstance());
			loadingView.showEmpty();
		} else {
			addressAdapter = new AddressListAdapter(R.layout.item_buy_address, addressModel.getAddressList());
			this.rv_address.setAdapter(addressAdapter);
			loadingView.showContent();
			addressAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
				@Override
				public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
					EventBus.getDefault().post(adapter.getData().get(position));
					ischoose = true;
					_mActivity.onBackPressed();
				}
			});
		}


	}

	/**
	 * 获取用户收货地址
	 */
	@Override
	public void initData() {
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
							setList(response.body().datas);

						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<AddressModel>> response) {
						HttpUtil.handleError(mContext, response);
					}
				});
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void updateList(AddressModel addressModel) {
		if (XEmptyUtils.isEmpty(addressModel.getAddressList())) {   //如果没有收货地址就返回，有则显示
			_mActivity.onBackPressed();
		} else {
			setList(addressModel);
		}

	}

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_buy_address;
	}

	@Override
	public void onStart() {
		super.onStart();
		EventBus.getDefault().register(this);

	}

	@Override
	public void onStop() {
		if (!ischoose) {
			if (!XEmptyUtils.isEmpty(addressAdapter)) {
				if (!XEmptyUtils.isEmpty(addressAdapter.getData())) {
					EventBus.getDefault().post(addressAdapter.getData().get(0));

				}
			}
		}
		EventBus.getDefault().unregister(this);
		super.onStop();
	}
}
