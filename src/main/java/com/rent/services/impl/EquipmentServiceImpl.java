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
	 * ����
	 * @return ִ�н��1��-1
	 */
	public int addEquipment(Equipment equipment){
		return equipmentMapper.insert(equipment);
	}
	/**
	 * �޸�
	 * @return ִ�н��1��-1
	 */
	public int updateEquipment(Equipment equipment){
		return equipmentMapper.updateByPrimaryKey(equipment);
	}
	
	/**
	 * ɾ��
	 * @return ִ�н��1��-1
	 */
	public int delEquipment(Integer id){
		return equipmentMapper.deleteByPrimaryKey(id);
	}
	

	/**
	 * ɾ��(��������)
	 * @return ִ�н��1��-1
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
	 * ����������ѯ
	 * @return ִ�н��1��-1
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
	 * ��ҳ��ѯȫ��
	 * @param ��ǰҳ
	 * @param ҳ��С
	 * @return �������ͼ���
	 */

	/**
	 * ��ѯ����
	 * @return ����
	 */
	
}
