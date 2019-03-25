package com.rent.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author liwenxiang
 * Date:2019/3/25
 * Time:11:28
 */
public class HMACSHA256 {

    private static Log log = LogFactory.getLog(HMACSHA256.class);

    /**
     * sha256_HMAC加密
     * @param message 消息
     * @param secret  秘钥
     * @return 加密后字符串
     */
    public static String sha256_HMAC(String message, String secret) {
        String hash = "";
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(message.getBytes());
            return Base64URL.encode(bytes);
        } catch (Exception e) {
            log.error("Error HmacSHA256 ===========" + e.getMessage());
        }
        return hash;
    }

    public static void main(String[] args) throws Exception{
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("alg","HS256");
        jsonObject.put("typ","JWT");
        String data = jsonObject.toJSONString();
        String encodedStr = Base64URL.encode(data);
        System.out.println("加密后-->"+encodedStr);
        //--------
        JSONObject jsonObject1 = new JSONObject(true);
        jsonObject1.put("appkey", MjConfig.get("appkey"));
        String timestamp = String.valueOf(System.currentTimeMillis()/1000);
        System.out.println(timestamp);
        jsonObject1.put("timestamp",timestamp);
        String data1 = jsonObject1.toJSONString();
        String encodedStr1 = Base64URL.encode(data1);
        System.out.println("加密后-->"+encodedStr1);
        //--------
        String message = encodedStr+"."+encodedStr1;
        String secret = MjConfig.get("secret");
        String sig = sha256_HMAC(message, secret);
        System.out.println(sig);

    }

}
