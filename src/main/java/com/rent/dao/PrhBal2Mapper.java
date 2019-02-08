package com.rent.dao;

import com.rent.common.persistence.annotation.MyBatisDao;
import com.rent.entity.PrhBal2;

@MyBatisDao
public interface PrhBal2Mapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_BAL2
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_BAL2
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int insert(PrhBal2 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_BAL2
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int insertSelective(PrhBal2 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_BAL2
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    PrhBal2 selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_BAL2
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int updateByPrimaryKeySelective(PrhBal2 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_BAL2
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int updateByPrimaryKey(PrhBal2 record);
}