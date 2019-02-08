package com.rent.services;

import java.text.ParseException;
import java.util.List;

import com.rent.condition.RenDaliyCondition;
import com.rent.condition.RenterCondition;
import com.rent.entity.PrhRental;

public interface RenterService {

	public  boolean updateHousePrice(Integer[] chk,String[] caname,String reason,String decDate);
	
	
	 Integer findCountByCondition(RenterCondition rental);
	
	 List<PrhRental>  findByConditionAndPaged(RenterCondition rental,Integer currpage,Integer size);
	
	 void createRentals(int masterId) throws ParseException;
	
	 PrhRental  findById(Integer id);
	 
	  List<PrhRental>  findByAccnt(Integer accnt);
	  
	  //修改rental sta 状态
	  Integer payMoneyAndUpdateAndInsert(Integer[] chk,String ino,String pay,Integer payType,String batch) ;
	  //根据账务单id 修改账务 状态
	  Integer  updateById(Integer id);
	  	
	  Integer updateandinsert(Integer[] chk);

	  
	  Integer delByMaster(int master);
	  
	  List<PrhRental>	findByMasterId(Integer masterID);


	 Integer accountRental(Integer[] chk2,Integer userId);
	 
	 


	 Integer checkRefer(Integer accntid2);


	 List<PrhRental> findArrears();


	 Integer findCountByExpiringCondition(RenDaliyCondition condition);


	 List<PrhRental> findCountByExpiringConditionAndPaged(RenDaliyCondition condition, Integer currpage,
			Integer size);


	 boolean updateTui(List<PrhRental> rentals);
	 
	 
}
