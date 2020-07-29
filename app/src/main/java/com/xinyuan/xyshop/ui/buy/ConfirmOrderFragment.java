package com.xinyuan.xyshop.ui.buy;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayout;
import com.google.gson.JsonArray;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.OrderConfirmAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.bean.AddressBean;
import com.xinyuan.xyshop.bean.BuyExpressBean;
import com.xinyuan.xyshop.bean.CartGoodBean;
import com.xinyuan.xyshop.bean.CouponOrderBean;
import com.xinyuan.xyshop.bean.RedPacketBean;
import com.xinyuan.xyshop.bean.StoreInfoBean;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.common.ShopHelper;
import com.xinyuan.xyshop.even.BuyBusEven;
import com.xinyuan.xyshop.model.BuyExpressModel;
import com.xinyuan.xyshop.model.ConfirmOrderModel;
import com.xinyuan.xyshop.mvp.contract.ConfirmOrderView;
import com.xinyuan.xyshop.mvp.presenter.ConfirmOrderPresenter;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.EditTextWithDel;
import com.xinyuan.xyshop.widget.dialog.OrderVoucherDialog;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.XToast;
import com.youth.xframe.widget.loadingview.XLoadingView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fx on 2017/9/12.
 * 实物订单确认订单
 */

public class ConfirmOrderFragment extends BaseFragment<ConfirmOrderPresenter> implements ConfirmOrderView {
    @BindView(R.id.rv_confirm_order)
    RecyclerView rv_order;      //订单列表
    @BindView(R.id.tv_header_center)
    TextView tv_header_center;
    @BindView(R.id.iv_header_left)
    ImageView iv_header_left;
    @BindView(R.id.iv_header_right)
    ImageView iv_header_right;
    @BindView(R.id.toolbar_iv)
    Toolbar msg_toolbar;
    @BindView(R.id.tv_order_price)
    TextView tv_order_price; //订单总价

    @BindView(R.id.lodingView)
    XLoadingView lodingView;
    private List<StoreInfoBean> storeList;
    private OrderConfirmAdapter orderAdapter;


    private HashMap<String, CouponOrderBean> chosesCoupon = new HashMap<String, CouponOrderBean>();
    private HashMap<String, RedPacketBean> chosesRed = new HashMap<String, RedPacketBean>();
    private HashMap<String, String> orderExpress = new HashMap<String, String>();


    private BigDecimal totalExpressPrice = new BigDecimal(0); //订单邮费总金额
    private BigDecimal totalRedPrice = new BigDecimal(0);  //订单红包总金额
    private BigDecimal totalCouponProce = new BigDecimal(0); //订单优惠券总金额
    private BigDecimal totalOrderPrice = new BigDecimal(0); //订单总价
    private BigDecimal totalGoodsPrice = new BigDecimal(0); //商品总价

    private static int currentIndex = 0; //当前选择的订单

    private TextView tv_order_person;   //收货人
    private TextView tv_order_phone;    //收货联系
    private Button bt_order_location;   //收货地址
    private AddressBean addressBean;//当前地址
    private static long invoiceId; //发票ID

    private RedPacketBean storeRedBean; //选中的红包
    private CouponOrderBean storeCouponBean; //选中的优惠券
    private TextView tv_order_good_price;
    private TextView tv_order_good_send;
    private TextView tv_order_store;
    private TextView tv_order_store_red;
    private int indexFla = 1; //订单内店铺数（邮费计算用）

    public static ConfirmOrderFragment newInstance() {
        ConfirmOrderFragment fragment = new ConfirmOrderFragment();
        return fragment;
    }

