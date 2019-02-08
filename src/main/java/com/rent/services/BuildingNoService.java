package com.rent.services;

import java.util.List;

import com.rent.entity.BuildingNo;
import com.rent.entity.Estate;

public interface BuildingNoService {
	/**
	 * ��ѯȫ��
	 * @return ¥�ż���
	 */
	List<BuildingNo> findAll();
	
	/**
	 * ����¥��(id�ֶ���ֵ,ΨһԼ���Ѵ���)
	 * @param ¥��
	 * @return ִ�н��
	 */
	int addBuildingNo(BuildingNo buildingNo) ;
	

	/**
	 * �޸�¥��(id�ֶ���ֵ,ΨһԼ���Ѵ���)
	 * @param ¥��
	 * @return ִ�н��
	 */
	int updateBuildingNo(BuildingNo buildingNo) ;
	
	/**
	 * ����id��ѯ
	 * @param id
	 * @return ¥��
	 */
	BuildingNo findById(String id);
	
	/**
	 * ����idɾ��
	 * @param id
	 * @return ���ؽ��
	 */
	int delBuildingNo(String id);
	
	int delBuildingNo(String[] id);
	
	/**
	 * ��ȡȫ����ҵ
	 * @return ��ҵ����
	 */
	List<Estate> getAllEstate();
	 
	/**
     * ������ҵid��ҳ��ѯȫ��
     * @return ¥�ż���
     */
	List<BuildingNo> findByEstatePaged(Integer estateId,Integer currage,Integer size);
	
	 /**
     * ������ҵ��ȡ����
     * @param ��ҵid
     * @return ����
     */
    int getCountByEstate(Integer estateId);
    
    /**
     * ������ҵ��¥���������¥��
     * @param ��ҵ���
     * @param ��ʼ¥��
     * @param ����¥��
     * @return ¥�ż���
     */
    List<BuildingNo> getAllByRangeEstate(int estate, int begin,int end);
    
    /**
     * ������ҵid��ѯ ¥�ż���
     * @param id
     * @return ¥�ż���
     */
    List<BuildingNo> findByEstate(int estateId);
    
    /**
	 * �������Ʋ���
	 * @param ��ҵ���
	 * @param ����
	 * @return ����
	 */
	BuildingNo findByName(Integer estateId, String name);
	
	List<BuildingNo> findByBuildingIdAndEstate(int buildingId, int estateId);
	
	int clearBuildingIdBy(int buildingId, int estateId);
}
