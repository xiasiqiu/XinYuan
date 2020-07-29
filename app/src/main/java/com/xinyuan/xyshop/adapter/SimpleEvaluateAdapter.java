package com.xinyuan.xyshop.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayout;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.bean.GoodEvaBean;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.widget.dialog.ClickBigImageDialog;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by fx on 2017/5/25.
 * 商品详情评论列表Adapter
 */

public class SimpleEvaluateAdapter extends BaseQuickAdapter<GoodEvaBean, BaseViewHolder> {

	public SimpleEvaluateAdapter(@LayoutRes int layoutResId, @Nullable List<GoodEvaBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(final BaseViewHolder helper, final GoodEvaBean item) {
		//用户头像
		final CircleImageView headImag = helper.getView(R.id.customer_image);
		GlideImageLoader.setCircleImageView(mContext, item.getEvaluateUserImg(), headImag);

		//用户名
		TextView userNmae = helper.getView(R.id.customerName);
		userNmae.setText(item.getEvaluateName());
		//用户评论时间
		TextView evaluatetime = helper.getView(R.id.evaluatetime);
		evaluatetime.setText(item.getTime());
		//用户购买商品规格
		TextView goodSpec = helper.getView(R.id.goodSpec);

		if (!XEmptyUtils.isEmpty(item.getParams())) {

			goodSpec.setText(item.getParams().toString());

		}

		//用户评论评分
		ImageView iv_user_star = helper.getView(R.id.iv_user_star);
		switch (item.getEvaluateRating()) {
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

		//用户首次评论内容
		TextView evaluateText = helper.getView(R.id.evaluateText);
		evaluateText.setText(item.getEvaluateInfo());

		//用户首次评论图片
		if (!XEmptyUtils.isEmpty(item.getEvaluateImgList())) {

			FlexboxLayout llImages = (FlexboxLayout) helper.getView(R.id.evaluateImages);
			llImages.removeAllViews();
			for (final String img : item.getEvaluateImgList()) {
				AddViewHolder addViewHolder = new AddViewHolder(mContext, R.layout.view_image_simple);
				ImageView ivImg = addViewHolder.getView(R.id.ivImg);
				XLog.v(img);
				GlideImageLoader.setUrlImg(mContext, img, ivImg);
				addViewHolder.getCustomView().setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						ClickBigImageDialog dialog = new ClickBigImageDialog(mContext, item.getEvaluateInfo(), img);
						Window dialogWindow = dialog.getWindow();
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

		if (!XEmptyUtils.isSpace(item.getReplay()) && !XEmptyUtils.isEmpty(item.getReplay())) {
			LinearLayout ll_evaluate_store_later = helper.getView(R.id.ll_evaluate_store);
			ll_evaluate_store_later.setVisibility(View.VISIBLE);
			TextView tv_eva_store_content_later = helper.getView(R.id.tv_eva_store_content);
			tv_eva_store_content_later.setText(item.getReplay());
		}
		if (!XEmptyUtils.isEmpty(item.getChaseRatings().getChaseRatingReplay())) {
			GoodEvaBean.ChaseRatingBean bean = item.getChaseRatings();
			LinearLayout ll_evaluate_late = helper.getView(R.id.ll_evaluate_late);
			ll_evaluate_late.setVisibility(View.VISIBLE);
			TextView tv_eva_late_time = helper.getView(R.id.tv_eva_late_time);
			if (bean.getChaseRatingtime() == 0) {
				tv_eva_late_time.setText(R.string.order_eva_later_day);

			} else {
				tv_eva_late_time.setText("用户" + bean.getChaseRatingtime() + "天后追评");
			}
			TextView tv_eva_late_content = helper.getView(R.id.tv_eva_late_content);
			tv_eva_late_content.setText(bean.getChaseRatingInfo());
			if (!XEmptyUtils.isEmpty(bean.getChaseRatingImgList())) {
				FlexboxLayout evaluate_late_Images = helper.getView(R.id.evaluate_late_Images);
				for (final String img : bean.getChaseRatingImgList()) {
					AddViewHolder addViewHolder = new AddViewHolder(mContext, R.layout.view_image_simple);
					addViewHolder.setImage((int) R.id.ivImg, img);
					addViewHolder.getCustomView().setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							ClickBigImageDialog dialog = new ClickBigImageDialog(mContext, item.getChaseRatings().getChaseRatingInfo(), img);
							Window dialogWindow = dialog.getWindow();
							dialogWindow.setGravity(Gravity.CENTER);
							dialog.show();
							DisplayMetrics dm = new DisplayMetrics();
							WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
							wm.getDefaultDisplay().getMetrics(dm);
							dialogWindow.setLayout(dm.widthPixels, dialogWindow.getAttributes().height);

						}
					});
					evaluate_late_Images.addView(addViewHolder.getCustomView());


				}
			}
			//商家是否对追评进行回复
			if (!XEmptyUtils.isSpace(bean.getChaseRatingReplay()) && !XEmptyUtils.isEmpty(bean.getChaseRatingReplay())) {
				LinearLayout ll_evaluate_store_later = helper.getView(R.id.ll_evaluate_store_later);
				ll_evaluate_store_later.setVisibility(View.VISIBLE);
				TextView tv_eva_store_content_later = helper.getView(R.id.tv_eva_store_content_later);
				tv_eva_store_content_later.setText(bean.getChaseRatingReplay());
			}


		}


	}
}
