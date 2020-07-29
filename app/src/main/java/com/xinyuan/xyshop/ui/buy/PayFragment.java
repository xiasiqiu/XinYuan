package com.xinyuan.xyshop.ui.buy;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.PayTypeAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.PayTypeBean;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.ShopHelper;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.RecycleViewDivider;
import com.youth.xframe.widget.XToast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by fx on 2017/9/7.
 * 支付页面
 */

public class PayFragment extends BaseFragment {
    @BindView(R.id.toolbar_tv)
    Toolbar toolbar_tv;
    @BindView(R.id.tv_header_center)
    TextView tv_header_center;
    @BindView(R.id.iv_header_left)
    ImageView iv_header_left;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.rv_pay_type)
    RecyclerView rv_pay_type;

    PayTypeAdapter typeAdapter;
    private BigDecimal price;
    private String orderNumber;

    public static PayFragment newInstance(String orderNumber, BigDecimal sumMoney) {
        PayFragment fragment = new PayFragment();
        fragment.orderNumber = orderNumber;
        fragment.price = sumMoney;
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
        tv_price.setText(getResources().getString(R.string.money_rmb) + ShopHelper.getPriceString(price));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(1);
        rv_pay_type.setLayoutManager(linearLayoutManager);
        rv_pay_type.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.VERTICAL));

        List<PayTypeBean> typeBeans = new ArrayList<>();
        PayTypeBean aliPay = new PayTypeBean();
        aliPay.setTypeHint("支付宝");
        aliPay.setTypeName("适合安装并使用支付宝APP支付的用户");
        aliPay.setTypeImg(R.drawable.ali_pay);
        typeBeans.add(aliPay);
        PayTypeBean wechatPay = new PayTypeBean();

        wechatPay.setTypeHint("微信支付");
        wechatPay.setTypeName("适合安装并使用微信APP支付的用户");
        wechatPay.setTypeImg(R.drawable.wechat_pay);
        typeBeans.add(wechatPay);

        PayTypeBean BankPay = new PayTypeBean();

        BankPay.setTypeHint("银联支付");
        BankPay.setTypeName("适合安装并使用银联支付的用户");
        BankPay.setTypeImg(R.drawable.bank);
        typeBeans.add(BankPay);

        typeAdapter = new PayTypeAdapter(R.layout.item_pay_type, typeBeans);
        rv_pay_type.setAdapter(typeAdapter);
        typeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                switch (position) {
                    case 0:
                        startWithPop(PayResultFragment.newInstance(Constants.ALIPAY, orderNumber));
                        break;
                    case 1:
                        //start(PayResultFragment.newInstance(Constants.WECHATPAY, orderId));
                        XToast.error("暂未开通该支付方式");
                        break;
                    case 2:
                        //start(PayResultFragment.newInstance(Constants.YINLANPAY, orderId));
                        XToast.error("暂未开通该支付方式");
                        break;
                }

            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_pay;
    }


}
