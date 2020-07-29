package com.xinyuan.xyshop.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.common.GlideImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/25.
 */

public class ClickBigImageDialog extends Dialog {


	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;
	@BindView(R.id.iv_big_img)
	ImageView iv_big_img;
	@BindView(R.id.tv_eva)
	TextView tv_eva;
	@BindView(R.id.iv_close)
	ImageView iv_close;
	private String position;
	private String evaInfo;

	public ClickBigImageDialog(Context mContext, String evaInfo, String url) {
		super(mContext, R.style.CommonDialog);
		this.position = url;
		this.evaInfo = evaInfo;

	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView((int) R.layout.click_bigimage_list_show);
		getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		ButterKnife.bind((Dialog) this);
		iv_header_left.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dismiss();
			}
		});
		GlideImageLoader.setUrlImg(getContext(), position, iv_big_img);
		iv_big_img.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dismiss();
			}
		});
		tv_eva.setText(evaInfo);
	}

	@OnClick(R.id.tv_close)
	public void OnClick() {
		iv_close.setBackground(getContext().getResources().getDrawable(R.drawable.down));
		dismiss();
	}

	@Override
	public void cancel() {
		super.cancel();
	}

}
