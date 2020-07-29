package com.xinyuan.xyshop.ui.mine.order;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.bean.OrderBean;
import com.xinyuan.xyshop.bean.OrderGoodBean;
import com.xinyuan.xyshop.bean.OrderServiceStoreInfoBean;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.common.ShopHelper;
import com.xinyuan.xyshop.even.OrderPageRefresh;
import com.xinyuan.xyshop.mvp.contract.OrderDetailView;
import com.xinyuan.xyshop.mvp.presenter.OrderDetailPresenter;
import com.xinyuan.xyshop.ui.buy.PayFragment;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailActivity;
import com.xinyuan.xyshop.ui.goods.store.StoreActivity;
import com.xinyuan.xyshop.ui.mine.msg.ChattingDetailActivity;
import com.xinyuan.xyshop.ui.mine.order.fragment.LogisticFragment;
import com.xinyuan.xyshop.ui.mine.order.service.ServiceGoodsFragment;
import com.xinyuan.xyshop.ui.mine.order.service.ServiceMoneyDetailFragment;
import com.xinyuan.xyshop.ui.mine.order.service.ServiceReasonFragment;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.dialog.color.ColorDialog;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;
import com.youth.xframe.widget.loadingview.XLoadingView;

import org.greenrobot.eventbus.EventBus;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/20.
 * 订单详情activity
 */

public class OrderDetailFragment extends BaseFragment<OrderDetailPresenter> implements OrderDetailView {
    @BindView(R.id.loadingView)
    XLoadingView loadingView;
    @BindView(R.id.tv_order_status)
    TextView tv_order_status;
    @BindView(R.id.tv_order_closetime)
    TextView tv_order_closetime;
    @BindView(R.id.tv_order_receiver)
    TextView tv_order_receiver;
    @BindView(R.id.tv_order_phone)
    TextView tv_order_phone;
    @BindView(R.id.tv_order_address)
    TextView tv_order_address;
    @BindView(R.id.fl_goods)
    FlexboxLayout fl_goods;
    @BindView(R.id.tv_order_good_price)
    TextView tv_order_good_price;
    @BindView(R.id.tv_order_good_send)
    TextView tv_order_good_send;
    @BindView(R.id.tv_coupon_price)
    TextView tv_coupon_price;
    @BindView(R.id.tv_order_orderId)
    TextView tv_order_orderId;

    @BindView(R.id.tv_order_price_total)
    TextView tv_order_price_total;
    @BindView(R.id.tv_order_createTime)
    TextView tv_order_createTime;
    @BindView(R.id.bt_order_item_store)
    Button bt_order_item_store;

    @BindView(R.id.bt_order_red)
    Button bt_order_red;

    @BindView(R.id.bt_order_2)
    Button bt_order_2;
    @BindView(R.id.bt_order_service)
    Button bt_order_service;
    @BindView(R.id.bt_order_1)
    Button bt_order_1;
    @BindView(R.id.ll_order_receiver)
    RelativeLayout ll_order_receiver;
    @BindView(R.id.rl_order_detail_bottom)
    LinearLayout rl_order_detail_bottom;
    @BindView(R.id.ll_order_code)
    RelativeLayout ll_order_code;
    @BindView(R.id.tv_order_code)
    TextView tv_order_code;
    @BindView(R.id.tv_order_code_time)
    TextView tv_order_code_time;
    @BindView(R.id.tv_order_good_send_t)
    TextView tv_order_good_send_t;
    @BindView(R.id.toolbar_iv)
    Toolbar toolbar_iv;
    @BindView(R.id.tv_header_center)
    TextView tv_header_center;
    @BindView(R.id.iv_header_left)
    ImageView iv_header_left;
    @BindView(R.id.iv_header_right)
    ImageView iv_header_right;

    private BigDecimal goodPrice = new BigDecimal(0);
    private long orderId;
    private String userName;
    private String userHeadImg;
    private String userId;
    private String storeId;
    private String phone;
    private OrderBean orderBean;
    private int orderType;
    private String mainGoodImg;

    public static OrderDetailFragment getInstance(long orderId, int orderType) {
        OrderDetailFragment sf = new OrderDetailFragment();
        sf.orderId = orderId;
        sf.orderType = orderType;
        return sf;
    }

