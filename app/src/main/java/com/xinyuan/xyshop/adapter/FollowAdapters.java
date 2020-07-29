package com.xinyuan.xyshop.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.bean.FavStoreBean;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.ui.goods.store.StoreActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.Image;
import com.youth.xframe.utils.XEmptyUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fx on 2017/6/5.
 * 关注店铺列表Adapter
 */

public class FollowAdapters extends BaseQuickAdapter<FavStoreBean, BaseViewHolder> {
    public static boolean isDetele = false;
    public static HashMap<Integer, String> choeseList = new HashMap<Integer, String>();

    public FollowAdapters(int layoutResId, @Nullable List<FavStoreBean> data, boolean isDetele) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final FavStoreBean item) {
        CheckBox chekbox = helper.getView(R.id.chekbox);
        if (isDetele) {
            chekbox.setVisibility(View.VISIBLE);
            if (choeseList.containsValue(item.getFollowId())) {
                chekbox.setChecked(true);
            } else {
                chekbox.setChecked(false);
            }
            chekbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        choeseList.put(helper.getPosition(), String.valueOf(item.getFollowId()));
                    }
                }
            });
        } else {
            chekbox.setVisibility(View.GONE);
        }

        ImageView iv_follow_img = helper.getView(R.id.iv_follow_img);
        GlideImageLoader.setUrlImg(mContext, item.getStoreLogo(), iv_follow_img);
        helper.setText(R.id.tv_follow_name, item.getStoreName());

        if (!XEmptyUtils.isEmpty(item.getStoreGradeLevel())) {
            ImageView iv_store_level = helper.getView(R.id.iv_store_level);
            GlideImageLoader.setImg(mContext, CommUtil.getStoreCredit(mContext, Integer.parseInt(item.getStoreGradeLevel())), iv_store_level);
        }

        TextView tv_store_credit = helper.getView(R.id.tv_store_credit);
        tv_store_credit.setText(item.getStoreGradeName());
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
}

