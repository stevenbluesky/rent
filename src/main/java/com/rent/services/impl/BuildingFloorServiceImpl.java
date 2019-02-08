package com.rent.services.impl;

import com.rent.dao.BuildingFloorMapper;
import com.rent.entity.BuildingFloor;
import com.rent.services.BuildingFloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BuildingFloorServiceImpl implements BuildingFloorService {
	@Autowired
	private BuildingFloorMapper buildingFloorMapper;

	public BuildingFloorMapper getBuildingFloorMapper() {
		return buildingFloorMapper;
	}

	public void setBuildingFloorMapper(BuildingFloorMapper buildingFloorMapper) {
		this.buildingFloorMapper = buildingFloorMapper;
	}

	/**
	 * 查询全部
	 */
	public List<BuildingFloor> findAll() {
		return buildingFloorMapper.findAll();
	}

	/**
	 * 新增
	 */
	public int addBuildingFloor(BuildingFloor buildingFloor) {
		if (buildingFloor.getId() != null) {
			BuildingFloor floor = this.findById(buildingFloor.getId());
			if (floor != null) {
				return -1;
			}
			return buildingFloorMapper.insert(buildingFloor);
		}
		return -1;

	}

	/**
	 * 根据id查询
	 */
	public BuildingFloor findById(Integer id) {
		return buildingFloorMapper.selectByPrimaryKey(id);
	}
	/**
	 * 修改楼层
	 */

	public int updateBuildingFloor(BuildingFloor buildingFloor) {
		if (buildingFloor.getId() != null) {
			return buildingFloorMapper.updateByPrimaryKey(buildingFloor);
		}
		return -1;
	}
	/**
	 * 根据id删除
	 */
	public int delBuildingFloor(Integer id) {
		return buildingFloorMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 删除（根据数组）
	 */
	public int delBuildingFloor(Integer[] ids) {
		int result =0;
		for (Integer id : ids) {
			result = buildingFloorMapper.deleteByPrimaryKey(id);
			if (result!=1) {
				return -1;
			}
		}
		return 1;

	}

	public int getTotalCount() {
		return buildingFloorMapper.selectTotalCount();

	}

	public List<BuildingFloor> findAllPaged(Integer currpage, Integer size) {
		Integer begin=(currpage-1)*size+1;
		Integer end=begin+size-1;
		return buildingFloorMapper.getAllPaged((currpage-1)*size, end);
	}
	/**
	 * 根据区间新增楼层
	 */
	public int autoAddBuildingFloors(Integer begin, Integer end) {

		try {
			for (int i = begin; i <= end; i++) {
				//先删除
				if (buildingFloorMapper.selectByPrimaryKey(i)==null) {
					BuildingFloor floor=new BuildingFloor(i, i+"层", i+" Floor", null);
					this.addBuildingFloor(floor);
				}



			}
			return 1;
		} catch (Exception e) {
			return -1;
		}


	}

}
