package com.xinyuan.xyshop.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/8/14.
 * 商品评论数据
 */

public class GoodEvaBean implements Serializable {
	private static final long serialVersionUID = -6159665846945269658L;
	private List<EvaParam> params;
	private String evaluateInfo;
	private int pointNumber;
	private String evaluateName;
	private List<String> evaluateImgList;
	private String evaluateUserImg;
	private int evaluateRating;
	private int seeNumber;
	private String time;
	private String replay;

	private ChaseRatingBean chaseRatings;

	public List<EvaParam> getParams() {
		return params;
	}

	public String getEvaluateInfo() {
		return evaluateInfo;
	}

	public int getPointNumber() {
		return pointNumber;
	}

	public String getEvaluateName() {
		return evaluateName;
	}

	public List<String> getEvaluateImgList() {
		return evaluateImgList;
	}

	public String getEvaluateUserImg() {
		return evaluateUserImg;
	}

	public int getEvaluateRating() {
		return evaluateRating;
	}

	public int getSeeNumber() {
		return seeNumber;
	}

	public String getTime() {
		return time;
	}

	public String getReplay() {
		return replay;
	}

	public ChaseRatingBean getChaseRatings() {
		return chaseRatings;
	}

	@Override
	public String toString() {
		return "GoodsEvaModel{" +
				"params='" + params + '\'' +
				", evaluateInfo='" + evaluateInfo + '\'' +
				", pointNumber=" + pointNumber +
				", evaluateName='" + evaluateName + '\'' +
				", evaluateImgList=" + evaluateImgList +
				", evaluateUserImg='" + evaluateUserImg + '\'' +
				", evaluateRating=" + evaluateRating +
				", seeNumber=" + seeNumber +
				", time='" + time + '\'' +
				", replay='" + replay + '\'' +
				", chaseRatings=" + chaseRatings +
				'}';
	}

	public class ChaseRatingBean {
		private List<String> chaseRatingImgList;
		private String chaseRatingInfo;
		private String chaseRatingReplay;
		private int chaseRatingtime;

		public List<String> getChaseRatingImgList() {
			return chaseRatingImgList;
		}

		public String getChaseRatingInfo() {
			return chaseRatingInfo;
		}

		public String getChaseRatingReplay() {
			return chaseRatingReplay;
		}

		public int getChaseRatingtime() {
			return chaseRatingtime;
		}

		@Override
		public String toString() {
			return "chaseRatings{" +
					"chaseRatingImgList=" + chaseRatingImgList +
					", chaseRatingInfo='" + chaseRatingInfo + '\'' +
					", chaseRatingReplay='" + chaseRatingReplay + '\'' +
					", chaseRatingtime='" + chaseRatingtime + '\'' +
					'}';
		}
	}

	public class EvaParam {
		private String gspName;
		private String gspValue;

		public String getGspName() {
			return gspName;
		}

		public String getGspValue() {
			return gspValue;
		}

		@Override
		public String toString() {
			return gspName + ":" + gspValue;
		}
	}
}
