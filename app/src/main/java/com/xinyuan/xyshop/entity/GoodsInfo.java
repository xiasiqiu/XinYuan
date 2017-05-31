package com.xinyuan.xyshop.entity;

/**
 * Created by Administrator on 2017/5/31.
 */

public class GoodsInfo {
	protected String Id;
	protected String name;
	protected boolean isChoosed;
	private String imageUrl;
	private String desc;
	private double price;
	private int count;
	private int position;
	private String color;
	private String size;
	private int goodsImg;
	private double discountPrice;

	public GoodsInfo(String id, String name, String desc, double price, int count, String color,
	                 String size, int goodsImg, double discountPrice) {
		Id = id;
		this.name = name;
		this.desc = desc;
		this.price = price;
		this.count = count;
		this.color = color;
		this.size = size;
		this.goodsImg = goodsImg;
		this.discountPrice = discountPrice;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isChoosed() {
		return isChoosed;
	}

	public void setChoosed(boolean choosed) {
		isChoosed = choosed;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getGoodsImg() {
		return goodsImg;
	}

	public void setGoodsImg(int goodsImg) {
		this.goodsImg = goodsImg;
	}

	public double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}

	@Override
	public String toString() {
		return "GoodsInfo{" +
				"Id='" + Id + '\'' +
				", name='" + name + '\'' +
				", isChoosed=" + isChoosed +
				", imageUrl='" + imageUrl + '\'' +
				", desc='" + desc + '\'' +
				", price=" + price +
				", count=" + count +
				", position=" + position +
				", color='" + color + '\'' +
				", size='" + size + '\'' +
				", goodsImg=" + goodsImg +
				", discountPrice=" + discountPrice +
				'}';
	}
}
