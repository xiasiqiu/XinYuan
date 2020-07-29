package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.bean.CouponBean;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailActivity;
import com.xinyuan.xyshop.ui.goods.store.StoreActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.Image;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fx on 2017/6/26.
 * 优惠券列表Adapter
 */

public class CouponAdapter extends BaseQuickAdapter<CouponBean, BaseViewHolder> {

    public CouponAdapter(@LayoutRes int layoutResId, @Nullable List<CouponBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final CouponBean item) {
        ImageView iv_coupon_img = helper.getView(R.id.iv_coupon_img);
        TextView tv_store_level = helper.getView(R.id.tv_store_level);
        ImageView iv_store_credits = helper.getView(R.id.iv_store_credits);

        GlideImageLoader.setUrlImg(mContext, item.getCouponImg(), iv_coupon_img);
        helper.setText(R.id.tv_store_name, item.getStoreName());
        helper.setText(R.id.tv_coupon_name, item.getCouponName());
        helper.setText(R.id.tv_coupon_time, mContext.getString(R.string.coupon_usetime) + item.getBeginTime() + mContext.getString(R.string.text_to) + item.getEndTime());
        tv_store_level.setText(item.getStoreGradeName());

        if (!XEmptyUtils.isEmpty(item.getStoreCredit())) {
            GlideImageLoader.setImg(mContext, CommUtil.getStoreCredit(mContext, item.getStoreCredit()), iv_store_credits);
        }

        helper.setOnClickListener(R.id.bt_go_store, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> params = new HashMap();
                params.put(Constants.STOREID, item.getStoreId());
                CommUtil.gotoActivity(mContext, StoreActivity.class, false, params);
            }
        });

    }
}
