package com.rent.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.dao.RoomTypeMapper;

import com.rent.entity.RoomType;
import com.rent.services.RoomTypeService;
@Service
@Transactional(readOnly = true)
public class RoomTypeServiceImpl implements RoomTypeService{

	@Autowired
	private RoomTypeMapper roomTypeMapper;
	
	
	public RoomTypeMapper getRoomTypeMapper() {
		return roomTypeMapper;
	}
	public void setRoomTypeMapper(RoomTypeMapper roomTypeMapper) {
		this.roomTypeMapper = roomTypeMapper;
	}
	
	/**
	 * ����
	 */
	public int addRoomType(RoomType roomType) {
		
		return roomTypeMapper.insert(roomType);
	}
	/**
	 * �޸�
	 */
	public int updateRoomType(RoomType roomType) {
		
		return roomTypeMapper.updateByPrimaryKey(roomType);
	}
	/**
	 * ����idɾ��
	 */
	public int delRoomType(Integer id) {
		
		return roomTypeMapper.deleteByPrimaryKey(id);
	}
	/**
	 * ��������ɾ��
	 */
	public int delRoomType(Integer[] ids) {
		int result=0;
		if (ids.length!=0) {
			for (Integer id : ids) {
				result= roomTypeMapper.deleteByPrimaryKey(id);
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
	public RoomType findById(Integer id) {
		
		return roomTypeMapper.selectByPrimaryKey(id);
	}

	/**
	 * ��ҳ��ѯ
	 */
	public List<RoomType> findAllPaged(Integer currpage, Integer size) {
	
		Integer begin=(currpage-1)*size+1;
		Integer end=begin+size-1;
		return roomTypeMapper.findAllByPaged(begin, end);
	}
	
	/**
	 * ��ѯ����
	 */
	
	public Integer getTotalCount(){
		return roomTypeMapper.getTotalCount();
	}
	/**
	 * ��ѯȫ��
	 */
	public List<RoomType> findAll() {
		return roomTypeMapper.findAll();
	}
	
	public RoomType findtByName(String name) {
		return roomTypeMapper.findByName(name);
	}
	@Override
	public Integer findMaxId() {
		
		return roomTypeMapper.findMaxId();
	}

}
