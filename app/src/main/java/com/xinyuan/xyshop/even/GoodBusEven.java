package com.xinyuan.xyshop.even;

/**
 * Created by fx on 2017/6/2.
 * 商品详情消息
 */

public class GoodBusEven {


	public static String GoodsStoreId = "goodsStoreId";
	public static String SelectedGoods = "selectedGoods";
	public static String SelectedEvaluate = "selectEvaluate";
	public static String GoodEvaluate = "goodEvaluate";
	public static String SelectedSpec = "SelectedSpec";
	public static String addShopCar = "addShopCar";
	public static String contanctSercvice = "contanctSercvice";
	public static String goodFav = "goodFav";
	public static String goToBuy = "goToBuy";
	public static String goToShare = "goToShare";
	public static String showSpecView = "showSpecView";


	private String flag;
	private Object obj;

	public GoodBusEven(String flag) {
		this.flag = flag;
	}

	public GoodBusEven(String flag, Object obj) {
		this.flag = flag;
		this.obj = obj;
	}

	public GoodBusEven(String flag, Object obj, int goodsType) {
		this.flag = flag;
		this.obj = obj;
		this.goodsType = goodsType;
	}

	public static int getGoodsType() {
		return goodsType;
	}

	public static int goodsType;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
