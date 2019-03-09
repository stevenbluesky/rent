package com.rent.services;

import java.util.Date;
import java.util.List;

import com.rent.condition.HouseCondition;
import com.rent.condition.HouseFileContion;
import com.rent.condition.HouseRmdevCondition;
import com.rent.door.HouseInfo;
import com.rent.entity.*;

public interface PrHouseService {

	int addPrHouse(PrHouse prHouse) ;

	int updatePrHouse(PrHouse prHouse) ;

	PrHouse findById(Integer id);

	int delPrHouse(Integer id);

	int delPrHouse(Integer[] ids);

	List<Estate> getAllEstate();

	List<PrHouse> findByEstatePaged(Integer estateId,Integer currage,Integer size);
	
	List<PrHouse> findByConditionPaged(HouseFileContion condition,Integer currage,Integer size);

    int getCountByEstate(Integer estateId);

  	List<BuildingNo> getAllBuildingNo(int estateId);

  	List<BuildingFloor> getAllBuildingFloor();

  	List<RoomType> getAllRoomType();

  	public List<PrHouse> readByExcelStr(String [][] result,Users user);

  	public int addAuto(List<PrHouse> prHouses);

  	public PrHouse findByNo(String no);

    List<PrHouse> findByCondition(HouseCondition condition);
    
    List<PrHouse> findByFileCondition(HouseFileContion condition);
	
	 Integer getStateById(int houseId) ;

	List<PrHouse> findByHouseRmdevCondition(HouseRmdevCondition condition);

	 List<PrHouse> findHouseByConditionPaged(HouseCondition condition);
	 
	 Integer getHouseCountByConditionPaged(HouseCondition condition);

	 Integer getCountByEach(Integer state, Integer estateId,String buildingNoId,String buildingId);
	 List<PrHouse> findByFileConditionAndPaged(HouseFileContion condition,Integer currage,Integer size);


	boolean plan(List<PrHouse> houses, Double rentMod, String reason, String decMan, Date decDate);


	Integer doPlan();


	boolean ExplainPlan(List<PrhRental> rentals);


	Integer findCountByFileCondition(HouseFileContion condition);


	List<PrHouse> findByFileCondition2(HouseFileContion condition);


	List<PrHouse> findByFileCondition2Paged(HouseFileContion condition, Integer currpage, Integer size);
	
	void test();
	
	List<PrHouse> findByBuildingNo(String buildingNoId);

	PrHouse findByLock(String associatedlock);

	List<String> findAllLock();

	HouseInfo findRoomInfoById(Integer houseid);
}
