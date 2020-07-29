package com.xinyuan.xyshop.ui.home;

import android.Manifest;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.ScanBean;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.callback.DialogCallback;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.common.state.LoginContext;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.even.LoginPageEvent;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.ui.goods.detail.GoodDetailActivity;
import com.xinyuan.xyshop.ui.goods.store.StoreActivity;
import com.xinyuan.xyshop.ui.mine.login.LoginActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.widget.dialog.color.ColorDialog;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.utils.permission.XPermission;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

import static com.xinyuan.xyshop.util.CommUtil.getInAnimationTest;
import static com.xinyuan.xyshop.util.CommUtil.getOutAnimationTest;

/**
 * Created by fx on 2017/7/3.
 * 二维码扫码
 */

public class ScanActivity extends BaseActivity implements QRCodeView.Delegate {
    @BindView(R.id.toolbar_tv)
    Toolbar toolbar_tv;
    @BindView(R.id.tv_header_center)
    TextView tv_header_center;
    @BindView(R.id.zxingview)
    ZXingView mZxingview;
    @BindView(R.id.bt_rescan)
    Button bt_rescan;

    private String loginKey;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_scan;
    }

    @Override
    public void initView() {
        mZxingview.setDelegate(this);
        tv_header_center.setText("二维码扫描");
    }

    public MediaPlayer createLocalMp3() {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.wechat);
        mp.stop();
        return mp;
    }

    @Override
    public void initData() {

    }

    private MediaPlayer mediaPlayer;

    @Override
    public void onScanQRCodeSuccess(String result) {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(200);
        mZxingview.startSpot();
        mZxingview.setVisibility(View.GONE);
        getType(result);
        bt_rescan.setVisibility(View.VISIBLE);
    }

    private void getType(String json) {
        final ScanBean scanBean = new ScanBean();
        json = json.substring(1, json.length() - 1);
        XLog.v(json);
        try {
            JSONObject jsonObject = new JSONObject(json);
            scanBean.setKey(jsonObject.optString("key"));
            scanBean.setType(jsonObject.optString("type"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (scanBean.getType().equals("login")) {
            ColorDialog dialog = new ColorDialog(this);
            dialog.setTitle("退出");
            dialog.setAnimationEnable(true);
            dialog.setContentText("是否要登录PC商城？");
            dialog.setAnimationIn(getInAnimationTest(this));
            dialog.setAnimationOut(getOutAnimationTest(this));
            dialog.setPositiveListener("登录", new ColorDialog.OnPositiveListener() {
                @Override
                public void onClick(ColorDialog dialog) {
                    dialog.dismiss();
                    loginKey = scanBean.getKey();
                    loginPc();

                }
            })
                    .setNegativeListener("取消", new ColorDialog.OnNegativeListener() {
                        @Override
                        public void onClick(ColorDialog dialog) {
                            dialog.dismiss();


                        }
                    }).show();
        } else if (scanBean.getType().equals("goods")) {
            Map<String, String> params = new HashMap();
            params.put(Constants.GOODID, scanBean.getKey());
            params.put(Constants.GOODTYPE, Constants.GOOD_NORMAL);
            CommUtil.gotoActivity(this, GoodDetailActivity.class, false, params);
            finish();
        } else if (scanBean.getType().equals(Constants.STORE)) {
            Map<String, String> params = new HashMap();
            params.put(Constants.STOREID, scanBean.getKey());
            CommUtil.gotoActivity(this, StoreActivity.class, false, params);
        }
    }

    @OnClick({R.id.bt_rescan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_rescan:
                bt_rescan.setVisibility(View.GONE);
                mZxingview.setVisibility(View.VISIBLE);
                mZxingview.setDelegate(this);
                break;
        }

    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        XToast.error("打开相机出错！", 3000);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEventBus(this);
        XPermission.requestPermissions(this, 100, new String[]{Manifest.permission
                .WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, new XPermission.OnPermissionListener() {
            //权限申请成功时调用
            @Override
            public void onPermissionGranted() {
                mZxingview.startCamera();
                mZxingview.startSpotAndShowRect();

            }

            //权限被用户禁止时调用
            @Override
            public void onPermissionDenied() {

                XPermission.showTipsDialog(ScanActivity.this);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        mZxingview.stopCamera();
    }

    @Override
    protected void onDestroy() {
        mZxingview.onDestroy();
        unregisterEventBus(this);
        super.onDestroy();

    }

    @OnClick(R.id.iv_header_left)
    public void onBack() {
        finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //当登录后自动跳转回来
    public void onEvent(LoginPageEvent event) {
        if (event.isLoginStatus()) {
            loginPc();
        }
    }

    private void loginPc() {
        if (LoginContext.isLogin) {
            OkGo.<LzyResponse<TokenBean>>post(Urls.URL_LOGIN_SCAN)
                    .params("token", MyShopApplication.Token)
                    .params("user_id", MyShopApplication.userId)
                    .params("key", loginKey)
                    .execute(new DialogCallback<LzyResponse<TokenBean>>(this, "登录中...") {
                        @Override
                        public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
                            if (HttpUtil.handleResponse(ScanActivity.this, response.body())) {
                                XToast.info("登录成功");
                                finish();

                            }
                        }

                        @Override
                        public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
                            HttpUtil.handleError(ScanActivity.this, response);
                        }
                    });
        } else {
            CommUtil.gotoActivity(this, LoginActivity.class, false, null);
        }


    }


}
