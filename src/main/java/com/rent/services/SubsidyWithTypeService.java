package com.rent.services;

import java.util.List;

import com.rent.entity.SubsidyWithType;

public interface SubsidyWithTypeService {

	/**
	 * ����
	 * @return ִ�н��1��-1
	 */
	int addSubsidyWithType(SubsidyWithType subsidyWithType);
	
	/**
	 * ����
	 * @return ִ�н��1��-1
	 */
	int addAuto(List<SubsidyWithType> subsidyWithTypes);
	/**
	 * �޸�
	 * @return ִ�н��1��-1
	 */
	int updateSubsidyWithType(SubsidyWithType subsidyWithType);
	
	/**
	 * ɾ��
	 * @return ִ�н��1��-1
	 */
	int delSubsidyWithType(Integer id);
	

	/**
	 * ɾ��(��������)
	 * @return ִ�н��1��-1
	 */
	int delSubsidyWithType(Integer[] ids);
	/**
	 * ����������ѯ
	 * @return ִ�н��1��-1
	 */
	SubsidyWithType findById(Integer id);
	
	/**
	 * ���ݲ������ɾ��
	 * @param subsidyId
	 * @return
	 */
	int deleteBySubsidy(int subsidyId);

	/**
	 * ���ݲ����������ɾ��
	 * @param subsidyId
	 * @return
	 */
	int deleteBySubsidy(Integer [] subsidyId);
}
