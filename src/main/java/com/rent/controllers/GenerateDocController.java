package com.rent.controllers;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rent.common.utils.ObjectUtils;
import com.rent.common.utils.WordGenerator;
import com.rent.modules.sys.entity.Repaire;
import com.rent.modules.sys.service.SystemService;  
  
/** 
 * Servlet implementation class MyServlet 
 */  
@Controller
@RequestMapping(value = "${adminPath}/exportDoc")
public class GenerateDocController{  
    private static final long serialVersionUID = 1L;  

    @Autowired
	private SystemService systemService;
    
    
    
    @RequestMapping(value = "export1")
    protected void export1(HttpServletRequest req, HttpServletResponse resp) throws Exception{  
          
        Map<String, Object> map = new HashMap<String, Object>();  
        Repaire rep = systemService.getRepaireByID(req.getParameter("id"));
        String docName = req.getParameter("name");
        String type = req.getParameter("type");
        
        map = ObjectUtils.objToMap(rep);
      
        File file = null;  
        InputStream fin = null;  
        ServletOutputStream out = null;  
        try {  
            // ���ù�����WordGenerator��createDoc��������Word�ĵ�  
            file = WordGenerator.createDoc(map, type);  
            fin = new FileInputStream(file);  
              
            resp.setCharacterEncoding("utf-8");  
            resp.setContentType("application/msword");
            
            // ��������������صķ�ʽ������ļ�Ĭ����Ϊresume.doc  
            //resp.addHeader("Content-Disposition", "attachment;filename="+docName+".doc");
            resp.addHeader("Content-Disposition", "attachment;filename="+new String(docName.getBytes("gbk"), "iso-8859-1")+".doc");
              
            out = resp.getOutputStream();  
            byte[] buffer = new byte[512];  // ������  
            int bytesToRead = -1;
            // ͨ��ѭ���������Word�ļ�������������������  
            while((bytesToRead = fin.read(buffer)) != -1) {  
                out.write(buffer, 0, bytesToRead);
            }
            out.flush();
            resp.flushBuffer();
            // resp.flushBuffer();
        } finally {  
            if(fin != null) fin.close();
            if(out != null) out.close();  
            if(file != null) file.delete(); // ɾ����ʱ�ļ�  
        }  
    }
    
    
    
    
    
    
    
    
    @RequestMapping(value = "export")
    protected void export(HttpServletRequest req, HttpServletResponse resp) throws Exception{  
        req.setCharacterEncoding("utf-8");  
        Map<String, Object> map = new HashMap<String, Object>();  
        Repaire rep = systemService.getRepaireByID(req.getParameter("id"));
        String docName = req.getParameter("name");
        String type = req.getParameter("type");
        map = ObjectUtils.objToMap(rep);
        File file = null;  
        InputStream fin = null;  
        ServletOutputStream out = null;  
        try {  
            // ���ù�����WordGenerator��createDoc��������Word�ĵ�  
            file = WordGenerator.createDoc(map, type);  
            fin = new FileInputStream(file);  
              
            resp.setCharacterEncoding("utf-8");  
            resp.setContentType("application/msword");
            
            // ��������������صķ�ʽ������ļ�Ĭ����Ϊresume.doc  
            //resp.addHeader("Content-Disposition", "attachment;filename="+docName+".doc");
            resp.addHeader("Content-Disposition", "attachment;filename="+new String(docName.getBytes("gbk"), "iso-8859-1")+".doc");
              
            out = resp.getOutputStream();  
            byte[] buffer = new byte[512];  // ������  
            int bytesToRead = -1;
            // ͨ��ѭ���������Word�ļ�������������������  
            while((bytesToRead = fin.read(buffer)) != -1) {  
                out.write(buffer, 0, bytesToRead);
            }
            out.flush();
            resp.flushBuffer();
            // resp.flushBuffer();
        } finally {  
            if(fin != null) fin.close();
            if(out != null) out.close();  
            if(file != null) file.delete(); // ɾ����ʱ�ļ�  
        }  
    }
}
