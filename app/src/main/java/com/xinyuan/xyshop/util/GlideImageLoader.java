package com.xinyuan.xyshop.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xinyuan.xyshop.R;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by fx on 2017/5/4 0004.
 */

public class GlideImageLoader extends ImageLoader {


	private static final long serialVersionUID = -8887196687198312416L;

	@Override
	public void displayImage(Context context, Object path, ImageView imageView) {
		/**
		 注意：
		 1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
		 2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
		 传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
		 切记不要胡乱强转！
		 */

		//Glide 加载图片简单用法

		Glide.with(context).load(path).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder((int) R.drawable.img_defaule).error((int) R.drawable.img_defaule).into(imageView);


	}
	private void ii(){

	}
	public static void setImage(Context context, Object path, ImageView imageView) {
		Glide.with(context).load(path).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder((int) R.drawable.img_defaule).error((int) R.drawable.img_defaule).into(imageView);
	}


}
