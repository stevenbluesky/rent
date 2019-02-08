package aaa;

import com.rent.common.utils.MyDateUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
 
 public class Test {
 
     private Configuration configuration = null;
   
 
     public Test() {
         configuration = new Configuration();
         configuration.setDefaultEncoding("UTF-8");
     }
 
     public void createWord() {
         Map<String, Object> dataMap = new HashMap<String, Object>();
        try {
            	String id="1";
            	String name="xxx";
            	String path="";
				FileInputStream fs = new FileInputStream(path);
				InputStream fis = new BufferedInputStream(new FileInputStream(path));
				fs.close();
				
            	String bDate="2012-1-1";
            	String eDate="2012-1-1";
                 dataMap.put("id", id);
                 dataMap.put("name", name);
                 dataMap.put("bDate", bDate);
                 dataMap.put("eDate", eDate);
                 
                 configuration.setClassForTemplateLoading(this.getClass(), "/aaa"); // FTL文件所存在的位置
                 Template template = configuration.getTemplate("xxx.ftl");

                 File outFile = new File("E:/Doc1.doc");
                 Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));
                 template.process(dataMap, out);
                 out.close();
                 
           
             
         } catch (Exception e) {
            e.printStackTrace();
        }
     }

     public static void main(String[] args) {
    	 Date date=new Date("2017/1/30");
    	 Calendar c = Calendar.getInstance();
    	 c.setTime(date);
    	 c.add(Calendar.MONTH, 1);

    	 Date newDate = c.getTime();
    	 System.out.println(MyDateUtil.getDateFormat(newDate) );
 	}
}