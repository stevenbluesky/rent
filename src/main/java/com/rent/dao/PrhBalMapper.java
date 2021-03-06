package com.rent.dao;

import com.rent.common.persistence.annotation.MyBatisDao;
import com.rent.entity.PrhBal;

@MyBatisDao
public interface PrhBalMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_BAL
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_BAL
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int insert(PrhBal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_BAL
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int insertSelective(PrhBal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_BAL
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    PrhBal selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_BAL
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int updateByPrimaryKeySelective(PrhBal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_BAL
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int updateByPrimaryKey(PrhBal record);
}