/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rent.common.mapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * �?单封装Jackson，实现JSON String<->Java Object的Mapper.
 * 封装不同的输出风�?, 使用不同的builder函数创建实例.
 * @author ThinkGem
 * @version 2013-11-15
 */
public class JsonMapper extends ObjectMapper {

	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(JsonMapper.class);

	private static JsonMapper mapper;

	public JsonMapper() {
		this(Include.NON_EMPTY);
	}

	public JsonMapper(Include include) {
		// 设置输出时包含属性的风格
		if (include != null) {
			this.setSerializationInclusion(include);
		}
		// 允许单引号�?�允许不带引号的字段名称
		this.enableSimple();
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属�?
		this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 空�?�处理为空串
		this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>(){
			@Override
			public void serialize(Object value, JsonGenerator jgen,
					SerializerProvider provider) throws IOException,
					JsonProcessingException {
				jgen.writeString("");
			}
        });
		// 进行HTML解码�?
		this.registerModule(new SimpleModule().addSerializer(String.class, new JsonSerializer<String>(){
			@Override
			public void serialize(String value, JsonGenerator jgen,
					SerializerProvider provider) throws IOException,
					JsonProcessingException {
				jgen.writeString(StringEscapeUtils.unescapeHtml4(value));
			}
        }));
		// 设置时区
		this.setTimeZone(TimeZone.getDefault());//getTimeZone("GMT+8:00")
	}

	/**
	 * 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper,建议在外部接口中使用.
	 */
	public static JsonMapper getInstance() {
		if (mapper == null){
			mapper = new JsonMapper().enableSimple();
		}
		return mapper;
	}

	/**
	 * 创建只输出初始�?�被改变的属性到Json字符串的Mapper, �?节约的存储方式，建议在内部接口中使用�?
	 */
	public static JsonMapper nonDefaultMapper() {
		if (mapper == null){
			mapper = new JsonMapper(Include.NON_DEFAULT);
		}
		return mapper;
	}
	
	/**
	 * Object可以是POJO，也可以是Collection或数组�??
	 * 如果对象为Null, 返回"null".
	 * 如果集合为空集合, 返回"[]".
	 */
	public String toJson(Object object) {
		try {
			return this.writeValueAsString(object);
		} catch (IOException e) {
			logger.warn("write to json string error:" + object, e);
			return null;
		}
	}

	/**
	 * 反序列化POJO或简单Collection如List<String>.
	 * 
	 * 如果JSON字符串为Null�?"null"字符�?, 返回Null.
	 * 如果JSON字符串为"[]", 返回空集�?.
	 * 
	 * 如需反序列化复杂Collection如List<MyBean>, 请使用fromJson(String,JavaType)
	 * @see #fromJson(String, JavaType)
	 */
	public <T> T fromJson(String jsonString, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		try {
			return this.readValue(jsonString, clazz);
		} catch (IOException e) {
			logger.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}

	/**
	 * 反序列化复杂Collection如List<Bean>, 先使用函數createCollectionType构�?�类�?,然后调用本函�?.
	 * @see #createCollectionType(Class, Class...)
	 */
	@SuppressWarnings("unchecked")
	public <T> T fromJson(String jsonString, JavaType javaType) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		try {
			return (T) this.readValue(jsonString, javaType);
		} catch (IOException e) {
			logger.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}

	/**
	 * 構�?�泛型的Collection Type�?:
	 * ArrayList<MyBean>, 则调用constructCollectionType(ArrayList.class,MyBean.class)
	 * HashMap<String,MyBean>, 则调�?(HashMap.class,String.class, MyBean.class)
	 */
	public JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return this.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}

	/**
	 * 當JSON裡只含有Bean的部分屬性時，更新一個已存在Bean，只覆蓋該部分的屬�??.
	 */
	@SuppressWarnings("unchecked")
	public <T> T update(String jsonString, T object) {
		try {
			return (T) this.readerForUpdating(object).readValue(jsonString);
		} catch (JsonProcessingException e) {
			logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
		} catch (IOException e) {
			logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
		}
		return null;
	}

	/**
	 * 輸出JSONP格式數據.
	 */
	public String toJsonP(String functionName, Object object) {
		return toJson(new JSONPObject(functionName, object));
	}

	/**
	 * 設定是否使用Enum的toString函數來讀寫Enum,
	 * 為False時時使用Enum的name()函數來讀寫Enum, 默認為False.
	 * 注意本函數一定要在Mapper創建�?, �?有的�?寫動作之前調�?.
	 */
	public JsonMapper enableEnumUseToString() {
		this.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
		this.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
		return this;
	}

	/**
	 * 支持使用Jaxb的Annotation，使得POJO上的annotation不用与Jackson耦合�?
	 * 默认会先查找jaxb的annotation，如果找不到再找jackson的�??
	 */
	public JsonMapper enableJaxbAnnotation() {
		JaxbAnnotationModule module = new JaxbAnnotationModule();
		this.registerModule(module);
		return this;
	}

	/**
	 * 允许单引�?
	 * 允许不带引号的字段名�?
	 */
	public JsonMapper enableSimple() {
		this.configure(Feature.ALLOW_SINGLE_QUOTES, true);
		this.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		return this;
	}
	
	/**
	 * 取出Mapper做进�?步的设置或使用其他序列化API.
	 */
	public ObjectMapper getMapper() {
		return this;
	}

	/**
	 * 对象转换为JSON字符�?
	 * @param object
	 * @return
	 */
	public static String toJsonString(Object object){
		return JsonMapper.getInstance().toJson(object);
	}
	
	/**
	 * JSON字符串转换为对象
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	public static Object fromJsonString(String jsonString, Class<?> clazz){
		return JsonMapper.getInstance().fromJson(jsonString, clazz);
	}
	
	/**
	 * 测试
	 */
	public static void main(String[] args) {
		List<Map<String, Object>> list = Lists.newArrayList();
		Map<String, Object> map = Maps.newHashMap();
		map.put("id", 1);
		map.put("pId", -1);
		map.put("name", "根节�?");
		list.add(map);
		map = Maps.newHashMap();
		map.put("id", 2);
		map.put("pId", 1);
		map.put("name", "你好");
		map.put("open", true);
		list.add(map);
		String json = JsonMapper.getInstance().toJson(list);
		System.out.println(json);
	}
	
}
