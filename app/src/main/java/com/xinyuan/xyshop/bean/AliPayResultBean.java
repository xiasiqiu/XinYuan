package com.xinyuan.xyshop.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/10/8.
 */

public class AliPayResultBean implements Serializable {
	private static final long serialVersionUID = 4495302610358543924L;

	private String sign;
	private String sign_type;
	private AliPayResBean alipay_trade_app_pay_response;

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public AliPayResBean getAlipay_trade_app_pay_response() {
		return alipay_trade_app_pay_response;
	}

	public void setAlipay_trade_app_pay_response(AliPayResBean alipay_trade_app_pay_response) {
		this.alipay_trade_app_pay_response = alipay_trade_app_pay_response;
	}

	public class AliPayResBean implements Serializable {
		private static final long serialVersionUID = -4673640658695248121L;
		private String out_trade_no;
		private String trade_no;
		private String app_id;
		private String total_amount;
		private String seller_id;
		private String msg;
		private String charset;
		private String timestamp;
		private String code;

		public String getOut_trade_no() {
			return out_trade_no;
		}

		public void setOut_trade_no(String out_trade_no) {
			this.out_trade_no = out_trade_no;
		}

		public String getTrade_no() {
			return trade_no;
		}

		public void setTrade_no(String trade_no) {
			this.trade_no = trade_no;
		}

		public String getApp_id() {
			return app_id;
		}

		public void setApp_id(String app_id) {
			this.app_id = app_id;
		}

		public String getTotal_amount() {
			return total_amount;
		}

		public void setTotal_amount(String total_amount) {
			this.total_amount = total_amount;
		}

		public String getSeller_id() {
			return seller_id;
		}

		public void setSeller_id(String seller_id) {
			this.seller_id = seller_id;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public String getCharset() {
			return charset;
		}

		public void setCharset(String charset) {
			this.charset = charset;
		}

		public String getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
	}
}
