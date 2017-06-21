package com.xinyuan.xyshop.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/21.
 */

public class EvaluateModel implements Serializable {
	private static final long serialVersionUID = 2501231470366439473L;

	@Override
	public String toString() {
		return "EvaluateModel{" +
				"type=" + type +
				", skip=" + skip +
				", limit=" + limit +
				", totalCount=" + totalCount +
				", goodAssess=" + goodAssess +
				", normalAssess=" + normalAssess +
				", lowAssess=" + lowAssess +
				", blueprint=" + blueprint +
				", list=" + list +
				'}';
	}

	private int type;
	private int skip;
	private int limit;


	private int totalCount;
	private int goodAssess;
	private int normalAssess;
	private int lowAssess;
	private int blueprint;

	public int getTotalCount() {
		return totalCount;
	}

	public int getGoodAssess() {
		return goodAssess;
	}

	public int getNormalAssess() {
		return normalAssess;
	}

	public int getLowAssess() {
		return lowAssess;
	}

	public int getBlueprint() {
		return blueprint;
	}

	private List<EvaluateBean> list;


	public int getType() {
		return type;
	}

	public int getSkip() {
		return skip;
	}

	public int getLimit() {
		return limit;
	}

	public List<EvaluateBean> getList() {
		return list;
	}

	public class EvaluateBean implements Serializable {

		private static final long serialVersionUID = -2594035039352221114L;
		private String headImg;
		private String name;
		private int commentLevel;
		private String commentContent;
		private List<String> commentImg;
		private int lookCount;
		private int praiseCount;
		private String time;
		private List<GoodDetailModel.Params> params;
		private CommentMore commentMore;

		public String getHeadImg() {
			return headImg;
		}

		public String getName() {
			return name;
		}

		public int getCommentLevel() {
			return commentLevel;
		}

		public String getCommentContent() {
			return commentContent;
		}

		public List<String> getCommentImg() {
			return commentImg;
		}

		public int getLookCount() {
			return lookCount;
		}

		public int getPraiseCount() {
			return praiseCount;
		}

		public String getTime() {
			return time;
		}

		public List<GoodDetailModel.Params> getParams() {
			return params;
		}

		public CommentMore getCommentMore() {
			return commentMore;
		}

		public class CommentMore implements Serializable {
			private static final long serialVersionUID = -4290950938413289974L;
			private String time;
			private String content;
			private List<String> imgList;

			public String getTime() {
				return time;
			}

			public String getContent() {
				return content;
			}

			public List<String> getImgs() {
				return imgList;
			}
		}
	}
}
