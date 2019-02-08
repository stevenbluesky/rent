package com.rent.services;

import java.util.List;

import com.rent.entity.RoomFeature;

public interface RoomFeatureService {

	/**
	 * 新增
	 * @return 执行结果1或-1
	 */
	int addRoomFeature(RoomFeature roomFeature);
	/**
	 * 修改
	 * @return 执行结果1或-1
	 */
	int updateRoomFeature(RoomFeature roomFeature);
	
	/**
	 * 删除
	 * @return 执行结果1或-1
	 */
	int delRoomFeature(Integer id);
	

	/**
	 * 删除(根据数组)
	 * @return 执行结果1或-1
	 */
	int delRoomFeature(Integer[] ids);
	/**
	 * 根据主键查询
	 * @return 执行结果1或-1
	 */
	RoomFeature findById(Integer id);
	
	/**
	 * 分页查询全部
	 * @param 当前页
	 * @param 页大小
	 * @return 房屋特征集合
	 */
	List<RoomFeature> findAllPaged(Integer currpage,Integer size);
	

	/**
	 * 查询总数
	 * @return 总数
	 */
	Integer getTotalCount();
}
