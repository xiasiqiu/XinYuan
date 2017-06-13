package com.xinyuan.xyshop.adapter;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayout;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.entity.GoodsEvaluate;
import com.xinyuan.xyshop.model.GoodDetailModel;
import com.xinyuan.xyshop.model.GoodsDetailModel;
import com.xinyuan.xyshop.ui.goods.fragment.GoodsInfoFragment;
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.xinyuan.xyshop.util.Image;
import com.xinyuan.xyshop.widget.dialog.ClickBigImageDialog;

import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/25.
 */

public class SimpleEvaluateAdapter extends BaseQuickAdapter<GoodDetailModel.CommentList, BaseViewHolder> {

	public SimpleEvaluateAdapter(@LayoutRes int layoutResId, @Nullable List<GoodDetailModel.CommentList> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(final BaseViewHolder helper, final GoodDetailModel.CommentList item) {

		ImageView headImag = helper.getView(R.id.customer_image);
		GlideImageLoader.setImage(mContext, item.getHeadImg(), headImag);
		TextView userNmae = helper.getView(R.id.customerName);
		userNmae.setText(item.getName());
		TextView evaluatetime = helper.getView(R.id.evaluatetime);
		evaluatetime.setText(item.getTime());
		TextView goodSpec = helper.getView(R.id.goodSpec);
		goodSpec.setText(item.getParams().toString());
		ImageView iv_user_star = helper.getView(R.id.iv_user_star);
		switch (item.getCommentLevel()) {
			case 1:
				iv_user_star.setImageResource(R.drawable.evaluate_star_1);
				break;
			case 2:
				iv_user_star.setImageResource(R.drawable.evaluate_star_2);
				break;
			case 3:
				iv_user_star.setImageResource(R.drawable.evaluate_star_3);
				break;
			case 4:
				iv_user_star.setImageResource(R.drawable.evaluate_star_4);
				break;
			case 5:
				iv_user_star.setImageResource(R.drawable.evaluate_star_5);
				break;

		}

		TextView evaluateText = helper.getView(R.id.evaluateText);
		evaluateText.setText(item.getCommentContent());


		if (!item.getCommentImg().isEmpty() && item.getCommentImg() != null) {

			FlexboxLayout llImages = (FlexboxLayout) helper.getView(R.id.evaluateImages);
			for (final String img : item.getCommentImg()) {
				AddViewHolder addViewHolder = new AddViewHolder(mContext, R.layout.view_image_simple);
				addViewHolder.setImage((int) R.id.ivImg, img);
				addViewHolder.getCustomView().setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						XLog.v("点击 了" + v.getId());
						ClickBigImageDialog dialog = new ClickBigImageDialog(mContext, item.getCommentImg().indexOf(img));
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


		if (!item.getCommentMore().getTime().equals("")) {
			LinearLayout ll_evaluate_late = helper.getView(R.id.ll_evaluate_late);
			ll_evaluate_late.setVisibility(View.VISIBLE);

			TextView tv_eva_late_time = helper.getView(R.id.tv_eva_late_time);
			tv_eva_late_time.setText("用户" + item.getCommentMore().getTime() + "天后追评");
			TextView tv_eva_late_content = helper.getView(R.id.tv_eva_late_content);
			tv_eva_late_content.setText(item.getCommentMore().getContent());

		}

		TextView tv_goods_eva_lookcount = helper.getView(R.id.tv_goods_eva_lookcount);
		TextView tv_goods_eva_praisecount = helper.getView(R.id.tv_goods_eva_praisecount);
		tv_goods_eva_lookcount.setText(""+item.getLookCount());
		tv_goods_eva_praisecount.setText(""+item.getPraiseCount());

	}
}
