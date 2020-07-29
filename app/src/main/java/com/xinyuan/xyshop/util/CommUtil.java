package com.xinyuan.xyshop.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.common.state.LoginContext;
import com.xinyuan.xyshop.ui.home.ScanActivity;
import com.youth.xframe.utils.XEmptyUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/16.
 */

public class CommUtil {
    public static File logDir;

    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static void gotoActivity(Context poFrom, Class<?> poTo, boolean pbFinish, Map<String, String> pmExtra) {
        Intent loIntent = new Intent(poFrom, poTo);
        loIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (!(pmExtra == null || pmExtra.isEmpty())) {
            for (String lsKey : pmExtra.keySet()) {
                loIntent.putExtra(lsKey, (String) pmExtra.get(lsKey));
            }
        }
        if (pbFinish) {
            ((Activity) poFrom).finish();
        }
        poFrom.startActivity(loIntent);
    }


    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static String getText(EditText view) {
        return view.getText().toString();
    }

    public static String getText(TextView view) {
        return view.getText().toString();
    }

    public static String getText(Button view) {
        return view.getText().toString().trim();
    }

    public static boolean isNotEmpty(CharSequence... cs) {
        for (CharSequence c : cs) {
            if (isEmpty(c)) {
                return false;
            }
        }
        return true;
    }

    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime;

    /**
     * 防止因网络等原因卡顿点击多次
     *
     * @return
     */
    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }

    public static void createDir() {
        if ("mounted".equals(Environment.getExternalStorageState())) {
            File rootDir = new File(Constants.APP_DIR);
            if (!rootDir.exists()) {
                rootDir.mkdirs();
            }
            File cacheDir = new File(Constants.CACHE_DIR);
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            logDir = new File(Constants.LOG_DIR);
            if (!logDir.exists()) {
                logDir.mkdirs();
            }
        }
    }

    public static int getScreenWeight(Context context) {
        if (context == null) {
            return 0;
        }
        DisplayMetrics dm = new DisplayMetrics();
        return context.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
    }

    public static String getSubString(Context context, TextView tv, String content, int maxLine) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        float width = tv.getPaint().measureText(content);
        //这里只是为了方便，用屏幕宽度代替了textview控件宽度，如果需要精准控制，可以换成控件宽度
        float tvWidth = wm.getDefaultDisplay().getWidth();
        if (width / tvWidth > (maxLine + 0.3)) {
            return content.substring(0, (int) (content.length() / (width / tvWidth) / (maxLine + 0.3))) + "...";
        }
        return content;
    }

    //抖动动画CycleTimes动画重复的次数
    public static Animation shakeAnimation(int CycleTimes) {
        Animation translateAnimation = new TranslateAnimation(0, 6, 0, 6);
        translateAnimation.setInterpolator(new CycleInterpolator(CycleTimes));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }

    public static Point getScreenSize(Context context) {
        Point point = new Point();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getSize(point);
        return point;
    }

    public static AnimationSet getInAnimationTest(Context context) {
        AnimationSet out = new AnimationSet(context, null);
        AlphaAnimation alpha = new AlphaAnimation(0.0f, 1.0f);
        alpha.setDuration(150);
        ScaleAnimation scale = new ScaleAnimation(0.6f, 1.0f, 0.6f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(150);
        out.addAnimation(alpha);
        out.addAnimation(scale);
        return out;
    }

    public static AnimationSet getOutAnimationTest(Context context) {
        AnimationSet out = new AnimationSet(context, null);
        AlphaAnimation alpha = new AlphaAnimation(1.0f, 0.0f);
        alpha.setDuration(150);
        ScaleAnimation scale = new ScaleAnimation(1.0f, 0.6f, 1.0f, 0.6f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(150);
        out.addAnimation(alpha);
        out.addAnimation(scale);
        return out;
    }


    public static Object getStoreCredit(Context context, int level) {
        int drawable = 0;
        switch (level) {
            case 1:
                drawable = R.drawable.level_1;
                break;
            case 2:
                drawable = R.drawable.level_2;
                break;
            case 3:
                drawable = R.drawable.level_3;
                break;
            case 4:
                drawable = R.drawable.level_4;
                break;
            case 5:
                drawable = R.drawable.level_5;
                break;
            case 6:
                drawable = R.drawable.level_6;
                break;
            case 7:
                drawable = R.drawable.level_7;
                break;
            case 8:
                drawable = R.drawable.level_8;
                break;
            case 9:
                drawable = R.drawable.level_9;
                break;
            case 10:
                drawable = R.drawable.level_10;
                break;
            case 11:
                drawable = R.drawable.level_11;
                break;
            case 12:
                drawable = R.drawable.level_12;
                break;
            case 13:
                drawable = R.drawable.level_13;
                break;
            case 14:
                drawable = R.drawable.level_14;
                break;
            case 15:
                drawable = R.drawable.level_15;
                break;
        }
        return drawable;

    }


    public static void initToolBar(final Activity activity, ImageView iv_header_left, ImageView iv_header_right) {

        if (iv_header_left != null) {
            iv_header_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.onBackPressed();
                }
            });
        }

        if (iv_header_right != null) {
            iv_header_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LoginContext.getInstance().showMsg(activity);
                }
            });
        }

    }

    public static void initScanTool(final Activity activity, ImageView iv_scan, ImageView iv_msg) {
        iv_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommUtil.gotoActivity(activity, ScanActivity.class, false, null);

            }
        });
        iv_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginContext.getInstance().showMsg(activity);
            }
        });


    }


    public static void initToolBar(final FragmentActivity activity, final Context context, ImageView iv_header_left, ImageView iv_header_right) {
        if (iv_header_left != null) {
            iv_header_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.onBackPressed();
                }
            });
        }

        if (iv_header_right != null) {
            iv_header_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LoginContext.getInstance().showMsg(activity);
                }
            });

        }


    }

    public static List<String> distinctList(List<String> list) {
        List<String> newList = new ArrayList<>();
        for (String s : list) {
            if (!newList.contains(s)) {
                newList.add(s);
            }
        }
        return newList;
    }


    public static int chose;

    /**
     * 服务器图片地址包含\字符，此方法进行转换
     *
     * @param po
     * @return
     */
    public static String getUrl(String po) {
        if (!XEmptyUtils.isEmpty(po)) {
            String url = po.replace("\\", "/");
            return url;

        } else {
            return "";
        }

    }


}