package com.xinyuan.xyshop;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.xinyuan.xyshop.base.BaseActivity;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.ui.catrgory.CategoryFragment;
import com.xinyuan.xyshop.ui.home.HomeFragment;
import com.xinyuan.xyshop.ui.mine.MineFragment;
import com.xinyuan.xyshop.ui.shopcar.ShopCarFragment;
import com.xinyuan.xyshop.widget.NotSlipViewPager;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

	private static final String CURRENT_FRAGMENT = "STATE_FRAGMENT_SHOW";
	@BindView(R.id.main_content)
	FrameLayout main_content;

	@BindView(R.id.act_home_btn_home)
	Button bt_home;
	@BindView(R.id.act_home_btn_category)
	Button bt_category;
	@BindView(R.id.act_home_btn_shopcar)
	Button bt_shopcar;
	@BindView(R.id.act_home_btn_mine)
	Button bt_mine;

	private FragmentManager fragmentManager;
	private int currentIndex = 0;
	private List<Fragment> fragments = new ArrayList<>();
	private Fragment currentFragment = new Fragment();
	private Button btns[];

	Bundle savedInstanceState;

	@Override
	public int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	public void initView() {
		btns = new Button[]{bt_home, bt_category, bt_shopcar, bt_mine};
		fragmentManager = getSupportFragmentManager();
		if (savedInstanceState != null) { // “内存重启”时调用

			//获取“内存重启”时保存的索引下标
			currentIndex = savedInstanceState.getInt(CURRENT_FRAGMENT, 0);

			fragments.removeAll(fragments);
			fragments.add(fragmentManager.findFragmentByTag(0 + ""));
			fragments.add(fragmentManager.findFragmentByTag(1 + ""));
			fragments.add(fragmentManager.findFragmentByTag(2 + ""));
			fragments.add(fragmentManager.findFragmentByTag(3 + ""));
			//恢复fragment页面
			restoreFragment();
		} else {      //正常启动时调用
			fragments.add(new HomeFragment());
			fragments.add(new CategoryFragment());
			fragments.add(new ShopCarFragment());
			fragments.add(new MineFragment());
			showFragment();
		}
	}


	@Override
	public void initData(Bundle savedInstanceState) {

		this.savedInstanceState = savedInstanceState;

	}


	@OnClick({R.id.act_home_btn_home, R.id.act_home_btn_category, R.id.act_home_btn_shopcar, R.id.act_home_btn_mine})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.act_home_btn_home:
				changBtnSelectedStatus(0);
				currentIndex = 0;
				break;
			case R.id.act_home_btn_category:
				currentIndex = 1;
				changBtnSelectedStatus(1);
				break;
			case R.id.act_home_btn_shopcar:
				currentIndex = 2;
				changBtnSelectedStatus(2);
				break;
			case R.id.act_home_btn_mine:
				currentIndex = 3;
				changBtnSelectedStatus(3);
				break;
		}
		showFragment();

	}

	/**
	 * 使用show() hide()切换页面
	 * 显示fragment
	 */
	private void showFragment() {

		FragmentTransaction transaction = fragmentManager.beginTransaction();


		//如果之前没有添加过
		if (!fragments.get(currentIndex).isAdded()) {
			transaction
					.hide(currentFragment)
					.add(R.id.main_content, fragments.get(currentIndex), "" + currentIndex);  //第三个参数为添加当前的fragment时绑定一个tag
			XLog.v("当前显示的Fragment为" + currentIndex);
		} else {
			XLog.v("当前显示的Fragment为" + currentIndex);
			transaction
					.hide(currentFragment)
					.show(fragments.get(currentIndex));
		}

		currentFragment = fragments.get(currentIndex);
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

		transaction.commitAllowingStateLoss();


	}

	/**
	 * 恢复fragment
	 */
	private void restoreFragment() {


		FragmentTransaction mBeginTreansaction = fragmentManager.beginTransaction();

		for (int i = 0; i < fragments.size(); i++) {

			if (i == currentIndex) {
				mBeginTreansaction.show(fragments.get(i));
			} else {
				mBeginTreansaction.hide(fragments.get(i));
			}

		}

		mBeginTreansaction.commit();

		//把当前显示的fragment记录下来
		currentFragment = fragments.get(currentIndex);

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {

		//“内存重启”时保存当前的fragment名字
		outState.putInt(CURRENT_FRAGMENT, currentIndex);
		super.onSaveInstanceState(outState);
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
