package com.mtr.psn.model.webEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mtr.psn.model.BaseModel;

public class GoodsRegistrationFormEntiy extends BaseModel {

	private Integer goodsType;//物品类型
	private String goodsValueName;//类型名称
	private String goodsName;//名称
	private Double price;//单价
	private Integer goodsId;//物品编号
	private Integer claimantCount;
	private Integer claimantBy;
	private String claimantName;
	private String claimantPhone;
	private Integer dep;
	private Date claimantTime;
	private String claimantSite;
	private Integer handledBy;
	private String handledName;//经手人姓名
	private String createName;//记录人
	private List<Map<String, Integer>> goodsList;//{（物品，数量）,（物品，数量）...}
	
	
	public Integer getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getClaimantCount() {
		return claimantCount;
	}
	public void setClaimantCount(Integer claimantCount) {
		this.claimantCount = claimantCount;
	}
	public Integer getClaimantBy() {
		return claimantBy;
	}
	public void setClaimantBy(Integer claimantBy) {
		this.claimantBy = claimantBy;
	}
	public String getClaimantName() {
		return claimantName;
	}
	public void setClaimantName(String claimantName) {
		this.claimantName = claimantName;
	}
	public String getClaimantPhone() {
		return claimantPhone;
	}
	public void setClaimantPhone(String claimantPhone) {
		this.claimantPhone = claimantPhone;
	}
	public Integer getDep() {
		return dep;
	}
	public void setDep(Integer dep) {
		this.dep = dep;
	}
	public Date getClaimantTime() {
		return claimantTime;
	}
	public void setClaimantTime(Date claimantTime) {
		this.claimantTime = claimantTime;
	}
	public String getClaimantSite() {
		return claimantSite;
	}
	public void setClaimantSite(String claimantSite) {
		this.claimantSite = claimantSite;
	}
	public Integer getHandledBy() {
		return handledBy;
	}
	public void setHandledBy(Integer handledBy) {
		this.handledBy = handledBy;
	}
	public String getHandledName() {
		return handledName;
	}
	public void setHandledName(String handledName) {
		this.handledName = handledName;
	}
	public String getGoodsValueName() {
		return goodsValueName;
	}
	public void setGoodsValueName(String goodsValueName) {
		this.goodsValueName = goodsValueName;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	
	public List<Map<String, Integer>> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<Map<String, Integer>> goodsList) {
		this.goodsList = goodsList;
	}
	@Override
	public String toString() {
		return "GoodsRegistrationFormEntiy [goodsType=" + goodsType
				+ ", goodsName=" + goodsName + ", price=" + price
				+ ", goodsId=" + goodsId + ", claimantCount=" + claimantCount
				+ ", claimantBy=" + claimantBy + ", claimantName="
				+ claimantName + ", claimantPhone=" + claimantPhone + ", dep="
				+ dep + ", claimantTime=" + claimantTime + ", claimantSite="
				+ claimantSite + ", handledBy=" + handledBy + ", handledName="
				+ handledName + "]";
	}
	
}
