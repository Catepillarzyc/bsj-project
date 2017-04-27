package com.questionnaire.dao;

import com.questionnaire.pojo.ClinicalApplication;
import java.util.List;

public interface ClinicalApplicationDao {

	public Integer addClinicalApplication(ClinicalApplication clinicalApplication);

	public boolean deleteClinicalApplication(ClinicalApplication clinicalApplication);

	public Integer updateClinicalApplication(ClinicalApplication clinicalApplication);

	public Integer countClinicalApplication(ClinicalApplication clinicalApplication);

	public ClinicalApplication findClinicalApplication(ClinicalApplication clinicalApplication);

	public List<ClinicalApplication> findClinicalApplicationList(ClinicalApplication clinicalApplication);

}
