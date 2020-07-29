package com.xinyuan.xyshop.adapter;


import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.bean.BrandBean;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.entity.MySection;
import com.xinyuan.xyshop.ui.goods.store.StoreActivity;
import com.xinyuan.xyshop.util.CommUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/5/18.
 * 品牌店铺列表Adapter
 */

public class BrandAdapter extends BaseSectionQuickAdapter<MySection, BaseViewHolder> {


	/**
	 * Same as QuickAdapter#QuickAdapter(Context,int) but with
	 * some initialization data.
	 *
	 * @param layoutResId      The layout resource id of each item.
	 * @param sectionHeadResId The section head layout id for each item
	 * @param data             A new list is created out of this one to avoid mutable list
	 */
	public BrandAdapter(int layoutResId, int sectionHeadResId, List<MySection> data) {
		super(layoutResId, sectionHeadResId, data);
	}

	@Override
	protected void convertHead(BaseViewHolder helper, MySection item) {
		helper.setText(R.id.brand_UPName, item.header);
	}

	@Override
	protected void convert(BaseViewHolder helper, MySection item) {
		BrandBean bean = (BrandBean) item.t;
		GlideImageLoader.setImg(mContext, R.drawable.mine_logo, (ImageView) helper.getView(R.id.iv_store_img));
		helper.setText(R.id.store_name, bean.getStoreName());
		helper.setOnClickListener(R.id.iv_store_img, new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Map<String, String> params = new HashMap();
				params.put(Constants.STOREID, String.valueOf(bean.getStoreId()));
				CommUtil.gotoActivity(mContext, StoreActivity.class, false, params);
			}
		});

	}

	public int getLetterPosition(String letter) {
		for (int i = 0; i < getData().size(); i++) {
			if (getData().get(i).isHeader && getData().get(i).header.contains(letter)) {
				return i;
			}
		}
		return -1;
	}
}
