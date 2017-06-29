package com.xinyuan.xyshop.ui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.xinyuan.xyshop.MainActivity;
import com.xinyuan.xyshop.R;
import com.youth.xframe.utils.XNetworkUtils;
import com.youth.xframe.utils.XPreferencesUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

public class WelcomeActivity extends AppCompatActivity {

	private boolean isInstall = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_welcome);
		getNet();
		if (!getNet()) {
			return;
		}

		if (XPreferencesUtils.contains("isInstall")) {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
					startActivity(intent);
					finish();
				}
			}, 2000);
		} else {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					Intent intent = new Intent(WelcomeActivity.this, GuideActivity.class);
					startActivity(intent);
					finish();
				}
			}, 2000);
			XPreferencesUtils.put("isInstall", isInstall);
		}


	}

	private boolean getNet() {
		XLog.v("网络：" + XNetworkUtils.isAvailable());
		if (!XNetworkUtils.isAvailable()) {
			XToast.error("没有网络连接，请检查是否连接至网络或WIFI", 3000);
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					finish();
				}
			}, 5000);

			return false;

		} else {
			return true;
		}
	}

}
