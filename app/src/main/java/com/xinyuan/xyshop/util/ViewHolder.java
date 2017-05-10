package com.xinyuan.xyshop.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.xinyuan.xyshop.R;

/**
 * Title:
 * Description:
 * Created by Administrator on 2017/5/10.
 * 作者：fx on 2017/5/10 23:04
 */

public class ViewHolder {
    private Context mContext;
    private View mConvertView;
    private int mLayoutId;
    private int mPosition;
    private SparseArray<View> mViews = new SparseArray();

    public ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mContext = context;
        this.mLayoutId = layoutId;
        this.mPosition = position;
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        this.mConvertView.setTag(this);
    }

    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.mPosition = position;
        return holder;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public int getLayoutId() {
        return this.mLayoutId;
    }

    public <T extends View> T getView(int viewId) {
        View view = (View) this.mViews.get(viewId);
        if (view != null) {
            return (T) view;
        }
        view = this.mConvertView.findViewById(viewId);
        this.mViews.put(viewId, view);
        return (T) view;
    }

    public View getConvertView() {
        return this.mConvertView;
    }

    public ViewHolder setText(int viewId, String text) {
        ((TextView) getView(viewId)).setText(text);
        return this;
    }

    public ViewHolder setScore(int viewId, float score) {
        ((RatingBar) getView(viewId)).setRating(score);
        return this;
    }

    public ViewHolder setText(int viewId, String text, int color) {
        TextView tv = (TextView) getView(viewId);
        tv.setText(text);
        tv.setTextColor(color);
        return this;
    }

    public String getText(int viewId) {
        return ((TextView) getView(viewId)).getText().toString().trim();
    }

    public ViewHolder setImageViewSelected(int viewId, boolean bool) {
        ((ImageView) getView(viewId)).setSelected(bool);
        return this;
    }

    public boolean isImageViewSelected(int viewId) {
        return ((ImageView) getView(viewId)).isSelected();
    }

    public ViewHolder setImageButtonSelected(int viewId, boolean bool) {
        ((ImageButton) getView(viewId)).setSelected(bool);
        return this;
    }

    public boolean isImageButtonSelected(int viewId) {
        return ((ImageButton) getView(viewId)).isSelected();
    }

    public ViewHolder setImage(int viewId, String url) {
        GlideImageLoader.setImage(this.mContext, url, (ImageView) getView(viewId));
        return this;
    }

    public ViewHolder setImage(int viewId, int resId) {
        ((ImageView) getView(viewId)).setImageResource(resId);
        return this;
    }

    public ViewHolder setImage(int viewId, Bitmap bitmap) {
        ((ImageView) getView(viewId)).setImageBitmap(bitmap);
        return this;
    }

    public ViewHolder setImage(int viewId, Drawable drawable) {
        ((ImageView) getView(viewId)).setImageDrawable(drawable);
        return this;
    }

    public ViewHolder setImageGrey(int viewId, int resId) {
        setImage(viewId, grey(drawableToBitmap(this.mContext.getResources().getDrawable(resId))));
        return this;
    }

    private static Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }

    private Bitmap grey(Bitmap bitmap) {
        Bitmap faceIconGreyBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Bitmap alphaBitmap = bitmap.extractAlpha();
        Canvas canvas = new Canvas(faceIconGreyBitmap);
        Paint paint = new Paint();
        paint.setColor(this.mContext.getResources().getColor(R.color.tv_hint));
        canvas.drawBitmap(alphaBitmap, 0.0f, 0.0f, paint);
        return faceIconGreyBitmap;
    }

    public ViewHolder setBackgroundColor(int viewId, int color) {
        getView(viewId).setBackgroundColor(color);
        return this;
    }

    public ViewHolder setClickable(int viewId, boolean b) {
        getView(viewId).setClickable(b);
        return this;
    }

    public ViewHolder setBackgroundRes(int viewId, int backgroundRes) {
        getView(viewId).setBackgroundResource(backgroundRes);
        return this;
    }

    public ViewHolder setTextColor(int viewId, int textColor) {
        ((TextView) getView(viewId)).setTextColor(textColor);
        return this;
    }

    public ViewHolder setTextColorRes(int viewId, int textColorRes) {
        ((TextView) getView(viewId)).setTextColor(this.mContext.getResources().getColor(textColorRes));
        return this;
    }

    @SuppressLint({"NewApi"})
    public ViewHolder setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= 11) {
            getView(viewId).setAlpha(value);
        } else {
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    public ViewHolder setVisible(int viewId, boolean visible) {
        getView(viewId).setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public ViewHolder setPadding(int viewId, int left, int top, int right, int bottom) {
        getView(viewId).setPadding(left, top, right, bottom);
        return this;
    }

    public ViewHolder linkify(int viewId) {
        Linkify.addLinks((TextView) getView(viewId), Linkify.PHONE_NUMBERS);
        return this;
    }

    public ViewHolder setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = (TextView) getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | 128);
        }
        return this;
    }

    public ViewHolder setProgress(int viewId, int progress) {
        ((ProgressBar) getView(viewId)).setProgress(progress);
        return this;
    }

    public ViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = (ProgressBar) getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public ViewHolder setMax(int viewId, int max) {
        ((ProgressBar) getView(viewId)).setMax(max);
        return this;
    }

    public ViewHolder setRating(int viewId, float rating) {
        ((RatingBar) getView(viewId)).setRating(rating);
        return this;
    }

    public ViewHolder setRating(int viewId, float rating, int max) {
        RatingBar view = (RatingBar) getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    public ViewHolder setTag(int viewId, Object tag) {
        getView(viewId).setTag(tag);
        return this;
    }

    public ViewHolder setTag(int viewId, int key, Object tag) {
        getView(viewId).setTag(key, tag);
        return this;
    }

    public ViewHolder setChecked(int viewId, boolean checked) {
        ((Checkable) getView(viewId)).setChecked(checked);
        return this;
    }

    public ViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        getView(viewId).setOnClickListener(listener);
        return this;
    }

    public ViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        getView(viewId).setOnTouchListener(listener);
        return this;
    }

    public ViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        getView(viewId).setOnLongClickListener(listener);
        return this;
    }

    public ViewHolder setView(int viewId, View view) {
        ((LinearLayout) getView(viewId)).addView(view);
        return this;
    }

    public ViewHolder setAdapter(int viewId, ListAdapter adapter) {
        ((ListView) getView(viewId)).setAdapter(adapter);
        return this;
    }

    public ViewHolder setGridAdapter(int viewId, ListAdapter adapter) {
        ((GridView) getView(viewId)).setAdapter(adapter);
        return this;
    }
}
