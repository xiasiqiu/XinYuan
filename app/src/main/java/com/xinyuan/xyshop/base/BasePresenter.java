package com.xinyuan.xyshop.base;


import android.content.Context;

/**
 * Created by fx on 2017/7/31.
 * Presenter基类
 */

public abstract class BasePresenter<V> {
	protected V mView;
	private Context context;

	public BasePresenter(V view) {
		attachView(view);
	}

	public void attachView(V view) {
		mView = view;
	}

	public void detachView() {
		mView = null;
	}


}