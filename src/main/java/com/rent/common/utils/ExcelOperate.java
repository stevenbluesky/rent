package com.rent.common.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelOperate {
	public static void main(String[] args) throws Exception {
		File file = new File("e:\\ExcelDemo.xls");
		String[][] result = getData(file, 1);
		int rowLength = result.length;
		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < result[i].length; j++) {
				System.out.print(result[i][j] + "\t\t");
			}
			System.out.println();
		}
	}

	
	public static void createTable(HttpServletResponse response) throws IOException {

		// ����HSSFWorkbook����(excel���ĵ�����)
		HSSFWorkbook wb = new HSSFWorkbook();
		
		
		
		// �����µ�sheet����excel�ı���
		HSSFSheet sheet = wb.createSheet("�ɼ���");
		// ��sheet�ﴴ����һ�У�����Ϊ������(excel����)��������0��65535֮����κ�һ��
		HSSFRow row1 = sheet.createRow(0);
		// ������Ԫ��excel�ĵ�Ԫ�񣬲���Ϊ��������������0��255֮����κ�һ��
		HSSFCell cell = row1.createCell(0);
		// ���õ�Ԫ������
		cell.setCellValue("ѧԱ���Գɼ�һ����");
		 
	
	     
		 
		// �ϲ���Ԫ��CellRangeAddress����������α�ʾ��ʼ�У������У���ʼ�У� ������
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
		// ��sheet�ﴴ���ڶ���
		HSSFRow row2 = sheet.createRow(1);
		// ������Ԫ�����õ�Ԫ������
		row2.createCell(0).setCellValue("����");
		row2.createCell(1).setCellValue("�༶");
		row2.createCell(2).setCellValue("���Գɼ�");
		row2.createCell(3).setCellValue("���Գɼ�");
		// ��sheet�ﴴ��������
		HSSFRow row3 = sheet.createRow(2);
		row3.createCell(0).setCellValue("����");
		row3.createCell(1).setCellValue("As178");
		row3.createCell(2).setCellValue(87);
		row3.createCell(3).setCellValue(78);
		// .....ʡ�Բ��ִ���

		// ���Excel�ļ�
		OutputStream output = response.getOutputStream();
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename=details.xls");
		response.setContentType("application/msexcel");
		wb.write(output);
		output.close();

	}

	/**
	 * ��ȡExcel�����ݣ���һά����洢����һ���и��е�ֵ����ά����洢���Ƕ��ٸ���
	 * 
	 * @param file
	 *            ��ȡ���ݵ�ԴExcel
	 * @param ignoreRows
	 *            ��ȡ���ݺ��Ե�������������ͷ����Ҫ���� ���Ե�����Ϊ1
	 * @return ������Excel�����ݵ�����
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String[][] getData(File file, int ignoreRows) throws FileNotFoundException, IOException {
		List<String[]> result = new ArrayList<String[]>();
		int rowSize = 0;
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		// ��HSSFWorkbook
		POIFSFileSystem fs = new POIFSFileSystem(in);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFCell cell = null;
		for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
			HSSFSheet st = wb.getSheetAt(sheetIndex);
			// ��һ��Ϊ���⣬��ȡ
			for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
				HSSFRow row = st.getRow(rowIndex);
				if (row == null) {
					continue;
				}
				int tempRowSize = row.getLastCellNum() + 1;
				if (tempRowSize > rowSize) {
					rowSize = tempRowSize;
				}
				String[] values = new String[rowSize];
				Arrays.fill(values, "");
				boolean hasValue = false;
				for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
					String value = "";
					cell = row.getCell(columnIndex);
					if (cell != null) {
						// ע�⣺һ��Ҫ��������������ܻ��������

						// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_STRING:
							value = cell.getStringCellValue();
							break;
						case HSSFCell.CELL_TYPE_NUMERIC:
							if (HSSFDateUtil.isCellDateFormatted(cell)) {
								Date date = cell.getDateCellValue();
								if (date != null) {
									value = new SimpleDateFormat("yyyy-MM-dd").format(date);
								} else {
									value = "";
								}
							} else {
								value = new DecimalFormat("0.00").format(cell.getNumericCellValue());
								
							}
							break;
						case HSSFCell.CELL_TYPE_FORMULA:
							// ����ʱ���Ϊ��ʽ���ɵ���������ֵ
							if (!cell.getStringCellValue().equals("")) {
								value = cell.getStringCellValue();
							} else {
								value = cell.getNumericCellValue() + "";
							}
							break;
						case HSSFCell.CELL_TYPE_BLANK:
							break;
						case HSSFCell.CELL_TYPE_ERROR:
							value = "";
							break;
						case HSSFCell.CELL_TYPE_BOOLEAN:
							value = (cell.getBooleanCellValue() == true ? "Y" : "N");
							break;
						default:
							value = "";
						}
					}
					if (columnIndex == 0 && value.trim().equals("")) {
						break;
					}
					values[columnIndex] = rightTrim(value);
					hasValue = true;
				}

				if (hasValue) {
					result.add(values);
				}
			}
		}
		in.close();
		String[][] returnArray = new String[result.size()][rowSize];
		for (int i = 0; i < returnArray.length; i++) {
			returnArray[i] = (String[]) result.get(i);
		}
		return returnArray;
	}

	/**
	 * ȥ���ַ����ұߵĿո�
	 * 
	 * @param str
	 *            Ҫ������ַ���
	 * @return �������ַ���
	 */
	public static String rightTrim(String str) {
		if (str == null) {
			return "";
		}
		int length = str.length();
		for (int i = length - 1; i >= 0; i--) {
			if (str.charAt(i) != 0x20) {
				break;
			}
			length--;
		}
		return str.substring(0, length);
	}
}