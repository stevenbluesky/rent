package com.rent.services;

import java.util.List;

import com.rent.entity.RepaireReason;

public interface RepaireReasonService {
	/**
	 * 新增
	 * @return 执行结果1或-1
	 */
	int addRepaireReason(RepaireReason repaireReason);
	/**
	 * 修改
	 * @return 执行结果1或-1
	 */
	int updateRepaireReason(RepaireReason repaireReason);
	
	/**
	 * 删除
	 * @return 执行结果1或-1
	 */
	int delRepaireReason(Integer id);
	

	/**
	 * 删除(根据数组)
	 * @return 执行结果1或-1
	 */
	int delRepaireReason(Integer[] ids);
	/**
	 * 根据主键查询
	 * @return 执行结果1或-1
	 */
	RepaireReason findById(Integer id);
	
	/**
	 * 分页查询全部
	 * @param 当前页
	 * @param 页大小
	 * @return 房屋类型集合
	 */
	List<RepaireReason> findAllPaged(Integer currpage,Integer size);
	
	/**
	 * 查询总数
	 * @return 总数
	 */
	Integer getTotalCount();
}
