package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by fx on 2017/9/13.
 * 加入购物车
 */

public class AddCartBean  implements Serializable{
	private static final long serialVersionUID = -7220487800411735767L;
	private Long goodsCartId;
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getGoodsCartId() {
		return goodsCartId;
	}

	public void setGoodsCartId(Long goodsCartId) {
		this.goodsCartId = goodsCartId;
	}
}
