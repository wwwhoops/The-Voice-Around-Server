package com.theVoiceAround.music.config;

import com.theVoiceAround.music.utils.Consts;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Taliy4h
 * @date 2021/2/4 10:59
 * @description SpringBoot内置Tomcat配置虚拟目录映射
 */
@Configuration
public class FileConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //内置tomcat配置虚拟目录映射
        //歌手图片地址
        registry.addResourceHandler("/img/singerPic/**")
                .addResourceLocations("file:" + Consts.FILE_PATH + "/img/singerPic/");

        //歌曲图片地址
        registry.addResourceHandler("/img/songPic/**")
                .addResourceLocations("file:" + Consts.FILE_PATH + "/img/songPic/");

        //歌单图片地址
        registry.addResourceHandler("/img/songListPic/**")
                .addResourceLocations("file:" + Consts.FILE_PATH + "/img/songListPic/");

        //歌曲地址
        registry.addResourceHandler("/music/song/**")
                .addResourceLocations("file:" + Consts.FILE_PATH + "/music/song/");

        //客户端头像地址
        registry.addResourceHandler("/img/avatar/**")
                .addResourceLocations("file:" + Consts.FILE_PATH + "/img/avatar/");
    }
}
