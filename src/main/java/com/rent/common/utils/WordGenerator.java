package com.rent.common.utils;

import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.OutputStreamWriter;  
import java.io.Writer;  
import java.util.HashMap;  
import java.util.Map;  
  
import freemarker.template.Configuration;  
import freemarker.template.Template;  
  
public class WordGenerator {  
    private static Configuration configuration = null;  
    private static Map<String, Template> allTemplates = null;  
      
    static {  
        configuration = new Configuration();  
        configuration.setDefaultEncoding("utf-8");  
        configuration.setClassForTemplateLoading(WordGenerator.class, "/com/rent/common/utils");  
        allTemplates = new HashMap<>();
        try {  
            allTemplates.put("approve", configuration.getTemplate("/repairerApprove.tpl"));
            allTemplates.put("accept", configuration.getTemplate("/repairerAccept.tpl"));
            allTemplates.put("register", configuration.getTemplate("/repaireregister.tpl"));
            allTemplates.put("contract", configuration.getTemplate("/contract.ftl"));
            allTemplates.put("leave", configuration.getTemplate("/leave.ftl"));
            
        } catch (IOException e) {  
            e.printStackTrace();  
            throw new RuntimeException(e);
        }  
    }  
  
    private WordGenerator() {  
        throw new AssertionError();  
    }  
  
    public static File createDoc(Map<?, ?> dataMap, String type) {  
        String name = "temp" + (int) (Math.random() * 100000) + ".doc";  
        File f = new File(name);  
        Template t = allTemplates.get(type);  
        try {  
            
            Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");  
            t.process(dataMap, w);  
            w.close();  
        } catch (Exception ex) {  
            ex.printStackTrace();  
            throw new RuntimeException(ex);  
        }  
        return f;  
    }  
  
}  
