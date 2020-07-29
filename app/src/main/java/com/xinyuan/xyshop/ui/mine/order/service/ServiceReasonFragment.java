package com.xinyuan.xyshop.ui.mine.order.service;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.ImagePickerAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.bean.OrderGoodBean;
import com.xinyuan.xyshop.bean.OrderServiceStoreInfoBean;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.model.OrderServiceReasonModel;
import com.xinyuan.xyshop.mvp.contract.ServiceReasonView;
import com.xinyuan.xyshop.mvp.presenter.ServiceReasonPresenter;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.EditTextWithDel;
import com.xinyuan.xyshop.widget.dialog.SelectDialog;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/1.
 * 选择售后原因
 */

public class ServiceReasonFragment extends BaseFragment<ServiceReasonPresenter> implements ServiceReasonView, ImagePickerAdapter.OnRecyclerViewItemClickListener {
	@BindView(R.id.toolbar_iv)
	Toolbar toolbar_iv;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;
	@BindView(R.id.iv_header_right)
	ImageView iv_header_right;

	@BindView(R.id.rv_good_pic)
	RecyclerView rv_good_pic;   //售后图片
	@BindView(R.id.ed_eva)
	EditTextWithDel ed_eva;     //退款说明
	@BindView(R.id.ed_price)
	EditTextWithDel ed_price;//退货价格
	@BindView(R.id.spinner_reason)
	Spinner spinner_reason; //退货原因
	@BindView(R.id.tv_good_price)
	TextView tv_good_price;     //商品价格
	@BindView(R.id.iv_good_img)
	ImageView iv_good_img;      //商品图片
	@BindView(R.id.tv_good_name)
	TextView tv_good_name;      //商品名称
	@BindView(R.id.tv_good_spec)
	TextView tv_good_spec;      //商品规格
	@BindView(R.id.rl_goods_reason)
	RelativeLayout rl_goods_reason;     //选择售后原因
	@BindView(R.id.loadingView)
	XLoadingView loadingView;
	@BindView(R.id.bt_post)
	Button bt_post;             //提交申请

	private int type; //退款1，退款退货2
	public static final int IMAGE_ITEM_ADD = -1;
	public static final int REQUEST_CODE_SELECT = 100;
	public static final int REQUEST_CODE_PREVIEW = 101;

	private long reasonId;
	ArrayList<ImageItem> images = null;
	private ImagePickerAdapter adapter;
	private ArrayList<ImageItem> selImageList; //当前选择的所有图片
	private int maxImgCount = 3;               //允许选择图片最大数
	private long orderId;
	private OrderGoodBean orderGoodBean;
	private ArrayAdapter<String> statusAdapter;
	private ArrayAdapter<String> reasonAdapter;
	private String price;       //退款金额
	private OrderServiceStoreInfoBean bean;

	public static ServiceReasonFragment newInstance(int type, OrderGoodBean orderGoodBean, long orderId, OrderServiceStoreInfoBean bean) {
		ServiceReasonFragment fragment = new ServiceReasonFragment();
		fragment.type = type;
		fragment.orderId = orderId;
		fragment.orderGoodBean = orderGoodBean;
		fragment.bean = bean;
		return fragment;
	}


	@Override
	public void initView(View rootView) {
		SystemBarHelper.immersiveStatusBar(getActivity()); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_iv);
		CommUtil.initToolBar(getActivity(), iv_header_left, iv_header_right);
		tv_header_center.setText("申请退款");
		if (type == 2) {
			ed_price.setVisibility(View.VISIBLE);
		} else {
			ed_price.setVisibility(View.GONE);
		}

	}

	@Override
	public void initData() {
		ImagePicker imagePicker = ImagePicker.getInstance();
		imagePicker.setSelectLimit(maxImgCount);
		GlideImageLoader.setUrlImg(mContext, orderGoodBean.getGoodsImg(), iv_good_img);
		tv_good_name.setText(orderGoodBean.getGoods_name());
		tv_good_spec.setText("已选：" + orderGoodBean.getSpec_info());
		selImageList = new ArrayList<>();
		adapter = new ImagePickerAdapter(mContext, selImageList, maxImgCount);
		adapter.setOnItemClickListener(this);
		rv_good_pic.setLayoutManager(new GridLayoutManager(mContext, 5));
		rv_good_pic.setHasFixedSize(true);
		rv_good_pic.setAdapter(adapter);

		mPresenter.getRefundReason(orderId, String.valueOf(orderGoodBean.getGoodsCart_id()));

	}


	private List<OrderServiceReasonModel.ServiceReasonBean> reasList;

	@Override
	public void postBack(int id, Long goodsRefundId) {
		if (type == 1) {
			bean.setServiceId(goodsRefundId);
			startWithPop(ServiceMoneyDetailFragment.newInstance(bean));
		} else {
			bean.setServiceId(goodsRefundId);
			startWithPop(ServiceGoodsFragment.newInstance(bean));
		}
		_mActivity.onBackPressed();
	}

	@Override
	public void reasonBack(OrderServiceReasonModel model) {
		List<String> reason = new ArrayList<>();
		reasList = model.getGoodsReturnResonList();
		for (OrderServiceReasonModel.ServiceReasonBean bean : model.getGoodsReturnResonList()) {
			reason.add(bean.getGoodsReturnResonName());
		}

		reasonAdapter = new ArrayAdapter<String>(mContext, R.layout.spinner_item, reason);
		reasonAdapter.setDropDownViewResource(R.layout.dropdown_stytle);
		spinner_reason.setAdapter(reasonAdapter);
		spinner_reason.setOnItemSelectedListener(new ReasonSelectedListener());

		if (type == 2) {
			price = model.getMoney();
			ed_price.setText(model.getMoney());
			tv_good_price.setText("(退款上限" + model.getMoney() + ")");
		} else {
			price = model.getMoney();
			tv_good_price.setText(model.getMoney());
		}
		loadingView.showContent();
	}


	//使用数组形式操作
	class ReasonSelectedListener implements AdapterView.OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
		                           long arg3) {
			reasonId = reasList.get(arg2).getGoodsReturnResonId();
		}

		public void onNothingSelected(AdapterView<?> arg0) {


		}
	}


	@OnClick(R.id.bt_post)
	public void onClick() {
		String msg = ed_eva.getText().toString().trim();


		ArrayList<File> files = new ArrayList<>();
		if (images != null && images.size() > 0) {
			for (int i = 0; i < images.size(); i++) {
				files.add(new File(images.get(i).path));
			}
		}
		if (type == 2) {
			price = ed_price.getText().toString().trim();
			mPresenter.postRefundGood(orderId, orderGoodBean.getGoodsCart_id(), price, msg, reasonId, files);

		} else {
			mPresenter.postRefundMoney(orderId, orderGoodBean.getGoodsCart_id(), price, msg, reasonId, files);
		}
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
					XLog.list(selImageList);
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


	@Override
	protected ServiceReasonPresenter createPresenter() {
		return new ServiceReasonPresenter(getActivity(), this);
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_service_money;
	}


}
