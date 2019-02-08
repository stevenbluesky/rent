package com.rent.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.services.EquipmentService;

import com.sun.org.apache.regexp.internal.recompile;

import com.rent.dao.EquipmentMapper;
import com.rent.entity.Building;
import com.rent.entity.Equipment;
@Service
@Transactional(readOnly = true)
public class EquipmentServiceImpl implements EquipmentService{
	@Autowired
	private EquipmentMapper equipmentMapper;
	
	public EquipmentMapper getEquipmentMapper() {
		return equipmentMapper;
	}
	public void setEquipmentMapper(EquipmentMapper equipmentMapper) {
		this.equipmentMapper = equipmentMapper;
	}
	/**
	 * 新增
	 * @return 执行结果1或-1
	 */
	public int addEquipment(Equipment equipment){
		return equipmentMapper.insert(equipment);
	}
	/**
	 * 修改
	 * @return 执行结果1或-1
	 */
	public int updateEquipment(Equipment equipment){
		return equipmentMapper.updateByPrimaryKey(equipment);
	}
	
	/**
	 * 删除
	 * @return 执行结果1或-1
	 */
	public int delEquipment(Integer id){
		return equipmentMapper.deleteByPrimaryKey(id);
	}
	

	/**
	 * 删除(根据数组)
	 * @return 执行结果1或-1
	 */
	public int delEquipment(Integer[] ids){
		int result=0;
		if (ids.length!=0) {
			for (Integer id : ids) {
				result= equipmentMapper.deleteByPrimaryKey(id);
				if (result!=1) {
					return -1;
				}
			}
		}
		return 1;
	}
	/**
	 * 根据主键查询
	 * @return 执行结果1或-1
	 */
	public Equipment findById(Integer id){
		return equipmentMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<Equipment> findAllPaged(Integer currpage, Integer size) {
		
		int begin = (currpage - 1) * size + 1;
		int end = begin + size - 1;
		List<Equipment> Equipment = equipmentMapper.findAllPaged(
				(currpage - 1) * size + 1, end);
			
		return Equipment;
	}
	@Override
	public Integer getTotalCount() {
		
		return equipmentMapper.getTotalCount();
	}
	@Override
	public List<Equipment> findAllEqu() {
		
		return equipmentMapper.findAllEqu();
	}
	
	/**
	 * 分页查询全部
	 * @param 当前页
	 * @param 页大小
	 * @return 房屋类型集合
	 */

	/**
	 * 查询总数
	 * @return 总数
	 */
	
}
