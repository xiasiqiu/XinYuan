package com.xinyuan.xyshop.ui.buy;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.OrderOnGoodBean;
import com.xinyuan.xyshop.callback.DialogCallback;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.common.ShopHelper;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.ConfirmOrderModel;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.EditTextWithDel;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.XRegexUtils;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fx on 2017/8/12.
 * 虚拟商品确认订单
 */

public class ConfirmOrderOnFragment extends BaseFragment<BasePresenter> {
	@BindView(R.id.toolbar_iv)
	Toolbar toolbar_iv;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;
	@BindView(R.id.iv_header_right)
	ImageView iv_header_right;
	@BindView(R.id.iv_good_img)
	ImageView iv_good_img;
	@BindView(R.id.tv_good_name)
	TextView tv_good_name;
	@BindView(R.id.tv_good_spec)
	TextView tv_good_spec;
	@BindView(R.id.tv_good_price)
	TextView tv_good_price;
	@BindView(R.id.tv_good_num)
	TextView tv_good_num;
	@BindView(R.id.ed_user_phone)
	EditTextWithDel ed_user_phone;
	@BindView(R.id.tv_order_price)
	TextView tv_order_price;

	private String price;
	private OrderOnGoodBean orderOnGoodBean;

	public static ConfirmOrderOnFragment newInstance() {
		ConfirmOrderOnFragment fragment = new ConfirmOrderOnFragment();
		return fragment;
	}

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_confirm_order_online;
	}


	@Override
	public void initView(View rootView) {
		SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_iv);
		tv_header_center.setText("确认虚拟商品订单");
		CommUtil.initToolBar(getActivity(), iv_header_left, iv_header_right);
	}


	private void showOrder(OrderOnGoodBean bean) {
		this.orderOnGoodBean = bean;
		GlideImageLoader.setUrlImg(mContext, bean.getImgUrl(), iv_good_img);
		tv_good_name.setText(bean.getGoodName());
		tv_good_num.setText("X" + bean.getGoodNum());
		tv_good_price.setText(getResources().getText(R.string.money_rmb) + ShopHelper.getPriceString(bean.getPrice()));
		tv_order_price.setText("实付款：" + getResources().getText(R.string.money_rmb) + ShopHelper.getPriceString(bean.getPrice()));
		tv_good_spec.setText(bean.getGoodSpec());
		price = ShopHelper.getPriceString(bean.getPrice());
	}

	@Override
	public void initData() {

	}

	//订单页面各组件通信，发票、优惠券、红包、地址的选择和更改
	@Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
	public void BuyBusEven(OrderOnGoodBean even) {
		if (!XEmptyUtils.isEmpty(even)) {
			showOrder(even);
		}
	}

	@OnClick(R.id.tv_order_post)
	public void putOrder() {
		if (XEmptyUtils.isSpace(ed_user_phone.getText().toString().trim())) {
			XToast.error("请输入您的手机号码");
		} else if (!XRegexUtils.checkMobile(ed_user_phone.getText().toString().trim())) {
			XToast.error("您的手机号码格式不正确");
		} else {
			postOrder(orderOnGoodBean.getGoodId(), orderOnGoodBean.getGoodNum(), ed_user_phone.getText().toString().trim());
		}
	}

	/**
	 * 提交虚拟订单
	 *
	 * @param goodsId
	 * @param count
	 * @param phone
	 */
	public void postOrder(long goodsId, int count, String phone) {
		OkGo.<LzyResponse<ConfirmOrderModel>>post(Urls.URL_USER_PUT_ONORDER)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("goodsId", goodsId)
				.params("count", count)
				.params("phone", phone)
				.params("buyer_msg", "")
				.params("type", "android")
				.execute(new DialogCallback<LzyResponse<ConfirmOrderModel>>(getActivity(), "....订单提交中") {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<ConfirmOrderModel>> response) {
						if (HttpUtil.handleResponse(mContext, response.body())) {
							XToast.info("订单提交成功");
							startWithPop(PayFragment.newInstance(response.body().getDatas().getOrderNumber(), response.body().getDatas().getSumMoney()));
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<ConfirmOrderModel>> response) {
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
		super.onStop();
		EventBus.getDefault().unregister(this);

	}
}
