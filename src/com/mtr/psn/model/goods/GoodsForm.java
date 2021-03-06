package com.mtr.psn.model.goods;

import java.io.Serializable;
import java.util.Date;

import com.mtr.psn.model.BaseModel;
import com.mtr.psn.model.BaseStringTime;

public class GoodsForm extends BaseStringTime implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long goodsType;
	private String goodsCode;
	private String goodsName;
	private Integer goodsCount;
	private Double price;
	private Long goodsInBy;
	private String goodsInManName;
	private String goodsInTime;
	private Integer goodsInCount;

	public Long getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(Long goodsType) {
		this.goodsType = goodsType;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getGoodsInBy() {
		return goodsInBy;
	}

	public void setGoodsInBy(Long goodsInBy) {
		this.goodsInBy = goodsInBy;
	}

	public String getGoodsInManName() {
		return goodsInManName;
	}

	public void setGoodsInManName(String goodsInManName) {
		this.goodsInManName = goodsInManName;
	}

	public String getGoodsInTime() {
		return goodsInTime;
	}

	public void setGoodsInTime(String goodsInTime) {
		this.goodsInTime = goodsInTime;
	}

	public Integer getGoodsInCount() {
		return goodsInCount;
	}

	public void setGoodsInCount(Integer goodsInCount) {
		this.goodsInCount = goodsInCount;
	}

}