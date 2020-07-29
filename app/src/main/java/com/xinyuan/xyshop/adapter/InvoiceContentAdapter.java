package com.xinyuan.xyshop.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.bean.InvoiceContentBean;

import java.util.List;

/**
 * Created by fx on 2017/9/6.
 * 发票明细选择列表Adapter
 */

public class InvoiceContentAdapter extends BaseQuickAdapter<InvoiceContentBean, BaseViewHolder> {
	public InvoiceContentAdapter(int layoutResId, @Nullable List<InvoiceContentBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(final BaseViewHolder helper, final InvoiceContentBean item) {
		final CheckBox checkBox = helper.getView(R.id.cb_invoice_content);
		checkBox.setText(item.getContentName());
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

	public void setOnItemClickListener(InvoiceSpecAdapter.OnItemClickListener onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}

	private InvoiceSpecAdapter.OnItemClickListener onItemClickListener;
}
