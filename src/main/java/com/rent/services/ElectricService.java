package com.rent.services;

import java.util.List;

import com.rent.entity.Electric;

public interface ElectricService {
	/**
	 * 新增
	 * @return 执行结果1或-1
	 */
	int addElectric(Electric electric);
	/**
	 * 修改
	 * @return 执行结果1或-1
	 */
	int updateElectric(Electric electric);
	
	/**
	 * 删除
	 * @return 执行结果1或-1
	 */
	int delElectric(Integer id);
	

	/**
	 * 删除(根据数组)
	 * @return 执行结果1或-1
	 */
	int delElectric(Integer[] ids);
	/**
	 * 根据主键查询
	 * @return 执行结果1或-1
	 */
	Electric findById(Integer id);
	
	/**
	 * 分页查询全部
	 * @param 当前页
	 * @param 页大小
	 * @return 房屋类型集合
	 */
	List<Electric> findAllPaged(Integer currpage,Integer size);
	
	/**
	 * 查询总数
	 * @return 总数
	 */
	Integer getTotalCount();
}
