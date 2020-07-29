package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.bean.CouponBean;
import com.xinyuan.xyshop.bean.StoreCouponBean;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.youth.xframe.widget.XToast;

import java.util.List;

/**
 * Created by fx on 2017/6/6.
 * 店铺优惠券列表Adapter
 */

public class VoucherAdapter extends BaseQuickAdapter<StoreCouponBean, BaseViewHolder> {

	public static int chosesId;
	public List<StoreCouponBean> list;


	public VoucherAdapter(@LayoutRes int layoutResId, @Nullable List<StoreCouponBean> data) {
		super(layoutResId, data);
		this.list = data;
	}

	@Override
	protected void convert(final BaseViewHolder helper, final StoreCouponBean item) {
		helper.setText(R.id.tv_coupon_name, item.getCouponName());
		final CheckBox bt_store_fav = helper.getView(R.id.bt_store_fav);
		if (item.getType().equals("5")) {
			helper.setText(R.id.tv_coupontype, mContext.getString(R.string.red_store));
		} else {
			helper.setText(R.id.tv_coupontype, mContext.getString(R.string.coupon_store));

		}
		if (item.getStatus().equals("1")) {
			bt_store_fav.setChecked(true);
			bt_store_fav.setBackground(mContext.getResources().getDrawable(R.color.bg_gray));
			bt_store_fav.setTextColor(mContext.getResources().getColor(R.color.tv_hint));
			bt_store_fav.setText(mContext.getString(R.string.text_choesed));
		} else {
			bt_store_fav.setChecked(false);
			bt_store_fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
					if (b) {
						if (item.getType().equals("5")) {
							getRed(item);
						} else {
							getCoupon(item);
						}
						bt_store_fav.setBackground(mContext.getResources().getDrawable(R.color.bg_gray));
						bt_store_fav.setTextColor(mContext.getResources().getColor(R.color.tv_hint));
						bt_store_fav.setText(mContext.getString(R.string.text_choesed));

					}
				}
			});
		}

	}


	private void getRed(final StoreCouponBean bean) {
		OkGo.<LzyResponse<TokenBean>>post(Urls.URL_STORE_GET_RED)//
				.tag(this)//
				.headers("token", MyShopApplication.Token)//
				.params("userId", MyShopApplication.userId)
				.params("redPacketId", bean.getCouponId())
				.execute(new JsonCallback<LzyResponse<TokenBean>>() {
					@Override
					public void onSuccess(Response<LzyResponse<TokenBean>> response) {
						if (HttpUtil.handleResponse(mContext, response.body())) {
							XToast.info("领取成功");
						}
					}

					@Override
					public void onError(Response<LzyResponse<TokenBean>> response) {
						HttpUtil.handleError(mContext, response);
					}
				});

	}

	private void getCoupon(final StoreCouponBean bean) {
		OkGo.<LzyResponse<TokenBean>>post(Urls.URL_STORE_GET_COUPON)//
				.tag(this)//
				.headers("token", MyShopApplication.Token)//
				.params("userId", MyShopApplication.userId)
				.params("couponId", bean.getCouponId())
				.execute(new JsonCallback<LzyResponse<TokenBean>>() {
					@Override
					public void onSuccess(Response<LzyResponse<TokenBean>> response) {
						if (HttpUtil.handleResponse(mContext, response.body())) {
							XToast.info("领取成功");

						}
					}

					@Override
					public void onError(Response<LzyResponse<TokenBean>> response) {
						HttpUtil.handleError(mContext, response);
					}
				});

	}
}
