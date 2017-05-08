package com.xinyuan.xyshop.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.FragmentEvent;
import com.trello.rxlifecycle.FragmentLifecycleProvider;
import com.trello.rxlifecycle.RxLifecycle;
import com.trello.rxlifecycle.components.RxFragment;

import rx.Observable;
import rx.subjects.BehaviorSubject;


/**
 * Created by fx on 2017/5/2 0002.
 */

public abstract class BaseFragment extends RxFragment {


	//定义一个用于重复view 的回收池
	private View contentView = null;
	/**
	 * Fragment当前状态是否可见
	 */
	protected boolean isVisible;


	@Nullable
	@Override
	public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (contentView == null) {//判断回收池是否为空
			contentView = initLayout(inflater, container, false);
		}
		if (contentView != null) {
			return contentView;
		}

		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//初始化数据
		initData(savedInstanceState);
	}

	/**
	 * 可见
	 */
	protected void onVisible() {
		lazyLoad();
	}


	/**
	 * 不可见
	 */
	protected void onInvisible() {

	}

	/**
	 * 延迟加载
	 * 子类必须重写此方法
	 */
	protected abstract void lazyLoad();

	@Override
	public final void onDestroyView() {
		//移除当前视图，防止重复加载相同视图使得程序闪退
		((ViewGroup) contentView.getParent()).removeView(contentView);
		lifecycleSubject.onNext(FragmentEvent.DESTROY_VIEW);
		super.onDestroyView();
	}

	/**
	 * 初始化Fragment的布局,当要创建视图时调用
	 *
	 * @param inflater  布局填充器
	 * @param container ViewGroup
	 * @param b         标记
	 * @return view 返回视图
	 */
	public abstract View initLayout(LayoutInflater inflater, ViewGroup container, boolean b);

	/**
	 * 初始化数据,当ViewCreate被创建是调用此方法
	 */
	protected abstract void initData(@Nullable Bundle savedInstanceState);


	private final BehaviorSubject<FragmentEvent> lifecycleSubject = BehaviorSubject.create();


}
