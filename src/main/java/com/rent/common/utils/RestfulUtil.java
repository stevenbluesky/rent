package com.rent.common.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

import com.alibaba.fastjson.JSONObject;
import com.rent.common.utils.HttpUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.alibaba.fastjson.JSON;

public class RestfulUtil {

	private static Log log = LogFactory.getLog(RestfulUtil.class);
	private static RestfulUtil instance = new RestfulUtil();

	private String token = "";

	private RestfulUtil() {

	}

	public static RestfulUtil getInstance() {
		return instance ;
	}

	public static String getToken(){
		if(checkNull(instance.token)){
			/**token为空，则登录获取token*/
			instance.login();
		}
		return instance.token;
	}

	/**
	 * 调用接口
	 * @param url
	 * @param pmap
	 * @return
	 */
	public static String postToServerHttps(String url,Map pmap){
		String token = getToken();
		pmap.put("token", token);
		String str = HttpUtil.httprequest(url, pmap);
		log.info(str);
		Map map = null;
		try {
			map = JSON.parseObject(str,HashMap.class);
		} catch (Exception e) {
			throw new RuntimeException("Server connection failed");
		}
		if(checkNull(map.get("resultCode"))){
			throw new RuntimeException("System error, error code is blank");
		}
		int resultCode = Integer.parseInt(map.get("resultCode").toString());
		if(resultCode == 30300){
			/**无效的token，重新登录*/
			instance.login();
			pmap.put("token", token);
			str =postToServerHttps(url, pmap);
		}
		return str;
	}

	/**
	 * 登录
	 * @return
	 */
	public synchronized void login(){
		String code = MjConfig.get("restCode");
		String password = MjConfig.get("restPassword");

		Map<String , Object> pmap = new HashMap<String , Object>();
		pmap.put("code", code);
		pmap.put("password", password);
		String str = HttpUtil.httprequest(MjConfig.get("restUrl") + "thirdpart/login", pmap);

		Map map = null;
		try {
			map = JSON.parseObject(str,HashMap.class);
		} catch (Exception e) {
			throw new RuntimeException("Server connection failed");
		}
		if(checkNull(map.get("resultCode")) || checkNull(map.get("token"))){
			throw new RuntimeException("Login to third-party platform failed");
		}
		int resultCode = Integer.parseInt(map.get("resultCode").toString());
		if(resultCode != 0){
			throw new RuntimeException("Login to third-party platform failed");
		}
		instance.token = map.get("token").toString();
	}

	public static void main(String[] args) {
		Map<String , Object> pmap = new HashMap<String , Object>();
		pmap.put("code", MjConfig.get("restCode"));
		pmap.put("password", MjConfig.get("restPassword"));
		try{
			String str = HttpUtil.httprequest("https://dev.isurpass.com.cn/iremote/thirdpart/login", pmap);
			log.info(str);
		}catch (Exception e) {
			log.error(e.getMessage() , e);
		}
	}

	public String postHttps(String url,Map pmap){
		String str = JSON.toJSONString(pmap);
		String result = "";
		try{
			HttpsURLConnection urlCon = (HttpsURLConnection)(new URL(url)).openConnection();
			urlCon.setDoInput(true);
			urlCon.setDoOutput(true);
			urlCon.setRequestMethod("POST");
			urlCon.setUseCaches(false);
			urlCon.setRequestProperty("Content-Length", str.length() + "");
			urlCon.getOutputStream().write(str.getBytes("utf-8"));
			urlCon.getOutputStream().flush();
			urlCon.getOutputStream().close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlCon.getInputStream()));
			String line;
			while((line = in.readLine()) != null){
				result += line;
			}
		}catch (Exception e) {
			log.error(e.getMessage() , e);
		}
		return result;
	}

	public static boolean checkNull(Object obj) {
		return (obj == null) || (obj.toString().length() == 0);
	}
	//TODO 待调用
	public static void checkResult(String str)
	{
		JSONObject resultMap = null;
		try {
			resultMap = JSON.parseObject(str);
		} catch (Exception e) {
			throw new RuntimeException("服务器连接失败");
		}
		if(checkNull(resultMap.getString("resultCode"))){
			throw new RuntimeException("系统错误，错误代码为空");
		}
		int resultCode = resultMap.getIntValue("resultCode");
		if(resultCode != 0 &&  resultCode != 30580){
			throw new RuntimeException("系统错误，错误代码为"+resultCode);
		}
	}
}
