package com.rent.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.dao.HouseSpaceMapper;
import com.rent.entity.HouseSpace;
import com.rent.services.HouseSpaceService;
@Service
@Transactional(readOnly = true)
public class HouseSpaceImpl implements HouseSpaceService{

	@Autowired
	private HouseSpaceMapper houseSpaceMapper;
	
	
	public HouseSpaceMapper getHouseSpaceMapper() {
		return houseSpaceMapper;
	}

	public void setHouseSpaceMapper(HouseSpaceMapper houseSpaceMapper) {
		this.houseSpaceMapper = houseSpaceMapper;
	}

	@Override
	public int addHouseSpace(HouseSpace houseSpace) {
		return houseSpaceMapper.insert(houseSpace);
	}

	@Override
	public int updateHouseSpace(HouseSpace houseSpace) {
		return houseSpaceMapper.updateByPrimaryKey(houseSpace);
	}

	@Override
	public HouseSpace findById(Integer id) {
		return houseSpaceMapper.selectByPrimaryKey(id);
	}

	@Override
	public int delHouseSpace(Integer[] ids) {
		if (ids!=null&&ids.length!=0) {
			for (Integer i : ids) {
				houseSpaceMapper.deleteByPrimaryKey(i);
			}
		}
		return 1;
	}

	@Override
	public int delHouseSpace(Integer id) {
		return houseSpaceMapper.deleteByPrimaryKey(id);
	}

	@Override
	public HouseSpace findLastRecord(Integer houseId) {
		List<HouseSpace> houses = houseSpaceMapper.findLastRecord(houseId);
		if (houses!=null&&houses.size()!=0) {
			return houses.get(0);
		}else{
			return null;
		}
	}
}
