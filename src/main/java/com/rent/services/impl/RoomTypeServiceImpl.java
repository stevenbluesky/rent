package com.rent.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.dao.RoomTypeMapper;

import com.rent.entity.RoomType;
import com.rent.services.RoomTypeService;
@Service
@Transactional
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
	 * 新增
	 */
	public int addRoomType(RoomType roomType) {

		return roomTypeMapper.insert(roomType);
	}
	/**
	 * 修改
	 */
	public int updateRoomType(RoomType roomType) {

		return roomTypeMapper.updateByPrimaryKey(roomType);
	}
	/**
	 * 根据id删除
	 */
	public int delRoomType(Integer id) {

		return roomTypeMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 根据数组删除
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
	 * 根据id查询房屋类型
	 */
	public RoomType findById(Integer id) {

		return roomTypeMapper.selectByPrimaryKey(id);
	}

	/**
	 * 分页查询
	 */
	public List<RoomType> findAllPaged(Integer currpage, Integer size) {

		Integer begin=(currpage-1)*size;
		Integer end=begin+size;
		return roomTypeMapper.findAllByPaged(begin, end);
	}

	/**
	 * 查询总数
	 */

	public Integer getTotalCount(){
		return roomTypeMapper.getTotalCount();
	}
	/**
	 * 查询全部
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
