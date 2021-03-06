package com.rent.dao;

import com.rent.common.persistence.annotation.MyBatisDao;
import com.rent.entity.SmsHis;

@MyBatisDao
public interface SmsHisMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SMS_HIS
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SMS_HIS
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int insert(SmsHis record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SMS_HIS
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int insertSelective(SmsHis record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SMS_HIS
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    SmsHis selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SMS_HIS
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int updateByPrimaryKeySelective(SmsHis record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SMS_HIS
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int updateByPrimaryKey(SmsHis record);
}