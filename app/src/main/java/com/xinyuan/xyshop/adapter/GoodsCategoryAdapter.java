package com.xinyuan.xyshop.adapter;

import android.content.Context;
import android.view.View;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.mvp.presenter.CategoryPresenterImpl;
import com.xinyuan.xyshop.util.ViewHolder;
import com.xinyuan.xyshop.model.CategoryModel.CategoryData;
import com.youth.xframe.utils.log.XLog;

/**
 * Title:
 * Description:
 * Created by Administrator on 2017/5/14.
 * 作者：fx on 2017/5/14 22:46
 */

public class GoodsCategoryAdapter extends CommonAdapter<CategoryData> {
	public GoodsCategoryAdapter(Context context) {
		super(context, R.layout.fragment_category_item_gridview);
	}

	public void convert(ViewHolder holder, final CategoryData goodsCategory) {

		holder.setText(R.id.tvGoodsClassName, goodsCategory.getCategoryName());
		holder.setImage(R.id.tvGoodsClassImage, goodsCategory.getCategoryImageUrl());
		XLog.v("imageUrl"+goodsCategory.getCategoryImageUrl());
		holder.getConvertView().setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				CategoryPresenterImpl.jump(GoodsCategoryAdapter.this.mContext, goodsCategory.getCategoryName(), false);
			}
		});
	}
}
