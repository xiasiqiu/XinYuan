package com.xinyuan.xyshop.ui.goods.detail.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.even.GoodBusEven;
import com.xinyuan.xyshop.model.GoodDetailModels;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fx on 2017/5/18.
 * 商品评价界面
 */

public class GoodsEvaFragment extends BaseFragment<BasePresenter> {

	@BindView(R.id.ll_eva_all)
	LinearLayout ll_eva_all;
	@BindView(R.id.ll_eva_good)
	LinearLayout ll_eva_good;
	@BindView(R.id.ll_eva_med)
	LinearLayout ll_eva_med;
	@BindView(R.id.ll_eva_bad)
	LinearLayout ll_eva_bad;
	@BindView(R.id.ll_eva_pic)
	LinearLayout ll_eva_pic;

	@BindView(R.id.tv_eva_all_num)
	TextView tv_eva_all_num;
	@BindView(R.id.tv_eva_good_num)
	TextView tv_eva_good_num;
	@BindView(R.id.tv_eva_med_num)
	TextView tv_eva_med_num;
	@BindView(R.id.tv_eva_bad_num)
	TextView tv_eva_bad_num;
	@BindView(R.id.tv_eva_pic_num)
	TextView tv_eva_pic_num;

	@BindView(R.id.tv_eva_all)
	TextView tv_eva_all;
	@BindView(R.id.tv_eva_good)
	TextView tv_eva_good;
	@BindView(R.id.tv_eva_med)
	TextView tv_eva_med;
	@BindView(R.id.tv_eva_bad)
	TextView tv_eva_bad;
	@BindView(R.id.tv_eva_pic)
	TextView tv_eva_pic;


	private static final String CURRENT_FRAGMENT = "STATE_FRAGMENT_SHOW";
	@BindView(R.id.good_eva_content)
	FrameLayout good_eva_content;


	private int currentIndex = 0;
	private List<Fragment> fragments = new ArrayList<>();
	private Fragment currentFragment = new Fragment();
	Bundle savedInstanceState;
	private static boolean VIEW_INIT = true;
	private FragmentManager fragmentManager;
	private GoodCommentContentFragment commentContentFragment;
	private TextView tvs[];

	@Override
	public void initView(View rootView) {
		tv_eva_all_num.setEnabled(false);
		tv_eva_all.setEnabled(false);

		fragmentManager = getChildFragmentManager();
		if (savedInstanceState != null) { // “内存重启”时调用
			//获取“内存重启”时保存的索引下标
			currentIndex = savedInstanceState.getInt(CURRENT_FRAGMENT, 0);

			fragments.removeAll(fragments);
			fragments.add(fragmentManager.findFragmentByTag(0 + ""));
			fragments.add(fragmentManager.findFragmentByTag(1 + ""));
			fragments.add(fragmentManager.findFragmentByTag(2 + ""));
			fragments.add(fragmentManager.findFragmentByTag(3 + ""));
			fragments.add(fragmentManager.findFragmentByTag(4 + ""));
			//恢复fragment页面
			restoreFragment();
		} else {      //正常启动时调用
			fragments.add(commentContentFragment.getInstance(""));
			fragments.add(commentContentFragment.getInstance("good"));
			fragments.add(commentContentFragment.getInstance("normal"));
			fragments.add(commentContentFragment.getInstance("low"));
			fragments.add(commentContentFragment.getInstance("img"));
			showFragment();
		}
		tvs = new TextView[]{tv_eva_all, tv_eva_all_num, tv_eva_good, tv_eva_good_num, tv_eva_med, tv_eva_med_num, tv_eva_bad, tv_eva_bad_num, tv_eva_pic, tv_eva_pic_num};
	}

	@Override
	public void initData() {
		this.savedInstanceState = savedInstanceState;

	}

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_good_comment;
	}


	@Subscribe(threadMode = ThreadMode.MAIN)
	//第2步:注册一个在后台线程执行的方法,用于接收事件
	public void onGoodEvent(GoodBusEven goodBusEven) {//参数必须是ClassEvent类型, 否则不会调用此方法
		if (goodBusEven.getFlag().equals(GoodBusEven.GoodEvaluate)) {
			GoodDetailModels.GoodEvaNum comment = (GoodDetailModels.GoodEvaNum) goodBusEven.getObj();
			tv_eva_all_num.setText("" + comment.getGoodsEvaluates());
			tv_eva_good_num.setText("" + comment.getGoodsEvaluatesGood());
			tv_eva_med_num.setText("" + comment.getGoodsEvaluatesNormal());
			tv_eva_bad_num.setText("" + comment.getGoodsEvaluatesLow());
			tv_eva_pic_num.setText("" + comment.getGoodsEvaluatesImg());
		}

	}

	@OnClick({R.id.ll_eva_all, R.id.ll_eva_good, R.id.ll_eva_med, R.id.ll_eva_bad, R.id.ll_eva_pic})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.ll_eva_all:
				currentIndex = 0;
				GoodCommentContentFragment.page = 1;
				changBtnSelectedStatus(0);

				break;
			case R.id.ll_eva_good:
				currentIndex = 1;
				GoodCommentContentFragment.page = 1;
				changBtnSelectedStatus(1);
				break;
			case R.id.ll_eva_med:
				currentIndex = 2;
				GoodCommentContentFragment.page = 1;

				changBtnSelectedStatus(2);
				break;
			case R.id.ll_eva_bad:
				currentIndex = 3;
				GoodCommentContentFragment.page = 1;

				changBtnSelectedStatus(3);
				break;
			case R.id.ll_eva_pic:
				currentIndex = 4;
				GoodCommentContentFragment.page = 1;

				changBtnSelectedStatus(4);
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
					.add(R.id.good_eva_content, fragments.get(currentIndex), "" + currentIndex);  //第三个参数为添加当前的fragment时绑定一个tag

		} else {

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
	public void onSaveInstanceState(Bundle outState) {

		//“内存重启”时保存当前的fragment名字
		outState.putInt(CURRENT_FRAGMENT, currentIndex);
		super.onSaveInstanceState(outState);
	}

	public void changBtnSelectedStatus(int position) {
		int index = position * 2;
		int next = index + 1;
		for (int i = 0; i < 9; i++) {
			if (i == index) {
				tvs[i].setEnabled(false);
			} else if (i == next) {
				tvs[i].setEnabled(false);
			} else {
				tvs[i].setEnabled(true);
			}
		}

	}


	@Override
	public void onStart() {
		super.onStart();
		EventBus.getDefault().register(this);

	}

	@Override
	public void onStop() {
		EventBus.getDefault().unregister(this);
		super.onStop();
	}


}



