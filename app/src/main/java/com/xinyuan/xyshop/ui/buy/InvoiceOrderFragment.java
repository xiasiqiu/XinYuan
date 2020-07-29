package com.xinyuan.xyshop.ui.buy;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.InvoiceContentAdapter;
import com.xinyuan.xyshop.adapter.InvoiceSpecAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.bean.InvoiceContentBean;
import com.xinyuan.xyshop.bean.InvoiceHeaderBean;
import com.xinyuan.xyshop.bean.InvoiceSpecBean;
import com.xinyuan.xyshop.even.BuyBusEven;
import com.xinyuan.xyshop.even.OrderBusEven;
import com.xinyuan.xyshop.model.InvoiceModel;
import com.xinyuan.xyshop.mvp.contract.InvoiceView;
import com.xinyuan.xyshop.mvp.presenter.InvoicePresenter;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.EditTextWithDel;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.XRegexUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fx on 2017/8/3.
 * 订单发票选择
 */

public class InvoiceOrderFragment extends BaseFragment<InvoicePresenter> implements InvoiceView {
	@BindView(R.id.rg_type)
	RadioGroup rg_type;
	@BindView(R.id.rg_user)
	RadioGroup rg_user;

	@BindView(R.id.ll_invoice_user)
	LinearLayout ll_invoice_user;
	@BindView(R.id.ll_spec_content)
	LinearLayout ll_spec_content;
	@BindView(R.id.ll_person_info)
	LinearLayout ll_person_info;
	@BindView(R.id.ll_content)
	RelativeLayout ll_content;
	@BindView(R.id.ll_more)
	LinearLayout ll_more;
	@BindView(R.id.ll_spec_info)
	LinearLayout ll_spec_info;
	@BindView(R.id.toolbar_tv)
	Toolbar toolbar_tv;
	@BindView(R.id.et_user)
	EditTextWithDel et_user;
	@BindView(R.id.ed_person_phone)
	EditTextWithDel ed_person_phone; //收票人手机号
	@BindView(R.id.ed_person_email)
	EditTextWithDel ed_person_email;//收票人邮箱


	@BindView(R.id.ed_spec_name)
	EditTextWithDel ed_spec_name; //收票人姓名
	@BindView(R.id.ed_spec_phone)
	EditTextWithDel ed_spec_phone; //增值税收票人手机号
	@BindView(R.id.ed_spec_address)
	EditTextWithDel ed_spec_address;//增值税收票人地址

	@BindView(R.id.iv_header_left)
	ImageView iv_header_left;
	@BindView(R.id.tv_header_center)
	TextView tv_header_center;
	@BindView(R.id.tv_header_right)
	TextView tv_header_right;

	@BindView(R.id.rv_invoice_content)
	RecyclerView rv_invoice_content;
	@BindView(R.id.rv_spec_content)
	RecyclerView rv_spec_content;
	private LinearLayoutManager contentLayoutManager;
	private LinearLayoutManager specLayoutManager;

	private int invoiceType = 1;    //发票类型
	private int invoiceHeaderType = 1;  //发票抬头类型

	private long contentId; //发票内容Id;
	private long headerId; //发票抬头Id;
	private long aptitudeId;//增值票Id;
	private String userName;//收票人姓名；
	private String phone;   //收票人手机
	private String email;   //收票人邮箱
	private String address; //收票人地址

	private InvoiceSpecAdapter specAdapter;
	private InvoiceContentAdapter contentAdapter;
	private List<InvoiceHeaderBean> headerBeanList;
	private List<InvoiceContentBean> contentBeanList;
	private List<InvoiceSpecBean> specBeanList;


	public static InvoiceOrderFragment newInstance() {
		InvoiceOrderFragment fragment = new InvoiceOrderFragment();
		return fragment;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.activity_invoice;
	}


