package com.xinyuan.xyshop.base;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.xinyuan.xyshop.R;
import com.youth.xframe.base.ICallback;
import com.youth.xframe.base.XActivity;
import com.youth.xframe.common.XActivityStack;
import com.youth.xframe.utils.permission.XPermission;
import com.youth.xframe.utils.statusbar.XStatusBar;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by fx on 2017/5/2 0002.
 */

public abstract class BaseActivity extends AutoLayoutActivity implements ICallback {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		XActivityStack.getInstance().addActivity(this);
		if (getLayoutId()>0) {
			setContentView(getLayoutId());
		}
		initData(savedInstanceState);
		initView();
		super.onCreate(savedInstanceState);

	}

	@Override
	public void setContentView(int layoutResID) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.setContentView(layoutResID);

		//setStatusBar();
	}


	protected void setStatusBar() {
		XStatusBar.setColor(this, getResources().getColor(R.color.colorTransparency), 0);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	/**
	 * Android M 全局权限申请回调
	 * @param requestCode
	 * @param permissions
	 * @param grantResults
	 */
	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
			grantResults) {
		XPermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		XActivityStack.getInstance().finishActivity();
	}


}
