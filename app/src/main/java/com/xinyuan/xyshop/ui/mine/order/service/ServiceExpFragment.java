package com.xinyuan.xyshop.ui.mine.order.service;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.okgo.OkGo;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.ImagePickerAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.ExpressCompanyBean;
import com.xinyuan.xyshop.bean.TokenBean;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.ServiceModel;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.EditTextWithDel;
import com.xinyuan.xyshop.widget.dialog.SelectDialog;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.XRegexUtils;
import com.youth.xframe.widget.XToast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/11.
 * 售后填单号Fragment
 */

public class ServiceExpFragment extends BaseFragment implements ImagePickerAdapter.OnRecyclerViewItemClickListener {

	@BindView(R.id.sp_chose)
	Spinner sp_chose;
	@BindView(R.id.toolbar_tv)
	Toolbar toolbar_iv;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;


	@BindView(R.id.ed_expressnum)
	EditTextWithDel ed_expressnum;
	@BindView(R.id.ed_phone)
	EditTextWithDel ed_phone;
	@BindView(R.id.ed_info)
	EditTextWithDel ed_info;
	@BindView(R.id.rv_good_pic)
	RecyclerView rv_good_pic;   //售后图片
	public static final int IMAGE_ITEM_ADD = -1;
	public static final int REQUEST_CODE_SELECT = 100;
	public static final int REQUEST_CODE_PREVIEW = 101;
	ArrayList<ImageItem> images = null;
	private ImagePickerAdapter adapter;
	private ArrayList<ImageItem> selImageList; //当前选择的所有图片
	private int maxImgCount = 3;               //允许选择图片最大数


	private ArrayAdapter<String> companyAdapter;
	private List<String> company;
	private List<ExpressCompanyBean> companyBeans;
	private long expressCompanyId;
	private long returnId;
	private String shipNumber;
	private String phone;
	private String msg;

