package com.rent.services;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rent.condition.MasterCondition;
import com.rent.condition.MasterReletCondition;
import com.rent.condition.RenDaliyCondition;
import com.rent.condition.renAcountCondition;
import com.rent.entity.PrhLinkman;
import com.rent.entity.PrhMaster;

public interface PrhMasterService {

	/**
	 * 鏂板
	 * 
	 * @return 杩斿洖鏂板id
	 */
	int addPrhMaster(PrhMaster prhMaster);

	/**
	 * 淇敼
	 * 
	 * @return 鎵ц缁撴灉1鎴�-1
	 */
	int updatePrhMaster(PrhMaster prhMaster);

	/**
	 * 
	 * @param prhMaster
	 * @return
	 */
	int updatePrhMaster(List<PrhMaster> prhMasters);

	/**
	 * 鍒犻櫎
	 * 
	 * @return 鎵ц缁撴灉1鎴�-1
	 */
	int delPrhMaster(Integer id);

	int delPrhMaster(Integer[] ids);

	/**
	 * 鏍规嵁涓婚敭鏌ヨ
	 * 
	 * @return 鎵ц缁撴灉1鎴�-1
	 */
	PrhMaster findById(Integer id);

	/**
	 * 根据id和状态查询 租户id
	 * 
	 * @return 鎵ц缁撴灉1鎴�-1
	 */
	String findMasterIdByStaAndHouseId(String sta, Integer houseId);

	/**
	 * 通过准入编号查找
	 * 
	 * @param allowInNo
	 * @return
	 */
	PrhMaster getByAllowInNo(String allowInNo);

	/**
	 * 通过合同号查找
	 * 
	 * @param allowInNo
	 * @return
	 */
	PrhMaster getByContract(String contract);

	/**
	 * 通过条件查找
	 * 
	 * @param allowInNo
	 * @return
	 */
	List<PrhMaster> findByCondition(MasterCondition condition);

	/**
	 * 根据条件查询条件
	 * 
	 * @param condition
	 * @return
	 */
	int getCountByCondition(MasterCondition condition);

	/**
	 * 通过续租条件查找
	 * 
	 * @param allowInNo
	 * @return
	 */
	List<PrhMaster> findByReletCondition(MasterReletCondition condition);

	/**
	 * 根据条件查询个数
	 * 
	 * @param condition
	 * @return
	 */
	Integer getCountByReletCondition(MasterReletCondition condition);

	/**
	 * 更新当前主单的补贴以及租金
	 * 
	 * @return
	 */
	int updateSubsidyAndRate(int masterId);

	/**
	 * 查询申请续租
	 * 
	 * @return
	 */
	List<PrhMaster> findByReletApplyPaged(MasterReletCondition condition);

	/**
	 * 查询申请续总数
	 * 
	 * @return
	 */
	Integer getCountByReletApplyPaged(MasterReletCondition condition);

	/**
	 * 查询登记记录
	 * 
	 * @return
	 */
	List<PrhMaster> findByCheckInRecordPaged(MasterReletCondition condition);

	/**
	 * 查询登记记录总数
	 * 
	 * @return
	 */
	Integer getCountByCheckInRecordPaged(MasterReletCondition condition);

	/**
	 * 根据组合条件 查询 总条数
	 * 
	 * @return
	 */
	Integer findCountByRenDaliyCondition(RenDaliyCondition condition);

	/**
	 * 根据组合条件 分页查询
	 * 
	 * @return
	 */
	List<PrhMaster> findByRenDaliyConditionAndPaged(RenDaliyCondition condition, Integer currage, Integer size);

	/**
	 * 根据组合条件 分页查询 退组记录
	 * 
	 * @return
	 */
	List<PrhMaster> findByRenDaliyConditionAndPagedTui(RenDaliyCondition condition, Integer currage, Integer size);

	Integer findCountByRenDaliyConditionTui(RenDaliyCondition condition);

	List<PrhMaster> findMasterByrentAccountAndPaged(renAcountCondition condition, Integer currage, Integer size);

	Integer findMasterCountByrentAccount(renAcountCondition condition);

	List<PrhMaster> findByGuestNo(String guestno);

	List<PrhMaster> findByCusNo(String cusNo);

	List<PrhMaster> findAllByConditionAndPaged(MasterCondition condition, Integer currage, Integer size);

	Integer findAllCountByCondition(MasterCondition condition);

	List<PrhMaster> findHistory(String guestno);

	List<PrhMaster> findByHouseId(Integer houseid);

	List<PrhMaster> findArreaseHouse();

	Integer findCountByRenDaliyConditionTai(RenDaliyCondition condition);

	List<PrhMaster> findCountByRenDaliyConditionTaiAndPaged(RenDaliyCondition condition, Integer currpage,
			Integer size);

	List<PrhMaster> findByRenDaliyConditionAndPagedHe(RenDaliyCondition condition, Integer currpage, Integer size);

	Integer findCountByRenDaliyConditionHe(RenDaliyCondition condition);

	boolean checkBeforeRental(PrhMaster master);

	/**
	 * 查询可开卡记录
	 * 
	 * @return
	 */
	List<PrhMaster> findCanGetCardPaged(MasterReletCondition condition);

	/**
	 * 查询可开卡记录总数
	 * 
	 * @return
	 */
	Integer getCanGetCardCount(MasterReletCondition condition);

	Double calSubsidy(Integer estateId, Integer subsidyTypeId, Integer perNum, Double area, Date calDate);

	List<PrhMaster> findMastersByEstate(Integer estateId);

	Integer getLiveInGuestCount(String idno);

	List<PrhMaster> findRentersExportPaged(MasterCondition condition);

	Integer getCountRentersExport(MasterCondition condition);
	
	List<PrhMaster> findRentersExport(MasterCondition condition);
	
	List<String> findAllBatchs(String batch,Integer estateId);
	
	List<PrhMaster> findMastersByBatchs(String batch,Integer estateId);
	
}
