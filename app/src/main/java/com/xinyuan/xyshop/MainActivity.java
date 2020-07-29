package com.xinyuan.xyshop;

import android.content.Intent;

import com.umeng.socialize.UMShareAPI;
import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.state.LoginContext;
import com.xinyuan.xyshop.http.UpdateAppHttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.youth.xframe.utils.log.XLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;


/**
 * Created by fx on 2017/4/23.
 * 首页Activity
 */
public class MainActivity extends BaseActivity<BasePresenter> {
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        if (findFragment(MainFragment.class) == null) {
            //fragmentation框架里加载主Fragment
            loadRootFragment(R.id.main_content, MainFragment.newInstance());
        }
        setFragmentAnimator(new DefaultHorizontalAnimator());

    }

    @Override
    public void initData() {
        checkAppVersion(); //检查是否有新版本
        LoginContext.getInstance().checkNewMsg(MainActivity.this); //检查是否有新消息

    }

    /**
     * 检查APP版本更新
     */
    public void checkAppVersion() {
        String path = Constants.APK_DIR;
        Map<String, String> params = new HashMap<String, String>();
        params.put("serverVersion", BuildConfig.VERSION_NAME);
        new UpdateAppManager
                .Builder()
                //必须设置，当前Activity
                .setActivity(this)
                //必须设置，实现httpManager接口的对象
                .setHttpManager(new UpdateAppHttpUtil())
                //必须设置，更新地址
                .setUpdateUrl(Urls.URL_APP_UPDATE)
                .setPost(true)
                .setParams(params)
                .hideDialogOnDownloading(false)
                .setTopPic(R.drawable.update)
                .setThemeColor(0xffffac5d)
                .setTargetPath(path)
                .build()
                .checkNewApp(new UpdateCallback() {
                    @Override
                    protected UpdateAppBean parseJson(String json) {
                        UpdateAppBean updateAppBean = new UpdateAppBean();
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            updateAppBean
                                    //（必须）是否更新Yes,No
                                    .setUpdate(jsonObject.optBoolean("isUpdate") ? "Yes" : "No")
                                    //（必须）新版本号，
                                    .setNewVersion(jsonObject.optString("serverVersion"))
                                    //（必须）下载地址
                                    .setApkFileUrl(jsonObject.optString("updateUrl"))
//                                  //更新日志
                                    .setUpdateLog(jsonObject.optString("upgradeInfo"))
                                    //大小，不设置不显示大小，可以不设置
                                    .setTargetSize(jsonObject.optString("updateSize"))
                                    //是否强制更新，可以不设置
                                    .setConstraint(jsonObject.optBoolean("lastForce"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return updateAppBean;
                    }

                    @Override
                    protected void hasNewApp(UpdateAppBean updateApp, UpdateAppManager updateAppManager) {
                        updateAppManager.showDialogFragment();
                    }

                    /**
                     * 网络请求之前
                     */
                    @Override
                    public void onBefore() {
                        //CProgressDialogUtils.showProgressDialog(MainActivity.this);
                    }

                    /**
                     *
                     * 网路请求之后
                     */
                    @Override
                    public void onAfter() {
                        //CProgressDialogUtils.cancelProgressDialog(MainActivity.this);
                    }

                    /**
                     * 没有新版本
                     */
                    @Override
                    public void noNewApp() {
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }

}
