package com.mtr.psn.model.webEntity;


import com.mtr.psn.model.BaseStringTime;
import com.mtr.psn.model.goods.GoodsForm;

public class GoodsFormEntity extends BaseStringTime {

	private Long goodsType;
	private String goodsTypeName;
	private String goodsCode;
	private String goodsName;
	private Integer goodsCount;//库存余量
	private Double price;
	private Long goodsInBy;
	private String goodsInByName;
	private String goodsInByPhone;
	private String goodsInTime;
	private Integer goodsInCount;//进货量
	private Long dep;
	private String dName;//部门名称
	private String claimantTime;
	private Integer claimantCount;//出库量
	private String handledName;//经手人姓名
	private String handledPhone;
	
	public GoodsFormEntity(){};
	public GoodsFormEntity(GoodsForm form){
		super(form.getId(), form.getCreateBy(), form.getCreateTime(), form.getModifyBy(), 
				form.getModifyTime(), form.getRemarks());
		this.goodsType=form.getGoodsType();
		this.goodsCode=form.getGoodsCode();
		this.goodsName=form.getGoodsName();
		this.goodsCount=form.getGoodsCount();
		this.price=form.getPrice();
		this.goodsInBy=form.getGoodsInBy();
		this.goodsInByName=form.getGoodsInManName();
		this.goodsInTime=form.getGoodsInTime();
		this.goodsInCount=form.getGoodsInCount();
	}
	
	
	public String getGoodsTypeName() {
		return goodsTypeName;
	}
	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
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

	public String getGoodsInByName() {
		return goodsInByName;
	}
	public void setGoodsInByName(String goodsInByName) {
		this.goodsInByName = goodsInByName;
	}
	public String getGoodsInByPhone() {
		return goodsInByPhone;
	}
	public void setGoodsInByPhone(String goodsInByPhone) {
		this.goodsInByPhone = goodsInByPhone;
	}
	
	public Integer getGoodsInCount() {
		return goodsInCount;
	}
	public void setGoodsInCount(Integer goodsInCount) {
		this.goodsInCount = goodsInCount;
	}
	
	public String getGoodsInTime() {
		return goodsInTime;
	}
	public void setGoodsInTime(String goodsInTime) {
		this.goodsInTime = goodsInTime;
	}
	public String getClaimantTime() {
		return claimantTime;
	}
	public void setClaimantTime(String claimantTime) {
		this.claimantTime = claimantTime;
	}
	public Integer getClaimantCount() {
		return claimantCount;
	}
	public void setClaimantCount(Integer claimantCount) {
		this.claimantCount = claimantCount;
	}
	
	public String getHandledName() {
		return handledName;
	}
	public void setHandledName(String handledName) {
		this.handledName = handledName;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public String getHandledPhone() {
		return handledPhone;
	}
	public void setHandledPhone(String handledPhone) {
		this.handledPhone = handledPhone;
	}
	public Long getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(Long goodsType) {
		this.goodsType = goodsType;
	}
	public Long getGoodsInBy() {
		return goodsInBy;
	}
	public void setGoodsInBy(Long goodsInBy) {
		this.goodsInBy = goodsInBy;
	}
	public Long getDep() {
		return dep;
	}
	public void setDep(Long dep) {
		this.dep = dep;
	}
	
	
}
