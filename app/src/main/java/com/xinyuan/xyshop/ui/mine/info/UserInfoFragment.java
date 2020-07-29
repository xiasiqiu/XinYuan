package com.xinyuan.xyshop.ui.mine.info;

import android.Manifest;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.bumptech.glide.Glide;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.bean.UserInfoBean;
import com.xinyuan.xyshop.callback.DialogCallback;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.even.TabSelectedEvent;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.BottomPopupImage;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.permission.XPermission;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.badgeview.BGABadgeImageView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by fx on 2017/6/26.
 * 用户个人信息fragment
 */

public class UserInfoFragment extends BaseFragment {
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    @BindView(R.id.rl_user_head)
    RelativeLayout rl_user_head;
    @BindView(R.id.rl_user_name)
    RelativeLayout rl_user_name;
    @BindView(R.id.rl_user_sex)
    RelativeLayout rl_user_sex;
    @BindView(R.id.rl_user_birth)
    RelativeLayout rl_user_birth;


    @BindView(R.id.tv_user_name)
    TextView tv_user_name;
    @BindView(R.id.tv_user_sex)
    TextView tv_user_sex;
    @BindView(R.id.toolbar_iv)
    Toolbar msg_toolbar;
    @BindView(R.id.tv_header_center)
    TextView tv_header_center;
    @BindView(R.id.iv_header_right)
    BGABadgeImageView iv_header_right;
    @BindView(R.id.iv_header_left)
    ImageView iv_header_left;
    @BindView(R.id.iv_user_head)
    CircleImageView iv_user_head;

    TimePickerView pvTime;
    private static UserInfoBean userInfoBean;

    private static String birthday = null;
    private static String sex = null;

    public static UserInfoFragment newInstance(UserInfoBean userInfoBean) {
        UserInfoFragment fragment = new UserInfoFragment();
        UserInfoFragment.userInfoBean = userInfoBean;
        return fragment;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_user_info;
    }

    @Override
    public void initView(View rootView) {
        EventBus.getDefault().register(this);
        SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
        SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
        CommUtil.initToolBar(getActivity(), iv_header_left, iv_header_right);
    }

    @Override
    public void initData() {
        tv_header_center.setText("个人资料");
        selImageList = new ArrayList<>();
        if (!XEmptyUtils.isEmpty(userInfoBean)) {
            GlideImageLoader.setCircleImageView(mContext, userInfoBean.getUserPhoto(), iv_user_head);
            tv_user_name.setText(userInfoBean.getUserName());
            tv_user_birth.setText(userInfoBean.getBirthday());
            if (userInfoBean.getUserSex() == 1) {
                tv_user_sex.setText("男");

            } else if (userInfoBean.getUserSex() == 0) {
                tv_user_sex.setText("女");

            } else {
                tv_user_sex.setText("保密");
            }

        } else {
            _mActivity.onBackPressed();
        }
        setTime();
    }

    @OnClick({R.id.rl_user_head, R.id.rl_user_name, R.id.rl_user_sex, R.id.rl_user_birth})
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.rl_user_birth:
                pvTime.show();
                break;
            case R.id.rl_user_head:

