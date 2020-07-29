package com.xinyuan.xyshop.ui.goods.store.fragment;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.bean.StoreBean;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.common.state.LoginContext;
import com.xinyuan.xyshop.mvp.contract.StoreView;
import com.xinyuan.xyshop.mvp.presenter.StorePresenter;
import com.xinyuan.xyshop.ui.mine.login.LoginActivity;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.dialog.color.ColorDialog;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import cn.bingoogolapple.badgeview.BGABadgeImageView;

import static com.xinyuan.xyshop.util.CommUtil.getInAnimationTest;
import static com.xinyuan.xyshop.util.CommUtil.getOutAnimationTest;

/**
 * Created by fx on 2017/9/22.
 */

public class StoreIntroduceFragment extends BaseFragment<StorePresenter> implements StoreView {

    @BindView(R.id.toolbar_iv)
    Toolbar store_toolbar;
    @BindView(R.id.tv_header_center)
    TextView tv_header_center;

    @BindView(R.id.iv_store_logo)
    ImageView iv_store_logo;
    @BindView(R.id.tv_store_name)
    TextView tv_store_name;
    @BindView(R.id.tv_store_type)
    TextView tv_store_type;
    @BindView(R.id.tv_store_fans)
    TextView tv_store_fans;
    @BindView(R.id.bt_store_fav)
    Button bt_store_fav;


    @BindView(R.id.tv_store_score)
    TextView tv_store_score;
    @BindView(R.id.tv_store_service_score)
    TextView tv_store_service_score;
    @BindView(R.id.tv_store_express_score)
    TextView tv_store_express_score;
    @BindView(R.id.tv_store_name2)
    TextView tv_store_name2;
    @BindView(R.id.tv_store_location)
    TextView tv_store_location;
    @BindView(R.id.tv_store_createTime)
    TextView tv_store_createTime;
    @BindView(R.id.tv_store_phone)
    TextView tv_store_phone;

    @BindView(R.id.iv_header_left)
    ImageView iv_header_left;
    @BindView(R.id.iv_store_credit)
    ImageView iv_store_credit;
    @BindView(R.id.tv_store_level)
    TextView tv_store_level;
    @BindView(R.id.iv_header_right)
    BGABadgeImageView iv_header_right;
    private StoreBean bean;

    public static StoreIntroduceFragment newInstance(StoreBean storeBean) {
        StoreIntroduceFragment fragment = new StoreIntroduceFragment();
        fragment.bean = storeBean;
        return fragment;
    }

    @Override
    protected StorePresenter createPresenter() {
        return new StorePresenter(getActivity(), this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_sotre_introduce;
    }


    @Override
    public void initView(View rootView) {
        SystemBarHelper.immersiveStatusBar(getActivity()); //设置状态栏透明
        SystemBarHelper.setHeightAndPadding(getActivity(), store_toolbar);
        tv_header_center.setText("店铺介绍");
        CommUtil.initToolBar(getActivity(), iv_header_left, iv_header_right);
    }

    @Override
    public void initData() {
        if (!XEmptyUtils.isEmpty(bean)) {
            GlideImageLoader.setUrlImg(getActivity(), bean.getStoreLogo(), iv_store_logo);
            tv_store_name.setText(bean.getStoreName());
            tv_store_name2.setText(bean.getStoreName());
            tv_store_type.setText("类型:" + bean.getStoreType());
            tv_store_fans.setText(bean.getStoreFansSum());
            if (bean.getUserFavorites() == 1) {
                bt_store_fav.setText("已关注");
            }

            bt_store_fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (LoginContext.isLogin) {
                        if (bean.getUserFavorites() == 0) {
                            mPresenter.FavStore(bean.getStoreId());

                        } else {
                            ColorDialog colorDialog = new ColorDialog(getActivity());
                            colorDialog.setTitle("取消关注");
                            colorDialog.setContentText("确定要取消对" + bean.getStoreName() + "的关注？");
                            colorDialog.setAnimationEnable(true);
                            colorDialog.setAnimationIn(getInAnimationTest(getActivity()));
                            colorDialog.setAnimationOut(getOutAnimationTest(getActivity()));
                            colorDialog.setPositiveListener("确定", new ColorDialog.OnPositiveListener() {
                                @Override
                                public void onClick(ColorDialog dialog) {
                                    dialog.dismiss();
                                    mPresenter.cancelFavGood(bean.getStoreId());
                                }
                            })
                                    .setNegativeListener("取消", new ColorDialog.OnNegativeListener() {
                                        @Override
                                        public void onClick(ColorDialog dialog) {
                                            dialog.dismiss();
                                        }
                                    }).show();


                        }
                    } else {
                        CommUtil.gotoActivity(getActivity(), LoginActivity.class, false, null);
                    }
                }

            });
            if (bean.getEvaluateScoreText().contains("低")) {
                tv_store_score.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            } else if (bean.getServiceScoreText().contains("低")) {
                tv_store_service_score.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            } else if (bean.getLogisticsScoreText().contains("低")) {
                tv_store_express_score.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }
            tv_store_score.setText(bean.getEvaluateScore() + " " + bean.getServiceScoreText());
            tv_store_service_score.setText(bean.getServiceScore() + " " + bean.getServiceScoreText());
            tv_store_express_score.setText(bean.getLogisticsScore() + " " + bean.getLogisticsScoreText());
            tv_store_location.setText(bean.getStoreAddress());
            tv_store_phone.setText(bean.getStorePhone());
            tv_store_createTime.setText(bean.getCreateTime());

            tv_store_level.setText(bean.getStoreGradeName());
            GlideImageLoader.setImg(getActivity(), CommUtil.getStoreCredit(getActivity(), bean.getStoreCredit()), iv_store_credit);

        } else {
            XToast.error("获取店铺数据错误");
            _mActivity.onBackPressed();
        }
    }


    @Override
    public void setFavView(int type) {
        switch (type) {
            case 1:
                bt_store_fav.setText("关注");
                XToast.info("已取消关注");
                break;
            case 2:
                bt_store_fav.setText("已关注");
                XToast.info("已关注该店铺");
                break;
            case 3:
                XToast.error("关注失败");
                break;
        }
    }


    @Override
    public void onResume() {
        if (MyShopApplication.IsNewMsg) {
            iv_header_right.showCirclePointBadge();
        } else {
            iv_header_right.hiddenBadge();
        }

        super.onResume();
    }

}
