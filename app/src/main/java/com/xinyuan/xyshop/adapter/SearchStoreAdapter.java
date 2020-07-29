package com.xinyuan.xyshop.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.ShopHelper;
import com.xinyuan.xyshop.bean.StoreItemBean;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailActivity;
import com.xinyuan.xyshop.ui.goods.store.StoreActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by fx on 2017/8/3.
 * 店铺搜索列表Adapter
 */

public class SearchStoreAdapter extends BaseQuickAdapter<StoreItemBean, BaseViewHolder> {
    public SearchStoreAdapter(int layoutResId, @Nullable List<StoreItemBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreItemBean item) {
        CircleImageView circleImageView = helper.getView(R.id.iv_store_head);

        ImageView iv_good1 = helper.getView(R.id.iv_store_good1);
        ImageView iv_good2 = helper.getView(R.id.iv_store_good2);
        ImageView iv_good3 = helper.getView(R.id.iv_store_good3);

        TextView tv_store_good1 = helper.getView(R.id.tv_store_good1);
        TextView tv_store_good2 = helper.getView(R.id.tv_store_good2);
        TextView tv_store_good3 = helper.getView(R.id.tv_store_good3);

        if (!XEmptyUtils.isEmpty(item.getStoreLogo()) && !item.getStoreLogo().contains("undefined")) {
            XLog.v("店铺头像" + item.getStoreLogo());
            GlideImageLoader.setCircleImageView(mContext, item.getStoreLogo(), circleImageView);
        } else {
            circleImageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.class_default));

        }

        helper.setText(R.id.tv_store_name, item.getStoreName());
        helper.setText(R.id.tv_store_sell, "销量:" + item.getStoreSalenum());
        helper.setText(R.id.tv_store_goods, "共" + item.getStoreGoodsSum() + "件商品");

        if (XEmptyUtils.isEmpty(item.getGoodsInfo())) {
            helper.setVisible(R.id.ll_store_good, false);
        } else {
            helper.setVisible(R.id.ll_store_good, true);
        }
        XLog.d(item.getStoreName() + "推荐商品数量" + item.getGoodsInfo().size());
        switch (item.getGoodsInfo().size()) {
            case 1:
                GlideImageLoader.setUrlImg(mContext, item.getGoodsInfo().get(0).getGoodsPhoto(), iv_good1);
                helper.setText(R.id.tv_store_good1, mContext.getString(R.string.money_rmb) + ShopHelper.getPriceString(item.getGoodsInfo().get(0).getGoodsPrice()));
                helper.setVisible(R.id.tv_store_good2, false);
                helper.setVisible(R.id.tv_store_good3, false);
                helper.setOnClickListener(R.id.rl_store_good1, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goGoodDetail(item.getGoodsInfo().get(0));
                    }
                });
                iv_good1.setVisibility(View.VISIBLE);
                tv_store_good1.setVisibility(View.VISIBLE);
                iv_good2.setVisibility(View.GONE);
                iv_good3.setVisibility(View.GONE);
                tv_store_good2.setVisibility(View.GONE);
                tv_store_good3.setVisibility(View.GONE);
                break;
            case 2:
                GlideImageLoader.setUrlImg(mContext, item.getGoodsInfo().get(0).getGoodsPhoto(), iv_good1);
                GlideImageLoader.setUrlImg(mContext, item.getGoodsInfo().get(1).getGoodsPhoto(), iv_good2);
                helper.setText(R.id.tv_store_good1, mContext.getString(R.string.money_rmb) + ShopHelper.getPriceString(item.getGoodsInfo().get(0).getGoodsPrice()));
                helper.setText(R.id.tv_store_good2, mContext.getString(R.string.money_rmb) + ShopHelper.getPriceString(item.getGoodsInfo().get(1).getGoodsPrice()));
                helper.setVisible(R.id.tv_store_good3, false);
                helper.setOnClickListener(R.id.rl_store_good1, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goGoodDetail(item.getGoodsInfo().get(0));
                    }
                });
                helper.setOnClickListener(R.id.rl_store_good2, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goGoodDetail(item.getGoodsInfo().get(1));
                    }
                });
                iv_good1.setVisibility(View.VISIBLE);
                tv_store_good1.setVisibility(View.VISIBLE);
                iv_good2.setVisibility(View.VISIBLE);
                tv_store_good2.setVisibility(View.VISIBLE);
                iv_good3.setVisibility(View.GONE);
                tv_store_good3.setVisibility(View.GONE);

                break;
            case 3:
                GlideImageLoader.setUrlImg(mContext, item.getGoodsInfo().get(0).getGoodsPhoto(), iv_good1);
                GlideImageLoader.setUrlImg(mContext, item.getGoodsInfo().get(1).getGoodsPhoto(), iv_good2);
                GlideImageLoader.setUrlImg(mContext, item.getGoodsInfo().get(2).getGoodsPhoto(), iv_good3);
                helper.setText(R.id.tv_store_good1, mContext.getString(R.string.money_rmb) + ShopHelper.getPriceString(item.getGoodsInfo().get(0).getGoodsPrice()));
                helper.setText(R.id.tv_store_good2, mContext.getString(R.string.money_rmb) + ShopHelper.getPriceString(item.getGoodsInfo().get(1).getGoodsPrice()));
                helper.setText(R.id.tv_store_good3, mContext.getString(R.string.money_rmb) + ShopHelper.getPriceString(item.getGoodsInfo().get(2).getGoodsPrice()));
                helper.setOnClickListener(R.id.rl_store_good1, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goGoodDetail(item.getGoodsInfo().get(0));
                    }
                });
                helper.setOnClickListener(R.id.rl_store_good2, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goGoodDetail(item.getGoodsInfo().get(1));
                    }
                });
                helper.setOnClickListener(R.id.rl_store_good3, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goGoodDetail(item.getGoodsInfo().get(2));
                    }
                });
                iv_good1.setVisibility(View.VISIBLE);
                tv_store_good1.setVisibility(View.VISIBLE);
                iv_good2.setVisibility(View.VISIBLE);
                tv_store_good2.setVisibility(View.VISIBLE);
                iv_good3.setVisibility(View.VISIBLE);
                tv_store_good3.setVisibility(View.VISIBLE);
                break;

        }
        Button bt_store = helper.getView(R.id.bt_store);
        bt_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> params = new HashMap();
                params.put(Constants.STOREID, String.valueOf(item.getStoreId()));
                CommUtil.gotoActivity(mContext, StoreActivity.class, false, params);
            }
        });


    }

    private void goGoodDetail(StoreItemBean.GoodsInfoBean bean) {
        Map<String, String> params = new HashMap();
        params.put(Constants.GOODID, String.valueOf(bean.getGoodsId()));
        params.put(Constants.GOODTYPE, String.valueOf(bean.getGoodsType()));
        CommUtil.gotoActivity(mContext, GoodDetailActivity.class, false, params);
    }
}
