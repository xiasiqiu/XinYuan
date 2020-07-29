package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.ShopHelper;
import com.xinyuan.xyshop.bean.GoodListItemBean;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.youth.xframe.utils.XEmptyUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by fx on 2017/5/17.
 * 搜索商品列表Adapter
 */

public class SearchGoodListAdapter extends BaseQuickAdapter<GoodListItemBean, BaseViewHolder> {
    private boolean isList;

    public SearchGoodListAdapter(@LayoutRes int layoutResId, @Nullable List<GoodListItemBean> data, Boolean isList) {
        super(layoutResId, data);
        this.isList = isList;
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodListItemBean item) {

        if (isList) {
            if (item.getGoodsType() == Constants.GOOD_ONLINE) {    //是否是虚拟商品
                helper.setVisible(R.id.tv_good_type, true);
                helper.setVisible(R.id.tv_good_active, false);

            } else {
                helper.setVisible(R.id.tv_good_type, false);

                if (!XEmptyUtils.isEmpty(item.getGoodsActive())) {
                    helper.setVisible(R.id.tv_good_active, true);
                    helper.setText(R.id.tv_good_active, item.getGoodsActive());
                } else {
                    helper.setVisible(R.id.tv_good_active, false);

                }
            }
            ImageView goodsImg = helper.getView(R.id.ivGoodPic);
            GlideImageLoader.setUrlImg(mContext, item.getImageUrl(), goodsImg);

            helper.setText(R.id.tv_goods_name, item.getGoodsName());
            helper.setText(R.id.tv_goods_price, mContext.getString(R.string.money_rmb) + ShopHelper.getPriceString(item.getGoodsPrice()));
            helper.setText(R.id.tv_goods_sellnum, mContext.getString(R.string.month_sell_num) + item.getConsumeNum() + "件");
            helper.setText(R.id.tv_goods_talk, mContext.getString(R.string.text_eva) + item.getGoodsEvaNum());


            goodsImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HashMap<String, String> params;
                    params = new HashMap();
                    params.put(Constants.GOODID, item.getGoodsId());
                    params.put(Constants.GOODTYPE, item.getGoodsType());
                    CommUtil.gotoActivity(mContext, GoodDetailActivity.class, false, params);
                }
            });

        } else {
            if (item.getGoodsType() == Constants.GOOD_ONLINE) {
                helper.setVisible(R.id.tv_good_type, true);
                helper.setVisible(R.id.tv_good_active, false);

            } else {
                helper.setVisible(R.id.tv_good_type, false);

                if (!XEmptyUtils.isEmpty(item.getGoodsActive())) {
                    helper.setVisible(R.id.tv_good_active, true);
                    helper.setText(R.id.tv_good_active, item.getGoodsActive());
                } else {
                    helper.setVisible(R.id.tv_good_active, false);

                }
            }
            ImageView goodsImg = helper.getView(R.id.ivGoodPic);
            GlideImageLoader.setUrlImg(mContext, item.getImageUrl(), goodsImg);

            helper.setText(R.id.tv_goods_name, item.getGoodsName());
            helper.setText(R.id.tv_goods_price, mContext.getString(R.string.money_rmb) + ShopHelper.getPriceString(item.getGoodsPrice()));
            helper.setText(R.id.tv_goods_sellnum, mContext.getString(R.string.month_sell_num) + item.getConsumeNum() + "件");
            helper.setText(R.id.tv_goods_talk, mContext.getString(R.string.text_eva) + item.getGoodsEvaNum());


            goodsImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HashMap<String, String> params;
                    params = new HashMap();
                    params.put(Constants.GOODID, item.getGoodsId());
                    params.put(Constants.GOODTYPE, item.getGoodsType());
                    CommUtil.gotoActivity(mContext, GoodDetailActivity.class, false, params);
                }
            });
        }


    }


}
