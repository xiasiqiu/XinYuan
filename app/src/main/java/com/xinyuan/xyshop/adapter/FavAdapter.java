package com.xinyuan.xyshop.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.callback.OnItemClickListener;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */

public class FavAdapter extends SwipeMenuAdapter<FavAdapter.ViewHolder> {
	List<GoodsVo> list;
	private OnItemClickListener mOnItemClickListener;


	public FavAdapter(List<GoodsVo> list) {
		this.list = list;
	}

	@Override
	public View onCreateContentView(ViewGroup parent, int viewType) {
		return LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_favorite_item, parent, false);
	}

	@Override
	public ViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
		ViewHolder viewHolder = new ViewHolder(realContentView);
		viewHolder.mOnItemClickListener = mOnItemClickListener;
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {

	}

	@Override
	public int getItemCount() {
		return list == null ? 0 : list.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		OnItemClickListener mOnItemClickListener;

		public ViewHolder(View itemView) {
			super(itemView);
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View view) {
			if (mOnItemClickListener != null) {
				mOnItemClickListener.onItemClick(getAdapterPosition());
			}
		}
	}

	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.mOnItemClickListener = onItemClickListener;
	}
}

