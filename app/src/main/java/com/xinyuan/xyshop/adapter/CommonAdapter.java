package com.xinyuan.xyshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xinyuan.xyshop.common.interfac.INCOnDel;
import com.xinyuan.xyshop.common.interfac.INCOnEdit;
import com.xinyuan.xyshop.util.ViewHolder;


import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter {
	protected INCOnDel incOnDel;
	protected INCOnEdit incOnEdit;
	private int layoutId;
	protected Context mContext;
	protected List<T> mDatas;
	protected LayoutInflater mInflater;
	protected int position;

	public abstract void convert(ViewHolder viewHolder, T t);

	public CommonAdapter(Context context, List<T> datas, int layoutId) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(context);
		this.mDatas = datas;
		this.layoutId = layoutId;
	}

	protected CommonAdapter(Context context, int layoutId) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(context);
		this.layoutId = layoutId;
	}

	protected CommonAdapter(Context context, int layoutId, INCOnDel incOnDel, INCOnEdit incOnEdit) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(context);
		this.layoutId = layoutId;
		this.incOnDel = incOnDel;
		this.incOnEdit = incOnEdit;
	}

	public List<T> getmDatas() {
		return this.mDatas;
	}

	public void setmDatas(List<T> mDatas) {
		this.mDatas = mDatas;
	}

	public int getCount() {
		return this.mDatas == null ? 0 : this.mDatas.size();
	}

	public T getItem(int position) {
		return this.mDatas.get(position);
	}

	public long getItemId(int position) {
		return (long) position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = ViewHolder.get(this.mContext, convertView, parent, this.layoutId, position);
		this.position = position;
		convert(holder, getItem(position));
		return holder.getConvertView();
	}
}
