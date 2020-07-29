package com.xinyuan.xyshop.ui.mine.msg;

import android.Manifest;
import android.content.Intent;
import android.view.View;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.entity.MessageInfo;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.utils.permission.XPermission;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;

import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/4.
 */

public class ChatFunctionFragment extends BaseFragment<BasePresenter> {
	public static final int IMAGE_ITEM_ADD = -1;
	public static final int REQUEST_CODE_SELECT = 100;
	public static final int REQUEST_CODE_PREVIEW = 101;

	@Override
	public void initView(View rootView) {

	}

	@Override
	public void initData() {

	}

	@OnClick({R.id.chat_function_photo, R.id.chat_function_photograph})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.chat_function_photograph:
				XPermission.requestPermissions(getContext(), 100, new String[]{Manifest.permission
						.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, new XPermission.OnPermissionListener() {
					//权限申请成功时调用
					@Override
					public void onPermissionGranted() {
						Intent intent1 = new Intent(getActivity(), ImageGridActivity.class);
						startActivityForResult(intent1, REQUEST_CODE_SELECT);
					}

					//权限被用户禁止时调用
					@Override
					public void onPermissionDenied() {
						XPermission.showTipsDialog(getContext());
					}
				});
				break;
			case R.id.chat_function_photo:
				XPermission.requestPermissions(getContext(), 100, new String[]{Manifest.permission
						.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, new XPermission.OnPermissionListener() {
					//权限申请成功时调用
					@Override
					public void onPermissionGranted() {
						Intent intent = new Intent(getActivity(), ImageGridActivity.class);
						intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
						startActivityForResult(intent, REQUEST_CODE_SELECT);
					}

					//权限被用户禁止时调用
					@Override
					public void onPermissionDenied() {
						XPermission.showTipsDialog(getContext());
					}
				});
				break;
		}
	}

	ArrayList<ImageItem> images = null;
	private ArrayList<ImageItem> selImageList; //当前选择的所有图片

	@Override
	public void onActivityResult(final int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		selImageList = new ArrayList<>();
		if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
			//添加图片返回
			if (data != null && requestCode == REQUEST_CODE_SELECT) {
				images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
				if (images != null) {
					selImageList.addAll(images);
					XLog.v(selImageList.get(0).path);
					File file = new File(selImageList.get(0).path);
					MessageInfo messageInfo = new MessageInfo();
					messageInfo.setImageUrl(file.getPath());
					EventBus.getDefault().post(messageInfo);
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
					XLog.list(selImageList);
					MessageInfo messageInfo = new MessageInfo();
					messageInfo.setImageUrl(file.getPath());
					EventBus.getDefault().post(messageInfo);
				}
			}
		}
	}

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_chat_function;
	}
}
