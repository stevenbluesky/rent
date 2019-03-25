package com.rent.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Map;

public class RestfulUtil {

	private static Log log = LogFactory.getLog(RestfulUtil.class);
	private static RestfulUtil instance = new RestfulUtil();

	private RestfulUtil() {}

	public static RestfulUtil getInstance() {
		return instance ;
	}

	public static void main(String[] args) {
		String json = "{\"method\":\"thing.service.GetNodeList\",\"params\":{\"Conditions\":{\"_nodetype\":\"lock\"}}}";
		String authorization = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhcHBrZXkiOiJpU3VycGFzc0FwcCIsInRpbWVzdGFtcCI6IjEyMzQ1Njc4OTAifQ.KRLkB8N-3w-_tkUfUbHbZCBtZrHuOwKbmQeNK1w08_0";
		try{
			String str = HttpUtil.httprequest("http://192.168.5.10:8888/app", json,authorization);
			log.info(str);
		}catch (Exception e) {
			log.error(e.getMessage() , e);
		}
	}

	public static String getAuthorization(){
		JSONObject jsonObject = new JSONObject(true);
		jsonObject.put("alg","HS256");
		jsonObject.put("typ","JWT");
		String data = jsonObject.toJSONString();
		String encodedStr = null;
		try {
			encodedStr = Base64URL.encode(data);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//--------
		JSONObject jsonObject1 = new JSONObject(true);
		jsonObject1.put("appkey", MjConfig.get("appkey"));
		String timestamp = String.valueOf(System.currentTimeMillis()/1000);
		jsonObject1.put("timestamp",timestamp);
		String data1 = jsonObject1.toJSONString();
		String encodedStr1 = null;
		try {
			encodedStr1 = Base64URL.encode(data1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//--------
		String message = encodedStr+"."+encodedStr1;
		String secret = MjConfig.get("secret");
		String signature = HMACSHA256.sha256_HMAC(message, secret);
		return "Bearer "+encodedStr+"."+encodedStr1+"."+signature;
	}

	public static String postHttps(String json,String project){
		try{
			String authorization = getAuthorization();
			String str = HttpUtil.httprequest(MjConfig.get("tcptype")+"://"+MjConfig.get("tcpHost")+":"+MjConfig.get("tcpPort")+"/"+project, json, authorization);
			log.info(str);
			return str;
		}catch (Exception e) {
			log.error(e.getMessage() , e);
			return "{\"resultcode\":-1}";
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

}
