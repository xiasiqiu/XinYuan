package com.xinyuan.xyshop.base;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.xinyuan.xyshop.R;
import com.youth.xframe.base.XActivity;
import com.youth.xframe.utils.permission.XPermission;
import com.youth.xframe.utils.statusbar.XStatusBar;

/**
 * Created by fx on 2017/5/2 0002.
 */

public abstract class BaseActivity extends XActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
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


}
