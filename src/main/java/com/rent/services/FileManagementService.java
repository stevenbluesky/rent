package com.rent.services;

import java.util.List;

import com.rent.condition.FileCondition;
import com.rent.entity.Building;
import com.rent.entity.BuildingFloor;
import com.rent.entity.Profile;

public interface FileManagementService {
//	/**
//	 * 鏌ヨ鍏ㄩ儴鍗曚綅妗ｆ 
//	 * @return 妗ｆ闆嗗悎
//	 */
//	List<Profile> findProfileByClass(String cla);

	/**
	 * 澶氭潯浠舵ā绯婃煡璇㈠叏閮ㄥ崟浣嶆。妗� 
	 * @return 妗ｆ闆嗗悎
	 */	
	List<Profile> findProfileByCondition(String cla,String sta,String name);
	 List<Profile> findProfileByCondition1(FileCondition condition);
	/**
	 * 澶氭潯浠舵ā绯婃煡璇㈠叏閮ㄥ崟浣嶆。妗� 
	 * @return 妗ｆ闆嗗悎
	 */	
	List<Profile> findProfileByConditionAndPage(String cla,String sta,String name,Integer currage,Integer size);
	    
	List<Profile> findProfileByConditionAndPage1(FileCondition condition,Integer currage,Integer size);
	/**
	 * 鏂板涓�鏉″崟浣嶆。妗堢鐞�
	 * @return 鏄惁鎴愬姛
	 */	    
	Boolean saveProFile(Profile record);
	
	/**
	 * 
	 * 淇敼鍗曚綅妗ｆ绠＄悊
	 * @return 鏄惁鎴愬姛
	 */	
	Boolean updateProFile(Profile record);
	
	/**
	 * 
	 * 鍒犻櫎鍗曚綅妗ｆ绠＄悊
	 * @return 鏄惁鎴愬姛
	 */	
	Boolean deleteProFile(String guestno);
	
	/**
	 * 鏍规嵁鐪佷唤璇佹煡璇�
	 * @param cardId
	 * @return
	 */
	List<Profile> findByCardId(String cardId);
	
	/**
	 * 根据id
	 * @param id
	 * @return 
	 */
	Profile findById(String id);
	/**
	 * 根据id个状态 查询 档案的 name
	 * @param guestno	 
	 * @return name
	 */
	String  findName(String guestno);
	/**
	 * 查询所有公司
	 * @return 
	 */
	List<Profile> findAllCompanyByEstate(Integer estateId);
	String findNameByHouseId(Integer houseid);
	List<Profile> findProfileByCondition2(FileCondition condition);
	List<Profile> findProfileByConditionAndPage2(FileCondition condition, Integer currpage, Integer size);
	
	//通过物业查询公司（分页）
	List<Profile> findCompanyByEstatePaged(Integer estateId,String company, Integer currpage,Integer size);
	
	// 通过物业查询公司总数
	Integer findCompanyByEstateCount(Integer estateId,String company);
}
