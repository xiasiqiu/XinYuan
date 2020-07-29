package com.xinyuan.xyshop.ui.home;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.MenuMoreAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.SignBean;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.state.LoginContext;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.even.MainFragmentStartEvent;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.entity.ItemData;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.ui.goods.credit.CreditMallFragment;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailActivity;
import com.xinyuan.xyshop.ui.goods.groupbuy.GroupBuyActivity;
import com.xinyuan.xyshop.ui.goods.store.StoreActivity;
import com.xinyuan.xyshop.ui.mine.login.LoginActivity;
import com.xinyuan.xyshop.ui.mine.login.LoginFragment;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.dialog.SignDialog;
import com.youth.xframe.widget.XToast;
import com.youth.xframe.widget.loadingview.XLoadingView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cn.bingoogolapple.badgeview.BGABadgeImageView;

/**
 * Created by fx on 2017/7/3.
 * 菜单fragment
 */

public class MenuFragment extends BaseFragment {

	@BindView(R.id.rv_menu)
	RecyclerView rv_menu;
	MenuMoreAdapter adapter;
	@BindView(R.id.toolbar_iv)
	Toolbar toolbar_iv;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.lodingView)
	XLoadingView lodingView;
	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;
	@BindView(R.id.iv_header_right)
	BGABadgeImageView iv_header_right;

	public static MenuFragment newInstance() {
		MenuFragment fragment = new MenuFragment();
		return fragment;
	}


	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.activity_menu;
	}


	@Override
	public void initView(View rootView) {
		tv_header_center.setText("全部菜单");
		SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_iv);
		CommUtil.initToolBar(getActivity(), iv_header_left, null);
		lodingView.showLoading();
	}

	@Override
	public void initData() {
		OkGo.<LzyResponse<List<ItemData>>>get(Urls.URL_MENU)
				.tag(this)
				.execute(new JsonCallback<LzyResponse<List<ItemData>>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<List<ItemData>>> response) {
						if (HttpUtil.handleResponse(mContext, response.body())) {
							inRV(response.body().getDatas());
						}

					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<List<ItemData>>> response) {
						XToast.error("请求错误");
					}
				});
	}


	private void inRV(List<ItemData> brands) {
		adapter = new MenuMoreAdapter(1,R.layout.item_menu, brands);
		final GridLayoutManager manager = new GridLayoutManager(mContext, 4);
		rv_menu.setLayoutManager(manager);
		rv_menu.setAdapter(adapter);
		lodingView.showContent();
		adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
				OnImageViewClick(view, brands.get(position));
			}
		});

	}


	public void OnImageViewClick(View view, ItemData itemData) {
		Map<String, String> params = new HashMap();
		if (itemData.getType().equals(Constants.GOOD)) {
			params.put(Constants.GOODID, itemData.getData());
			params.put(Constants.GOODTYPE, "1");
			CommUtil.gotoActivity(getActivity(), GoodDetailActivity.class, false, params);
		} else if (itemData.getType().equals(Constants.STOREID)) {
			params.put(Constants.STOREID, itemData.getData());
			CommUtil.gotoActivity(getActivity(), StoreActivity.class, false, params);
		} else if (itemData.getType().equals(Constants.HTML)) {
			params.put(Constants.URL, itemData.getData());
			CommUtil.gotoActivity(getActivity(), WebViewActivity.class, false, params);
		} else if (itemData.getType().equals(Constants.NATIVE)) {
			if (itemData.getText().equals(mContext.getResources().getString(R.string.brandtitle))) {
				EventBus.getDefault().post(new MainFragmentStartEvent(BrandFragment.newInstance()));
			} else if (itemData.getText().equals(getString(R.string.credit_mall))) {
				EventBus.getDefault().post(new MainFragmentStartEvent(CreditMallFragment.newInstance()));
			} else if (itemData.getText().equals(getString(R.string.group_mall))) {
				CommUtil.gotoActivity(mContext, GroupBuyActivity.class, false, null);
			} else if (itemData.getText().equals(getString(R.string.sign_day))) {
				if (LoginContext.isLogin) {
					SignDay();
				} else {
					CommUtil.gotoActivity(getActivity(), LoginActivity.class, false, null);
				}
			} else if (itemData.getText().equals(getString(R.string.menu_more))) {
				EventBus.getDefault().post(new MainFragmentStartEvent(MenuFragment.newInstance()));
			}


		}
	}

	private void SignDay() {
		OkGo.<LzyResponse<SignBean>>post(Urls.URL_SIGN)
				.tag(this)
				.isMultipart(true)
				.headers("token", MyShopApplication.Token)
				.params("id", MyShopApplication.userId)
				.execute(new JsonCallback<LzyResponse<SignBean>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<SignBean>> response) {
						if (HttpUtil.handleResponse(getActivity(), response.body())) {
							showSign(true, response.body().getDatas().getNum());
						} else {
							showSign(false, "");
						}


					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<SignBean>> response) {
						HttpUtil.handleError(getActivity(), response);
					}
				});
	}

	public void showSign(boolean status, String num) {
		SignDialog.Builder builder = new SignDialog.Builder(getActivity(), status, num);
		builder.create().show();
	}
}
