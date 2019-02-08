package com.rent.services;

import java.util.List;


import com.rent.entity.PrhLinkman;

public interface PrhLinkManService {
	/**
	 * 根据主单查询
	 * @return 楼层集合
	 */
	List<PrhLinkman> findAllByMaster(int masterId);
	
	/**
	 * 新增楼层(id手动赋值,唯一约束已处理)
	 * @param 楼层
	 * @return 执行结果
	 */
	int addPrhLinkman(PrhLinkman prhLinkman) ;
	
	/**
	 * 批量新增同住人
	 * @param prhLinkmans
	 * @return
	 */
	
	int addPrhLinkman(List<PrhLinkman> prhLinkmans);
	/**
	 * 修改(id手动赋值,唯一约束已处理)
	 * @param 楼层
	 * @return 执行结果
	 */
	int updatePrhLinkman(PrhLinkman prhLinkman) ;
	
	/**
	 * 修改(id手动赋值,唯一约束已处理)
	 * @param 楼层
	 * @return 执行结果
	 */
	int updatePrhLinkman(List<PrhLinkman> prhLinkmans) ;
	/**
	 * 根据id查询
	 * @param id
	 * @return 楼层
	 */
	PrhLinkman findById(Integer id);
	
	

	/**
	 * 根据id删除
	 * @param id
	 * @return 返回结果
	 */
	int delPrhLinkman(Integer id);
	
	
	
	/**
	 * 根据主单查询总数
	 * @return 总数
	 */
	int getTotalCountByMaster(int masterId);
	/**
	 * 分页查询全部
	 * @param 当前页
	 * @param 页大小
	 * @return 楼层集合
	 */
}