    @Override
    protected ConfirmOrderPresenter createPresenter() {
        return new ConfirmOrderPresenter(getActivity(), this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_confirm_order;
    }

    @Override
    public void initView(View rootView) {
        SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
        SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
        tv_header_center.setText("确认订单");
        CommUtil.initToolBar(getActivity(), iv_header_left, iv_header_right);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(1);
        this.rv_order.setLayoutManager(layoutManager);
        lodingView.showLoading();
    }

    @Override
    public void initData() {

    }

    /**
     * 1-接收从购物车或详情页发送的订单列表数据
     * 2-获取收货地址
     *
     * @param storeList
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void GetOrder(List<StoreInfoBean> storeList) {
        this.storeList = storeList;
        mPresenter.getAddress();
    }

    /**
     * 显示收货地址，获取订单邮费
     *
     * @param addressBean
     */
    @Override
    public void showAddress(AddressBean addressBean) {
        if (XEmptyUtils.isEmpty(addressBean)) {
            showGoodList();
        } else {
            this.addressBean = addressBean;
            for (StoreInfoBean storeInfoBean : storeList) {
                storeInfoBean.setAddressId(String.valueOf(addressBean.getAddressId()));
                List<String> ids = new ArrayList<>();
                for (CartGoodBean goodBean : storeInfoBean.getGoodsCartList()) {
                    ids.add(String.valueOf(goodBean.getGoodsCartId()).trim());
                }

                mPresenter.getExpressMoney(String.valueOf(storeInfoBean.getStoreId()), ids.toString(), String.valueOf(addressBean.getAddressId()));
            }
        }


    }


    /**
     * 显示订单邮费
     * 设置每个订单的邮费，同时加入orderExpress Map,加入完后显示列表
     *
     * @param expressModel
     * @param ids
     */
    @Override
    public void showExpress(String storeId, BuyExpressModel expressModel, String ids) {
        for (StoreInfoBean storeInfoBean : storeList) {
            if (storeId.equals(String.valueOf(storeInfoBean.getStoreId()))) {
                storeInfoBean.setExpress(expressModel);
                if (!XEmptyUtils.isSpace(expressModel.getEms())) {
                    storeInfoBean.getExpress().setChoses("ems");
                    String price = expressModel.getEms();
                    price = price.substring(price.indexOf("[") + 1, price.indexOf("元"));
                    orderExpress.put(String.valueOf(storeInfoBean.getStoreId()), price);

                } else if (!XEmptyUtils.isSpace(expressModel.getSeller())) {
                    storeInfoBean.getExpress().setChoses("seller");
                    orderExpress.put(String.valueOf(storeInfoBean.getStoreId()), "0");
                } else if (!XEmptyUtils.isSpace(expressModel.getMail())) {
                    storeInfoBean.getExpress().setChoses("mail");
                    String price = expressModel.getMail();
                    price = price.substring(price.indexOf("[") + 1, price.indexOf("元"));
                    orderExpress.put(String.valueOf(storeInfoBean.getStoreId()), price);
                } else if (!XEmptyUtils.isSpace(expressModel.getExpress())) {
                    storeInfoBean.getExpress().setChoses("express");
                    String price = expressModel.getExpress();
                    price = price.substring(price.indexOf("[") + 1, price.indexOf("元"));
                    orderExpress.put(String.valueOf(storeInfoBean.getStoreId()), price);
                }
            }

        }
        if (indexFla == storeList.size()) { //请求完毕，显示订单列表
            showGoodList();
        }
        indexFla++;
    }

    @Override
    public void showOrderStatus(ConfirmOrderModel model) {
        startWithPop(PayFragment.newInstance(model.getOrderNumber(), model.getSumMoney()));
    }


    /**
     * 显示订单列表，加入头部和底部布局
     */
    @Override
    public void showGoodList() {
        if (!XEmptyUtils.isEmpty(storeList)) {
            if (!XEmptyUtils.isEmpty(orderAdapter)) {
                orderAdapter.notifyDataSetChanged();
            } else {
                this.orderAdapter = new OrderConfirmAdapter(R.layout.fragment_confirm_order_item, storeList) {
                    @Override
                    protected void convert(final BaseViewHolder helper, final StoreInfoBean item) {
                        final List<String> ids = new ArrayList<>();
                        for (CartGoodBean beans : item.getGoodsCartList()) {
                            ids.add(String.valueOf(beans.getGoodsCartId()));
                        }
                        item.setGoodCartId(ids.toString());
                        Button bt_order_good_send = helper.getView(R.id.bt_order_good_send);
                        Button bt_order_good_bill = helper.getView(R.id.bt_order_good_bill);
                        Button bt_order_good_discount = helper.getView(R.id.bt_order_good_discount);
                        Button bt_order_good_red = helper.getView(R.id.bt_order_good_red);
                        EditTextWithDel et_msg = helper.getView(R.id.editText);
                        if (XEmptyUtils.isEmpty(item.getInvoiceInfoId())) {
                            bt_order_good_bill.setText("不开发票");
                        } else {
                            bt_order_good_bill.setText("已选发票");
                        }
                        FlexboxLayout fl_order_goods = helper.getView(R.id.fl_goods);
                        fl_order_goods.removeAllViews();
                        edMsgList.add(et_msg);
                        for (CartGoodBean goodsVo : item.getGoodsCartList()) {
                            AddViewHolder addViewHolder = new AddViewHolder(mContext, R.layout.fragment_confirm_order_item_good);
                            ImageView iv_good_img = addViewHolder.getView(R.id.iv_good_img);
                            TextView tv_good_name = addViewHolder.getView(R.id.tv_good_name);
                            TextView tv_good_spec = addViewHolder.getView(R.id.tv_good_spec);
                            TextView tv_good_price = addViewHolder.getView(R.id.tv_good_price);
                            TextView tv_good_num = addViewHolder.getView(R.id.tv_good_num);

                            GlideImageLoader.setUrlImg(mContext, goodsVo.getGoodsImg(), iv_good_img);
                            tv_good_name.setText(goodsVo.getGoodsName());
                            tv_good_num.setText("X" + goodsVo.getGoodsCount());
                            tv_good_spec.setText("已选 " + goodsVo.getGoodsSpecText());
                            tv_good_price.setText(getResources().getString(R.string.money_rmb) + ShopHelper.getPriceString(goodsVo.getGoodsPrice()));
                            fl_order_goods.addView(addViewHolder.getCustomView());
                        }

                        if (!XEmptyUtils.isEmpty(item.getRedPacketBean())) {
                            bt_order_good_red.setText(item.getRedPacketBean().getRedpacketName());
                        }
                        bt_order_good_red.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                currentIndex = helper.getLayoutPosition();
                                mPresenter.getRedPacketList(item.getStoreId(), ids.toString(), getRedIds());

                            }
                        });


                        if (!XEmptyUtils.isEmpty(item.getCouponBean())) {
                            bt_order_good_discount.setText(item.getCouponBean().getCouponName());
                        }
                        bt_order_good_discount.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                currentIndex = helper.getLayoutPosition();
                                mPresenter.getCouponList(item.getStoreId(), ids.toString(), getCouponIds());

                            }
                        });


