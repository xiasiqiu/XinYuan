package com.xinyuan.xyshop.adapter;

import android.location.Address;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.entity.AddressBean;
import com.xinyuan.xyshop.ui.mine.info.AddressEven;
import com.xinyuan.xyshop.widget.dialog.color.ColorDialog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */

public class AddressAdapter extends BaseQuickAdapter<AddressBean, BaseViewHolder> {
	private List<AddressBean> datas;

	public AddressAdapter(@LayoutRes int layoutResId, @Nullable List<AddressBean> data) {
		super(layoutResId, data);
		this.datas = data;
	}

	@Override
	protected void convert(final BaseViewHolder helper, final AddressBean item) {
		TextView tv_user_name = helper.getView(R.id.tv_user_name);
		tv_user_name.setText(item.getName());
		TextView tv_address = helper.getView(R.id.tv_address);
		tv_address.setText(item.getAreaName());
		TextView tv_user_phone = helper.getView(R.id.tv_user_phone);
		tv_user_phone.setText(item.getPhone());
		Button bt_address_delete = helper.getView(R.id.bt_address_delete);
		Button bt_address_edit = helper.getView(R.id.bt_address_edit);
		bt_address_edit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				EventBus.getDefault().post(new AddressEven(helper.getPosition()));
			}
		});
		bt_address_delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				final ColorDialog colorDialog = new ColorDialog(mContext);
				colorDialog.setTitle("删除地址");
				colorDialog.setContentText("确认删除该地址?");
				colorDialog.setPositiveListener("删除", new ColorDialog.OnPositiveListener() {
					@Override
					public void onClick(ColorDialog dialog) {
						remove(helper.getPosition());
						if (item.isDefault()) {
							datas.get(0).setDefault(true);
						}
						XToast.info("地址已删除");
						colorDialog.dismiss();
					}
				})
						.setNegativeListener("取消", new ColorDialog.OnNegativeListener() {
							@Override
							public void onClick(ColorDialog dialog) {
								colorDialog.dismiss();
							}
						}).show();

			}
		});
		final CheckBox checkBox = helper.getView(R.id.address_checkbox);
		checkBox.setChecked(item.isDefault());
		checkBox.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				if (!item.isDefault()) {
					for (AddressBean addressBean : datas) {
						addressBean.setDefault(false);
					}
					datas.get(helper.getLayoutPosition()).setDefault(true);
					setDefault(datas.get(helper.getLayoutPosition()));
					notifyDataSetChanged();
				}
			}
		});

	}


	public void setDefault(AddressBean bean) {
		XToast.info("设置成功");
	}
}
