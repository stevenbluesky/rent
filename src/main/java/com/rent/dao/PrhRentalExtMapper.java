package com.rent.dao;

import com.rent.common.persistence.annotation.MyBatisDao;
import com.rent.entity.PrhRentalExt;

@MyBatisDao
public interface PrhRentalExtMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_RENTAL_EXT
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_RENTAL_EXT
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int insert(PrhRentalExt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_RENTAL_EXT
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int insertSelective(PrhRentalExt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_RENTAL_EXT
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    PrhRentalExt selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_RENTAL_EXT
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int updateByPrimaryKeySelective(PrhRentalExt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_RENTAL_EXT
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int updateByPrimaryKey(PrhRentalExt record);
}