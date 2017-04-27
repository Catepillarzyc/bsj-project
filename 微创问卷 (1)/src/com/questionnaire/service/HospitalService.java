package com.questionnaire.service;

import com.questionnaire.pojo.Hospital;
import com.questionnaire.pojo.dto.PageDto;

import java.util.List;

public interface HospitalService {

	/**
	 * 增加Hospital
	 */
	public Integer addHospital(Hospital hospital);

	/**
	 * 删除Hospital
	 */
	public boolean deleteHospital(Hospital hospital);

	/**
	 * 修改Hospital
	 */
	public Integer updateHospital(Hospital hospital);

	/**
	 * 查询Hospital
	 */
	public Hospital findHospital(Hospital hospital);

	/**
	 * 统计总数Hospital
	 */
	public Integer countHospital(Hospital hospital);

	/**
	 * 查询Hospital列表
	 */
	public List<Hospital> findHospitalList(Hospital hospital);
	
	public PageDto<Hospital> pageHospital(PageDto<Hospital> page);
	
}
