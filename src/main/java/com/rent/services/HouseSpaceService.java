package com.rent.services;

import java.util.List;

import com.rent.entity.HouseSpace;

public interface HouseSpaceService {

	/**
	 * 新增房间空闲记录(id手动赋值,唯一约束已处理)
	 * @param 房间空闲记录
	 * @return 执行结果
	 */
	int addHouseSpace(HouseSpace houseSpace) ;
	

	/**
	 * 修改房间空闲记录(id手动赋值,唯一约束已处理)
	 * @param 房间空闲记录
	 * @return 执行结果
	 */
	int updateHouseSpace(HouseSpace houseSpace) ;
	
	/**
	 * 根据id查询
	 * @param id
	 * @return 房间空闲记录
	 */
	HouseSpace findById(Integer id);
	
	/**
	 * 根据数组删除
	 * @param id
	 * @return 返回结果
	 */
	int delHouseSpace(Integer [] ids);
	

	/**
	 * 根据id删除
	 * @param id
	 * @return 返回结果
	 */
	int delHouseSpace(Integer id);
	
	
	HouseSpace findLastRecord(Integer houseId);
}
