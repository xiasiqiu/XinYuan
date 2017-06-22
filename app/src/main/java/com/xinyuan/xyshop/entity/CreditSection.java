package com.xinyuan.xyshop.entity;


import com.chad.library.adapter.base.entity.SectionEntity;
import com.xinyuan.xyshop.model.CreditModel;

/**
 * Created by Administrator on 2017/5/22.
 */

public class CreditSection extends XYEntity<CreditModel.CreditModule> {


	public CreditSection(boolean isHeader, CreditModel.CreditModule s) {
		super(isHeader, s);

	}

	public CreditSection(CreditModel.CreditModule t) {
		super(t);
	}

}
