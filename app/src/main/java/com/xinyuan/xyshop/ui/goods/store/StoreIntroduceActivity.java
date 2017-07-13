package com.xinyuan.xyshop.ui.goods.store;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.entity.StoreInfoBean;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/6.
 */

public class StoreIntroduceActivity extends BaseActivity {

	@BindView(R.id.toolbar_iv)
	Toolbar store_toolbar;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;

	@BindView(R.id.iv_store_logo)
	ImageView iv_store_logo;
	@BindView(R.id.tv_store_name)
	TextView tv_store_name;
	@BindView(R.id.tv_store_type)
	TextView tv_store_type;
	@BindView(R.id.tv_store_fans)
	TextView tv_store_fans;
	@BindView(R.id.bt_store_fav)
	Button bt_store_fav;


	@BindView(R.id.tv_store_score)
	TextView tv_store_score;
	@BindView(R.id.tv_store_service_score)
	TextView tv_store_service_score;
	@BindView(R.id.tv_store_express_score)
	TextView tv_store_express_score;
	@BindView(R.id.tv_store_name2)
	TextView tv_store_name2;
	@BindView(R.id.tv_store_location)
	TextView tv_store_location;
	@BindView(R.id.tv_store_createTime)
	TextView tv_store_createTime;
	@BindView(R.id.tv_store_phone)
	TextView tv_store_phone;
	@BindView(R.id.tv_store_workTime)
	TextView tv_store_workTime;

	@BindView(R.id.fl_store_sign)
	FlexboxLayout fl_store_sign;

	@Override
	public int getLayoutId() {
		return R.layout.activity_sotre_introduce;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		final StoreInfoBean bean = (StoreInfoBean) getIntent().getSerializableExtra("storeInfo");
		if (!XEmptyUtils.isEmpty(bean)) {
			XLog.v("数据接收" + bean.toString());

			GlideImageLoader.setImage(this, bean.getStoreLogo(), iv_store_logo);
			tv_store_name.setText(bean.getStorename());
			tv_store_name2.setText(bean.getStorename());
			tv_store_type.setText("类型:" + bean.getStoreType());
			tv_store_fans.setText(bean.getFansNum());
			if (bean.isFollowStatus() != 0) {
				bt_store_fav.setText("已关注");
			}

			bt_store_fav.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (bean.isFollowStatus() == 0) {
						bt_store_fav.setText("已关注");
						XToast.info("已关注" + bean.getStorename() + "店铺");
					}
				}
			});
			if (bean.getStoreScoreText().contains("低")) {
				tv_store_score.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
			} else if (bean.getServiceScoreText().contains("低")) {
				tv_store_service_score.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
			} else if (bean.getLogisticsScoreText().contains("低")) {
				tv_store_express_score.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
			}


			tv_store_score.setText(bean.getStoreScore() + " " + bean.getStoreScoreText());
			tv_store_service_score.setText(bean.getServiceScore() + " " + bean.getServiceScoreText());
			tv_store_express_score.setText(bean.getLogisticsScore() + " " + bean.getLogisticsScoreText());

			tv_store_location.setText(bean.getStoreAddress());
			tv_store_phone.setText(bean.getStorePhone());
			tv_store_createTime.setText(bean.getCreateTime());
			tv_store_workTime.setText(bean.getWorkTime());


			AddViewHolder levelView = new AddViewHolder(this, R.layout.view_store_sign);
			ImageView leveImage = levelView.getView(R.id.ivImg);
			GlideImageLoader.setImage(StoreIntroduceActivity.this, CommUtil.getStoreSign(this, bean.getStoreLevel()), leveImage);
			fl_store_sign.addView(levelView.getCustomView());

			for (int i = 0; i < bean.getStoresign().size(); i++) {

				AddViewHolder addViewHolder = new AddViewHolder(this, R.layout.view_store_sign);
				ImageView iv = addViewHolder.getView(R.id.ivImg);
				GlideImageLoader.setImage(StoreIntroduceActivity.this, bean.getStoresign().get(i), iv);
				fl_store_sign.addView(addViewHolder.getCustomView());

			}
		}
	}


	@Override
	public void initView() {
		tv_header_center.setText("店铺介绍");

	}
}
