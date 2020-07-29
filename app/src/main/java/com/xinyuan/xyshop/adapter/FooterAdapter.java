package com.xinyuan.xyshop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.bean.UserHistoryBean;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.common.ShopHelper;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.youth.xframe.widget.XToast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fx on 2017/7/14.
 * 足迹列表Adapter
 */

public class FooterAdapter extends BaseQuickAdapter<UserHistoryBean, BaseViewHolder> {

	public FooterAdapter(@LayoutRes int layoutResId, @Nullable List<UserHistoryBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(final BaseViewHolder helper, final UserHistoryBean item) {

		ImageView iv_good_img = helper.getView(R.id.iv_good_img);
		GlideImageLoader.setUrlImg(mContext, item.getGoodsImg(), iv_good_img);
		helper.setText(R.id.tv_good_name, mContext.getString(R.string.money_rmb) + ShopHelper.getPriceString(item.getGoodsPrice()));
		helper.setOnClickListener(R.id.iv_detele, new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				deteleFooter(item);
			}
		});
		iv_good_img.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Map<String, String> params = new HashMap();
				params.put(Constants.GOODID, String.valueOf(item.getGoodsId()));
				params.put(Constants.GOODTYPE, String.valueOf(item.getGoodType()));
				CommUtil.gotoActivity(mContext, GoodDetailActivity.class, false, params);
			}
		});
	}

	/**
	 * 删除足迹商品
	 *
	 * @param bean
	 */
	private void deteleFooter(final UserHistoryBean bean) {
		String id = "[" + bean.getfId() + "]";
		OkGo.<LzyResponse<TokenBean>>post(Urls.URL_USER_FOOT_DETELE)//
				.tag(this)//
				.headers("token", MyShopApplication.Token)//
				.params("id", MyShopApplication.userId)
				.params("footerId", id)
				.execute(new JsonCallback<LzyResponse<TokenBean>>() {
					@Override
					public void onSuccess(Response<LzyResponse<TokenBean>> response) {
						if (HttpUtil.handleResponse(mContext, response.body())) {
							getData().remove(bean);
							notifyDataSetChanged();
							XToast.info("删除成功！");
						}
					}

					@Override
					public void onError(Response<LzyResponse<TokenBean>> response) {
						HttpUtil.handleError(mContext, response);
					}
				});

	}
}
