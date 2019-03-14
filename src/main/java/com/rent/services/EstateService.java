package com.rent.services;

import java.util.List;

import com.rent.entity.Estate;
import com.rent.entity.EstateType;

public interface EstateService {
	/**
	 * ��ѯȫ��
	 * @return ��ҵ����
	 */
	List<Estate> findAll();
	
	/**
	 * ����
	 * @return ִ�н��1��-1
	 */
	int insertEstate(Estate estate);
	/**
	 * �޸�
	 * @return ִ�н��1��-1
	 */
	int updateEstate(Estate estate);
	
	/**
	 * ɾ��
	 * @return ִ�н��1��-1
	 */
	int delEstate(Integer id);
	/**
	 * ����������ѯ
	 * @return ִ�н��1��-1
	 */
	Estate findById(Integer id);
	/**
	 * ��ѯȫ��
	 * @return ��ȡ������ҵ����
	 */
	List<EstateType> findAllEstateTypes();
	/**
	 * �������Ʋ���
	 * @param ����
	 * @return ����
	 */
	Estate findByName(String name);
}
