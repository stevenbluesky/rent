package com.rent.services;

import java.util.List;

import com.rent.entity.Estate;
import com.rent.entity.EstateType;

public interface EstateService {
	/**
	 * 查询全部
	 * @return 物业集合
	 */
	List<Estate> findAll();
	
	/**
	 * 新增
	 * @return 执行结果1或-1
	 */
	int addEstate(Estate estate);
	/**
	 * 修改
	 * @return 执行结果1或-1
	 */
	int updateEstate(Estate estate);
	
	/**
	 * 删除
	 * @return 执行结果1或-1
	 */
	int delEstate(Integer id);
	/**
	 * 根据主键查询
	 * @return 执行结果1或-1
	 */
	Estate findById(Integer id);
	/**
	 * 查询全部
	 * @return 获取所有物业类型
	 */
	List<EstateType> findAllEstateTypes();
	/**
	 * 根据名称查找
	 * @param 名称
	 * @return 对象
	 */
	Estate findByName(String name);
}
