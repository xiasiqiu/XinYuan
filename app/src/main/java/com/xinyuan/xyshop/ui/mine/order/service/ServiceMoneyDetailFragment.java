package com.xinyuan.xyshop.ui.mine.order.service;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.bean.OrderBean;
import com.xinyuan.xyshop.bean.OrderGoodBean;
import com.xinyuan.xyshop.bean.OrderServiceStoreInfoBean;
import com.xinyuan.xyshop.bean.ReFundServiceDetailBean;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.model.ServiceGoodDetailModel;
import com.xinyuan.xyshop.mvp.contract.ServiceDetailView;
import com.xinyuan.xyshop.mvp.presenter.ServiceDetailPresenter;
import com.xinyuan.xyshop.ui.mine.msg.ChattingDetailActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.dialog.color.ColorDialog;
import com.youth.xframe.widget.XToast;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/6.
 * 退款详情
 */

public class ServiceMoneyDetailFragment extends BaseFragment<ServiceDetailPresenter> implements ServiceDetailView {


	@BindView(R.id.toolbar_iv)
	Toolbar toolbar_iv;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;
	@BindView(R.id.iv_header_right)
	ImageView iv_header_right;


	@BindView(R.id.tv_service_status)
	TextView tv_service_status;         //退款状态
	@BindView(R.id.tv_status_time)
	TextView tv_status_time;            //售后时间（时间点，倒计时）

	@BindView(R.id.bt_order_cancel)
	Button bt_order_cancel;             //撤销申请
	@BindView(R.id.bt_order_change)
	Button bt_order_change;             //重新申请
	@BindView(R.id.tv_service_notice)
	TextView tv_service_notice;         //退款提示文字

	@BindView(R.id.ll_notice_1)
	RelativeLayout ll_notice_1;       //退款提示布局第一行
	@BindView(R.id.tv_notice_1)
	TextView tv_notice_1;           //退款提示布局第一行文字

	@BindView(R.id.ll_notice_2)
	LinearLayout ll_notice_2;       //退款提示布局第二行
	@BindView(R.id.tv_notice_2)
	TextView tv_notice_2;           //退款提示布局第二行文字
	@BindView(R.id.ll_service_button)
	LinearLayout ll_service_button;     //操作按钮布局

	@BindView(R.id.iv_good_img)
	ImageView iv_good_img;
	@BindView(R.id.tv_goods_name)
	TextView tv_goods_name;
	@BindView(R.id.tv_good_spec)
	TextView tv_good_spec;
	@BindView(R.id.tv_service_reason)
	TextView tv_service_reason;
	@BindView(R.id.tv_service_money)
	TextView tv_service_money;
	@BindView(R.id.tv_service_time)
	TextView tv_service_time;
	@BindView(R.id.tv_service_id)
	TextView tv_service_id;

	@BindView(R.id.loadingView)
	XLoadingView loadingView;
	private OrderServiceStoreInfoBean storeInfoBean;
	private long orderId;
	private String phone;

	public static ServiceMoneyDetailFragment newInstance(OrderServiceStoreInfoBean bean) {
		ServiceMoneyDetailFragment fragment = new ServiceMoneyDetailFragment();
		fragment.storeInfoBean = bean;
		return fragment;
	}

	@Override
	protected ServiceDetailPresenter createPresenter() {
		return new ServiceDetailPresenter(getActivity(), this);
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_service_money_detail;
	}


	@Override
	public void initView(View rootView) {
		SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(mContext, toolbar_iv);
		CommUtil.initToolBar(getActivity(), iv_header_left, iv_header_right);
	}


	@OnClick({R.id.bt_order_cancel, R.id.bt_order_change, R.id.bt_service_contatct, R.id.bt_service_phone})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.bt_order_cancel:
				final ColorDialog colorDialog = new ColorDialog(getActivity());
				colorDialog.setTitle("撤销退货");
				colorDialog.setContentText("确认撤销售后？");
				colorDialog.setPositiveListener("确认", new ColorDialog.OnPositiveListener() {
					@Override
					public void onClick(ColorDialog dialog) {
						mPresenter.cancelService("refund", storeInfoBean.getServiceId());
						colorDialog.dismiss();
					}
				})
						.setNegativeListener("取消", new ColorDialog.OnNegativeListener() {
							@Override
							public void onClick(ColorDialog dialog) {
								colorDialog.dismiss();
							}
						}).show();
				break;
			case R.id.bt_order_change:
				start(ServiceReasonFragment.newInstance(1, orderBean, orderId, storeInfoBean));

