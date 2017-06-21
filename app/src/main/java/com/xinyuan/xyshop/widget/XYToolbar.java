package com.xinyuan.xyshop.widget;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinyuan.xyshop.R;

import static com.xinyuan.xyshop.R.id.iv_header_left;
import static com.xinyuan.xyshop.R.id.iv_left;
import static com.xinyuan.xyshop.R.id.rl_search;

/**
 * Created by Administrator on 2017/6/7.
 */

public class XYToolbar extends Toolbar {
	private static ImageView iv_left;
	private static ImageView iv_right;
	private static TextView tv_center;
	private static TextView tv_right;
	private static LinearLayout ll_search_layout;

	private static EditText category_et_search;
	private static ImageView iv_search_go;
	private View view;

	private Context mContext;

	public XYToolbar(Context context) {
		super(context);
	}

	public XYToolbar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		this.mContext = context;
	}


	public XYToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		view = LayoutInflater.from(getContext()).inflate(R.layout.view_toolbar_common, null);
		iv_left = (ImageView) view.findViewById(R.id.iv_left);
		iv_right = (ImageView) view.findViewById(R.id.iv_right);
		tv_center = (TextView) view.findViewById(R.id.tv_center);
		tv_right = (TextView) view.findViewById(R.id.tv_right);
		ll_search_layout = (LinearLayout) view.findViewById(R.id.ll_search_layout);

		category_et_search = (EditText) view.findViewById(R.id.category_et_search);
		iv_search_go = (ImageView) view.findViewById(R.id.iv_search_go);
	}


	public static void setBackImage() {
		iv_left.setVisibility(View.VISIBLE);


	}

	public static void setSearch() {

	}

	public static void setRightMsg() {

	}

	public static void setRightMsg_de() {

	}

	private static void setTvRight(String msg) {

	}


	public static void setTvCenter(String msg) {


	}


}
