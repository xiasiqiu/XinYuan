package com.xinyuan.xyshop.ui.mine.info.security;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.callback.DialogCallback;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.EditTextWithDel;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.XToast;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fx on 2017/8/2.
 * 修改密码Fragment
 */

public class ChangePassFragment extends BaseFragment<BasePresenter> {
    @BindView(R.id.toolbar_iv)
    Toolbar msg_toolbar;
    @BindView(R.id.iv_header_right)
    ImageView iv_header_right;
    @BindView(R.id.iv_header_left)
    ImageView iv_header_left;
    @BindView(R.id.tv_header_center)
    TextView tv_header_center;

    @BindView(R.id.ed_password_new)
    EditTextWithDel ed_password_new;
    @BindView(R.id.ed_password_new_re)
    EditTextWithDel ed_password_new_re;

    private String key;

    public static ChangePassFragment newInstance(String key) {
        ChangePassFragment changePassFragment = new ChangePassFragment();
        changePassFragment.key = key;
        return changePassFragment;
    }


    @Override
    public void initView(View rootView) {
        tv_header_center.setText("设置密码");
        SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
        SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
        CommUtil.initToolBar(getActivity(), iv_header_left, iv_header_right);
    }

    @Override
    public void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_mine_change_pass;
    }

    @OnClick(R.id.bt_login)
    public void onClick() {
        String pass_new = ed_password_new.getText().toString().trim();
        String pass_new_re = ed_password_new_re.getText().toString().trim();

            if (XEmptyUtils.isSpace(pass_new)) {
                XToast.error("请输入新密码！");
            } else {
                if (XEmptyUtils.isSpace(pass_new_re)) {
                    XToast.error("请重复输入新密码！");
                } else {
                    if (!pass_new.equals(pass_new_re)) {
                        XToast.error("新密码两次输入不一致！");
                    } else {
                        changePass(pass_new);
                    }
                }

            }

    }


    private void changePass(String newPass) {
        OkGo.<LzyResponse<TokenBean>>post(Urls.URL_UPDATE_USER)
                .tag(this)
                .headers("token", MyShopApplication.Token)
                .params("id", MyShopApplication.userId)
                .params("password", newPass)
                .params("key", key)
                .execute(new DialogCallback<LzyResponse<TokenBean>>(getActivity(), "修改中...") {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
                        if (HttpUtil.handleResponse(getContext(), response.body())) {
                            XToast.info("修改成功");
                            _mActivity.onBackPressed();
                        }

                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
                        HttpUtil.handleError(mContext, response);
                    }
                });
    }
}
