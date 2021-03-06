package com.rent.dao;

import java.util.List;

import com.rent.common.persistence.annotation.MyBatisDao;
import com.rent.entity.RentPayWay;
import com.rent.entity.RoomFeature;

@MyBatisDao
public interface RentPayWayMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RENT_PAY_WAY
     *
     * @mbggenerated Tue May 02 16:18:40 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RENT_PAY_WAY
     *
     * @mbggenerated Tue May 02 16:18:40 CST 2017
     */
    int insert(RentPayWay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RENT_PAY_WAY
     *
     * @mbggenerated Tue May 02 16:18:40 CST 2017
     */
    int insertSelective(RentPayWay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RENT_PAY_WAY
     *
     * @mbggenerated Tue May 02 16:18:40 CST 2017
     */
    RentPayWay selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RENT_PAY_WAY
     *
     * @mbggenerated Tue May 02 16:18:40 CST 2017
     */
    int updateByPrimaryKeySelective(RentPayWay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RENT_PAY_WAY
     *
     * @mbggenerated Tue May 02 16:18:40 CST 2017
     */
    int updateByPrimaryKey(RentPayWay record);
    
    /**
     * ��ѯ����
     * @return ����
     */
     int getTotalCount();
     /**
      * ��ҳ��ѯ
      * @param begin
      * @param end
      * @return ����
      */
     List<RentPayWay> findAllPaged(int begin,int end);
    
     /**
      * 查询全部
      * @return 支付方式
      */
     List<RentPayWay> findAll();
     
}