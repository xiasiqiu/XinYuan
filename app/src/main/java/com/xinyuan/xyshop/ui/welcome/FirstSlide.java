package com.xinyuan.xyshop.ui.welcome;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinyuan.xyshop.R;
import com.youth.xframe.utils.permission.XPermission;

/**
 * Created by fx on 2017/5/9 0009.
 */

public class FirstSlide extends Fragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.activity_guide_firsts, container, false);

		return v;

	}


}
