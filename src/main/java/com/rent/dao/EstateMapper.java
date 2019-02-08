package com.rent.dao;

import java.util.List;

import com.rent.common.persistence.annotation.MyBatisDao;
import com.rent.entity.BuildingNo;
import com.rent.entity.Estate;

@MyBatisDao
public interface EstateMapper {
   
    int deleteByPrimaryKey(Integer id);
   
    int insert(Estate record);

   
    int insertSelective(Estate record);

   
    Estate selectByPrimaryKey(Integer id);

    
    int updateByPrimaryKeySelective(Estate record);

   
    int updateByPrimaryKey(Estate record);
    
    /**
     * ��ѯȫ��
     * @return ��ҵ����
     */
    List<Estate> findAll();
 
    /**
     * �������Ʋ��Ҷ���
     * @param ����
     * @return ����
     */
    Estate findByName(String name);
    
    Integer getCurrId();
}