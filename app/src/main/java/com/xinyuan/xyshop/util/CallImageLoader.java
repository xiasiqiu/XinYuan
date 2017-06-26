package com.xinyuan.xyshop.util;

import android.app.Activity;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xinyuan.xyshop.R;
import com.yancy.gallerypick.inter.ImageLoader;
import com.yancy.gallerypick.widget.GalleryImageView;

/**
 * Created by Administrator on 2017/6/26.
 */

public class CallImageLoader implements ImageLoader {
	private static final long serialVersionUID = -4852076541765374203L;

	@Override
	public void displayImage(Activity activity, Context context, String path, GalleryImageView galleryImageView, int width, int height) {
		Glide.with(context).load(path).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder((int) R.drawable.img_defaule).error((int) R.drawable.img_defaule).into(galleryImageView);

	}

	@Override
	public void clearMemoryCache() {

	}
}
