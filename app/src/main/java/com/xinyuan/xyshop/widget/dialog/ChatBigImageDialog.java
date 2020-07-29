package com.xinyuan.xyshop.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.common.GlideImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/23.
 */

public class ChatBigImageDialog extends Dialog {

	@BindView(R.id.iv_big_img)
	ImageView iv_big_img;


	private String position;


	public ChatBigImageDialog(Context mContext, String url) {
		super(mContext, R.style.CommonDialog);
		this.position = url;

	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView((int) R.layout.view_chat_bigimage);
		ButterKnife.bind((Dialog) this);
		GlideImageLoader.setUrlImg(getContext(), position, iv_big_img);
	}

	@OnClick(R.id.iv_big_img)
	public void OnClick() {
		dismiss();
	}

	@Override
	public void cancel() {
		super.cancel();
	}
}