                XPermission.requestPermissions(mContext, 100, new String[]{Manifest.permission
                        .WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, new XPermission.OnPermissionListener() {
                    //权限申请成功时调用
                    @Override
                    public void onPermissionGranted() {
                        final BottomPopupImage bottomPopupOption = new BottomPopupImage(mContext);
                        bottomPopupOption.setItemText(getString(R.string.take_pic), getString(R.string.chose_pic));
                        bottomPopupOption.showPopupWindow();
                        bottomPopupOption.setItemClickListener(new BottomPopupImage.onPopupWindowItemClickListener() {
                            @Override
                            public void onItemClick(int position) {

                                switch (position) {
                                    case 0:
                                        Intent intent = new Intent(getActivity(), ImageGridActivity.class);
                                        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                        startActivityForResult(intent, REQUEST_CODE_SELECT);
                                        bottomPopupOption.dismiss();
                                        break;
                                    case 1:
                                        Intent intent1 = new Intent(getActivity(), ImageGridActivity.class);
                                        startActivityForResult(intent1, REQUEST_CODE_SELECT);
                                        bottomPopupOption.dismiss();
                                        break;
                                }

                            }
                        });
                    }

                    //权限被用户禁止时调用
                    @Override
                    public void onPermissionDenied() {

                        XPermission.showTipsDialog(mContext);
                    }
                });


                break;
            case R.id.rl_user_sex:
                final BottomPopupImage bottomPopupSex = new BottomPopupImage(mContext);
                bottomPopupSex.setItemText("女", "男", "保密");
                bottomPopupSex.showPopupWindow();
                bottomPopupSex.setItemClickListener(new BottomPopupImage.onPopupWindowItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        switch (position) {
                            case 0:
                                bottomPopupSex.dismiss();
                                tv_user_sex.setText("女");
                                sex = "0";
                                updateInfo();
                                break;
                            case 1:
                                bottomPopupSex.dismiss();
                                tv_user_sex.setText("男");
                                sex = "1";
                                updateInfo();
                                break;
                            case 2:
                                bottomPopupSex.dismiss();
                                tv_user_sex.setText("保密");
                                sex = "-1";
                                updateInfo();
                                break;
                        }


                    }
                });
        }

    }

    ArrayList<ImageItem> images = null;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片

    @Override
    public void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    File file = new File(selImageList.get(0).path);
                    updateHead(file);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    File file = new File(selImageList.get(0).path);
                    updateHead(file);
                }
            }
        }
    }


    private void updateHead(File file) {
        OkGo.<LzyResponse<TokenBean>>post(Urls.URL_UPDATE_USERHEAD)//
                .tag(this)//
                .headers("token", MyShopApplication.Token)//
                .params("id", MyShopApplication.userId)
                .params("file", file)
                .execute(new DialogCallback<LzyResponse<TokenBean>>(getActivity(), "修改中...") {
                    @Override
                    public void onSuccess(Response<LzyResponse<TokenBean>> response) {
                        if (HttpUtil.handleResponse(mContext, response.body())) {
                            GlideImageLoader.setCircleImageView(mContext, response.body().datas.getUserPhoto(), iv_user_head);
                            EventBus.getDefault().post(new TabSelectedEvent(500));
                            XToast.info("头像修改成功！");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<TokenBean>> response) {
                        HttpUtil.handleError(mContext, response);
                    }
                });
    }

    private void updateInfo() {
        OkGo.<LzyResponse<TokenBean>>post(Urls.URL_UPDATE_USER)//
                .tag(this)//
                .headers("token", MyShopApplication.Token)//
                .params("id", MyShopApplication.userId)
                .params("birthday", birthday)
                .params("userSex", sex)
                .execute(new DialogCallback<LzyResponse<TokenBean>>(getActivity(), "修改中...") {
                    @Override
                    public void onSuccess(Response<LzyResponse<TokenBean>> response) {
                        if (HttpUtil.handleResponse(mContext, response.body())) {
                            if (!XEmptyUtils.isEmpty(sex)) {
                                if (sex.equals("1")) {
                                    tv_user_sex.setText("男");

                                } else if (sex.equals("0")) {
                                    tv_user_sex.setText("女");

                                } else {
                                    tv_user_sex.setText("保密");
                                }
                            }


                            XToast.info("修改成功！");

                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<TokenBean>> response) {
                        HttpUtil.handleError(mContext, response);
                    }
                });

    }

    @BindView(R.id.tv_user_birth)
    TextView tv_user_birth;

    private void setTime() {
        Calendar defaulDate = Calendar.getInstance();
        defaulDate.set(1994, 0, 0);
        Calendar startDate = Calendar.getInstance();
        startDate.set(1950, 0, 0);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2017, 0, 0);

        pvTime = new TimePickerView.Builder(mContext, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                tv_user_birth.setText(getTime(date));
                birthday = getTime(date);
                updateInfo();
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setTitleSize(16)//标题文字大小
                .setSubCalSize(13)
                .setTitleText("选择生日")//标题文字
                .setContentSize(25)
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(false)//是否循环滚动
                .setTitleColor(getResources().getColor(R.color.tv_name))//标题文字颜色
                .setSubmitColor(getResources().getColor(R.color.colorPrimaryDark))//确定按钮文字颜色
                .setCancelColor(getResources().getColor(R.color.tv_hint))//取消按钮文字颜色
                .setTitleBgColor(getResources().getColor(R.color.bg_white))//标题背景颜色 Night mode
                .setBgColor(getResources().getColor(R.color.bg_white))//滚轮背景颜色 Night mode
                .setDate(defaulDate)
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。


    }

    @Override
    public boolean onBackPressedSupport() {
        EventBus.getDefault().post(new TabSelectedEvent(3));
        return super.onBackPressedSupport();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @Subscribe
    public void onTabSelectedEvent(TokenBean event) {

    }


    @Override
    public void onSupportVisible() { //当fragment可见时，检查是否有新消息
        if (MyShopApplication.IsNewMsg) {
            iv_header_right.showCirclePointBadge();
        } else {
            iv_header_right.hiddenBadge();
        }
        super.onSupportVisible();
    }


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }


}
