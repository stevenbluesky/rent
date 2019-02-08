package com.rent.dao;

import java.util.List;

import com.rent.common.persistence.annotation.MyBatisDao;
import com.rent.entity.PrhTempLiveMan;

@MyBatisDao
public interface PrhTempLiveManMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PrhTempLiveMan record);

    int insertSelective(PrhTempLiveMan record);

    PrhTempLiveMan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PrhTempLiveMan record);

    int updateByPrimaryKey(PrhTempLiveMan record);
    
    List<PrhTempLiveMan> findByMaster(Integer masterId);
}