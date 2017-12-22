package com.mtr.psn.model;

import java.util.Date;

public class BaseModel {

	private Long id; //父类id
	private Integer starNum;//分页的起始行
	private Integer pageSize;//每页显示多少行
	private Date createTime;
	private Long createBy;
	private Date modifyTime;
	private Long modifyBy;
	private String remarks;
	private Date startTime;
	private Date endtime;
	
	public BaseModel(){}
	
	public BaseModel(Long id,Long createBy,
			Date createTime,Long modifyBy,
			Date modifyTime,String remarks){
		this.id = id;
		this.createBy=createBy;
		this.modifyBy=modifyBy;
		this.createTime=createTime;
		this.modifyTime=modifyTime;
		this.remarks=remarks;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Long getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Long modifyBy) {
		this.modifyBy = modifyBy;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Integer getStarNum() {
		return starNum;
	}
	public void setStarNum(Integer starNum) {
		this.starNum = starNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndTime(Date endtime) {
		this.endtime = endtime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
}
