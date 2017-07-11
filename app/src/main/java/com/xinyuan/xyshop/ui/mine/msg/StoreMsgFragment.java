package com.xinyuan.xyshop.ui.mine.msg;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.adapter.MsgAdapter;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.entity.GoodsEvaluate;
import com.xinyuan.xyshop.entity.MsgBean;
import com.xinyuan.xyshop.util.FullyLinearLayoutManager;
import com.xinyuan.xyshop.widget.dialog.color.ColorDialog;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.xinyuan.xyshop.util.CommUtil.getInAnimationTest;
import static com.xinyuan.xyshop.util.CommUtil.getOutAnimationTest;

/**
 * Created by Administrator on 2017/6/5.
 */

public class StoreMsgFragment extends BaseFragment {

	@BindView(R.id.rv_msg)
	RecyclerView rv_msg;

	MsgAdapter msgAdapter;

	@Override
	public int getLayoutId() {
		return R.layout.fragment_msg;
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	public void initView() {
		final List<MsgBean> data = new ArrayList<>();
		data.add(new MsgBean("1号店", "萨达所大所多几位千万IE气温气温气温IE"));
		data.add(new MsgBean("2号店", "萨达所大所多几位千万IE气温气温气温IE"));
		data.add(new MsgBean("3号店", "萨达所大所多几位千万IE气温气温气温IE"));
		data.add(new MsgBean("4号店", "萨达所大所多几位千万IE气温气温气温IE"));
		data.add(new MsgBean("5号店", "萨达所大所多几位千万IE气温气温气温IE"));
		data.add(new MsgBean("6号店", "萨达所大所多几位千万IE气温气温气温IE"));
		this.msgAdapter = new MsgAdapter(R.layout.fragment_msg_item, data);
		FullyLinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(context);
		rv_msg.setNestedScrollingEnabled(false);
		//设置布局管理器
		rv_msg.setLayoutManager(linearLayoutManager);
		rv_msg.setAdapter(msgAdapter);

		msgAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position) {
				XToast.info("position" + position);
				ColorDialog colorDialog = new ColorDialog(getContext());
				colorDialog.setTitle("删除消息");
				colorDialog.setContentText("确定删除此消息吗？");
				colorDialog.setAnimationEnable(true);
				colorDialog.setAnimationIn(getInAnimationTest(getActivity()));
				colorDialog.setAnimationOut(getOutAnimationTest(getActivity()));
				colorDialog.setPositiveListener("确定", new ColorDialog.OnPositiveListener() {
					@Override
					public void onClick(ColorDialog dialog) {
						msgAdapter.remove(position);
						dialog.dismiss();
						msgAdapter.notifyDataSetChanged();
						XToast.info("删除了" + position);
						XLog.list(data);

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
	}
}
