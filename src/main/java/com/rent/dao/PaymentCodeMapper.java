package com.rent.dao;

import java.util.List;

import com.rent.common.persistence.annotation.MyBatisDao;
import com.rent.entity.PaymentCode;
import com.rent.entity.RoomFeature;

@MyBatisDao
public interface PaymentCodeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PAYMENT_CODE
     *
     * @mbggenerated Tue May 02 16:18:40 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PAYMENT_CODE
     *
     * @mbggenerated Tue May 02 16:18:40 CST 2017
     */
    int insert(PaymentCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PAYMENT_CODE
     *
     * @mbggenerated Tue May 02 16:18:40 CST 2017
     */
    int insertSelective(PaymentCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PAYMENT_CODE
     *
     * @mbggenerated Tue May 02 16:18:40 CST 2017
     */
    PaymentCode selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PAYMENT_CODE
     *
     * @mbggenerated Tue May 02 16:18:40 CST 2017
     */
    int updateByPrimaryKeySelective(PaymentCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PAYMENT_CODE
     *
     * @mbggenerated Tue May 02 16:18:40 CST 2017
     */
    int updateByPrimaryKey(PaymentCode record);
    
    /**
     * 查询总数
     * @return 总数
     */
     int getTotalCount();
     /**
      * 分页查询
      * @param begin
      * @param end
      * @return 集合
      */
     List<PaymentCode> findAllPaged(int begin,int end);
}