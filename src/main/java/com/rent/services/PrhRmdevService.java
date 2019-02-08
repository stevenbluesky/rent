package com.rent.services;

import java.util.List;

import com.rent.condition.RmdevCondition;
import com.rent.entity.Building;
import com.rent.entity.PrhRmdev;

public interface PrhRmdevService {

	/**
	 * 根据id查找设备信息
	 * @return 
	 */
	List<PrhRmdev> findById(Integer houseid);
	
	/**
     * 根据物业id分页查询全部设备信息
     * @return 设备
     */
	List<PrhRmdev> findByConditionPaged(RmdevCondition condition,Integer currage,Integer size);
	
	
	Integer insert(PrhRmdev prhRmdev);
	
	 int updateStateByid(Integer id);
	 
	 Integer findMaxID();
}
