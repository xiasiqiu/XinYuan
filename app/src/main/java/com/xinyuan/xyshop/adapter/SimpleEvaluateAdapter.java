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
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.xinyuan.xyshop.model.EvaluateModel;
import com.xinyuan.xyshop.model.GoodDetailModel;
import com.xinyuan.xyshop.widget.dialog.ClickBigImageDialog;
import com.youth.xframe.utils.log.XLog;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/5/25.
 */

public class SimpleEvaluateAdapter extends BaseQuickAdapter<EvaluateModel.EvaluateBean, BaseViewHolder> {

	public SimpleEvaluateAdapter(@LayoutRes int layoutResId, @Nullable List<EvaluateModel.EvaluateBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(final BaseViewHolder helper, final EvaluateModel.EvaluateBean item) {

		final CircleImageView headImag = helper.getView(R.id.customer_image);
		XLog.v("IAMGE" + item.getHeadImg());
		Glide.with(mContext).load(item.getHeadImg()).asBitmap().centerCrop().placeholder(R.drawable.img_defaule).into(new BitmapImageViewTarget(headImag) {
			@Override
			protected void setResource(Bitmap resource) {
				RoundedBitmapDrawable circularBitmapDrawable =
						RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
				circularBitmapDrawable.setCircular(true);
				headImag.setImageDrawable(circularBitmapDrawable);
			}
		});
		final CheckBox cb_eva_like = helper.getView(R.id.cb_eva_like);


		TextView userNmae = helper.getView(R.id.customerName);
		userNmae.setText(item.getName());
		TextView evaluatetime = helper.getView(R.id.evaluatetime);
		evaluatetime.setText(item.getTime());
		TextView goodSpec = helper.getView(R.id.goodSpec);
		goodSpec.setText(item.getParams().toString().substring(1, item.getParams().toString().length() - 1));
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


		if (!item.getCommentMore().getTime().equals("") && item.getCommentImg() != null) {
			LinearLayout ll_evaluate_late = helper.getView(R.id.ll_evaluate_late);
			ll_evaluate_late.setVisibility(View.VISIBLE);
			TextView tv_eva_late_time = helper.getView(R.id.tv_eva_late_time);
			tv_eva_late_time.setText("用户" + item.getCommentMore().getTime() + "天后追评");
			TextView tv_eva_late_content = helper.getView(R.id.tv_eva_late_content);
			tv_eva_late_content.setText(item.getCommentMore().getContent());
			if (!item.getCommentMore().getImgs().isEmpty()) {
				FlexboxLayout evaluate_late_Images = helper.getView(R.id.evaluate_late_Images);
				for (final String img : item.getCommentMore().getImgs()) {
					AddViewHolder addViewHolder = new AddViewHolder(mContext, R.layout.view_image_simple);
					addViewHolder.setImage((int) R.id.ivImg, img);
					addViewHolder.getCustomView().setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							XLog.v("点击 了" + v.getId());
							ClickBigImageDialog dialog = new ClickBigImageDialog(mContext, item.getCommentMore().getImgs().indexOf(img));
							Window dialogWindow = dialog.getWindow();
							dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);
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


		}

		TextView tv_goods_eva_lookcount = helper.getView(R.id.tv_goods_eva_lookcount);
		final TextView tv_goods_eva_praisecount = helper.getView(R.id.tv_goods_eva_praisecount);
		tv_goods_eva_lookcount.setText("" + item.getLookCount());
		tv_goods_eva_praisecount.setText("" + item.getPraiseCount());

		cb_eva_like.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
				if (b) {
					cb_eva_like.setBackground(mContext.getResources().getDrawable(R.drawable.test_good_eva_dianz));
					tv_goods_eva_praisecount.setText("" + (item.getPraiseCount() + 1));
					tv_goods_eva_praisecount.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
				} else {
					cb_eva_like.setBackground(mContext.getResources().getDrawable(R.drawable.test_good_eva_dianz_de));
					tv_goods_eva_praisecount.setText("" + item.getPraiseCount());
					tv_goods_eva_praisecount.setTextColor(mContext.getResources().getColor(R.color.tv_hint));
				}
			}
		});


	}
}
