package com.xinyuan.xyshop.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/16.
 */

public class KeyWord implements Serializable {

	private int code;
	private Key datas;

	public class Key{

		private String keywordValue;
		private String keywordName;

		public String getKeywordValue() {
			return keywordValue;
		}

		public void setKeywordValue(String keywordValue) {
			this.keywordValue = keywordValue;
		}

		public String getKeywordName() {
			return keywordName;
		}

		public void setKeywordName(String keywordName) {
			this.keywordName = keywordName;
		}

		@Override
		public String toString() {
			return "Key{" +
					"keywordValue='" + keywordValue + '\'' +
					", keywordName='" + keywordName + '\'' +
					'}';
		}
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Key getKey() {
		return datas;
	}

	public void setKey(Key key) {
		this.datas = key;
	}

	@Override
	public String toString() {
		return "KeyWord{" +
				"code=" + code +
				", key=" + datas +
				'}';
	}
}
