package com.theVoiceAround.music.utils;

import sun.misc.BASE64Encoder;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Taliy4h
 * @date 2021/3/5 11:38
 * @description 加密工具
 */
public class MD5Encryptor {
    /**
     * 使用BASE64 MD5进行加密
     */
    public static String EncoderByBaese64(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String strByMD5 = base64en.encode(md5.digest(str.getBytes("utf-8")));
        return strByMD5;
    }

    /**
     * 使用Spring DigestUtils MD5进行加密
     */
    public static String EncoderBySpring(String str) {
        //32位，小写
        String md532Lower = DigestUtils.md5DigestAsHex(str.getBytes());
        //32位，大写
        String md532Upper=md532Lower.toUpperCase();
        //16位，小写
//        String md516Lower =md532Lower.substring(8, 24);
        //16位，大写
//        String md516Upper=md532Lower.substring(8, 24).toUpperCase();
        return md532Upper;
    }

    /**
     * 判断用户密码是否正确
     * inputPassword 用户输入的密码
     * correctPassword 正确密码
     */
    public static boolean checkPassword(String inputPassword,String correctPassword) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        if(EncoderByBaese64(inputPassword).equals(correctPassword))
            return true;
        else
            return false;
    }
}
