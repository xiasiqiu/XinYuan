package com.xinyuan.xyshop.ui.mine.pro;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.common.ShopHelper;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.bean.ProPertyBean;
import com.xinyuan.xyshop.bean.UserInfoBean;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.even.MainFragmentStartEvent;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.XEmptyUtils;

import org.greenrobot.eventbus.EventBus;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.badgeview.BGABadgeImageView;

/**
 * Created by Administrator on 2017/6/26.
 */

public class ProPertyFragment extends BaseFragment {
	@BindView(R.id.toolbar_iv)
	Toolbar msg_toolbar;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;

	@BindView(R.id.tv_pro_account_num)
	TextView tv_pro_account_num;
	@BindView(R.id.tv_mine_coupon_num)
	TextView tv_mine_coupon_num;
	@BindView(R.id.tv_mine_redpacke_num)
	TextView tv_mine_redpacke_num;
	@BindView(R.id.tv_mine_credit_num)
	TextView tv_mine_credit_num;

	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;
	@BindView(R.id.iv_header_right)
	BGABadgeImageView iv_header_right;
	private String moneny;
	private String creditNum;

	public static ProPertyFragment newInstance() {
		ProPertyFragment fragment = new ProPertyFragment();
		return fragment;
	}


	@OnClick({R.id.rl_pro_account, R.id.rl_pro_coupon, R.id.rl_pro_redpacke, R.id.rl_pro_credit})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.rl_pro_account:
				EventBus.getDefault().post(new MainFragmentStartEvent(AccountFragment.newInstance()));
				break;
			case R.id.rl_pro_coupon:
				EventBus.getDefault().post(new MainFragmentStartEvent(CouponFragment.newInstance()));
				break;
			case R.id.rl_pro_redpacke:
				EventBus.getDefault().post(new MainFragmentStartEvent(RedPacketFragment.newInstance()));
				break;
			case R.id.rl_pro_credit:
				EventBus.getDefault().post(new MainFragmentStartEvent(CreditFragment.newInstance(creditNum)));
				break;
		}
	}

	@Override
	public void initView(View rootView) {
		SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
		tv_header_center.setText("我的财产");
		CommUtil.initToolBar(getActivity(), iv_header_left, iv_header_right);

	}

	@Override
	public void initData() {
		OkGo.<LzyResponse<ProPertyBean>>post(Urls.URL_USER_PROPERTY)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("id", MyShopApplication.userId)
				.execute(new JsonCallback<LzyResponse<ProPertyBean>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<ProPertyBean>> response) {
						if (HttpUtil.handleResponse(mContext, response.body())) {
							setNums(response.body().datas);
						}
					}


					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<ProPertyBean>> response) {
						HttpUtil.handleError(mContext, response);
					}
				});
	}

	private void setNums(ProPertyBean datas) {
		if (!XEmptyUtils.isEmpty(datas.getBalance())) {
			tv_pro_account_num.setText(ShopHelper.getPriceString(datas.getBalance()));
			moneny = ShopHelper.getPriceString(datas.getBalance());
		} else {
			tv_pro_account_num.setText(ShopHelper.getPriceString(new BigDecimal(0.00)));
			moneny = "0.00";
		}
		if (!XEmptyUtils.isEmpty(datas.getCoupon())) {
			tv_mine_coupon_num.setText(String.valueOf(datas.getCoupon()) + "张");

		}
		if (!XEmptyUtils.isEmpty(datas.getRedpacket())) {
			tv_mine_redpacke_num.setText(String.valueOf(datas.getRedpacket()) + "张");

		}
		if (!XEmptyUtils.isEmpty(datas.getIntegral())) {
			tv_mine_credit_num.setText(String.valueOf(datas.getIntegral()) + "分");
			creditNum = String.valueOf(datas.getIntegral());
		}

	}

	@Override
	public void onSupportVisible() { //当fragment可见时，检查是否有新消息
		if (MyShopApplication.IsNewMsg) {
			iv_header_right.showCirclePointBadge();
		} else {
			iv_header_right.hiddenBadge();
		}
		super.onSupportVisible();
	}

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_property;
	}


}
