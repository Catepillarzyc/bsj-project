package com.questionnaire.service.impl;

import com.questionnaire.pojo.ClinicalApplication;
import com.questionnaire.dao.ClinicalApplicationDao;
import com.questionnaire.service.ClinicalApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("clinicalApplicationService")
public class ClinicalApplicationServiceImpl implements ClinicalApplicationService {

	@Autowired
	private ClinicalApplicationDao clinicalApplicationDao;

	/**
	*增加ClinicalApplication
	*author:xjg
	*/
	@Override
	public Integer addClinicalApplication(ClinicalApplication clinicalApplication) {
			clinicalApplicationDao.addClinicalApplication(clinicalApplication);
			return clinicalApplication.getId();
	}

	/**
	*删除ClinicalApplication
	*author:xjg
	*/
	@Override
	public boolean deleteClinicalApplication(ClinicalApplication clinicalApplication)   {
			return clinicalApplicationDao.deleteClinicalApplication(clinicalApplication);
	}

	/**
	*更新ClinicalApplication
	*author:xjg
	*/
	@Override
	public Integer updateClinicalApplication(ClinicalApplication clinicalApplication)   {
			return clinicalApplicationDao.updateClinicalApplication(clinicalApplication);
	}

	/**
	*统计总数ClinicalApplication
	*author:xjg
	*/
	@Override
	public Integer countClinicalApplication(ClinicalApplication clinicalApplication)  {
			return clinicalApplicationDao.countClinicalApplication(clinicalApplication);
	}

	/**
	*查找ClinicalApplication
	*author:xjg
	*/
	@Override
	public ClinicalApplication findClinicalApplication(ClinicalApplication clinicalApplication)  {
			return clinicalApplicationDao.findClinicalApplication(clinicalApplication);
	}

	/**
	*查找列表ClinicalApplication
	*author:xjg
	*/
	@Override
	public List<ClinicalApplication> findClinicalApplicationList(ClinicalApplication clinicalApplication) {
			return clinicalApplicationDao.findClinicalApplicationList(clinicalApplication);
	}
}

