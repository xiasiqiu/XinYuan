package com.xinyuan.xyshop.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.util.GlideImageLoader;


public class AddViewHolder {
    private Context context;
    private View customView;
    private LayoutInflater inflater;
    private SparseArray<View> mViews = new SparseArray();

    public AddViewHolder(Context context, int layoutId) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.customView = this.inflater.inflate(layoutId, null);
        this.customView.setTag(this);
    }

    public View getCustomView() {
        return this.customView;
    }

    public <T extends View> T getView(int viewId) {
        View view = (View) this.mViews.get(viewId);
        if (view != null) {
            return (T) view;
        }
        view = this.customView.findViewById(viewId);
        this.mViews.put(viewId, view);
        return (T) view;
    }

    public AddViewHolder setText(int viewId, CharSequence text) {
        ((TextView) getView(viewId)).setText(text);
        return this;
    }

    public AddViewHolder setTag(int position) {
        this.customView.setTag(Integer.valueOf(position));
        return this;
    }

    public AddViewHolder setText(int viewId, CharSequence text, BufferType type) {
        ((TextView) getView(viewId)).setText(text, type);
        return this;
    }

    public AddViewHolder setImage(int viewId, String url) {
        GlideImageLoader.setImage(this.context, url, (ImageView) getView(viewId));
        return this;
    }

    public AddViewHolder setImageRed(int viewId, String url) {
        final ImageView view = (ImageView) getView(viewId);
        Glide.with(this.context).load(url).placeholder((int) R.drawable.icon_null).error((int) R.drawable.nc_mine_bg).centerCrop().into(new SimpleTarget<GlideDrawable>() {
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                view.setImageBitmap(AddViewHolder.this.red(AddViewHolder.drawableToBitmap(resource)));
            }
        });
        return this;
    }

    private Bitmap red(Bitmap bitmap) {
        Bitmap faceIconGreyBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(faceIconGreyBitmap);
        Paint paint = new Paint();
        paint.setColor(this.context.getResources().getColor(R.color.colorPrimary));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return faceIconGreyBitmap;
    }

    public AddViewHolder setImageGrey(int viewId, String url) {
        final ImageView view = (ImageView) getView(viewId);
        Glide.with(this.context).load(url).placeholder((int) R.drawable.icon_null).error((int) R.drawable.nc_mine_bg).centerCrop().into(new SimpleTarget<GlideDrawable>() {
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                view.setImageBitmap(AddViewHolder.grey(AddViewHolder.drawableToBitmap(resource)));
            }
        });
        return this;
    }

    private static Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888 : Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }

    private static Bitmap grey(Bitmap bitmap) {
        Bitmap faceIconGreyBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(faceIconGreyBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return faceIconGreyBitmap;
    }

    public void setImage(ImageView view, String url) {
        GlideImageLoader.setImage(this.context, url, view);
    }

    public void setImageLocal(ImageView view, String url) {
        Glide.with(this.context).load(url).placeholder((int) R.drawable.icon_null).error((int) R.drawable.nc_mine_bg).centerCrop().into(view);
    }

    public AddViewHolder setImageDrawbleToBitmap(int viewId, int res) {
        ((ImageView) getView(viewId)).setImageBitmap(grey(drawableToBitmap(ContextCompat.getDrawable(this.context, res))));
        return this;
    }

    public AddViewHolder setVisible(int viewId, boolean visible) {
        getView(viewId).setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public AddViewHolder setOnClickListener(int viewId, OnClickListener listener) {
        getView(viewId).setOnClickListener(listener);
        return this;
    }

    public Bitmap getBitmap(int viewId) {
        return ((ImageView) getView(viewId)).getDrawingCache();
    }

    public void setColorFilter(int viewId, int color, Mode type) {
        ImageView iv = (ImageView) getView(viewId);
        iv.getDrawable().setColorFilter(color, type);
        iv.invalidate();
    }

    public AddViewHolder setColorFilter(int viewId) {
        ImageView iv = (ImageView) getView(viewId);
        Bitmap b = iv.getDrawingCache();
        Canvas canvas = new Canvas(b);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(b, 0.0f, 0.0f, paint);
        iv.setImageBitmap(b);
        return this;
    }

    public AddViewHolder setIMage(int viewId, Bitmap bitmap) {
        ((ImageView) getView(viewId)).setImageBitmap(bitmap);
        return this;
    }

    public void clearColorFilter(int viewId) {
        ImageView view = (ImageView) getView(viewId);
        view.getDrawable().clearColorFilter();
        view.invalidate();
    }

    public AddViewHolder setTextColor(int viewId, int color) {
        ((TextView) getView(viewId)).setTextColor(this.context.getResources().getColor(color));
        return this;
    }

    public AddViewHolder setBackgroundColor(int viewId, int color) {
        ((View) getView(viewId)).setBackgroundColor(this.context.getResources().getColor(color));
        return this;
    }

    public AddViewHolder setDimensionPixelSize(int viewId, int dimes) {
        View tv = (View) getView(viewId);
        LayoutParams params = tv.getLayoutParams();
        params.height = this.context.getResources().getDimensionPixelSize(dimes);
        tv.setLayoutParams(params);
        return this;
    }

    public String getText(int viewId) {
        return ((TextView) getView(viewId)).getText().toString();
    }

    public AddViewHolder setImageDrawable(int viewId, int res) {
        ((ImageView) getView(viewId)).setImageDrawable(this.context.getResources().getDrawable(res));
        return this;
    }

    public AddViewHolder setImageDrawable(int viewId, Drawable res) {
        ((ImageView) getView(viewId)).setImageDrawable(res);
        return this;
    }

    public AddViewHolder setView(int viewId, View view) {
        ((LinearLayout) getView(viewId)).addView(view);
        return this;
    }

    public AddViewHolder setAdapter(int viewId, ListAdapter adapter) {
        ((ListView) getView(viewId)).setAdapter(adapter);
        return this;
    }

    public AddViewHolder setGridAdapter(int viewId, ListAdapter adapter) {
        ((GridView) getView(viewId)).setAdapter(adapter);
        return this;
    }

    public AddViewHolder setSelected(int viewId, boolean flag) {
        ((ImageView) getView(viewId)).setSelected(flag);
        return this;
    }

    public boolean isselected(int viewId) {
        return ((ImageView) getView(viewId)).isSelected();
    }

    public AddViewHolder setClickable(int viewId, boolean flag) {
        ((ImageButton) getView(viewId)).setClickable(flag);
        return this;
    }

    public boolean isClickable(int viewId) {
        return ((ImageButton) getView(viewId)).isClickable();
    }
}