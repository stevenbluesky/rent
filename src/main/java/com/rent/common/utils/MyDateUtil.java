package com.rent.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDateUtil {
	
	
	public static Date cloneDate(Date date){
		return toDate(getDateFormat(date)) ;
	}
	
	/**
	 * 获取格式化后的时间(yyyy-MM-dd)
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateFormat(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String format = df.format(date);
		return format;
	}

	/**
	 * 获取格式化后的时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateFormat(Date date, String formart) {
		SimpleDateFormat df = new SimpleDateFormat(formart);
		String format = df.format(date);
		return format;
	}

	/**
	 * 获取将字符串转为时间(yyyy-MM-dd)
	 * 
	 * @param date
	 * @return
	 */
	public static Date toDate(String date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 获取将字符串转为时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date toDate(String date, String formart) {
		SimpleDateFormat df = new SimpleDateFormat(formart);
		Date d = null;
		try {
			d = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 加 月份
	 * 
	 * @param date
	 * @param add
	 * @return
	 */
	public static Date addMonth(Date date, int add) {
    	 Calendar c = Calendar.getInstance();
    	 c.setTime(date);
    	 c.add(Calendar.MONTH, add);
    	 Date newDate = c.getTime();
    	
		
		return newDate;

	}

	/**
	 * 加年份
	 * 
	 * @param date
	 * @param add
	 * @return
	 */
	public static Date addYear(Date date, int add) {
		date.setYear(date.getYear() + add);
		return date;
	}

	/**
	 * 加天数
	 * 
	 * @param date
	 * @param add
	 * @return
	 */
	public static Date addDate(Date date, int add) {
		date.setDate(date.getDate() + add);
		return date;
	}

	/**
	 * 获取该时间段的天数
	 * 
	 * @param begin
	 * @param end
	 * @return 天数
	 */
	public static int getDateNum(Date start, Date end) {
		long days = end.getTime() / 86400000 - start.getTime() / 86400000;
		return Integer.parseInt(days + "");

	}

	public static int monthNum(Date begin, Date end) {
		// 年月日
		int year1 = begin.getYear();
		int year2 = end.getYear();
		int month1 = begin.getMonth();
		int month2 = end.getMonth();
		int date1 = begin.getDate();
		int date2 = end.getDate();

		int monthsub = (year2 - year1) * 12 + (month2 - month1);
		if (date2 > date1) {
			monthsub += 1;
		}
		if (monthsub == 0) {
			monthsub = 1;
		}
		return monthsub;
	}

	// 判断是否是当月的最后一天
	public static boolean isLastDayInMonth(Date date) {

		Calendar b = Calendar.getInstance();
		b.setTime(date);

		int lastDay = b.getActualMaximum(Calendar.DAY_OF_MONTH);
		int now = b.get(Calendar.DAY_OF_MONTH);
		/*	// 一月份 的
		if ((date.getMonth() + 1) == 1) {
			//平年
			if (isLeapYeal(date)) {
				if (date.getDate()==31||date.getDate()==30||date.getDate()==29||date.getDate()==28) {

				}
			
			//瑞年
			}else{
				if (date.getDate()==31||date.getDate()==30||date.getDate()==29) {
					
				}
			}
		}*/
		return now == lastDay;
	}
	
	// 比较两个日期是否相同
		public static boolean isTheSame(Date date1,Date date2) {
			if (date1.getYear()==date2.getYear()&&date1.getMonth()==date2.getMonth()&&date1.getDate()==date2.getDate()) {
				return true;
			}
			return false;
		}
	
	// 获取下个月最后一天
	public static Date getLastDayNextMonth(Date date) {
		Calendar b = Calendar.getInstance();
		b.setTime(date);
		b.add(Calendar.MONTH, 1);
		int lastDay = b.getActualMaximum(Calendar.DAY_OF_MONTH);
		b.set(Calendar.DATE, lastDay);
		return b.getTime();
	}
	//判断是否是瑞年
	public static boolean isLeapYeal(Date date) {
		Integer year = date.getYear() + 1900;
		if (year % 100 == 0) {
			if (year % 400 == 0) {
				return true;
			}
		} else {
			if (year % 4 == 0) {
				return true;
			}
		}
		return false;
	}
	
	
	public static void main(String[] args) {
	/*	Date da = new Date();
		da.setDate(22);
		Date date = getLastDayNextMonth(da);
		String d = MyDateUtil.getDateFormat(date);
		System.out.println(d);*/

		/*
		 * Date start = new Date("2017/05/07"); Date end = new Date("2018/5/3");
		 * int monthNum = monthNum(start, end); System.out.println(monthNum);
		 */
		boolean flag = isLeapYeal(new Date(2020-1900,1,1));
		System.out.println(flag);
	}

	

}
