package com.xinyuan.xyshop.ui.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseActivity;
import com.youth.xframe.utils.log.XLog;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/6/23.
 */

public class UserInfoActivity extends BaseActivity {

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

	@BindView(R.id.customer_image)
	CircleImageView customer_image;


	@Override
	public int getLayoutId() {
		return R.layout.activity_user_info;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	TimePickerView pvTime;

	@Override
	public void initView() {
		setTime();
	}


	@OnClick({R.id.rl_user_head, R.id.rl_user_name, R.id.rl_user_sex, R.id.rl_user_birth})
	public void onClick(View v) {

		switch (v.getId()) {
			case R.id.rl_user_birth:
				pvTime.show();
				break;
		}

	}

	private void setTime() {
		Calendar selectedDate = Calendar.getInstance();
		Calendar startDate = Calendar.getInstance();
		startDate.set(1950, 0, 0);
		Calendar endDate = Calendar.getInstance();
		endDate.set(2018, 0, 0);

		pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
			@Override
			public void onTimeSelect(Date date, View v) {//选中事件回调
				XLog.v("时间" + date.toString());
			}
		})
				.setType(new boolean[]{true, true, true, false, false, false})
				.setCancelText("取消")//取消按钮文字
				.setSubmitText("确定")//确认按钮文字
				.setTitleSize(12)//标题文字大小
				.setTitleText("选择生日")//标题文字
				.setContentSize(16)
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

}
