package com.rent.services;

import java.util.List;

import com.rent.entity.BuildingNo;
import com.rent.entity.Estate;

public interface BuildingNoService {

	List<BuildingNo> findAll();

	int addBuildingNo(BuildingNo buildingNo) ;

	int updateBuildingNo(BuildingNo buildingNo) ;

	BuildingNo findById(String id);

	int delBuildingNo(String id);
	
	int delBuildingNo(String[] id);

	List<Estate> getAllEstate();

	List<BuildingNo> findByEstatePaged(Integer estateId,Integer currage,Integer size);

    int getCountByEstate(Integer estateId);

    List<BuildingNo> getAllByRangeEstate(int estate, int begin,int end);

    List<BuildingNo> findByEstate(int estateId);

	BuildingNo findByName(Integer estateId, String name);
	
	List<BuildingNo> findByBuildingIdAndEstate(int buildingId, int estateId);
	
	int clearBuildingIdBy(int buildingId, int estateId);
}