                        bt_order_good_bill.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                currentIndex = helper.getLayoutPosition();

                                start(InvoiceOrderFragment.newInstance());
                            }
                        });
                        if (!XEmptyUtils.isEmpty(item.getExpress())) {

                            if (!XEmptyUtils.isEmpty(item.getExpress().getChoses()) && !XEmptyUtils.isSpace(item.getExpress().getChoses())) {

                                if (item.getExpress().getChoses().equals("ems")) {
                                    bt_order_good_send.setText(item.getExpress().getEms());

                                } else if (item.getExpress().getChoses().equals("mail")) {
                                    bt_order_good_send.setText(item.getExpress().getMail());

                                } else if (item.getExpress().getChoses().equals("seller")) {
                                    bt_order_good_send.setText(item.getExpress().getSeller());

                                } else if (item.getExpress().getChoses().equals("express")) {
                                    bt_order_good_send.setText(item.getExpress().getExpress());

                                }
                            }
                        }

                        bt_order_good_send.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                currentIndex = helper.getLayoutPosition();

                                showPromoDialog(null, null, item.getExpress());
                            }
                        });


                    }

                };
                this.orderAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
                this.rv_order.setAdapter(orderAdapter);
                addTop();
                addBottom();
                calculate();
            }
        }

    }


    /**
     * 显示收货地址布局
     */
    private void addTop() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_confirm_order_top, (ViewGroup) rv_order.getParent(), false);
        tv_order_person = (TextView) view.findViewById(R.id.tv_order_person);
        tv_order_phone = (TextView) view.findViewById(R.id.tv_order_phone);
        bt_order_location = (Button) view.findViewById(R.id.bt_order_location);


        if (XEmptyUtils.isEmpty(addressBean)) {
            tv_order_person.setText(R.string.buy_recvice_null);
            bt_order_location.setText(R.string.buy_revice_address_null);
            bt_order_location.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    start(AddressListFragment.newInstance());
                }
            });

        } else {
            tv_order_person.setText(getString(R.string.text_revicer) + addressBean.getUserName());
            tv_order_phone.setText(addressBean.getMobile());
            bt_order_location.setText(addressBean.getAddress() + addressBean.getAddressInfo());
            bt_order_location.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    start(AddressListFragment.newInstance());
                }
            });

        }
        orderAdapter.addHeaderView(view);
        rv_order.scrollToPosition(0);
    }


    /**
     * 显示底部结算布局
     */
    private void addBottom() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_confirm_order_bottom, (ViewGroup) rv_order.getParent(), false);
        tv_order_good_price = (TextView) view.findViewById(R.id.tv_order_good_price);
        tv_order_good_send = (TextView) view.findViewById(R.id.tv_order_good_send);
        tv_order_store = (TextView) view.findViewById(R.id.tv_order_store);
        tv_order_store_red = (TextView) view.findViewById(R.id.tv_order_store_red);
        for (StoreInfoBean store : storeList) {
            for (CartGoodBean good : store.getGoodsCartList()) {
                totalGoodsPrice = totalGoodsPrice.add(good.getGoodsPrice().multiply(BigDecimal.valueOf(good.getGoodsCount())));
            }
        }
        tv_order_good_price.setText(ShopHelper.getPriceString(totalGoodsPrice));
        orderAdapter.setFooterView(view);
    }

    //订单页面各组件通信，发票、优惠券、红包、地址的选择和更改
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void BuyBusEven(BuyBusEven even) {
        if (even.getFlag().equals(BuyBusEven.chosesInvoice)) {
            storeList.get(currentIndex - 1).setInvoiceInfoId(String.valueOf(even.getObj()));
            orderAdapter.notifyDataSetChanged();//列表刷新

        } else if (even.getFlag().equals(BuyBusEven.choeseRedVoucher)) { //选择了红包
            storeRedBean = (RedPacketBean) even.getObj();
            storeList.get(currentIndex - 1).setRedPacketBean((RedPacketBean) even.getObj());
            chosesRed.put(String.valueOf(storeList.get(currentIndex - 1).getStoreId()), ((RedPacketBean) even.getObj()));
            calculate();    //统计计算各金额
            orderAdapter.notifyDataSetChanged();//列表刷新


        } else if (even.getFlag().equals(BuyBusEven.choeseCouponVoucher)) {//选择了优惠券
            storeCouponBean = (CouponOrderBean) even.getObj();
            storeList.get(currentIndex - 1).setCouponBean((CouponOrderBean) even.getObj());
            chosesCoupon.put(String.valueOf(storeList.get(currentIndex - 1).getStoreId()), ((CouponOrderBean) even.getObj()));
            calculate();  //统计计算各金额
            orderAdapter.notifyDataSetChanged();//列表刷新


        } else if (even.getFlag().equals(BuyBusEven.choeseExpress)) {
            BuyExpressBean expressBean = (BuyExpressBean) even.getObj();
            storeList.get(currentIndex - 1).getExpress().setChoses(expressBean.getType());
            String express = expressBean.getContent();
            express = express.substring(express.indexOf("[") + 1, express.indexOf("元"));
            orderExpress.put(String.valueOf(storeList.get(currentIndex - 1).getStoreId()), express);
            calculate(); //统计计算各金额
            orderAdapter.notifyDataSetChanged();//列表刷新
        }

    }

    /**
     * 统计计算
     */
    private void calculate() {

        getExpressMoney();
        getCouponMoney();
        getRedPackgetMoney();
        tv_order_good_price.setText(getResources().getString(R.string.money_rmb) + ShopHelper.getPriceString(totalGoodsPrice));
        tv_order_good_send.setText(getResources().getString(R.string.money_rmb) + ShopHelper.getPriceString(totalExpressPrice));
        tv_order_store_red.setText("- " + getResources().getString(R.string.money_rmb) + ShopHelper.getPriceString(totalRedPrice));
        tv_order_store.setText("- " + getResources().getString(R.string.money_rmb) + ShopHelper.getPriceString(totalCouponProce));
        totalOrderPrice = totalGoodsPrice.add(totalExpressPrice).subtract(totalCouponProce).subtract(totalRedPrice);
        tv_order_price.setText(getResources().getString(R.string.money_rmb) + ShopHelper.getPriceString(totalOrderPrice));
        lodingView.showContent();
    }


    /**
     * 统计邮费
     */
    private void getExpressMoney() {
        totalExpressPrice = new BigDecimal(0);
        if (!XEmptyUtils.isEmpty(orderExpress)) {
            for (Map.Entry<String, String> entry : orderExpress.entrySet()) {
                entry.getKey();
                entry.getValue();
                String s = entry.getValue();
                BigDecimal bigDecimal = new BigDecimal(s);
                totalExpressPrice = totalExpressPrice.add(bigDecimal);
            }
        }


    }

    /**
     * 统计订单优惠券金额
     */
    private void getCouponMoney() {
        totalCouponProce = new BigDecimal(0);
        if (!XEmptyUtils.isEmpty(chosesCoupon)) {
            for (Map.Entry<String, CouponOrderBean> entry : chosesCoupon.entrySet()) {
                entry.getKey();
                CouponOrderBean bean = entry.getValue();
                totalCouponProce = totalCouponProce.add(bean.getPrice());
            }
        }


    }

    /**
     * 统计订单红包金额
     */
    private void getRedPackgetMoney() {
        totalRedPrice = new BigDecimal(0);
        if (!XEmptyUtils.isEmpty(chosesRed)) {
            for (Map.Entry<String, RedPacketBean> entry : chosesRed.entrySet()) {
                entry.getKey();
                RedPacketBean bean = entry.getValue();
                totalRedPrice = totalRedPrice.add(bean.getPrice());
            }
        }
    }


    /**
     * 获取已经选择的优惠券字符串ID
     *
     * @return
     */
    private String getCouponIds() {
        if (!XEmptyUtils.isEmpty(chosesCoupon)) {
            String ids = "";
            for (Map.Entry<String, CouponOrderBean> entry : chosesCoupon.entrySet()) {
                entry.getKey();
                CouponOrderBean bean = entry.getValue();
                if (!entry.getKey().equals(String.valueOf(storeList.get(currentIndex - 1).getStoreId()))) { //在同一店铺选择的不加入
                    if (XEmptyUtils.isSpace(bean.getCouponStoreId())) {                 //店铺的优惠券不加入
                        ids += bean.getCouponInfoId() + ",";

                    }

                }


            }
            if (XEmptyUtils.isSpace(ids)) {
                return "[]";

            } else {
                return "[" + ids.substring(0, ids.length() - 1) + "]";

            }
        } else {
            return "[]";
        }


    }

    /**
     * 获取已经选择的红包字符串ID
     *
     * @return
     */
    private String getRedIds() {
        if (!XEmptyUtils.isEmpty(chosesRed)) {
            String ids = "";
            for (Map.Entry<String, RedPacketBean> entry : chosesRed.entrySet()) {
                entry.getKey();
                RedPacketBean bean = entry.getValue();
                if (!entry.getKey().equals(String.valueOf(storeList.get(currentIndex - 1).getStoreId()))) { //在同一店铺选择的不加入
                    if (XEmptyUtils.isSpace(bean.getRedpacketStoreId())) {                 //店铺的红包不加入
                        ids += bean.getRedpacketInfoId() + ",";
                    }

                }


            }
            if (XEmptyUtils.isSpace(ids)) {
                return "[]";

            } else {
                return "[" + ids.substring(0, ids.length() - 1) + "]";

            }
        } else {
            return "[]";
        }

    }

    /**
     * 换收货地址
     *
     * @param addressBean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateAddress(AddressBean addressBean) {
        tv_order_person.setText(mContext.getString(R.string.text_revicer) + addressBean.getUserName());
        tv_order_phone.setText(addressBean.getMobile());
        bt_order_location.setText(addressBean.getAddress() + " " + addressBean.getAddressInfo());
        this.addressBean = addressBean;
        showAddress(addressBean);
    }


    /**
     * 显示可用红包列表
     *
     * @param list
     */
    @Override
    public void showRedList(List<RedPacketBean> list) {
        if (XEmptyUtils.isEmpty(list)) {
            XToast.info("没有红包了");
        } else {
            showPromoDialog(list, null, null);

        }
    }

    /**
     * 显示可用优惠券列表
     *
     * @param list
     */
    @Override
    public void showCouponList(List<CouponOrderBean> list) {
        if (XEmptyUtils.isEmpty(list)) {
            XToast.info("没有优惠券了");
        } else {
            showPromoDialog(null, list, null);

        }
    }

    private List<EditTextWithDel> edMsgList = new ArrayList<>();

    @OnClick(R.id.tv_up_order)
    public void upOrder() {
        if (XEmptyUtils.isEmpty(addressBean)) {
            XToast.error("请选择收货地址！");
            return;
        }
        JsonArray jsonArr = new JsonArray();//json格式的数组
        for (int i = 0; i < storeList.size(); i++) {
            storeList.get(i).setMsg(edMsgList.get(i).getText().toString());
            jsonArr.add(storeList.get(i).getOrderJson());
        }
        mPresenter.putOrder(jsonArr.toString());
    }

    /**
     * 显示红包/优惠券弹窗
     *
     * @param redPacketBeans
     * @param couponOrderBeans
     */
    private void showPromoDialog(List<RedPacketBean> redPacketBeans, List<CouponOrderBean> couponOrderBeans, BuyExpressModel expressModel) {
        OrderVoucherDialog dialog = new OrderVoucherDialog(mContext, redPacketBeans, couponOrderBeans, expressModel);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialog.show();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        dialogWindow.setLayout(dm.widthPixels, dialogWindow.getAttributes().height);
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
