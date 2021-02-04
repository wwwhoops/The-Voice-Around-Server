package com.theVoiceAround.music;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 热加载、热更新
 * 1. Ctrl + Shift + A -->搜索registry,找到registry...,然后找到compiler.automake.allow.when.app.running,勾选
 * 2. 执行快捷键 Ctrl + F9 自动编译进行热更新（新增接口不可以使用热加载）
 */
@SpringBootApplication
@MapperScan("com.theVoiceAround.music.mapper")
public class MusicApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicApplication.class, args);
		System.out.println("[SUCCESS] The voice around started ! ");
	}

}
