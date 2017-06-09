package com.xinyuan.xyshop.adapter;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayout;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.entity.GoodsEvaluate;
import com.xinyuan.xyshop.ui.goods.fragment.GoodsInfoFragment;
import com.xinyuan.xyshop.widget.dialog.ClickBigImageDialog;
import com.xinyuan.xyshop.widget.dialog.GoodDetailsSpecDialog;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/25.
 */

public class SimpleEvaluateAdapter extends BaseQuickAdapter<GoodsEvaluate, BaseViewHolder> {

	public SimpleEvaluateAdapter(@LayoutRes int layoutResId, @Nullable List<GoodsEvaluate> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(final BaseViewHolder helper, final GoodsEvaluate item) {
		final ArrayList<String> imgList = new ArrayList();
		imgList.add("http://img30.360buyimg.com/shaidan/s616x405_jfs/t4426/42/2095712931/81237/4fe8af98/58eb1a90Nf9a70b96.jpg");
		imgList.add("http://img30.360buyimg.com/shaidan/s616x405_jfs/t4699/49/2088711895/79151/7cdba7ce/58eb1a90Na939fc6b.jpg");
		imgList.add("http://img30.360buyimg.com/shaidan/s616x405_jfs/t4426/42/2095712931/81237/4fe8af98/58eb1a90Nf9a70b96.jpg");

		FlexboxLayout llImages = (FlexboxLayout) helper.getView(R.id.evaluateImages);
		for (final String img : imgList) {
			AddViewHolder addViewHolder = new AddViewHolder(mContext, R.layout.view_image_simple);
			addViewHolder.setImage((int) R.id.ivImg, img);
			addViewHolder.getCustomView().setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					XLog.v("点击 了" + v.getId());
					ClickBigImageDialog dialog = new ClickBigImageDialog(mContext, imgList.indexOf(img));
					Window dialogWindow = dialog.getWindow();
					dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);
					dialog.show();
					DisplayMetrics dm = new DisplayMetrics();
					WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
					wm.getDefaultDisplay().getMetrics(dm);
					dialogWindow.setLayout(dm.widthPixels, dialogWindow.getAttributes().height);

				}
			});
			llImages.addView(addViewHolder.getCustomView());


		}
	}
}
