package com.xinyuan.xyshop.ui.buy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.AliPayResultBean;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.callback.DialogCallback;
import com.xinyuan.xyshop.callback.PayResult;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.ui.mine.order.OrderActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.XToast;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/8.
 */

public class PayResultFragment extends BaseFragment {
    @BindView(R.id.toolbar_tv)
    Toolbar toolbar_tv;
    @BindView(R.id.tv_header_center)
    TextView tv_header_center;
    @BindView(R.id.iv_header_left)
    ImageView iv_header_left;
    @BindView(R.id.ll_pay_success)
    LinearLayout ll_pay_success;
    @BindView(R.id.ll_pay_failed)
    LinearLayout ll_pay_failed;

    private int payType;
    private String orderNumber;

    public static PayResultFragment newInstance(int payType, String orderNumber) {
        PayResultFragment fragment = new PayResultFragment();
        fragment.payType = payType;
        fragment.orderNumber = orderNumber;
        return fragment;
    }

    @Override
    public void initView(View rootView) {
        SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
        SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_tv);
        tv_header_center.setText("支付订单");
        CommUtil.initToolBar(getActivity(), iv_header_left, null);
    }

    @Override
    public void initData() {
        switch (payType) {
            case Constants.ALIPAY:
                getOrderInfo();
                break;
            case Constants.WECHATPAY:
                break;
            case Constants.YINLANPAY:
                break;
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_pay_result;
    }

    /**
     * 获取订单签名信息
     */
    private void getOrderInfo() {
        OkGo.<LzyResponse<String>>post(Urls.URL_USER_ORDER_ALIPAY)
                .tag(this)
                .params("userId", MyShopApplication.userId)
                .params("orderNumber", orderNumber)
                .execute(new DialogCallback<LzyResponse<String>>(getActivity(), "请求支付中...") {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<String>> response) {
                        if (HttpUtil.handleResponse(getActivity(), response.body())) {
                            orderInfo = response.body().getDatas();
                            aliPay();
                        }
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<LzyResponse<String>> response) {
                        HttpUtil.handleError(getActivity(), response);
                    }
                });
    }

    private String orderInfo;

    /**
     * 调用支付宝支付
     */
    private void aliPay() {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(getActivity());
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 接受支付宝回调
     */
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            @SuppressWarnings("unchecked")
            PayResult payResult = new PayResult((Map<String, String>) msg.obj);
            /**
             对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
             */
            String resultInfo = payResult.getResult();// 同步返回需要验证的信息
            String resultStatus = payResult.getResultStatus();
            // 判断resultStatus 为9000则代表支付成功
            if (TextUtils.equals(resultStatus, "9000")) {
                // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                postAliPay(payResult);

            } else {
                // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                if (!XEmptyUtils.isEmpty(ll_pay_success)) {
                    ll_pay_success.setVisibility(View.GONE);
                    ll_pay_failed.setVisibility(View.VISIBLE);
                }

                XToast.error("支付失败");
            }
        }


    };

    /**
     * 通知服务器接收通知
     *
     * @param payResult
     */
    private void postAliPay(PayResult payResult) {
        Gson gson = new Gson();
        AliPayResultBean aliPayResultBean = gson.fromJson(payResult.getResult(), AliPayResultBean.class);
        AliPayResultBean.AliPayResBean bean = aliPayResultBean.getAlipay_trade_app_pay_response();
//		OkGo.<String>post(Urls.URL_USER_ORDER_ALIPAY_NOTIFY)
//				.tag(this)
//				.params("app_id", bean.getApp_id())
//				.params("charset", bean.getCharset())
//				.params("code", bean.getCode())
//				.params("msg", bean.getMsg())
//				.params("out_trade_no", bean.getOut_trade_no())
//				.params("seller_id", bean.getSeller_id())
//				.params("sign", aliPayResultBean.getSign())
//				.params("sign_type", aliPayResultBean.getSign_type())
//				.params("timestamp", bean.getTimestamp())
//				.params("total_amount", bean.getTotal_amount())
//				.params("trade_no", bean.getTrade_no())
//				.execute(new DialogCallback<String>(getActivity(), "支付中...") {
//					@Override
//					public void onSuccess(com.lzy.okgo.model.Response<String> response) {
//						XLog.v("支付结果" + response.body());
//						ll_pay_success.setVisibility(View.VISIBLE);
//						ll_pay_failed.setVisibility(View.GONE);
////						CommUtil.gotoActivity(getActivity(), MainActivity.class, true, null);
////						XToast.info("支付成功");
//
//					}
//
//					@Override
//					public void onError(com.lzy.okgo.model.Response<String> response) {
//						HttpUtil.handleError(getActivity(), response);
//						ll_pay_success.setVisibility(View.GONE);
//						ll_pay_failed.setVisibility(View.VISIBLE);
//					}
//				});
        OkGo.<TokenBean>post(Urls.URL_USER_ORDER_ALIPAY_NOTIFY)
                .tag(this)
                .params("orderNumber", orderNumber)
                .params("trade_no", bean.getTrade_no())
                .execute(new DialogCallback<TokenBean>(getActivity(), "支付中...") {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<TokenBean> response) {
                        ll_pay_success.setVisibility(View.VISIBLE);
                        ll_pay_failed.setVisibility(View.GONE);
//						CommUtil.gotoActivity(getActivity(), MainActivity.class, true, null);
//						XToast.info("支付成功");

                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<TokenBean> response) {
                        HttpUtil.handleError(getActivity(), response);
                        ll_pay_success.setVisibility(View.GONE);
                        ll_pay_failed.setVisibility(View.VISIBLE);
                    }
                });
    }

    @OnClick({R.id.bt_pay_showorder, R.id.bt_pay_rest, R.id.bt_pay_order})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_pay_showorder:
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ORDER_INDEX", 0);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.bt_pay_rest:
                aliPay();
                break;
            case R.id.bt_pay_order:
                Intent intent2 = new Intent(getActivity(), OrderActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putSerializable("ORDER_INDEX", 0);
                intent2.putExtras(bundle2);
                startActivity(intent2);
                break;
        }

    }
}
