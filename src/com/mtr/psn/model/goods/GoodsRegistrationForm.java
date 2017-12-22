package com.mtr.psn.model.goods;

import java.io.Serializable;
import java.util.Date;

import com.mtr.psn.model.BaseModel;
import com.mtr.psn.model.BaseStringTime;

public class GoodsRegistrationForm extends BaseStringTime implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long goodsId;
	private String goodsName;
	private Integer claimantCount;
	private Long claimantBy;
	private String claimantName;
	private String claimantPhone;
	private Long dep;
	private String dName;
	private String claimantTime;
	private String claimantSite;
	private Long handledBy;
	private String handledName;
	private double price;

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getClaimantCount() {
		return claimantCount;
	}

	public void setClaimantCount(Integer claimantCount) {
		this.claimantCount = claimantCount;
	}

	public Long getClaimantBy() {
		return claimantBy;
	}

	public void setClaimantBy(Long claimantBy) {
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

	public Long getDep() {
		return dep;
	}

	public void setDep(Long dep) {
		this.dep = dep;
	}

	public String getDName() {
		return dName;
	}

	public void setDName(String dName) {
		this.dName = dName;
	}

	public String getClaimantTime() {
		return claimantTime;
	}

	public void setClaimantTime(String claimantTime) {
		this.claimantTime = claimantTime;
	}

	public String getClaimantSite() {
		return claimantSite;
	}

	public void setClaimantSite(String claimantSite) {
		this.claimantSite = claimantSite;
	}

	public Long getHandledBy() {
		return handledBy;
	}

	public void setHandledBy(Long handledBy) {
		this.handledBy = handledBy;
	}

	public String getHandledName() {
		return handledName;
	}

	public void setHandledName(String handledName) {
		this.handledName = handledName;
	}

}