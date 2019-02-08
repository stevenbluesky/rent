package com.rent.dao;

import com.rent.common.persistence.annotation.MyBatisDao;
import com.rent.entity.Pbcatvld;

@MyBatisDao
public interface PbcatvldMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCATVLD
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCATVLD
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int insert(Pbcatvld record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCATVLD
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int insertSelective(Pbcatvld record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCATVLD
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    Pbcatvld selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCATVLD
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int updateByPrimaryKeySelective(Pbcatvld record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PBCATVLD
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int updateByPrimaryKey(Pbcatvld record);
}