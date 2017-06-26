package com.xinyuan.xyshop.even;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2017/6/26.
 */

public class StartBrotherEvent {
	public SupportFragment targetFragment;

	public StartBrotherEvent(SupportFragment targetFragment) {
		this.targetFragment = targetFragment;
	}
}
