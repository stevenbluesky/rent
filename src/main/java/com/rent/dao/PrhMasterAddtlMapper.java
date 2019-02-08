package com.rent.dao;

import com.rent.common.persistence.annotation.MyBatisDao;
import com.rent.entity.PrhMasterAddtl;

@MyBatisDao
public interface PrhMasterAddtlMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_MASTER_ADDTL
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_MASTER_ADDTL
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int insert(PrhMasterAddtl record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_MASTER_ADDTL
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int insertSelective(PrhMasterAddtl record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_MASTER_ADDTL
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    PrhMasterAddtl selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_MASTER_ADDTL
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int updateByPrimaryKeySelective(PrhMasterAddtl record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PRH_MASTER_ADDTL
     *
     * @mbggenerated Mon Apr 24 14:56:49 CST 2017
     */
    int updateByPrimaryKey(PrhMasterAddtl record);
}