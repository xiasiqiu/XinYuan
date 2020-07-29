package com.xinyuan.xyshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 规格选择Adapter基类
 *
 * @author 胡逸枫
 * @time 2016/9/8 9:19
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {
	protected List<T> mData;
	protected final Context mContext;
	protected LayoutInflater mInflater;
	private OnItemClickListener mClickListener;
	private OnItemLongClickListener mLongClickListener;
	protected Intent intent = new Intent();

	public BaseRecyclerAdapter(Context ctx, List<T> list) {
		mData = (list != null) ? list : new ArrayList<T>();
		mContext = ctx;
		mInflater = LayoutInflater.from(ctx);
	}


	@Override
	public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		final RecyclerViewHolder holder = new RecyclerViewHolder(mContext,
				mInflater.inflate(getItemLayoutId(viewType), parent, false));
		if (mClickListener != null) {
			holder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mClickListener.onItemClick(holder.itemView, holder.getLayoutPosition());
				}
			});
		}
		if (mLongClickListener != null) {
			holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					mLongClickListener.onItemLongClick(holder.itemView, holder.getLayoutPosition());
					return true;
				}
			});
		}
		return holder;
	}

	@Override
	public void onBindViewHolder(RecyclerViewHolder holder, int position) {
		bindData(holder, position, mData.get(position));
	}

	@Override
	public int getItemCount() {
		return mData.size();
	}

	public T getItem(int position) {
		return mData.get(position);
	}

	// 刷新
	public void refresh(List<T> list) {
		mData = (list != null) ? list : new ArrayList<T>();
		notifyDataSetChanged();
	}


	public void add(int pos, T item) {
		mData.add(pos, item);
		notifyItemInserted(pos);
	}

	public void delete(int pos) {
		mData.remove(pos);
		notifyItemRemoved(pos);
	}

	public void setOnItemClickListener(OnItemClickListener listener) {
		mClickListener = listener;
	}

	public void setOnItemLongClickListener(OnItemLongClickListener listener) {
		mLongClickListener = listener;
	}

	abstract public int getItemLayoutId(int viewType);

	abstract public void bindData(RecyclerViewHolder holder, int position, T item);

	public interface OnItemClickListener {
		void onItemClick(View itemView, int pos);
	}

	public interface OnItemLongClickListener {
		void onItemLongClick(View itemView, int pos);
	}
}
