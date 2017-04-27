package com.questionnaire.pojo;

import java.util.Date;

public class Hospital{
	private Integer id;	//编号
	private String hospitalName;	//医院名称
	private String legalRepresentative;	//法人代表
	private String contact;	//联系人
	private String hospitalAddress;	//医院地址
	private String telephoneFax;	//电话传真
	private String ksPosition;	//科室职位
	private String email;	//电子邮箱
	private String contactPhone;	//联系电话
	private String hospitalLevel;	//医院等级（三甲、二甲、、、）
	private String hospitalNature;	//医院性质
	private String beds;	//床位数
	private Integer isDel;	//删除状态
	private Date gmtCreated;	//创建时间
	private Date gmtModified;	//更新时间
    
	private Integer start;
	private Integer size;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getLegalRepresentative() {
		return legalRepresentative;
	}
	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getHospitalAddress() {
		return hospitalAddress;
	}
	public void setHospitalAddress(String hospitalAddress) {
		this.hospitalAddress = hospitalAddress;
	}
	public String getTelephoneFax() {
		return telephoneFax;
	}
	public void setTelephoneFax(String telephoneFax) {
		this.telephoneFax = telephoneFax;
	}
	public String getKsPosition() {
		return ksPosition;
	}
	public void setKsPosition(String ksPosition) {
		this.ksPosition = ksPosition;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getHospitalLevel() {
		return hospitalLevel;
	}
	public void setHospitalLevel(String hospitalLevel) {
		this.hospitalLevel = hospitalLevel;
	}
	public String getHospitalNature() {
		return hospitalNature;
	}
	public void setHospitalNature(String hospitalNature) {
		this.hospitalNature = hospitalNature;
	}
	public String getBeds() {
		return beds;
	}
	public void setBeds(String beds) {
		this.beds = beds;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public Date getGmtCreated() {
		return gmtCreated;
	}
	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}
	public Date getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	
}