package com.xinyuan.xyshop.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.RxLifecycle;
import com.trello.rxlifecycle.android.FragmentEvent;
import com.trello.rxlifecycle.android.RxLifecycleAndroid;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.youth.xframe.base.ICallback;
import com.youth.xframe.utils.log.XLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;
import rx.Observable;
import rx.subjects.BehaviorSubject;


/**
 * Created by fx on 2017/5/2 0002.
 */

public abstract class BaseFragment extends SupportFragment implements ICallback, LifecycleProvider<FragmentEvent> {
	// 再点一次退出程序时间设置
	private static final long WAIT_TIME = 2000L;
	private long TOUCH_TIME = 0;

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
		if (contentView == null) {
			contentView = inflater.inflate(getLayoutId(), container, false);
			contentView.setClickable(true);// 防止点击穿透，底层的fragment响应上层点击触摸事件
			mUnbinder = ButterKnife.bind(this, contentView);

			return contentView;
		} else {

			return contentView;

		}

	}


	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.application = MyShopApplication.getInstance();
		this.context = getContext();
		initData(savedInstanceState);
		initView();
	}


	@Override
	public void onDestroyView() {
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
		XLog.d("-----------------------Fragment销毁!------------------");

	}

	@Override
	public void onDetach() {
		lifecycleSubject.onNext(FragmentEvent.DETACH);
		super.onDetach();
	}

	@Subscribe
	public void onDoing() {

	}


}
