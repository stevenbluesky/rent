package com.rent.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.services.RentPayWayService;

import com.rent.dao.RentPayWayMapper;

import com.rent.entity.RentPayWay;
@Service
@Transactional(readOnly = true)
public class RentPayWayServiceImpl implements RentPayWayService{
	@Autowired
	private RentPayWayMapper rentPayWayMapper;
	
	public RentPayWayMapper getRentPayWayMapper() {
		return rentPayWayMapper;
	}
	public void setRentPayWayMapper(RentPayWayMapper rentPayWayMapper) {
		this.rentPayWayMapper = rentPayWayMapper;
	}
	/**
	 * 锟斤拷锟斤拷
	 * @return 执锟叫斤拷锟�1锟斤拷-1
	 */
	public int addRentPayWay(RentPayWay rentPayWay){
		return rentPayWayMapper.insert(rentPayWay);
	}
	/**
	 * 锟睫革拷
	 * @return 执锟叫斤拷锟�1锟斤拷-1
	 */
	public int updateRentPayWay(RentPayWay rentPayWay){
		return rentPayWayMapper.updateByPrimaryKey(rentPayWay);
	}
	
	/**
	 * 删锟斤拷
	 * @return 执锟叫斤拷锟�1锟斤拷-1
	 */
	public int delRentPayWay(Integer id){
		return rentPayWayMapper.deleteByPrimaryKey(id);
	}
	

	/**
	 * 删锟斤拷(锟斤拷锟斤拷锟斤拷锟斤拷)
	 * @return 执锟叫斤拷锟�1锟斤拷-1
	 */
	public int delRentPayWay(Integer[] ids){
		int result=0;
		if (ids.length!=0) {
			for (Integer id : ids) {
				result= rentPayWayMapper.deleteByPrimaryKey(id);
				if (result!=1) {
					return -1;
				}
			}
		}
		return 1;
	}
	/**
	 * 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷询
	 * @return 执锟叫斤拷锟�1锟斤拷-1
	 */
	public RentPayWay findById(Integer id){
		return rentPayWayMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 锟斤拷页锟斤拷询全锟斤拷
	 * @param 锟斤拷前页
	 * @param 页锟斤拷小
	 * @return 锟斤拷锟斤拷锟斤拷锟酵硷拷锟斤拷
	 */
	public List<RentPayWay> findAllPaged(Integer currpage,Integer size){
		Integer begin=(currpage-1)*size+1;
		Integer end=begin+size-1;
		return rentPayWayMapper.findAllPaged(begin, end);
	}
	
	/**
	 * 锟斤拷询锟斤拷锟斤拷
	 * @return 锟斤拷锟斤拷
	 */
	public Integer getTotalCount(){
		return rentPayWayMapper.getTotalCount();
	}
	
	@Override
	public List<RentPayWay> findAll() {
		return rentPayWayMapper.findAll();
	}
}
