package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayout;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.entity.ExpressBean;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.util.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */

public class LogisticAdapter extends BaseQuickAdapter<ExpressBean.ExpressInfo, BaseViewHolder> {
	private static boolean isFrist = true;
	private List<ExpressBean.ExpressInfo> list = new ArrayList<>();

	public LogisticAdapter(@LayoutRes int layoutResId, @Nullable List<ExpressBean.ExpressInfo> expressInfos) {
		super(layoutResId, expressInfos);
		this.list = expressInfos;

	}

	@Override
	protected void convert(BaseViewHolder helper, final ExpressBean.ExpressInfo item) {
		if (helper.getLayoutPosition() == 1) {
			ImageView imageView = helper.getView(R.id.iv_express_index);
			imageView.setBackground(mContext.getResources().getDrawable(R.drawable.ic_logistic_frist));
			isFrist = false;
		} else if (helper.getLayoutPosition() == list.size()) {
			ImageView imageView = helper.getView(R.id.iv_express_index);
			imageView.setBackground(mContext.getResources().getDrawable(R.drawable.ic_logistic_last));
		}


		TextView tv_content = helper.getView(R.id.tv_express_detail);
		tv_content.setText(item.getContext());

	}


}
