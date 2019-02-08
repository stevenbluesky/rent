package com.rent.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rent.entity.Building;
import com.rent.entity.BuildingNo;
import com.rent.entity.Estate;
import com.rent.common.persistence.annotation.MyBatisDao;
@MyBatisDao
public interface BuildingMapper {
   
    int deleteByPrimaryKey(String id);

   
    int insert(Building record);

   
    int insertSelective(Building record);

   
    Building selectByPrimaryKey(String id);

   
    int updateByPrimaryKeySelective(Building record);

   
    int updateByPrimaryKey(Building record);
    
    int selectTotalCount();
    

    /**
     * ������ҵid��ѯȫ��
     * @return ¥������
     */
    List<Building> findAllByEstate(Integer estateId);

    /**
     * ��ѯȫ��
     * @return ¥�ż���
     */
    List<Building> findAll();
  
    /**
     * ������ҵid��ҳ��ѯȫ��
     * @return ¥�ż���
     */
    List<Building> findByEstatePaged(Integer estateId,Integer begin,Integer end);
    
    List<Building> findByName(String name);
    
    /**
     * ������ҵ��ȡ����
     * @param ��ҵid
     * @return ����
     */
    Integer getCountByEstate(Integer estateId);
    
    List<Building> findByEstatePositioned(Integer estateId);
    
    List<Building> findByNameAndEstate(@Param("name")String name,@Param("estateId") Integer estateId);
    
}