package com.xinyuan.xyshop.ui.mine.order.service;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.ServiceGoodsAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.bean.OrderBean;
import com.xinyuan.xyshop.bean.OrderGoodBean;
import com.xinyuan.xyshop.bean.OrderServiceStoreInfoBean;
import com.xinyuan.xyshop.bean.ReFundServiceDetailBean;
import com.xinyuan.xyshop.bean.ServiceGoodDetailBean;
import com.xinyuan.xyshop.entity.ServiceMultipleItem;
import com.xinyuan.xyshop.model.ServiceGoodDetailModel;
import com.xinyuan.xyshop.mvp.contract.ServiceDetailView;
import com.xinyuan.xyshop.mvp.presenter.ServiceDetailPresenter;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.dialog.color.ColorDialog;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.XToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/1.
 * 退货退款详情
 */

public class ServiceGoodsFragment extends BaseFragment<ServiceDetailPresenter> implements ServiceDetailView {
    @BindView(R.id.rv_service_good)
    RecyclerView rv_service_good;

    ServiceGoodsAdapter adapter;

    @BindView(R.id.tv_header_center)
    TextView tv_header_center;
    @BindView(R.id.iv_header_left)
    ImageView iv_header_left;

    @BindView(R.id.toolbar_tv)
    Toolbar toolbar_tv;
    @BindView(R.id.bt_service_express)
    Button bt_service_express;
    @BindView(R.id.bt_service_cancel)
    Button bt_service_cancel;


    private int step;
    private OrderServiceStoreInfoBean storeInfoBean;

    public static ServiceGoodsFragment newInstance(OrderServiceStoreInfoBean bean) {
        ServiceGoodsFragment fragment = new ServiceGoodsFragment();
        fragment.storeInfoBean = bean;
        return fragment;
    }

    @Override
    protected ServiceDetailPresenter createPresenter() {
        return new ServiceDetailPresenter(getActivity(), this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_service_goods;

    }


    @Override
    public void initView(View rootView) {
        SystemBarHelper.immersiveStatusBar(getActivity()); //设置状态栏透明
        SystemBarHelper.setHeightAndPadding(getActivity(), toolbar_tv);
        tv_header_center.setText("退货售后");
        CommUtil.initToolBar(getActivity(), iv_header_left, null);
        final GridLayoutManager manager = new GridLayoutManager(mContext, 4);
        rv_service_good.setLayoutManager(manager);

    }

    @Override
    public void initData() {
    }


    @OnClick({R.id.bt_service_express, R.id.bt_service_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_service_express:
                start(ServiceExpFragment.newInstance(storeInfoBean.getServiceId()));
                break;
            case R.id.bt_service_cancel:
                final ColorDialog colorDialog = new ColorDialog(getActivity());
                colorDialog.setTitle("撤销退货");
                colorDialog.setContentText("确认撤销售后退货？");
                colorDialog.setPositiveListener("确认", new ColorDialog.OnPositiveListener() {
                    @Override
                    public void onClick(ColorDialog dialog) {
                        mPresenter.cancelService("return", storeInfoBean.getServiceId());
                        colorDialog.dismiss();
                    }
                })
                        .setNegativeListener("取消", new ColorDialog.OnNegativeListener() {
                            @Override
                            public void onClick(ColorDialog dialog) {
                                colorDialog.dismiss();
                            }
                        }).show();

                break;

        }
    }


    @Override
    public void showGoodInfo(ServiceGoodDetailModel model) {
        step = model.getInfoList().get(model.getInfoList().size() - 1).getStep();
        List<ServiceMultipleItem> list = new ArrayList<>();

        switch (step) {
            case 1:
                bt_service_cancel.setVisibility(View.VISIBLE);
                break;
            case 2:
                bt_service_cancel.setVisibility(View.VISIBLE);
                bt_service_express.setVisibility(View.VISIBLE);
                break;
            case 3:
                bt_service_cancel.setVisibility(View.VISIBLE);
                break;
            case 4:
                break;
            case 5:
                break;
        }
        for (ServiceGoodDetailBean goodBean : model.getInfoList()) {
            if (goodBean.getRole() == 0) {
                list.add(new ServiceMultipleItem(ServiceMultipleItem.User, ServiceMultipleItem.User_Span_Size, goodBean));
            } else {
                list.add(new ServiceMultipleItem(ServiceMultipleItem.Store, ServiceMultipleItem.Store_Span_Size, goodBean));
            }
        }
        adapter = new ServiceGoodsAdapter(list);
        adapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {

                return list.get(position).getSpanSize();
            }
        });
        rv_service_good.setAdapter(adapter);

    }


    @Override
    public void cancelBack(boolean status) {
        if (status) {
            XToast.info("撤销成功！");
            _mActivity.onBackPressed();
        } else {
            XToast.error("撤销错误,请稍后重试");
        }
    }

    @Override
    public void showMoneyInfo(ReFundServiceDetailBean bean) {

    }

    @Override
    public void onSupportVisible() { //当fragment可见时，刷新内容
        mPresenter.getGoodDetail(storeInfoBean.getServiceId());

        super.onSupportVisible();
    }
}
