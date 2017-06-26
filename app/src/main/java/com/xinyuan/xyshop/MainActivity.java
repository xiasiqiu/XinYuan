package com.xinyuan.xyshop;

import android.Manifest;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.ui.catrgory.CategoryFragment;
import com.xinyuan.xyshop.ui.goods.GoodBusBean;
import com.xinyuan.xyshop.ui.home.HomeFragment;
import com.xinyuan.xyshop.ui.home.UserBusBean;
import com.xinyuan.xyshop.ui.mine.MineFragment;
import com.xinyuan.xyshop.ui.shopcar.ShopCarFragment;
import com.xinyuan.xyshop.widget.NotSlipViewPager;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.utils.permission.XPermission;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class MainActivity extends BaseActivity {

	private static final String CURRENT_FRAGMENT = "STATE_FRAGMENT_SHOW";


	@Override
	public int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		getPer();
		if (findFragment(MainFragment.class) == null) {
			loadRootFragment(R.id.main_content, MainFragment.newInstance());
		}
		setFragmentAnimator(new DefaultHorizontalAnimator());
	}

	private void getPer() {
		XPermission.requestPermissions(this, 100, new String[]{Manifest.permission
				.CALL_PHONE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.GET_ACCOUNTS, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE}, new XPermission.OnPermissionListener() {
			//权限申请成功时调用
			@Override
			public void onPermissionGranted() {

			}

			//权限被用户禁止时调用
			@Override
			public void onPermissionDenied() {
				//给出友好提示，并且提示启动当前应用设置页面打开权限
				XPermission.showTipsDialog(getApplicationContext());
			}
		});
	}


}
