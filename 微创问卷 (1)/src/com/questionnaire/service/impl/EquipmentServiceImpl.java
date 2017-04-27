package com.questionnaire.service.impl;

import com.questionnaire.pojo.Equipment;
import com.questionnaire.dao.EquipmentDao;
import com.questionnaire.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("equipmentService")
public class EquipmentServiceImpl implements EquipmentService {

	@Autowired
	private EquipmentDao equipmentDao;

	/**
	*增加Equipment
	*author:xjg
	*/
	@Override
	public Integer addEquipment(Equipment equipment) {
			equipmentDao.addEquipment(equipment);
			return equipment.getId();
	}

	/**
	*删除Equipment
	*author:xjg
	*/
	@Override
	public boolean deleteEquipment(Equipment equipment)   {
			return equipmentDao.deleteEquipment(equipment);
	}

	/**
	*更新Equipment
	*author:xjg
	*/
	@Override
	public Integer updateEquipment(Equipment equipment)   {
			return equipmentDao.updateEquipment(equipment);
	}

	/**
	*统计总数Equipment
	*author:xjg
	*/
	@Override
	public Integer countEquipment(Equipment equipment)  {
			return equipmentDao.countEquipment(equipment);
	}

	/**
	*查找Equipment
	*author:xjg
	*/
	@Override
	public Equipment findEquipment(Equipment equipment)  {
			return equipmentDao.findEquipment(equipment);
	}

	/**
	*查找列表Equipment
	*author:xjg
	*/
	@Override
	public List<Equipment> findEquipmentList(Equipment equipment) {
			return equipmentDao.findEquipmentList(equipment);
	}
}

