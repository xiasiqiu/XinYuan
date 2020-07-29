package com.xinyuan.xyshop.common;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.widget.BubbleImageView;
import com.youth.banner.loader.ImageLoader;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by fx on 2017/5/4 0004.
 * Glide设置
 */

public class GlideImageLoader extends ImageLoader {


    private static final long serialVersionUID = -8887196687198312416L;

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .crossFade()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .placeholder((int) R.drawable.img_defaule)
                .error((int) R.drawable.img_defaule)
                .into(imageView);


    }

    public static void setUrlImg(Context context, String path, ImageView imageView) {
        if (XEmptyUtils.isEmpty(path)) {
            Glide.with(context)
                    .load(R.drawable.img_defaule)
                    .crossFade()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .placeholder((int) R.drawable.img_defaule)
                    .error((int) R.drawable.img_defaule)
                    .into(imageView);
        } else {
            Glide.with(context)
                    .load(CommUtil.getUrl(path))
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .placeholder((int) R.drawable.img_defaule)
                    .error((int) R.drawable.img_defaule)
                    .into(imageView);
        }

    }

    public static void setBubbleImg(Context context, String path, final BubbleImageView imageView) {
        Glide.with(context)
                .load(CommUtil.getUrl(path))
                .crossFade()
                .skipMemoryCache(true)

                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .placeholder((int) R.drawable.img_defaule)
                .error((int) R.drawable.img_defaule)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource,
                                                GlideAnimation<? super GlideDrawable> glideAnimation) {
                        imageView.setImageDrawable(resource);
                    }
                });


    }

    public static void setImg(Context context, Object path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .placeholder((int) R.drawable.img_defaule)
                .error((int) R.drawable.img_defaule)
                .into(imageView);
    }

    public static void setCircleImageView(Context context, String path, CircleImageView imageView) {
        if (XEmptyUtils.isEmpty(path)) {
            Glide.with(context)
                    .load(R.drawable.img_defaule).crossFade()
                    .error((int) R.drawable.img_defaule)
                    .placeholder(R.drawable.img_defaule)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource,
                                                    GlideAnimation<? super GlideDrawable> glideAnimation) {
                            imageView.setImageDrawable(resource);
                        }
                    });
        } else {
            Glide.with(context)
                    .load(CommUtil.getUrl(path))
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .error((int) R.drawable.img_defaule)
                    .placeholder(R.drawable.img_defaule)
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource,
                                                    GlideAnimation<? super GlideDrawable> glideAnimation) {
                            imageView.setImageDrawable(resource);
                        }
                    });
        }

    }

}
