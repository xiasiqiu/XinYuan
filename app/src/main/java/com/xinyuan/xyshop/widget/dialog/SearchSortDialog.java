package com.xinyuan.xyshop.widget.dialog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xinyuan.xyshop.R;

/**
 * Created by Administrator on 2017/5/17.
 */

public class SearchSortDialog extends PopupWindow {

	private Context context;
	private View locationView;
	private PopupWindow popupWindow;
	private TextView tvShowSort;


	public SearchSortDialog(Context context, View locationView, TextView tvShowSort) {
		this.context = context;
		this.tvShowSort = tvShowSort;
		this.locationView = locationView;
		initmPopupWindowView();
	}

	public void initmPopupWindowView() {
		View customView = LayoutInflater.from(this.context).inflate(R.layout.activity_search_sort_dialog, null);
		this.popupWindow = new PopupWindow(customView, -2, -2);
		this.popupWindow.setBackgroundDrawable(new BitmapDrawable(this.context.getResources(), (Bitmap) null));
		this.popupWindow.setOutsideTouchable(true);
		this.popupWindow.setFocusable(true);
		customView.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (SearchSortDialog.this.popupWindow != null && SearchSortDialog.this.popupWindow.isShowing()) {
					SearchSortDialog.this.popupWindow.dismiss();
				}
				return false;
			}
		});
		TextView tvStore = (TextView) customView.findViewById(R.id.tvStore);
		((TextView) customView.findViewById(R.id.tvGood)).setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				SearchSortDialog.this.tvShowSort.setText("商品");
				SearchSortDialog.this.popupWindow.dismiss();
			}
		});
		tvStore.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				SearchSortDialog.this.tvShowSort.setText("店铺");
				SearchSortDialog.this.popupWindow.dismiss();
			}
		});
	}

	public void show() {
		this.popupWindow.showAsDropDown(this.locationView);
	}
}










