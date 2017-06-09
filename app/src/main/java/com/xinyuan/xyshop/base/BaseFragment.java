package com.xinyuan.xyshop.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.RxLifecycle;
import com.trello.rxlifecycle.android.FragmentEvent;
import com.trello.rxlifecycle.android.RxLifecycleAndroid;
import com.xinyuan.xyshop.MyShopApplication;
import com.youth.xframe.base.ICallback;
import com.youth.xframe.utils.log.XLog;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.subjects.BehaviorSubject;


/**
 * Created by fx on 2017/5/2 0002.
 */

public abstract class BaseFragment extends Fragment implements ICallback, LifecycleProvider<FragmentEvent> {


	private final BehaviorSubject<FragmentEvent> lifecycleSubject = BehaviorSubject.create();
	private static boolean VIEW_INIT = true;
	//定义一个用于重复view 的回收池
	private View contentView = null;
	/**
	 * Fragment当前状态是否可见
	 */
	protected boolean isVisible;
	protected MyShopApplication application;
	protected Context context;
	Unbinder mUnbinder;
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		this.application = MyShopApplication.getInstance();

		this.context = getActivity();
		if (contentView == null) {
			contentView = inflater.inflate(getLayoutId(), container, false);
			mUnbinder=ButterKnife.bind(this,contentView);
			return contentView;
		} else {

			return contentView;

		}

	}


	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initData(savedInstanceState);
		initView();
	}


	@Override
	public final void onDestroyView() {
		//移除当前视图，防止重复加载相同视图使得程序闪退
		((ViewGroup) contentView.getParent()).removeView(contentView);
		lifecycleSubject.onNext(FragmentEvent.DESTROY_VIEW);
		super.onDestroyView();
	}


	@Override
	@NonNull
	@CheckResult
	public final Observable<FragmentEvent> lifecycle() {
		return lifecycleSubject.asObservable();
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
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		lifecycleSubject.onNext(FragmentEvent.CREATE);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		lifecycleSubject.onNext(FragmentEvent.CREATE_VIEW);
	}

	@Override
	public void onStart() {
		super.onStart();
		lifecycleSubject.onNext(FragmentEvent.START);
	}

	@Override
	public void onResume() {
		super.onResume();
		lifecycleSubject.onNext(FragmentEvent.RESUME);
	}

	@Override
	public void onPause() {
		lifecycleSubject.onNext(FragmentEvent.PAUSE);
		super.onPause();
	}

	@Override
	public void onStop() {
		lifecycleSubject.onNext(FragmentEvent.STOP);
		super.onStop();
	}


	@Override
	public void onDestroy() {
		lifecycleSubject.onNext(FragmentEvent.DESTROY);
		mUnbinder.unbind();
		super.onDestroy();
		XLog.d("-----------------------Acitivty已经销毁了------------------");

	}

	@Override
	public void onDetach() {
		lifecycleSubject.onNext(FragmentEvent.DETACH);
		super.onDetach();
	}



}
