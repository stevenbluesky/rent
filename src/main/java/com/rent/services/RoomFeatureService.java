package com.rent.services;

import java.util.List;

import com.rent.entity.RoomFeature;

public interface RoomFeatureService {

	/**
	 * ����
	 * @return ִ�н��1��-1
	 */
	int addRoomFeature(RoomFeature roomFeature);
	/**
	 * �޸�
	 * @return ִ�н��1��-1
	 */
	int updateRoomFeature(RoomFeature roomFeature);
	
	/**
	 * ɾ��
	 * @return ִ�н��1��-1
	 */
	int delRoomFeature(Integer id);
	

	/**
	 * ɾ��(��������)
	 * @return ִ�н��1��-1
	 */
	int delRoomFeature(Integer[] ids);
	/**
	 * ����������ѯ
	 * @return ִ�н��1��-1
	 */
	RoomFeature findById(Integer id);
	
	/**
	 * ��ҳ��ѯȫ��
	 * @param ��ǰҳ
	 * @param ҳ��С
	 * @return ������������
	 */
	List<RoomFeature> findAllPaged(Integer currpage,Integer size);
	

	/**
	 * ��ѯ����
	 * @return ����
	 */
	Integer getTotalCount();
}
