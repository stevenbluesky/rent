package com.rent.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.dao.BuildingMapper;

import com.rent.entity.Building;
import com.rent.entity.BuildingNo;
import com.rent.entity.Estate;
import com.rent.services.BuildingNoService;
import com.rent.services.BuildingService;
import com.rent.services.EstateService;

@Service
@Transactional
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	private BuildingMapper buildingMapper;

	@Autowired 
	private EstateService estateService;
	
	@Autowired
	private BuildingNoService buildingNoService;
	

	public BuildingNoService getBuildingNoService() {
		return buildingNoService;
	}

	public void setBuildingNoService(BuildingNoService buildingNoService) {
		this.buildingNoService = buildingNoService;
	}

	public EstateService getEstateService() {
		return estateService;
	}

	public void setEstateService(EstateService estateService) {
		this.estateService = estateService;
	}


	// 封装导航属性
	private void setGuideProperty(List<Building> buildings) {
		for (Building b : buildings) {
			Estate estate = estateService.findById(b.getEstateId());
			b.setEstate(estate);
		}
	}

	public BuildingMapper getBuildingMapper() {
		return buildingMapper;
	}

	public void setBuildingMapper(BuildingMapper buildingMapper) {

		this.buildingMapper = buildingMapper;
	}

	// 生成id

	private String createId(Integer estateId) {
		
		List<Building> buildings = findAllByEstate(estateId);
		int max=0;
		if (buildings!=null&&buildings.size()!=0) {
			String bId= buildings.get(0).getId();
			int id = Integer.valueOf(bId.substring(bId.indexOf("-") + 1,
					bId.length()));
			max=id;
		}
		
		for (Building b : buildings) {
			String bId = b.getId();
			int id = Integer.valueOf(bId.substring(bId.indexOf("-") + 1,
					bId.length()));
			
			if (id > max) {
				max=id;
			}
		}
		return estateId + "-" + (max + 1);
	}

	/**
	 * 查询全部
	 */
	public List<Building> findAll() {

		return buildingMapper.findAll();
	}

	/**
	 * 新增
	 */
	public int addBuilding(Building building) {
		// 生成id
		building.setId(createId(building.getEstateId()));
		buildingMapper.insert(building);
		
		List<BuildingNo> buildingNos = this.getBuildingNos(building);
		
		for (BuildingNo no : buildingNos) {
			if (no!=null) {
				no.setBuildingId(building.getName());
			    buildingNoService.updateBuildingNo(no);	
			}
				
		}
		return 1;
	}

	private List<BuildingNo> getBuildingNos(Building building){
		List<BuildingNo> buildingNos=new ArrayList<BuildingNo>();
		
		String code = building.getBuildingnocode();
		String[] split = code.split("#");
		for (String str : split) {
			String no = str.substring(str.indexOf("-")+1);
			System.out.println("no:"+no);
			BuildingNo buildingNo = buildingNoService.findByName(building.getEstateId(), no+"单元");
			System.out.println(buildingNo);
			buildingNos.add(buildingNo);
		}
		return buildingNos;
	}
	
	
	/**
	 * 修改
	 */
	public int updateBuilding(Building building) {
	
		buildingMapper.updateByPrimaryKey(building);
		
		List<BuildingNo> buildingNos = this.getBuildingNos(building);
		
			for (BuildingNo no : buildingNos) {
				try {
					no.setBuildingId(building.getName());
				    buildingNoService.updateBuildingNo(no);
				} catch (Exception e) {
					e.printStackTrace();
				}
					
			}
		return 1;
	}

	/**
	 * 根据id查询
	 */
	public Building findById(String id) {
		return buildingMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除
	 */
	public int delBuilding(String id) {
		Building building = this.findById(id);
		List<BuildingNo> buildingNos = this.getBuildingNos(building);
		for (BuildingNo no : buildingNos) {
			if (no!=null) {
				no.setBuildingId(null);
			    buildingNoService.updateBuildingNo(no);	
			}
				
		}
		return buildingMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据物业查询
	 */
	public List<Building> findAllByEstate(Integer estateId) {

		return buildingMapper.findAllByEstate(estateId);
	}

	/**
	 * 分页查询
	 */
	@Override
	public List<Building> findByEstatePaged(Integer estateId, Integer currage,
											Integer size) {
		int begin = (currage - 1) * size;
		int end = begin + size;
		List<Building> buildings = buildingMapper.findByEstatePaged(estateId,
				(currage - 1) * size, end);
			setGuideProperty(buildings) ;
		return buildings;
	}

	/**
	 * 根据物业查询总数
	 */
	public Integer getCountByEstate(Integer estateId) {
		return buildingMapper.getCountByEstate(estateId);
	}

	
	public List<Building> findByName(String name){
	return buildingMapper.findByName(name);
	}

	@Override
	public List<Building> findByEstatePositioned(Integer estateId) {
		return buildingMapper.findByEstatePositioned(estateId);
	}

	@Override
	public List<Building> findByNameAndEstate(String name, Integer estateId) {
		return buildingMapper.findByNameAndEstate(name, estateId);
	}
}
