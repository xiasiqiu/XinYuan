package com.xinyuan.xyshop.adapter;


import android.widget.ImageView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.entity.BrandBean;
import com.xinyuan.xyshop.entity.MySection;
import com.xinyuan.xyshop.model.BrandModel;
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.youth.xframe.utils.log.XLog;

import java.util.List;


/**
 * Created by Administrator on 2017/5/18.
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
		XLog.v("图片"+bean.getBrandImage());
		GlideImageLoader.setImage(mContext, bean.getBrandImage(), (ImageView) helper.getView(R.id.ivBrandPic));
	}

	public int getLetterPosition(String letter) {
		XLog.v("HHHHH" + letter);
		for (int i = 0; i < getData().size(); i++) {
			if (getData().get(i).isHeader && getData().get(i).header.contains(letter)) {
				return i;
			}
		}
		return -1;
	}
}
