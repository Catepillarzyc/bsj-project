package com.questionnaire.pojo;

import java.util.Date;

public class Ks{
	private Integer id;	//编号
	private Integer hospitalId;	//医院编号
	private String generalSurgery;	//普外科
	private String thoracicSurgery;	//胸外科
	private String urology;	//泌尿外科
	private String womenObstetrics;	//妇产科
	private String orthopaedic;	//骨科
	private String radioactive;	//放射介入科
	private Integer isDel;	//删除状态
	private Date gmtCreated;	//创建时间
	private Date gmtModified;	//更新时间


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getGeneralSurgery() {
		return generalSurgery;
	}

	public void setGeneralSurgery(String generalSurgery) {
		this.generalSurgery = generalSurgery;
	}

	public String getThoracicSurgery() {
		return thoracicSurgery;
	}

	public void setThoracicSurgery(String thoracicSurgery) {
		this.thoracicSurgery = thoracicSurgery;
	}

	public String getUrology() {
		return urology;
	}

	public void setUrology(String urology) {
		this.urology = urology;
	}

	public String getWomenObstetrics() {
		return womenObstetrics;
	}

	public void setWomenObstetrics(String womenObstetrics) {
		this.womenObstetrics = womenObstetrics;
	}

	public String getOrthopaedic() {
		return orthopaedic;
	}

	public void setOrthopaedic(String orthopaedic) {
		this.orthopaedic = orthopaedic;
	}

	public String getRadioactive() {
		return radioactive;
	}

	public void setRadioactive(String radioactive) {
		this.radioactive = radioactive;
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

}