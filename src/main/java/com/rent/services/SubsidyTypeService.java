package com.rent.services;

import java.util.List;

import com.rent.entity.SubsidyType;

public interface SubsidyTypeService {

	/**
	 * ����
	 * @return ִ�н��1��-1
	 */
	int addSubsidyType(SubsidyType subsidyType);
	/**
	 * �޸�
	 * @return ִ�н��1��-1
	 */
	int updateSubsidyType(SubsidyType subsidyType);
	
	/**
	 * ɾ��
	 * @return ִ�н��1��-1
	 */
	int delSubsidyType(Integer id);
	

	/**
	 * ɾ��(��������)
	 * @return ִ�н��1��-1
	 */
	int delSubsidyType(Integer[] ids);
	/**
	 * ����������ѯ
	 * @return ִ�н��1��-1
	 */
	SubsidyType findById(Integer id);
	
	/**
	 * ��ѯȫ��
	 * @return ȫ��
	 */
	List<SubsidyType> findAll();
	
	/**
	 * ��ҳ��ѯȫ��
	 * @param ��ǰҳ
	 * @param ҳ��С
	 * @return �������ͼ���
	 */
	List<SubsidyType> findAllPaged(Integer currpage,Integer size);
	
	/**
	 * ��ѯ����
	 * @return ����
	 */
	Integer getTotalCount();
	
	/**
	 * �������Ʋ���
	 * @param ����
	 * @return ����
	 */
	SubsidyType findByName(String name);
	
	
}
