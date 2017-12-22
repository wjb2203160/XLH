package com.mtr.psn.model.webEntity;

import java.util.Date;

import com.mtr.psn.model.BaseModel;
import com.mtr.psn.model.user.User;

public class UserEntity extends BaseModel{

	private String cellphone;
	private String userName;
	private Date birthday;
	private String cardTypeName;//证件名称
	private String cardCode;//证件号
	private String country;
	private String province;
	private String city;
	private String county;
	private String address;
	private String email;
	private Long roleId;
	private Date hiredate;//入职日期
	private String idCardPicPathA;//身份证正面
	private String idCardPicPathB;//反面
	private String headPortraitPath;//头像
	private Long superior;//上级领导
	private String superiorName;//上级领导
	private String superiorPhone;//上级领导电话
	private Long stateId;
	private String state;//用户状态
	private Long userTypeId;
	private String userType;//用户类型
	private Integer isStart;
	
	public UserEntity(){};
	
	public UserEntity(User user){
		super(user.getId(),user.getCreateBy(),user.getCreateTime(),
				user.getModifyBy(),user.getModifyTime(),user.getRemarks());
		this.cellphone=user.getCellphone();
		this.userName=user.getUserName();
		this.birthday=user.getBirthday();
		this.cardCode=user.getCardCode();
		this.cardTypeName=user.getCardTypeName();
		this.country=user.getCountry();
		this.province=user.getProvince();
		this.city=user.getCity();
		this.county=user.getCounty();
		this.address=user.getAddress();
		this.email=user.getEmail();
		this.roleId=user.getRoleId();
		this.hiredate=user.getHiredate();
		this.idCardPicPathA=user.getIdCardPicPath();
		this.headPortraitPath=user.getHeadPortraitPath();
		this.superior=user.getSuperior();
		this.stateId=user.getStateId();
		this.userTypeId=user.getUserTypeId();
		this.isStart=user.getIsStart();
	};
	
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getCardTypeName() {
		return cardTypeName;
	}
	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdCardPicPathA() {
		return idCardPicPathA;
	}
	public void setIdCardPicPathA(String idCardPicPathA) {
		this.idCardPicPathA = idCardPicPathA;
	}
	public String getIdCardPicPathB() {
		return idCardPicPathB;
	}
	public void setIdCardPicPathB(String idCardPicPathB) {
		this.idCardPicPathB = idCardPicPathB;
	}
	public String getHeadPortraitPath() {
		return headPortraitPath;
	}
	public void setHeadPortraitPath(String headPortraitPath) {
		this.headPortraitPath = headPortraitPath;
	}
	public Integer getIsStart() {
		return isStart;
	}
	public void setIsStart(Integer isStart) {
		this.isStart = isStart;
	}
	public String getSuperiorName() {
		return superiorName;
	}
	public void setSuperiorName(String superiorName) {
		this.superiorName = superiorName;
	}
	public String getSuperiorPhone() {
		return superiorPhone;
	}
	public void setSuperiorPhone(String superiorPhone) {
		this.superiorPhone = superiorPhone;
	}
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getSuperior() {
		return superior;
	}

	public void setSuperior(Long superior) {
		this.superior = superior;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Long getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	
}
