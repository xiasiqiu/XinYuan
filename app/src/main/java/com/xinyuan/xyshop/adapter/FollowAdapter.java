package com.xinyuan.xyshop.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayout;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.callback.OnItemClickListener;
import com.xinyuan.xyshop.common.AddViewHolder;
import com.xinyuan.xyshop.entity.CreditBean;
import com.xinyuan.xyshop.entity.FollowBean;
import com.xinyuan.xyshop.entity.GoodsVo;
import com.xinyuan.xyshop.util.GlideImageLoader;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/6/26.
 */

public class FollowAdapter extends SwipeMenuAdapter<FollowAdapter.ViewHolder> {
	List<FollowBean> list;
	private OnItemClickListener mOnItemClickListener;
	private Context context;

	public FollowAdapter(List<FollowBean> data, Context context) {
		this.context = context;
		this.list = data;
	}

	@Override
	public View onCreateContentView(ViewGroup parent, int viewType) {
		return LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_follow_item, parent, false);
	}

	@Override
	public ViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
		ViewHolder viewHolder = new ViewHolder(realContentView);
		viewHolder.mOnItemClickListener = mOnItemClickListener;
		return viewHolder;


	}


	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		holder.setData(list.get(position));
	}

	@Override
	public int getItemCount() {
		return list == null ? 0 : list.size();
	}


	public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		OnItemClickListener mOnItemClickListener;
		ImageView iv_store;
		TextView tv_follow_name;
		FlexboxLayout flexboxLayout;

		public ViewHolder(View itemView) {
			super(itemView);
			itemView.setOnClickListener(this);
			iv_store = (ImageView) itemView.findViewById(R.id.iv_follow_img);
			tv_follow_name = (TextView) itemView.findViewById(R.id.tv_follow_name);
			flexboxLayout = (FlexboxLayout) itemView.findViewById(R.id.fl_follow_level);

		}

		public void setData(FollowBean bean) {
			GlideImageLoader.setImage(context, bean.getStoreImage(), iv_store);
			tv_follow_name.setText(bean.getStoreName());

			for (String level : bean.getStoreLevel()) {
				AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.fragment_follow_item_level);
				ImageView imageView = addViewHolder.getView(R.id.iv_store_level);
				if (level.equals("皇冠")) {
					imageView.setBackground(context.getResources().getDrawable(R.drawable.ic_store_leve1));
				} else if (level.equals("金牌卖家")) {
					imageView.setBackground(context.getResources().getDrawable(R.drawable.ic_store_glod));
				}
				flexboxLayout.addView(addViewHolder.getCustomView());
			}
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
