package com.theVoiceAround.music.utils;

import org.springframework.cglib.beans.BeanMap;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author Taliy4h
 * @date 2021/3/4 22:10
 * @description 类型转换工具
 */
public class TypeConvert {
    /**
     * 将对象转换成Map
     *
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }
}