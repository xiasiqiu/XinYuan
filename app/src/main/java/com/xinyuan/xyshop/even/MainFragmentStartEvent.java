package com.xinyuan.xyshop.even;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2017/6/26.
 */

public class MainFragmentStartEvent {
	public SupportFragment targetFragment;
	public boolean isResult = false;
	public int requestCode;
	public MainFragmentStartEvent() {

	}

	public MainFragmentStartEvent(SupportFragment targetFragment, boolean isResult, int requestCode) {
		this.targetFragment = targetFragment;
		this.isResult = isResult;
		this.requestCode = requestCode;
	}

	public MainFragmentStartEvent(SupportFragment targetFragment) {
		this.targetFragment = targetFragment;
	}
}
