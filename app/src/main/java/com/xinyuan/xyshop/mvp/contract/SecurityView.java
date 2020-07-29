package com.xinyuan.xyshop.mvp.contract;

import com.xinyuan.xyshop.model.UserSecurityModel;

/**
 * Created by Administrator on 2017/9/23.
 */

public interface SecurityView {

	void showUserInfoBack(UserSecurityModel model);

	void showBindBack();

	void ShowUnBindBack();


}
