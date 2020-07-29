package com.xinyuan.xyshop.model;

import com.xinyuan.xyshop.bean.GoodEvaBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/6/21.
 * 商品评论列表数据
 */

public class EvaluateModel implements Serializable {
	private static final long serialVersionUID = 2501231470366439473L;
	private List<GoodEvaBean> evaluateList;

	public List<GoodEvaBean> getEvaluateList() {
		return evaluateList;
	}
}
