package com.questionnaire.service;

import com.questionnaire.pojo.Equipment;
import java.util.List;

public interface EquipmentService {

	/**
	 * 增加Equipment
	 */
	public Integer addEquipment(Equipment equipment);

	/**
	 * 删除Equipment
	 */
	public boolean deleteEquipment(Equipment equipment);

	/**
	 * 修改Equipment
	 */
	public Integer updateEquipment(Equipment equipment);

	/**
	 * 查询Equipment
	 */
	public Equipment findEquipment(Equipment equipment);

	/**
	 * 统计总数Equipment
	 */
	public Integer countEquipment(Equipment equipment);

	/**
	 * 查询Equipment列表
	 */
	public List<Equipment> findEquipmentList(Equipment equipment);

}
