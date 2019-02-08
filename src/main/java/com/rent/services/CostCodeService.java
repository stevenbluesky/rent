package com.rent.services;

import java.util.List;

import com.rent.entity.CostCode;


public interface CostCodeService {

	/**
	 * ����
	 * @return ִ�н��1��-1
	 */
	int addCostCode(CostCode costCode);
	/**
	 * �޸�
	 * @return ִ�н��1��-1
	 */
	int updateCostCode(CostCode costCode);
	
	/**
	 * ɾ��
	 * @return ִ�н��1��-1
	 */
	int delCostCode(Integer id);
	

	/**
	 * ɾ��(��������)
	 * @return ִ�н��1��-1
	 */
	int delCostCode(Integer[] ids);
	/**
	 * ����������ѯ
	 * @return ִ�н��1��-1
	 */
	CostCode findById(Integer id);
	
	/**
	 * ��ҳ��ѯȫ��
	 * @param ��ǰҳ
	 * @param ҳ��С
	 * @return �������ͼ���
	 */
	List<CostCode> findAllPaged(Integer currpage,Integer size);
	
	/**
	 * ��ѯ����
	 * @return ����
	 */
	Integer getTotalCount();
}
