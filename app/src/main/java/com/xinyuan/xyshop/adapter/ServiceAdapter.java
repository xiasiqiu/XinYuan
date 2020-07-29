package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayout;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.bean.OrderServiceStoreInfoBean;
import com.xinyuan.xyshop.bean.ServiceGoodBean;
import com.xinyuan.xyshop.bean.ServiceOrderBean;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.even.ServiceBusEven;
import com.xinyuan.xyshop.ui.goods.store.StoreActivity;
import com.xinyuan.xyshop.util.CommUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;

/**
 * Created by fx on 2017/6/5.
 * 售后订单列表Adapter
 */

public class ServiceAdapter extends BaseQuickAdapter<ServiceOrderBean, BaseViewHolder> {

    private static boolean flag = true;

    public ServiceAdapter(@LayoutRes int layoutResId, @Nullable List<ServiceOrderBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, final ServiceOrderBean item) {
        Button bt_order_item_store = helper.getView(R.id.bt_order_item_store);
        bt_order_item_store.setText(item.getStoreName());
        bt_order_item_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> params;
                params = new HashMap();
                params.put(Constants.STOREID, String.valueOf(item.getStoreId()));
                CommUtil.gotoActivity(mContext, StoreActivity.class, false, params);
            }
        });

        OrderServiceStoreInfoBean storeInfoBean = new OrderServiceStoreInfoBean();
        storeInfoBean.setStoreUserName(item.getStoreName());
        storeInfoBean.setStoreUserImg(item.getStoreUserImg());
        storeInfoBean.setStoreUserId(item.getStoreUserId());
        storeInfoBean.setStoreId(item.getStoreId());
        storeInfoBean.setOrderId(item.getOrderId());
        final FlexboxLayout fl_order_goods = helper.getView(R.id.fl_service_goods);
        fl_order_goods.removeAllViews();
        for (final ServiceGoodBean goodBean : item.getGoodsCartList()) {
            AddViewHolder addViewHolder = new AddViewHolder(mContext, R.layout.fragment_orderservice_item_good);
            ImageView iv_good_img = addViewHolder.getView(R.id.iv_good_img);  //商品图片
            TextView tv_good_name = addViewHolder.getView(R.id.tv_good_name);
            TextView tv_good_spec = addViewHolder.getView(R.id.tv_good_spec);
            TextView tv_good_pice = addViewHolder.getView(R.id.tv_good_pice);
            TextView tv_good_status = addViewHolder.getView(R.id.tv_good_status);
            tv_good_name.setText(goodBean.getGoodsName());
            tv_good_spec.setText("已选:" + goodBean.getSpec_info());
            String tag = "";
            GlideImageLoader.setUrlImg(mContext, goodBean.getGoodsImg(), iv_good_img);
            int state = 0;
            if (goodBean.getRefundId() == 0) {
                state = goodBean.getReturnMark();
                tv_good_pice.setText("售后金额：" + mContext.getString(R.string.money_rmb) + goodBean.getReturnMoney());
                tag = "退货申请";
                storeInfoBean.setServiceId(goodBean.getReturnId());
            } else {
                state = goodBean.getRefundMark();
                tv_good_pice.setText("售后金额：" + mContext.getString(R.string.money_rmb) + goodBean.getRefundMoney());
                tag = "退款申请";
                storeInfoBean.setServiceId(goodBean.getRefundId());

            }
            Button bt_service_detail = addViewHolder.getView(R.id.bt_service_detail);
            bt_service_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (goodBean.getRefundId() == 0) {
                        EventBus.getDefault().post(new ServiceBusEven(ServiceBusEven.ToServiceGoodDetail, storeInfoBean));
                    } else {
                        EventBus.getDefault().post(new ServiceBusEven(ServiceBusEven.ToServiceMoneyDetail, storeInfoBean));

                    }
                }
            });
            switch (state) {
                case 1:
                    tv_good_status.setText(tag + "-已发起");
                    break;
                case 2:
                    tv_good_status.setText(tag + "-卖家同意申请");

                    break;
                case 3:
                    tv_good_status.setText(tag + "-卖家拒绝申请");

                    break;
                case 4:
                    tv_good_status.setText(tag + "-提交平台处理");

                    break;
                case 5:
                    tv_good_status.setText(tag + "-买家已退货");

                    break;
                case 6:
                    tv_good_status.setText(tag + "-卖家已收货");

                    break;
                case 7:
                    tv_good_status.setText(tag + "-卖家同意退款");

                    break;
                case 8:
                    tv_good_status.setText(tag + "-卖家拒绝退款");

                    break;
                case 9:
                    tv_good_status.setText(tag + "-卖家已退款");

                    break;
                case 10:
                    tv_good_status.setText(tag + "-处理完毕");

                    break;
                case 0:
                    tv_good_status.setText(tag + "-售后已关闭");
                    bt_service_detail.setVisibility(View.GONE);
                    break;
            }

            fl_order_goods.addView(addViewHolder.getCustomView());
        }


    }


}
