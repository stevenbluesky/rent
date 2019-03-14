package com.rent.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.services.RepaireReasonService;

import com.rent.dao.RepaireReasonMapper;

import com.rent.entity.RepaireReason;

@Service
@Transactional
public class RepaireReasonServiceImpl implements RepaireReasonService{
	@Autowired
	private RepaireReasonMapper repaireReasonMapper;


	public RepaireReasonMapper getRepaireReasonMapper() {
		return repaireReasonMapper;
	}
	public void setRepaireReasonMapper(RepaireReasonMapper repaireReasonMapper) {
		this.repaireReasonMapper = repaireReasonMapper;
	}
	/**
	 * 新增
	 * @return 执行结果1或-1
	 */
	public int addRepaireReason(RepaireReason repaireReason){
		return repaireReasonMapper.insert(repaireReason);
	}
	/**
	 * 修改
	 * @return 执行结果1或-1
	 */
	public int updateRepaireReason(RepaireReason repaireReason){
		return repaireReasonMapper.updateByPrimaryKey(repaireReason);
	}

	/**
	 * 删除
	 * @return 执行结果1或-1
	 */
	public int delRepaireReason(Integer id){
		return repaireReasonMapper.deleteByPrimaryKey(id);
	}


	/**
	 * 删除(根据数组)
	 * @return 执行结果1或-1
	 */
	public int delRepaireReason(Integer[] ids){
		int result=0;
		if (ids.length!=0) {
			for (Integer id : ids) {
				result= repaireReasonMapper.deleteByPrimaryKey(id);
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
	public RepaireReason findById(Integer id){
		return repaireReasonMapper.selectByPrimaryKey(id);
	}

	/**
	 * 分页查询全部
	 * @param currpage
	 * @param size
	 * @return 房屋类型集合
	 */
	public List<RepaireReason> findAllPaged(Integer currpage,Integer size){
		Integer begin=(currpage-1)*size;
		Integer end=begin+size;
		return repaireReasonMapper.findAllPaged(begin, end);
	}

	/**
	 * 查询总数
	 * @return 总数
	 */
	public Integer getTotalCount(){
		return repaireReasonMapper.getTotalCount();
	}
}
