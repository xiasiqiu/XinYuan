package com.xinyuan.xyshop.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.entity.MsgMultipleItem;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.widget.BubbleImageView;
import com.xinyuan.xyshop.widget.BubbleLinearLayout;
import com.xinyuan.xyshop.widget.GifTextView;
import com.xinyuan.xyshop.widget.dialog.ChatBigImageDialog;
import com.youth.xframe.utils.XDensityUtils;
import com.youth.xframe.utils.XEmptyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fx on 2017/8/4.
 * 聊天详情Adapter
 */

public class ChatDetailAdapter extends BaseMultiItemQuickAdapter<MsgMultipleItem, BaseViewHolder> {

    private RelativeLayout.LayoutParams layoutParams;
    public Handler handler;
    private Activity activity;
    public static final int REQUEST_CODE_PREVIEW = 101;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ChatDetailAdapter(Activity activity, List<MsgMultipleItem> data) {
        super(data);
        addItemType(MsgMultipleItem.CHAT_ITEM_LEFT, R.layout.item_chat_left);
        addItemType(MsgMultipleItem.CHAT_ITEM_RIGHT, R.layout.item_chat_right);
        addItemType(MsgMultipleItem.CHAT_TIME_CENTER, R.layout.item_chat_center);
        handler = new Handler();
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, MsgMultipleItem item) {
        switch (item.getItemType()) {
            case MsgMultipleItem.CHAT_ITEM_LEFT:
                final List<ImageItem> imgs = new ArrayList<>();
                TextView chatItemDate = helper.getView(R.id.chat_item_date);
                ImageView chatItemHeader = helper.getView(R.id.chat_item_header);
                GifTextView chatItemContentText = helper.getView(R.id.chat_item_content_text);
                BubbleImageView chatItemContentImage = helper.getView(R.id.chat_item_content_image);
                BubbleLinearLayout chatItemLayoutContent = helper.getView(R.id.chat_item_layout_content);
                layoutParams = (RelativeLayout.LayoutParams) chatItemLayoutContent.getLayoutParams();
                chatItemDate.setText(item.getTime() != null ? item.getTime() : "");
                GlideImageLoader.setUrlImg(mContext, item.getHeader(), chatItemHeader);
                if (!XEmptyUtils.isEmpty(item.getContent())) {
                    chatItemContentText.setSpanText(handler, item.getContent(), true);
                    chatItemContentText.setVisibility(View.VISIBLE);
                    chatItemLayoutContent.setVisibility(View.VISIBLE);
                    chatItemContentImage.setVisibility(View.GONE);
                    TextPaint paint = chatItemContentText.getPaint();
                    // 计算textview在屏幕上占多宽
                    int len = (int) paint.measureText(chatItemContentText.getText().toString().trim());
                    if (len < XDensityUtils.dp2px(200)) {
                        layoutParams.width = len + XDensityUtils.dp2px(30);
                    } else {
                        layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
                    }
                    layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    chatItemLayoutContent.setLayoutParams(layoutParams);
                } else if (!XEmptyUtils.isEmpty(item.getImageUrl())) {
                    chatItemLayoutContent.setVisibility(View.GONE);
                    chatItemContentText.setVisibility(View.GONE);
                    chatItemContentImage.setVisibility(View.VISIBLE);
                    GlideImageLoader.setBubbleImg(mContext, item.getImageUrl(), chatItemContentImage);
                    layoutParams.width = XDensityUtils.dp2px(120);
                    layoutParams.height = XDensityUtils.dp2px(48);
                    chatItemLayoutContent.setLayoutParams(layoutParams);


                    chatItemContentImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ChatBigImageDialog dialog = new ChatBigImageDialog(mContext, item.getImageUrl());
                            Window dialogWindow = dialog.getWindow();
                            dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);
                            dialog.show();
                            DisplayMetrics dm = new DisplayMetrics();
                            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
                            wm.getDefaultDisplay().getMetrics(dm);
                            dialogWindow.setLayout(dm.widthPixels, dialogWindow.getAttributes().height);

                        }
                    });
                }
                break;
            case MsgMultipleItem.CHAT_ITEM_RIGHT:

                TextView chatItemDate1 = helper.getView(R.id.chat_item_date);
                ImageView chatItemHeader1 = helper.getView(R.id.chat_item_header);
                GifTextView chatItemContentText1 = helper.getView(R.id.chat_item_content_text);
                BubbleImageView chatItemContentImage1 = helper.getView(R.id.chat_item_content_image);
                ImageView chatItemFail1 = helper.getView(R.id.chat_item_fail);
                ProgressBar chatItemProgress1 = helper.getView(R.id.chat_item_progress);
                BubbleLinearLayout chatItemLayoutContent1 = helper.getView(R.id.chat_item_layout_content);

                layoutParams = (RelativeLayout.LayoutParams) chatItemLayoutContent1.getLayoutParams();

                chatItemDate1.setText(item.getTime() != null ? item.getTime() : "");
                GlideImageLoader.setUrlImg(mContext, item.getHeader(), chatItemHeader1);
                if (!XEmptyUtils.isEmpty(item.getContent())) {
                    chatItemContentText1.setSpanText(handler, item.getContent(), true);
                    chatItemContentText1.setVisibility(View.VISIBLE);
                    chatItemLayoutContent1.setVisibility(View.VISIBLE);
                    chatItemContentImage1.setVisibility(View.GONE);
                    TextPaint paint = chatItemContentText1.getPaint();
                    // 计算textview在屏幕上占多宽
                    int len = (int) paint.measureText(chatItemContentText1.getText().toString().trim());
                    if (len < XDensityUtils.dp2px(200)) {
                        layoutParams.width = len + XDensityUtils.dp2px(30);
                    } else {
                        layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
                    }
                    layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    chatItemLayoutContent1.setLayoutParams(layoutParams);
                } else if (!XEmptyUtils.isEmpty(item.getImageUrl())) {
                    chatItemLayoutContent1.setVisibility(View.GONE);
                    chatItemContentText1.setVisibility(View.GONE);
                    chatItemContentImage1.setVisibility(View.VISIBLE);
                    GlideImageLoader.setBubbleImg(mContext, item.getImageUrl(), chatItemContentImage1);
                    layoutParams.width = XDensityUtils.dp2px(120);
                    layoutParams.height = XDensityUtils.dp2px(48);
                    chatItemLayoutContent1.setLayoutParams(layoutParams);
                    chatItemContentImage1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ChatBigImageDialog dialog = new ChatBigImageDialog(mContext, item.getImageUrl());
                            Window dialogWindow = dialog.getWindow();
                            dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);
                            dialog.show();
                            DisplayMetrics dm = new DisplayMetrics();
                            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
                            wm.getDefaultDisplay().getMetrics(dm);
                            dialogWindow.setLayout(dm.widthPixels, dialogWindow.getAttributes().height);
                        }
                    });
                }

                switch (item.getSendState()) {
//					case MsgMultipleItem.CHAT_ITEM_SENDING:
//						chatItemProgress1.setVisibility(View.VISIBLE);
//						chatItemFail1.setVisibility(View.GONE);
//						break;
                    case MsgMultipleItem.CHAT_ITEM_SEND_ERROR:
                        chatItemProgress1.setVisibility(View.GONE);
                        chatItemFail1.setVisibility(View.VISIBLE);
                        break;
                    case MsgMultipleItem.CHAT_ITEM_SEND_SUCCESS:
                        chatItemProgress1.setVisibility(View.GONE);
                        chatItemFail1.setVisibility(View.GONE);
                        break;
                }
                break;
            case MsgMultipleItem.CHAT_TIME_CENTER:
                TextView tv_time = helper.getView(R.id.tv_time);
                tv_time.setText(item.getTime());
                break;
        }

    }
}
