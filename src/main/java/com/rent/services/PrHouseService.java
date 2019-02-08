package com.rent.services;

import java.util.Date;
import java.util.List;

import com.rent.condition.HouseCondition;
import com.rent.condition.HouseFileContion;
import com.rent.condition.HouseRmdevCondition;
import com.rent.entity.BuildingFloor;
import com.rent.entity.BuildingNo;
import com.rent.entity.Estate;
import com.rent.entity.PrHouse;
import com.rent.entity.PrhMaster;
import com.rent.entity.PrhRental;
import com.rent.entity.RoomType;
import com.rent.entity.Users;

public interface PrHouseService {
	
	/**
	 * 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓瑙ｆ埧(id閿熻璁规嫹閿熸枻鎷峰��,鍞竴绾﹂敓鏂ゆ嫹閿熺獤杈炬嫹閿熸枻鎷�)
	 * @param 閿熸枻鎷烽敓瑙ｆ埧
	 * @return 鎵ч敓鍙枻鎷烽敓锟�
	 */
	int addPrHouse(PrHouse prHouse) ;
	

	/**
	 * 閿熺潾鏀圭櫢鎷烽敓瑙ｆ埧(id閿熻璁规嫹閿熸枻鎷峰��,鍞竴绾﹂敓鏂ゆ嫹閿熺獤杈炬嫹閿熸枻鎷�)
	 * @param 閿熸枻鎷烽敓瑙ｆ埧
	 * @return 鎵ч敓鍙枻鎷烽敓锟�
	 */
	int updatePrHouse(PrHouse prHouse) ;
	
	/**
	 * 閿熸枻鎷烽敓鏂ゆ嫹id閿熸枻鎷疯
	 * @param id
	 * @return 閿熸枻鎷烽敓瑙ｆ埧
	 */
	PrHouse findById(Integer id);
	
	/**
	 * 閿熸枻鎷烽敓鏂ゆ嫹id鍒犻敓鏂ゆ嫹
	 * @param id
	 * @return 閿熸枻鎷烽敓鎴枻鎷烽敓锟�
	 */
	int delPrHouse(Integer id);

	/**
	 * 閿熸枻鎷烽敓鏂ゆ嫹鍒犻敓鏂ゆ嫹
	 * @param id
	 * @return 閿熸枻鎷烽敓鎴枻鎷烽敓锟�
	 */
	int delPrHouse(Integer[] ids);
	
	/**
	 * 閿熸枻鎷峰彇鍏ㄩ敓鏂ゆ嫹閿熸枻鎷蜂笟
	 * @return 閿熸枻鎷蜂笟閿熸枻鎷烽敓鏂ゆ嫹
	 */
	List<Estate> getAllEstate();
	 
	/**
     * 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷蜂笟id閿熸枻鎷烽〉閿熸枻鎷疯鍏ㄩ敓鏂ゆ嫹
     * @return 閿熸枻鎷烽敓瑙ｆ埧閿熸枻鎷烽敓鏂ゆ嫹
     */
	List<PrHouse> findByEstatePaged(Integer estateId,Integer currage,Integer size);
	
	List<PrHouse> findByConditionPaged(HouseFileContion condition,Integer currage,Integer size);
	 /**
     * 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷蜂笟閿熸枻鎷峰彇閿熸枻鎷烽敓鏂ゆ嫹
     * @param 閿熸枻鎷蜂笟id
     * @return 閿熸枻鎷烽敓鏂ゆ嫹
     */
    int getCountByEstate(Integer estateId);
    

  	
    /**
     * 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷蜂笟id閿熸枻鎷疯妤奸敓鏂ゆ嫹閿熷彨鎲嬫嫹
     * @return 妤奸敓鏂ゆ嫹閿熷彨鎲嬫嫹
     */
  	List<BuildingNo> getAllBuildingNo(int estateId);

  	/**
     * 閿熸枻鎷疯妤奸敓鏂ゆ嫹閿熷彨鎲嬫嫹
     * @return 妤奸敓鏂ゆ嫹閿熷彨鎲嬫嫹
     */
  	List<BuildingFloor> getAllBuildingFloor();
  
  	/**
     *  閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熷彨鎲嬫嫹
     * @return 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熷彨鎲嬫嫹
     */
  	List<RoomType> getAllRoomType();
  	/**
  	 * 閿熺獤杈炬嫹excel閿熸枻鎷峰彇閿熸枻鎷烽敓渚ヨ鎷风淮閿熸枻鎷烽敓鏂ゆ嫹杞敓鏂ゆ嫹閿熺即纭锋嫹閿熸枻鎷�
  	 * @param 閿熸枻鎷风淮閿熸枻鎷烽敓鏂ゆ嫹
  	 * @return 閿熸枻鎷烽敓鏂ゆ嫹
  	 */
  	public List<PrHouse> readByExcelStr(String [][] result,Users user);
  	/**
  	 * 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
  	 * @param prHouses
  	 * @return 閿熸枻鎷烽敓锟�
  	 */
  	public int addAuto(List<PrHouse> prHouses);
  	/**
  	 * 閿熸枻鎷烽敓鎹锋唻鎷锋尓閿熺獤锟�
  	 * @return 
  	 */
  	public PrHouse findByNo(String no);
  	
    //閺夆�叉閺屻儴顕�
    List<PrHouse> findByCondition(HouseCondition condition);
    
    List<PrHouse> findByFileCondition(HouseFileContion condition);
	
	 Integer getStateById(int houseId) ;

	List<PrHouse> findByHouseRmdevCondition(HouseRmdevCondition condition);

	 /**
	  * 房源管理首页
	  * @param condition
	  * @return
	  */
	 List<PrHouse> findHouseByConditionPaged(HouseCondition condition);
	 
	 Integer getHouseCountByConditionPaged(HouseCondition condition);
	
	 //统计
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
}
