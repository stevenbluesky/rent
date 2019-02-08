package com.rent.services;

import java.util.List;

import com.rent.entity.Equipment;

public interface EquipmentService {
	/**
	 * ����
	 * @return ִ�н��1��-1
	 */
	int addEquipment(Equipment equipment);
	/**
	 * �޸�
	 * @return ִ�н��1��-1
	 */
	int updateEquipment(Equipment equipment);
	
	/**
	 * ɾ��
	 * @return ִ�н��1��-1
	 */
	int delEquipment(Integer id);
	

	/**
	 * ɾ��(��������)
	 * @return ִ�н��1��-1
	 */
	int delEquipment(Integer[] ids);
	/**
	 * ����������ѯ
	 * @return ִ�н��1��-1
	 */
	Equipment findById(Integer id);
	
	/**
	 * ��ҳ��ѯȫ��
	 * @param ��ǰҳ
	 * @param ҳ��С
	 * @return �������ͼ���
	 */
	List<Equipment> findAllPaged(Integer currpage,Integer size);
	
	/**
	 * ��ѯ����
	 * @return ����
	 */
	Integer getTotalCount();
	List<Equipment> findAllEqu();
}