    @Override
    protected OrderDetailPresenter createPresenter() {
        return new OrderDetailPresenter(getActivity(), this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void initView(View rootView) {
        SystemBarHelper.immersiveStatusBar(getActivity()); //设置状态栏透明
        SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_iv);
        CommUtil.initToolBar(getActivity(), iv_header_left, iv_header_right);
        tv_header_center.setText("订单详情");

    }


    @Override
    public void initData() {
    }

    @Override
    public void onSupportVisible() { //当fragment可见时，刷新内容
        if (orderType == 1) {
            mPresenter.getOrderDetail(orderId);
        } else {
            mPresenter.getOnOrderDetail(orderId);
        }
        super.onSupportVisible();
    }

    @Override
    public void showOrderDetail(OrderBean orderBean) {
        if (!XEmptyUtils.isEmpty(orderBean)) {    //非空判断
            this.orderBean = orderBean;
            if (orderType == 1) {
                ll_order_receiver.setVisibility(View.VISIBLE);
                /**顶部收货人信息*/
                tv_order_receiver.setText("收货人：" + orderBean.getReceiver());
                tv_order_address.setText(orderBean.getAddress());
                tv_order_phone.setText(orderBean.getReceiverPhone());
                phone = orderBean.getStorePhone();
                if (!orderBean.getCountdown().equals("")) {
                    tv_order_closetime.setVisibility(View.VISIBLE);
                    tv_order_closetime.setText("剩余" + orderBean.getCountdown() + "自动关闭");
                }
            }

            /**订单信息*/
            userName = orderBean.getStoreUserName();
            userId = String.valueOf(orderBean.getStoreUserId());
            userHeadImg = orderBean.getStoreUserImg();

            tv_order_good_price.setText(getString(R.string.money_rmb) + ShopHelper.getPriceString(orderBean.getGoodsPrice()));
            if (orderType == 1) {
                tv_order_good_send.setText(getString(R.string.money_rmb) + ShopHelper.getPriceString(orderBean.getShipPrice()));
            } else {
                tv_order_good_send_t.setVisibility(View.GONE);
                tv_order_good_send.setVisibility(View.GONE);
            }

            tv_coupon_price.setText("- " + getString(R.string.money_rmb) + (ShopHelper.getPriceString(orderBean.getCouponsPrice())));
            tv_order_price_total.setText(getString(R.string.money_rmb) + (ShopHelper.getPriceString(orderBean.getOrderPrice())));
            tv_order_orderId.setText("订单编号:" + orderBean.getOrderNumber());
            tv_order_createTime.setText("创建时间:" + orderBean.getCreateTime());


            /**商品及商家信息*/
            bt_order_item_store.setText(orderBean.getStoreName());
            storeId = String.valueOf(orderBean.getStoreId());
            bt_order_item_store.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Map<String, String> params = new HashMap();
                    params.put("STOREID", String.valueOf(orderBean.getStoreId()));
                    CommUtil.gotoActivity(getActivity(), StoreActivity.class, false, params);
                }
            });

