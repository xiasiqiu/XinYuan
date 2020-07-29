package com.xinyuan.xyshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.model.OrderServiceReasonModel;

import java.util.List;

/**
 * Created by Administrator on 2017/9/23.
 */

public class ReasonAdapter extends ArrayAdapter<OrderServiceReasonModel.ServiceReasonBean> {

	private List<OrderServiceReasonModel.ServiceReasonBean> objects;
	private Context context;

	public ReasonAdapter(@NonNull Context context, int resource, List<OrderServiceReasonModel.ServiceReasonBean> objects) {
		super(context, resource, objects);
		this.context = context;
		this.objects = objects;
	}


	private static class ViewHolder {

		TextView tv_reason;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return objects.size();
	}

	@Override
	public OrderServiceReasonModel.ServiceReasonBean getItem(int position) {
		// TODO Auto-generated method stub
		return objects.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			LayoutInflater mInflater = LayoutInflater.from(context);
			convertView = mInflater.inflate(R.layout.spinner_item, null);

			viewHolder.tv_reason = (TextView) convertView.findViewById(R.id.tv_reason);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		OrderServiceReasonModel.ServiceReasonBean person = objects.get(position);
		if (null != person) {
			viewHolder.tv_reason.setText(person.getGoodsReturnResonName());
		}
		return convertView;
	}


}
