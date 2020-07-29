package com.xinyuan.xyshop.ui.welcome;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.xinyuan.xyshop.MainActivity;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.CommonFragmentPagerAdapter;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.util.CommUtil;
import com.youth.xframe.utils.permission.XPermission;

import java.util.ArrayList;

import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator;

public class GuideActivity extends BaseActivity {
	@BindView(R.id.guide_vp)
	ViewPager viewPager;
	@BindView(R.id.indicator)
	CircleIndicator indicator;
	@BindView(R.id.bt_start)
	Button bt_start;
	CommonFragmentPagerAdapter adapter;
	private ArrayList<Fragment> fragmentList = new ArrayList<>();

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.activity_guide;
	}

	@Override
	public void initView() {
		for (int i = 1; i < 5; i++) {
			fragmentList.add(GuideFragment.newInstance(i));
		}
		adapter = new CommonFragmentPagerAdapter(this.getSupportFragmentManager(), fragmentList);
		viewPager.setAdapter(adapter);
		indicator.setViewPager(viewPager);
		checkpermission();
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
				if (position == 3) {
					indicator.setVisibility(View.GONE);
					bt_start.setVisibility(View.VISIBLE);
					bt_start.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							if (CommUtil.isFastClick()) {
								CommUtil.gotoActivity(GuideActivity.this, MainActivity.class, true, null);

							}
						}
					});
				} else {
					indicator.setVisibility(View.VISIBLE);
					bt_start.setVisibility(View.GONE);
				}
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
	}

	@Override
	public void initData() {

	}

	public void checkpermission() {
		XPermission.requestPermissions(this, 100, new String[]{Manifest.permission
				.READ_PHONE_STATE, Manifest.permission
				.READ_EXTERNAL_STORAGE, Manifest.permission
				.CAMERA, Manifest.permission
				.INTERNET, Manifest.permission
				.CALL_PHONE}, new XPermission.OnPermissionListener() {
			//权限申请成功时调用
			@Override
			public void onPermissionGranted() {

			}

			//权限被用户禁止时调用
			@Override
			public void onPermissionDenied() {
				//给出友好提示，并且提示启动当前应用设置页面打开权限
				XPermission.showTipsDialog(GuideActivity.this);
			}
		});
	}


}
