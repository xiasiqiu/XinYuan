package com.xinyuan.xyshop.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by fx on 2017/7/31.
 * Fragment基类
 */

public abstract class BaseFragment<T extends BasePresenter> extends SupportFragment implements LifecycleProvider<FragmentEvent> {
	protected T mPresenter;
	private View rootView;
	Unbinder mUnbinder;
	protected Context mContext;
	private final BehaviorSubject<FragmentEvent> lifecycleSubject = BehaviorSubject.create();
	public static final String TAG = "LIFE:";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		lifecycleSubject.onNext(FragmentEvent.CREATE);
		super.onCreate(savedInstanceState);
		mPresenter = createPresenter();
		mContext = getContext();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (rootView == null) {
			rootView = inflater.inflate(provideContentViewId(), container, false);
			rootView.setClickable(true);// 防止点击穿透，底层的fragment响应上层点击触摸事件
			ButterKnife.bind(this, rootView);
			mUnbinder = ButterKnife.bind(this, rootView);
			initView(rootView);
			initData();
			initListener();
		} else {
			ViewGroup parent = (ViewGroup) rootView.getParent();
			if (parent != null) {
				parent.removeView(rootView);
			}
		}
		return rootView;


	}

	public abstract void initView(View rootView);

	public abstract void initData();

	public void initListener() {

	}

	//用于创建Presenter和判断是否使用MVP模式(由子类实现)
	protected abstract T createPresenter();

	//得到当前界面的布局文件id(由子类实现)
	protected abstract int provideContentViewId();


	/**
	 * StateView的根布局，默认是整个界面，如果需要变换可以重写此方法
	 */
	public View getStateViewRoot() {
		return rootView;
	}


	@Override
	public void onDestroy() {
		Log.e(TAG + "OnDestroy", getClass().getSimpleName());
		mUnbinder.unbind();
		super.onDestroy();
		lifecycleSubject.onNext(FragmentEvent.DESTROY);
		if (mPresenter != null) {
			mPresenter.detachView();
			mPresenter = null;
		}
		rootView = null;
	}

	@Override
	public void onDestroyView() {
		Log.e(TAG + "OnDestroyView", getClass().getSimpleName());
		//移除当前视图，防止重复加载相同视图使得程序闪退
		((ViewGroup) rootView.getParent()).removeView(rootView);
		lifecycleSubject.onNext(FragmentEvent.DESTROY_VIEW);
		super.onDestroyView();
	}


	public boolean isEventBusRegisted(Object subscribe) {
		return EventBus.getDefault().isRegistered(subscribe);
	}

	public void registerEventBus(Object subscribe) {
		if (!isEventBusRegisted(subscribe)) {
			EventBus.getDefault().register(subscribe);
		}
	}

	public void unregisterEventBus(Object subscribe) {
		if (isEventBusRegisted(subscribe)) {
			EventBus.getDefault().unregister(subscribe);
		}
	}

	@Override
	@NonNull
	@CheckResult
	public final Observable<FragmentEvent> lifecycle() {
		return lifecycleSubject.hide();
	}

	@Override
	@NonNull
	@CheckResult
	public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull FragmentEvent event) {
		return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
	}

	@Override
	@NonNull
	@CheckResult
	public final <T> LifecycleTransformer<T> bindToLifecycle() {
		return RxLifecycleAndroid.bindFragment(lifecycleSubject);
	}

	@Override
	public void onAttach(android.app.Activity activity) {
		super.onAttach(activity);
		lifecycleSubject.onNext(FragmentEvent.ATTACH);
	}


	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		lifecycleSubject.onNext(FragmentEvent.CREATE_VIEW);
	}

	@Override
	public void onStart() {
		Log.e(TAG + "OnStart", getClass().getSimpleName());
		lifecycleSubject.onNext(FragmentEvent.START);
		super.onStart();
	}

	@Override
	public void onResume() {
		Log.e(TAG + "OnResume", getClass().getSimpleName());
		lifecycleSubject.onNext(FragmentEvent.RESUME);
		super.onResume();
	}

	@Override
	public void onPause() {
		Log.e(TAG + "OnPause", getClass().getSimpleName());
		lifecycleSubject.onNext(FragmentEvent.PAUSE);
		super.onPause();
	}

	@Override
	public void onStop() {
		Log.e(TAG + "OnStop", getClass().getSimpleName());
		lifecycleSubject.onNext(FragmentEvent.STOP);
		super.onStop();
	}


	@Override
	public void onDetach() {
		lifecycleSubject.onNext(FragmentEvent.DETACH);
		super.onDetach();
	}
}
