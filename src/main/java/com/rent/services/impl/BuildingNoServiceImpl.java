package com.rent.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.dao.BuildingNoMapper;
import com.rent.dao.EstateMapper;

import com.rent.entity.BuildingNo;
import com.rent.entity.Estate;
import com.rent.entity.EstateType;
import com.rent.services.BuildingNoService;
import com.rent.services.BuildingService;
import com.rent.services.EstateService;

@Service
@Transactional
public class BuildingNoServiceImpl implements BuildingNoService {

	@Autowired
	private BuildingNoMapper buildingNoMapper;
	@Autowired
	private EstateService estateService;

	@Autowired
	private BuildingService buildingService;

	public EstateService getEstateService() {
		return estateService;
	}

	public void setEstateService(EstateService estateService) {
		this.estateService = estateService;
	}

	public BuildingNoMapper getBuildingNoMapper() {
		return buildingNoMapper;
	}

	public void setBuildingNoMapper(BuildingNoMapper buildingNoMapper) {
		this.buildingNoMapper = buildingNoMapper;
	}

	// 封装导航属性
	private void setGuideProperty(List<BuildingNo> buildingNos) {
		for (BuildingNo b : buildingNos) {
			Estate estate = estateService.findById(b.getEstateId());
			if (b.getBuildingId()!=null) {

			}
			b.setEstate(estate);
		}
	}

	// 封装导航属性
	private void setGuideProperty(BuildingNo b) {

		Estate estate = estateService.findById(b.getEstateId());
		b.setEstate(estate);

	}

	/**
	 * 查询全部
	 */
	public List<BuildingNo> findAll() {
		List<BuildingNo> buildingNos = buildingNoMapper.findAll();
		setGuideProperty(buildingNos);
		return buildingNos;
	}

	/**
	 * 新增
	 */

	public int addBuildingNo(BuildingNo buildingNo) {
		BuildingNo findById = this.findById(buildingNo.getId());
		if (findById != null) {
			return -1;
		} else {
			return buildingNoMapper.insert(buildingNo);
		}
	}

	/**
	 * 修改
	 */
	public int updateBuildingNo(BuildingNo buildingNo) {
		return buildingNoMapper.updateByPrimaryKey(buildingNo);
	}

	/**
	 * 通过id查找
	 */

	public BuildingNo findById(String id) {
		return buildingNoMapper.selectByPrimaryKey(id);

	}

	/**
	 * 删除
	 */
	public int delBuildingNo(String id) {

		return buildingNoMapper.deleteByPrimaryKey(id);
	}


	/**
	 * 删除
	 */
	public int delBuildingNo(String[] id) {

		for (String string : id) {
			buildingNoMapper.deleteByPrimaryKey(string);
		}
		return 1;

	}


	/**
	 * 物业集合
	 */
	public List<Estate> getAllEstate() {
		return estateService.findAll();
	}

	/**
	 *
	 * 根据物业分页查询
	 */
	public List<BuildingNo> findByEstatePaged(Integer estateId,
											  Integer currage, Integer size) {

		Integer begin = (currage - 1) * size;
		Integer end = begin + size ;

		List<BuildingNo> buildingNos = buildingNoMapper.findByEstatePaged(
				estateId, begin, end);
		setGuideProperty(buildingNos);
		return buildingNos;
	}

	/**
	 * 根据物业获取总条数
	 */
	public int getCountByEstate(Integer estateId) {
		return buildingNoMapper.getCountByEstate(estateId);
	}

	public List<BuildingNo> getAllByRangeEstate(int estate, int begin, int end) {
		return buildingNoMapper.getAllByRangeEstate(estate, begin, end);
	}

	/**
	 * 根据id查询楼号集合
	 */
	public List<BuildingNo> findByEstate(int estate) {

		return buildingNoMapper.findByEstateId(estate);
	}

	// 根据名称查找
	public BuildingNo findByName(Integer estateId, String name) {
		BuildingNo buildingNo = buildingNoMapper.findByName(estateId.toString(), name);
		if (buildingNo!=null) {
			setGuideProperty(buildingNo);
		}
		return buildingNo;
	}

	@Override
	public List<BuildingNo> findByBuildingIdAndEstate(int buildingId, int estateId) {
		return buildingNoMapper.findByBuildingIdAndEstate(buildingId, estateId);
	}

	@Override
	public int clearBuildingIdBy(int buildingId, int estateId) {
		return buildingNoMapper.clearBuildingIdBy(buildingId, estateId);
	}

}
