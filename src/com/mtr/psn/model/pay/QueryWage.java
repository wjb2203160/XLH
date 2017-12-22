package com.mtr.psn.model.pay;

import java.io.Serializable;

import com.mtr.psn.model.BaseStringTime;

public class QueryWage extends BaseStringTime  implements Serializable {

	private static final long serialVersionUID = 1L;
	private String orderForm;
	private String orderTo;
	private Integer riderID;
	private String riderName;
	private Integer teamID;
	private String zeroNotDisp;

	public String getOrderForm() {
		return orderForm;
	}

	public void setOrderForm(String OrderForm) {
		this.orderForm = OrderForm;
	}

	public String getOrderTo() {
		return orderTo;
	}

	public void setOrderTo(String orderTo) {
		this.orderTo = orderTo;
	}
	public Integer getTeamID() {
		return teamID;
	}

	public void setTeamID(Integer teamID) {
		this.teamID = teamID;
	}
	public Integer getRiderID() {
		return riderID;
	}

	public void setRiderID(Integer riderID) {
		this.riderID = riderID;
	}

	public String getRiderName() {
		return riderName;
	}

	public void setRiderName(String riderName ){
		this.riderName = riderName;
	}
	public String getZeroNotDisp() {
		return zeroNotDisp;
	}

	public void setZeroNotDisp(String zeroNotDisp ){
		this.zeroNotDisp = zeroNotDisp;
	}
}