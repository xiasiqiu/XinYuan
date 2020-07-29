package com.xinyuan.xyshop.util;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lzy.imagepicker.loader.ImageLoader;
import com.xinyuan.xyshop.R;

import java.io.File;
import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/7/21.
 * 图片选择器专用图片加载
 */

public class ImagePickerLoader implements ImageLoader {


	private static final long serialVersionUID = -7440709422624932286L;

	@Override
	public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
		Glide.with(activity)                             //配置上下文
				.load(Uri.fromFile(new File(path)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
				.crossFade()
				.skipMemoryCache(true)
				.diskCacheStrategy(DiskCacheStrategy.RESULT)//配置缓存模式
				.error(R.drawable.img_defaule)           //设置错误图片
				.placeholder(R.drawable.img_defaule)     //设置占位图片
				.into(imageView);
	}

	@Override
	public void clearMemoryCache() {

	}


}
