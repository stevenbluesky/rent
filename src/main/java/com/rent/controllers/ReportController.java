package com.rent.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rent.common.config.Global;
import com.rent.common.utils.ExcelExport;
import com.rent.common.utils.FileUtils;
import com.rent.common.utils.MyConvertUtil;
import com.rent.common.utils.MyDateUtil;
import com.rent.condition.HouseCondition;
import com.rent.condition.MasterCondition;
import com.rent.dao.PrhTempLiveManMapper;
import com.rent.entity.Estate;
import com.rent.entity.PrHouse;
import com.rent.entity.PrhLinkman;
import com.rent.entity.PrhMaster;
import com.rent.entity.PrhRental;
import com.rent.entity.PrhTempLiveMan;
import com.rent.entity.Report;
import com.rent.entity.Users;
import com.rent.services.CardService;
import com.rent.services.PrHouseService;
import com.rent.services.PrhLinkManService;
import com.rent.services.PrhMasterService;
import com.rent.services.PrhTempLiveManService;
import com.rent.services.RenterService;
import com.rent.services.ReportService;
import com.rent.services.SubsidyService;
import com.rent.services.impl.SubsidyCal;

@Controller
public class ReportController {

	@Autowired
	private PrhTempLiveManService prhTempLiveManService;
	
	private PrhTempLiveManMapper prhTempLiveManMapper;
	
	@Autowired
	private SubsidyService subsidyService;
	@Autowired
	private RenterService renterService ;
	@Autowired
	private PrHouseService prHouseService;
	@Autowired
	private CardService cardService;
	@Autowired
	private PrhLinkManService prhLinkManService;

	@Autowired
	private PrhMasterService prhMasterService;


	
	public PrhTempLiveManService getPrhTempLiveManService() {
		return prhTempLiveManService;
	}

	public void setPrhTempLiveManService(PrhTempLiveManService prhTempLiveManService) {
		this.prhTempLiveManService = prhTempLiveManService;
	}

	public PrhTempLiveManMapper getPrhTempLiveManMapper() {
		return prhTempLiveManMapper;
	}

	public void setPrhTempLiveManMapper(PrhTempLiveManMapper prhTempLiveManMapper) {
		this.prhTempLiveManMapper = prhTempLiveManMapper;
	}

	public RenterService getRenterService() {
		return renterService;
	}

	public void setRenterService(RenterService renterService) {
		this.renterService = renterService;
	}

	public SubsidyService getSubsidyService() {
		return subsidyService;
	}

	public void setSubsidyService(SubsidyService subsidyService) {
		this.subsidyService = subsidyService;
	}

	public PrhLinkManService getPrhLinkManService() {
		return prhLinkManService;
	}

	public void setPrhLinkManService(PrhLinkManService prhLinkManService) {
		this.prhLinkManService = prhLinkManService;
	}

	public CardService getCardService() {
		return cardService;
	}

	public void setCardService(CardService cardService) {
		this.cardService = cardService;
	}

	public PrhMasterService getPrhMasterService() {
		return prhMasterService;
	}

	public void setPrhMasterService(PrhMasterService prhMasterService) {
		this.prhMasterService = prhMasterService;
	}

	public PrHouseService getPrHouseService() {
		return prHouseService;
	}

	@Autowired
	private ReportService reportService;

