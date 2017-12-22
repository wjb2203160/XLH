package com.mtr.psn.model.pay;

import java.io.Serializable;
import java.util.Date;

import com.mtr.psn.model.BaseStringTime;

public class Wage extends BaseStringTime implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long jopNumber;
	private Integer totalCount;
	private Integer errorCount;
	private Integer normalCount;
	private Integer timeoutCount;
	private Integer onTimeCount;
	private Double subsidization;
	private Double attendance;
	private Double leakageBuckle;
	private Double referralFee;
	private Double insuranceFee;
	private Double amerce;
	private Double overAllowance;
	private Double shouldPay;
	private Double incomeTax;
	private Double goodFee;
	private Double advancePay;
	private Double electricity;
	private Double realPay;
	private String wageDate;
	private String riderName;
    private String userName;
    private String userName1;
    private Long teamID;
    private String teamName;
	public Long getJopNumber() {
		return jopNumber;
	}

	public void setJopNumber(Long jopNumber) {
		this.jopNumber = jopNumber;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	
	public Integer getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}

	public Integer getNormalCount() {
		return normalCount;
	}

	public void setNormalCount(Integer normalCount) {
		this.normalCount = normalCount;
	}

	public Integer getTimeoutCount() {
		return timeoutCount;
	}

	public void setTimeoutCount(Integer timeoutCount) {
		this.timeoutCount = timeoutCount;
	}

	public Integer getOnTimeCount() {
		return onTimeCount;
	}

	public void setOnTimeCount(Integer onTimeCount) {
		this.onTimeCount = onTimeCount;
	}
	public Long getTeamID() {
		return teamID;
	}

	public void setTeamID(Long teamID) {
		this.teamID = teamID;
	}
	public Double getSubsidization() {
		return subsidization;
	}

	public void setSubsidization(Double subsidization) {
		this.subsidization = subsidization;
	}
	public Double getAttendance() {
		return attendance;
	}

	public void setAttendance(Double attendance) {
		this.attendance = attendance;
	}
	public Double getLeakageBuckle() {
		return leakageBuckle;
	}

	public void setLeakageBuckle(Double leakageBuckle) {
		this.leakageBuckle = leakageBuckle;
	}

	public Double getReferralFee() {
		return referralFee;
	}

	public void setReferralFee(Double referralFee) {
		this.referralFee = referralFee;
	}

	public Double getInsuranceFee() {
		return insuranceFee;
	}

	public void setInsuranceFee(Double insuranceFee) {
		this.insuranceFee = insuranceFee;
	}

	public Double getAmerce() {
		return amerce;
	}

	public void setAmerce(Double amerce) {
		this.amerce = amerce;
	}

	public Double getOverAllowance() {
		return overAllowance;
	}

	public void setOverAllowance(Double overAllowance) {
		this.overAllowance = overAllowance;
	}

	public Double getShouldPay() {
		return shouldPay;
	}

	public void setShouldPay(Double shouldPay) {
		this.shouldPay = shouldPay;
	}

	public Double getIncomeTax() {
		return incomeTax;
	}

	public void setIncomeTax(Double incomeTax) {
		this.incomeTax = incomeTax;
	}

	public Double getGoodFee() {
		return goodFee;
	}

	public void setGoodFee(Double goodFee) {
		this.goodFee = goodFee;
	}

	public Double getAdvancePay() {
		return advancePay;
	}

	public void setAdvancePay(Double i) {
		this.advancePay = i;
	}

	public Double getElectricity() {
		return electricity;
	}

	public void setElectricity(Double electricity) {
		this.electricity = electricity;
	}

	public Double getRealPay() {
		return realPay;
	}

	public void setRealPay(Double realPay) {
		this.realPay = realPay;
	}

	public String getWageDate() {
		return wageDate;
	}

	public void setWageDate(String wageDate) {
		this.wageDate = wageDate;
	}
	public String getRiderName() {
		return riderName;
	}

	public void setRiderName(String riderName) {
		this.riderName = riderName;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserName1() {
		return userName1;
	}

	public void setUserName1(String userName1) {
		this.userName1 = userName1;
	}
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
}