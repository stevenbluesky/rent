package com.rent.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

/**
 * @author liwenxiang
 * Date:2019/3/25
 * Time:9:30
 */
/**
 * 基于Commons Codec的URLBase64加密
 */
public class Base64URL {

     private static final String ENCODING = "UTF-8";
    /**
    * URLBase64加密
    */
    public static String encode(String data) throws UnsupportedEncodingException {
       byte[] encodedByte = Base64.encodeBase64URLSafe(data.getBytes(ENCODING));
     return new String(encodedByte, ENCODING);
    }

    /**
     * 接收byte数组
     * @param data
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encode(byte[] data) throws UnsupportedEncodingException {
        byte[] encodedByte = Base64.encodeBase64URLSafe(data);
        return new String(encodedByte, ENCODING);
    }

    /**
    * URLBase64解密
    */
    public static String decode(String data) throws UnsupportedEncodingException{
       byte[] decodedByte = Base64.decodeBase64(data.getBytes(ENCODING));
       return new String(decodedByte, ENCODING);
    }

    public static void main(String[] args) throws Exception{
        String message = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhcHBrZXkiOiJpU3VycGFzc0FwcCIsInRpbWVzdGFtcCI6IjEyMzQ1Njc4OTAifQ";
        String secret = "iSurpass2018";
        Mac hmacSha256 = Mac.getInstance("HmacSHA256");
        byte[] keyBytes = secret.getBytes("UTF-8");
        hmacSha256.init(new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA256"));
        System.out.println(Base64URL.encode(hmacSha256.doFinal(message.getBytes("UTF-8"))));
    }



}
