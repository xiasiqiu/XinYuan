package com.xinyuan.xyshop.ui.mine;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {

	@BindView(R.id.bt_login)
	Button bt_login;
	private int resultCode = 0;

	@Override
	public int getLayoutId() {
		return R.layout.activity_login;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		ButterKnife.bind(this);
		bt_login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent mIntent = new Intent();
				mIntent.putExtra("userId", "11231231000");
				setResult(resultCode, mIntent);
				finish();
			}
		});
	}
}

