package com.rent.services;

import java.util.List;

import com.rent.entity.SubsidyType;

public interface SubsidyTypeService {

	/**
	 * 新增
	 * @return 执行结果1或-1
	 */
	int addSubsidyType(SubsidyType subsidyType);
	/**
	 * 修改
	 * @return 执行结果1或-1
	 */
	int updateSubsidyType(SubsidyType subsidyType);
	
	/**
	 * 删除
	 * @return 执行结果1或-1
	 */
	int delSubsidyType(Integer id);
	

	/**
	 * 删除(根据数组)
	 * @return 执行结果1或-1
	 */
	int delSubsidyType(Integer[] ids);
	/**
	 * 根据主键查询
	 * @return 执行结果1或-1
	 */
	SubsidyType findById(Integer id);
	
	/**
	 * 查询全部
	 * @return 全部
	 */
	List<SubsidyType> findAll();
	
	/**
	 * 分页查询全部
	 * @param 当前页
	 * @param 页大小
	 * @return 房屋类型集合
	 */
	List<SubsidyType> findAllPaged(Integer currpage,Integer size);
	
	/**
	 * 查询总数
	 * @return 总数
	 */
	Integer getTotalCount();
	
	/**
	 * 根据名称查找
	 * @param 名称
	 * @return 对象
	 */
	SubsidyType findByName(String name);
	
	
}
