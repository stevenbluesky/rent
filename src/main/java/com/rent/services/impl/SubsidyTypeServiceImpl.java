package com.rent.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.dao.SubsidyTypeMapper;

import com.rent.entity.SubsidyPercent;
import com.rent.entity.SubsidyType;

import com.rent.services.SubsidyTypeService;
@Service
@Transactional(readOnly = true)
public class SubsidyTypeServiceImpl implements SubsidyTypeService{

	@Autowired
	private SubsidyTypeMapper subsidyTypeMapper;
	
	public SubsidyTypeMapper getSubsidyTypeMapper() {
		return subsidyTypeMapper;
	}
	public void setSubsidyTypeMapper(SubsidyTypeMapper subsidyTypeMapper) {
		this.subsidyTypeMapper = subsidyTypeMapper;
	}
	//����
	public int addSubsidyType(SubsidyType subsidyType) {
		return subsidyTypeMapper.insert(subsidyType);
	}
	//�޸�
	public int updateSubsidyType(SubsidyType subsidyType) {
		return subsidyTypeMapper.updateByPrimaryKey(subsidyType);
	}
	//ɾ��
	public int delSubsidyType(Integer id) {
		return subsidyTypeMapper.deleteByPrimaryKey(id);
	}
	//ɾ������
	public int delSubsidyType(Integer[] ids) {
		int result=0;
		if (ids.length!=0) {
			for (Integer id : ids) {
				result= subsidyTypeMapper.deleteByPrimaryKey(id);
			}
			if (result!=1) {
				return -1;
			}
		}
		return 1;
		
	}
	//����id����
	public SubsidyType findById(Integer id) {
		
		return subsidyTypeMapper.selectByPrimaryKey(id);
	}
	//��ѯȫ��
	public List<SubsidyType> findAll() {
		
		return subsidyTypeMapper.findAll();
	}
	//��ҳ��ѯ
	public List<SubsidyType> findAllPaged(Integer currpage, Integer size) {

		Integer begin=(currpage-1)*size+1;
		Integer end=begin+size-1;
		return subsidyTypeMapper.findAllByPaged(begin, end);
		
	}
	//��ѯ����
	public Integer getTotalCount() {
		return subsidyTypeMapper.getTotalCount();
	}
	//ͨ�����Ʋ���
	public SubsidyType findByName(String name) {
		
		return subsidyTypeMapper.findByName(name);
	}
	
}
