package com.mtr.psn.model.orders;

import java.io.Serializable;
import java.util.Date;

public class OrderForm implements Serializable {

	private static final long serialVersionUID = 1L;
	private String orderDate;
	private Long franchiseeID;
	private String franchiseeName;
	private Long teamID;
	private String teamName;
	private Long merchantID;
	private String merchantName;
	private String orderFrom;
	private Long id;
	private Long billNum;
	private Long riderID;
	private String riderName;
	private String overTime;
	private String status;
	private String fastDelivery;
	private String reason;
	private String result;
	private String illegal;
	private String orderTime;
	private String endTime;
	private double tdFee;
	private double jcFee;
	private double hdSubsidy;
	private double djSubsidy;
	private double sxSubsidy;
	private double jlSubsidy;
	private double sdSubsidy;
	private double tqSubsidy;
	private double yxsSubsidy;
	private double xtDebit;
	private double psDebit;
	private double ddDebit;
	private double sxDebit;
	private double wgDebit;
	private double weightMarkup;
	private double jjDebit;
	private double llDebit;
	private double wgfDebit;
	private String remarks;

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Long getFranchiseeID() {
		return franchiseeID;
	}

	public void setFranchiseeID(Long franchiseeID) {
		this.franchiseeID = franchiseeID;
	}

	public Long getTeamID() {
		return teamID;
	}

	public void setTeamID(Long teamID) {
		this.teamID = teamID;
	}

	public Long getMerchantID() {
		return merchantID;
	}

	public void setMerchantID(Long merchantID) {
		this.merchantID = merchantID;
	}

	public String getOrderFrom() {
		return orderFrom;
	}

	public void setOrderFrom(String orderFrom) {
		this.orderFrom = orderFrom;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBillNum() {
		return billNum;
	}

	public void setBillNum(Long billNum) {
		this.billNum = billNum;
	}

	public Long getRiderID() {
		return riderID;
	}

	public void setRiderID(Long riderID) {
		this.riderID = riderID;
	}

	public String getOverTime() {
		return overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFastDelivery() {
		return fastDelivery;
	}

	public void setFastDelivery(String fastDelivery) {
		this.fastDelivery = fastDelivery;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getIllegal() {
		return illegal;
	}

	public void setIllegal(String illegal) {
		this.illegal = illegal;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public double getTdFee() {
		return tdFee;
	}

	public void setTdFee(double tdFee) {
		this.tdFee = tdFee;
	}

	public double getJcFee() {
		return jcFee;
	}

	public void setJcFee(double jcFee) {
		this.jcFee = jcFee;
	}

	public double getHdSubsidy() {
		return hdSubsidy;
	}

	public void setHdSubsidy(double hdSubsidy) {
		this.hdSubsidy = hdSubsidy;
	}

	public double getDjSubsidy() {
		return djSubsidy;
	}

	public void setDjSubsidy(double djSubsidy) {
		this.djSubsidy = djSubsidy;
	}

	public double getSxSubsidy() {
		return sxSubsidy;
	}

	public void setSxSubsidy(double sxSubsidy) {
		this.sxSubsidy = sxSubsidy;
	}

	public double getJlSubsidy() {
		return jlSubsidy;
	}

	public void setJlSubsidy(double jlSubsidy) {
		this.jlSubsidy = jlSubsidy;
	}

	public double getSdSubsidy() {
		return sdSubsidy;
	}

	public void setSdSubsidy(double sdSubsidy) {
		this.sdSubsidy = sdSubsidy;
	}

	public double getTqSubsidy() {
		return tqSubsidy;
	}

	public void setTqSubsidy(double tqSubsidy) {
		this.tqSubsidy = tqSubsidy;
	}

	public double getYxsSubsidy() {
		return yxsSubsidy;
	}

	public void setYxsSubsidy(double yxsSubsidy) {
		this.yxsSubsidy = yxsSubsidy;
	}

	public double getXtDebit() {
		return xtDebit;
	}

	public void setXtDebit(double xtDebit) {
		this.xtDebit = xtDebit;
	}

	public double getPsDebit() {
		return psDebit;
	}

	public void setPsDebit(double psDebit) {
		this.psDebit = psDebit;
	}

	public double getDdDebit() {
		return ddDebit;
	}

	public void setDdDebit(double ddDebit) {
		this.ddDebit = ddDebit;
	}

	public double getSxDebit() {
		return sxDebit;
	}

	public void setSxDebit(double sxDebit) {
		this.sxDebit = sxDebit;
	}

	public double getWgDebit() {
		return wgDebit;
	}

	public void setWgDebit(double wgDebit) {
		this.wgDebit = wgDebit;
	}

	public double getWeightMarkup() {
		return weightMarkup;
	}

	public void setWeightMarkup(double weightMarkup) {
		this.weightMarkup = weightMarkup;
	}

	public double getJjDebit() {
		return jjDebit;
	}

	public void setJjDebit(double jjDebit) {
		this.jjDebit = jjDebit;
	}

	public double getLlDebit() {
		return llDebit;
	}

	public void setLlDebit(double llDebit) {
		this.llDebit = llDebit;
	}

	public double getWgfDebit() {
		return wgfDebit;
	}

	public void setWgfDebit(double wgfDebit) {
		this.wgfDebit = wgfDebit;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getFranchiseeName() {
		return franchiseeName;
	}

	public void setFranchiseeName(String franchiseeName) {
		this.franchiseeName = franchiseeName;
	}
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getRiderName() {
		return riderName;
	}

	public void setRiderName(String riderName) {
		this.riderName = riderName;
	}

}