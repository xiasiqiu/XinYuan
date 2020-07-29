package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.entity.ItemData;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fx on 2017/7/3.
 * 首页商品列表Adapter
 */

public class MenuMoreAdapter extends BaseQuickAdapter<ItemData, BaseViewHolder> {
    private List<ItemData> menus = new ArrayList<>();
    private int flag;

    public MenuMoreAdapter(int flag, @LayoutRes int layoutResId, @Nullable List<ItemData> data) {
        super(layoutResId, data);
        this.menus = data;
        this.flag = flag;
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemData item) {
        if (flag == 1) {
            helper.setText(R.id.home_menu_title, item.getText());
            GlideImageLoader.setUrlImg(mContext, item.getImageUrl(), (ImageView) helper.getView(R.id.home_menu_img));
        } else {
            helper.setText(R.id.home_menu_title, item.getText());
            if (helper.getLayoutPosition() == 9) {  //菜单第十位为默认的更多按钮
                GlideImageLoader.setImg(mContext, R.drawable.home_menu10, (ImageView) helper.getView(R.id.home_menu_img));
            } else {
                GlideImageLoader.setUrlImg(mContext, item.getImageUrl(), (ImageView) helper.getView(R.id.home_menu_img));
            }
        }


    }
}
