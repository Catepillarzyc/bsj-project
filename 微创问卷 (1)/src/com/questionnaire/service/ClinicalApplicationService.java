package com.questionnaire.service;

import com.questionnaire.pojo.ClinicalApplication;
import java.util.List;

public interface ClinicalApplicationService {

	/**
	 * 增加ClinicalApplication
	 */
	public Integer addClinicalApplication(ClinicalApplication clinicalApplication);

	/**
	 * 删除ClinicalApplication
	 */
	public boolean deleteClinicalApplication(ClinicalApplication clinicalApplication);

	/**
	 * 修改ClinicalApplication
	 */
	public Integer updateClinicalApplication(ClinicalApplication clinicalApplication);

	/**
	 * 查询ClinicalApplication
	 */
	public ClinicalApplication findClinicalApplication(ClinicalApplication clinicalApplication);

	/**
	 * 统计总数ClinicalApplication
	 */
	public Integer countClinicalApplication(ClinicalApplication clinicalApplication);

	/**
	 * 查询ClinicalApplication列表
	 */
	public List<ClinicalApplication> findClinicalApplicationList(ClinicalApplication clinicalApplication);

}
