package com.rent.services;

import java.util.List;

import com.rent.entity.RentPayWay;

public interface RentPayWayService {
	/**
	 * ����
	 * @return ִ�н��1��-1
	 */
	int addRentPayWay(RentPayWay rentPayWay);
	/**
	 * �޸�
	 * @return ִ�н��1��-1
	 */
	int updateRentPayWay(RentPayWay rentPayWay);
	
	/**
	 * ɾ��
	 * @return ִ�н��1��-1
	 */
	int delRentPayWay(Integer id);
	

	/**
	 * ɾ��(��������)
	 * @return ִ�н��1��-1
	 */
	int delRentPayWay(Integer[] ids);
	/**
	 * ����������ѯ
	 * @return ִ�н��1��-1
	 */
	RentPayWay findById(Integer id);
	
	/**
	 * ��ҳ��ѯȫ��
	 * @param ��ǰҳ
	 * @param ҳ��С
	 * @return �������ͼ���
	 */
	List<RentPayWay> findAllPaged(Integer currpage,Integer size);
	
	/**
	 * ��ѯ����
	 * @return ����
	 */
	Integer getTotalCount();
	
	List<RentPayWay> findAll();
	
}
