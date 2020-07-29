package com.xinyuan.xyshop.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.entity.CreditMultipleItem;
import com.xinyuan.xyshop.model.CreditMallModel;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;

import java.util.List;

/**
 * Created by fx on 2017/6/22.
 * 积分商城首页多布局Adapter
 */

public class CreditMallAdapter extends BaseMultiItemQuickAdapter<CreditMultipleItem, BaseViewHolder> {
    public static CreditMallModel.CreditCouponBean couponBean;  //优惠券数据
    public static CreditMallModel.CreditRedpacketBean redpacketBean;    //红包数据
    public static CreditMallModel.CreditGoodBean goodBean;  //商品数据

    public CreditMallAdapter(List data, CreditMallModel.CreditGoodBean goodsModule, CreditMallModel.CreditCouponBean couponModule, CreditMallModel.CreditRedpacketBean redpacketModule) {
        super(data);
        addItemType(CreditMultipleItem.C_AD, R.layout.activity_credit_mall_item_ad);
        addItemType(CreditMultipleItem.C_MODULE, R.layout.activity_credit_mall_item_module);
        addItemType(CreditMultipleItem.R_AD, R.layout.activity_credit_mall_item_ad);
        addItemType(CreditMultipleItem.R_MODULE, R.layout.activity_credit_mall_item_module);
        addItemType(CreditMultipleItem.G_AD, R.layout.activity_credit_mall_item_ad);
        addItemType(CreditMultipleItem.G_MODULE, R.layout.activity_credit_mall_item_module);
        this.couponBean = couponModule;
        this.goodBean = goodsModule;
        this.redpacketBean = redpacketModule;
    }

    @Override
    protected void convert(BaseViewHolder helper, CreditMultipleItem item) {
        switch (item.getItemType()) {
            case CreditMultipleItem.C_AD:
                ImageView iv_coupon_ad = helper.getView(R.id.iv_credit_ad);
                if (!XEmptyUtils.isEmpty(couponBean.getAd().getImageUrl())) {
                    GlideImageLoader.setUrlImg(mContext, couponBean.getAd().getImageUrl(), iv_coupon_ad);
                    onAdclick(couponBean.getAd().getType(), couponBean.getAd().getData());
                }

                break;
            case CreditMultipleItem.C_MODULE:
                if (!XEmptyUtils.isEmpty(couponBean.getAd())) {
                    helper.setText(R.id.tv_credit_module, couponBean.getAd().getText());
                }
                if (!XEmptyUtils.isEmpty(couponBean.getCouponList())) {
                    RecyclerView rv_coupon = helper.getView(R.id.rv_credit_content);
                    CreditGoodsAdapter rv_adapter = new CreditGoodsAdapter(R.layout.item_credit_good, couponBean.getCouponList());
                    GridLayoutManager layoutManager = new GridLayoutManager(mContext, 2, 1, false);
                    rv_coupon.setLayoutManager(layoutManager);
                    rv_coupon.setAdapter(rv_adapter);
                }

                break;
            case CreditMultipleItem.R_AD:
                if (!XEmptyUtils.isEmpty(couponBean.getAd().getText())) {
                    helper.setText(R.id.tv_credit_module, couponBean.getAd().getText());
                    onAdclick(redpacketBean.getAd().getType(), redpacketBean.getAd().getData());
                }
                ImageView iv_red_ad = helper.getView(R.id.iv_credit_ad);
                GlideImageLoader.setUrlImg(mContext, redpacketBean.getAd().getImageUrl(), iv_red_ad);
                break;
            case CreditMultipleItem.R_MODULE:
                helper.setText(R.id.tv_credit_module, redpacketBean.getAd().getText());
                RecyclerView rv_red = helper.getView(R.id.rv_credit_content);
                CreditGoodsAdapter adapter = new CreditGoodsAdapter(R.layout.item_credit_good, redpacketBean.getRedpacketList());
                GridLayoutManager layoutManager2 = new GridLayoutManager(mContext, 2, 1, false);
                rv_red.setLayoutManager(layoutManager2);
                rv_red.setAdapter(adapter);
                break;
            case CreditMultipleItem.G_AD:
                ImageView iv_good_ad = helper.getView(R.id.iv_credit_ad);
                GlideImageLoader.setUrlImg(mContext, goodBean.getAd().getImageUrl(), iv_good_ad);
                onAdclick(goodBean.getAd().getType(), goodBean.getAd().getData());
                break;
            case CreditMultipleItem.G_MODULE:

                helper.setText(R.id.tv_credit_module, goodBean.getAd().getText());
                RecyclerView rv_good = helper.getView(R.id.rv_credit_content);
                CreditGoodsAdapter good_adapter = new CreditGoodsAdapter(R.layout.item_credit_good, goodBean.getGoodsList());
                GridLayoutManager layoutManager3 = new GridLayoutManager(mContext, 2, 1, false);
                rv_good.setLayoutManager(layoutManager3);
                rv_good.setAdapter(good_adapter);

                break;


        }
    }

    private void onAdclick(String type, String data) {


    }
}
