package com.xinyuan.xyshop.widget;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinyuan.xyshop.R;

/**
 * Created by Administrator on 2017/6/7.
 */

public class XYToolbar extends Toolbar {
	private ImageView iv_header_left;
	private RelativeLayout rl_search;
	private TextView tv_header_right;
	private TextView tv_header_center;
	private RelativeLayout rl_header_msg;
	private RelativeLayout rl_header_msg_de;

	private View view;


	public XYToolbar(Context context) {
		super(context);
	}

	public XYToolbar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);

	}


	public XYToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		view = LayoutInflater.from(getContext()).inflate(R.layout.header_common, null);

		iv_header_left = (ImageView) view.findViewById(R.id.iv_header_left);
		rl_search = (RelativeLayout) view.findViewById(R.id.rl_search);
		tv_header_right = (TextView) view.findViewById(R.id.tv_header_right);
		tv_header_center = (TextView) view.findViewById(R.id.tv_header_center);
		rl_header_msg = (RelativeLayout) view.findViewById(R.id.rl_header_msg);
		rl_header_msg_de = (RelativeLayout) view.findViewById(R.id.rl_header_msg_de);

	}


	public void setBackImage() {
		iv_header_left.setVisibility(View.VISIBLE);
	}

	public void setSearch() {
		rl_search.setVisibility(View.VISIBLE);
	}

	public void setRightMsg() {
		rl_header_msg.setVisibility(View.VISIBLE);
	}

	public void setRightMsg_de() {
		rl_header_msg_de.setVisibility(View.VISIBLE);
	}

	private void setTvRight(String msg) {
		tv_header_right.setVisibility(View.VISIBLE);
		tv_header_right.setText(msg);

	}


	public void setTvCenter(String msg) {
		tv_header_center.setVisibility(View.VISIBLE);
		tv_header_center.setText(msg);

	}


}
