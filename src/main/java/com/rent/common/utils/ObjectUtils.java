/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rent.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 对象操作工具, 继承org.apache.commons.lang3.ObjectUtils
 * @author ThinkGem
 * @version 2014-6-29
 */
public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils {

	/**
	 * 注解到对象复制，只复制能匹配上的方法
	 * @param annotation
	 * @param object
	 */
	public static void annotationToObject(Object annotation, Object object){
		if (annotation != null){
			Class<?> annotationClass = annotation.getClass();
			if (null == object) {
				return;
			}
			Class<?> objectClass = object.getClass();
			for (Method m : objectClass.getMethods()){
				if (StringUtils.startsWith(m.getName(), "set")){
					try {
						String s = StringUtils.uncapitalize(StringUtils.substring(m.getName(), 3));
						Object obj = annotationClass.getMethod(s).invoke(annotation);
						if (obj != null && !"".equals(obj.toString())){
							m.invoke(object, obj);
						}
					} catch (Exception e) {
						// 忽略有设置失败
					}
				}
			}
		}
	}
	
	/**
	 * 序列化对
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			if (object != null){
				baos = new ByteArrayOutputStream();
				oos = new ObjectOutputStream(baos);
				oos.writeObject(object);
				return baos.toByteArray();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 反序列化对象
	 * @param bytes
	 * @return
	 */
	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			if (bytes != null && bytes.length > 0){
				bais = new ByteArrayInputStream(bytes);
				ObjectInputStream ois = new ObjectInputStream(bais);
				return ois.readObject();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将对象转成map
	 * @param obj
	 * @return
	 */
	public static Map<String,Object> objToMap(Object obj) {
		if(obj == null){    
            return null;    
        }   
  
        Map<String, Object> map = new HashMap<String, Object>();    
        try{
        	Field[] declaredFields = obj.getClass().getDeclaredFields();    
        	for (Field field : declaredFields) {    
        		field.setAccessible(true);
        		Object value = field.get(obj);
        		if(value != null && value.toString().length() > 0 ){
        			map.put(field.getName(), field.get(obj));
        		}else{
        			map.put(field.getName(), defaultValue(field));
        		}
        	}
        }catch(Exception e){
        	return map;
        }
        return map;
	}
	
	public static Object defaultValue(Field field){
		String type = field.getGenericType().toString();
		if(type.equals("class java.lang.String")){
			return "";
		}
		if(type.equals("class java.util.Date")){
			return new Date();
		}
		if(type.equals("class java.lang.Boolean")){
			return false;
		}
		if(type.equals("class java.lang.Integer")){
			return 0;
		}
		if(type.equals("class java.lang.Double")){
			return 0.0;
		}
		if(type.equals("class java.lang.Long")){
			return 0;
		}
		if(type.equals("class java.lang.Float")){
			return 0.0;
		}
		return null;
	}
}