	public ReportService getReportService() {
		return reportService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public void setPrHouseService(PrHouseService prHouseService) {
		this.prHouseService = prHouseService;
	}

	// 日报表
	@RequestMapping("dayReport.do")
	public String dayReport(Integer estateId, Integer year, Integer month, ModelMap map) {
		Date date = new Date();
		if (year == null) {
			year = date.getYear() + 1900;
		}
		if (month == null) {

			month = date.getMonth() + 1;
		}

		// 查询物业集合
		List<Estate> estates = prHouseService.getAllEstate();
		if (estateId == null) {
			if (estates != null && estates.size() != 0) {
				estateId = estates.get(0).getId();
			}
		}
		Date now = new Date();
		String title = "";
		if (now.getYear() + 1900 == year && now.getMonth() + 1 == month) {

			title = year + "年" + month + "月 营业日报汇总报表(截至" + (now.getYear() + 1900) + "年" + (now.getMonth() + 1) + "月"
					+ now.getDate() + "日" + now.getHours() + "点" + now.getMinutes() + "分）";
		} else {
			title = year + "年" + month + "月 营业日报汇总报表";
		}
		map.put("title", title);

		// 查询报表
		Map<Integer, Double> dayRents = reportService.findDayRent(year, month, estateId);
		Map<Integer, Double> dayExitRents = reportService.findDayExitRent(year, month, estateId);
		Map<Integer, Double> dayDesposits = reportService.findDayDesposit(year, month, estateId);
		Map<Integer, Double> dayExitDesposits = reportService.findDayExitDesposit(year, month, estateId);

		map.put("estates", estates);
		map.put("estateId", estateId);

		map.put("dayRents", dayRents);
		map.put("dayExitRents", dayExitRents);
		map.put("dayDesposits", dayDesposits);
		map.put("nowYear", year);
		map.put("nowMonth", month);
		map.put("dayExitDesposits", dayExitDesposits);

		return "prh/report/dayReport.jsp";
	}

	// 导出日报表
	@RequestMapping("dayReportExport.do")
	public void dayReportExport(Integer estateId, Integer year, Integer month, HttpServletResponse res,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		int days = 31;
		Date now = new Date();
		String fileName = "日报表（" + year + "年" + month + "月）";

		// 获取output
		BufferedOutputStream bufferedOutPut = getOutPut(fileName, response);
		// 生成workbook
		HSSFWorkbook wb = createWorkbook();
		// 获取sheet
		HSSFSheet sheet = wb.createSheet();

		sheet.setDefaultColumnWidth(7);
		sheet.setDefaultRowHeightInPoints(20);
		// 定义单元格格式，添加单元格表样式，并添加到工作簿
		HSSFCellStyle cellStyle = wb.createCellStyle();
		// 设置单元格水平对齐类型
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 竖直对齐
		// 创建单元格
		HSSFRow row0 = sheet.createRow(0);
		HSSFCell c = row0.createCell(0);
		String title = "";
		if (now.getYear() + 1900 == year && now.getMonth() + 1 == month) {

			title = year + "年" + month + "月 营业日报汇总报表(截至" + (now.getYear() + 1900) + "年" + (now.getMonth() + 1) + "月"
					+ now.getDate() + "日" + now.getHours() + "点" + now.getMinutes() + "分）";
		} else {
			title = year + "年" + month + "月 营业日报汇总报表";
		}

		c.setCellValue(new HSSFRichTextString(title));
		c.setCellStyle(cellStyle);
		HSSFRow row = sheet.createRow(1);
		HSSFCell c0 = row.createCell(0);
		c0.setCellValue(new HSSFRichTextString("项目"));
		c0.setCellStyle(cellStyle);
		HSSFCell c3 = row.createCell(2);
		c3.setCellValue(new HSSFRichTextString("月累计"));
		c3.setCellStyle(cellStyle);

		HSSFCell c4 = row.createCell(4);
		c4.setCellValue(new HSSFRichTextString(""));
		HSSFCell c5 = row.createCell(4);
		c5.setCellValue(new HSSFRichTextString(""));
		HSSFCell c6 = row.createCell(4);
		c6.setCellValue(new HSSFRichTextString(""));
		CellRangeAddress region0 = new CellRangeAddress(0, 0, 0, days + 2);
		CellRangeAddress region1 = new CellRangeAddress(1, 2, 0, 1);
		CellRangeAddress region2 = new CellRangeAddress(1, 2, 2, 2);

		CellRangeAddress region3 = new CellRangeAddress(1, 1, 3, 12);
		CellRangeAddress region4 = new CellRangeAddress(1, 1, 13, 22);
		CellRangeAddress region5 = new CellRangeAddress(1, 1, 23, days + 2);

		sheet.addMergedRegion(region0);
		sheet.addMergedRegion(region1);
		sheet.addMergedRegion(region2);
		sheet.addMergedRegion(region3);
		sheet.addMergedRegion(region4);
		sheet.addMergedRegion(region5);
		// 天数
		HSSFRow row2 = sheet.createRow(2);
		for (int i = 1; i <= days; i++) {
			HSSFCell daysCell = row2.createCell(i + 2);
			daysCell.setCellValue(new HSSFRichTextString(i + ""));
		}

		// 查询报表
		Map<Integer, Double> dayRents = reportService.findDayRent(year, month, estateId);
		Map<Integer, Double> dayExitRents = reportService.findDayExitRent(year, month, estateId);
		Map<Integer, Double> dayDesposits = reportService.findDayDesposit(year, month, estateId);
		Map<Integer, Double> dayExitDesposits = reportService.findDayExitDesposit(year, month, estateId);

		Map<Integer, Double> total = new HashMap<Integer, Double>();

		// 房租统计
		HSSFRow row4 = sheet.createRow(3);
		HSSFCell cell4_1 = row4.createCell(0);
		cell4_1.setCellValue(new HSSFRichTextString("1."));
		HSSFCell cell4_2 = row4.createCell(1);
		cell4_2.setCellValue(new HSSFRichTextString("房租统计"));

		// 填充数值
		for (int i = 1; i <= days; i++) {
			HSSFCell daysCell = row4.createCell(i + 2);
			double dayRent = dayRents.get(i) != null ? dayRents.get(i) : 0.0;
			double dayExit = dayExitRents.get(i) != null ? dayExitRents.get(i) : 0.0;
			daysCell.setCellValue(new HSSFRichTextString((dayRent - dayExit) != 0 ? (dayRent - dayExit) + "" : ""));
			// 总计
			if (total.get(1) == null) {
				total.put(1, (dayRent - dayExit));
			} else {
				total.put(1, total.get(1) + (dayRent - dayExit));
			}
		}

		// 收房租
		HSSFRow row5 = sheet.createRow(4);
		HSSFCell cell5_2 = row5.createCell(1);
		cell5_2.setCellValue(new HSSFRichTextString("收房租"));
		for (int i = 1; i <= days; i++) {
			HSSFCell daysCell = row5.createCell(i + 2);
			daysCell.setCellValue(new HSSFRichTextString(dayRents.get(i) != null ? dayRents.get(i).toString() : ""));
			// 总计
			if (total.get(2) == null) {
				total.put(2, dayRents.get(i));
			} else {
				total.put(2, total.get(2) + (dayRents.get(i) == null ? 0 : dayRents.get(i)));
			}
		}
		// 退房租
		HSSFRow row6 = sheet.createRow(5);
		HSSFCell cell6_2 = row6.createCell(1);
		cell6_2.setCellValue(new HSSFRichTextString("退房租"));
		for (int i = 1; i <= days; i++) {
			HSSFCell daysCell = row6.createCell(i + 2);
			daysCell.setCellValue(
					new HSSFRichTextString(dayExitRents.get(i) != null ? dayExitRents.get(i).toString() : ""));
			// 总计
			if (total.get(3) == null) {
				total.put(3, dayExitRents.get(i));
			} else {
				total.put(3, total.get(3) + (dayExitRents.get(i) != null ? dayExitRents.get(i) : 0));
			}
		}
		// 押金统计
		HSSFRow row7 = sheet.createRow(6);
		HSSFCell cell7_1 = row7.createCell(0);
		cell7_1.setCellValue(new HSSFRichTextString("2."));
		HSSFCell cell7_2 = row7.createCell(1);
		cell7_2.setCellValue(new HSSFRichTextString("押金统计"));

		for (int i = 1; i <= days; i++) {
			HSSFCell daysCell = row7.createCell(i + 2);
			double dayDesposit = dayDesposits.get(i) != null ? dayDesposits.get(i) : 0.0;
			double dayExitDesposit = dayExitDesposits.get(i) != null ? dayExitDesposits.get(i) : 0.0;
			daysCell.setCellValue(new HSSFRichTextString(
					(dayDesposit - dayExitDesposit) != 0 ? (dayDesposit - dayExitDesposit) + "" : ""));

			if (total.get(4) == null) {
				total.put(4, (dayDesposit - dayExitDesposit));
			} else {
				total.put(4, total.get(4) + (dayDesposit - dayExitDesposit));
			}
		}

		// 收押金
		HSSFRow row8 = sheet.createRow(7);
		HSSFCell cell8_2 = row8.createCell(1);
		cell8_2.setCellValue(new HSSFRichTextString("收押金"));

		for (int i = 1; i <= days; i++) {
			HSSFCell daysCell = row8.createCell(i + 2);
			daysCell.setCellValue(new HSSFRichTextString(
					dayDesposits.get(i) != null && dayDesposits.get(i) != 0 ? dayDesposits.get(i).toString() : ""));
			// 总计
			if (total.get(5) == null) {
				total.put(5, dayDesposits.get(i));
			} else {
				total.put(5, total.get(5) + (dayDesposits.get(i) != null ? dayDesposits.get(i) : 0));
			}
		}

		// 退押金
		HSSFRow row9 = sheet.createRow(8);
		HSSFCell cell9_2 = row9.createCell(1);
		cell9_2.setCellValue(new HSSFRichTextString("退押金"));

		for (int i = 1; i <= days; i++) {
			HSSFCell daysCell = row9.createCell(i + 2);
			daysCell.setCellValue(new HSSFRichTextString(dayExitDesposits.get(i) != null && dayExitDesposits.get(i) != 0
					? dayExitDesposits.get(i).toString() : ""));
			// 总计
			if (total.get(6) == null) {
				total.put(6, dayExitDesposits.get(i));
			} else {
				total.put(6, total.get(6) + (dayExitDesposits.get(i) != null ? dayExitDesposits.get(i) : 0));
			}
		}

		// 统计
		HSSFRow row10 = sheet.createRow(9);
		HSSFCell cell10_1 = row10.createCell(0);
		cell10_1.setCellValue(new HSSFRichTextString("3."));
		HSSFCell cell10_2 = row10.createCell(1);
		cell10_2.setCellValue(new HSSFRichTextString("总计"));

		for (int i = 1; i <= days; i++) {
			HSSFCell daysCell = row10.createCell(i + 2);

			double dayRent = dayRents.get(i) != null ? dayRents.get(i) : 0.0;
			double dayExitRent = dayExitRents.get(i) != null ? dayExitRents.get(i) : 0.0;
			double dayDesposit = dayDesposits.get(i) != null ? dayDesposits.get(i) : 0.0;
			double dayExitDesposit = dayExitDesposits.get(i) != null ? dayExitDesposits.get(i) : 0.0;
			daysCell.setCellValue(
					new HSSFRichTextString(((dayRent - dayExitRent) + (dayDesposit - dayExitDesposit)) != 0
							? ((dayRent - dayExitRent) + (dayDesposit - dayExitDesposit)) + "" : ""));

			if (total.get(7) == null) {
				total.put(7, ((dayRent - dayExitRent) + (dayDesposit - dayExitDesposit)));
			} else {
				total.put(7, total.get(7) + ((dayRent - dayExitRent) + (dayDesposit - dayExitDesposit)));
			}
		}

		HSSFCell cell4_3 = row4.createCell(2);
		cell4_3.setCellValue(
				new HSSFRichTextString(total.get(1) != null && total.get(1) != 0 ? total.get(1).toString() : ""));
		HSSFCell cell5_3 = row5.createCell(2);
		cell5_3.setCellValue(
				new HSSFRichTextString(total.get(2) != null && total.get(2) != 0 ? total.get(2).toString() : ""));
		HSSFCell cell6_3 = row6.createCell(2);
		cell6_3.setCellValue(
				new HSSFRichTextString(total.get(3) != null && total.get(3) != 0 ? total.get(3).toString() : ""));
		HSSFCell cell7_3 = row7.createCell(2);
		cell7_3.setCellValue(
				new HSSFRichTextString(total.get(4) != null && total.get(4) != 0 ? total.get(4).toString() : ""));
		HSSFCell cell8_3 = row8.createCell(2);
		cell8_3.setCellValue(
				new HSSFRichTextString(total.get(5) != null && total.get(5) != 0 ? total.get(5).toString() : ""));
		HSSFCell cell9_3 = row9.createCell(2);
		cell9_3.setCellValue(
				new HSSFRichTextString(total.get(6) != null && total.get(6) != 0 ? total.get(6).toString() : ""));
		HSSFCell cell10_3 = row10.createCell(2);
		cell10_3.setCellValue(
				new HSSFRichTextString(total.get(7) != null && total.get(7) != 0 ? total.get(7).toString() : ""));

		try {
			// 定义单元格格式，添加单元格表样式，并添加到工作簿

			bufferedOutPut.flush();
			wb.write(bufferedOutPut);
			bufferedOutPut.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Output   is   closed ");
		} finally {

		}

	}

	// 导出月报表
	@RequestMapping("monthReportExport.do")
	public void monthReportExport(Integer estateId, Integer year, HttpServletResponse res, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int month = 12;
		Date now = new Date();
		String fileName = "月报表（" + year + "年）";

		// 获取output
		BufferedOutputStream bufferedOutPut = getOutPut(fileName, response);
		// 生成workbook
		HSSFWorkbook wb = createWorkbook();
		// 获取sheet
		HSSFSheet sheet = wb.createSheet();
		sheet.setDefaultColumnWidth(11);
		sheet.setDefaultRowHeightInPoints(20);
		// 创建单元格
		HSSFRow row0 = sheet.createRow(0);
		HSSFCell c = row0.createCell(0);

		// 定义单元格格式，添加单元格表样式，并添加到工作簿
		HSSFCellStyle cellStyle = wb.createCellStyle();
		// 设置单元格水平对齐类型
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		c.setCellStyle(cellStyle);

		String title = "";
		if (now.getYear() + 1900 == year) {

			title = year + "年  营业月报汇总报表(截至" + (now.getYear() + 1900) + "年" + (now.getMonth() + 1) + "月" + now.getDate()
					+ "日" + now.getHours() + "点" + now.getMinutes() + "分）";
		} else {
			title = year + "年  营业月报汇总报表";
		}
		c.setCellValue(new HSSFRichTextString(title));
		c.setCellStyle(cellStyle);

		HSSFRow row = sheet.createRow(1);
		HSSFCell c0 = row.createCell(0);
		c0.setCellValue(new HSSFRichTextString("项目"));
		c0.setCellStyle(cellStyle);
		HSSFCell c3 = row.createCell(2);
		c3.setCellValue(new HSSFRichTextString("年累计"));
		c3.setCellStyle(cellStyle);
		HSSFCell c4 = row.createCell(3);
		c4.setCellValue(new HSSFRichTextString("第一季度"));
		HSSFCell c5 = row.createCell(6);
		c5.setCellValue(new HSSFRichTextString("第二季度"));
		HSSFCell c6 = row.createCell(9);
		c6.setCellValue(new HSSFRichTextString("第三季度"));
		HSSFCell c7 = row.createCell(12);
		c7.setCellValue(new HSSFRichTextString("第四季度"));

		CellRangeAddress region0 = new CellRangeAddress(0, 0, 0, month + 2);
		CellRangeAddress region1 = new CellRangeAddress(1, 2, 0, 1);
		CellRangeAddress region2 = new CellRangeAddress(1, 2, 2, 2);

		CellRangeAddress region3 = new CellRangeAddress(1, 1, 3, 5);
		CellRangeAddress region4 = new CellRangeAddress(1, 1, 6, 8);
		CellRangeAddress region5 = new CellRangeAddress(1, 1, 9, 11);
		CellRangeAddress region6 = new CellRangeAddress(1, 1, 12, 14);

		sheet.addMergedRegion(region0);
		sheet.addMergedRegion(region1);
		sheet.addMergedRegion(region2);
		sheet.addMergedRegion(region3);
		sheet.addMergedRegion(region4);
		sheet.addMergedRegion(region5);
		sheet.addMergedRegion(region6);
		// 天数
		HSSFRow row2 = sheet.createRow(2);
		for (int i = 1; i <= month; i++) {
			HSSFCell daysCell = row2.createCell(i + 2);
			daysCell.setCellValue(new HSSFRichTextString(i + ""));
		}
		// 查找租金和押金信息
		Map<Integer, Double> monthRent = reportService.findMonthRent(year, estateId);
		Map<Integer, Double> monthDesposit = reportService.findMonthDesposit(year, estateId);
		Map<Integer, Double> monthExitRent = reportService.findMonthExitRent(year, estateId);
		Map<Integer, Double> monthExitDesposit = reportService.findMonthExitDesposit(year, estateId);

		// 房租统计
		HSSFRow row4 = sheet.createRow(3);
		HSSFCell cell4_1 = row4.createCell(0);
		cell4_1.setCellValue(new HSSFRichTextString("1."));
		HSSFCell cell4_2 = row4.createCell(1);
		cell4_2.setCellValue(new HSSFRichTextString("房租统计"));

		// 填充金额
		double total = 0;
		for (int i = 1; i <= month; i++) {
			HSSFCell daysCell = row4.createCell(i + 2);
			double rent = monthRent.get(i) != null ? monthRent.get(i) : 0;
			double exit = monthExitRent.get(i) != null ? monthExitRent.get(i) : 0;
			Double money = rent - exit;

			daysCell.setCellValue(new HSSFRichTextString(money != null && money != 0 ? money.toString() : ""));
			total += (money != null) ? money : 0;
			if (i == month) {
				HSSFCell cell4_3 = row4.createCell(2);
				cell4_3.setCellValue(new HSSFRichTextString(total != 0 ? total + "" : ""));
			}
		}

		// 收房租
		HSSFRow row5 = sheet.createRow(4);
		HSSFCell cell5_2 = row5.createCell(1);
		cell5_2.setCellValue(new HSSFRichTextString("收房租"));
		// 填充金额
		total = 0;
		for (int i = 1; i <= month; i++) {
			HSSFCell daysCell = row5.createCell(i + 2);
			Double money = monthRent.get(i);
			daysCell.setCellValue(new HSSFRichTextString(money != null && money != 0 ? money.toString() : ""));
			total += (money != null) ? money : 0;
			if (i == month) {
				HSSFCell cell4_3 = row5.createCell(2);
				cell4_3.setCellValue(new HSSFRichTextString(total != 0 ? total + "" : ""));
			}
		}

		// 退房租
		HSSFRow row6 = sheet.createRow(5);
		HSSFCell cell6_2 = row6.createCell(1);
		cell6_2.setCellValue(new HSSFRichTextString("退房租"));
		// 填充金额
		total = 0;
		for (int i = 1; i <= month; i++) {
			HSSFCell daysCell = row6.createCell(i + 2);
			Double money = monthExitRent.get(i);
			daysCell.setCellValue(new HSSFRichTextString(money != null && money != 0 ? money.toString() : ""));
			total += (money != null) ? money : 0;
			if (i == month) {
				HSSFCell cell4_3 = row6.createCell(2);
				cell4_3.setCellValue(new HSSFRichTextString(total != 0 ? total + "" : ""));
			}
		}
		// 押金统计
		HSSFRow row7 = sheet.createRow(6);
		HSSFCell cell7_1 = row7.createCell(0);
		cell7_1.setCellValue(new HSSFRichTextString("2."));
		HSSFCell cell7_2 = row7.createCell(1);
		cell7_2.setCellValue(new HSSFRichTextString("押金统计"));

		// 填充金额
		total = 0;
		for (int i = 1; i <= month; i++) {
			HSSFCell daysCell = row7.createCell(i + 2);
			double rent = monthDesposit.get(i) != null ? monthDesposit.get(i) : 0;
			double exit = monthExitDesposit.get(i) != null ? monthExitDesposit.get(i) : 0;
			Double money = rent - exit;
			daysCell.setCellValue(new HSSFRichTextString(money != null && money != 0 ? money.toString() : ""));
			total += (money != null) ? money : 0;
			if (i == month) {
				HSSFCell cell4_3 = row7.createCell(2);
				cell4_3.setCellValue(new HSSFRichTextString(total != 0 ? total + "" : ""));
			}

		}

		// 收押金
		HSSFRow row8 = sheet.createRow(7);
		HSSFCell cell8_2 = row8.createCell(1);
		cell8_2.setCellValue(new HSSFRichTextString("收押金"));
		total = 0;
		for (int i = 1; i <= month; i++) {
			HSSFCell daysCell = row8.createCell(i + 2);
			Double money = monthDesposit.get(i);
			daysCell.setCellValue(new HSSFRichTextString(money != null && money != 0 ? money.toString() : ""));
			total += (money != null) ? money : 0;
			if (i == month) {
				HSSFCell cell4_3 = row8.createCell(2);
				cell4_3.setCellValue(new HSSFRichTextString(total != 0 ? total + "" : ""));
			}
		}

		// 退押金
		HSSFRow row9 = sheet.createRow(8);
		HSSFCell cell9_2 = row9.createCell(1);
		cell9_2.setCellValue(new HSSFRichTextString("退押金"));
		total = 0;
		for (int i = 1; i <= month; i++) {
			HSSFCell daysCell = row9.createCell(i + 2);
			Double money = monthExitDesposit.get(i);
			daysCell.setCellValue(new HSSFRichTextString(money != null && money != 0 ? money.toString() : ""));
			total += (money != null) ? money : 0;
			if (i == month) {
				HSSFCell cell4_3 = row9.createCell(2);
				cell4_3.setCellValue(new HSSFRichTextString(total != 0 ? total + "" : ""));
			}
		}
		// 押金统计
		HSSFRow row10 = sheet.createRow(9);
		HSSFCell cell10_1 = row10.createCell(0);
		cell10_1.setCellValue(new HSSFRichTextString("3."));

		HSSFCell cell10_2 = row10.createCell(1);
		cell10_2.setCellValue(new HSSFRichTextString("总计"));

		// 填充金额
		total = 0;
		for (int i = 1; i <= month; i++) {
			HSSFCell daysCell = row10.createCell(i + 2);
			double desposit = monthDesposit.get(i) != null ? monthDesposit.get(i) : 0;
			double exitDesposit = monthExitDesposit.get(i) != null ? monthExitDesposit.get(i) : 0;
			double rent = monthRent.get(i) != null ? monthRent.get(i) : 0;
			double exitRent = monthExitRent.get(i) != null ? monthExitRent.get(i) : 0;
			Double money = (desposit - exitDesposit) + (rent - exitRent);

			daysCell.setCellValue(new HSSFRichTextString(money != null && money != 0 ? money.toString() : ""));
			total += (money != null) ? money : 0;
			if (i == month) {
				HSSFCell cell4_3 = row10.createCell(2);

				cell4_3.setCellValue(new HSSFRichTextString(total != 0 ? total + "" : ""));
			}

		}

		try {
			// 定义单元格格式，添加单元格表样式，并添加到工作簿

			bufferedOutPut.flush();
			wb.write(bufferedOutPut);
			bufferedOutPut.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Output   is   closed ");
		} finally {

		}

	}

	// 导出租户报表
	@RequestMapping("renterReportExport.do")
	public void renterReportExport(Integer estateId, HttpServletResponse res, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		Users user = (Users) session.getAttribute(Global.USER);

		Map<String, List<Double>> reports = new HashMap<String, List<Double>>();
		// 1.房间数、入住房间数、入住人数、入住面积、实收租金、实收押金（按顺序）
		if (user != null && user.getId() != 1) {
			List<Double> result = reportService.renterReport(user.getEstateId());
			reports.put(user.getEstate().getName(), result);
		} else {
			// 查询物业集合
			List<Estate> estates = prHouseService.getAllEstate();

			for (Estate estate : estates) {
				List<Double> result = reportService.renterReport(estate.getId());
				reports.put(estate.getName(), result);
			}

		}
		Date now = new Date();

		String fileName = "租户统计分析报表";

		// 获取output
		BufferedOutputStream bufferedOutPut = getOutPut(fileName, response);
		// 生成workbook
		HSSFWorkbook wb = createWorkbook();
		// 获取sheet
		HSSFSheet sheet = wb.createSheet();

		sheet.setDefaultColumnWidth(16);
		sheet.setDefaultRowHeightInPoints(20);
		// 定义单元格格式，添加单元格表样式，并添加到工作簿
		HSSFCellStyle cellStyle = wb.createCellStyle();
		// 设置单元格水平对齐类型
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 竖直对齐

		// 创建单元格
		HSSFRow row0 = sheet.createRow(0);

		HSSFCell c0 = row0.createCell(0);
		c0.setCellValue(new HSSFRichTextString("租户统计分析(截至" + (now.getYear() + 1900) + "年" + (now.getMonth() + 1) + "月"
				+ now.getDate() + "日" + now.getHours() + "点" + now.getMinutes() + "分）"));
		c0.setCellStyle(cellStyle);
		HSSFRow row1 = sheet.createRow(1);
		HSSFCell c1_0 = row1.createCell(0);
		c1_0.setCellValue(new HSSFRichTextString("物业"));
		c1_0.setCellStyle(cellStyle);
		HSSFCell c1_2 = row1.createCell(2);
		c1_2.setCellValue(new HSSFRichTextString("基本信息"));
		c1_2.setCellStyle(cellStyle);
		HSSFCell c1_7 = row1.createCell(7);
		c1_7.setCellStyle(cellStyle);
		c1_7.setCellValue(new HSSFRichTextString("财务统计"));

		HSSFRow row2 = sheet.createRow(2);
		HSSFCell c2_0 = row2.createCell(2);
		c2_0.setCellValue(new HSSFRichTextString("房数"));

		HSSFCell c2_4 = row2.createCell(4);
		c2_4.setCellValue(new HSSFRichTextString("人数"));
		HSSFCell c2_5 = row2.createCell(5);
		c2_5.setCellValue(new HSSFRichTextString("面积"));
		HSSFCell c2_6 = row2.createCell(6);
		c2_6.setCellValue(new HSSFRichTextString("月租"));

		HSSFCell c2_7 = row2.createCell(7);
		c2_7.setCellValue(new HSSFRichTextString("收押金"));

		HSSFCell c2_8 = row2.createCell(8);
		c2_8.setCellValue(new HSSFRichTextString("收租金"));

		HSSFCell c2_9 = row2.createCell(9);
		c2_9.setCellValue(new HSSFRichTextString("退押金"));

		HSSFCell c2_10 = row2.createCell(10);
		c2_10.setCellValue(new HSSFRichTextString("退租金"));

		HSSFCell c2_11 = row2.createCell(11);
		c2_11.setCellValue(new HSSFRichTextString("合计"));

		CellRangeAddress region0 = new CellRangeAddress(0, 0, 0, 9);
		CellRangeAddress region1 = new CellRangeAddress(1, 2, 0, 1);
		CellRangeAddress region2 = new CellRangeAddress(1, 1, 2, 6);
		CellRangeAddress region3 = new CellRangeAddress(1, 1, 7, 9);
		CellRangeAddress region4 = new CellRangeAddress(2, 2, 2, 3);
		sheet.addMergedRegion(region0);
		sheet.addMergedRegion(region1);
		sheet.addMergedRegion(region2);
		sheet.addMergedRegion(region3);
		sheet.addMergedRegion(region4);

		// 填充数据
		int rowCount = 1;
		for (String key : reports.keySet()) {
			List<Double> values = reports.get(key);
			HSSFRow row = sheet.createRow(rowCount + 2);

			// 序号
			HSSFCell c00 = row.createCell(0);
			c00.setCellValue(new HSSFRichTextString(rowCount + "."));
			// 小区
			HSSFCell c1 = row.createCell(1);
			c1.setCellValue(new HSSFRichTextString(key));
			c1.setCellStyle(cellStyle);
			// 房数
			HSSFCell c2 = row.createCell(2);
			c2.setCellValue(new HSSFRichTextString(
					values.get(1) == null || values.get(1) == 0 ? "0" : values.get(1).intValue() + ""));
			// 百分比
			HSSFCell c3 = row.createCell(3);
			if (values.get(0) == null || values.get(1) == null || values.get(0) == 0 || values.get(0) == 0) {
				c3.setCellValue(new HSSFRichTextString("0.0000%"));
			} else {
				double percent = values.get(1) / values.get(0) * 100;
				c3.setCellValue(new HSSFRichTextString(String.format("%.4f", percent) + "%"));
			}
			// 入住人数
			HSSFCell c4 = row.createCell(4);
			c4.setCellValue(new HSSFRichTextString(
					values.get(2) == null || values.get(2) == 0 ? "0" : values.get(2).intValue() + ""));
			// 面积
			HSSFCell c5 = row.createCell(5);
			c5.setCellValue(new HSSFRichTextString(
					values.get(3) == null || values.get(3) == 0 ? "0" : values.get(3).toString()));

			// 月租
			HSSFCell c6 = row.createCell(6);
			c6.setCellValue(new HSSFRichTextString(
					values.get(6) == null || values.get(6) == 0 ? "0" : values.get(6).toString()));
			// 收押金
			HSSFCell c7 = row.createCell(7);
			c7.setCellValue(new HSSFRichTextString(
					values.get(5) == null || values.get(5) == 0 ? "0" : values.get(5).toString()));

			// 收租金
			HSSFCell c8 = row.createCell(8);
			c8.setCellValue(new HSSFRichTextString(
					values.get(4) == null || values.get(4) == 0 ? "0" : values.get(4).toString()));

			// 退押金
			HSSFCell c9 = row.createCell(9);
			c9.setCellValue(new HSSFRichTextString(
					values.get(8) == null || values.get(8) == 0 ? "0" : values.get(8).toString()));

			// 退押金
			HSSFCell c10 = row.createCell(10);
			c10.setCellValue(new HSSFRichTextString(
					values.get(7) == null || values.get(7) == 0 ? "0" : values.get(7).toString()));

			// 合计
			double total = (values.get(4) == null ? 0 : values.get(4)) + (values.get(5) == null ? 0 : values.get(5))
					- (values.get(7) == null ? 0 : values.get(7)) + (values.get(8) == null ? 0 : values.get(8));
			HSSFCell c11 = row.createCell(11);

			c11.setCellValue(new HSSFRichTextString(String.format("%.2f", total)));
			rowCount++;
		}

		try {

			bufferedOutPut.flush();
			wb.write(bufferedOutPut);
			bufferedOutPut.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Output   is   closed ");
		} finally {

		}

	}

	// 月报表

	// 月报表
	@RequestMapping("monthReport.do")
	public String monthReport(Integer estateId, Integer year, ModelMap map) {
		Date date = new Date();
		if (year == null) {
			year = date.getYear() + 1900;
		}
		// 查询物业集合
		List<Estate> estates = prHouseService.getAllEstate();
		if (estateId == null) {
			if (estates != null && estates.size() != 0) {
				estateId = estates.get(0).getId();
			}
		}
		map.put("estates", estates);
		map.put("estateId", estateId);

		Date now = new Date();
		String title = "";
		if (now.getYear() + 1900 == year) {

			title = year + "年  营业月报汇总报表(截至" + (now.getYear() + 1900) + "年" + (now.getMonth() + 1) + "月" + now.getDate()
					+ "日" + now.getHours() + "点" + now.getMinutes() + "分）";
		} else {
			title = year + "年  营业月报汇总报表";
		}
		map.put("title", title);

		// 查找租金和押金信息
		Map<Integer, Double> monthRent = reportService.findMonthRent(year, estateId);
		Map<Integer, Double> monthDesposit = reportService.findMonthDesposit(year, estateId);
		Map<Integer, Double> monthExitRent = reportService.findMonthExitRent(year, estateId);
		Map<Integer, Double> monthExitDesposit = reportService.findMonthExitDesposit(year, estateId);
		map.put("monthRent", monthRent);
		map.put("monthDesposit", monthDesposit);
		map.put("monthExitRent", monthExitRent);
		map.put("monthExitDesposit", monthExitDesposit);
		map.put("nowYear", year);
		return "prh/report/monthReport.jsp";
	}

	// 租户统计

	// 租户分析
	@RequestMapping("renterReport.do")
	public String renterReport(Integer estateId, ModelMap map, HttpSession session) {
		Users user = (Users) session.getAttribute(Global.USER);

		Date now = new Date();
		String title = "租户统计分析(截至" + (now.getYear() + 1900) + "年" + (now.getMonth() + 1) + "月" + now.getDate() + "日"
				+ now.getHours() + "点" + now.getMinutes() + "分）";
		map.put("title", title);

		Map<String, List<Double>> reports = new HashMap<String, List<Double>>();
		// 1.房间数、入住房间数、入住人数、入住面积、实收租金、实收押金（按顺序）
		if (user != null && user.getId() != 1 && user.getEstateId() != null) {
			List<Double> result = reportService.renterReport(user.getEstateId());
			reports.put(user.getEstate().getName(), result);
		} else {
			// 查询物业集合
			List<Estate> estates = prHouseService.getAllEstate();
			for (Estate estate : estates) {
				List<Double> result = reportService.renterReport(estate.getId());
				reports.put(estate.getName(), result);
			}

		}
		map.put("reports", reports);
		return "prh/report/renterReport.jsp";
	}

	public HSSFWorkbook createWorkbook() {
		HSSFWorkbook wb = new HSSFWorkbook();

		// 创建单元格样式
		HSSFCellStyle cellStyleTitle = wb.createCellStyle();
		// 指定单元格居中对齐
		cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 指定单元格垂直居中对齐
		cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 指定当单元格内容显示不下时自动换行
		cellStyleTitle.setWrapText(true);
		// ------------------------------------------------------------------
		HSSFCellStyle cellStyle = wb.createCellStyle();
		// 指定单元格居中对齐
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 指定单元格垂直居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 指定当单元格内容显示不下时自动换行
		cellStyle.setWrapText(true);
		// ------------------------------------------------------------------
		// 设置单元格字体
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 200);
		cellStyleTitle.setFont(font);
		return wb;
	}

