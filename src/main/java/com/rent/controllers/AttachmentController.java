package com.rent.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.rent.common.utils.ExcelExport;
import com.rent.common.utils.ExcelOperate;
import com.rent.common.utils.FileUtils;
import com.rent.common.utils.excel.ExportExcel;
import com.rent.entity.Attachment;
import com.rent.services.AttachmentService;

@Controller
public  class AttachmentController {

	@Autowired
	private AttachmentService attachmentService;

	public AttachmentService getAttachmentService() {
		return attachmentService;
	}

	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	// 转到附件管理
	@RequestMapping("toAttachment.do")
	public String toAttachment(Integer masterId, ModelMap map, HttpSession session) {
		// 附件集合
		List<Attachment> attachments = attachmentService.findByMaster(masterId);
		map.put("attachments", attachments);

		map.put("masterId", masterId);
		return "renter/checkInRecord/attachment.jsp";
	}

	// 上传附件
	@RequestMapping("uploadAttachment.do")
	@Transactional(readOnly = false)
	public String uploadAttachment(Attachment attachment,
			@RequestParam(value = "file", required = false) MultipartFile file, ModelMap map, HttpSession session,
			HttpServletRequest request) {
		boolean isSavedFile = false;
		// 上传文件
		if (file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
			// 获取文件名
			String fileName = file.getOriginalFilename();
			int z = fileName.lastIndexOf(".");

			// 获取后缀
			String houzui = fileName.substring(z + 1);

			String oldName = fileName.substring(0, fileName.lastIndexOf("."));
			// 新文件名
			String str = new Date().getTime() + "" + new Random().nextInt();

			String newfilename = oldName + "^#$" + str + "." + houzui;
			attachment.setName(newfilename);

			String path = request.getSession().getServletContext().getRealPath("static/attachment");
			System.out.println(path);
			// 创建目标文件
			File targetFile = new File(path, newfilename);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
				System.out.println("创建文件成功");

			}
			// 保存
			try {
				file.transferTo(targetFile);
				isSavedFile = true;
				System.out.println("保存成功");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		// 文件上传成功
		if (isSavedFile) {

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			attachmentService.add(attachment);
		} else {
			map.put("tip", "文件上传失败");
		}

		return "../toAttachment.do?masterId=" + attachment.getMasterId();
	}

	// 删除文件
	@RequestMapping("delAttachment.do")
	@Transactional(readOnly = false)
	public String delAttachment(Integer[] chk, Integer masterId, ModelMap map, HttpServletRequest request) {
		System.out.println("进入删除");
		String root = request.getSession().getServletContext().getRealPath("static/attachment");
		for (Integer i : chk) {
			Attachment attachment = attachmentService.findById(i);
			String path = root + "/" + attachment.getName();
			boolean flag = FileUtils.deleteFile(path);
			if (flag) {
				attachmentService.del(i);
			} else {
				map.put("tip", "删除失败");
			}
		}
		
		return "../toAttachment.do?masterId=" + masterId;
	}

	// 下载
	@RequestMapping("downloadAttachment.do")
	public void download(Integer id, HttpServletResponse res, HttpServletRequest request) throws IOException {
		OutputStream os = res.getOutputStream();
		try {
			Attachment attachment = attachmentService.findById(id);

			String root = request.getSession().getServletContext().getRealPath("static/attachment");

			res.reset();
			res.setHeader("Content-Disposition",
					"attachment; filename=" + new String(attachment.getName().getBytes("gbk"), "iso-8859-1"));
			res.setContentType("application/octet-stream; charset=utf-8");

			File downFiles = new File(root + "/" + attachment.getRealName());
			os.write(FileUtils.readFileToByteArray(downFiles));
			os.flush();
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}

	// 登录页下载组建
	@RequestMapping("downloadActiveDll.do")
	public void downloadActiveDll(HttpServletResponse res, HttpServletRequest request) throws IOException {
		OutputStream os = res.getOutputStream();
		try {

			String root = request.getSession().getServletContext().getRealPath("static/download");

			res.reset();
			res.setHeader("Content-Disposition",
					"attachment; filename=" + new String("ActiveDll组件.rar".getBytes("gbk"), "iso-8859-1"));
			res.setContentType("application/octet-stream;charset=utf-8");

			File downFiles = new File(root + "/ActiveDll组件.rar");
			os.write(FileUtils.readFileToByteArray(downFiles));
			os.flush();
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}

	// 登录页下载组建
	@RequestMapping("downloadHouse.do")
	public void downloadHouse(HttpServletResponse res, HttpServletRequest request) throws IOException {
		OutputStream os = res.getOutputStream();
		try {

			String root = request.getSession().getServletContext().getRealPath("static/download");

			res.reset();

			res.setHeader("Content-Disposition",
					"attachment; filename=" + new String("房源导入模板.xls".getBytes("gbk"), "iso-8859-1"));
			res.setContentType("application/octet-stream;charset=utf-8");

			File downFiles = new File(root + "/房源导入模板.xls");
			os.write(FileUtils.readFileToByteArray(downFiles));
			os.flush();
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}

	public BufferedOutputStream getOutPut(String fileName, HttpServletResponse response) throws IOException{
		  response.reset();  
	        response.setHeader("Content-Disposition", "attachment;filename="  
	        		+ new String((fileName+".xls").getBytes("gbk"), "iso-8859-1"));// 指定下载的文件名  
	        response.setContentType("application/vnd.ms-excel");  
	        response.setHeader("Pragma", "no-cache");  
	        response.setHeader("Cache-Control", "no-cache");  
	        response.setDateHeader("Expires", 0);  
	        OutputStream output = response.getOutputStream();  
	        BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output); 
	        return bufferedOutPut;
	}
	
	public HSSFWorkbook createWorkbook(){
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

}
