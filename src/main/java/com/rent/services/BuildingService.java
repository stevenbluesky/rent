package com.rent.services;

import java.util.List;

import com.rent.entity.Building;
import com.rent.entity.BuildingNo;
import com.rent.entity.Estate;

public interface BuildingService {
	/**
	 * ��ѯȫ��
	 * @return ¥������
	 */
	List<Building> findAll();
	
	/**
	 * ����¥��(id�ֶ���ֵ,ΨһԼ���Ѵ���)
	 * @param ¥��
	 * @return ִ�н��
	 */
	int addBuilding(Building building) ;
	

	/**
	 * �޸�¥��(id�ֶ���ֵ,ΨһԼ���Ѵ���)
	 * @param ¥��
	 * @return ִ�н��
	 */
	int updateBuilding(Building building) ;
	
	/**
	 * ����id��ѯ
	 * @param id
	 * @return ¥��
	 */
	Building findById(String id);
	
	
	
	int delBuilding(String id);
	
	
	 /**
     * 
     * @param ��ҵid
     * @return ¥������
     */

   List<Building> findAllByEstate(Integer estateId);
	/**
	 * ����idɾ��
	 * @param id
	 * @return ���ؽ��
	 */
	 
	/**
     * ������ҵid��ҳ��ѯȫ��
     * @return ¥������
     */
	List<Building> findByEstatePaged(Integer estateId,Integer currage,Integer size);
	 /**
     * ������ҵ��ȡ����
     * @param ��ҵid
     * @return ����
     */
    Integer getCountByEstate(Integer estateId);
    
    List<Building> findByName(String name);
    
    List<Building> findByEstatePositioned(Integer estateId);
    
    List<Building> findByNameAndEstate(String name,Integer estateId);
}
