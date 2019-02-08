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
@Transactional(readOnly = true)
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

	// ��װ��������
	private void setGuideProperty(List<BuildingNo> buildingNos) {
		for (BuildingNo b : buildingNos) {
			Estate estate = estateService.findById(b.getEstateId());
			if (b.getBuildingId()!=null) {
				
			}
			b.setEstate(estate);
		}
	}

	// ��װ��������
	private void setGuideProperty(BuildingNo b) {

		Estate estate = estateService.findById(b.getEstateId());
		b.setEstate(estate);

	}

	/**
	 * ��ѯȫ��
	 */
	public List<BuildingNo> findAll() {
		List<BuildingNo> buildingNos = buildingNoMapper.findAll();
		setGuideProperty(buildingNos);
		return buildingNos;
	}

	/**
	 * ����
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
	 * �޸�
	 */
	public int updateBuildingNo(BuildingNo buildingNo) {
		return buildingNoMapper.updateByPrimaryKey(buildingNo);
	}

	/**
	 * ͨ��id����
	 */

	public BuildingNo findById(String id) {
		return buildingNoMapper.selectByPrimaryKey(id);

	}

	/**
	 * ɾ��
	 */
	public int delBuildingNo(String id) {

		return buildingNoMapper.deleteByPrimaryKey(id);
	}


	/**
	 * ɾ��
	 */
	public int delBuildingNo(String[] id) {
		
		for (String string : id) {
			buildingNoMapper.deleteByPrimaryKey(string);	
		}
		return 1;
		
	}

	
	/**
	 * ��ҵ����
	 */
	public List<Estate> getAllEstate() {
		return estateService.findAll();
	}

	/**
	 * 
	 * ������ҵ��ҳ��ѯ
	 */
	public List<BuildingNo> findByEstatePaged(Integer estateId,
			Integer currage, Integer size) {

		Integer begin = (currage - 1) * size + 1;
		Integer end = begin + size - 1;

		List<BuildingNo> buildingNos = buildingNoMapper.findByEstatePaged(
				estateId, begin, end);
		setGuideProperty(buildingNos);
		return buildingNos;
	}

	/**
	 * ������ҵ��ȡ������
	 */
	public int getCountByEstate(Integer estateId) {
		return buildingNoMapper.getCountByEstate(estateId);
	}

	public List<BuildingNo> getAllByRangeEstate(int estate, int begin, int end) {
		return buildingNoMapper.getAllByRangeEstate(estate, begin, end);
	}

	/**
	 * ����id��ѯ¥�ż���
	 */
	public List<BuildingNo> findByEstate(int estate) {

		return buildingNoMapper.findByEstateId(estate);
	}

	// �������Ʋ���
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