	public static ServiceExpFragment newInstance(long returnId) {
		ServiceExpFragment fragment = new ServiceExpFragment();
		fragment.returnId = returnId;
		return fragment;
	}

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.activity_service_express;

	}


	@Override
	public void initView(View rootView) {
		SystemBarHelper.immersiveStatusBar(getActivity()); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_iv);
		CommUtil.initToolBar(getActivity(), iv_header_left, null);
		tv_header_center.setText("填写单号");
		selImageList = new ArrayList<>();
		adapter = new ImagePickerAdapter(mContext, selImageList, maxImgCount);
		adapter.setOnItemClickListener(this);
		rv_good_pic.setLayoutManager(new GridLayoutManager(mContext, 5));
		rv_good_pic.setHasFixedSize(true);
		rv_good_pic.setAdapter(adapter);

	}


	private void showCompany(List<ExpressCompanyBean> datas) {
		if (!XEmptyUtils.isEmpty(datas)) {
			companyBeans = datas;
			company = new ArrayList<String>();
			for (ExpressCompanyBean bean : datas) {
				company.add(bean.getExpressCompanyName());
			}
			companyAdapter = new ArrayAdapter<String>(mContext, R.layout.spinner_item, company);
			companyAdapter.setDropDownViewResource(R.layout.dropdown_stytle);
			companyAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
			sp_chose.setAdapter(companyAdapter);
			sp_chose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
				                           int pos, long id) {
					expressCompanyId = datas.get(pos).getExpressCompanyId();
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// Another interface callback
				}
			});
		} else {
			company = new ArrayList<String>();
			company.add("暂无支持的物流公司");
			companyAdapter = new ArrayAdapter<String>(mContext, R.layout.spinner_item, company);
			companyAdapter.setDropDownViewResource(R.layout.dropdown_stytle);
			companyAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
			sp_chose.setAdapter(companyAdapter);
		}
	}


	@Override
	public void initData() {
		getExPressInfo();
	}

	@OnClick({R.id.button})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.button:
				if (XEmptyUtils.isSpace(ed_expressnum.getText().toString().trim())) {
					XToast.error("请输入物流单号");
				} else if (XEmptyUtils.isSpace(ed_phone.getText().toString().trim())) {
					XToast.error("请输入联系电话");

				} else if (!XRegexUtils.checkMobile(ed_phone.getText().toString().trim())) {
					XToast.error("手机号码格式不正确");
				} else {
					shipNumber = ed_expressnum.getText().toString().trim();
					phone = ed_phone.getText().toString().trim();
					msg = ed_info.getText().toString().trim();
					ArrayList<File> files = new ArrayList<>();
					if (images != null && images.size() > 0) {
						for (int i = 0; i < images.size(); i++) {
							files.add(new File(images.get(i).path));
						}
					}
					postExpress(files);

				}

		}
	}


	private void getExPressInfo() {
		OkGo.<LzyResponse<List<ExpressCompanyBean>>>get(Urls.URL_EXPRESS_COMPANY)
				.execute(new JsonCallback<LzyResponse<List<ExpressCompanyBean>>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<List<ExpressCompanyBean>>> response) {
						if (HttpUtil.handleResponse(getActivity(), response.body())) {
							showCompany(response.body().getDatas());
						}
					}


					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<List<ExpressCompanyBean>>> response) {
						HttpUtil.handleError(getActivity(), response);
					}
				});


	}

	private void postExpress(ArrayList<File> files) {
		OkGo.<LzyResponse<TokenBean>>post(Urls.URL_ORDER_SERVICE_GOOD_RETURN)
				.tag(this)
				.headers("token", MyShopApplication.Token)
				.params("userId", MyShopApplication.userId)
				.params("returnId", returnId)
				.params("expressCompanyId", expressCompanyId)
				.params("shipNumber", shipNumber)
				.params("phone", phone)
				.params("msg", msg)
				.addFileParams("files", files)
				.execute(new JsonCallback<LzyResponse<TokenBean>>() {
					@Override
					public void onSuccess(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						if (HttpUtil.handleResponse(getActivity(), response.body())) {
							XToast.info("提交成功");
							_mActivity.onBackPressed();

						}
					}


					@Override
					public void onError(com.lzy.okgo.model.Response<LzyResponse<TokenBean>> response) {
						HttpUtil.handleError(getActivity(), response);
					}
				});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
			//添加图片返回
			if (data != null && requestCode == REQUEST_CODE_SELECT) {
				images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
				if (images != null) {
					selImageList.addAll(images);
					adapter.setImages(selImageList);

				}
			}
		} else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
			//预览图片返回
			if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
				images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
				if (images != null) {
					selImageList.clear();
					selImageList.addAll(images);
					adapter.setImages(selImageList);
				}
			}
		}
	}

	private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
		SelectDialog dialog = new SelectDialog(getActivity(), R.style
				.transparentFrameWindowStyle,
				listener, names);
		if (!getActivity().isFinishing()) {
			dialog.show();
		}
		return dialog;
	}

	@Override
	public void onItemClick(View view, int position) {
		switch (position) {
			case IMAGE_ITEM_ADD:
				List<String> names = new ArrayList<>();
				names.add("拍照");
				names.add("相册");
				showDialog(new SelectDialog.SelectDialogListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						switch (position) {
							case 0: // 直接调起相机
								/**
								 * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
								 *
								 * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
								 *
								 * 如果实在有所需要，请直接下载源码引用。
								 */
								//打开选择,本次允许选择的数量
								ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
								Intent intent = new Intent(mContext, ImageGridActivity.class);
								intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
								startActivityForResult(intent, REQUEST_CODE_SELECT);
								break;
							case 1:
								//打开选择,本次允许选择的数量
								ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
								Intent intent1 = new Intent(mContext, ImageGridActivity.class);
								/* 如果需要进入选择的时候显示已经选中的图片，
								 * 详情请查看ImagePickerActivity
                                 * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
								startActivityForResult(intent1, REQUEST_CODE_SELECT);
								break;
							default:
								break;
						}

					}
				}, names);
				break;
			default:
				//打开预览
				Intent intentPreview = new Intent(mContext, ImagePreviewDelActivity.class);
				intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
				intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
				intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
				startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
				break;
		}
	}
}

