package com.xinyuan.xyshop.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinyuan.xyshop.entity.MessageInfo;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2017/8/4.
 * 输入框管理类
 */

public class EmotionInputDetector {

	private static final String SHARE_PREFERENCE_NAME = "com.dss886.emotioninputdetector";
	private static final String SHARE_PREFERENCE_TAG = "soft_input_height";

	private Activity mActivity;
	private InputMethodManager mInputManager;
	private SharedPreferences sp;
	private View mEmotionLayout;
	private EditText mEditText;
	private TextView mVoiceText;
	private View mContentView;
	private ViewPager mViewPager;
	private View mSendButton;
	private View mAddButton;
	private Boolean isShowEmotion = false;
	private Boolean isShowAdd = false;
	private TextView mPopVoiceText;

	private EmotionInputDetector() {
	}

	public static EmotionInputDetector with(Activity activity) {
		EmotionInputDetector emotionInputDetector = new EmotionInputDetector();
		emotionInputDetector.mActivity = activity;
		emotionInputDetector.mInputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		emotionInputDetector.sp = activity.getSharedPreferences(SHARE_PREFERENCE_NAME, Context.MODE_PRIVATE);
		return emotionInputDetector;
	}

	public EmotionInputDetector bindToContent(View contentView) {
		mContentView = contentView;
		return this;
	}

	public EmotionInputDetector bindToEditText(EditText editText) {
		mEditText = editText;
		mEditText.requestFocus();
		mEditText.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP && mEmotionLayout.isShown()) {
					lockContentHeight();
					hideEmotionLayout(true);

					mEditText.postDelayed(new Runnable() {
						@Override
						public void run() {
							unlockContentHeightDelayed();
						}
					}, 200L);
				}
				return false;
			}
		});

		mEditText.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (s.length() > 0) {
					mAddButton.setVisibility(View.GONE);
					mSendButton.setVisibility(View.VISIBLE);
				} else {
					mAddButton.setVisibility(View.VISIBLE);
					mSendButton.setVisibility(View.GONE);
				}
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		return this;
	}

	public EmotionInputDetector bindToEmotionButton(View emotionButton) {
		emotionButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mEmotionLayout.isShown()) {
					if (isShowAdd) {
						mViewPager.setCurrentItem(0);
						isShowEmotion = true;
						isShowAdd = false;
					} else {
						lockContentHeight();
						hideEmotionLayout(true);
						isShowEmotion = false;
						unlockContentHeightDelayed();
					}
				} else {
					if (isSoftInputShown()) {
						lockContentHeight();
						showEmotionLayout();
						unlockContentHeightDelayed();
					} else {
						showEmotionLayout();
					}
					mViewPager.setCurrentItem(0);
					isShowEmotion = true;
				}
			}
		});
		return this;
	}

	public EmotionInputDetector build() {
		mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN |
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		hideSoftInput();

		return this;
	}

	public EmotionInputDetector bindToAddButton(View addButton) {
		mAddButton = addButton;
		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mEmotionLayout.isShown()) {
					if (isShowEmotion) {
						mViewPager.setCurrentItem(1);
						isShowAdd = true;
						isShowEmotion = false;
					} else {
						lockContentHeight();
						hideEmotionLayout(true);
						isShowAdd = false;
						unlockContentHeightDelayed();
					}
				} else {
					if (isSoftInputShown()) {
						lockContentHeight();
						showEmotionLayout();
						unlockContentHeightDelayed();
					} else {
						showEmotionLayout();
					}
					mViewPager.setCurrentItem(1);
					isShowAdd = true;
				}
			}
		});
		return this;
	}

	public EmotionInputDetector bindToSendButton(View sendButton) {
		mSendButton = sendButton;
		sendButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mAddButton.setVisibility(View.VISIBLE);
				mSendButton.setVisibility(View.GONE);
				MessageInfo messageInfo = new MessageInfo();
				messageInfo.setContent(mEditText.getText().toString());
				EventBus.getDefault().post(messageInfo);
				mEditText.setText("");
			}
		});
		return this;
	}

	public EmotionInputDetector bindToVoiceButton(View voiceButton) {
		voiceButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				hideEmotionLayout(false);
				hideSoftInput();
				mVoiceText.setVisibility(mVoiceText.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
				mEditText.setVisibility(mVoiceText.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
			}
		});
		return this;
	}


	private boolean wantToCancle(int x, int y) {
		// 超过按钮的宽度
		if (x < 0 || x > mVoiceText.getWidth()) {
			return true;
		}
		// 超过按钮的高度
		if (y < -50 || y > mVoiceText.getHeight() + 50) {
			return true;
		}
		return false;
	}

	public EmotionInputDetector setEmotionView(View emotionView) {
		mEmotionLayout = emotionView;
		return this;
	}

	public EmotionInputDetector setViewPager(ViewPager viewPager) {
		mViewPager = viewPager;
		return this;
	}


	public boolean interceptBackPress() {
		if (mEmotionLayout.isShown()) {
			hideEmotionLayout(false);
			return true;
		}
		return false;
	}

	private void showEmotionLayout() {
		int softInputHeight = getSupportSoftInputHeight();
		if (softInputHeight == 0) {
			softInputHeight = sp.getInt(SHARE_PREFERENCE_TAG, 787);
		}
		hideSoftInput();
		mEmotionLayout.getLayoutParams().height = softInputHeight;
		mEmotionLayout.setVisibility(View.VISIBLE);
	}

	public void hideEmotionLayout(boolean showSoftInput) {
		if (mEmotionLayout.isShown()) {
			mEmotionLayout.setVisibility(View.GONE);
			if (showSoftInput) {
				showSoftInput();
			}
		}
	}

	private void lockContentHeight() {
		LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mContentView.getLayoutParams();
		params.height = mContentView.getHeight();
		params.weight = 0.0F;
	}

	private void unlockContentHeightDelayed() {
		mEditText.postDelayed(new Runnable() {
			@Override
			public void run() {
				((LinearLayout.LayoutParams) mContentView.getLayoutParams()).weight = 1.0F;
			}
		}, 200L);
	}

	private void showSoftInput() {
		mEditText.requestFocus();
		mEditText.post(new Runnable() {
			@Override
			public void run() {
				mInputManager.showSoftInput(mEditText, 0);
			}
		});
	}

	public void hideSoftInput() {
		mInputManager.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
	}

	private boolean isSoftInputShown() {
		return getSupportSoftInputHeight() != 0;
	}

	private int getSupportSoftInputHeight() {
		Rect r = new Rect();
		mActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
		int screenHeight = mActivity.getWindow().getDecorView().getRootView().getHeight();
		int softInputHeight = screenHeight - r.bottom;
		if (Build.VERSION.SDK_INT >= 20) {
			// When SDK Level >= 20 (Android L), the softInputHeight will contain the height of softButtonsBar (if has)
			softInputHeight = softInputHeight - getSoftButtonsBarHeight();
		}
		if (softInputHeight < 0) {
			Log.w("EmotionInputDetector", "Warning: value of softInputHeight is below zero!");
		}
		if (softInputHeight > 0) {
			sp.edit().putInt(SHARE_PREFERENCE_TAG, softInputHeight).apply();
		}
		return softInputHeight;
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
	private int getSoftButtonsBarHeight() {
		DisplayMetrics metrics = new DisplayMetrics();
		mActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int usableHeight = metrics.heightPixels;
		mActivity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
		int realHeight = metrics.heightPixels;
		if (realHeight > usableHeight) {
			return realHeight - usableHeight;
		} else {
			return 0;
		}
	}
}
