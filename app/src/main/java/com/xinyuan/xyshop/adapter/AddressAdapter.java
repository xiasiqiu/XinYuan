package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.bean.AddressBean;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.even.AddressEven;
import com.xinyuan.xyshop.widget.dialog.color.ColorDialog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by fx on 2017/7/10.
 * 收货地址列表Adapter
 */

@SuppressWarnings("deprecation")
public class AddressAdapter extends BaseQuickAdapter<AddressBean, BaseViewHolder> {

	public AddressAdapter(@LayoutRes int layoutResId, @Nullable List<AddressBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(final BaseViewHolder helper, final AddressBean item) {
		helper.setText(R.id.tv_address, item.getAddress() + " " + item.getAddressInfo());
		helper.setText(R.id.tv_user_phone, item.getMobile());
		helper.setText(R.id.tv_user_name, item.getUserName());
		helper.setOnClickListener(R.id.bt_address_delete, new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				EventBus.getDefault().post(new AddressEven(helper.getLayoutPosition(), false));
			}
		});
		Button bt_address_delete = helper.getView(R.id.bt_address_delete);
		Button bt_address_edit = helper.getView(R.id.bt_address_edit);
		bt_address_edit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				EventBus.getDefault().post(new AddressEven(helper.getLayoutPosition(), false));
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
						if (item.getIsDefault() == 1) {
							if (getData().size() == 1) {
							} else {
								getData().get(1).setIsDefault(1);
							}
						}
						deteleAddress(item);
						remove(helper.getPosition());
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
		checkBox.setChecked(item.getIsDefault() == 1 ? true : false);
		checkBox.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				if (item.getIsDefault() != 1) {
					setDefault(item);
					checkBox.setChecked(true);
				}
			}
		});
	}

	/**
	 * 删除地址
	 *
	 * @param bean
	 */
	public void deteleAddress(AddressBean bean) {
		OkGo.<LzyResponse<List<TokenBean>>>post(Urls.URL_USER_AREA_DETELE)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("id", MyShopApplication.userId)
				.params("userName", bean.getUserName())
				.params("addressId", bean.getAddressId())
				.execute(new JsonCallback<LzyResponse<List<TokenBean>>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<List<TokenBean>>> response) {
						if (HttpUtil.handleResponse(mContext, response.body())) {
							EventBus.getDefault().post(new AddressEven(0, true));
							XToast.info("该地址删除成功");
						}
					}
					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<List<TokenBean>>> response) {
						HttpUtil.handleError(mContext, response);
					}
				});
	}

	/**
	 * 设置默认地址
	 *
	 * @param bean
	 */
	public void setDefault(AddressBean bean) {
		OkGo.<LzyResponse<List<TokenBean>>>post(Urls.URL_USER_AREA_DEFAULT)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("id", MyShopApplication.userId)
				.params("addressId", bean.getAddressId())
				.execute(new JsonCallback<LzyResponse<List<TokenBean>>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<List<TokenBean>>> response) {
						if (HttpUtil.handleResponse(mContext, response.body())) {
							EventBus.getDefault().post(new AddressEven(0, true));
							XToast.info("设置成功");
						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<List<TokenBean>>> response) {
						HttpUtil.handleError(mContext, response);
					}
				});
	}
}
