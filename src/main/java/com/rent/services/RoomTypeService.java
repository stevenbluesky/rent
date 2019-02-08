package com.rent.services;

import java.util.List;

import com.rent.entity.RoomType;

public interface RoomTypeService {
	
	/**
	 * ����
	 * @return ִ�н��1��-1
	 */
	int addRoomType(RoomType roomType);
	/**
	 * �޸�
	 * @return ִ�н��1��-1
	 */
	int updateRoomType(RoomType roomType);
	
	/**
	 * ɾ��
	 * @return ִ�н��1��-1
	 */
	int delRoomType(Integer id);
	

	/**
	 * ɾ��(��������)
	 * @return ִ�н��1��-1
	 */
	int delRoomType(Integer[] ids);
	/**
	 * ����������ѯ
	 * @return ִ�н��1��-1
	 */
	RoomType findById(Integer id);
	
	/**
	 * ��ѯȫ��
	 * @return ȫ��
	 */
	List<RoomType> findAll();
	
	/**
	 * ��ҳ��ѯȫ��
	 * @param ��ǰҳ
	 * @param ҳ��С
	 * @return �������ͼ���
	 */
	List<RoomType> findAllPaged(Integer currpage,Integer size);
	
	/**
	 * ��ѯ����
	 * @return ����
	 */
	Integer getTotalCount();
	
	/**
	 * �������Ʋ���
	 * @param ����
	 * @return ����
	 */
	RoomType findtByName(String name);
	
	Integer findMaxId();
	
}
