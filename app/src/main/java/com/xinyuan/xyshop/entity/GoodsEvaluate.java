package com.xinyuan.xyshop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/5/25.
 */

public class GoodsEvaluate implements Serializable {
	private String commonId;
	private String days;
	private long evalCount;
	private long evaluateAgainTime;
	private String evaluateAgainTimeStr;
	private String evaluateContent1;
	private String evaluateContent2;
	private String evaluateId;
	private long evaluateTime;
	private String evaluateTimeStr;
	private String goodsFullSpecs;
	private String goodsId;
	private String goodsImage;
	private String goodsName;
	private List<String> image1FullList;
	private List<String> image1List;
	private List<String> image2FullList;
	private List<String> image2List;
	private String images1;
	private String images2;
	private String memberHeadUrl;
	private String memberName;
	private String scoreTitle;
	private String scores;

	@Override
	public String toString() {
		return "GoodsEvaluate{" +
				"commonId='" + commonId + '\'' +
				", days='" + days + '\'' +
				", evalCount=" + evalCount +
				", evaluateAgainTime=" + evaluateAgainTime +
				", evaluateAgainTimeStr='" + evaluateAgainTimeStr + '\'' +
				", evaluateContent1='" + evaluateContent1 + '\'' +
				", evaluateContent2='" + evaluateContent2 + '\'' +
				", evaluateId='" + evaluateId + '\'' +
				", evaluateTime=" + evaluateTime +
				", evaluateTimeStr='" + evaluateTimeStr + '\'' +
				", goodsFullSpecs='" + goodsFullSpecs + '\'' +
				", goodsId='" + goodsId + '\'' +
				", goodsImage='" + goodsImage + '\'' +
				", goodsName='" + goodsName + '\'' +
				", image1FullList=" + image1FullList +
				", image1List=" + image1List +
				", image2FullList=" + image2FullList +
				", image2List=" + image2List +
				", images1='" + images1 + '\'' +
				", images2='" + images2 + '\'' +
				", memberHeadUrl='" + memberHeadUrl + '\'' +
				", memberName='" + memberName + '\'' +
				", scoreTitle='" + scoreTitle + '\'' +
				", scores='" + scores + '\'' +
				'}';
	}

	public String getCommonId() {
		return commonId;
	}

	public void setCommonId(String commonId) {
		this.commonId = commonId;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public long getEvalCount() {
		return evalCount;
	}

	public void setEvalCount(long evalCount) {
		this.evalCount = evalCount;
	}

	public long getEvaluateAgainTime() {
		return evaluateAgainTime;
	}

	public void setEvaluateAgainTime(long evaluateAgainTime) {
		this.evaluateAgainTime = evaluateAgainTime;
	}

	public String getEvaluateAgainTimeStr() {
		return evaluateAgainTimeStr;
	}

	public void setEvaluateAgainTimeStr(String evaluateAgainTimeStr) {
		this.evaluateAgainTimeStr = evaluateAgainTimeStr;
	}

	public String getEvaluateContent1() {
		return evaluateContent1;
	}

	public void setEvaluateContent1(String evaluateContent1) {
		this.evaluateContent1 = evaluateContent1;
	}

	public String getEvaluateContent2() {
		return evaluateContent2;
	}

	public void setEvaluateContent2(String evaluateContent2) {
		this.evaluateContent2 = evaluateContent2;
	}

	public String getEvaluateId() {
		return evaluateId;
	}

	public void setEvaluateId(String evaluateId) {
		this.evaluateId = evaluateId;
	}

	public long getEvaluateTime() {
		return evaluateTime;
	}

	public void setEvaluateTime(long evaluateTime) {
		this.evaluateTime = evaluateTime;
	}

	public String getEvaluateTimeStr() {
		return evaluateTimeStr;
	}

	public void setEvaluateTimeStr(String evaluateTimeStr) {
		this.evaluateTimeStr = evaluateTimeStr;
	}

	public String getGoodsFullSpecs() {
		return goodsFullSpecs;
	}

	public void setGoodsFullSpecs(String goodsFullSpecs) {
		this.goodsFullSpecs = goodsFullSpecs;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsImage() {
		return goodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public List<String> getImage1FullList() {
		return image1FullList;
	}

	public void setImage1FullList(List<String> image1FullList) {
		this.image1FullList = image1FullList;
	}

	public List<String> getImage1List() {
		return image1List;
	}

	public void setImage1List(List<String> image1List) {
		this.image1List = image1List;
	}

	public List<String> getImage2FullList() {
		return image2FullList;
	}

	public void setImage2FullList(List<String> image2FullList) {
		this.image2FullList = image2FullList;
	}

	public List<String> getImage2List() {
		return image2List;
	}

	public void setImage2List(List<String> image2List) {
		this.image2List = image2List;
	}

	public String getImages1() {
		return images1;
	}

	public void setImages1(String images1) {
		this.images1 = images1;
	}

	public String getImages2() {
		return images2;
	}

	public void setImages2(String images2) {
		this.images2 = images2;
	}

	public String getMemberHeadUrl() {
		return memberHeadUrl;
	}

	public void setMemberHeadUrl(String memberHeadUrl) {
		this.memberHeadUrl = memberHeadUrl;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getScoreTitle() {
		return scoreTitle;
	}

	public void setScoreTitle(String scoreTitle) {
		this.scoreTitle = scoreTitle;
	}

	public String getScores() {
		return scores;
	}

	public void setScores(String scores) {
		this.scores = scores;
	}
}
