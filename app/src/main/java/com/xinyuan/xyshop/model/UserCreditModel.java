package com.xinyuan.xyshop.model;

import com.xinyuan.xyshop.bean.CreditBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/8/18.
 * 用户积分列表数据
 */

public class UserCreditModel implements Serializable{
	private static final long serialVersionUID = 765891963828385034L;
	private List<CreditBean>integralList;

	public List<CreditBean> getIntegralList() {
		return integralList;
	}
}
