package com.xinyuan.xyshop.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.bean.InvoiceContentBean;
import com.xinyuan.xyshop.bean.InvoiceSpecBean;
import com.youth.xframe.utils.log.XLog;

import java.util.List;

/**
 * Created by fx on 2017/9/6.
 * 发票类型选择Adapter
 */

public class InvoiceSpecAdapter extends BaseQuickAdapter<InvoiceSpecBean, BaseViewHolder> {
	public InvoiceSpecAdapter(int layoutResId, @Nullable List<InvoiceSpecBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(final BaseViewHolder helper, final InvoiceSpecBean item) {
		final CheckBox checkBox = helper.getView(R.id.cb_spec);
		checkBox.setText(item.getInvoiceUnit());

		if (item.isCheck()) {
			checkBox.setChecked(true);
		} else {
			checkBox.setChecked(false);
		}
		if (onItemClickListener != null) {
			checkBox.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					onItemClickListener.onItemClick(view, helper.getLayoutPosition());
				}
			});
		}
	}
	public interface OnItemClickListener {
		void onItemClick(View view, int position);
	}
	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}
	private OnItemClickListener onItemClickListener;

}
