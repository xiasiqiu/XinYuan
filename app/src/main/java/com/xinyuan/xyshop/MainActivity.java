package com.xinyuan.xyshop;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.mvp.contract.MainContract;
import com.xinyuan.xyshop.mvp.presenter.MainPresenterImpl;
import com.xinyuan.xyshop.widget.NotSlipViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainContract.IMainView {

	@BindView(R.id.nvp_home_content)
	NotSlipViewPager mActHomeVpContent;

	@BindView(R.id.act_home_btn_home)
	Button bt_home;
	@BindView(R.id.act_home_btn_category)
	Button bt_category;
	@BindView(R.id.act_home_btn_shopcar)
	Button bt_shopcar;
	@BindView(R.id.act_home_btn_mine)
	Button bt_mine;

	/**
	 * 定义一个button数组，用于改变颜色
	 */
	private Button btns[];

	private int btnID[];

	private FragmentManager manager;
	//声明一个Presenter 对象，用于相关逻辑的处理
	private MainContract.IMainPresenter presenter;
	@Override
	public int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	public void initView() {
		ButterKnife.bind(this);
		new MainPresenterImpl(this);
		btnID = new int[]{R.id.act_home_btn_home, R.id.act_home_btn_category, R.id.act_home_btn_shopcar, R.id.act_home_btn_mine};
		btns = new Button[]{bt_home, bt_category, bt_shopcar, bt_mine};
		//默认第一个按钮被选中
		bt_home.setEnabled(false);
		presenter.initData();

	}
	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void setPresenter(MainContract.IMainPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void showState(int Sate) {

	}

	@Override
	public ViewPager getmActHomeVpContent() {
		return mActHomeVpContent;
	}

	@Override
	public FragmentManager getManager() {
		manager = getSupportFragmentManager();
		return manager;
	}


	@OnClick({R.id.act_home_btn_home, R.id.act_home_btn_category, R.id.act_home_btn_shopcar, R.id.act_home_btn_mine})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.act_home_btn_home:
				changBtnSelectedStatus(0);
				mActHomeVpContent.setCurrentItem(0);
				break;
			case R.id.act_home_btn_category:
				changBtnSelectedStatus(1);
				mActHomeVpContent.setCurrentItem(1);
				break;
			case R.id.act_home_btn_shopcar:
				changBtnSelectedStatus(2);
				mActHomeVpContent.setCurrentItem(2);
				break;
			case R.id.act_home_btn_mine:
				changBtnSelectedStatus(3);
				mActHomeVpContent.setCurrentItem(3);
				break;
		}

	}


	public void changBtnSelectedStatus(int position) {
		for (int i = 0; i < 4; i++) {
			if (i == position) {
				btns[i].setEnabled(false);
			} else {
				btns[i].setEnabled(true);
			}
		}

	}
}
