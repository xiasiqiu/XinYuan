package com.xinyuan.xyshop.mvp.contract;

import com.xinyuan.xyshop.bean.InvoiceHeaderBean;
import com.xinyuan.xyshop.model.InvoiceModel;

/**
 * Created by fx on 2017/9/6.
 */

public interface InvoiceView {



	void showInvoice(InvoiceModel datas);

	void saveHeader(InvoiceHeaderBean bean);

	void saveInvoice(long invoiceInfoId);

}
