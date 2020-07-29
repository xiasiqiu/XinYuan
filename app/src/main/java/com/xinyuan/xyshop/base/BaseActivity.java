package com.xinyuan.xyshop.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.util.Log;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import com.umeng.analytics.MobclickAgent;
import com.xinyuan.xyshop.MyShopApplication;
import com.youth.xframe.common.XActivityStack;
import com.youth.xframe.utils.permission.XPermission;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by fx on 2017/7/31.
 * Activity基类
 */

public abstract class BaseActivity<T extends BasePresenter> extends SupportActivity implements LifecycleProvider<ActivityEvent> {
	public static final String TAG = "LIFE:";
	protected Bundle savedInstanceState;
	protected T mPresenter;
	Unbinder mUnbinder;
	private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();
	protected MyShopApplication mApp;
	@Override
	@CallSuper
	protected void onCreate(Bundle savedInstanceState) {
		Log.e(TAG + "OnCreate", getClass().getSimpleName());
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		lifecycleSubject.onNext(ActivityEvent.CREATE);
		XActivityStack.getInstance().addActivity(this);

		super.onCreate(savedInstanceState);
		this.savedInstanceState = savedInstanceState;
		mApp = MyShopApplication.getInstance();
		mPresenter = createPresenter();
		//子类不再需要设置布局ID，也不再需要使用ButterKnife.bind()
		setContentView(provideContentViewId());
		ButterKnife.bind(this);
		mUnbinder = ButterKnife.bind(this);
		initView();
		initData();
		initListener();

	}

	//用于创建Presenter和判断是否使用MVP模式(由子类实现)
	protected abstract T createPresenter();

	//得到当前界面的布局文件id(由子类实现)
	protected abstract int provideContentViewId();

	public abstract void initView();

	public abstract void initData();

	public void initListener() {
	}

	@Override
	protected void onDestroy() {
		Log.e(TAG + "OnDestroy", getClass().getSimpleName());
		super.onDestroy();
		lifecycleSubject.onNext(ActivityEvent.DESTROY);
		mUnbinder.unbind();
		XActivityStack.getInstance().finishActivity();

	}

	@Override
	@NonNull
	@CheckResult
	public final Observable<ActivityEvent> lifecycle() {
		return lifecycleSubject.hide();
	}

	@Override
	@NonNull
	@CheckResult
	public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event) {
		return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
	}

	@Override
	@NonNull
	@CheckResult
	public final <T> LifecycleTransformer<T> bindToLifecycle() {
		return RxLifecycleAndroid.bindActivity(lifecycleSubject);
	}

	/**
	 * Android M 全局权限申请回调
	 *
	 * @param requestCode
	 * @param permissions
	 * @param grantResults
	 */
	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
			grantResults) {
		XPermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
	}

	public boolean isEventBusRegisted(Object subscribe) {
		return EventBus.getDefault().isRegistered(subscribe);
	}

	public void registerEventBus(Object subscribe) {
		if (!isEventBusRegisted(subscribe)) {
			EventBus.getDefault().register(subscribe);
		}
	}
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	public void unregisterEventBus(Object subscribe) {
		if (isEventBusRegisted(subscribe)) {
			EventBus.getDefault().unregister(subscribe);
		}
	}
}
