package com.rent.door;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Description: http请求参数验证签名算法
 * @author liangjiange
 * @date 2016年7月29日 下午1:32:51
 */
public class HttpSignCaculate {

	/**
	 * 英杰物联平台生成签名的算法
	 * 
	 * @param params
	 *            请求参数
	 * @param secret
	 *            请求参数加密密钥
	 * @param signMethod
	 *            签名算法 md5或者hmac,0表示md5、1表示hmac
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException 
	 */
	public static String signEIOTRequest(Map<String, Object> params, String secret, Integer signMethod)
			throws IOException, NoSuchAlgorithmException {
		// 第一步：检查参数是否已经排序
		String[] keys = params.keySet().toArray(new String[0]);
		Arrays.sort(keys);

		// 第二步：把所有参数名和参数值串在一起
		StringBuilder query = new StringBuilder();
		if (signMethod.equals(Constants.SIGN_METHOD_MD5)) {
			query.append(secret);
		}

		for (String key : keys) {
			Object value = params.get(key);
			if (value != null && !"".equalsIgnoreCase(String.valueOf(value))) {
				query.append(key).append(value);
			}
		}
		System.out.println("query:" + query);
		// 第三步：使用MD5/HMAC加密
		byte[] bytes;
		if (signMethod.equals(Constants.SIGN_METHOD_HMAC)) {
			bytes = encryptHMAC(query.toString(), secret);
		} else {
			query.append(secret);
			bytes = encryptMD5(query.toString());
		}

		// 第四步：把二进制转化为大写的十六进制
		return byte2hex(bytes);
	}

	private static byte[] encryptHMAC(String data, String secret) throws IOException {
		byte[] bytes = null;
		try {
			SecretKey secretKey = new SecretKeySpec(secret.getBytes(Constants.CHARSET_UTF8), "HmacMD5");
			Mac mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
			bytes = mac.doFinal(data.getBytes(Constants.CHARSET_UTF8));
		} catch (GeneralSecurityException gse) {
			throw new IOException(gse.toString());
		}
		return bytes;
	}

	private static byte[] encryptMD5(String data) throws IOException, NoSuchAlgorithmException {
            MessageDigest  digest = MessageDigest.getInstance("MD5");  
            return digest.digest(data.getBytes());  
	}

	private static String byte2hex(byte[] bytes) {
		StringBuilder sign = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign.append("0");
			}
			sign.append(hex.toUpperCase());
		}
		return sign.toString();
	}

}
