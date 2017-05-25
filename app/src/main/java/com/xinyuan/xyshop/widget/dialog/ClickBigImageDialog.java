package com.xinyuan.xyshop.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.entity.GoodsEvaluate;
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/25.
 */

public class ClickBigImageDialog extends Dialog {

	@BindView(R.id.bigimage_banner)
	Banner banner;
	private int position;
	@BindView(R.id.ll_close)
	LinearLayout tv_close;
	@BindView(R.id.iv_close)
	ImageView iv_close;


	public ClickBigImageDialog(Context context, int position) {
		super(context, R.style.CommonDialog);
		this.position = position;
		XLog.v("position"+position);
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView((int) R.layout.click_bigimage_list_show);
		ButterKnife.bind((Dialog) this);
		List<String> imgs = new ArrayList<>();
		imgs.add("https://java.bizpower.com/upload/image/a9/e8/a9e8241550bd1f8885ee4ca2cbb7d215.jpg");
		imgs.add("http://img30.360buyimg.com/shaidan/s616x405_jfs/t4426/42/2095712931/81237/4fe8af98/58eb1a90Nf9a70b96.jpg");
		imgs.add("http://img30.360buyimg.com/shaidan/s616x405_jfs/t4699/49/2088711895/79151/7cdba7ce/58eb1a90Na939fc6b.jpg");

		String s = imgs.get(position);
		imgs.remove(position);
		imgs.add(0, s);
		banner.setBannerStyle(BannerConfig.NOT_INDICATOR)
				.setImageLoader(new GlideImageLoader())
				.setImages(imgs)
				.setBannerAnimation(Transformer.Default)
				.setIndicatorGravity(BannerConfig.CENTER)
				.start();
	}

	@OnClick(R.id.tv_close)
	public void OnClick() {
		iv_close.setBackground(getContext().getResources().getDrawable(R.drawable.down));
		dismiss();
	}


}
