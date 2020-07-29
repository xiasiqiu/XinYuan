package com.xinyuan.xyshop.ui.mine.msg;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xinyuan.xyshop.MyShopApplication;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.MsgAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;
import com.xinyuan.xyshop.entity.LzyResponse;
import com.xinyuan.xyshop.callback.JsonCallback;
import com.xinyuan.xyshop.http.HttpUtil;
import com.xinyuan.xyshop.bean.MsgBean;
import com.xinyuan.xyshop.http.Urls;
import com.xinyuan.xyshop.model.ChatListModule;
import com.xinyuan.xyshop.model.MsgModel;
import com.xinyuan.xyshop.mvp.contract.StoreMsgView;
import com.xinyuan.xyshop.util.CommUtil;
import com.xinyuan.xyshop.widget.dialog.color.ColorDialog;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;
import com.youth.xframe.widget.loadingview.XLoadingView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.xinyuan.xyshop.util.CommUtil.getInAnimationTest;
import static com.xinyuan.xyshop.util.CommUtil.getOutAnimationTest;

/**
 * Created by fx on 2017/6/5.
 */

public class MallMsgFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.rv_msg)
    RecyclerView rv_msg;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    MsgAdapter msgAdapter;
    @BindView(R.id.lodingView)
    XLoadingView lodingView;
    private List<MsgBean> data = new ArrayList<>();
    private int page = 1;
    private int limit = 20;

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        getMsg();
    }


    @Override
    public void initView(View rootView) {
        lodingView.showLoading();
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
    }

    @Override
    public void initData() {
        getMsg();
    }

    private void showList(MsgModel msgList) {
        if (XEmptyUtils.isEmpty(msgAdapter)) {
            if (XEmptyUtils.isEmpty(msgList)) {
                lodingView.showEmpty();
            } else {
                this.msgAdapter = new MsgAdapter(R.layout.fragment_msg_item, msgList.getMsgList());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                linearLayoutManager.setOrientation(1);
                rv_msg.setLayoutManager(linearLayoutManager);
                rv_msg.setAdapter(msgAdapter);
                msgAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(final BaseQuickAdapter adapter, View view, final int position) {
                        ColorDialog colorDialog = new ColorDialog(getContext());
                        colorDialog.setTitle("删除消息");
                        colorDialog.setContentText("确定删除此消息吗？");
                        colorDialog.setAnimationEnable(true);
                        colorDialog.setAnimationIn(getInAnimationTest(getActivity()));
                        colorDialog.setAnimationOut(getOutAnimationTest(getActivity()));
                        colorDialog.setPositiveListener("确定", new ColorDialog.OnPositiveListener() {
                            @Override
                            public void onClick(ColorDialog dialog) {
                                deleteMsg(data.get(position).getMessageId());
                                msgAdapter.remove(position);
                                dialog.dismiss();
                                msgAdapter.notifyDataSetChanged();


                            }
                        })
                                .setNegativeListener("取消", new ColorDialog.OnNegativeListener() {
                                    @Override
                                    public void onClick(ColorDialog dialog) {
                                        Toast.makeText(getActivity(), dialog.getNegativeText().toString(), Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                }).show();

                        return false;
                    }
                });
                lodingView.showContent();
                page++;
            }

        } else {
            if (XEmptyUtils.isEmpty(msgList)) {
                if (page == 1) {
                    lodingView.showEmpty();
                } else {
                    msgAdapter.loadMoreEnd();

                }
            } else {
                if (page == 1) {
                    msgAdapter.setNewData(msgList.getMsgList());
                    mSwipeRefreshLayout.setRefreshing(false);
                    msgAdapter.notifyDataSetChanged();
                    msgAdapter.setEnableLoadMore(true);
                    lodingView.showContent();

                } else {
                    msgAdapter.addData(msgList.getMsgList());
                    mSwipeRefreshLayout.setRefreshing(false);
                    msgAdapter.notifyDataSetChanged();
                    msgAdapter.loadMoreComplete();
                }
                lodingView.showContent();
            }
            page++;

        }

    }


    /**
     * 获取消息
     */

    private void getMsg() {
        OkGo.<LzyResponse<MsgModel>>post(Urls.URL_MSG)//
                .tag(this)//
                .headers("token", MyShopApplication.Token)//
                .params("id", MyShopApplication.userId)
                .params("type", "1")
                .params("ship", page)
                .params("limit", limit)
                .execute(new JsonCallback<LzyResponse<MsgModel>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<MsgModel>> response) {
                        if (HttpUtil.handleResponse(mContext, response.body())) {
                            showList(response.body().getDatas());
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<MsgModel>> response) {
                        HttpUtil.handleError(mContext, response);
                    }
                });
    }

    /**
     * 删除消息
     *
     * @param msgId
     */
    private void deleteMsg(String msgId) {
        OkGo.<LzyResponse<List<MsgBean>>>post(Urls.URL_MSG_DE)//
                .tag(this)//
                .headers("token", MyShopApplication.Token)//
                .params("id", MyShopApplication.userId)
                .params("messageId", msgId)
                .execute(new JsonCallback<LzyResponse<List<MsgBean>>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<MsgBean>>> response) {
                        if (HttpUtil.handleResponse(mContext, response.body())) {
                            XToast.info("已删除");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<List<MsgBean>>> response) {
                        XLog.v(response.body().toString());
                    }
                });
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                getMsg();
            }
        }, 500);
    }

    @Override
    public void onLoadMoreRequested() {
        rv_msg.postDelayed(new Runnable() {
            @Override
            public void run() {
                getMsg();
            }

        }, 500);
    }


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_msg;
    }

}
