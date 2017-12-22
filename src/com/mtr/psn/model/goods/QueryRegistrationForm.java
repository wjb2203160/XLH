package com.mtr.psn.model.goods;

import java.io.Serializable;
import java.util.Date;

import com.mtr.psn.model.BaseModel;
import com.mtr.psn.model.BaseStringTime;

public class QueryRegistrationForm extends BaseStringTime  implements Serializable {

	private static final long serialVersionUID = 1L;
	private String orderForm;
	private String orderTo;
	private Long riderID;
	private String riderName;
	 

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

	public Long getRiderID() {
		return riderID;
	}

	public void setRiderID(Long riderID) {
		this.riderID = riderID;
	}

	 

	public String getRiderName() {
		return riderName;
	}

	public void setRiderName(String riderName ){
		this.riderName = riderName;
	}

}