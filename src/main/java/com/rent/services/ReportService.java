package com.rent.services;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rent.entity.Report;

public interface ReportService {
	// 每日租金
	Map<Integer, Double> findDayRent(Integer year, Integer month, Integer estateId);

	// 每日收租金
	Map<Integer, Double> findDayDesposit(Integer year, Integer month, Integer estateId);

	// 每日 退租金
	Map<Integer, Double> findDayExitRent(Integer year, Integer month, Integer estateId);

	// 每日 实退租金
	Map<Integer, Double> findDayExitDesposit(Integer year, Integer month, Integer estateId);

	// 每月 实退租金
	Map<Integer, Double> findMonthRent(Integer year, Integer estateId);

	// 每月押金
	Map<Integer, Double> findMonthDesposit(Integer year, Integer estateId);

	// 每月退租金
	Map<Integer, Double> findMonthExitRent(Integer year, Integer estateId);

	// 每月退押金
	Map<Integer, Double> findMonthExitDesposit(Integer year, Integer estateId);
	
	 //租户分析
    List<Double> renterReport(Integer estateId);
}
