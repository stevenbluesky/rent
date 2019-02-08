package com.rent.dao;

import java.util.List;

import com.rent.common.persistence.annotation.MyBatisDao;
import com.rent.entity.Electric;
import com.rent.entity.SubsidyPercent;

@MyBatisDao
public interface SubsidyPercentMapper {
   
    int deleteByPrimaryKey(Integer id);

    
    int insert(SubsidyPercent record);

    int insertSelective(SubsidyPercent record);

    
    SubsidyPercent selectByPrimaryKey(Integer id);

   
    int updateByPrimaryKeySelective(SubsidyPercent record);

   
    int updateByPrimaryKey(SubsidyPercent record);
    
    /**
     * 获取当前新增的id
     * @return
     */
   Integer getCurrId();

}