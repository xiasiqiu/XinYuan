package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.entity.ItemData;
import com.xinyuan.xyshop.entity.Menu;
import com.xinyuan.xyshop.util.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/3.
 */

public class MenuMoreAdapter extends BaseQuickAdapter<ItemData, BaseViewHolder> {
	private List<ItemData> menus = new ArrayList<>();

	public MenuMoreAdapter(@LayoutRes int layoutResId, @Nullable List<ItemData> data) {
		super(layoutResId, data);
		this.menus = data;
	}

	@Override
	protected void convert(BaseViewHolder helper, ItemData item) {

		helper.setText(R.id.home_menu_title, item.getText());
		GlideImageLoader.setImage(mContext, item.getImageUrl(), (ImageView) helper.getView(R.id.home_menu_img));

	}
}
