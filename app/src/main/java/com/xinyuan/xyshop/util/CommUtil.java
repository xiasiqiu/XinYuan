package com.xinyuan.xyshop.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xinyuan.xyshop.common.Constants;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/16.
 */

public class CommUtil {
	public static File logDir;
	public static int dip2px(Context context, float dipValue) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	public static void gotoActivity(Activity poFrom, Class<?> poTo, boolean pbFinish, Map<String, String> pmExtra) {
		Intent loIntent = new Intent(poFrom, poTo);
		if (!(pmExtra == null || pmExtra.isEmpty())) {
			for (String lsKey : pmExtra.keySet()) {
				loIntent.putExtra(lsKey, (String) pmExtra.get(lsKey));
			}
		}
		if (pbFinish) {
			poFrom.finish();
		}
		poFrom.startActivity(loIntent);
	}

	public static boolean isEmpty(CharSequence cs) {
		return cs == null || cs.length() == 0;
	}
	public static String getText(EditText view) {
		return view.getText().toString();
	}

	public static String getText(TextView view) {
		return view.getText().toString();
	}

	public static String getText(Button view) {
		return view.getText().toString().trim();
	}
	public static boolean isNotEmpty(CharSequence... cs) {
		for (CharSequence c : cs) {
			if (isEmpty(c)) {
				return false;
			}
		}
		return true;
	}
	public static  void createDir() {
		if ("mounted".equals(Environment.getExternalStorageState())) {
			File rootDir = new File(Constants.APP_DIR);
			if (!rootDir.exists()) {
				rootDir.mkdirs();
			}
			File cacheDir = new File(Constants.CACHE_DIR);
			if (!cacheDir.exists()) {
				cacheDir.mkdirs();
			}
			logDir = new File(Constants.LOG_DIR);
			if (!logDir.exists()) {
				logDir.mkdirs();
			}
		}
	}

	public static int getScreenWeight(Context context) {
		if (context == null) {
			return 0;
		}
		DisplayMetrics dm = new DisplayMetrics();
		return context.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
	}
}
