package com.xinyuan.xyshop.model;

import com.xinyuan.xyshop.bean.UserHistoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/7/25.
 * 商品浏览历史列表数据
 */

public class FooterModel implements Serializable
{
	private static final long serialVersionUID = 6445962944333752426L;

	public List<UserHistoryBean> getFooterList() {
		return footerList;
	}

	private List<UserHistoryBean>footerList;
}
