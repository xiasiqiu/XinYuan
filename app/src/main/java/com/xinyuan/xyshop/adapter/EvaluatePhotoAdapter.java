package com.xinyuan.xyshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.util.GlideImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */

public class EvaluatePhotoAdapter extends BaseAdapter {
	private Context context;
	private List<String> imageFileList;
	private LayoutInflater inflater;
	private int maxLength;

	public EvaluatePhotoAdapter(Context ctx, int maxLength) {
		this.inflater = LayoutInflater.from(ctx);
		this.context = ctx;
		this.maxLength = maxLength;
	}

	public void setImageFileList(List<String> imageFileList) {
		this.imageFileList = imageFileList;
	}

	public int getCount() {
		if (this.imageFileList == null) {
			return 1;
		}
		if (this.imageFileList.size() < this.maxLength) {
			return this.imageFileList.size() + 1;
		}
		if (this.imageFileList.size() == this.maxLength) {
			return this.imageFileList.size();
		}
		return 0;
	}

	public Object getItem(int i) {
		return null;
	}

	public long getItemId(int i) {
		return (long) i;
	}

	public View getView(int i, View view, ViewGroup viewGroup) {
		view = this.inflater.inflate(R.layout.view_evapull_simple, null);
		ImageView iv = (ImageView) view.findViewById(R.id.ivImg);
		if (this.imageFileList != null && this.imageFileList.size() == this.maxLength) {
			GlideImageLoader.setImage(context, imageFileList.get(i), iv);
		}
		return view;
	}
}
