package com.rent.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rent.common.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.rent.entity.PrHouse;
import com.rent.entity.PrhMaster;
import com.rent.entity.PrhRental;
import com.rent.modules.sys.entity.Repaire;
import com.rent.modules.sys.service.SystemService;
import com.rent.services.PrHouseService;
import com.rent.services.PrhMasterService;
import com.rent.services.RenterService;

@Controller("middleController")
public class MiddleController {
	@Autowired
	private PrhMasterService prhMasterService;

	@Autowired
	private PrHouseService prHouseService;
	@Autowired
	private RenterService renterService;
	
	
	
	public RenterService getRenterService() {
		return renterService;
	}
	public void setRenterService(RenterService renterService) {
		this.renterService = renterService;
	}
	public PrHouseService getPrHouseService() {
		return prHouseService;
	}
	public void setPrHouseService(PrHouseService prHouseService) {
		this.prHouseService = prHouseService;
	}
	public PrhMasterService getPrhMasterService() {
		return prhMasterService;
	}
	public void setPrhMasterService(PrhMasterService prhMasterService) {
		this.prhMasterService = prhMasterService;
	}


	@Autowired
	private SystemService systemService;
	 public SystemService getSystemService() {
		return systemService;
	}
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	
	@RequestMapping("leavePrint.do")
    public void leavePrint(HttpServletRequest req, HttpServletResponse resp) throws Exception{  
          
        Map<String, Object> map = new HashMap<String, Object>();  
        
        PrhMaster master = prhMasterService.findById(Integer.valueOf(req.getParameter("id")));
        
        String docName = StringUtils.isBlank(req.getParameter("name"))?"退租单":req.getParameter("name");
        String type = req.getParameter("type");
        
        System.out.println("id"+req.getParameter("id"));
        System.out.println("name"+docName);
        System.out.println("type"+type);
        map = ObjectUtils.objToMap(master);
      
        File file = null;  
        InputStream fin = null;  
        ServletOutputStream out = null;  
        try {  
            // 调用工具类WordGenerator的createDoc方法生成Word文档  
            file = WordGenerator.createDoc(map, type);  
            fin = new FileInputStream(file);  
              
            resp.setCharacterEncoding("utf-8");  
            resp.setContentType("application/msword");
            
            // 设置浏览器以下载的方式处理该文件默认名为resume.doc  
			resp.addHeader("Content-Disposition", "attachment;filename="+new String(docName.getBytes("gbk"), "iso-8859-1")+".doc");
            out = resp.getOutputStream();  
            byte[] buffer = new byte[512];  // 缓冲区  
            int bytesToRead = -1;
            // 通过循环将读入的Word文件的内容输出到浏览器中  
            while((bytesToRead = fin.read(buffer)) != -1) {  
                out.write(buffer, 0, bytesToRead);
            }
            out.flush();
            resp.flushBuffer();
            // resp.flushBuffer();
        } finally {  
            if(fin != null) fin.close();
            if(out != null) out.close();  
            if(file != null) file.delete(); // 删除临时文件  
        }  
    }
	
	
	
	
	
	@RequestMapping("createContract.do")
	    public void createContract(HttpServletRequest req, HttpServletResponse resp) throws Exception{  
	        Map<String, Object> map = new HashMap<String, Object>();  
	        PrhMaster master = prhMasterService.findById(Integer.valueOf(req.getParameter("id")));
	        
	        master.setRentMonth(MyDateUtil.getDateNum(master.getBdate(), master.getEdate())+1);
	        
	        List<PrhRental> rentals = renterService.findByAccnt(master.getId());
	        Double realPrice= 0.0;
	        double setRates=0.0;
	        double shouldPrice=0.0;
	        
	        
	        for (PrhRental rental : rentals) {
	        	
	        	realPrice+= rental.getRate()!=null?rental.getRate():0.0;
	        	double setRate= rental.getCharge1()!=null?rental.getCharge1().doubleValue():0;
	        	setRates+=setRate;
	        	
	        	if (rental.getInumber()>0) {
					shouldPrice+=rental.getCharge2()!=null?rental.getCharge2().doubleValue():0.0;
				}
			}

	        
	        
	        master.setRealPrice(realPrice);
	        master.setShouldPrice(shouldPrice);
	        
	        
	        master.setSetrate(BigDecimal.valueOf(setRates));
	        
	        //补贴小于应收
	        if (setRates<shouldPrice) {
	        	
				master.setLogName(String.format("%.2f", setRates)+"元");
	        	//补贴小于应收
				System.out.println(master.getLogName());
			}else{
				master.setLogName("100%");
			}
	        
	        req.setCharacterEncoding("UTF-8");
	        String docName = req.getParameter("name");
	        String type = req.getParameter("type");
	        
	        docName="合同";
	        
	        map = ObjectUtils.objToMap(master);
	      
	        File file = null;  
	        InputStream fin = null;  
	        ServletOutputStream out = null;  
	        try {  
	            // 调用工具类WordGenerator的createDoc方法生成Word文档  
	            file = WordGenerator.createDoc(map, type);  
	            fin = new FileInputStream(file);  
	              
	            resp.setCharacterEncoding("utf-8");  
	            resp.setContentType("application/msword");
	            
	            // 设置浏览器以下载的方式处理该文件默认名为resume.doc  
	            resp.addHeader("Content-Disposition", "attachment;filename="+new String(docName.getBytes("gbk"), "iso-8859-1")+".doc");  
	              
	            out = resp.getOutputStream();  
	            byte[] buffer = new byte[512];  // 缓冲区  
	            int bytesToRead = -1;
	            // 通过循环将读入的Word文件的内容输出到浏览器中  
	            while((bytesToRead = fin.read(buffer)) != -1) {  
	                out.write(buffer, 0, bytesToRead);
	            }
	            out.flush();
	            resp.flushBuffer();
	            // resp.flushBuffer();
	        } finally {  
	            if(fin != null) fin.close();
	            if(out != null) out.close();  
	            if(file != null) file.delete(); // 删除临时文件  
	        }  
	    }
	
	
	
	
	// 根据身份证前两位获取省份
	@RequestMapping("getProvince.do")
	@ResponseBody
	public String getProvince(int proId, ModelMap map) {
		return MyConvertUtil.getProvince(proId);
	}

