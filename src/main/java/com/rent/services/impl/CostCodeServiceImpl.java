package com.rent.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.services.CostCodeService;

import com.sun.org.apache.regexp.internal.recompile;

import com.rent.dao.CostCodeMapper;

import com.rent.entity.CostCode;
import com.rent.entity.RoomFeature;

@Service
@Transactional(readOnly = true)
public class CostCodeServiceImpl implements CostCodeService{

	@Autowired
	private CostCodeMapper costCodeMapper;
	public CostCodeMapper getCostCodeMapper() {
		return costCodeMapper;
	}
	public void setCostCodeMapper(CostCodeMapper costCodeMapper) {
		this.costCodeMapper = costCodeMapper;
	}
	/**
	 * 新增
	 * @return 执行结果1或-1
	 */
	public int addCostCode(CostCode costCode){
		return costCodeMapper.insert(costCode);
	}
	/**
	 * 修改
	 * @return 执行结果1或-1
	 */
	public int updateCostCode(CostCode costCode){
		return costCodeMapper.updateByPrimaryKey(costCode);
	}

	/**
	 * 删除
	 * @return 执行结果1或-1
	 */
	public int delCostCode(Integer id){
		return costCodeMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 删除(根据数组)
	 * @return 执行结果1或-1
	 */
	public int delCostCode(Integer[] ids){
		int result=0;
		if (ids.length!=0) {
			for (Integer id : ids) {
				result= costCodeMapper.deleteByPrimaryKey(id);
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
	public CostCode findById(Integer id){
		return costCodeMapper.selectByPrimaryKey(id);
	}

	/**
	 * 分页查询全部
	 * @param currpage
	 * @param size
	 * @return 房屋类型集合
	 */
	public List<CostCode> findAllPaged(Integer currpage,Integer size){
		Integer begin=(currpage-1)*size+1;
		Integer end=begin+size-1;
		return costCodeMapper.findAllPaged(begin, end);
	}

	/**
	 * 查询总数
	 * @return 总数
	 */
	public Integer getTotalCount(){
		return costCodeMapper.getTotalCount();
	}
}
