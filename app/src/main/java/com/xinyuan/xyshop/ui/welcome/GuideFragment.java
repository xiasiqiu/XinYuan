package com.xinyuan.xyshop.ui.welcome;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinyuan.xyshop.R;
import com.xinyuan.xyshop.base.BaseFragment;
import com.xinyuan.xyshop.base.BasePresenter;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/8/22.
 */

public class GuideFragment extends BaseFragment {
	@BindView(R.id.iv_guide)
	ImageView iv_guide;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.tv_content)
	TextView tv_content;

	private int mPostion;

	public static GuideFragment newInstance(int position) {
		GuideFragment guideFragment = new GuideFragment();
		guideFragment.mPostion = position;
		return guideFragment;
	}

	@Override
	public void initView(View rootView) {
		switch (mPostion) {
			case 1:
				iv_guide.setImageDrawable(getResources().getDrawable(R.drawable.iv_guide_1));
				tv_title.setText("实实在在珍惜低");
				tv_content.setText("守着商城随你挑");
				break;
			case 2:
				iv_guide.setImageDrawable(getResources().getDrawable(R.drawable.iv_guide_2));
				tv_title.setText("全城好货");
				tv_content.setText("想买的全部都有");
				break;
			case 3:
				iv_guide.setImageDrawable(getResources().getDrawable(R.drawable.iv_guide_3));
				tv_title.setText("便捷 安全");
				tv_content.setText("支持多种支付方式");
				break;
			case 4:
				iv_guide.setImageDrawable(getResources().getDrawable(R.drawable.iv_guide_4));
				tv_title.setText("及时");
				tv_content.setText("精选优惠开抢提醒");
				break;
		}
	}

	@Override
	public void initData() {

	}

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected int provideContentViewId() {
		return R.layout.fragment_guide;
	}


}
