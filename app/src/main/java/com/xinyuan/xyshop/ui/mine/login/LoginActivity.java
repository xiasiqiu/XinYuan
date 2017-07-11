package com.xinyuan.xyshop.ui.mine.login;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.xinyuan.xyshop.MainFragment;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.even.LoginPageEvent;
import com.xinyuan.xyshop.ui.goods.GoodBusBean;
import com.youth.xframe.utils.log.XLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;

/**
 * Created by Administrator on 2017/6/30.
 */

public class LoginActivity extends BaseActivity {


	@Override
	public int getLayoutId() {
		return R.layout.activity_login;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		if (findFragment(LoginFragment.class) == null) {
			loadRootFragment(R.id.login_content, LoginFragment.newInstance());
		}
		setFragmentAnimator(new DefaultHorizontalAnimator());

	}

}
