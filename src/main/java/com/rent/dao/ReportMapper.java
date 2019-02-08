package com.rent.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rent.common.persistence.annotation.MyBatisDao;
import com.rent.entity.Report;
@MyBatisDao
public interface ReportMapper {
   
    int updateByPrimaryKey(Report record);
    
    //每日租金    
    List<Report> findDayRent(@Param("month") Integer month,@Param("year") Integer year,@Param("estateId") Integer estateId);
    
    //每日收租金     
    List<Report> findDayDesposit(@Param("month") Integer month,@Param("year") Integer year,@Param("estateId") Integer estateId);
    
    //每日 退租金
    List<Report> findDayExitRent(@Param("month") Integer month,@Param("year") Integer year,@Param("estateId") Integer estateId);
    
    //每日 实退租金  
    List<Report> findDayExitDesposit(@Param("month") Integer month,@Param("year") Integer year,@Param("estateId") Integer estateId);
    
    //每月 实退租金  
    List<Report> findMonthRent(@Param("year") Integer year,@Param("estateId") Integer estateId);
    
    //每月押金
    List<Report> findMonthDesposit(@Param("year") Integer year,@Param("estateId") Integer estateId);
    
    //每月退租金
    List<Report> findMonthExitRent(@Param("year") Integer year,@Param("estateId") Integer estateId);
    
    //每月退押金
    List<Report> findMonthExitDesposit(@Param("year") Integer year,@Param("estateId") Integer estateId);
    
    //租户分析
    List<Double> renterReport(@Param("estateId") Integer estateId);
    
}