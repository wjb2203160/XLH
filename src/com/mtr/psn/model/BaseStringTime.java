package com.mtr.psn.model;

import java.util.Date;

public class BaseStringTime {

	private Long id; //父类id
	private Integer starNum;//分页的起始行
	private Integer pageSize;//每页显示多少行
	private String createTime;
	private Long createBy;
	private String modifyTime;
	private Long modifyBy;
	private String remarks;

	
	public BaseStringTime(){}
	

	

	public BaseStringTime(Long id,Long createBy,
			String createTime,Long modifyBy,

			String modifyTime,String remarks){
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
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
	
	
}