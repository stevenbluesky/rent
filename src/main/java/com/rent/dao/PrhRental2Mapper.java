package com.rent.dao;

import com.rent.common.persistence.annotation.MyBatisDao;
import com.rent.entity.PrhRental2;

@MyBatisDao
public interface PrhRental2Mapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_RENTAL2
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_RENTAL2
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int insert(PrhRental2 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_RENTAL2
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int insertSelective(PrhRental2 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_RENTAL2
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    PrhRental2 selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_RENTAL2
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int updateByPrimaryKeySelective(PrhRental2 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_RENTAL2
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int updateByPrimaryKey(PrhRental2 record);
}