package com.rent.services;

import java.util.List;

import com.rent.entity.SubsidyWithType;

public interface SubsidyWithTypeService {

	/**
	 * 新增
	 * @return 执行结果1或-1
	 */
	int addSubsidyWithType(SubsidyWithType subsidyWithType);
	
	/**
	 * 批量
	 * @return 执行结果1或-1
	 */
	int addAuto(List<SubsidyWithType> subsidyWithTypes);
	/**
	 * 修改
	 * @return 执行结果1或-1
	 */
	int updateSubsidyWithType(SubsidyWithType subsidyWithType);
	
	/**
	 * 删除
	 * @return 执行结果1或-1
	 */
	int delSubsidyWithType(Integer id);
	

	/**
	 * 删除(根据数组)
	 * @return 执行结果1或-1
	 */
	int delSubsidyWithType(Integer[] ids);
	/**
	 * 根据主键查询
	 * @return 执行结果1或-1
	 */
	SubsidyWithType findById(Integer id);
	
	/**
	 * 根据补贴编号删除
	 * @param subsidyId
	 * @return
	 */
	int deleteBySubsidy(int subsidyId);

	/**
	 * 根据补贴编号批量删除
	 * @param subsidyId
	 * @return
	 */
	int deleteBySubsidy(Integer [] subsidyId);
}
