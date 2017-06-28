package com.xinyuan.xyshop.ui.mine.order.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexboxLayout;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.ui.mine.pro.AccountConFragment;
import com.xinyuan.xyshop.util.CallImageLoader;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.BottomPopupImage;
import com.xinyuan.xyshop.widget.StarBar;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.inter.IHandlerCallBack;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/27.
 */

public class EvaActivity extends BaseActivity {
	@BindView(R.id.toolbar_iv)
	Toolbar toolbar_iv;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;


	@BindView(R.id.ed_eva)
	EditText ed_eva;
	@BindView(R.id.rl_eva_pic_pull)
	RelativeLayout rl_eva_pic_pull;

	@BindView(R.id.rl_eva_star)
	RelativeLayout rl_eva_star;


	@BindView(R.id.startBar1)
	StarBar starBar1;
	@BindView(R.id.startBar2)
	StarBar starBar2;
	@BindView(R.id.startBar3)
	StarBar starBar3;
	private String mTitle;
	private IHandlerCallBack iHandlerCallBack;
	private GalleryConfig galleryConfig;
	private List<String> path = new ArrayList<>();

	@Override
	public int getLayoutId() {
		return R.layout.fragment_eva;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		if (toolbar_iv != null) {
			XLog.v("余额操作加载Tooolbar");
			SystemBarHelper.immersiveStatusBar(this, 0); //设置状态栏透明
			SystemBarHelper.setHeightAndPadding(this, toolbar_iv);
			tv_header_center.setText(mTitle);
		}

		initGallery();
		galleryConfig = new GalleryConfig.Builder()
				.imageLoader(new CallImageLoader())    // ImageLoader 加载框架（必填）
				.iHandlerCallBack(iHandlerCallBack)     // 监听接口（必填）
				.provider("com.xyshop.fileprovider")   // provider(必填)
				.pathList(path)                         // 记录已选的图片
				.multiSelect(true, 5)                   // 配置是否多选的同时 配置多选数量   默认：false ， 9
				.maxSize(5)                             // 配置多选时 的多选数量。    默认：9
				.isShowCamera(true)                     // 是否现实相机按钮  默认：false
				.filePath("/Gallery/Pictures")          // 图片存放路径
				.build();

		getStart();
	}

	private void getStart() {
		starBar1.setIntegerMark(true);
		starBar2.setIntegerMark(true);
		starBar3.setIntegerMark(true);
		starBar1.setOnStarChangeListener(new StarBar.OnStarChangeListener() {
			@Override
			public void onStarChange(float mark) {
				XLog.v("1分数" + mark);
			}
		});
		starBar2.setOnStarChangeListener(new StarBar.OnStarChangeListener() {
			@Override
			public void onStarChange(float mark) {
				XLog.v("2分数" + mark);
			}
		});
		starBar3.setOnStarChangeListener(new StarBar.OnStarChangeListener() {
			@Override
			public void onStarChange(float mark) {
				XLog.v("3分数" + mark);
			}
		});
	}

	@OnClick({R.id.rl_eva_pic_pull})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.rl_eva_pic_pull:
				path.clear();
				XLog.list(path);
				galleryConfig.getBuilder().isOpenCamera(false).build();
				GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(EvaActivity.this);
				break;
		}

	}

	private void intoView(List<String> path) {

	}

	private void initGallery() {
		iHandlerCallBack = new IHandlerCallBack() {
			@Override
			public void onStart() {
				XLog.i("onStart: 开启");
			}

			@Override
			public void onSuccess(List<String> photoList) {
				XLog.i("onSuccess: 返回数据");
				path = photoList;
				XLog.list(path);
				intoView(path);

			}

			@Override
			public void onCancel() {
				XLog.i("onCancel: 取消");
			}

			@Override
			public void onFinish() {
				XLog.i("onFinish: 结束");
			}

			@Override
			public void onError() {
				XLog.i("onError: 出错");
			}
		};
	}
}