	public BufferedOutputStream getOutPut(String fileName, HttpServletResponse response) throws IOException {
		response.reset();
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String((fileName + ".xls").getBytes("gbk"), "iso-8859-1"));// 指定下载的文件名
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		OutputStream output = response.getOutputStream();
		BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
		return bufferedOutPut;
	}

	// 导出日记
	@RequestMapping("masterReport.do")
	public void masterReport(Integer estateId, String dateStr, HttpServletResponse res, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String fileName = "租户导出";

		List<PrhMaster> masters = prhMasterService.findMastersByEstate(estateId);

		// 获取output
		BufferedOutputStream bufferedOutPut = getOutPut(fileName, response);
		// 生成workbook
		HSSFWorkbook wb = createWorkbook();
		// 获取sheet
		HSSFSheet sheet = wb.createSheet();
		sheet.setDefaultColumnWidth(11);
		sheet.setDefaultRowHeightInPoints(20);

		// 定义单元格格式，添加单元格表样式，并添加到工作簿
		HSSFCellStyle cellStyle = wb.createCellStyle();
		// 设置单元格水平对齐类型
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 创建单元格

		HSSFRow row0 = sheet.createRow(0);

		HSSFCell cell0_0 = row0.createCell(0);
		cell0_0.setCellValue(new HSSFRichTextString("档案编号"));

		HSSFCell cell0_1 = row0.createCell(1);
		cell0_1.setCellValue(new HSSFRichTextString("楼号"));

		HSSFCell cell0_2 = row0.createCell(2);
		cell0_2.setCellValue(new HSSFRichTextString("单元"));

		HSSFCell cell0_3 = row0.createCell(3);
		cell0_3.setCellValue(new HSSFRichTextString("房间号"));

		HSSFCell cell0_4 = row0.createCell(4);
		cell0_4.setCellValue(new HSSFRichTextString("姓名"));

		HSSFCell cell0_5 = row0.createCell(5);
		cell0_5.setCellValue(new HSSFRichTextString("性别"));

		HSSFCell cell0_6 = row0.createCell(6);
		cell0_6.setCellValue(new HSSFRichTextString("证件号码"));

		HSSFCell cell0_7 = row0.createCell(7);
		cell0_7.setCellValue(new HSSFRichTextString("电话号码"));

		HSSFCell cell0_8 = row0.createCell(8);
		cell0_8.setCellValue(new HSSFRichTextString("单位"));

		HSSFCell cell0_9 = row0.createCell(9);
		cell0_9.setCellValue(new HSSFRichTextString("合同开始时间"));

		HSSFCell cell0_10 = row0.createCell(10);
		cell0_10.setCellValue(new HSSFRichTextString("合同结束时间时间"));

		HSSFCell cell0_11 = row0.createCell(11);
		cell0_11.setCellValue(new HSSFRichTextString("物业"));

		for (int i = 0; i < masters.size(); i++) {
			HSSFRow row = sheet.createRow(i + 1);

			HSSFCell cell0 = row.createCell(0);
			cell0.setCellValue(new HSSFRichTextString(masters.get(i).getFileNo()));

			HSSFCell cell1 = row.createCell(1);
			cell1.setCellValue(
					new HSSFRichTextString(masters.get(i).getPrHouse().getBuildingNo().getBuildingId() + "号楼"));

			HSSFCell cell2 = row.createCell(2);
			cell2.setCellValue(new HSSFRichTextString(masters.get(i).getPrHouse().getBuildingNo().getUnitName()));

			HSSFCell cell3 = row.createCell(3);
			cell3.setCellValue(new HSSFRichTextString(masters.get(i).getPrHouse().getRoomNo()));

			HSSFCell cell4 = row.createCell(4);
			cell4.setCellValue(new HSSFRichTextString(masters.get(i).getProfile().getName()));

			HSSFCell cell5 = row.createCell(5);
			cell5.setCellValue(new HSSFRichTextString("1".equals(masters.get(i).getProfile().getSex()) ? "男" : "女"));

			HSSFCell cell6 = row.createCell(6);
			cell6.setCellValue(new HSSFRichTextString(masters.get(i).getProfile().getIdno()));

			HSSFCell cell7 = row.createCell(7);
			cell7.setCellValue(new HSSFRichTextString(masters.get(i).getProfile().getPhone()));

			HSSFCell cell8 = row.createCell(8);
			cell8.setCellValue(new HSSFRichTextString(masters.get(i).getProfile().getCompany()));

			HSSFCell cell9 = row.createCell(9);
			cell9.setCellValue(new HSSFRichTextString(MyDateUtil.getDateFormat(masters.get(i).getBdate())));

			HSSFCell cell10 = row.createCell(10);
			cell10.setCellValue(new HSSFRichTextString(MyDateUtil.getDateFormat(masters.get(i).getEdate())));

			HSSFCell cell11 = row.createCell(11);
			cell10.setCellValue(new HSSFRichTextString(masters.get(i).getPrHouse().getEstate().getName()));
		}

		// 填充金额
		try {
			// 定义单元格格式，添加单元格表样式，并添加到工作簿

			bufferedOutPut.flush();
			wb.write(bufferedOutPut);
			bufferedOutPut.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}

	}

	// 导出对码
	@RequestMapping("printCode.do")
	public void printCode(Integer masterId, String dateStr, HttpServletResponse res, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String fileName = "导出对码";
		// 主单
		PrhMaster master = prhMasterService.findById(masterId);

		// 同住人
		List<PrhLinkman> linkMans = prhLinkManService.findAllByMaster(masterId);

		// 获取output
		BufferedOutputStream bufferedOutPut = getOutPut(fileName, response);
		// 生成workbook
		HSSFWorkbook wb = createWorkbook();
		// 获取sheet
		HSSFSheet sheet = wb.createSheet();
		sheet.setDefaultColumnWidth(11);
		sheet.setDefaultRowHeightInPoints(20);

		sheet.setColumnWidth(0, 8 * 256);
		sheet.setColumnWidth(1, 30 * 256);
		sheet.setColumnWidth(2, 20 * 256);
		sheet.setColumnWidth(3, 13 * 256);
		sheet.setColumnWidth(4, 24 * 256);

		// 定义单元格格式，添加单元格表样式，并添加到工作簿
		HSSFCellStyle cellStyle = wb.createCellStyle();

		// 设置单元格水平对齐类型
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		// 创建单元格

		HSSFRow rowhead = sheet.createRow(0);
		HSSFCell cell = rowhead.createCell(0);
		cell.setCellValue(new HSSFRichTextString("身份证对码"));
		cell.setCellStyle(cellStyle);
		CellRangeAddress region0 = new CellRangeAddress(0, 0, 0, 4);
		sheet.addMergedRegion(region0);

		HSSFRow row0 = sheet.createRow(1);
		HSSFCell cell0_0 = row0.createCell(0);
		cell0_0.setCellValue(new HSSFRichTextString("姓名"));

		HSSFCell cell0_1 = row0.createCell(1);
		cell0_1.setCellValue(new HSSFRichTextString("地址"));

		HSSFCell cell0_2 = row0.createCell(2);
		cell0_2.setCellValue(new HSSFRichTextString("身份证"));

		HSSFCell cell0_3 = row0.createCell(3);
		cell0_3.setCellValue(new HSSFRichTextString("手机"));

		HSSFCell cell0_4 = row0.createCell(4);
		cell0_4.setCellValue(new HSSFRichTextString("对码"));

		HSSFRow row1 = sheet.createRow(2);

		HSSFCell cell1_0 = row1.createCell(0);
		cell1_0.setCellValue(new HSSFRichTextString(master.getProfile().getName()));

		HSSFCell cell1_1 = row1.createCell(1);
		cell1_1.setCellValue(new HSSFRichTextString(
				master.getPrHouse().getEstate().getName() + master.getPrHouse().getBuildingNo().getBuildingId() + "号楼"
						+ master.getPrHouse().getBuildingNo().getUnitName() + master.getPrHouse().getRoomNo()));

		HSSFCell cell1_2 = row1.createCell(2);
		cell1_2.setCellValue(
				new HSSFRichTextString(MyConvertUtil.idCardReplaceWithStar(master.getProfile().getIdno())));

		HSSFCell cell1_3 = row1.createCell(3);
		cell1_3.setCellValue(
				new HSSFRichTextString(MyConvertUtil.phoneReplaceWithStar(master.getProfile().getMobile())));

		HSSFCell cell1_4 = row1.createCell(4);
		cell1_4.setCellValue(
				new HSSFRichTextString(master.getIdentityCard() != null ? master.getIdentityCard().getIdenPwd() : ""));

		for (int i = 0; i < linkMans.size(); i++) {
			if (linkMans.get(i).getDel() != null && linkMans.get(i).getDel().equals(1)) {
				continue;
			}
			HSSFRow row = sheet.createRow(i + 3);

			HSSFCell cell0 = row.createCell(0);
			cell0.setCellValue(new HSSFRichTextString(linkMans.get(i).getProfile().getName()));

			HSSFCell cell1 = row.createCell(1);
			cell1.setCellValue(new HSSFRichTextString(linkMans.get(i).getPrhMaster().getPrHouse().getEstate().getName()
					+ linkMans.get(i).getPrhMaster().getPrHouse().getBuildingNo().getBuildingId() + "号楼"
					+ linkMans.get(i).getPrhMaster().getPrHouse().getBuildingNo().getUnitName()
					+ linkMans.get(i).getPrhMaster().getPrHouse().getRoomNo()));

			HSSFCell cell2 = row.createCell(2);
			cell2.setCellValue(new HSSFRichTextString(
					MyConvertUtil.idCardReplaceWithStar(linkMans.get(i).getProfile().getIdno())));

			HSSFCell cell3 = row.createCell(3);
			cell3.setCellValue(new HSSFRichTextString(
					MyConvertUtil.phoneReplaceWithStar(linkMans.get(i).getProfile().getMobile())));

			HSSFCell cell4 = row.createCell(4);
			cell4.setCellValue(new HSSFRichTextString(
					linkMans.get(i).getIdentityCard() != null ? linkMans.get(i).getIdentityCard().getIdenPwd() : ""));

		}

		List<PrhTempLiveMan> temps = prhTempLiveManService.findByMaster(masterId);

		for (int i = 0; i < temps.size(); i++) {
			
			HSSFRow row = sheet.createRow(i + 3+linkMans.size());

			HSSFCell cell0 = row.createCell(0);
			cell0.setCellValue(new HSSFRichTextString(temps.get(i).getProfile().getName()));

			HSSFCell cell1 = row.createCell(1);
			cell1.setCellValue(new HSSFRichTextString(temps.get(i).getPrhMaster().getPrHouse().getEstate().getName()
					+ temps.get(i).getPrhMaster().getPrHouse().getBuildingNo().getBuildingId() + "号楼"
					+ temps.get(i).getPrhMaster().getPrHouse().getBuildingNo().getUnitName()
					+ temps.get(i).getPrhMaster().getPrHouse().getRoomNo()));

			HSSFCell cell2 = row.createCell(2);
			cell2.setCellValue(new HSSFRichTextString(
					MyConvertUtil.idCardReplaceWithStar(temps.get(i).getProfile().getIdno())));

			HSSFCell cell3 = row.createCell(3);
			cell3.setCellValue(new HSSFRichTextString(
					MyConvertUtil.phoneReplaceWithStar(temps.get(i).getProfile().getMobile())));

			HSSFCell cell4 = row.createCell(4);
			cell4.setCellValue(new HSSFRichTextString(
					temps.get(i).getIdenPwd() != null ? temps.get(i).getIdenPwd() : ""));

		}

		
		try {
			// 定义单元格格式，添加单元格表样式，并添加到工作簿

			bufferedOutPut.flush();
			wb.write(bufferedOutPut);
			bufferedOutPut.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}

	}

	// 导出房源
	@RequestMapping("houseReport.do")
	public void houseReport(Integer estateId, String buildingId, String buildingNoId, Integer floorId, Integer typeId,
			Integer houseNature, HttpServletResponse res, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		if ("-1".equals(buildingId) || "".equals(buildingId)) {
			buildingId = null;
		}

		if ("-1".equals(buildingNoId) || "".equals(buildingNoId)) {
			buildingNoId = null;
		}

		if ("-1".equals(houseNature) || "".equals(houseNature)) {
			houseNature = null;
		}

		if (floorId != null && floorId == -1 || "".equals(floorId)) {
			floorId = null;
		}
		if (typeId != null && -1 == typeId || "".equals(typeId)) {
			typeId = null;
		}

		String fileName = "导出房源";

		HouseCondition condition = new HouseCondition(estateId, buildingId, buildingNoId, floorId, typeId, null, null);
		condition.setHouseNature(houseNature);
		List<PrHouse> houses = prHouseService.findByCondition(condition);
		System.out.println(houses.size());

		// 获取output
		BufferedOutputStream bufferedOutPut = getOutPut(fileName, response);
		// 生成workbook
		HSSFWorkbook wb = createWorkbook();
		// 获取sheet
		HSSFSheet sheet = wb.createSheet();
		sheet.setDefaultColumnWidth(11);
		sheet.setDefaultRowHeightInPoints(20);

		// 定义单元格格式，添加单元格表样式，并添加到工作簿
		HSSFCellStyle cellStyle = wb.createCellStyle();
		// 设置单元格水平对齐类型
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		// 创建单元格

		HSSFRow rowhead = sheet.createRow(0);
		HSSFCell cellHeader = rowhead.createCell(0);
		cellHeader.setCellValue(new HSSFRichTextString("房源导出"));
		cellHeader.setCellStyle(cellStyle);
		CellRangeAddress region0 = new CellRangeAddress(0, 0, 0, 10);
		sheet.addMergedRegion(region0);

		HSSFRow row0 = sheet.createRow(1);
		HSSFCell cell0_0 = row0.createCell(0);
		cell0_0.setCellValue(new HSSFRichTextString("物业"));

		HSSFCell cell0_1 = row0.createCell(1);
		cell0_1.setCellValue(new HSSFRichTextString("楼号"));

		HSSFCell cell0_2 = row0.createCell(2);
		cell0_2.setCellValue(new HSSFRichTextString("单元"));

		HSSFCell cell0_3 = row0.createCell(3);
		cell0_3.setCellValue(new HSSFRichTextString("房号"));

		HSSFCell cell0_4 = row0.createCell(4);
		cell0_4.setCellValue(new HSSFRichTextString("楼层"));

		HSSFCell cell0_5 = row0.createCell(5);
		cell0_5.setCellValue(new HSSFRichTextString("房型"));

		HSSFCell cell0_6 = row0.createCell(6);
		cell0_6.setCellValue(new HSSFRichTextString("面积"));

		HSSFCell cell0_7 = row0.createCell(7);
		cell0_7.setCellValue(new HSSFRichTextString("地下室编号"));

		HSSFCell cell0_8 = row0.createCell(8);
		cell0_8.setCellValue(new HSSFRichTextString("房间"));

		HSSFCell cell0_9 = row0.createCell(9);
		cell0_9.setCellValue(new HSSFRichTextString("月租金"));

		HSSFCell cell0_10 = row0.createCell(10);
		cell0_10.setCellValue(new HSSFRichTextString("状态"));

		for (int i = 0; i < houses.size(); i++) {

			HSSFRow row = sheet.createRow(i + 2);
			HSSFCell cell0 = row.createCell(0);
			cell0.setCellValue(new HSSFRichTextString(houses.get(i).getEstate().getName()));

			HSSFCell cell1 = row.createCell(1);
			cell1.setCellValue(new HSSFRichTextString(houses.get(i).getBuildingNo().getBuildingId() + "号楼"));

			HSSFCell cell2 = row.createCell(2);
			cell2.setCellValue(new HSSFRichTextString(houses.get(i).getBuildingNo().getUnitName()));

			HSSFCell cell3 = row.createCell(3);
			cell3.setCellValue(new HSSFRichTextString(houses.get(i).getRoomNo()));

			HSSFCell cell4 = row.createCell(4);
			cell4.setCellValue(new HSSFRichTextString(houses.get(i).getBuildingFloor().getName()));

			HSSFCell cell5 = row.createCell(5);
			cell5.setCellValue(new HSSFRichTextString(houses.get(i).getRoomType().getName()));

			HSSFCell cell6 = row.createCell(6);
			cell6.setCellValue(new HSSFRichTextString(houses.get(i).getArea() + ""));

			HSSFCell cell7 = row.createCell(7);
			cell7.setCellValue(new HSSFRichTextString(houses.get(i).getBackPrice() + ""));

			HSSFCell cell8 = row.createCell(8);
			cell8.setCellValue(new HSSFRichTextString(houses.get(i).getRoomNum() + ""));

			HSSFCell cell9 = row.createCell(9);
			cell9.setCellValue(new HSSFRichTextString(houses.get(i).getMonthPrice() + ""));

			HSSFCell cell10 = row.createCell(10);
			cell0_10.setCellValue(new HSSFRichTextString(MyConvertUtil.getHouseState(houses.get(i).getState())));

		}

		try {
			// 定义单元格格式，添加单元格表样式，并添加到工作簿

			bufferedOutPut.flush();
			wb.write(bufferedOutPut);
			bufferedOutPut.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}

	// 导出对码
	@RequestMapping("rentersReport.do")
		public void rentersReport(Integer estateId, String buildingId, String buildingNoId,
				Integer typeId,Integer subsidyTypeId, Integer currpage,Double minArea,Double maxArea,Integer masterType,
				ModelMap map, HttpSession session, HttpServletRequest request,HttpServletResponse response) throws IOException {
			
			if ("-1".equals(buildingId)||"".equals(buildingId)) {
				buildingId = null;
			}
			
			if ("-1".equals(buildingNoId)||"".equals(buildingNoId)) {
				buildingNoId = null;
			}
			
			if ("-1".equals(subsidyTypeId)) {
				subsidyTypeId= null;
			}
			if (typeId != null && -1 == typeId||"".equals(typeId)) {
				typeId = null;
			}
			
			String fileName = "租户导出";
			
			MasterCondition condition=new MasterCondition(null, null, estateId, buildingNoId, buildingId, typeId, subsidyTypeId, minArea, maxArea);
			condition.setMasterType(masterType);
			
			List<PrhMaster> masters= prhMasterService.findRentersExport(condition);
			System.out.println(masters.size());
			
			// 获取output
			BufferedOutputStream bufferedOutPut = getOutPut(fileName, response);
			// 生成workbook
			HSSFWorkbook wb = createWorkbook();
			// 获取sheet
			HSSFSheet sheet = wb.createSheet();
			sheet.setDefaultColumnWidth(11);
			sheet.setDefaultRowHeightInPoints(20);
			sheet.setColumnWidth(1, 20 * 256);

			// 定义单元格格式，添加单元格表样式，并添加到工作簿
			HSSFCellStyle cellStyle = wb.createCellStyle();
			// 设置单元格水平对齐类型
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			// 创建单元格
			HSSFRow rowhead = sheet.createRow(0);
			HSSFCell cellHeader = rowhead.createCell(0);
			cellHeader.setCellValue(new HSSFRichTextString("租户导出"));
			cellHeader.setCellStyle(cellStyle);
			CellRangeAddress region0 = new CellRangeAddress(0, 0, 0, 17);
			sheet.addMergedRegion(region0);
			HSSFRow row0 = sheet.createRow(1);
			HSSFCell cell0_0 = row0.createCell(0);
			cell0_0.setCellValue(new HSSFRichTextString("姓名"));
			HSSFCell cell0_1 = row0.createCell(1);
			cell0_1.setCellValue(new HSSFRichTextString("身份证"));
			HSSFCell cell0_2 = row0.createCell(2);
			cell0_2.setCellValue(new HSSFRichTextString("收入等级"));

			HSSFCell cell0_3 = row0.createCell(3);
			cell0_3.setCellValue(new HSSFRichTextString("人口"));

			HSSFCell cell0_4 = row0.createCell(4);
			cell0_4.setCellValue(new HSSFRichTextString("小区名称"));
			
			HSSFCell cell0_5 = row0.createCell(5);
			cell0_5.setCellValue(new HSSFRichTextString("房间号"));
			
			HSSFCell cell0_6 = row0.createCell(6);
			cell0_6.setCellValue(new HSSFRichTextString("面积"));
			
			HSSFCell cell0_7 = row0.createCell(7);
			cell0_7.setCellValue(new HSSFRichTextString("地下室编号"));
			
			HSSFCell cell0_8 = row0.createCell(8);
			cell0_8.setCellValue(new HSSFRichTextString("保障内面积"));
			
			HSSFCell cell0_9 = row0.createCell(9);
			cell0_9.setCellValue(new HSSFRichTextString("保障外面积"));
			
			HSSFCell cell0_10 = row0.createCell(10);
			cell0_10.setCellValue(new HSSFRichTextString("应缴月租金"));
			
			HSSFCell cell0_11 = row0.createCell(11);
			cell0_11.setCellValue(new HSSFRichTextString("应缴年租金"));
			
			HSSFCell cell0_12 = row0.createCell(12);
			cell0_12.setCellValue(new HSSFRichTextString("补贴"));
			
			HSSFCell cell0_13 = row0.createCell(13);
			cell0_13.setCellValue(new HSSFRichTextString("月实缴"));
			
			HSSFCell cell0_14 = row0.createCell(14);
			cell0_14.setCellValue(new HSSFRichTextString("年实缴"));
			
			HSSFCell cell0_15 = row0.createCell(15);
			cell0_15.setCellValue(new HSSFRichTextString("电话号码"));
			
			HSSFCell cell0_16 = row0.createCell(16);
			cell0_16.setCellValue(new HSSFRichTextString("合同止日期"));
			
			HSSFCell cell0_17 = row0.createCell(17);
			cell0_17.setCellValue(new HSSFRichTextString("签约日期"));

			
			for (int i = 0; i < masters.size(); i++) {
				PrhMaster master= masters.get(i);
				PrHouse house=master.getPrHouse();
				
				HSSFRow row = sheet.createRow(i+2);
				HSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(new HSSFRichTextString(master.getProfile().getName()));

				HSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(new HSSFRichTextString(master.getProfile().getIdno()));

				HSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(new HSSFRichTextString(master.getSubsidyType().getName()));

				HSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(new HSSFRichTextString(master.getNumbs()+"" ));

				HSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(new HSSFRichTextString(master.getPrHouse().getEstate().getName()  ));
				
				HSSFCell cell5 = row.createCell(5);
				String unit = master.getPrHouse().getBuildingNo().getUnitName().substring(0, 1);
				cell5.setCellValue(new HSSFRichTextString(master.getPrHouse().getBuildingNo().getBuildingId()+"-"+unit+"-"+master.getPrHouse().getRoomNo() ));
				
				HSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(new HSSFRichTextString(master.getPrHouse().getArea()+""));
				
				HSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(new HSSFRichTextString(master.getPrHouse().getBackPrice()+""));
				
				Integer currSubsidyTypeId=master.getSubsidyTypeId();
				SubsidyCal inSubsidyCal = null;
				SubsidyCal outSubsidyCal = null;
				Double inArea= null;
				Double outArea= null;
				if (currSubsidyTypeId !=null&&currSubsidyTypeId != -1) {
					inSubsidyCal = subsidyService.findSubsidyCal(house.getEstateId(), currSubsidyTypeId, 1, master.getBdate());
					outSubsidyCal = subsidyService.findSubsidyCal(house.getEstateId(), currSubsidyTypeId, 2, master.getBdate());
					int numbs = master.getNumbs();
					
					inSubsidyCal.setArea(Double.valueOf(house.getArea().toString()));
					inSubsidyCal.setPerNum(numbs);
					outSubsidyCal.setArea(Double.valueOf(house.getArea().toString()));
					outSubsidyCal.setPerNum(numbs);
					
					 inArea= inSubsidyCal.calSafeArea();
					 outArea= inSubsidyCal.calOutSafeArea();
							
				}
				
				HSSFCell cell8 = row.createCell(8);
				cell8.setCellValue(new HSSFRichTextString(inArea!=null?inArea+"":""));
				
				HSSFCell cell9 = row.createCell(9);
				String out="";
				if (outArea!=null) {
					out=String.format("%.2f",outArea);
				}
				cell9.setCellValue(new HSSFRichTextString(out ));
				
				
				HSSFCell cell10 = row.createCell(10);
				cell10.setCellValue(new HSSFRichTextString(String.format("%.2f", house.getMonthPrice())));
				
				HSSFCell cell11 = row.createCell(11);
				cell11.setCellValue(new HSSFRichTextString(String.format("%.2f", house.getMonthPrice().doubleValue()*12) ));
				
				HSSFCell cell12 = row.createCell(12);
				cell12.setCellValue(new HSSFRichTextString(master.getSetrate().doubleValue()+""));
				
				HSSFCell cell13 = row.createCell(13);
				cell13.setCellValue(new HSSFRichTextString(String.format("%.2f",  master.getRate().doubleValue())));
				
				HSSFCell cell14 = row.createCell(14);
				cell14.setCellValue(new HSSFRichTextString(String.format("%.2f",master.getRate().doubleValue()*12) ));
				
				HSSFCell cell15 = row.createCell(15);
				cell15.setCellValue(new HSSFRichTextString(master.getProfile().getMobile()));
				
				HSSFCell cell16 = row.createCell(16);
				cell16.setCellValue(new HSSFRichTextString(MyDateUtil.getDateFormat(master.getEdate())));
				
				HSSFCell cell17 = row.createCell(17);
				cell17.setCellValue(new HSSFRichTextString(MyDateUtil.getDateFormat(master.getBdate())));

			}
			
			try {
				// 定义单元格格式，添加单元格表样式，并添加到工作簿

				bufferedOutPut.flush();
				wb.write(bufferedOutPut);
				bufferedOutPut.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {

			}
		}
	
		//财务报表
		@RequestMapping("toMoneyReport.do")	
		public String moneyReport(Integer estateId,String batch, Integer currpage, ModelMap map, HttpSession session, HttpServletRequest request){
			// 处理当前页
			if (currpage == null || currpage <= 0) {
				currpage = 1;
			}
			Integer size = 10; // 页大小
			// 查询物业集合
			List<Estate> estates = prHouseService.getAllEstate();
			if (estateId == null) {
				if (estates != null && estates.size() != 0) {
					estateId = estates.get(0).getId();
				}
			}
			map.put("estates", estates);
			map.put("estateId", estateId);
			
			
			List<String> batchs = prhMasterService.findAllBatchs(batch,estateId);
			map.put("batchs", batchs);
			
			return "prh/prMoney/moneyReport.jsp";
		}
		
		
		@RequestMapping("moneyReport.do")
		public void moneyReport(Integer estateId,String id,
				ModelMap map, HttpSession session, HttpServletRequest request,HttpServletResponse response) throws IOException {
			
			
			
			String fileName = "财务报表导出";
			
			
			List<PrhMaster> masters= prhMasterService.findMastersByBatchs(id, estateId);
			System.out.println(masters.size());
			
			// 获取output
			BufferedOutputStream bufferedOutPut = getOutPut(fileName, response);
			// 生成workbook
			HSSFWorkbook wb = createWorkbook();
			// 获取sheet
			HSSFSheet sheet = wb.createSheet();
			sheet.setDefaultColumnWidth(11);
			sheet.setDefaultRowHeightInPoints(20);
			/*sheet.setColumnWidth(1, 20 * 256);*/

			// 定义单元格格式，添加单元格表样式，并添加到工作簿
			HSSFCellStyle cellStyle = wb.createCellStyle();
			// 设置单元格水平对齐类型
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			// 创建单元格
			HSSFRow rowhead = sheet.createRow(0);
			HSSFCell cellHeader = rowhead.createCell(0);
			cellHeader.setCellValue(new HSSFRichTextString("财务报表导出"));
			cellHeader.setCellStyle(cellStyle);
			CellRangeAddress region0 = new  CellRangeAddress(0, 0, 0, 8);
			sheet.addMergedRegion(region0);
			HSSFRow row0 = sheet.createRow(1);
			HSSFCell cell0_0 = row0.createCell(0);
			cell0_0.setCellValue(new HSSFRichTextString("序号"));
			HSSFCell cell0_1 = row0.createCell(1);
			cell0_1.setCellValue(new HSSFRichTextString("物业"));
			HSSFCell cell0_2 = row0.createCell(2);
			cell0_2.setCellValue(new HSSFRichTextString("房号"));

			HSSFCell cell0_3 = row0.createCell(3);
			cell0_3.setCellValue(new HSSFRichTextString("姓名"));

			HSSFCell cell0_4 = row0.createCell(4);
			cell0_4.setCellValue(new HSSFRichTextString("合同起始日期"));
			
			HSSFCell cell0_5 = row0.createCell(5);
			cell0_5.setCellValue(new HSSFRichTextString("合同截止日期"));
			
			HSSFCell cell0_6 = row0.createCell(6);
			cell0_6.setCellValue(new HSSFRichTextString("发票号"));
			
			HSSFCell cell0_7 = row0.createCell(7);
			cell0_7.setCellValue(new HSSFRichTextString("批次号"));
			
			HSSFCell cell0_8 = row0.createCell(8);
			cell0_8.setCellValue(new HSSFRichTextString("租金"));
			
			
			
			for (int i = 0; i < masters.size(); i++) {
				PrhMaster master= masters.get(i);
				PrHouse house=master.getPrHouse();
				
				HSSFRow row = sheet.createRow(i+2);
				HSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(new HSSFRichTextString((i+1)+""));

				HSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(new HSSFRichTextString(master.getPrHouse().getEstate().getName()));

				HSSFCell cell2 = row.createCell(2);
				String unit = master.getPrHouse().getBuildingNo().getUnitName().substring(0, 1);
				cell2.setCellValue(new HSSFRichTextString(master.getPrHouse().getBuildingNo().getBuildingId()+"-"+unit+"-"+master.getPrHouse().getRoomNo() ));

				HSSFCell cell3 = row.createCell(3);
				
				cell3.setCellValue(new HSSFRichTextString(master.getProfile().getName()));
						

				HSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(new HSSFRichTextString(MyDateUtil.getDateFormat(master.getBdate())));
				
				HSSFCell cell5 = row.createCell(5);
				
				cell5.setCellValue(new HSSFRichTextString(MyDateUtil.getDateFormat(master.getEdate())));
				
				HSSFCell cell6 = row.createCell(6);
				List<PrhRental> rentals = renterService.findByAccnt(master.getId());
				Double price=null;
				String ino="";
				
				if (rentals!=null&&rentals.size()>1) {
					price=rentals.get(1).getCharge();
					ino=rentals.get(0).getIno();
				}
				cell6.setCellValue(new HSSFRichTextString(ino));
				HSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(new HSSFRichTextString(master.getBatch() ));
		
				HSSFCell cell8 = row.createCell(8);
				cell8.setCellValue(new HSSFRichTextString(price!=null?price+"":"" ));
				
			}
			
			try {
				// 定义单元格格式，添加单元格表样式，并添加到工作簿
				bufferedOutPut.flush();
				wb.write(bufferedOutPut);
				bufferedOutPut.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {

			}
		}
		
}