	@Override
	public void initView(View rootView) {
		SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
		SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_tv);
		tv_header_center.setText("发票信息");
		CommUtil.initToolBar(getActivity(), iv_header_left, null);
		contentLayoutManager = new LinearLayoutManager(mContext);
		contentLayoutManager.setOrientation(1);
		specLayoutManager = new LinearLayoutManager(mContext);
		specLayoutManager.setOrientation(1);
	}

	@Override
	public void initData() {
		mPresenter.getInvoiceList();
	}


	@Override
	public void showInvoice(InvoiceModel datas) {
		if (!XEmptyUtils.isEmpty(datas)) {
			if (!XEmptyUtils.isEmpty(datas.getHeaders())) {
				headerBeanList = datas.getHeaders();
				et_user.setText(headerBeanList.get(0).getHeaderName());
				headerId = headerBeanList.get(0).getHeaderId();
			}
			if (!XEmptyUtils.isEmpty(datas.getContents())) {
				contentBeanList = datas.getContents();
				contentBeanList.get(0).setCheck(true);
				this.rv_invoice_content.setLayoutManager(contentLayoutManager);
				contentAdapter = new InvoiceContentAdapter(R.layout.view_item_invoce_content, contentBeanList);
				rv_invoice_content.setAdapter(contentAdapter);
				contentAdapter.setOnItemClickListener(new InvoiceSpecAdapter.OnItemClickListener() {
					@Override
					public void onItemClick(View view, int position) {
						for (int i = 0; i < contentAdapter.getData().size(); i++) {
							if (i == position) {
								contentAdapter.getData().get(i).setCheck(true);
							} else {
								contentAdapter.getData().get(i).setCheck(false);
							}
						}
						contentAdapter.notifyDataSetChanged();

					}


				});

			}
			if (!XEmptyUtils.isEmpty(datas.getAptitudes())) {
				specBeanList = datas.getAptitudes();
				specBeanList.get(0).setCheck(true);
				aptitudeId = specBeanList.get(0).getAptitudeId();
				this.rv_spec_content.setLayoutManager(specLayoutManager);
				specAdapter = new InvoiceSpecAdapter(R.layout.view_item_invoce_spec, specBeanList);
				rv_spec_content.setAdapter(specAdapter);

				specAdapter.setOnItemClickListener(new InvoiceSpecAdapter.OnItemClickListener() {
					@Override
					public void onItemClick(View view, int position) {
						for (int i = 0; i < specAdapter.getData().size(); i++) {
							if (i == position) {
								specAdapter.getData().get(i).setCheck(true);
							} else {
								specAdapter.getData().get(i).setCheck(false);
							}
						}
						specAdapter.notifyDataSetChanged();

					}


				});

			}
		}
	}

	/**
	 * 存完头部直接存发票
	 *
	 * @param bean
	 */
	@Override
	public void saveHeader(InvoiceHeaderBean bean) {
		headerId = bean.getHeaderId();
		getContentId();//拿contentId，单位保存发票
		mPresenter.saveInvoice(headerId, contentId, invoiceType, 0, null, ed_person_phone.getText().toString().trim(), ed_person_email.getText().toString().trim(), null);
	}

	/**
	 * 存完发票发送id
	 *
	 * @param invoiceInfoId
	 */
	@Override
	public void saveInvoice(long invoiceInfoId) {
		EventBus.getDefault().post(new BuyBusEven(BuyBusEven.chosesInvoice, invoiceInfoId));
		_mActivity.onBackPressed();

	}

	public void initListener() {
		rg_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup radioGroup, int i) {
				switch (i) {
					case R.id.rb_invoice_paper:  //纸质发票
						ll_person_info.setVisibility(View.GONE);
						ll_invoice_user.setVisibility(View.VISIBLE);
						ll_content.setVisibility(View.VISIBLE);
						ll_spec_info.setVisibility(View.GONE);
						ll_spec_content.setVisibility(View.GONE);
						ll_more.setVisibility(View.GONE);
						invoiceType = 1;
						break;
					case R.id.rb_invoice_online://电子发票
						ll_invoice_user.setVisibility(View.VISIBLE);
						ll_person_info.setVisibility(View.VISIBLE);
						ll_content.setVisibility(View.VISIBLE);
						ll_spec_info.setVisibility(View.GONE);
						ll_spec_content.setVisibility(View.GONE);
						ll_more.setVisibility(View.GONE);
						invoiceType = 2;

						break;
					case R.id.rb_invoice_spec://增值税发票
						if (XEmptyUtils.isEmpty(specBeanList)) {
							ll_more.setVisibility(View.VISIBLE);
							ll_content.setVisibility(View.GONE);
							ll_person_info.setVisibility(View.GONE);
							ll_invoice_user.setVisibility(View.GONE);
						} else {
							ll_person_info.setVisibility(View.GONE);
							ll_invoice_user.setVisibility(View.GONE);
							ll_spec_info.setVisibility(View.VISIBLE);
							ll_content.setVisibility(View.VISIBLE);
							ll_spec_content.setVisibility(View.VISIBLE);
						}
						invoiceType = 3;

						break;

				}

			}
		});
		rg_user.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup radioGroup, int i) {
				switch (i) {
					case R.id.rb_user_person:
						invoiceHeaderType = 1;
						et_user.setVisibility(View.GONE);
						break;
					case R.id.rb_user_company:
						invoiceHeaderType = 2;
						et_user.setVisibility(View.VISIBLE);
						break;

				}
			}
		});

	}

	@OnClick(R.id.bt_ok)
	public void finish() {
		switch (invoiceType) {
			case 1: //纸质发票

				if (invoiceHeaderType == 2) { //单位抬头
					if (XEmptyUtils.isEmpty(headerBeanList)) {
						mPresenter.saveInvoiceHeader(et_user.getText().toString().trim());
					} else {
						if (!et_user.getText().toString().trim().equals(headerBeanList.get(0).getHeaderName())) { //改动
							mPresenter.saveInvoiceHeader(et_user.getText().toString().trim());
						} else {                                                                                  //没改动
							getContentId();//拿contentId，单位保存发票
							mPresenter.saveInvoice(headerId, contentId, invoiceType, 0, null, ed_person_phone.getText().toString().trim(), ed_person_email.getText().toString().trim(), null);
						}
					}

				} else {
					getContentId();//拿contentId，个人保存发票
					mPresenter.saveInvoice(headerId, contentId, invoiceType, 0, null, ed_person_phone.getText().toString().trim(), ed_person_email.getText().toString().trim(), null);
				}
				break;
			case 2: //电子发票
				if (!XEmptyUtils.isSpace(ed_person_phone.getText().toString().trim())) {
					if (XRegexUtils.checkMobile(ed_person_phone.getText().toString().trim())) {
						if (!XEmptyUtils.isSpace(ed_person_email.getText().toString().trim())) {
							if (XRegexUtils.checkEmail(ed_person_email.getText().toString().trim())) {
								if (invoiceHeaderType == 2) {
									if (!XEmptyUtils.isEmpty(et_user.getText())) {
										if(XEmptyUtils.isEmpty(headerBeanList)){
											mPresenter.saveInvoiceHeader(et_user.getText().toString().trim()); //先存抬头

										}else {
											if (!et_user.getText().toString().trim().equals(headerBeanList.get(0).getHeaderName())) { //改动
												mPresenter.saveInvoiceHeader(et_user.getText().toString().trim()); //先存抬头
											} else {
												getContentId();//拿contentId，单位保存发票
												mPresenter.saveInvoice(headerId, contentId, invoiceType, 0, null, ed_person_phone.getText().toString().trim(), ed_person_email.getText().toString().trim(), null);
											}
										}

									} else {
										XToast.error("单位抬头不能为空！");
									}
								} else {
									getContentId(); //个人保存发票
									mPresenter.saveInvoice(headerId, contentId, invoiceType, 0, null, ed_person_phone.getText().toString().trim(), ed_person_email.getText().toString().trim(), null);
								}

							} else {
								XToast.error("邮箱地址格式不正确！");
							}
						} else {
							XToast.error("收票人邮箱不能为空！");
						}
					} else {
						XToast.error("手机格式不正确！");
					}
				} else {
					XToast.error("收票人电话不能为空！");
				}
				break;
			case 3: //增值发票
				if (!XEmptyUtils.isSpace(ed_spec_name.getText().toString().trim())) {
					if (!XEmptyUtils.isSpace(ed_spec_phone.getText().toString().trim())) {
						if (XRegexUtils.checkMobile(ed_spec_phone.getText().toString().trim())) {
							if (!XEmptyUtils.isSpace(ed_spec_address.getText().toString().trim())) {
								getAptitudeId();
								mPresenter.saveInvoice(0, contentId, invoiceType, aptitudeId, ed_spec_name.getText().toString().trim(), ed_spec_phone.getText().toString().trim(), null, ed_spec_address.getText().toString().trim());
							} else {
								XToast.error("收票人地址不能为空！");
							}
						} else {
							XToast.error("手机格式不正确！");
						}
					} else {
						XToast.error("收票人手机不能为空！");
					}
				} else {
					XToast.error("收票人姓名不能为空！");
				}
				break;
		}
	}

	/**
	 * 获取contentid
	 */

	private void getContentId() {
		for (InvoiceContentBean bean : contentAdapter.getData()) {
			if (bean.isCheck()) {
				XLog.v(bean.getContentName());
				contentId = bean.getContentId();
			}
		}

	}

	/**
	 * 获取增值税Id
	 */
	private void getAptitudeId() {
		for (InvoiceSpecBean bean : specAdapter.getData()) {
			if (bean.isCheck()) {
				XLog.v(bean.getInvoiceUnit());
				contentId = bean.getAptitudeId();
			}
		}

	}


	@Override
	protected InvoicePresenter createPresenter() {
		return new InvoicePresenter(getActivity(), this);
	}


}
