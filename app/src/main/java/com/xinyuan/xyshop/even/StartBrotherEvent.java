package com.xinyuan.xyshop.even;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2017/6/26.
 */

public class StartBrotherEvent {
	public SupportFragment targetFragment;
	public boolean isResult = false;
	public int requestCode;
	public StartBrotherEvent() {

	}

	public StartBrotherEvent(SupportFragment targetFragment, boolean isResult, int requestCode) {
		this.targetFragment = targetFragment;
		this.isResult = isResult;
		this.requestCode = requestCode;
	}

	public StartBrotherEvent(SupportFragment targetFragment) {
		this.targetFragment = targetFragment;
	}
}
