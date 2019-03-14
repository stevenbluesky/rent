package com.rent.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.services.ElectricService;

import com.rent.dao.ElectricMapper;

import com.rent.entity.Electric;
@Service
@Transactional
public class ElectricServiceImpl implements ElectricService{

	@Autowired
	private ElectricMapper electricMapper;


	public ElectricMapper getElectricMapper() {
		return electricMapper;
	}
	public void setElectricMapper(ElectricMapper electricMapper) {
		this.electricMapper = electricMapper;
	}
	/**
	 * 新增
	 * @return 执行结果1或-1
	 */
	public int addElectric(Electric electric){
		return electricMapper.insert(electric);
	}
	/**
	 * 修改
	 * @return 执行结果1或-1
	 */
	public int updateElectric(Electric electric){
		return electricMapper.updateByPrimaryKey(electric);
	}

	/**
	 * 删除
	 * @return 执行结果1或-1
	 */
	public int delElectric(Integer id){
		return electricMapper.deleteByPrimaryKey(id);
	}


	/**
	 * 删除(根据数组)
	 * @return 执行结果1或-1
	 */
	public int delElectric(Integer[] ids){
		int result=0;
		if (ids.length!=0) {
			for (Integer id : ids) {
				result= electricMapper.deleteByPrimaryKey(id);
				if (result!=1) {
					return -1;
				}
			}
		}
		return 1;
	}
	/**
	 * 根据主键查询
	 * @return 执行结果1或-1
	 */
	public Electric findById(Integer id){
		return electricMapper.selectByPrimaryKey(id);
	}

	/**
	 * 分页查询全部
	 * @param currpage
	 * @param size
	 * @return 房屋类型集合
	 */
	public List<Electric> findAllPaged(Integer currpage,Integer size){
		Integer begin=(currpage-1)*size;
		Integer end=begin+size;
		return electricMapper.findAllPaged(begin, end);
	}

	/**
	 * 查询总数
	 * @return 总数
	 */
	public Integer getTotalCount(){
		return electricMapper.getTotalCount();
	}
}
