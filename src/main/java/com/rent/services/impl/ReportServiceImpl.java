package com.rent.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.rent.dao.ReportMapper;
import com.rent.entity.Report;
import com.rent.services.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportMapper reportMapper;


	public ReportMapper getReportMapper() {
		return reportMapper;
	}

	public void setReportMapper(ReportMapper reportMapper) {
		this.reportMapper = reportMapper;
	}

	private Map<Integer, Double> dayTotal=new HashMap<Integer,Double>();

	public Map<Integer, Double> getDayTotal() {
		return dayTotal;
	}

	public void setDayTotal(Map<Integer, Double> dayTotal) {
		this.dayTotal = dayTotal;
	}

	// 每日租金
	@Override
	public Map<Integer, Double> findDayRent(Integer year, Integer month,Integer estateId) {
		Map< Integer, Double> map=new HashMap<Integer, Double>();
		List<Report> list = reportMapper.findDayRent(month,year,estateId);
		dayTotal.put(1, 0.0);
		for (Report l : list) {
			map.put(l.getDay().intValue() , l.getTotal().doubleValue());
			//计算总数
			if (dayTotal.containsKey(1)) {
				dayTotal.put(1, dayTotal.get(1)+l.getTotal().doubleValue());
			}else{
				dayTotal.put(1, 0.0);
			}


		}
		return map;
	}

	// 每日收租金
	@Override
	public Map<Integer, Double> findDayDesposit(Integer year, Integer month,Integer estateId) {
		Map< Integer, Double> map=new HashMap<Integer, Double>();
		List<Report> list =  reportMapper.findDayDesposit(month,year,estateId);
		for (Report l : list) {
			map.put(l.getDay().intValue() , l.getTotal().doubleValue());
		}
		return map;
	}

	// 每日 退租金
	@Override
	public Map<Integer, Double> findDayExitRent(Integer year, Integer month,Integer estateId) {
		Map< Integer, Double> map=new HashMap<Integer, Double>();
		List<Report> list =  reportMapper.findDayExitRent(month,year,estateId);
		for (Report l : list) {
			map.put(l.getDay().intValue() , l.getTotal().doubleValue());
		}
		return map;
	}

	// 每日 实退租金
	@Override
	public Map<Integer, Double> findDayExitDesposit(Integer year, Integer month,Integer estateId) {
		Map< Integer, Double> map=new HashMap<Integer, Double>();
		List<Report> list =  reportMapper.findDayExitDesposit(month,year,estateId);
		for (Report l : list) {
			map.put(l.getDay().intValue() , l.getTotal().doubleValue());
		}
		return map;
	}

	//每月 实退租金

	@Override
	public Map<Integer, Double> findMonthRent(Integer year, Integer estateId) {
		Map< Integer, Double> map=new HashMap<Integer, Double>();

		List<Report> list = reportMapper.findMonthRent(year, estateId);
		for (Report l : list) {
			map.put(l.getMonth().intValue() , l.getTotal().doubleValue());
		}
		return map;

	}
	//每月押金

	@Override
	public Map<Integer, Double>findMonthDesposit(Integer year, Integer estateId) {
		Map< Integer, Double> map=new HashMap<Integer, Double>();
		List<Report> list = reportMapper.findMonthDesposit(year, estateId);
		for (Report l : list) {
			map.put(l.getMonth().intValue() , l.getTotal().doubleValue());
		}
		return map;
	}
	//每月退租金

	@Override
	public Map<Integer, Double> findMonthExitRent(Integer year, Integer estateId) {
		Map< Integer, Double> map=new HashMap<Integer, Double>();
		List<Report> list = reportMapper.findMonthExitRent(year, estateId);
		for (Report l : list) {
			map.put(l.getMonth().intValue() , l.getTotal().doubleValue());
		}
		return map;
	}
	//每月退押金

	@Override
	public Map<Integer, Double> findMonthExitDesposit(Integer year, Integer estateId) {
		Map< Integer, Double> map=new HashMap<Integer, Double>();
		List<Report> list = reportMapper.findMonthExitDesposit(year, estateId);
		for (Report l : list) {
			map.put(l.getMonth().intValue() , l.getTotal().doubleValue());
		}
		return map;
	}

	@Override
	public List<Double> renterReport(Integer estateId) {
		//1.房间数、入住房间数、入住人数、入住面积、实收租金、实收押金（按顺序）
		return reportMapper.renterReport(estateId);
	}

}
