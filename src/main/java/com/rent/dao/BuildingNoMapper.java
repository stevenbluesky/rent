package com.rent.dao;

import java.util.List;
import com.rent.common.persistence.annotation.MyBatisDao;
import com.rent.entity.BuildingNo;

@MyBatisDao
public interface BuildingNoMapper {
  
    int deleteByPrimaryKey(String id);

  
    int insert(BuildingNo record);

    
    int insertSelective(BuildingNo record);

    BuildingNo selectByPrimaryKey(String id);

   
    int updateByPrimaryKeySelective(BuildingNo record);

   
    int updateByPrimaryKey(BuildingNo record);
    
    /**
     * ��ѯȫ��
     * @return ¥�ż���
     */
    List<BuildingNo> findAll();
  
    /**
     * ͨ����ҵ��ѯ¥�ż���
     * @return ¥�ż���
     */
    List<BuildingNo> findByEstateId(int estateId);
    
    /**
     * ������ҵid��ҳ��ѯȫ��
     * @return ¥�ż���
     */
    List<BuildingNo> findByEstatePaged(Integer estateId,Integer begin,Integer end);
    
    
    
    /**
     * ������ҵ��ȡ����
     * @param ��ҵid
     * @return ����
     */
    int getCountByEstate(Integer estateId);
    
    /**
     * ���ݷ�Χ��ȡ¥�ż���
     * @param ��ʼ¥��
     * @param ����¥��
     * @return  ¥�ż���
     */
    List<BuildingNo> getAllByRangeEstate(int estate, int begin,int end);
 
    /**
     * �������Ʋ��Ҷ���
     * @param ��ҵ���
     * @param ����
     * @return ����
     */
    BuildingNo findByName(String estateId, String name);
    
    List<BuildingNo> findByBuildingIdAndEstate(int buildingId, int estateId);
    
    int clearBuildingIdBy(int buildingId, int estateId);
}