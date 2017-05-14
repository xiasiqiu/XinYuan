package com.xinyuan.xyshop.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.entity.GoodCategory;
import com.xinyuan.xyshop.mvp.presenter.CategoryPresenterImpl;
import com.xinyuan.xyshop.util.ViewHolder;

/**
 * Title:
 * Description:
 * Created by Administrator on 2017/5/14.
 * 作者：fx on 2017/5/14 22:46
 */

public class GoodsCategoryAdapter extends CommonAdapter<GoodCategory> {
    public GoodsCategoryAdapter(Context context) {
        super(context, R.layout.category_gridview_item);
    }

    public void convert(ViewHolder holder, final GoodCategory goodsCategory) {


        holder.setText(R.id.tvGoodsClassName, goodsCategory.getCategoryName());
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("Catogory", "onClick: cat = " + goodsCategory.getCategoryId());
                CategoryPresenterImpl.jump(GoodsCategoryAdapter.this.mContext, goodsCategory.getCategoryId(), false);
            }
        });
    }
}