	// 根据身份证前两位获取省份
	@RequestMapping("uploadMasterPhoto.do")
	@ResponseBody
	public String [] uploadMasterPhoto(ModelMap map,HttpServletRequest reuqest,HttpSession session) {
		String oldPath="c:\\test\\photo.jpg";
		// 新文件名
		String generateNo = GenerateSequenceUtil.generateSequenceNo();
		String newfilename = generateNo + ".jpg" ;
		// 获取文件夹路径
		String path = reuqest.getSession().getServletContext().getRealPath("static/photo");
		copyFile(oldPath, path+"\\"+newfilename);
		
		
		String oldPath1="c:\\test\\card.jpg";
		// 新文件名
		String generateNo1 = GenerateSequenceUtil.generateSequenceNo();
		String newfilename1 = generateNo1 + ".jpg" ;
		// 获取文件夹路径
		String path1 = reuqest.getSession().getServletContext().getRealPath("static/photo");
		copyFile(oldPath1, path1+"\\"+newfilename1);
		String [] fileName={newfilename,newfilename1};
		return fileName;
	}
	
	
	/*	/
		@RequestMapping("getPicIdentity.do")
		@ResponseBody	
	*/
	
	
	/** 
     * 复制单个文件 
     * @param oldPath String 原文件路径 如：c:/fqf.txt 
     * @param newPath String 复制后路径 如：f:/fqf.txt 
     * @return boolean 
     */ 
   public void copyFile(String oldPath, String newPath) { 
       try { 
           int bytesum = 0; 
           int byteread = 0; 
           File oldfile = new File(oldPath); 
           if (oldfile.exists()) { //文件存在时 
               InputStream inStream = new FileInputStream(oldPath); //读入原文件 
               FileOutputStream fs = new FileOutputStream(newPath); 
               byte[] buffer = new byte[1444]; 
               int length; 
               while ( (byteread = inStream.read(buffer)) != -1) { 
                   bytesum += byteread; //字节数 文件大小 
                   System.out.println(bytesum); 
                   fs.write(buffer, 0, byteread); 
               } 
               inStream.close(); 
           } 
       } 
       catch (Exception e) { 
           System.out.println("复制单个文件操作出错"); 
           e.printStackTrace(); 

       } 

   } 
   
   
	// 根据身份证前两位获取省份
	@RequestMapping("test6.do")
	public String uploadMaster(ModelMap map,HttpServletRequest reuqest,HttpSession session) {
	     File f=new File("d:\\photo.jpg");
	     MultipartFile file=(MultipartFile)f;
	      String path = reuqest.getSession().getServletContext().getRealPath("static/photo");
	      File targetFile = new File(path,"xxx.jpg");
	      
	    //保存  
	      try {  
	          file.transferTo(targetFile);  
	      } catch (Exception e) {  
	          e.printStackTrace();  
	      }
	      
	      
		return "";
	}
   
	// 根据身份证前两位获取省份
		@RequestMapping("test.do")
		
		public String test9(){
		
			return "sys/test.jsp";
		}
	
	
		
   public static void main(String[] args) {
	   String oldPath="c:\\test\\photo.jpg";
		// 新文件名
		String newfilename = GenerateSequenceUtil.generateSequenceNo() + ".jpg" ;
		// 获取文件夹路径
		 
		String newPath="E:\\aa.jpg";
		
		new MiddleController().copyFile(oldPath,newPath);
}

}
