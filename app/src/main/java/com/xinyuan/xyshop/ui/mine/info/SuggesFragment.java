package com.xinyuan.xyshop.ui.mine.info;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.dialog.color.ColorDialog;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.XToast;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.badgeview.BGABadgeImageView;

import static com.xinyuan.xyshop.util.CommUtil.getInAnimationTest;
import static com.xinyuan.xyshop.util.CommUtil.getOutAnimationTest;

/**
 * Created by fx on 2017/6/27.
 * 建议fragment
 */

public class SuggesFragment extends BaseFragment {
	@BindView(R.id.toolbar_iv)
	Toolbar msg_toolbar;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;

	@BindView(R.id.ed_eva)
	EditText ed_eva;

	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;
	@BindView(R.id.iv_header_right)
	BGABadgeImageView iv_header_right;

	public static SuggesFragment newInstance() {
		SuggesFragment fragment = new SuggesFragment();
		return fragment;
	}


	@OnClick(R.id.bt_submit)
	public void onClick() {
		if (XEmptyUtils.isSpace(ed_eva.getText().toString().trim())) {
			XToast.error("还未填写建议,请填写后提交");
		} else {
			upSugges(ed_eva.getText().toString().trim());

		}

	}


	private static boolean falg = true;

	private void upSugges(String content) {
		OkGo.<LzyResponse<TokenBean>>post(Urls.URL_USER_SUGGES)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("id", MyShopApplication.userId)
				.params("content", content)
				.execute(new JsonCallback<LzyResponse<TokenBean>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						if (HttpUtil.handleResponse(mContext, response.body())) {

							ColorDialog dialog = new ColorDialog(getContext());
							dialog.setAnimationEnable(true);
							dialog.setContentText("提交成功，工作人员会在第一时间与您联系！");
							dialog.setContentImage(R.drawable.iv_info);
							dialog.setAnimationIn(getInAnimationTest(getActivity()));
							dialog.setAnimationOut(getOutAnimationTest(getActivity()));
							dialog.setPositiveListener("我知道了", new ColorDialog.OnPositiveListener() {
								@Override
								public void onClick(ColorDialog dialog) {
									ed_eva.setText("");
									dialog.dismiss();
									_mActivity.onBackPressed();

								}
							}).show();

						}
					}

					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						HttpUtil.handleError(mContext, response);
					}
				});

	}

	@Override
	public boolean onBackPressedSupport() {
		if (XEmptyUtils.isSpace(ed_eva.getText().toString())) {
			return super.onBackPressedSupport();
		} else {
			return showDialog();

		}


	}


	private boolean showDialog() {
		ColorDialog dialog = new ColorDialog(getContext());
		dialog.setTitle("退出");
		dialog.setAnimationEnable(true);
		dialog.setContentText("还未提交，确定要退出吗？");
		dialog.setAnimationIn(getInAnimationTest(getActivity()));
		dialog.setAnimationOut(getOutAnimationTest(getActivity()));
		dialog.setPositiveListener("确定", new ColorDialog.OnPositiveListener() {
			@Override
			public void onClick(ColorDialog dialog) {
				dialog.dismiss();
				ed_eva.setText("");
				_mActivity.onBackPressed();

			}
		})
				.setNegativeListener("取消", new ColorDialog.OnNegativeListener() {
					@Override
					public void onClick(ColorDialog dialog) {
						dialog.dismiss();
						falg = true;

					}
				}).show();

		return falg;
	}

	@Override
	public void initView(View rootView) {
		SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
		tv_header_center.setText("用户设置");
		CommUtil.initToolBar(getActivity(), iv_header_left, iv_header_right);


	}

	@Override
	public void initData() {

	}

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_sugges;
	}


}