				break;
			case R.id.bt_service_contatct:
				Map<String, String> params = new HashMap();
				params.put("USERNAME", storeInfoBean.getStoreUserName());
				params.put("USERHEAD", storeInfoBean.getStoreUserImg());
				params.put("USERID", String.valueOf(storeInfoBean.getStoreUserId()));
				params.put("STOREID", String.valueOf(storeInfoBean.getStoreId()));
				CommUtil.gotoActivity(getActivity(), ChattingDetailActivity.class, false, params);
				break;
			case R.id.bt_service_phone:
				Intent intent = new Intent(Intent.ACTION_DIAL);
				Uri data = Uri.parse("tel:" + phone);
				intent.setData(data);
				startActivity(intent);
				break;
		}


	}


	public void onSupportVisible() { //当fragment可见时，刷新内容
		mPresenter.getMoneyDetail(storeInfoBean.getServiceId());
		super.onSupportVisible();
	}

	@Override
	public void initData() {

	}

	@Override
	public void showMoneyInfo(ReFundServiceDetailBean bean) {
		tv_header_center.setText("退款详情");
		GlideImageLoader.setUrlImg(mContext, bean.getGoodsImg(), iv_good_img);
		tv_goods_name.setText(bean.getGoodsName());
		tv_good_spec.setText("已选" + bean.getSpec_info());
		tv_service_reason.setText(bean.getGoodsRefundResons());
		tv_service_money.setText(mContext.getString(R.string.money_rmb) + bean.getGoodsRefundMoney());
		tv_service_time.setText(bean.getGoodsRefundCreate());
		tv_service_id.setText(bean.getGoodsRefundNumber());
		orderId = bean.getOrderId();
		phone = bean.getStoreUserPhone();
		tv_status_time.setText(bean.getHandlingTime() == null ? "" : bean.getHandlingTime());

		switch (bean.getGoodsRefundStatus()) {
			case "1":                     //1：发起退款
				tv_service_status.setText("等待商家处理");
				tv_service_notice.setText("您已成功发起退款申请，请耐心等待商家处理");
				ll_notice_1.setVisibility(View.VISIBLE);
				ll_notice_2.setVisibility(View.VISIBLE);
				ll_service_button.setVisibility(View.VISIBLE);
				tv_notice_1.setText("商家同意或超时未处理，系统将退款给您");
				tv_notice_2.setText("如果商家拒绝，您可以修改退款申请后再次发起，商家会重新处理");
				break;
			case "2":                     //2：商家同意退款
				tv_service_status.setText("商家已同意你的申请");
				tv_service_notice.setText("平台将于3个工作日内将货款退至您的账户，请注意查收");

				break;
			case "3":                     //3：商家拒绝退款
				tv_service_status.setText("商家已拒绝你的申请");
				tv_service_notice.setText("商家已拒绝您的退款申请");
				ll_notice_1.setVisibility(View.VISIBLE);
				ll_service_button.setVisibility(View.VISIBLE);
				tv_notice_1.setText("您可以修改退款申请后再次发起，商家会重新处理");

				break;
			case "0":                     //4：撤销申请
				tv_service_status.setText("退款关闭");
				tv_service_notice.setText("您已撤销本次申请，如问题未解决，售后保质期内，您可以重新发起售后申请");

				break;


		}
		orderBean = new OrderGoodBean();
		orderBean.setGoods_name(bean.getGoodsName());
		orderBean.setGoodsImg(bean.getGoodsImg());
		orderBean.setGoodsCart_id(bean.getGoodsCartId());
		orderBean.setSpec_info(bean.getSpec_info());

		loadingView.showContent();
	}

	private OrderGoodBean orderBean;

	@Override
	public void showGoodInfo(ServiceGoodDetailModel model) {

	}

	@Override
	public void cancelBack(boolean status) {
		if (status) {
			XToast.info("撤销成功！");
			_mActivity.onBackPressed();
		} else {
			XToast.error("撤销错误,请稍后重试");
		}
	}
}
