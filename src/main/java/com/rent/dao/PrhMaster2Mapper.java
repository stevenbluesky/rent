package com.rent.dao;

import com.rent.common.persistence.annotation.MyBatisDao;
import com.rent.entity.PrhMaster2;

@MyBatisDao
public interface PrhMaster2Mapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_MASTER2
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_MASTER2
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int insert(PrhMaster2 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_MASTER2
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int insertSelective(PrhMaster2 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_MASTER2
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    PrhMaster2 selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_MASTER2
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int updateByPrimaryKeySelective(PrhMaster2 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_MASTER2
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int updateByPrimaryKey(PrhMaster2 record);
}