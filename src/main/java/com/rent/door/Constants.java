package com.rent.door;

/**
 * @Description: 常量类
 * @author liangjiange
 * @date 2016年7月28日 上午9:23:16
 */
public class Constants {
	
	/**
	 * 请求URL
	 */
	public static final String THINGSCLOUD_API_URL = "https://thingscloud.enginhz.com:8443/thingscloud/openapi/v1/";
//	public static final String THINGSCLOUD_API_URL = "https://thingscloud.enginhz.com:8443/thingscloud/openapi/v1/card/openright/add/continuous/";
	//public static final String THINGSCLOUD_API_URL = "http://localhost:8081/thingscloud/openapi/v1/";

//	public static final String ACCESS_TOKEN = "ee72c6f312a93a2aecea433cd41d3e29";
	public static final String ACCESS_TOKEN = "16e3acdb9fbfdc492fbe86b0926d5a31";


	/**
	 * 采用md5签名算法
	 */
	public static final Integer SIGN_METHOD_MD5 = 0;

	/**
	 * 采用hmac签名算法
	 */
	public static final Integer SIGN_METHOD_HMAC = 1;

	public static final String CHARSET_UTF8 = "UTF-8";

//	public static final String SECRET_KEY = "bad52d6f97c62fe8575a550e8535584d";
	public static final String SECRET_KEY = "bad52d6f97c62fe8575a550e8535583b";


}
