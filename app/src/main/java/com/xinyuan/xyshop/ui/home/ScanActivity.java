package com.xinyuan.xyshop.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseActivity;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import butterknife.BindView;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.QRCodeDecoder;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

/**
 * Created by Administrator on 2017/7/3.
 */

public class ScanActivity extends BaseActivity implements QRCodeView.Delegate {
	@BindView(R.id.toolbar_tv)
	Toolbar toolbar_tv;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.zxingview)
	ZXingView mZxingview;

	@Override
	public int getLayoutId() {
		return R.layout.activity_scan;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		mZxingview.setDelegate(this);
		tv_header_center.setText("二维码扫描");
	}

	@Override
	public void onScanQRCodeSuccess(String result) {
		XToast.info(result);
		XLog.v("扫码码结果" + result);
		Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(200);
		mZxingview.startSpot();
		finish();
	}


	@Override
	public void onScanQRCodeOpenCameraError() {
		XToast.error("打开相机出错！", 3000);
	}

	@Override
	protected void onStart() {
		super.onStart();
		mZxingview.startCamera();
		mZxingview.startSpotAndShowRect();
	}

	@Override
	protected void onStop() {
		super.onStop();
		mZxingview.stopCamera();
	}

	@Override
	protected void onDestroy() {
		mZxingview.onDestroy();
		super.onDestroy();

	}


}
