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

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.common.ShopHelper;
import com.xinyuan.xyshop.util.Image;
import com.youth.xframe.utils.log.XLog;

/**
 * Created by Administrator on 2017/6/28.
 */

public class SignDialog extends Dialog {
	private Context context;

	public SignDialog(@NonNull Context context) {
		super(context);
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

		public Builder(Context context) {
			this.context = context;
		}
		public SignDialog create() {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// instantiate the dialog with the custom Theme
			final SignDialog dialog = new SignDialog(context);
			View layout = inflater.inflate(R.layout.view_dialog_sign, null);
			dialog.addContentView(layout, new ViewGroup.LayoutParams(
						ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


			if (!ShopHelper.getSignStatus()) {
				((TextView) layout.findViewById(R.id.tv_content)).setText("今日你已签到");
				((TextView) layout.findViewById(R.id.tv_sign_credit)).setVisibility(View.GONE);

			} else {
				XLog.v("签到成功+1分");
				ShopHelper.setSignStatus(false);
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