package com.xinyuan.xyshop.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.bean.ChatSendBean;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.common.ShopHelper;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.util.Image;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;

/**
 * Created by Administrator on 2017/6/28.
 */

public class SignDialog extends Dialog {
	private Context context;
	public static boolean isSign = false;

	public SignDialog(@NonNull Context context, Boolean isSign) {
		super(context);
		this.isSign = isSign;
	}

	public SignDialog(Context context, int theme) {
		super(context, theme);
	}


	public static class Builder {
		private Context context;
		private int status;
		private View contentView;
		private DialogInterface.OnClickListener positiveButtonClickListener;
		private DialogInterface.OnClickListener negativeButtonClickListener;
		private boolean isSign;
		private String num;

		public Builder(Context context, Boolean isSign, String num) {
			this.context = context;
			this.isSign = isSign;
			this.num = num;
		}

		public SignDialog create() {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// instantiate the dialog with the custom Theme
			final SignDialog dialog = new SignDialog(context, isSign);
			View layout = inflater.inflate(R.layout.view_dialog_sign, null);
			dialog.addContentView(layout, new ViewGroup.LayoutParams(
					ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			if (isSign) {
				((TextView) layout.findViewById(R.id.tv_sign_credit)).setText("积分+" + num);
			} else {
				((TextView) layout.findViewById(R.id.tv_content)).setText("今日你已签到");
				((TextView) layout.findViewById(R.id.tv_sign_credit)).setVisibility(View.GONE);
			}
			((ImageView) layout.findViewById(R.id.iv_close)).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					dialog.dismiss();
				}
			});
			dialog.setContentView(layout);
			return dialog;
		}
	}


}