package com.rent.services;

import java.util.List;

import com.rent.entity.Building;
import com.rent.entity.BuildingFloor;

public interface BuildingFloorService {
	/**
	 * 查询全部
	 * @return 楼层集合
	 */
	List<BuildingFloor> findAll();

	/**
	 * 新增楼层(id手动赋值,唯一约束已处理)
	 * @param 楼层
	 * @return 执行结果
	 */
	int addBuildingFloor(BuildingFloor buildingFloor) ;


	/**
	 * 修改楼层(id手动赋值,唯一约束已处理)
	 * @param 楼层
	 * @return 执行结果
	 */
	int updateBuildingFloor(BuildingFloor buildingFloor) ;

	/**
	 * 根据id查询
	 * @param id
	 * @return 楼层
	 */
	BuildingFloor findById(Integer id);

	/**
	 * 根据数组删除
	 * @param id
	 * @return 返回结果
	 */
	int delBuildingFloor(Integer [] ids);


	/**
	 * 根据id删除
	 * @param id
	 * @return 返回结果
	 */
	int delBuildingFloor(Integer id);



	/**
	 * 查询总数
	 * @return 总数
	 */
	int getTotalCount();
	/**
	 * 分页查询全部
	 * @param 当前页
	 * @param 页大小
	 * @return 楼层集合
	 */
	List<BuildingFloor> findAllPaged(Integer currpage,Integer size);
	/**
	 * 根据起始位置新增楼层(先删除再新增)
	 * @param 开始楼号
	 * @param 结束楼号
	 * @return 楼层集合
	 */
	int autoAddBuildingFloors(Integer begin,Integer end);
}
