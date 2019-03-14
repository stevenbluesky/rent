package com.rent.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.dao.SubsidyWithTypeMapper;

import com.rent.entity.SubsidyType;
import com.rent.entity.SubsidyWithType;
import com.rent.services.SubsidyWithTypeService;
@Service
@Transactional
public class SubsidyWithTypeServiceImpl implements SubsidyWithTypeService{

	@Autowired
	private SubsidyWithTypeMapper subsidyWithTypeMapper;

	public SubsidyWithTypeMapper getSubsidyWithTypeMapper() {
		return subsidyWithTypeMapper;
	}

	public void setSubsidyWithTypeMapper(SubsidyWithTypeMapper subsidyWithTypeMapper) {
		this.subsidyWithTypeMapper = subsidyWithTypeMapper;
	}

	public int addSubsidyWithType(SubsidyWithType subsidyWithType) {
		return subsidyWithTypeMapper.insert(subsidyWithType);
	}

	public int updateSubsidyWithType(SubsidyWithType subsidyWithType) {
		return subsidyWithTypeMapper.updateByPrimaryKey(subsidyWithType);
	}

	public int delSubsidyWithType(Integer id) {
		return subsidyWithTypeMapper.deleteByPrimaryKey(id);
	}

	public int delSubsidyWithType(Integer[] ids) {
		int result=0;
		if (ids.length!=0) {
			for (Integer id : ids) {
				result= subsidyWithTypeMapper.deleteByPrimaryKey(id);
			}
			if (result!=1) {
				return -1;
			}
		}
		return 1;

	}

	public SubsidyWithType findById(Integer id) {
		return subsidyWithTypeMapper.selectByPrimaryKey(id);
	}
	//批量新增
	public int addAuto(List<SubsidyWithType> subsidyWithTypes) {
		int  insert=0;
		for (SubsidyWithType subsidyWithType : subsidyWithTypes) {
			insert = subsidyWithTypeMapper.insert(subsidyWithType);
			if (insert==-1) {
				return -1;
			}
		}

		return 1;
	}

	public int deleteBySubsidy(int subsidyId) {
		return subsidyWithTypeMapper.deleteBySubsidy(subsidyId);
	}

	public int deleteBySubsidy(Integer [] subsidyId) {

		int result=0;
		if (subsidyId.length!=0) {
			for (Integer id : subsidyId) {
				result= subsidyWithTypeMapper.deleteBySubsidy(id);
			}
			if (result!=1) {
				return -1;
			}
		}
		return 1;
	}

}
