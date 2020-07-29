package com.xinyuan.xyshop.http;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.common.Constants;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.ui.mine.login.LoginActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.widget.dialog.color.ColorDialog;
import com.youth.xframe.common.XActivityStack;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import java.util.Set;

import okhttp3.Call;
import okhttp3.Headers;

import static com.xinyuan.xyshop.util.CommUtil.getInAnimationTest;
import static com.xinyuan.xyshop.util.CommUtil.getOutAnimationTest;

/**
 * Created by Administrator on 2017/7/20.
 */

public class HttpUtil {

    public static <T> boolean handleResponse(Context context, LzyResponse<T> response) {
        boolean flag = false;
        if (XEmptyUtils.isEmpty(response)) {
            flag = false;
        } else {
            int code = response.getCode();
            switch (code) {
                case Constants.HTTP_200:
                    flag = true;
                    break;
                case Constants.HTTP_100:
                    XToast.error("用户已存在！");
                    flag = false;
                    break;
                case Constants.HTTP_101:
                    XToast.error("用户不存在！");
                    flag = false;
                    break;
                case Constants.HTTP_102:
                    XToast.error("密码错误！");
                    flag = false;
                    break;
                case Constants.HTTP_103:
                    XToast.error("用户已被冻结！");
                    flag = false;
                    break;
                case Constants.HTTP_104:
                    XToast.error("您已长时间未登录，请重新登录！");
                    CommUtil.gotoActivity(context, LoginActivity.class, false, null);
                    flag = false;
                    break;
                case Constants.HTTP_105:
                    XToast.error("该手机号号未绑定！");
                    flag = false;
                    break;
                case Constants.HTTP_106:
                    XToast.error("验证码错误！");
                    flag = false;
                    break;

                case Constants.HTTP_107:
                    XToast.error("验证码超时！");
                    flag = false;
                    break;

                case Constants.HTTP_108:
                    XToast.error("发送验证码时间间隔不足一分钟！");
                    flag = false;
                    break;

                case Constants.HTTP_109:
                    XToast.error("图片上传失败！");
                    flag = false;
                    break;
                case Constants.HTTP_110:
                    XToast.error("手机号已注册！");
                    flag = false;
                    break;
                case Constants.HTTP_111:
                    XToast.error("手机号未注册！");
                    flag = false;
                    break;
                case Constants.HTTP_112:
                    XToast.error("格式错误！");
                    flag = false;
                    break;
                case Constants.HTTP_113:
                    XToast.error("操作失败！");
                    flag = false;
                    break;
                case Constants.HTTP_114:
                    XToast.error("格式错误！");
                    flag = false;
                    break;
                case Constants.HTTP_116:
                    XToast.error("店铺不存在");
                    flag = false;
                    break;
                case Constants.HTTP_118:
                    XToast.error("商品不存在");
                    flag = false;
                    break;
                case Constants.HTTP_201:
                    XToast.error("对不起，您购买商品库存不足");
                    flag = false;
                    break;
                case Constants.HTTP_202:
                    XToast.error("购买数量超过限制");
                    flag = false;
                    break;
                case Constants.HTTP_211:
                    XToast.error("订单操作失败");
                    flag = false;
                    break;
                case Constants.HTTP_205:
                    XToast.error("已领取");
                    flag = false;
                    break;
                case Constants.HTTP_204:
                    XToast.error("优惠券不存在");
                    flag = false;
                    break;
                case Constants.HTTP_203:
                    XToast.error("该红包不存在");
                    flag = false;
                    break;
                case Constants.HTTP_206:
                    XToast.error("商品已下架！");
                    flag = false;
                    break;
                case Constants.HTTP_207:
                    flag = false;
                    XToast.error("账号已绑定快捷方式！");
                    break;
                case Constants.HTTP_212:
                    XToast.error("邮箱已被绑定");
                    flag = false;
                    break;
                case Constants.HTTP_213:
                    flag = false;
                    XToast.error("当前只有一种登录方式，不允许解除绑定");
                    break;
                case Constants.HTTP_301:
                    XToast.error("二维码超时");
                    flag = false;
                    break;
                case Constants.HTTP_302:
                    XToast.error("二维码已失效");
                    flag = false;
                    break;
                case Constants.HTTP_215:
                    XToast.error("抱歉，该商品暂不提供购买服务");
                    flag = true;
                    break;
                case Constants.HTTP_216:
                    XToast.error("抱歉，该店铺暂不开放");
                    flag = true;
                    break;

            }
        }

        return flag;
    }

    public static <T> void handleError(Context context, Response<T> response) {
        if (response == null) return;
        if (response.getException() != null)
            response.getException().printStackTrace();
        StringBuilder sb;
        Call call = response.getRawCall();
        if (call != null) {
            XLog.v("请求失败  请求内容：" + call.request().method() + "\n" + "url：" + call.request().url());
            Headers requestHeadersString = call.request().headers();
            Set<String> requestNames = requestHeadersString.names();
            sb = new StringBuilder();
            for (String name : requestNames) {
                sb.append(name).append(" ： ").append(requestHeadersString.get(name)).append("\n");
            }
        }

        okhttp3.Response rawResponse = response.getRawResponse();
        XLog.v("?=" + (rawResponse == null));
        if (rawResponse != null) {
            switch (rawResponse.code()) {
                case 400:
                    //XToast.error("400错误");
                    break;
                case 404:
                    //XToast.error("404错误");
                    break;
                case 500:
                    //XToast.error("500错误");
                    break;
                case 505:
                    //XToast.error("505错误");
                    break;

            }
        } else {
            showError(context, "与服务器连接失败,请关闭APP重启");
        }
    }

    public static void showError(Context context, String content) {
        if (!XEmptyUtils.isEmpty(context)) {
            ColorDialog dialog = new ColorDialog(context);
            dialog.setAnimationEnable(true);
            dialog.setContentText(content);
            dialog.setAnimationIn(getInAnimationTest(context));
            dialog.setAnimationOut(getOutAnimationTest(context));
            dialog.setPositiveListener("我知道了", new ColorDialog.OnPositiveListener() {
                @Override
                public void onClick(ColorDialog dialog) {
                    dialog.dismiss();
                    XActivityStack.getInstance().appExit();
                }
            })
                    .show();
        }
    }

}
