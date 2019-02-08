package com.rent.dao;

import java.util.List;

import com.rent.common.persistence.annotation.MyBatisDao;
import com.rent.entity.EstateType;

@MyBatisDao
public interface EstateTypeMapper {
   
    int deleteByPrimaryKey(Integer id);

    
    int insert(EstateType record);

   
    int insertSelective(EstateType record);

  
    EstateType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EstateType record);

   
    int updateByPrimaryKey(EstateType record);
    
    List<EstateType> findAll();
}