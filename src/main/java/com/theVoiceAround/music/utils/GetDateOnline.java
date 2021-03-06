package com.theVoiceAround.music.utils;

import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
/**
 * @author Taliy4h
 * @date 2021/3/5 13:02
 * @description 获取在线时间
 */
public class GetDateOnline {
    /**
     * 在线获取北京时间
     * @return 在线北京时间
     * @throws Exception
     */
    public static String getDateOnline() throws Exception {
        URL url = new URL("http://bjtime.cn/");//取得资源对象
        URLConnection uc = url.openConnection();//生成连接对象
        uc.connect(); //发出连接
        long ld = uc.getDate(); //取得网站日期时间
        Date date = new Date(ld); //转换为标准时间对象
        return date.toString();//toString(); //格式为yyyy-MM-dd

    }
}
