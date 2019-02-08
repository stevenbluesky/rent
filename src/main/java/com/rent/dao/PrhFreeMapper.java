package com.rent.dao;

import com.rent.common.persistence.annotation.MyBatisDao;
import com.rent.entity.PrhFree;

@MyBatisDao
public interface PrhFreeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_FREE
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_FREE
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int insert(PrhFree record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_FREE
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int insertSelective(PrhFree record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_FREE
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    PrhFree selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_FREE
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int updateByPrimaryKeySelective(PrhFree record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_FREE
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int updateByPrimaryKey(PrhFree record);
}