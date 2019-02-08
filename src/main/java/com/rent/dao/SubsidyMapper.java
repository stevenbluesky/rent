package com.rent.dao;

import java.util.Date;
import java.util.List;

import com.rent.common.persistence.annotation.MyBatisDao;
import com.rent.entity.Electric;
import com.rent.entity.Subsidy;
import com.rent.services.impl.SubsidyCal;

@MyBatisDao
public interface SubsidyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUBSIDY
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUBSIDY
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    int insert(Subsidy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUBSIDY
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    int insertSelective(Subsidy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUBSIDY
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    Subsidy selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUBSIDY
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    int updateByPrimaryKeySelective(Subsidy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUBSIDY
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    int updateByPrimaryKey(Subsidy record);
    /**
     * ��ѯ����
     * @return ����
     */
     int getCountByEstate(int estateId);
     /**
      * ��ҳ��ѯ
      * @param begin
      * @param end
      * @return ����
      */
     List<Subsidy> findByEstatePaged(int estateId, int begin,int end);
     /**
      * ��ȡ��ǰ������id
      * @return id
      */
     Integer getCurrId();
     
     /**
      * 查询需要计算不补贴信心 
      * @param estateId
      * @param typeId
      * @param inOrOut
      * @param date
      * @return 
      */	   
    List<SubsidyCal> findSubsidyCal(Integer estateId,Integer typeId,Integer inOrOut,Date date);
}