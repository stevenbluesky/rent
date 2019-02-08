package com.rent.services;

import java.util.List;

import com.rent.entity.CostCode;


public interface CostCodeService {

	/**
	 * 新增
	 * @return 执行结果1或-1
	 */
	int addCostCode(CostCode costCode);
	/**
	 * 修改
	 * @return 执行结果1或-1
	 */
	int updateCostCode(CostCode costCode);
	
	/**
	 * 删除
	 * @return 执行结果1或-1
	 */
	int delCostCode(Integer id);
	

	/**
	 * 删除(根据数组)
	 * @return 执行结果1或-1
	 */
	int delCostCode(Integer[] ids);
	/**
	 * 根据主键查询
	 * @return 执行结果1或-1
	 */
	CostCode findById(Integer id);
	
	/**
	 * 分页查询全部
	 * @param 当前页
	 * @param 页大小
	 * @return 房屋类型集合
	 */
	List<CostCode> findAllPaged(Integer currpage,Integer size);
	
	/**
	 * 查询总数
	 * @return 总数
	 */
	Integer getTotalCount();
}
