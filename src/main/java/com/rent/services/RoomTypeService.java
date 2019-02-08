package com.rent.services;

import java.util.List;

import com.rent.entity.RoomType;

public interface RoomTypeService {
	
	/**
	 * 新增
	 * @return 执行结果1或-1
	 */
	int addRoomType(RoomType roomType);
	/**
	 * 修改
	 * @return 执行结果1或-1
	 */
	int updateRoomType(RoomType roomType);
	
	/**
	 * 删除
	 * @return 执行结果1或-1
	 */
	int delRoomType(Integer id);
	

	/**
	 * 删除(根据数组)
	 * @return 执行结果1或-1
	 */
	int delRoomType(Integer[] ids);
	/**
	 * 根据主键查询
	 * @return 执行结果1或-1
	 */
	RoomType findById(Integer id);
	
	/**
	 * 查询全部
	 * @return 全部
	 */
	List<RoomType> findAll();
	
	/**
	 * 分页查询全部
	 * @param 当前页
	 * @param 页大小
	 * @return 房屋类型集合
	 */
	List<RoomType> findAllPaged(Integer currpage,Integer size);
	
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
	RoomType findtByName(String name);
	
	Integer findMaxId();
	
}
