package com.xinyuan.xyshop.ui.mine.order;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.ImagePickerAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.bean.OrderBean;
import com.xinyuan.xyshop.common.GlideImageLoader;
import com.xinyuan.xyshop.model.OrderModel;
import com.xinyuan.xyshop.mvp.contract.OrderEvaView;
import com.xinyuan.xyshop.mvp.presenter.OrderEvaPresenter;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.util.SystemBarHelper;
import com.xinyuan.xyshop.widget.EditTextWithDel;
import com.xinyuan.xyshop.widget.StarBar;
import com.xinyuan.xyshop.widget.dialog.SelectDialog;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.widget.XToast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/27.
 * 订单评价
 */

public class OrderEveFragment extends BaseFragment<OrderEvaPresenter> implements OrderEvaView, ImagePickerAdapter.OnRecyclerViewItemClickListener {
    @BindView(R.id.toolbar_iv)
    Toolbar toolbar_iv;
    @BindView(R.id.tv_header_center)
    TextView tv_header_center;
    @BindView(R.id.ed_eva)
    EditTextWithDel ed_eva;

    @BindView(R.id.rl_eva_star)
    RelativeLayout rl_eva_star;
    @BindView(R.id.tv_send_eva)
    TextView tv_send_eva;
    @BindView(R.id.iv_good_img)
    ImageView iv_good_img;
    @BindView(R.id.description_evaluate)
    StarBar description_evaluate;
    @BindView(R.id.service_evaluate)
    StarBar service_evaluate;
    @BindView(R.id.ship_evaluate)
    StarBar ship_evaluate;
    @BindView(R.id.rg_good_eva)
    RadioGroup rg_good_eva;
    @BindView(R.id.iv_header_left)
    ImageView iv_header_left;
    @BindView(R.id.rv_good_pic)
    RecyclerView rv_good_pic;
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private boolean anonymous = true;
    private int buyer_val = 5;
    private int description_val = 5;
    private int service_val = 5;
    private int ship_val = 5;
    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 5;               //允许选择图片最大数

    private long orderId;    //订单Id
    private long goodCartId;    //商品购物车ID、商品ID
    private int flag;   //是否是追评
    private String goodImg;    //商品图片
    private int orderType; //订单类型 实物：虚拟

    public static OrderEveFragment getInstance(String goodImg, int orderType, long orderId, long goodCartId, int flag) {
        OrderEveFragment sf = new OrderEveFragment();
        sf.orderId = orderId;
        sf.goodCartId = goodCartId;
        sf.flag = flag;
        sf.goodImg = goodImg;
        sf.orderType = orderType;
        return sf;
    }

    @Override
    protected OrderEvaPresenter createPresenter() {
        return new OrderEvaPresenter(getActivity(), this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_eva;
    }


    @Override
    public void initView(View rootView) {
        SystemBarHelper.immersiveStatusBar(getActivity(), 0); //设置状态栏透明
        SystemBarHelper.setHeightAndPadding(mContext, toolbar_iv);
        CommUtil.initToolBar(getActivity(), iv_header_left, null);
        if (flag == 0) {
            tv_header_center.setText("订单评价");

        } else {
            tv_header_center.setText("订单追评");

        }
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setSelectLimit(maxImgCount);
        getStart();
        GlideImageLoader.setUrlImg(mContext, goodImg, iv_good_img);


    }

    @Override
    public void initData() {
        tv_send_eva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!XEmptyUtils.isSpace(ed_eva.getText().toString().trim())) {
                    post();
                } else {
                    XToast.error("还没有写评价呢");
                }
            }
        });
    }

    private void getStart() {
        if (flag == 0) {
            description_evaluate.setIntegerMark(true);
            service_evaluate.setIntegerMark(true);
            ship_evaluate.setIntegerMark(true);

            description_evaluate.setStarMark(5);
            service_evaluate.setStarMark(5);
            ship_evaluate.setStarMark(5);

            description_evaluate.setOnStarChangeListener(new StarBar.OnStarChangeListener() {
                @Override
                public void onStarChange(float mark) {
                    description_val = (int) mark;
                }
            });
            service_evaluate.setOnStarChangeListener(new StarBar.OnStarChangeListener() {
                @Override
                public void onStarChange(float mark) {
                    service_val = (int) mark;

                }
            });
            ship_evaluate.setOnStarChangeListener(new StarBar.OnStarChangeListener() {
                @Override
                public void onStarChange(float mark) {
                    ship_val = (int) mark;

                }
            });

            rg_good_eva.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {

                    switch (radioGroup.getCheckedRadioButtonId()) {
                        case R.id.rb_good_good:
                            buyer_val = 5;
                            break;
                        case R.id.rb_good_normol:
                            buyer_val = 3;
                            break;
                        case R.id.rb_good_low:
                            buyer_val = 1;
                            break;
                    }


                }
            });
        } else {
            description_evaluate.setVisibility(View.GONE);
            service_evaluate.setVisibility(View.GONE);
            ship_evaluate.setVisibility(View.GONE);
            rl_eva_star.setVisibility(View.GONE);
            rg_good_eva.setVisibility(View.GONE);
        }

        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(mContext, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        rv_good_pic.setLayoutManager(new GridLayoutManager(mContext, 5));
        rv_good_pic.setHasFixedSize(true);
        rv_good_pic.setAdapter(adapter);
    }

    @BindView(R.id.cb_isanony)
    CheckBox cb_isanony;

    private void post() {

        if (cb_isanony.isChecked()) {
            anonymous = true;
        } else {
            anonymous = false;
        }

        ArrayList<File> files = new ArrayList<>();
        if (images != null && images.size() > 0) {
            for (int i = 0; i < images.size(); i++) {
                files.add(new File(images.get(i).path));
            }
        }
        if (flag == 0) {
            if (orderType == 1) {
                mPresenter.PostEva(orderId, goodCartId, ed_eva.getText().toString().trim(), buyer_val, description_val, service_val, ship_val, files, anonymous);
            } else {
                mPresenter.PostOnLineEva(orderId, goodCartId, ed_eva.getText().toString().trim(), buyer_val, description_val, service_val, ship_val, files, anonymous);
            }
        } else {
            if (orderType == 1) {
                mPresenter.AddEva(orderId, goodCartId, ed_eva.getText().toString().trim(), files);
            } else {
                mPresenter.AddOnlineEva(orderId, goodCartId, ed_eva.getText().toString().trim(), files);
            }
        }
    }

    ArrayList<ImageItem> images = null;

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


    @Override
    public void showEvaStatus(boolean status) {
        if (status) {
            XToast.info("评论成功！");
            _mActivity.onBackPressed();
        } else {
            XToast.error("评论失败，请稍后再试");
        }
    }
}
