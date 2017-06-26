package com.xinyuan.xyshop.ui.mine.info;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.bumptech.glide.Glide;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.util.CallImageLoader;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.BottomPopupImage;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.inter.IHandlerCallBack;
import com.youth.xframe.utils.log.XLog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/6/26.
 */

public class UserInfoFragment extends BaseFragment {

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
	@BindView(R.id.customer_image)
	CircleImageView customer_image;
	private IHandlerCallBack iHandlerCallBack;
	TimePickerView pvTime;
	private GalleryConfig galleryConfig;
	private List<String> path = new ArrayList<>();

	public static UserInfoFragment newInstance() {
		UserInfoFragment fragment = new UserInfoFragment();
		return fragment;
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_user_info;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(getActivity(), msg_toolbar);
		setTime();
		initGallery();
		galleryConfig = new GalleryConfig.Builder()
				.imageLoader(new CallImageLoader())    // ImageLoader 加载框架（必填）
				.iHandlerCallBack(iHandlerCallBack)     // 监听接口（必填）
				.provider("com.xyshop.fileprovider")   // provider(必填)
				.pathList(path)                         // 记录已选的图片
				.multiSelect(false)                      // 是否多选   默认：false
				.multiSelect(false, 9)                   // 配置是否多选的同时 配置多选数量   默认：false ， 9
				.maxSize(9)                             // 配置多选时 的多选数量。    默认：9
				.crop(false)                             // 快捷开启裁剪功能，仅当单选 或直接开启相机时有效
				.crop(false, 1, 1, 500, 500)             // 配置裁剪功能的参数，   默认裁剪比例 1:1
				.isShowCamera(true)                     // 是否现实相机按钮  默认：false
				.filePath("/Gallery/Pictures")          // 图片存放路径
				.build();
	}

	@OnClick({R.id.rl_user_head, R.id.rl_user_name, R.id.rl_user_sex, R.id.rl_user_birth})
	public void onClick(View v) {

		switch (v.getId()) {
			case R.id.rl_user_birth:
				pvTime.show();
				break;
			case R.id.rl_user_head:
				BottomPopupImage bottomPopupOption = new BottomPopupImage(context);
				bottomPopupOption.setItemText("拍照", "选择相册");
				bottomPopupOption.showPopupWindow();
				bottomPopupOption.setItemClickListener(new BottomPopupImage.onPopupWindowItemClickListener() {
					@Override
					public void onItemClick(int position) {
						XLog.v("选择" + position);
						switch (position) {
							case 0:
								GalleryPick.getInstance().setGalleryConfig(galleryConfig).openCamera(getActivity());
								break;
							case 1:
								galleryConfig.getBuilder().isOpenCamera(false).build();
								GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(getActivity());
								break;
						}

					}
				});
				break;
			case R.id.rl_user_sex:
				final BottomPopupImage bottomPopupSex = new BottomPopupImage(context);
				bottomPopupSex.setItemText("男", "女");
				bottomPopupSex.showPopupWindow();
				bottomPopupSex.setItemClickListener(new BottomPopupImage.onPopupWindowItemClickListener() {
					@Override
					public void onItemClick(int position) {
						XLog.v("选择" + position);
						switch (position) {
							case 0:
								bottomPopupSex.dismiss();
								tv_user_sex.setText("男");
								break;
							case 1:
								sex = 1;
								bottomPopupSex.dismiss();
								tv_user_sex.setText("女");
								break;
						}

					}
				});
		}

	}

	private static int sex = 0;

	private void initGallery() {
		iHandlerCallBack = new IHandlerCallBack() {
			@Override
			public void onStart() {
				XLog.i("onStart: 开启");
			}

			@Override
			public void onSuccess(List<String> photoList) {
				XLog.i("onSuccess: 返回数据");
				path.clear();
				for (String s : photoList) {
					XLog.i(s);
					path.add(s);
				}
				XLog.list(path);
				Glide.with(context)
						.load(path.get(0))
						.into(customer_image);

			}

			@Override
			public void onCancel() {
				XLog.i("onCancel: 取消");
			}

			@Override
			public void onFinish() {
				XLog.i("onFinish: 结束");
			}

			@Override
			public void onError() {
				XLog.i("onError: 出错");
			}
		};
	}

	@BindView(R.id.tv_user_birth)
	TextView tv_user_birth;

	private void setTime() {
		Calendar selectedDate = Calendar.getInstance();
		Calendar startDate = Calendar.getInstance();
		startDate.set(1950, 0, 0);
		Calendar endDate = Calendar.getInstance();
		endDate.set(2018, 0, 0);

		pvTime = new TimePickerView.Builder(context, new TimePickerView.OnTimeSelectListener() {
			@Override
			public void onTimeSelect(Date date, View v) {//选中事件回调
				XLog.v("时间" + getTime(date));
				tv_user_birth.setText(getTime(date));
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
				.isCyclic(true)//是否循环滚动
				.setTitleColor(getResources().getColor(R.color.tv_name))//标题文字颜色
				.setSubmitColor(getResources().getColor(R.color.colorPrimaryDark))//确定按钮文字颜色
				.setCancelColor(getResources().getColor(R.color.tv_hint))//取消按钮文字颜色
				.setTitleBgColor(getResources().getColor(R.color.bg_white))//标题背景颜色 Night mode
				.setBgColor(getResources().getColor(R.color.bg_white))//滚轮背景颜色 Night mode
				.setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
				.setRangDate(startDate, endDate)//起始终止年月日设定
				.setLabel("年", "月", "日", "时", "分", "秒")
				.isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
				.build();
		pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
	}

	private String getTime(Date date) {//可根据需要自行截取数据显示
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
}
