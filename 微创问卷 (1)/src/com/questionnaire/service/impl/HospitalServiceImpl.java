package com.questionnaire.service.impl;

import com.questionnaire.pojo.Hospital;
import com.questionnaire.pojo.dto.PageDto;
import com.questionnaire.dao.HospitalDao;
import com.questionnaire.service.HospitalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("hospitalService")
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	private HospitalDao hospitalDao;

	/**
	*增加Hospital
	*author:xjg
	*/
	@Override
	public Integer addHospital(Hospital hospital) {
			hospitalDao.addHospital(hospital);
			return hospital.getId();
	}

	/**
	*删除Hospital
	*author:xjg
	*/
	@Override
	public boolean deleteHospital(Hospital hospital)   {
			return hospitalDao.deleteHospital(hospital);
	}

	/**
	*更新Hospital
	*author:xjg
	*/
	@Override
	public Integer updateHospital(Hospital hospital)   {
			return hospitalDao.updateHospital(hospital);
	}

	/**
	*统计总数Hospital
	*author:xjg
	*/
	@Override
	public Integer countHospital(Hospital hospital)  {
			return hospitalDao.countHospital(hospital);
	}

	/**
	*查找Hospital
	*author:xjg
	*/
	@Override
	public Hospital findHospital(Hospital hospital)  {
			return hospitalDao.findHospital(hospital);
	}

	/**
	*查找列表Hospital
	*author:xjg
	*/
	@Override
	public List<Hospital> findHospitalList(Hospital hospital) {
			return hospitalDao.findHospitalList(hospital);
	}

	@Override
	public PageDto<Hospital> pageHospital(PageDto<Hospital> page) {
		List<Hospital> hList = new ArrayList<Hospital>();
		Hospital hospital = new Hospital();
		hospital.setStart(page.getStartIndex());
		hospital.setSize(page.getPageSize());
		hList = hospitalDao.findHospitalList(hospital);
		page.setRecords(hList);
		page.setTotalRecords(hospitalDao.countHospital(null));
		return page;
	}
	
	
	
}

