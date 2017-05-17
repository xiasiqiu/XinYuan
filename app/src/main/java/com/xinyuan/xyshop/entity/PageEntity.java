package com.xinyuan.xyshop.entity;

public class PageEntity {
	private boolean hasMore;
	private int totalPage;

	public boolean isHasMore() {
		return this.hasMore;
	}

	public void setHasMore(boolean hasMore) {
		this.hasMore = hasMore;
	}

	public int getTotalPage() {
		return this.totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public PageEntity(boolean hasMore, int totalPage) {
		this.hasMore = hasMore;
		this.totalPage = totalPage;
	}

	public String toString() {
		return "PageEntity{hasMore=" + this.hasMore + ", totalPage=" + this.totalPage + '}';
	}
}
