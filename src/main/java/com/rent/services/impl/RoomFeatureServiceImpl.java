package com.rent.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.dao.RoomFeatureMapper;

import com.rent.entity.RoomFeature;
import com.rent.services.RoomFeatureService;
@Service
@Transactional(readOnly = true)
public class RoomFeatureServiceImpl implements RoomFeatureService{

	@Autowired
	private RoomFeatureMapper roomFeatureMapper;
	public RoomFeatureMapper getRoomFeatureMapper() {
		return roomFeatureMapper;
	}
	public void setRoomFeatureMapper(RoomFeatureMapper roomFeatureMapper) {
		this.roomFeatureMapper = roomFeatureMapper;
	}
	
	/**
	 * 新增
	 */
	public int addRoomFeature(RoomFeature roomFeature) {
		
		return roomFeatureMapper.insert(roomFeature);
	}
	/**
	 * 修改
	 */
	public int updateRoomFeature(RoomFeature roomFeature) {
		
		return roomFeatureMapper.updateByPrimaryKey(roomFeature);
	}
	/**
	 * 根据id删除
	 */
	public int delRoomFeature(Integer id) {
		
		return roomFeatureMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 根据数组删除
	 */
	public int delRoomFeature(Integer[] ids) {
		int result=0;
		if (ids.length!=0) {
			for (Integer id : ids) {
				result= roomFeatureMapper.deleteByPrimaryKey(id);
			}
			if (result!=1) {
				return -1;
			}
		}
		return 1;
	}

	/**
	 * 根据id查询房屋类型
	 */
	public RoomFeature findById(Integer id) {
		
		return roomFeatureMapper.selectByPrimaryKey(id);
	}
	/**
	 * 分页查询
	 */
	public List<RoomFeature> findAllPaged(Integer currpage, Integer size) {
	
		Integer begin=(currpage-1)*size+1;
		Integer end=begin+size-1;
		return roomFeatureMapper.findAllPaged(begin, end);
	}
	
	/**
	 * 查询总数
	 */

	public Integer getTotalCount(){
		return roomFeatureMapper.getTotalCount();
	}
}
