package com.rent.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import javax.net.ssl.SSLContext;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtil {

	private static Log log = LogFactory.getLog(HttpUtil.class);
	
	public static String httpGet(String url , JSONObject parameter , JSONObject header) {
		try {
			if ( log.isInfoEnabled() ) {
				log.info(parameter.toJSONString());
			}
			StringBuffer sb = new StringBuffer();
			if ( parameter != null ) {
				for (String key : parameter.keySet()) {
					if (sb.length() != 0) {
						sb.append("&");
					}
					sb.append(key).append("=").append(URLEncoder.encode(parameter.getString(key), "UTF-8"));
				}
			}
			if ( url.contains("?")) {
				url += "&" + sb.toString();
			} else {
				url += "?" + sb.toString();
			}
			HttpClient httpclient = createHttpClient(url);

			HttpGet httpget = new HttpGet(url);
						
			if ( header != null ) {
				for (String key : header.keySet()) {
					httpget.addHeader(key, header.getString(key));
				}
			}
			HttpResponse response = httpclient.execute(httpget);
						
			HttpEntity entity = response.getEntity();

			String rst = EntityUtils.toString(entity , "UTF-8");
			log.info(rst);
			
			return rst;
		}
		catch(Throwable t) {
			log.error(t.getMessage(), t);
		}
		finally {
			
		}
		return "";
	}
	
	public static String httprequest(String url , String body,String authorization) {
		HttpClient httpclient = createHttpClient(url);
		log.info(url);
		log.info(body);
		
		try {
			HttpPost httpost = new HttpPost(url);
			if(StringUtils.isNotBlank(authorization)){
				httpost.setHeader("Authorization",authorization);
			}
			httpost.setEntity(new StringEntity(body , "UTF-8"));
			HttpResponse response = httpclient.execute(httpost);
			
			HttpEntity entity = response.getEntity();

			String rst = EntityUtils.toString(entity , "UTF-8");
			log.info(rst);
			
			return rst;
	
		}
		catch(Throwable t) {
			log.error(t.getMessage(), t);
		}
		finally {
			
		}
		return "";
	}
	
	public static String httprequest(String url , Map pmap) {
		JSONObject json = JSON.parseObject(JSON.toJSONString(pmap));
		HttpClient httpclient = createHttpClient(url);
		log.info(url);

		try {
			if ( log.isInfoEnabled() ) {
				log.info(json.toJSONString());
			}
			HttpPost httpost = new HttpPost(url);
			List <NameValuePair> nvps = new ArrayList <NameValuePair>();

			for ( String key : json.keySet()) {
				nvps.add(new BasicNameValuePair(key, json.getString(key)));
			}
			httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			HttpResponse response = httpclient.execute(httpost);

			HttpEntity entity = response.getEntity();

			String rst = EntityUtils.toString(entity , "UTF-8");
			log.info(rst);

			return rst;
		}
		catch(Throwable t) {
			log.error(t.getMessage(), t);
		}
		finally {
			
		}
		return "";
	}
	
	public static HttpClient createHttpClient(String url) {
		if ( url.startsWith("https")) {
			return createSSLClientDefault();
		} else {
			return HttpClientBuilder.create().build();
		}
	}
	
	public static CloseableHttpClient createSSLClientDefault() {
		try {
             SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new AnyTrustStrategy()).build();
             SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
             return HttpClients.custom().setSSLSocketFactory(sslsf).build();
         } catch (KeyManagementException e) {
             e.printStackTrace();
         } catch (NoSuchAlgorithmException e) {
             e.printStackTrace();
         } catch (KeyStoreException e) {
             e.printStackTrace();
         }
         return  HttpClients.createDefault();
	}

}
