package com.rent.dao;

import java.util.List;

import com.rent.common.persistence.annotation.MyBatisDao;
import com.rent.entity.SubsidyWithType;

@MyBatisDao
public interface SubsidyWithTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUBSIDY_WITH_TYPE
     *
     * @mbggenerated Fri May 05 10:29:45 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUBSIDY_WITH_TYPE
     *
     * @mbggenerated Fri May 05 10:29:45 CST 2017
     */
    int insert(SubsidyWithType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUBSIDY_WITH_TYPE
     *
     * @mbggenerated Fri May 05 10:29:45 CST 2017
     */
    int insertSelective(SubsidyWithType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUBSIDY_WITH_TYPE
     *
     * @mbggenerated Fri May 05 10:29:45 CST 2017
     */
    SubsidyWithType selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUBSIDY_WITH_TYPE
     *
     * @mbggenerated Fri May 05 10:29:45 CST 2017
     */
    int updateByPrimaryKeySelective(SubsidyWithType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUBSIDY_WITH_TYPE
     *
     * @mbggenerated Fri May 05 10:29:45 CST 2017
     */
    int updateByPrimaryKey(SubsidyWithType record);
    
    List<SubsidyWithType> findAll();
    
    /**
	 * ���ݲ������ɾ��
	 * @param subsidyId
	 * @return
	 */
	int deleteBySubsidy(int subsidyId);
}