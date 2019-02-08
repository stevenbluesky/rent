package com.rent.services;

import java.util.List;

import com.rent.entity.Electric;

public interface ElectricService {
	/**
	 * ����
	 * @return ִ�н��1��-1
	 */
	int addElectric(Electric electric);
	/**
	 * �޸�
	 * @return ִ�н��1��-1
	 */
	int updateElectric(Electric electric);
	
	/**
	 * ɾ��
	 * @return ִ�н��1��-1
	 */
	int delElectric(Integer id);
	

	/**
	 * ɾ��(��������)
	 * @return ִ�н��1��-1
	 */
	int delElectric(Integer[] ids);
	/**
	 * ����������ѯ
	 * @return ִ�н��1��-1
	 */
	Electric findById(Integer id);
	
	/**
	 * ��ҳ��ѯȫ��
	 * @param ��ǰҳ
	 * @param ҳ��С
	 * @return �������ͼ���
	 */
	List<Electric> findAllPaged(Integer currpage,Integer size);
	
	/**
	 * ��ѯ����
	 * @return ����
	 */
	Integer getTotalCount();
}
