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
	 * ����
	 * @return ִ�н��1��-1
	 */
	public int addCostCode(CostCode costCode){
		return costCodeMapper.insert(costCode);
	}
	/**
	 * �޸�
	 * @return ִ�н��1��-1
	 */
	public int updateCostCode(CostCode costCode){
		return costCodeMapper.updateByPrimaryKey(costCode);
	}
	
	/**
	 * ɾ��
	 * @return ִ�н��1��-1
	 */
	public int delCostCode(Integer id){
		return costCodeMapper.deleteByPrimaryKey(id);
	}
	/**
	 * ɾ��(��������)
	 * @return ִ�н��1��-1
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
	 * ����������ѯ
	 * @return ִ�н��1��-1
	 */
	public CostCode findById(Integer id){
		return costCodeMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * ��ҳ��ѯȫ��
	 * @param ��ǰҳ
	 * @param ҳ��С
	 * @return �������ͼ���
	 */
	public List<CostCode> findAllPaged(Integer currpage,Integer size){
		Integer begin=(currpage-1)*size+1;
		Integer end=begin+size-1;
		return costCodeMapper.findAllPaged(begin, end);
	}
	
	/**
	 * ��ѯ����
	 * @return ����
	 */
	public Integer getTotalCount(){
		return costCodeMapper.getTotalCount();
	}
}
