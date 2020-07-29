package com.xinyuan.xyshop.ui.mine.info;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.analytics.MobclickAgent;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.UserInfoBean;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.common.state.LoginContext;
import com.xinyuan.xyshop.common.state.LogoutState;
import com.xinyuan.xyshop.even.LoginPageEvent;
import com.xinyuan.xyshop.even.MainFragmentStartEvent;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.dialog.color.ColorDialog;
import com.youth.xframe.cache.XCache;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.badgeview.BGABadgeImageView;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.xinyuan.xyshop.util.CommUtil.getInAnimationTest;
import static com.xinyuan.xyshop.util.CommUtil.getOutAnimationTest;

/**
 * Created by fx on 2017/6/27.
 * 设置fragment
 */

public class SettingFragment extends BaseFragment {
	@BindView(R.id.toolbar_iv)
	Toolbar msg_toolbar;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;

	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;
	@BindView(R.id.iv_header_right)
	BGABadgeImageView iv_header_right;
	@BindView(R.id.tv_user_name)
	TextView tv_user_name;
	@BindView(R.id.customer_image)
	CircleImageView customer_image;
	private static UserInfoBean userInfo;

	public static SettingFragment newInstance(UserInfoBean UserInfoBean) {
		SettingFragment fragment = new SettingFragment();
		fragment.userInfo = UserInfoBean;
		return fragment;
	}


	@OnClick({R.id.bt_setting_address, R.id.bt_setting_cache, R.id.bt_setting_sugges, R.id.bt_setting_security, R.id.bt_logout, R.id.bt_setting_about})
	public void OnClick(View view) {
		switch (view.getId()) {
			case R.id.bt_setting_address:
				start(AddressFragment.newInstance());
				break;
			case R.id.bt_setting_security:
				start(SecurityFragment.newInstance(userInfo));
				break;
			case R.id.bt_setting_cache:
				ColorDialog dialog = new ColorDialog(getContext());
				dialog.setAnimationEnable(true);
				dialog.setContentText("确定要清理APP图片缓存吗？");
				dialog.setAnimationIn(getInAnimationTest(getActivity()));
				dialog.setAnimationOut(getOutAnimationTest(getActivity()));
				dialog.setPositiveListener("清理", new ColorDialog.OnPositiveListener() {
					@Override
					public void onClick(ColorDialog dialog) {
						Glide.get(mContext).clearMemory();
						dialog.dismiss();

					}
				})
						.setNegativeListener("取消", new ColorDialog.OnNegativeListener() {
							@Override
							public void onClick(ColorDialog dialog) {
								dialog.dismiss();
							}
						}).show();
				break;
			case R.id.bt_setting_sugges:

				start(SuggesFragment.newInstance());
				break;
			case R.id.bt_setting_about:
				start(AboutFragment.newInstance());
				break;
			case R.id.bt_logout:
				XCache xCache = XCache.get(mContext);
				xCache.remove("UserBase");
				MobclickAgent.onProfileSignOff();
				LoginContext.getInstance().setState(new LogoutState());
				MyShopApplication.Token = "";
				MyShopApplication.userId = 0;
				EventBus.getDefault().post(new MainFragmentStartEvent(null, true, 200));
				EventBus.getDefault().post(new LoginPageEvent("Login", false, ""));

				_mActivity.onBackPressed();
				XToast.info("您已退出登录");


		}

	}


	@Override
	public void initView(View rootView) {
		SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
		tv_header_center.setText("用户设置");

		CommUtil.initToolBar(_mActivity, mContext, iv_header_left, iv_header_right);
		if (!XEmptyUtils.isEmpty(userInfo) && !XEmptyUtils.isEmpty(userInfo.getUserPhoto())) {
			GlideImageLoader.setCircleImageView(mContext, userInfo.getUserPhoto(), customer_image);
			tv_user_name.setText("用户名:" + userInfo.getUserName());
		}

	}

	@Override
	public void initData() {

	}

	@Override
	public void onSupportVisible() { //当fragment可见时，检查是否有新消息
		if (MyShopApplication.IsNewMsg) {
			iv_header_right.showCirclePointBadge();
		} else {
			iv_header_right.hiddenBadge();
		}
		super.onSupportVisible();
	}

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_setting;
	}

}