            /**订单状态*/
            switch (orderBean.getOrderStatus()) {
                case "1":  //待付款状态
                    tv_order_status.setText("等待买家付款");
                    bt_order_1.setVisibility(View.GONE);
                    bt_order_2.setText("取消订单");
                    bt_order_red.setText("付款");
                    bt_order_2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ToOrderCancel();
                        }
                    });
                    bt_order_red.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            start(PayFragment.newInstance(orderBean.getOrderNumber(), orderBean.getOrderPrice()));
                        }
                    });
                    break;
                case "2"://待发货状态    待使用
                    if (orderType == 1) {
                        tv_order_status.setText("等待卖家发货");
                        rl_order_detail_bottom.setVisibility(View.GONE);
                    } else {
                        ll_order_code.setVisibility(View.VISIBLE);
                        tv_order_code.setText(orderBean.getRedemption_code());
                        tv_order_code_time.setText(orderBean.getValid_period_time());
                        tv_order_status.setText("等待买家使用");
                        rl_order_detail_bottom.setVisibility(View.GONE);
                    }

                    break;
                case "3"://待收货状态     已使用
                    if (orderType == 1) {
                        bt_order_1.setVisibility(View.GONE);
                        tv_order_status.setText("卖家已发货");
                        bt_order_2.setText("查看物流");
                        bt_order_red.setText("确认收货");
                        bt_order_2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ToLogistic();
                            }
                        });
                        bt_order_red.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ToReceivingOrder();
                            }
                        });
                    } else {
                        tv_order_status.setText("等待评价");
                        bt_order_red.setVisibility(View.GONE);
                        bt_order_1.setVisibility(View.GONE);
                        bt_order_2.setText("查看物流");
                        bt_order_2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ToLogistic();

                            }
                        });
                    }

                    break;
                case "4"://待评价状态       已过期
                    if (orderType == 1) {
                        tv_order_status.setText("等待评价");
                        bt_order_red.setVisibility(View.GONE);
                        bt_order_1.setVisibility(View.GONE);
                        bt_order_2.setText("查看物流");
                        bt_order_2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ToLogistic();

                            }
                        });
                    } else {
                        tv_order_status.setText("该订单已过期");
                        rl_order_detail_bottom.setVisibility(View.GONE);
                    }

                    break;
                case "5":
                    if (orderType == 1) {
                        tv_order_status.setText("申请售后中");
                        bt_order_red.setVisibility(View.GONE);
                        bt_order_1.setVisibility(View.GONE);
                        bt_order_2.setText("查看物流");
                        bt_order_2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ToLogistic();

                            }
                        });
                    } else {
                        tv_order_status.setText("交易成功");
                        rl_order_detail_bottom.setVisibility(View.GONE);

                    }


                    break;
                case "9"://交易成功状态
                    tv_order_status.setText("订单交易成功");
                    bt_order_red.setVisibility(View.GONE);
                    bt_order_1.setVisibility(View.GONE);
                    bt_order_2.setText("查看物流");
                    bt_order_2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ToLogistic();
                        }
                    });
                    break;
                case "10"://交易完成状态
                    tv_order_status.setText("订单交易完成");
                    bt_order_red.setVisibility(View.VISIBLE);
                    bt_order_red.setText("删除订单");
                    bt_order_1.setVisibility(View.GONE);

                    if (orderType == 1) {
                        bt_order_2.setText("查看物流");
                        bt_order_2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ToLogistic();
                            }
                        });
                    }
                    bt_order_red.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ToDetele();
                        }
                    });

                    break;
                case "0"://交易关闭状态
                    tv_order_status.setText("交易关闭");
                    bt_order_red.setText("删除订单");
                    bt_order_1.setVisibility(View.GONE);
                    bt_order_2.setVisibility(View.GONE);
                    bt_order_red.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ToDetele();
                        }
                    });
                    break;
            }
            OrderServiceStoreInfoBean storeInfoBean = new OrderServiceStoreInfoBean();
            storeInfoBean.setStoreUserName(orderBean.getStoreUserName());
            storeInfoBean.setStoreUserImg(orderBean.getStoreUserImg());
            storeInfoBean.setStoreUserId(orderBean.getStoreUserId());
            storeInfoBean.setStoreId(orderBean.getStoreId());
            mainGoodImg = orderBean.getGoodsList().get(0).getGoodsImg();
            /**商品信息*/
            fl_goods.removeAllViews();
            for (final OrderGoodBean good : orderBean.getGoodsList()) {
                AddViewHolder addViewHolder = new AddViewHolder(mContext, R.layout.fragment_order_item_good);

                ImageView iv_good_img = addViewHolder.getView(R.id.iv_good_img);
                GlideImageLoader.setUrlImg(mContext, good.getGoodsImg(), iv_good_img);
                TextView tv_good_name = addViewHolder.getView(R.id.tv_good_name);
                tv_good_name.setText(good.getGoods_name());
                TextView tv_good_spec = addViewHolder.getView(R.id.tv_good_spec);
                tv_good_spec.setText("已选:" + good.getSpec_info());
                TextView tv_good_price = addViewHolder.getView(R.id.tv_good_price);
                tv_good_price.setText("￥" + good.getStore_price());
                TextView tv_good_old_price = addViewHolder.getView(R.id.tv_good_old_price);
                tv_good_old_price.setText("￥" + good.getGoods_price());
                tv_good_price.setText("￥" + good.getStore_price());
                tv_good_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                TextView tv_good_num = addViewHolder.getView(R.id.tv_good_num);
                tv_good_num.setText("X" + good.getCount());
                goodPrice = goodPrice.add(good.getStore_price());
                iv_good_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        HashMap<String, String> params;
                        params = new HashMap();
                        params.put(Constants.GOODID, String.valueOf(good.getGoods_id()));
                        params.put(Constants.GOODTYPE, "1");
                        CommUtil.gotoActivity(getActivity(), GoodDetailActivity.class, false, params);
                    }
                });

                Button bt_order_good_refund = addViewHolder.getView(R.id.bt_order_good_refund); //显示退款
                Button bt_order_good_eva = addViewHolder.getView(R.id.bt_order_good_eva);   //显示评价
                bt_order_good_eva.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (good.getEvaluateMark().equals("1")) {
                            start(OrderEveFragment.getInstance(good.getGoodsImg(), orderType, orderId, good.getGoodsCart_id(), 1));    //追评
                        } else {
                            start(OrderEveFragment.getInstance(good.getGoodsImg(), orderType, orderId, good.getGoodsCart_id(), 0));    //评价
                        }
                    }
                });


                /**商品列表里按钮*/
                switch (orderBean.getOrderStatus()) {
                    case "1":   //待付款--无按钮
                        break;
                    case "2":   //待发货--可退款      //待使用
                        if (orderType == 1) {
                            bt_order_good_refund.setVisibility(View.VISIBLE);
                            storeInfoBean.setServiceId(good.getRefundId());

                            if (good.getRefundMark().equals("-1") || good.getRefundMark().equals("0")) {
                                bt_order_good_refund.setText("退款");
                                bt_order_good_refund.setOnClickListener(new ToMoneyServiceListenter(orderBean.getGoodsList().indexOf(good), storeInfoBean));
                            } else if (good.getRefundMark().equals("10")) {
                                bt_order_good_refund.setText("售后完成");
                                bt_order_good_refund.setOnClickListener(new ToMoneyServiceDetailListenter(storeInfoBean));
                            } else {
                                bt_order_good_refund.setText("售后中");
                                bt_order_good_refund.setOnClickListener(new ToMoneyServiceDetailListenter(storeInfoBean));
                            }
                        }

                        break;
                    case "3":   //待收货--无按钮      已使用待评价
                        if (orderType == 2) {
                            bt_order_good_eva.setVisibility(View.VISIBLE);
                            if (good.getEvaluateMark().equals("0")) {   //已评价
                                bt_order_good_eva.setText("评价");
                            } else if (good.getEvaluateMark().equals("1")) {
                                bt_order_good_eva.setText("追评");
                            } else if (good.getEvaluateMark().equals("2")) {
                                bt_order_good_eva.setVisibility(View.GONE);
                            }
                        }
                        break;
                    case "4":   //待评价--申请售后，评价/追评
                        if (orderType == 1) {
                            bt_order_good_eva.setVisibility(View.VISIBLE);
                            bt_order_good_refund.setVisibility(View.VISIBLE);
                            storeInfoBean.setServiceId(good.getReturnId());
                            if (good.getReturnMark().equals("-1") || good.getReturnMark().equals("0")) {
                                bt_order_good_refund.setText("申请售后");
                                bt_order_good_refund.setOnClickListener(new ToGoodServiceListenter(orderBean.getGoodsList().indexOf(good), storeInfoBean));
                            } else if (good.getReturnMark().equals("10")) {
                                bt_order_good_refund.setText("售后完成");
                                bt_order_good_refund.setOnClickListener(new ToGoodServiceDetailListenter(storeInfoBean));
                            } else {
                                bt_order_good_refund.setText("售后中");
                                bt_order_good_refund.setOnClickListener(new ToGoodServiceDetailListenter(storeInfoBean));
                            }
                            if (good.getEvaluateMark().equals("0")) {   //已评价
                                bt_order_good_eva.setText("评价");
                            } else if (good.getEvaluateMark().equals("1")) {
                                bt_order_good_eva.setText("追评");
                            } else if (good.getEvaluateMark().equals("2")) {
                                bt_order_good_eva.setVisibility(View.GONE);
                            }
                        }


                        break;
                    case "5":   //售后中       交易完成
                        if (orderType == 1) {
                            bt_order_good_refund.setVisibility(View.VISIBLE);
                            bt_order_good_refund.setText("售后中");
                            if (good.getRefundId() == 0) {
                                storeInfoBean.setServiceId(good.getReturnId());
                                /**退货*/
                                if (good.getReturnMark().equals("10")) {
                                    bt_order_good_refund.setText("售后完成");
                                    bt_order_good_refund.setOnClickListener(new ToGoodServiceDetailListenter(storeInfoBean));

                                } else if (good.getReturnMark().equals("0")) {
                                    bt_order_good_refund.setText("重新申请");
                                    bt_order_good_refund.setOnClickListener(new ToGoodServiceListenter(orderBean.getGoodsList().indexOf(good), storeInfoBean));

                                } else {
                                    bt_order_good_refund.setText("售后中");
                                    bt_order_good_refund.setOnClickListener(new ToGoodServiceDetailListenter(storeInfoBean));

                                }

                            } else {
                                /**退款*/
                                storeInfoBean.setServiceId(good.getRefundId());
                                if (good.getRefundMark().equals("10")) {
                                    bt_order_good_refund.setText("售后完成");
                                    bt_order_good_refund.setOnClickListener(new ToMoneyServiceDetailListenter(storeInfoBean));

                                } else if (good.getRefundMark().equals("0")) {
                                    bt_order_good_refund.setText("重新申请");
                                    bt_order_good_refund.setOnClickListener(new ToMoneyServiceListenter(orderBean.getGoodsList().indexOf(good), storeInfoBean));

                                } else {
                                    bt_order_good_refund.setText("售后中");
                                    bt_order_good_refund.setOnClickListener(new ToMoneyServiceDetailListenter(storeInfoBean));

                                }

                            }
                        } else {
                            bt_order_good_eva.setVisibility(View.VISIBLE);
                            if (good.getEvaluateMark().equals("0")) {   //已评价
                                bt_order_good_eva.setText("评价");
                            } else if (good.getEvaluateMark().equals("1")) {
                                bt_order_good_eva.setText("追评");
                            } else if (good.getEvaluateMark().equals("2")) {
                                bt_order_good_eva.setVisibility(View.GONE);
                            }
                        }


                        break;
                    case "9":   //交易成功--申请售后，追评
                        bt_order_good_eva.setVisibility(View.VISIBLE);
                        bt_order_good_refund.setVisibility(View.VISIBLE);
                        storeInfoBean.setServiceId(good.getReturnId());
                        if (good.getReturnMark().equals("-1")) {
                            bt_order_good_refund.setText("申请售后");
                            bt_order_good_refund.setOnClickListener(new ToGoodServiceListenter(orderBean.getGoodsList().indexOf(good), storeInfoBean));
                        } else if (good.getReturnMark().equals("10")) {
                            bt_order_good_refund.setText("售后完成");
                            bt_order_good_refund.setOnClickListener(new ToGoodServiceDetailListenter(storeInfoBean));
                        } else if (good.getReturnMark().equals("0")) {
                            bt_order_good_refund.setText("申请售后");
                            bt_order_good_refund.setOnClickListener(new ToGoodServiceListenter(orderBean.getGoodsList().indexOf(good), storeInfoBean));
                        } else {
                            bt_order_good_refund.setText("售后中");
                            bt_order_good_refund.setOnClickListener(new ToGoodServiceDetailListenter(storeInfoBean));
                        }

                        if (good.getEvaluateMark().equals("0")) {   //已评价
                            bt_order_good_eva.setText("评价");
                        } else if (good.getEvaluateMark().equals("1")) {
                            bt_order_good_eva.setText("追评");
                        } else if (good.getEvaluateMark().equals("2")) {
                            bt_order_good_eva.setVisibility(View.GONE);
                        }

                        break;
                    case "10":  //订单完成--无按钮
                        break;
                    case "0":   //关闭状态--无按钮
                        break;


                }
                fl_goods.addView(addViewHolder.getCustomView());
            }


            loadingView.showContent();
        }


    }


    @OnClick({R.id.bt_order_service, R.id.bt_order_phone})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_order_service:
                Map<String, String> params = new HashMap();
                params.put(Constants.USERNAME, userName);
                params.put(Constants.USERHEAD, userHeadImg);
                params.put(Constants.USERID, userId);
                params.put(Constants.STOREID, storeId);
                CommUtil.gotoActivity(getActivity(), ChattingDetailActivity.class, false, params);

                break;
            case R.id.bt_order_phone:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + phone);
                intent.setData(data);
                startActivity(intent);
                break;

        }

    }

    private void ToLogistic() {
        start(LogisticFragment.getInstance(mainGoodImg, String.valueOf(orderId)));

    }

    /**
     * 申请退款监听
     */
    class ToMoneyServiceListenter implements View.OnClickListener {
        private OrderServiceStoreInfoBean storeInfoBean;
        private int position;

        public ToMoneyServiceListenter(int position, OrderServiceStoreInfoBean storeInfoBean) {
            this.position = position;
            this.storeInfoBean = storeInfoBean;
        }

        @Override
        public void onClick(View view) {
            start(ServiceReasonFragment.newInstance(1, orderBean.getGoodsList().get(position), orderId, storeInfoBean));
        }
    }

    /**
     * 申请退货监听
     */
    class ToGoodServiceListenter implements View.OnClickListener {
        private OrderServiceStoreInfoBean storeInfoBean;
        private int position;


        public ToGoodServiceListenter(int position, OrderServiceStoreInfoBean storeInfoBean) {
            this.position = position;
            this.storeInfoBean = storeInfoBean;
        }

        @Override
        public void onClick(View view) {
            start(ServiceReasonFragment.newInstance(2, orderBean.getGoodsList().get(position), orderId, storeInfoBean));
        }
    }

    /**
     * 退货详情监听
     */
    class ToGoodServiceDetailListenter implements View.OnClickListener {
        private OrderServiceStoreInfoBean storeInfoBean;

        public ToGoodServiceDetailListenter(OrderServiceStoreInfoBean storeInfoBean) {
            this.storeInfoBean = storeInfoBean;
        }

        @Override
        public void onClick(View view) {
            start(ServiceGoodsFragment.newInstance(storeInfoBean));
        }
    }

    /**
     * 退款详情监听
     */
    class ToMoneyServiceDetailListenter implements View.OnClickListener {
        private OrderServiceStoreInfoBean storeInfoBean;

        public ToMoneyServiceDetailListenter(OrderServiceStoreInfoBean storeInfoBean) {
            this.storeInfoBean = storeInfoBean;
        }

        @Override
        public void onClick(View view) {
            start(ServiceMoneyDetailFragment.newInstance(storeInfoBean));
        }

    }


    /**
     * 确认收货对话框
     *
     * @param
     */
    private void ToReceivingOrder() {
        final ColorDialog colorDialog = new ColorDialog(mContext);
        //colorDialog.setTitle("确认收货？");
        colorDialog.setContentText("请您收到货后再点击确定，否则可能钱货两空！");
        colorDialog.setPositiveListener("确定", new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {
                mPresenter.ReceivingOrder(orderId);
                colorDialog.dismiss();


            }
        })
                .setNegativeListener("取消", new ColorDialog.OnNegativeListener() {
                    @Override
                    public void onClick(ColorDialog dialog) {
                        colorDialog.dismiss();
                    }
                }).show();
    }


    /**
     * 取消订单对话框
     *
     * @param
     */
    private void ToOrderCancel() {
        final ColorDialog colorDialog = new ColorDialog(mContext);
        //colorDialog.setTitle("确认收货？");
        colorDialog.setContentText("确定要取消订单吗？");
        colorDialog.setPositiveListener("确定", new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {
                mPresenter.OrderCancel(orderId);
                colorDialog.dismiss();


            }
        })
                .setNegativeListener("取消", new ColorDialog.OnNegativeListener() {
                    @Override
                    public void onClick(ColorDialog dialog) {
                        colorDialog.dismiss();
                    }
                }).show();
    }


    /**
     * 删除订单对话框
     *
     * @param
     */
    private void ToDetele() {
        final ColorDialog colorDialog = new ColorDialog(mContext);
        //colorDialog.setTitle("确认收货？");
        colorDialog.setContentText(mContext.getString(R.string.order_notice_detele));
        colorDialog.setPositiveListener(mContext.getString(R.string.bt_ok), new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {
                mPresenter.OrderDetele(orderId);
                colorDialog.dismiss();


            }
        })
                .setNegativeListener(mContext.getString(R.string.bt_cancel), new ColorDialog.OnNegativeListener() {
                    @Override
                    public void onClick(ColorDialog dialog) {
                        colorDialog.dismiss();
                    }
                }).show();
    }


    @Override
    public void receivingCallBack(boolean result) {
        if (result) {
            XToast.info("确认收货成功");
            EventBus.getDefault().postSticky(new OrderPageRefresh(true));


        } else {
            XToast.error("确认收货出错，请稍后再试");
        }

    }

    @Override
    public void cancelCallBack(boolean result) {
        if (result) {
            XToast.info("已取消订单");
            EventBus.getDefault().postSticky(new OrderPageRefresh(true));
        } else {
            XToast.error("取消订单出错，请稍后再试");

        }
    }

    @Override
    public void deteleCallBack(boolean result) {
        if (result) {
            XToast.info("已删除订单");
            _mActivity.onBackPressed();
            EventBus.getDefault().postSticky(new OrderPageRefresh(true));
        } else {
            XToast.error("删除出错，请稍后再试");

        }
    }
}
