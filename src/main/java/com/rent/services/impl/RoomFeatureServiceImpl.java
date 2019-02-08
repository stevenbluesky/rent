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
	 * ����
	 */
	public int addRoomFeature(RoomFeature roomFeature) {
		
		return roomFeatureMapper.insert(roomFeature);
	}
	/**
	 * �޸�
	 */
	public int updateRoomFeature(RoomFeature roomFeature) {
		
		return roomFeatureMapper.updateByPrimaryKey(roomFeature);
	}
	/**
	 * ����idɾ��
	 */
	public int delRoomFeature(Integer id) {
		
		return roomFeatureMapper.deleteByPrimaryKey(id);
	}
	/**
	 * ��������ɾ��
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
	 * ����id��ѯ��������
	 */
	public RoomFeature findById(Integer id) {
		
		return roomFeatureMapper.selectByPrimaryKey(id);
	}
	/**
	 * ��ҳ��ѯ
	 */
	public List<RoomFeature> findAllPaged(Integer currpage, Integer size) {
	
		Integer begin=(currpage-1)*size+1;
		Integer end=begin+size-1;
		return roomFeatureMapper.findAllPaged(begin, end);
	}
	
	/**
	 * ��ѯ����
	 */

	public Integer getTotalCount(){
		return roomFeatureMapper.getTotalCount();
	}
}
